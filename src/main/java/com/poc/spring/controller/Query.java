package com.poc.spring.controller;

import com.poc.spring.Model;
import com.poc.spring.ResponsModel;
import com.poc.spring.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class Query implements com.coxautodev.graphql.tools.GraphQLQueryResolver {

    @Autowired
    public TestService testService;

    public String readMe(){
        return "rishab graphql";
    }

    public String readMeWithParam(String name   ){
        return "MR"+ name +"graphql";
    }

    public ResponsModel requestBodyTest(Model model){

        return  testService.populateData(model);
    }
}
