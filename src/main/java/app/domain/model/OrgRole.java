package app.domain.model;

import app.controller.App;

import java.util.List;

public class OrgRole {
    public String designation;
    public OrgRole (String designation) {
        this.designation = designation;
    }
    public OrgRole createOrgRole(String designation) {
        OrgRole role = new OrgRole(designation);
        return role;
    }
    /*public List<OrgRole> getroleList() {
        return roleList;
    }
    public String getRoleID(OrgRole role) {
        return role.designation;
    }
    public OrgRole getOrgRoleByName(String name) {
        for (OrgRole role : roleList)
            if (role.designation.equalsIgnoreCase(name)) {
                return role;
            }
        return new OrgRole(null);
    }*/

}

