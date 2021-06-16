package app.domain.model;

import app.controller.App;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SortAlgorithmStore {
    private Company company;

    public SortAlgorithmStore() {
        company = App.getInstance().getCompany();
    }

    public Algoritmo1 getAlgoritmo1() throws IOException {
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
        Algoritmo1 algoritmo1 = null;
        try {
            algoritmo1 = (Algoritmo1) class1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return algoritmo1;
    }

    public Algoritmo2 getAlgoritmo2() throws IOException {
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
        Algoritmo2 algoritmo2 = null;
        try {
            algoritmo2 = (Algoritmo2) class2.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return algoritmo2;
    }
}