package auth.mappers;

import app.domain.model.OrgRole;
import auth.mappers.dto.OrgRoleDto;

import java.util.ArrayList;
import java.util.List;

public class RolesMapper {
    public static List<OrgRoleDto> toDto(List<OrgRole> list) {
        List<OrgRoleDto> listDto = new ArrayList<>();
        for (OrgRole role : list ) {
            OrgRoleDto c = new OrgRoleDto(role);
            listDto.add(c);
        }

        return listDto;
    }
}
