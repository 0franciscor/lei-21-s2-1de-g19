package app.domain.model;

import org.apache.commons.lang3.StringUtils;
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
        checkRoleRules(designation);
        if (designation.equalsIgnoreCase("SPEC DOCTOR"))
            this.designation = "SPEC DOCTOR";
        if (designation.equalsIgnoreCase("MED LAB TECH"))
            this.designation = "MED LAB TECH";
        if (designation.equalsIgnoreCase("ADMINISTRATOR"))
            this.designation = "ADMINISTRATOR";
        if (designation.equalsIgnoreCase("RECEPTIONIST"))
            this.designation = "RECEPTIONIST";
        if (designation.equalsIgnoreCase("LAB COORDINATOR"))
            this.designation = "LAB COORDINATOR";
        if (designation.equalsIgnoreCase("CLINICALCHEMTECH"))
            this.designation = "CLINICALCHEMTECH";
        if (designation.equalsIgnoreCase("CLIENT"))
            this.designation = "CLIENT";
    }
    /**
     * Method responsible for checking the acceptance criteria for the Employee's role.
     *
     * @param role Employee's role.
     *
     *
     */
    public void checkRoleRules(String role) {

        if (StringUtils.isBlank(role))
            throw new IllegalArgumentException("Name cannot be blank.");
        if ( role.length() > 18 )
            throw new IllegalArgumentException("Role must not have more than 18 chars.");
    }

}

