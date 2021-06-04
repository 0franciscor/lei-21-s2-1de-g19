package app.controller;

import app.domain.model.Client;
import app.domain.model.Company;
import app.domain.model.Test;
import auth.domain.store.ClientStore;
import auth.mappers.dto.ClientDto;
import auth.mappers.dto.TestDto;

import java.util.List;

import static auth.mappers.ClientMapper.toDTO;
import static auth.mappers.TestMapper.toDTOTestMapper;

public class ConsultTestDetailsByClientController {
    public Company company;
    public ConsultTestDetailsByClientController(){
        this.company = App.getInstance().getCompany();
    }
    /*
    public List<ClientDto> orderClients(String criteria) {
        ClientStore clientStore = company.getClientStore();
        List<Client> clientList = clientStore.getClientList();
        List<ClientDto> clientDtoList = toDTO(clientList);
        List<ClientDto> clientListDtoOrdered = orderClients(criteria, clientDtoList);
        return clientListDtoOrdered;
    }

     */
    public List<TestDto> presentTestsOfClient(ClientDto client) {
        List<Test> testListOfClient = client.getTestsOfClient();
        List<TestDto> testDtoListOfClient = toDTOTestMapper(testListOfClient);
        return testDtoListOfClient;
    }
    public String presentTestDetails(TestDto testDto) {
        return testDto.toString(3);
    }



    }
