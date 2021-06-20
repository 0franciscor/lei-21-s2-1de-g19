package app.controller;

import app.domain.model.ClinicalAnalysisLaboratory;
import app.domain.model.Company;
import app.domain.model.TestType;
import auth.domain.store.ClinicalAnalysisLaboratoryStore;
import auth.mappers.ClinicalAnalysisLaboratoryMapper;
import auth.mappers.dto.ClinicalAnalysisLaboratoryDto;

import java.util.List;

/**
 * RegisterClinicalAnalysisLaboratoryController class, which is responsible for creating the ClinicalAnalysisLaboratoryController object.
 *
 * @author Nuno Pinho (1190918)
 */
public class RegisterClinicalAnalysisLaboratoryController {

    /**
     *
     * The app.
     *
     */
    private App app;

    /**
     *
     * The company.
     *
     */
    private Company company;

    /**
     *
     * The Clinical Analysis Laboratory.
     *
     */
    private ClinicalAnalysisLaboratory cal;

    /**
     *
     * The Storage of Clinical Analysis Laboratories.
     *
     */
    private ClinicalAnalysisLaboratoryStore calStore;

    public RegisterClinicalAnalysisLaboratoryController() {
        this.app = app.getInstance();
        this.company = app.getCompany();
        calStore = company.getClinicalAnalysisLaboratoryStore();
    }


    public boolean registerClinicalAnalysisLaboratory(String name, String address, String phoneNumber, String TIN, String labID, List<TestType> TestTypesList) {
        this.cal = calStore.registerClinicalAnalysisLaboratory(name, address, phoneNumber, TIN, labID, TestTypesList);
        return true;
    }

    public boolean saveClinicalAnalysisLaboratory() {
        return calStore.saveClinicalAnalysisLaboratory(cal);
    }

    /**
     * Gives all the Clinical Analysis Laboratories.
     *
     * @return calStore
     */
    public List<ClinicalAnalysisLaboratory> getAllClinicalAnalysisLaboratories() {
        return calStore.getAllClinicalAnalysisLaboratories();
    }

    public List<ClinicalAnalysisLaboratoryDto> getAllClinicalAnalysisLaboratoriesDto() {
        return ClinicalAnalysisLaboratoryMapper.toDto(calStore.getAllClinicalAnalysisLaboratories());
    }
}
