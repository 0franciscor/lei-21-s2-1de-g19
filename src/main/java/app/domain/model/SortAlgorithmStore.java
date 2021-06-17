package app.domain.model;

import app.controller.App;
import auth.mappers.dto.ClientDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class SortAlgorithmStore {
    private Company company;

    public SortAlgorithmStore() {
        company = App.getInstance().getCompany();
    }
    public List<ClientDto> orderClients (int criteria, List<ClientDto> clientDtoList) throws IOException {
        if (criteria == 1)
            return getAlgoritmo().showListByName(clientDtoList);
        if (criteria == 2)
            return getAlgoritmo().showListByTIN(clientDtoList);
        return null;
    }

    public SortAlgorithm1 getAlgoritmo() throws IOException {
        File configFile = new File("config.properties");
        InputStream inputStream = new FileInputStream(configFile);
        Properties properties = new Properties();
        properties.load(inputStream);
        String key = properties.getProperty("algoritmo");
        Class< ? > class1 = null;
        try {
            class1 = Class.forName(key);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SortAlgorithm1 sortAlgorithm1 = null;
        try {
            sortAlgorithm1 = (SortAlgorithm1) class1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortAlgorithm1;
    }
}