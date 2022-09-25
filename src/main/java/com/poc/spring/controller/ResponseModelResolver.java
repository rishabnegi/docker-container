package com.poc.spring.controller;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.poc.spring.ResponsModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ResponseModelResolver implements GraphQLResolver<ResponsModel> {

    public String getFullName(ResponsModel model){

        return model.model.fName+model.model.lName;
    }

    public List<String> getCities(ResponsModel model , String filterCountry){

       if(filterCountry.equalsIgnoreCase("India")){
           return Arrays.asList("Mumbia","Delhi");
       }
       else{
           return Arrays.asList("LA","New York");
       }
    }
}
