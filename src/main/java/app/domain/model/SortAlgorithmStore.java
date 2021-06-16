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
    public List<ClientDto> orderClients (int criteria, List<ClientDto> clientDtoList) {
        if (criteria == 1)
            return getAlgoritmo1().showList(clientDtoList);
        if (criteria == 2)
            return getAlgoritmo2().showList(clientDtoList);
        return null;
    }

    public SortByNameAlgorithm getAlgoritmo1() throws IOException {
        File configFile = new File("config.properties");
        InputStream inputStream = new FileInputStream(configFile);
        Properties properties = new Properties();
        properties.load(inputStream);
        String key = properties.getProperty("algoritmo1");
        Class< ? > class1 = null;
        try {
            class1 = Class.forName(key);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SortByNameAlgorithm sortByNameAlgorithm = null;
        try {
            sortByNameAlgorithm = (SortByNameAlgorithm) class1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortByNameAlgorithm;
    }

    public SortByTINAlgorithm getAlgoritmo2() throws IOException {
        File configFile = new File("config.properties");
        InputStream inputStream = new FileInputStream(configFile);
        Properties properties = new Properties();
        properties.load(inputStream);
        String key = properties.getProperty("algoritmo2");
        Class< ? > class2 = null;
        try {
            class2 = Class.forName(key);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        SortByTINAlgorithm sortByTINAlgorithm = null;
        try {
            sortByTINAlgorithm = (SortByTINAlgorithm) class2.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sortByTINAlgorithm;
    }
}