package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import pl.coderslab.service.*;

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
    @Autowired
    MessageService messageService;


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

    @RequestMapping("/update")
    public String updateGet() {
        return "user/update";
    }

    @PostMapping("/update")
    public String updatePost(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "user/update";
        } else {
            if (user.getDateOfBirth().equals("")) {
                user.setDateOfBirth(null);
            }

            String[] message = userService.update(user);
            redirectAttributes.addFlashAttribute(message[0], message[1]);
            return "redirect:/user/profile?id=" + user.getId();
        }
    }

    @PostMapping("/photo")
    public String photo(@RequestParam("file") MultipartFile file, @AuthenticationPrincipal CurrentUser currentUser, RedirectAttributes redirectAttributes) {
        String[] message = userService.updatePhoto(currentUser.getId(), file);
        redirectAttributes.addFlashAttribute(message[0], message[1]);
        return "redirect:/user/profile?id=" + currentUser.getId();
    }

    @RequestMapping("/photoRemove")
    public String photoRemove(@AuthenticationPrincipal CurrentUser currentUser) {
        userService.removePhoto(currentUser.getId());
        return "redirect:/user/profile?id=" + currentUser.getId();
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

    @RequestMapping("/profile")
    public String otherUser(@RequestParam Long id, Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("other", userService.findUserById(id));
        Page<Tweet> tweetsPage = tweetService.findTweetsByUserId(id, new PageRequest(page, 10));
        model.addAttribute("tweetsPage", tweetsPage);
        return "user/profile";
    }

    @RequestMapping("/message")
    public String message(@RequestParam Long id, Model model) {
        model.addAttribute("userToId", id);
        return "user/message";
    }

    @PostMapping("/message")
    public String messagePost(@RequestParam Long userToId, @RequestParam String text, @AuthenticationPrincipal CurrentUser currentUser, RedirectAttributes redirectAttributes) {
        String[] message = messageService.sendMessage(text, userToId, currentUser.getId());
        redirectAttributes.addFlashAttribute(message[0], message[1]);
        return "redirect:/user/profile?id=" + userToId;
    }

    @RequestMapping("/messages")
    public String messagesPage(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("messagesSent", messageService.messagesSent(currentUser.getId()));
        model.addAttribute("messagesGot", messageService.messagesGot(currentUser.getId()));
        return "user/messages";
    }

    @RequestMapping("/changePassword")
    public String changePassword() {
        return "user/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePasswordPost(@RequestParam String password, @RequestParam String passwordConfirm, @AuthenticationPrincipal CurrentUser currentUser, RedirectAttributes redirectAttributes) {
        if (!userService.isPasswordCorrect(password, passwordConfirm)) {
            redirectAttributes.addFlashAttribute("messageDanger", "please confirm password correctly");
            return "user/changePassword";
        } else if (userService.isPasswordEmpty(password)) {
            redirectAttributes.addFlashAttribute("messageDanger", "password may not be empty");
            return "user/changePassword";
        } else {
            String[] message = userService.changePassword(password, currentUser.getId());
            redirectAttributes.addFlashAttribute(message[0], message[1]);
            return "redirect:/user/profile?id=" + currentUser.getId();
        }
    }

    @ModelAttribute("user")
    public User username(@AuthenticationPrincipal CurrentUser currentUser) {
        return userService.findUserById(currentUser.getId());
    }
}
