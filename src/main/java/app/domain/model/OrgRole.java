package app.domain.model;

import app.controller.App;

import java.util.List;
/**
 * Represents an Organization Role.
 *
 * @author Alexandre Soares
 */
public class OrgRole {
    /**
     * The Organization Role designation
     */
    public String designation;

    /**
     * Builds a new Organization Role object with its designation.
     *
     * @param designation Organization Role designation.
     *
     * @return OrgRole an Organization Role object.
     */
    public OrgRole (String designation) {
        if (designation.equalsIgnoreCase("SpecDoctor"))
            this.designation = "SPECIALIST DOCTOR";
        if (designation.equalsIgnoreCase("MedLabTech"))
            this.designation = "MEDICAL LAB TECHNICIAN";
        if (designation.equalsIgnoreCase("Administrator"))
            this.designation = "ADMINISTRATOR";
        if (designation.equalsIgnoreCase("Recepcionist"))
            this.designation = "RECEPCIONIST";
        if (designation.equalsIgnoreCase("Lab Coordinator"))
            this.designation = "LAB COORDINATOR";
        if (designation.equalsIgnoreCase("ClinicalChemTec"))
            this.designation = "CLINICAL CHEM TECH";
    }

}

