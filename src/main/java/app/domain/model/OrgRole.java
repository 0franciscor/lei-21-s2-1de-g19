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
        this.designation = designation;
    }

}

