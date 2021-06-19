package app.controller;

import app.domain.model.ImportTest;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ImportTestController {

    private ImportTest importTest;

    public ImportTestController(){
        this.importTest = new ImportTest();
    }

    public String importFromFile(String pathName) throws IOException{
        String currentlyImportedTest;
        boolean fileExists = importTest.getFile(pathName);

        if(fileExists){
            currentlyImportedTest = importTest.readListFromCSV();
        } else
            throw new FileNotFoundException();

        return currentlyImportedTest;
    }
}
