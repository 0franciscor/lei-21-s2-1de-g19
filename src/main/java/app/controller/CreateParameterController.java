package app.controller;

import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import auth.domain.store.ParameterStore;
import auth.mappers.ParameterCategoryMapper;
import auth.mappers.dto.ParameterCategoryDto;

import java.util.List;

public class CreateParameterController {

    private App app;
    private Company company;
    private ParameterStore parameterStore;
    private ParameterCategoryStore pcStore;
    private Parameter parameter;
    private List<ParameterCategory> listCategories;
    private List<ParameterCategoryDto> categoriesNamesDto;
    private String pcat;

    public CreateParameterController(){
        this.app=app.getInstance();
        this.company=app.getCompany();
        this.parameterStore=company.getParameterStore();
        this.pcStore=company.getParameterCategoryStore();
        listCategories=pcStore.getAllParameterCategories();
        categoriesNamesDto= ParameterCategoryMapper.toDto(listCategories);

    }

    public List<ParameterCategoryDto> toDto(){
        return categoriesNamesDto;
    }

    public boolean createParameter(String code, String description, String designation, ParameterCategoryDto catCode){
        ParameterCategory pcat = pcStore.getParameterCategoryByCode(catCode);
        this.parameter=parameterStore.create(code, description, designation, pcat);
        return true;
    }

    public boolean saveParameter(Parameter parameter){
        return this.parameterStore.saveParameter(parameter);
    }

    public List<Parameter> getAllParameters(){
        return parameterStore.getAllParameters();
    }


}
