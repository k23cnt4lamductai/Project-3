package K23CNT3_LamDucTai_WebBanDienThoai.controller;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtSanPham;
import K23CNT3_LamDucTai_WebBanDienThoai.repository.LdtSanPhamRepository;
import K23CNT3_LamDucTai_WebBanDienThoai.repository.LdtDanhMucSPRepository;
import K23CNT3_LamDucTai_WebBanDienThoai.util.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin/product")
public class LdtAdminProductController {

    @Autowired private LdtSanPhamRepository spRepo;
    @Autowired private LdtDanhMucSPRepository dmRepo;
    @Autowired private FileStorageService fileService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("products", spRepo.findAll());
        return "admin/product/list";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("productObj", new LdtSanPham());
        model.addAttribute("dms", dmRepo.findAll());
        return "admin/product/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("productObj") LdtSanPham product,
                       @RequestParam("imageFile") MultipartFile file){
        try {
            if(file != null && !file.isEmpty()){
                String filename = fileService.store(file);
                product.setHinhAnh(filename);
            } else if (product.getMaSP() != null) {
                LdtSanPham old = spRepo.findById(product.getMaSP()).orElse(null);
                if(old != null) product.setHinhAnh(old.getHinhAnh());
            }
            spRepo.save(product);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("productObj", spRepo.findById(id).orElse(new LdtSanPham()));
        model.addAttribute("dms", dmRepo.findAll());
        return "admin/product/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        spRepo.deleteById(id);
        return "redirect:/admin/product";
    }
}