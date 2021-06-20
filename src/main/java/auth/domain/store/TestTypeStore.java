package auth.domain.store;

import app.domain.model.Test;
import app.domain.shared.ExternalModule;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * TestTypeStore class, which is responsible for creating the TestTypeStore object.
 *
 * @author Francisco Redol (1201239)
 */
public class TestTypeStore implements Serializable {

    /**
     * List which keeps all the TestType objects created in the store.
     */
    private List<TestType> testTypeList;

    /**
     * Builds the TestTypeStore object.
     *
     */
    public TestTypeStore() {
        this.testTypeList = new ArrayList<>();
    }

    /**
     * @param code
     * @param description
     * @param collectingMethod
     * @param parameterCategoriesList
     *
     * Invokes the TestType Builder.
     *
     * @return created TestType, back to the controller
     */
    public TestType createTestType(String code, String description, String collectingMethod, List<ParameterCategory> parameterCategoriesList, ExternalModule externalModule){
        return new TestType(code, description, collectingMethod, parameterCategoriesList, externalModule);
    }

    /**
     * @param tt
     *
     * Saves the received TestType Object, after validating it (then adding it).
     *
     * @return a boolean stating the success of saving the received TestType (true if successful, false if it can't save)
     */
    public boolean saveTestType(TestType tt){
        addTestType(tt);
        guardarFicheiroBinario(this);
        return true;
    }

    /**
     * @param tt
     *
     * Validates if a certain TestType already exists in the store list.
     *
     * @return a boolean stating if the TestType gotten by parameter already exists in the list.
     */
    public boolean validateTestType(TestType tt){
        if(tt == null)
            return false;
        return !this.testTypeList.contains(tt);
    }

    /**
     * @param tt
     *
     * Adds a certain TestType after validating if it exists in the store list.
     *
     * @return a boolean stating if the TestType gotten by parameter was successfully added
     */
    public boolean addTestType(TestType tt) {
        if (!validateTestType(tt))
            return false;
        return this.testTypeList.add(tt);
    }

    /**
     * @param code
     *
     * Searches for a specific TestType, through a sent code.
     *
     * @return the found TestTypeObject or a null object, stating that
     * it was not found.
     */
    public TestType getTestTypeByCode(String code) {
        for (TestType tt : testTypeList) {
            if (tt.getCode().equalsIgnoreCase(code)) {
                return tt;
            }
        }
        return null;
    }

    /**
     *
     * @return all TestTypes that exist in the store list
     */
    public List<TestType> getAllTestTypes(){
        return testTypeList;
    }
    public boolean guardarFicheiroBinario(TestTypeStore store) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("testTypeStore.bin"));
            try {
                out.writeObject(store);
                return true;
            } finally {
                out.close();
            }
        } catch (IOException ex) {
            return false;
        }
    }
}