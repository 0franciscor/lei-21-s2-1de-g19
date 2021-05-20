package auth.domain.store;

import app.domain.model.Parameter;
import app.domain.model.Test;
import app.domain.model.TestType;

import java.util.ArrayList;
import java.util.List;

public class TestStore {

    List<Test> TestList;
    private String citizenID;

    public TestStore (){

        this.TestList = new ArrayList<>();
    }

    public Test createTest (TestType testType, List<Parameter> parameters, String citizenID ){

        return new Test(testType,parameters, citizenID);
    }

    public boolean saveTest (Test test){

        if (validateTest(test)){
            addTest(test);
            return true;
        }
        return false;
    }

    public void addTest (Test test){

        TestList.add(test);
    }

    public boolean validateTest (Test test){

        if (TestList.contains(test))
            return false;
        else
            return true;
    }
    public List<Test> getAnalyzedTests() {
        List<Test> analyzedTestsList = new ArrayList<Test>();
        for (Test c : TestList)
            if (c.getStatus() == "Analyzed Test")
                analyzedTestsList.add(c);

        return analyzedTestsList;
    }
    public Test getTestByCode(String testcode) {
        for(Test c : TestList)
            if(c.getCode() == testcode)
                return c;

        return new Test();
    }
}
