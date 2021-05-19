package app.controller;


import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Parameter;
import app.domain.model.TestType;
import auth.domain.store.ClientStore;
import auth.domain.store.ParameterStore;
import auth.domain.store.TestTypeStore;
import auth.mappers.ClientMapper;
import auth.mappers.ParametersMapper;
import auth.mappers.TestTypeMapper;
import auth.mappers.dto.ClientDto;
import auth.mappers.dto.ParametersDto;
import auth.mappers.dto.TestTypeDto;
import java.util.List;


public class RegisterTestController {


    private App app;
    private Company company;
    public ClientStore clientstore;


    public RegisterTestController () {
        this.app = App.getInstance();
        this.company = app.getCompany();
        this.clientstore = company.getClientStore();

    }

    public ClientDto getClient (String citizenID) {

        ClientStore store = company.getClientStore();
        Client cl = store.getClient(citizenID);
        ClientDto cl1 = ClientMapper.toDto(cl);


        return cl1;

    }

    public List<TestTypeDto> getAllTestType (){

        TestTypeStore store = company.getTestTypeStore();
        List<TestType> listTestType = store.getAllTestTypes();
        List<TestTypeDto> listTestTypeDto = TestTypeMapper.toDto(listTestType);

        return listTestTypeDto;
    }

    public List<ParametersDto> getAllParametersByTestType (TestTypeDto testTypeDto){

        ParameterStore store = company.getParameterStore();
        List<Parameter> listParameters = store.getAllParametersByTestType(testTypeDto);
        List<ParametersDto> listParametersDto = ParametersMapper.toDto(listParameters);

        return listParametersDto;
    }
}
