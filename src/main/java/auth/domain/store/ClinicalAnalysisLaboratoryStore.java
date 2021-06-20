package auth.domain.store;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.TestType;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ClinicalAnalysisLaboratoryStore class, which is responsible for creating the
 * ClinicalAnalysisLaboratoryStore object.
 *
 * @author Nuno Pinho (1190918)
 */
public class ClinicalAnalysisLaboratoryStore implements Serializable {

    /**
     * List which keeps all the ClinicalAnalysisLaboratory objects created in
     * the store.
     */
    private List<ClinicalAnalysisLaboratory> ClinicalAnalysisLaboratoryList;

    /**
     * Constructs the ClinicalAnalysisLaboratory object.
     *
     */
    public ClinicalAnalysisLaboratoryStore() {
        this.ClinicalAnalysisLaboratoryList = new ArrayList<>();
    }

    /**
     * @param name
     * @param address
     * @param phoneNumber
     * @param TIN
     * @param labID
     * @param TestTypes
     *
     * @return created ClinicalAnalysisLaboratory, back to the controller
     */
    public ClinicalAnalysisLaboratory registerClinicalAnalysisLaboratory(String name, String address, String phoneNumber, String TIN, String labID, List<TestType> TestTypes) {
        return new ClinicalAnalysisLaboratory(name, address, phoneNumber, TIN, labID, TestTypes);
    }

    /**
     * @param cal
     *
     * Saves the received ClinicalAnalysisLaboratory Object, after validating it
     * (then adding it).
     *
     * @return a boolean stating the success of saving the received Clinical
     * Analysis Laboratory (true if successful, false if it can't be saved)
     */
    public boolean saveClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal) {
        addClinicalAnalysisLaboratory(cal);
        guardarFicheiroBinario(this);
        return true;

    }

    /**
     * @param cal
     *
     * Validates if a certain ClinicalAnalysisLaboratory already exists in the
     * store list.
     *
     * @return a boolean stating if the Clinical Analysis Laboratory gotten
     * already exists in the list.
     */
    public boolean validateClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal) {
        if (cal == null) {
            return false;
        }
        return !this.ClinicalAnalysisLaboratoryList.contains(cal);
    }

    /**
     * @param cal
     *
     * Adds a certain ClinicalAnalysisLaboratory after validating if it exists
     * in the store list.
     *
     * @return a boolean stating if the ClinicalAnalysisLaboratory gotten was
     * successfully added
     */
    public boolean addClinicalAnalysisLaboratory(ClinicalAnalysisLaboratory cal) {
        if (!validateClinicalAnalysisLaboratory(cal)) {
            return false;
        }
        return this.ClinicalAnalysisLaboratoryList.add(cal);
    }

    /**
     * @param labID
     *
     * Searches for a specific ClinicalAnalysisLaboratory, through its labID.
     *
     * @return the found ClinicalAnalysisLaboratory Object or a null object,
     * stating that it was not found.
     */
    public ClinicalAnalysisLaboratory getClinicalAnalysisLaboratoryByID(String labID) {
        for (ClinicalAnalysisLaboratory cal : ClinicalAnalysisLaboratoryList) {
            if (cal.getLabID().equalsIgnoreCase(labID)) {
                return cal;
            }
        }
        return null;
    }

    /**
     *
     * @return all Clinical Analysis Laboratories that exist in the store list
     */
    public List<ClinicalAnalysisLaboratory> getAllClinicalAnalysisLaboratories() {
        return ClinicalAnalysisLaboratoryList;
    }
    public boolean guardarFicheiroBinario(ClinicalAnalysisLaboratoryStore store) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream("calStore.bin"));
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
