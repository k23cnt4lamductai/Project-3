package K23CNT3_LamDucTai_WebBanDienThoai.controller;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.*;
import K23CNT3_LamDucTai_WebBanDienThoai.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/checkout")
public class LdtCheckoutController {

    @Autowired private LdtKhachHangRepository khRepo;
    @Autowired private LdtHoaDonRepository hdRepo;
    @Autowired private LdtChiTietHoaDonRepository cthdRepo;
    @Autowired private LdtSanPhamRepository spRepo;

    @PostMapping
    public String checkout(@RequestParam Integer customerId, HttpSession session){
        Map<Integer,Integer> cart = (Map<Integer,Integer>) session.getAttribute("CART");
        if(cart==null || cart.isEmpty()) return "redirect:/cart";

        LdtKhachHang kh = khRepo.findById(customerId).orElse(null);
        if(kh==null) return "redirect:/cart";

        LdtHoaDon hd = new LdtHoaDon();
        hd.setKhachHang(kh);
        hd.setNgayLap(Date.valueOf(LocalDate.now()));
        hd.setTongTien(0.0);
        hd = hdRepo.save(hd);

        double total = 0;
        for(Map.Entry<Integer,Integer> e : cart.entrySet()){
            LdtSanPham sp = spRepo.findById(e.getKey()).orElse(null);
            if(sp==null) continue;
            int qty = e.getValue();
            double price = sp.getGia();
            LdtChiTietHoaDon ct = new LdtChiTietHoaDon();
            ct.setHoaDon(hd);
            ct.setSanPham(sp);
            ct.setSoLuong(qty);
            ct.setDonGia(price);
            cthdRepo.save(ct);
            total += price * qty;
        }
        hd.setTongTien(total);
        hdRepo.save(hd);

        session.removeAttribute("CART");
        return "redirect:/?success";
    }
}