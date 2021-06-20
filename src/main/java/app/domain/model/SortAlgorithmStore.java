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
    public List<ClientDto> sort (String criteria, List<ClientDto> clientDtoList) throws IOException {
        if (criteria.equalsIgnoreCase("Name"))
            return getAlgoritmo().showListByName(clientDtoList);
        if (criteria.equalsIgnoreCase("TIN"))
            return getAlgoritmo().showListByTIN(clientDtoList);
        return null;
    }

    public SortAlgorithm getAlgoritmo() throws IOException {
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
        SortAlgorithm sortAlgorithm = null;
        try {
            sortAlgorithm = (SortAlgorithm) class1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortAlgorithm;
    }
}