package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.News;
import pl.coderslab.service.NewsService;
import pl.coderslab.service.RoleService;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/news")
public class AdminNewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    RoleService roleService;

    @RequestMapping("")
    public String start(Model model) {
        model.addAttribute("news", newsService.findAll());
        return "admin/news";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        model.addAttribute("news", new News());
        model.addAttribute("roles", roleService.findAll());
        return "admin/newsForm";
    }

    @PostMapping("/add")
    public String add2(@Valid News news, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/newsForm";
        } else {
            String[] message = newsService.save(news);
            redirectAttributes.addFlashAttribute(message[0], message[1]);
            return "redirect:/admin/news";
        }
    }
}