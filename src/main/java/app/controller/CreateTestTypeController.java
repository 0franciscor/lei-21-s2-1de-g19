package app.controller;

import java.util.List;

import app.domain.model.Company;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import auth.domain.store.TestTypeStore;

public class CreateTestTypeController {

    private App app;
    private Company company;
    private TestTypeStore ttStore;

    public CreateTestTypeController(){
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.ttStore = company.getTestTypeStore();
    }

    public TestType createTestType(String code, String description, String collectingMethod, ParameterCategory parameterCategory){
        return ttStore.create(code, description, collectingMethod, parameterCategory);
    }

    public TestType createTestType(String code, String description, String collectingMethod, List<ParameterCategory> parameterCategoriesList){
        return ttStore.create(code, description, collectingMethod, parameterCategoriesList);
    }

    public boolean saveTestType(TestType tt){
        return ttStore.save(tt);
    }
}
