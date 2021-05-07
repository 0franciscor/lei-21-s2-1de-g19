package app.domain.model;

import auth.AuthFacade;
import auth.domain.store.ClientStore;
import auth.domain.store.ParameterCategoryStore;
import auth.domain.store.ParameterStore;
import auth.domain.store.TestTypeStore;
import auth.mappers.ClientMapper;
import auth.mappers.dto.ClientDto;
import org.apache.commons.lang3.StringUtils;

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

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.testTypeStore = new TestTypeStore();
        this.clientstore = new ClientStore();
        this.parameterStore = new ParameterStore();
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

}