package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.Role;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.service.CurrentUser;
import pl.coderslab.service.RoleService;
import pl.coderslab.service.TweetService;
import pl.coderslab.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    TweetService tweetService;


    @RequestMapping("")
    public String start() {
        if (roleService.findByName("ROLE_USER") == null) {
            roleService.save(new Role("ROLE_USER"));
        }
        return "home/start";
    }

    @RequestMapping("/log")
    public String log(Model model) {
        model.addAttribute("user", new User());
        return "home/log";
    }

    @PostMapping("/log")
    public String logPost(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return "home/log";
        } else {
            if (!userService.isExist(user.getEmail())) {
                String[] message = userService.saveUser(user);
                redirectAttributes.addFlashAttribute(message[0], message[1]);
                return "redirect:/";
            } else {
                redirectAttributes.addFlashAttribute("messageDanger", "Podaj inny email.");
                return "home/log";
            }
        }
    }

}
