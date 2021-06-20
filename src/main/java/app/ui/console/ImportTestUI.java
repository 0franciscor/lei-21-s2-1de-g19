package app.ui.console;

import app.controller.App;
import app.controller.ImportTestController;
import app.domain.model.Test;
import app.ui.console.utils.Utils;
import auth.domain.store.TestStore;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImportTestUI implements Runnable{

    private ImportTestController importTestController;

    public ImportTestUI(){
        this.importTestController = new ImportTestController();
    }

   public void run(){
        System.out.println("\nImport Test Section:\n\n");
        String pathName;
        do{
            pathName = Utils.readLineFromConsole("Please insert the name of the file.");
        }while(!new File(pathName).exists());

        List<List> receivedList;
        try {
            receivedList = importTestController.importFromFile(pathName);
        } catch (IOException e) {
            System.out.println("There was an error.");
            return;
        }

        List<String> successList = receivedList.get(0);
        List<String> insuccessList = receivedList.get(1);

       System.out.println("The following tests were created successfully:\n\n");
        for(String string : successList)
            System.out.println(string);

        System.out.println("Number of the tests which were not imported:");
        for(String string : insuccessList)
            System.out.println("Test" + string);

    }
}

