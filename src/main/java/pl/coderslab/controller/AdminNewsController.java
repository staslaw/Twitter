package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.News;
import pl.coderslab.repository.NewsRepository;
import pl.coderslab.repository.RoleRepository;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin/news")
public class AdminNewsController {
    @Autowired
    NewsRepository newsRepository;
    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("")
    public String start(Model model) {
        model.addAttribute("news", newsRepository.findAll());
        return "admin/news";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("news", new News());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/newsForm";
    }

    @PostMapping("/add")
    public String add2(@Valid News news, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/newsForm";
        } else {
            news.setDate(LocalDate.now());
            try {
                newsRepository.save(news);
                redirectAttributes.addFlashAttribute("message", "Wiadomość dodana prawidłowo.");
                return "redirect:/admin/news";
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("messageDanger", "Wiadomość nie została dodana.");
                return "redirect:/admin/news";
            }
        }
    }


}
