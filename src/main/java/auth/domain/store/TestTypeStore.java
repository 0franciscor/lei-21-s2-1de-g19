package auth.domain.store;

import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import java.util.List;

public class TestTypeStore {

    private List<TestType> testTypeList;

    public TestType create(String code, String description, String collectingMethod, ParameterCategory parameterCategory){
        TestType newTest = new TestType(code, description, collectingMethod, parameterCategory);
        testTypeList.add(newTest);
        return newTest;
    }

    public TestType create(String code, String description, String collectingMethod, List<ParameterCategory> parameterCategoriesList){
        TestType createdTest = new TestType(code, description, collectingMethod, parameterCategoriesList);
        testTypeList.add(createdTest);
        return createdTest;
    }

    public boolean validate(TestType tt){
        if(tt == null)
            return false;
        return !this.testTypeList.contains(tt);
    }

    public boolean add(TestType tt) {
        if (!validate(tt))
            return false;
        return this.testTypeList.add(tt);
    }

    public List<TestType> getAllTestTypes(){
        return testTypeList;
    }
}
