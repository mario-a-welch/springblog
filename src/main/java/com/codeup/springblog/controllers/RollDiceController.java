package com.codeup.springblog.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String resultOfDiceRoll(@PathVariable int n, Model model){
        String result;

        Random random = new Random();

        int randomDiceRoll = random.nextInt(5 + 1) + 1;

        if (n == randomDiceRoll){
            result = "Your guess is CORRECT!";
        } else {
            result = "Your guess is INCORRECT!";
        }

        model.addAttribute("n", n);
        model.addAttribute("randomDiceRoll", randomDiceRoll);
        model.addAttribute("result", result);

        return "dice-roll-n";
    }
}
