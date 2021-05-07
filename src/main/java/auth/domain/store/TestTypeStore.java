package auth.domain.store;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

public class TestTypeStore {

    private List<TestType> testTypeList;

    public TestTypeStore() {
        this.testTypeList = new ArrayList<>();
    }

    public TestType createTestType(String code, String description, String collectingMethod, ParameterCategory parameterCategory){
        return new TestType(code, description, collectingMethod, parameterCategory);
    }

    public TestType createTestType(String code, String description, String collectingMethod, List<ParameterCategory> parameterCategoriesList){
        return new TestType(code, description, collectingMethod, parameterCategoriesList);
    }

    public boolean saveTestType(TestType tt){
        return addTestType(tt);
    }

    public boolean validateTestType(TestType tt){
        if(tt == null)
            return false;
        return !this.testTypeList.contains(tt);
    }

    public boolean addTestType(TestType tt) {
        if (!validateTestType(tt))
            return false;
        return this.testTypeList.add(tt);
    }

    public List<TestType> getAllTestTypes(){
        return testTypeList;
    }
}