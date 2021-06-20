package app.domain.model;

import app.domain.shared.ExternalModule;
import app.domain.shared.ExternalModuleBloodWithKey;
import app.domain.shared.ExternalModuleBloodWithoutKey;
import app.domain.shared.ExternalModuleCovid;
import auth.AuthFacade;
import auth.domain.store.*;
import auth.mappers.ClientMapper;
import auth.mappers.EmployeeMapper;
import auth.mappers.dto.ClientDto;
import auth.mappers.dto.EmployeeDto;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company implements Serializable {

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
    public int numEmp;
    public int numTeste;
    private TestStore testStore;
    private List<ExternalModule> externalModuleList;
    private Notification notification;

    public Company(String designation){
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
        this.roleList.add(new OrgRole("RECEPTIONIST"));
        this.roleList.add(new OrgRole("LAB COORDINATOR"));
        this.roleList.add(new OrgRole("ADMINISTRATOR"));
        this.roleList.add(new OrgRole("CLINICALCHEMTECH"));
        this.roleList.add(new OrgRole("CLIENT"));

        this.numEmp = 1;
        this.numTeste = 0;
        this.testStore = new TestStore();
        this.externalModuleList = new ArrayList<>();
        externalModuleList.add(new ExternalModuleCovid());
        externalModuleList.add(new ExternalModuleBloodWithoutKey());
        externalModuleList.add(new ExternalModuleBloodWithKey());
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
     * Returns the number of employees'.
     *
     * @return client store
     */
    public int getNumEmp(){
        return this.numEmp;
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
    /**
     * Returns the report store.
     *
     * @return report store
     */
    public ReportStore getReportStore (){

        return this.reportStore;
    }
    
    /**
     * Class responsible for providing to other classes a file writing object.
     *
     * @return a Notification object which allows to write to a file.
     */
    public Notification getNotificationService() throws IOException {
        if(notification == null)
            return this.notification = new Notification();
        else
            return notification;
    }

    /**
     * @return a list of the existent External Modules.
     */
    public List<ExternalModule> getExternalModules(){
        return this.externalModuleList;
    }


    /**
     * @param sigLevel the significance level chosen by the user.
     * @param confLevel the confidence level chosen by the user.
     * @param hypTest the hypothesis test chosen by the user.
     *
     * Generates a covid-19 report that will be sent to the NHS.
     *
     * @return a NHSReport class
     */
    public NHSReport generateNHSReport (double sigLevel, double confLevel, boolean hypTest){
        return new NHSReport(sigLevel, confLevel, hypTest);
    }

    /**
     * @param sigLevel the significance level chosen by the user.
     * @param confLevel the confidence level chosen by the user.
     *
     * Generates a covid-19 report that will be sent to the NHS.
     *
     * @return a NHSReport class
     */
    public NHSReport generateNHSReport (double sigLevel, double confLevel){
        return new NHSReport(sigLevel, confLevel);
    }
    public void lerDeFicheiroBinario ()  {
        try {
            FileInputStream fileInputStream = new FileInputStream("testTypeStore.bin");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            this.testTypeStore = (TestTypeStore) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("clientStore.bin");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            this.clientstore = (ClientStore) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("calStore.bin");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            this.calStore = (ClinicalAnalysisLaboratoryStore) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("parameterStore.bin");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            this.parameterStore = (ParameterStore) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("parameterCategoryStore.bin");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            this.parameterCategoryStore =  (ParameterCategoryStore) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("reportStore.bin");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            this.reportStore = (ReportStore) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("empStore.bin");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            this.employeeStore = (EmpStore) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("orgRoleList.bin");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            this.roleList = (List<OrgRole>) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("testStore.bin");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            this.testStore = (TestStore) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
        try {
            FileInputStream fileInputStream = new FileInputStream("authFacadeData.bin");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            this.authFacade = (AuthFacade) inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
        }
    }


}

