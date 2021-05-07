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
        listCategories = pcStore.getAllParameterCategories();
        categoriesDto = ParameterCategoryMapper.toDto(listCategories);
    }

    public List<ParameterCategoryDto> getParameterCategoryDto(){
        return categoriesDto;
    }

    public boolean createTestType(String code, String description, String collectingMethod, ParameterCategoryDto parameterCategoryDto){
        this.pc = pcStore.getParameterCategoryByCode(parameterCategoryDto.getCode());
        this.tt = ttStore.createTestType(code, description, collectingMethod, pc);
        return true;
    }

    public boolean createTestType(String code, String description, String collectingMethod, List<ParameterCategoryDto> parameterCategoriesListDto){
        List<ParameterCategory> parameterCategoriesList = new ArrayList<>();
        for(ParameterCategoryDto parameterCategoryDto : parameterCategoriesListDto){
            this.pc = pcStore.getParameterCategoryByCode(parameterCategoryDto.getCode());
            parameterCategoriesList.add(pc);
        }

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