package auth.domain.store;

import app.domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class TestParameterStore {

    /**
     * List which keeps all the TestParameter objects created in the store.
     */
    private List<ParameterResult> parameterResultList;

    /**
     * Builds the TestParameterStore object.
     *
     */
    public TestParameterStore() {
        this.parameterResultList = new ArrayList<>();
    }

    /**
     * @param parameter
     * @param tpr
     *
     * Invokes the TestParameter Builder.
     *
     * @return created TestParameter, back to the controller
     */
    public ParameterResult createTestParameter(Parameter parameter, ParameterResult tpr){
        return new ParameterResult(parameter, tpr);
    }

    /**
     * @param parameterResult
     *
     * Saves the received TestParameter Object, after validating it (then adding it).
     *
     * @return a boolean stating the success of saving the received TestParameter (true if successful, false if it can't save)
     */
    public boolean saveTestParameter(ParameterResult parameterResult){
        return addTestParameter(parameterResult);
    }

    /**
     * @param parameterResult
     *
     * Validates if a certain TestParameter already exists in the store list.
     *
     * @return a boolean stating if the TestParameter gotten by parameter already exists in the list.
     */
    public boolean validateTestParameter(ParameterResult parameterResult){
        if(parameterResult == null)
            return false;
        return !this.parameterResultList.contains(parameterResult);
    }

    /**
     * @param parameterResult
     *
     * Adds a certain TestParameter after validating if it exists in the store list.
     *
     * @return a boolean stating if the TestParameter gotten by parameter was successfully added
     */
    public boolean addTestParameter(ParameterResult parameterResult) {
        if (!validateTestParameter(parameterResult))
            return false;
        return this.parameterResultList.add(parameterResult);
    }

    /**
     * @param code
     *
     * Searches for a specific TestParameter, through its code.
     *
     * @return the found TestParameterObject or a null object, stating that
     * it was not found.
     */
    public ParameterResult getTestParameterByCode(String code) {
        for (ParameterResult parameterResult : parameterResultList) {
            if (parameterResult.getParameter().getCode().equalsIgnoreCase(code)) {
                return parameterResult;
            }
        }
        return new ParameterResult(null, null);
    }

    /**
     *
     * @return all TestParameters that exist in the store list
     */
    public List<ParameterResult> getAllTestParameters(){
        return parameterResultList;
    }
}
