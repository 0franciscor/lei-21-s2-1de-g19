package app.controller;

import java.util.ArrayList;
import java.util.List;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import auth.domain.store.ParameterCategoryStore;
import auth.domain.store.TestTypeStore;
import auth.mappers.ParameterCategoryMapper;
import auth.mappers.dto.ParameterCategoryDto;

public class CreateTestTypeController {

    private App app;
    private Company company;
    private ParameterCategoryStore pcStore;
    private TestTypeStore ttStore;
    private List<ParameterCategory> listCategories;
    private List<ParameterCategoryDto> categoriesDto;
    private TestType tt;
    private ParameterCategory pc;

    public CreateTestTypeController(){
        this.app = app.getInstance();
        this.company = app.getCompany();
        this.ttStore = company.getTestTypeStore();
        this.pcStore = company.getParameterCategoryStore();
    }

    public List<ParameterCategoryDto> getParameterCategoryDto(){
        return ParameterCategoryMapper.toDto(pcStore.getAllParameterCategories());
    }

    public boolean createTestType(String code, String description, String collectingMethod, ParameterCategory parameterCategory){
        this.tt = ttStore.createTestType(code, description, collectingMethod, parameterCategory);
        return true;
    }

    public boolean createTestType(String code, String description, String collectingMethod, List<ParameterCategory> parameterCategoriesList){
        this.tt = ttStore.createTestType(code, description, collectingMethod, parameterCategoriesList);
        return true;
    }

    public boolean saveTestType(){
       return ttStore.saveTestType(tt);
    }

    public List<TestType> getAllTestTypes(){
        return ttStore.getAllTestTypes();
    }
}