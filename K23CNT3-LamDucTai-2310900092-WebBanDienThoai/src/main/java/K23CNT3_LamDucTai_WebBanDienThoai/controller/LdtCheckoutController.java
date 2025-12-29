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
    public String checkout(HttpSession session){
        // 1. Lấy giỏ hàng từ session
        Map<Integer,Integer> cart = (Map<Integer,Integer>) session.getAttribute("CART");
        if(cart==null || cart.isEmpty()) return "redirect:/cart";

        // 2. Lấy thông tin khách hàng từ session (Thay vì dùng @RequestParam)
        LdtKhachHang userSession = (LdtKhachHang) session.getAttribute("USER_SESSION");
        if(userSession == null) return "redirect:/user/login";

        // Tìm lại khách hàng trong DB để đảm bảo dữ liệu mới nhất
        LdtKhachHang kh = khRepo.findById(userSession.getMaKH()).orElse(null);
        if(kh == null) return "redirect:/cart";

        // 3. Tạo hóa đơn mới
        LdtHoaDon hd = new LdtHoaDon();
        hd.setKhachHang(kh);
        hd.setNgayLap(Date.valueOf(LocalDate.now()));
        hd.setTongTien(0.0);
        hd = hdRepo.save(hd);

        double total = 0;
        // 4. Lưu chi tiết hóa đơn
        for(Map.Entry<Integer,Integer> e : cart.entrySet()){
            LdtSanPham sp = spRepo.findById(e.getKey()).orElse(null);
            if(sp == null) continue;

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

        // 5. Cập nhật tổng tiền cuối cùng
        hd.setTongTien(total);
        hdRepo.save(hd);

        // 6. Xóa giỏ hàng sau khi đặt thành công
        session.removeAttribute("CART");
        return "redirect:/?success";
    }
}