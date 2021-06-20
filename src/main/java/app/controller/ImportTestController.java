package app.controller;

import app.domain.model.ImportTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * The ImportTest UI Controller
 *
 * @author Francisco Redol
 */
public class ImportTestController {

    /**
     * The importTest object
     */
    private ImportTest importTest;

    public ImportTestController(){
        this.importTest = new ImportTest();
    }

    /**
     * @param pathName of the file
     * @return if the file was found
     * @throws IOException if the file was not found
     */
    public List<List> importFromFile(String pathName) throws IOException{
        List<List> testsImportSuccessList;

        boolean fileExists = importTest.getFile(pathName);

        if(fileExists){
            testsImportSuccessList = importTest.readListFromCSV();
        } else
            throw new FileNotFoundException();

        return testsImportSuccessList;
    }
}
