package K23CNT3_LamDucTai_WebBanDienThoai.controller;

import K23CNT3_LamDucTai_WebBanDienThoai.entity.LdtSanPham;
import K23CNT3_LamDucTai_WebBanDienThoai.service.LdtSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/cart")
public class LdtCartController {

    @Autowired
    private LdtSanPhamService spService;

    @GetMapping
    public String viewCart(HttpSession session, Model model){
        Map<Integer,Integer> cart = (Map<Integer,Integer>) session.getAttribute("CART");
        List<LdtSanPham> items = new ArrayList<>();
        if(cart!=null){
            for(Integer id: cart.keySet()){
                LdtSanPham sp = spService.findById(id);
                if(sp!=null) items.add(sp);
            }
        }
        model.addAttribute("cartMap", cart==null? Collections.emptyMap() : cart);
        model.addAttribute("items", items);
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Integer id, @RequestParam(defaultValue="1") Integer qty, HttpSession session){
        Map<Integer,Integer> cart = (Map<Integer,Integer>) session.getAttribute("CART");
        if(cart==null) cart = new HashMap<>();
        cart.put(id, cart.getOrDefault(id,0) + qty);
        session.setAttribute("CART", cart);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Integer id, HttpSession session){
        Map<Integer,Integer> cart = (Map<Integer,Integer>) session.getAttribute("CART");
        if(cart!=null){
            cart.remove(id);
            session.setAttribute("CART", cart);
        }
        return "redirect:/cart";
    }
}