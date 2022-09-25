package com.poc.spring;

import com.poc.spring.entity.Person;
import com.poc.spring.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TestService {

    @Autowired
    private PeopleRepository peopleRepository;

    public ResponsModel populateData(Model requestModel){

        ResponsModel rm = new ResponsModel(requestModel);
        rm.testVariable ="test";
        return rm;
    }

    @Cacheable(cacheNames = "springCM")
    public List<String> getPersons(){

        peopleRepository.queryWithJpqlq();
        return Arrays.asList("rishab");
    }

    public List<Person> getPersonsTest(){

        peopleRepository.queryWithJpqlqq();
        return null;
    }
}
