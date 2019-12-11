package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("hello")
public class HelloController {

    // Handles request at path /hello
//    @GetMapping("hello")
//    @ResponseBody
//    public String hello() {
//        return "Hello, Spring!";
//    }

    // lives at /hello/goodbye
    @GetMapping("goodbye")
    @ResponseBody
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    // lives at /hello/form

    @GetMapping("form")
    public String helloForm() {
        return "form";
    }

    // Handles request of the form /hello?name=LaunchCode
    // lives at /hello/hello2

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "hello2")
    public String helloWithQueryParam(@RequestParam String name, Model model) {
        String theGreeting = "Hello, " + name + "!";
        model.addAttribute("greeting", theGreeting);
        return "hello";
    }

    // Handles requests of the form /hello/LaunchCode
    // lives at /hello (since removed "/hello" before {name}), else would be /hello/hello

    @GetMapping("{name}")
    public String helloWithPathParam(@PathVariable String name, Model model) {
        String theGreeting = "Hello, " + name + "!";
        model.addAttribute("greeting", theGreeting);
        return "hello";
    }

    @GetMapping("hello3")
    @ResponseBody
    public String helloFormText() {
        String html =
                "<html>" +
                        "<body>" +
                        "<form method = 'post' action = 'hello/helloText'>" +
                        "<input type = 'text' name = 'coder' />" +
                        "<input type = 'submit' value = 'Greet Me!' />" +
                        "</form>" +
                        "</body>" +
                        "</html>";
       return html;
    }

    @RequestMapping(value = "hello/helloText", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String helloText(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("hello-names")
    public String helloNames(Model model) {
        List<String> names = new ArrayList<>();
        names.add("LaunchCode");
        names.add("Java");
        names.add("JavaScript");
        model.addAttribute("names", names);
        return "hello-list";
    }



}
