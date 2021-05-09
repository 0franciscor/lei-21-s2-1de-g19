package auth.mappers.dto;

import app.domain.model.OrgRole;
/**
 * Represents an Organization Role Data Transfer object.
 *
 * @author Alexandre Soares
 */
public class OrgRoleDto {
    /**
     * The Organization Role Data Transfer object designation.
     */
    private String designation;
    /**
     * Builds a new Organization Role Data Transfer object.
     *
     * @param role an OrgRole object.
     *
     * @return OrgRoleDto an Organization Role Data Transfer object.
     */
    public OrgRoleDto(OrgRole role) {
        this.designation = role.designation;
    }
    /**
     * Method responsible to return the designation of this Organization Role Data Transfer object.
     *
     * @return String the designation of this Organization Role Data Transfer object.
     */
    public String getDesignation() {
        return this.designation;
    }
}
