package app.controller;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import auth.domain.store.ParameterCategoryStore;

import java.util.List;

public class CreateParameterCategoryController {

    private App app;
    private Company company;
    private ParameterCategoryStore pcStore;
    private ParameterCategory pc;

    public CreateParameterCategoryController(){
        this.app = app.getInstance();
        this.company = app.getCompany();
        pcStore = company.getParameterCategoryStore();
    }

    public boolean createParameterCategory(String name, String code){
        this.pc = pcStore.createParameterCategory(name, code);
        return true;
    }

    public boolean saveParameterCategory(){
        return pcStore.saveParameterCategory(pc);
    }

    public ParameterCategory getParameterCategoryByCode(String code){
        return pcStore.getParameterCategoryByCode(code);
    }

    public List<ParameterCategory> getAllParameterCategories(){
        return pcStore.getAllParameterCategories();
    }
}
