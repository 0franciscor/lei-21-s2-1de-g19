package app.domain.model;

import auth.AuthFacade;
import auth.domain.store.*;
import auth.mappers.ClientMapper;
import auth.mappers.EmployeeMapper;
import auth.mappers.dto.ClientDto;
import auth.mappers.dto.EmployeeDto;
import auth.mappers.dto.OrgRoleDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static auth.mappers.RolesMapper.toDto;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {

    private String designation;
    private AuthFacade authFacade;
    private TestTypeStore testTypeStore;
    private ClientStore clientstore;
    private ParameterStore parameterStore;
    private ParameterCategoryStore parameterCategoryStore;
    private EmpStore employeeStore;
    private List<OrgRole> roleList;
    private OrgRole orgRole;
    public int numEmp;

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.testTypeStore = new TestTypeStore();
        this.parameterCategoryStore = new ParameterCategoryStore();
        this.clientstore = new ClientStore();
        this.parameterStore = new ParameterStore();
        this.employeeStore = new EmpStore();
        this.roleList = new ArrayList<OrgRole>();
        this.roleList.add(new OrgRole("Specialist Doctor"));
        this.roleList.add(new OrgRole("Medical Lab Technician"));
        this.roleList.add(new OrgRole("Recepcionist"));
        this.roleList.add(new OrgRole("Lab Coordinator"));
        this.numEmp = 1;


    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public Client registerClient(ClientDto dto) {
        return ClientMapper.toModel(dto);
    }

    public ClientStore getClientStore (){
        return this.clientstore;
    }

    public TestTypeStore getTestTypeStore(){
        return testTypeStore;
    }

    public ParameterStore getParameterStore(){
        return parameterStore;
    }

    public ParameterCategoryStore getParameterCategoryStore(){
        return parameterCategoryStore;
    }
    public List<OrgRole> getRoles() {
        return roleList;
    }
    public EmpStore getEmployeeStore() {
        return this.employeeStore.getEmployeeStore();
    }
    public Employee createEmployee(EmployeeDto empDto) {
        return EmployeeMapper.toModel(empDto);
    }

    public OrgRole getOrgRoleByName(String name) {
        for (OrgRole c : roleList) {
            if (c.designation == name)
                return c;
        }
        return new OrgRole(null);
    }

}