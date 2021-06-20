package app.controller;

import app.domain.model.ImportTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class ImportTestController {

    private ImportTest importTest;

    public ImportTestController(){
        this.importTest = new ImportTest();
    }

    public List<List> importFromFile(String pathName) throws IOException{
        List<List> testsImportSuccessList;

        boolean fileExists = importTest.getFile("src\\" + pathName);

        if(fileExists){
            testsImportSuccessList = importTest.readListFromCSV();
        } else
            throw new FileNotFoundException();

        return testsImportSuccessList;
    }
}
