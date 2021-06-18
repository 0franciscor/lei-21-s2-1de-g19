package app.controller;

import app.domain.model.*;
import auth.domain.store.ClientStore;
import auth.mappers.dto.ClientDto;
import auth.mappers.dto.TestDto;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static auth.mappers.ClientMapper.toDTO;
import static auth.mappers.TestMapper.toDTOTestMapper;

public class ConsultTestDetailsByClientController {
    public Company company;
    public SortAlgorithmStore sortAlgorithmStore;
    public ConsultTestDetailsByClientController(){
        this.company = App.getInstance().getCompany();
        this.sortAlgorithmStore = new SortAlgorithmStore();
    }

    public List<ClientDto> orderClients(String criteria) throws IOException {
        ClientStore clientStore = company.getClientStore();
        List<Client> clientList = clientStore.getClientList();
        List<ClientDto> clientDtoList = toDTO(clientList);
        List<ClientDto> clientListDtoOrdered = sortAlgorithmStore.sort(criteria, clientDtoList);
        return clientListDtoOrdered;
    }


    public List<TestDto> presentTestsOfClient(String tin) {
        ClientStore store = company.getClientStore();
        Client client = store.getClient(tin);
        List<Test> testListOfClient = client.getClientTestsList();
        List<TestDto> testDtoListOfClient = toDTOTestMapper(testListOfClient);
        return testDtoListOfClient;
    }
        public void presentTestDetails(String code) {
        Test test = company.getTestStore().getTestByCode(code);
        showData(test.getTestType(), test.getChemicalAnalysisDateTime(), test.getParameterResults());
    }

    public void showData(TestType testType, Date chemicalAnalysisDate, List<TestParameterResult> parameterResultList){
        System.out.printf("The test was performed on %s type is described as: %s", chemicalAnalysisDate, testType.getDescription());
        System.out.println("List of Parameter Results:");

        for(TestParameterResult parameterResult : parameterResultList){
            System.out.println(parameterResult.toString());
        }



    }
}
