package com.poc.spring.controller;

import com.poc.spring.TestService;
import com.poc.spring.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/login")
    public String testMethod(){
        return "rishab";
    }

    @GetMapping("/readme")
    public String readMe(){
        return "rishab rest";
    }


    @GetMapping("/authn")
    public String testMethosd(){
        return "rishab";
    }

    @GetMapping("/readmewithparam")
    public String readMeWithParam(String name){
        return "MR"+ name +"rest";
    }

    @GetMapping("/people")
    public List<String> getAllpeople(){
        return testService.getPersons();
    }

    @GetMapping("/people/query")
    public List<Person> getAllpeoplea(){
        return testService.getPersonsTest();
    }

}
