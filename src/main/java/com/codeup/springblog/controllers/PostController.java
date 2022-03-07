package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private PostRepository postDao;
    private UserRepository userDao;
    private EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService){
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping(path = "/posts")
    public String allPosts(Model model) {
        model.addAttribute("allPost", postDao.findAll());

        return "posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    public String postId(@PathVariable long id, Model model) {
        model.addAttribute("individualPost", postDao.getById(id));

        return "posts/show";
    }

    @GetMapping(path = "/posts/create")
    public String postViewForm(Model model) {
        model.addAttribute("newPost", new Post());
        return "posts/create";
    }

    @PostMapping(path = "/posts/create")
    public String postCreateForm(@ModelAttribute Post newPost) {
        newPost.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        emailService.prepareAndSend(newPost, "New post", "You posted to our blog");
        postDao.save(newPost);

        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model){
        Post postToEdit = postDao.getById(id);
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(postToEdit.getUser().getId() == loggedInUser.getId()) {
            model.addAttribute("postToEdit", postToEdit);
            return "posts/edit";
        } else {
            return "redirect:/posts";
        }
    }

    @PostMapping("/posts/{id}/edit")
        public String submitEditForm(@PathVariable long id, @ModelAttribute Post postToEdit) {
//        postDao.save(postToEdit);
        postToEdit.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        postDao.save(postToEdit);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }
}

