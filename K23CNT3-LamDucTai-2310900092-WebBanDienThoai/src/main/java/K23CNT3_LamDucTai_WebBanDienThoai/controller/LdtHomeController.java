package K23CNT3_LamDucTai_WebBanDienThoai.controller;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtKhachHang;
import K23CNT3_LamDucTai_WebBanDienThoai.service.LdtSanPhamService;
import K23CNT3_LamDucTai_WebBanDienThoai.service.LdtDanhMucService;
import K23CNT3_LamDucTai_WebBanDienThoai.service.LdtKhachHangService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
public class LdtHomeController {

    @Autowired private LdtSanPhamService spService;
    @Autowired private LdtDanhMucService dmService;
    @Autowired private LdtKhachHangService khachHangService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("products", spService.findAll());
        model.addAttribute("dms", dmService.findAll());
        return "index";
    }

    @GetMapping("/user/login")
    public String showLoginForm(Model model) {
        model.addAttribute("khachHang", new LdtKhachHang());
        return "user/login";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute("khachHang") LdtKhachHang khachHang, HttpSession session, Model model) {
        Optional<LdtKhachHang> auth = khachHangService.login(khachHang.getUsername(), khachHang.getPassword());
        if (auth.isPresent()) {
            // Lưu đối tượng khách hàng vào USER_SESSION để trang Cart và Header dùng chung
            session.setAttribute("USER_SESSION", auth.get());
            return "redirect:/";
        } else {
            model.addAttribute("error", "Tài khoản hoặc mật khẩu không chính xác!");
            return "user/login";
        }
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Xóa sạch session khi đăng xuất
        return "redirect:/";
    }

    @GetMapping("/user/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("khachHang", new LdtKhachHang());
        return "user/register";
    }

    @PostMapping("/user/register")
    public String register(@ModelAttribute("khachHang") LdtKhachHang khachHang, Model model, RedirectAttributes ra) {
        if (khachHangService.isUsernameExist(khachHang.getUsername())) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "user/register";
        }
        khachHangService.registerKhachHang(khachHang);
        ra.addFlashAttribute("success", "Đăng ký thành công! Hãy đăng nhập.");
        return "redirect:/user/login";
    }
}