package auth.mappers;

import app.domain.model.OrgRole;
import auth.mappers.dto.OrgRoleDto;

import java.util.ArrayList;
import java.util.List;
/**
 * Class responsible for converting an Organization Role list to an Organization Role DTO list.
 *
 * @author Alexandre Soares
 */
public class RolesMapper {
    /**
     * Method responsible for converting an Organization Role list to an Organization Role DTO list.
     *
     * @param list a List of Organization Roles.
     *
     * @return listDto a List Organization Role DTO.
     */
    public static List<OrgRoleDto> toDto(List<OrgRole> list) {
        List<OrgRoleDto> listDto = new ArrayList<>();
        for (OrgRole role : list ) {
            OrgRoleDto c = new OrgRoleDto(role);
            listDto.add(c);
        }

        return listDto;
    }
}
