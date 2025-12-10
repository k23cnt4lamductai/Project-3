package K23CNT3_LdtDay8.controller;
import java.util.ArrayList;
import java.util.List;
import K23CNT3_LdtDay8.entity.ldtAuthor;
import K23CNT3_LdtDay8.entity.ldtBook;
import K23CNT3_LdtDay8.service.LdtAuthorService;
import K23CNT3_LdtDay8.service.LdtBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/ldtbooks"})
public class LdtBookController {
    @Autowired
    private LdtAuthorService ldtBookService;
    @Autowired
    private LdtAuthorService ldtAuthorService;

    @GetMapping
    public String getLdtBooks(Model model) {
        model.addAttribute("ldtBooks", this.LdtBookService.getAllldtBooks());
        return "ldtbooks/ldt-book-list";
    }

    @GetMapping({"/new"})
    public String showCreateFormLdtBook(Model model) {
        model.addAttribute("ldtBook", new ldtBook());
        model.addAttribute("ldtAuthors", this.ldtAuthorService.getAllLdtAuthors());
        return "ldtbooks/ldt-book-form";
    }

    @PostMapping({"/new"})
    public String createLdtBook(@ModelAttribute ldtBook ldtBook, @RequestParam List<Long> ldtAuthorIds, @RequestParam("imageBook") MultipartFile imageFile) {
        List<ldtAuthor> authors = new ArrayList(this.ldtAuthorService.findLdtAuthorById(ldtAuthorIds));
        ldtBook.setLdtAuthor(authors);
        this.ldtBookService.saveLdtBook(ldtBook);
        return "redirect:/ldtbooks";
    }
}
