package com.bsu.first.controller;

import com.bsu.first.model.WrapNumber;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DemoController {

//  @RequestMapping("/hello")
//  //@ResponseBody
//  public String helloGFG() {
//    return "";
//  }

//  @GetMapping("/main_1")
//  public String loginForm() {
//    return "main";
//  }
  @GetMapping("/main_1")
  public String numAction(@RequestParam(value ="num_1")String num ,Model model) {
    int numb = Integer.parseInt(num);
    ++numb;
    model.addAttribute("num", numb);
    return "main";
  }
  @GetMapping(value="/index")
  public String loginSubmit() {
    return "index";
  }
}