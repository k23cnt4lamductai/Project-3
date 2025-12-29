package K23CNT3_LamDucTai_WebBanDienThoai.controller;

import K23CNT3_LamDucTai_WebBanDienThoai.repository.LdtHoaDonRepository;
import K23CNT3_LamDucTai_WebBanDienThoai.repository.LdtChiTietHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/admin/order")
public class LdtAdminHoaDonController {

    @Autowired
    private LdtHoaDonRepository hdRepo;

    @Autowired
    private LdtChiTietHoaDonRepository cthdRepo;

    // Hiển thị danh sách hóa đơn
    @GetMapping
    public String list(Model model){
        // Sử dụng tên biến "order" cho danh sách để khớp với file list.html của bạn
        model.addAttribute("order", hdRepo.findAll());
        return "admin/order/list";
    }

    // Hiển thị chi tiết của một hóa đơn cụ thể
    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id, Model model){
        // Gửi thông tin hóa đơn đơn lẻ
        model.addAttribute("order", hdRepo.findById(id).orElse(null));

        // Lọc và gửi danh sách chi tiết sản phẩm thuộc hóa đơn đó
        model.addAttribute("details", cthdRepo.findAll().stream()
                .filter(d -> d.getHoaDon() != null && d.getHoaDon().getMaHD().equals(id))
                .toList());
        return "admin/order/view";
    }
}