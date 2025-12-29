package K23CNT3_LamDucTai_WebBanDienThoai.controller;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtSanPham;
import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtKhachHang;
import K23CNT3_LamDucTai_WebBanDienThoai.service.LdtSanPhamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/cart")
public class LdtCartController {

    @Autowired private LdtSanPhamService spService;

    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        // Lấy khách hàng từ USER_SESSION
        LdtKhachHang user = (LdtKhachHang) session.getAttribute("USER_SESSION");
        model.addAttribute("user", user);

        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("CART");
        if (cart == null) cart = new HashMap<>();

        List<LdtSanPham> items = new ArrayList<>();
        double total = 0.0;

        for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
            LdtSanPham sp = spService.findById(entry.getKey());
            if (sp != null) {
                items.add(sp);
                total += (sp.getGia() != null ? sp.getGia() : 0) * entry.getValue();
            }
        }

        model.addAttribute("items", items);
        model.addAttribute("cartMap", cart);
        model.addAttribute("total", total);
        return "cart";
    }

    @PostMapping("/update/{id}")
    public String updateQuantity(@PathVariable("id") Integer id, @RequestParam String action, HttpSession session) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("CART");
        if (cart != null && cart.containsKey(id)) {
            int qty = cart.get(id);
            if ("increase".equals(action)) cart.put(id, qty + 1);
            else if ("decrease".equals(action)) {
                if (qty > 1) cart.put(id, qty - 1);
                else cart.remove(id); // Nếu giảm xuống 0 thì xóa luôn
            }
            session.setAttribute("CART", cart);
        }
        return "redirect:/cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Integer id, @RequestParam(defaultValue = "1") Integer qty, HttpSession session) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("CART");
        if (cart == null) cart = new HashMap<>();
        cart.put(id, cart.getOrDefault(id, 0) + qty);
        session.setAttribute("CART", cart);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, HttpSession session) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("CART");
        if (cart != null) cart.remove(id);
        return "redirect:/cart";
    }
}