package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @RequestMapping(path = "/add/{number}/and/{num}", method = RequestMethod.GET)
    @ResponseBody
    public int add(@PathVariable int number, @PathVariable int num) {
        return number + num;
    }

    @RequestMapping(path = "/subtract/{number}/and/{num}", method = RequestMethod.GET)
    @ResponseBody
    public int subtract(@PathVariable int number, @PathVariable int num) {
        return number - num;
    }

    @RequestMapping(path = "/multiply/{number}/and/{num}", method = RequestMethod.GET)
    @ResponseBody
    public int multiply(@PathVariable int number, @PathVariable int num) {
        return number * num;
    }

    @RequestMapping(path = "/divide/{number}/and/{num}", method = RequestMethod.GET)
    @ResponseBody
    public int divide(@PathVariable int number, @PathVariable int num) {
        return number / num;
    }
}
