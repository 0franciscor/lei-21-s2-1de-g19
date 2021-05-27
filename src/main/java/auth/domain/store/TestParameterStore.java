package auth.domain.store;

import app.domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class TestParameterStore {

    /**
     * List which keeps all the TestParameter objects created in the store.
     */
    private List<TestParameter> testParameterList;

    /**
     * Builds the TestParameterStore object.
     *
     */
    public TestParameterStore() {
        this.testParameterList = new ArrayList<>();
    }

    /**
     * @param parameter
     * @param tpr
     *
     * Invokes the TestParameter Builder.
     *
     * @return created TestParameter, back to the controller
     */
    public TestParameter createTestParameter(Parameter parameter, TestParameterResult tpr){
        return new TestParameter(parameter, tpr);
    }

    /**
     * @param testParameter
     *
     * Saves the received TestParameter Object, after validating it (then adding it).
     *
     * @return a boolean stating the success of saving the received TestParameter (true if successful, false if it can't save)
     */
    public boolean saveTestParameter(TestParameter testParameter){
        return addTestParameter(testParameter);
    }

    /**
     * @param testParameter
     *
     * Validates if a certain TestParameter already exists in the store list.
     *
     * @return a boolean stating if the TestParameter gotten by parameter already exists in the list.
     */
    public boolean validateTestParameter(TestParameter testParameter){
        if(testParameter == null)
            return false;
        return !this.testParameterList.contains(testParameter);
    }

    /**
     * @param testParameter
     *
     * Adds a certain TestParameter after validating if it exists in the store list.
     *
     * @return a boolean stating if the TestParameter gotten by parameter was successfully added
     */
    public boolean addTestParameter(TestParameter testParameter) {
        if (!validateTestParameter(testParameter))
            return false;
        return this.testParameterList.add(testParameter);
    }

    /**
     * @param code
     *
     * Searches for a specific TestParameter, through its code.
     *
     * @return the found TestParameterObject or a null object, stating that
     * it was not found.
     */
    public TestParameter getTestParameterByCode(String code) {
        for (TestParameter testParameter : testParameterList) {
            if (testParameter.getParameter().getCode().equalsIgnoreCase(code)) {
                return testParameter;
            }
        }
        return new TestParameter(null, null);
    }

    /**
     *
     * @return all TestParameters that exist in the store list
     */
    public List<TestParameter> getAllTestParameters(){
        return testParameterList;
    }
}
