package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.service.CurrentUser;
import pl.coderslab.service.TweetService;
import pl.coderslab.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TweetService tweetService;

    @PostMapping("/start")
    public String hello() {
        return "redirect:/user/main";
    }

//    @RequestMapping("/main")
//    public String hello(Model model, Pageable pageable) {
//        Page<Tweet> tweetsPage = tweetService.findAll(pageable);
//        model.addAttribute("tweetsPage", tweetsPage);
//        model.addAttribute("tweet", new Tweet());
//        return "user/user";
//    }

    @RequestMapping("/main")
    public String hello(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Tweet> tweetsPage = tweetService.findAll(new PageRequest(page, 4));
        model.addAttribute("tweetsPage", tweetsPage);
        model.addAttribute("tweet", new Tweet());
        return "user/user";
    }



    @PostMapping("/main")
    public String addTweet(@Valid Tweet tweet, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "user/user";
        } else {
            tweetService.save(tweet, currentUser.getId());
            return "redirect:/user/main";
        }
    }

    @RequestMapping("/profile")
    public String profile() {
        return "user/profile";
    }


    @ModelAttribute("user")
    public User username(@AuthenticationPrincipal CurrentUser currentUser) {
        return userService.findUserById(currentUser.getId());
    }
}
