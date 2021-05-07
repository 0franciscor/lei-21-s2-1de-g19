package auth.mappers.dto;

import app.domain.model.OrgRole;

public class OrgRoleDto {
    String designation;

    public OrgRoleDto(OrgRole role) {
        this.designation = role.designation;
    }

    public String getDesignation() {
        return this.designation;
    }
}
