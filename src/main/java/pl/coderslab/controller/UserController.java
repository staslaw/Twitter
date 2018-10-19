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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;
import pl.coderslab.service.CommentService;
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
    @Autowired
    CommentService commentService;


    @PostMapping("/start")
    public String hello() {
        return "redirect:/user/main";
    }

    @RequestMapping("/main")
    public String hello(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Tweet> tweetsPage = tweetService.findAll(new PageRequest(page, 10));
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

    @RequestMapping("/update")
    public String updateGet() {
        return "user/update";
    }

    @PostMapping("/update")
    public String updatePost(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "user/update";
        } else {
            String[] message = userService.update(user);
            redirectAttributes.addFlashAttribute(message[0], message[1]);
            return "redirect:/user/profile";
        }
    }

    @PostMapping("/photo")
    public String photo(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal CurrentUser currentUser, RedirectAttributes redirectAttributes) {
        String[] message = userService.updatePhoto(currentUser.getId(), file);
        redirectAttributes.addFlashAttribute(message[0], message[1]);
        return "redirect:/user/profile";
    }

    @RequestMapping("/photoRemove")
    public String photoRemove(@AuthenticationPrincipal CurrentUser currentUser) {
        userService.removePhoto(currentUser.getId());
        return "redirect:/user/profile";
    }

    @RequestMapping("/tweetDetails")
    public String tweetDetails(@RequestParam Long id, Model model) {
        model.addAttribute("tweet", tweetService.findOneById(id));
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", commentService.findAllByTweet(id));
        return "user/tweet";
    }

    @PostMapping("/tweetDetails")
    public String tweetDetailsPost(@RequestParam String text, @RequestParam Long tweetId, @AuthenticationPrincipal CurrentUser currentUser) {
        commentService.saveComment(text, currentUser.getId(), tweetId);
        return "redirect:/user/tweetDetails?id=" + tweetId;
    }


    @ModelAttribute("user")
    public User username(@AuthenticationPrincipal CurrentUser currentUser) {
        return userService.findUserById(currentUser.getId());
    }
}
