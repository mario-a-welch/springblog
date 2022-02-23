package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping(path = "/posts")
    @ResponseBody
    public String post() {
        return "posts index page";
    }

    @GetMapping(path = "/posts/{id}")
    @ResponseBody
    public String postId(@PathVariable long id) {
        return "view an individual post";
    }

    @GetMapping(path = "/posts/create")
    @ResponseBody
    public String postViewForm() {
        return "view the form for creating a post";
    }

    @PostMapping(path = "/posts/create")
    @ResponseBody
    public String postCreate() {
        return "create a new post";
    }
}
