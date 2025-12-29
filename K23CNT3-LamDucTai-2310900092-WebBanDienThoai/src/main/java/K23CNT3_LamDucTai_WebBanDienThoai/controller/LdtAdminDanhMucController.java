package K23CNT3_LamDucTai_WebBanDienThoai.controller;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtDanhMucSP;
import K23CNT3_LamDucTai_WebBanDienThoai.repository.LdtDanhMucSPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin/danhmuc")
public class LdtAdminDanhMucController {

    @Autowired
    private LdtDanhMucSPRepository repo;

    // Hiển thị danh sách danh mục
    @GetMapping
    public String list(Model model){
        model.addAttribute("dms", repo.findAll());
        return "admin/danhmuc/list";
    }

    // Hiển thị form thêm mới
    @GetMapping("/create")
    public String createForm(Model model){
        // Sử dụng "dms" để khớp với th:object="${dms}" trong form.html
        model.addAttribute("dms", new LdtDanhMucSP());
        return "admin/danhmuc/form";
    }

    // Lưu dữ liệu (Thêm hoặc Cập nhật)
    @PostMapping("/save")
    public String save(@ModelAttribute("dms") LdtDanhMucSP dm){
        repo.save(dm);
        return "redirect:/admin/danhmuc";
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        // Sử dụng "dms" để khớp với logic trong form.html
        model.addAttribute("dms", repo.findById(id).orElse(new LdtDanhMucSP()));
        return "admin/danhmuc/form";
    }

    // Xóa danh mục
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        repo.deleteById(id);
        return "redirect:/admin/danhmuc";
    }
}