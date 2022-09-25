package com.poc.spring;

import java.util.List;

public class ResponsModel {
    public String fullName;
    public String testVariable;
    public Model model;
    public List<String> cities;

    public ResponsModel(){

    }

    public ResponsModel(Model model) {
        this.model = model;
    }
}
