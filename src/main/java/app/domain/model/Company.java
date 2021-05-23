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
    private ClinicalAnalysisLaboratoryStore calStore;
    private ParameterStore parameterStore;
    private ParameterCategoryStore parameterCategoryStore;
    private ReportStore reportStore;
    private EmpStore employeeStore;
    private List<OrgRole> roleList;
    private OrgRole orgRole;
    public int numEmp;
    public int numTeste;
    private TestStore testStore;

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.testTypeStore = new TestTypeStore();
        this.parameterCategoryStore = new ParameterCategoryStore();
        this.clientstore = new ClientStore();
        this.calStore = new ClinicalAnalysisLaboratoryStore();
        this.parameterStore = new ParameterStore();
        this.employeeStore = new EmpStore();
        this.reportStore = new ReportStore();
        this.roleList = new ArrayList<OrgRole>();
        this.roleList.add(new OrgRole("SPEC DOCTOR"));
        this.roleList.add(new OrgRole("MED LAB TECH"));
        this.roleList.add(new OrgRole("RECEPCIONIST"));
        this.roleList.add(new OrgRole("LAB COORDINATOR"));
        this.roleList.add(new OrgRole("ADMINISTRATOR"));
        this.roleList.add(new OrgRole("CLINICALCHEMTEC"));
        this.numEmp = 1;
        this.numTeste = 0;
        this.testStore = new TestStore();
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    /**
     * Returns the method toModel of the ClientMapper class that receives a dto as a parameter.
     *
     * @param dto Client's dto
     * @return method toModel of the ClientMapper class that receives a dto as a parameter
     */
    public Client registerClient(ClientDto dto) {
        return ClientMapper.toModel(dto);
    }

    /**
     * Returns the client store.
     *
     * @return client store
     */
    public ClientStore getClientStore (){
        return this.clientstore;
    }

    /**
     * Returns the TestTypeStore.
     *
     * @return parameter store.
     */
    public TestTypeStore getTestTypeStore(){
        return testTypeStore;
    }

    /**
     * Returns the parameter store.
     *
     * @return parameter store.
     */
    public ParameterStore getParameterStore(){
        return parameterStore;
    }

    /**
     * Returns the ParameterCategoryStore.
     *
     * @return parameter store.
     */
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
            if (c.designation.equalsIgnoreCase(name))
                return c;
        }
        return new OrgRole(null);
    }

    /**
     * Returns the clinical analysis laboratory store
     *
     * @return clinical analysis laboratory store
     */
    public ClinicalAnalysisLaboratoryStore getClinicalAnalysisLaboratoryStore() {
        return this.calStore;
    }

    /**
     * Returns the test store.
     *
     * @return test store
     */
    public TestStore getTestStore (){

        return this.testStore;
    }

    public ReportStore getReportStore (){

        return this.reportStore;
    }



}