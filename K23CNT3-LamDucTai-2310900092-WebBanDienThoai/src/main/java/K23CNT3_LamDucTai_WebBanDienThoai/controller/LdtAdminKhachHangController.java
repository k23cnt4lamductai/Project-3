package K23CNT3_LamDucTai_WebBanDienThoai.controller;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtKhachHang;
import K23CNT3_LamDucTai_WebBanDienThoai.repository.LdtKhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/khach-hang")
public class LdtAdminKhachHangController {

    @Autowired
    private LdtKhachHangRepository ldtKhachHangRepository;

    // Hiển thị danh sách & Tìm kiếm
    @GetMapping
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        List<LdtKhachHang> list;
        if (keyword != null && !keyword.isEmpty()) {
            list = ldtKhachHangRepository.findByHoTenContainingIgnoreCase(keyword);
        } else {
            list = ldtKhachHangRepository.findAll();
        }
        model.addAttribute("listKH", list);
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalKH", ldtKhachHangRepository.count());
        return "admin/khachhang/index";
    }

    // Load form sửa thông tin
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        LdtKhachHang kh = ldtKhachHangRepository.findById(id).orElseThrow();
        model.addAttribute("khachHang", kh);
        return "admin/khachhang/edit";
    }

    // Xử lý cập nhật
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("khachHang") LdtKhachHang kh) {
        kh.setMaKH(id);
        ldtKhachHangRepository.save(kh);
        return "redirect:/admin/khach-hang";
    }

    // Xử lý xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        ldtKhachHangRepository.deleteById(id);
        return "redirect:/admin/khach-hang";
    }
}