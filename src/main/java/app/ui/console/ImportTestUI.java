package app.ui.console;

import app.controller.App;
import app.controller.ImportTestController;
import app.domain.model.Test;
import app.ui.console.utils.Utils;
import auth.domain.store.TestStore;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImportTestUI implements Runnable{

    private ImportTestController importTestController;

    public ImportTestUI(){
        this.importTestController = new ImportTestController();
    }

   public void run(){
        System.out.println("\n\n");
        String pathName;
        do{
            pathName = Utils.readLineFromConsole("Please insert the name of the file.");
        }while(!new File("src\\" + pathName).exists());

        List<List> receivedList;
        try {
            receivedList = importTestController.importFromFile(pathName);
        } catch (IOException e) {
            System.out.println("There was an error.");
            return;
        }

        List<String> successList = receivedList.get(0);
        List<String> insuccessList = receivedList.get(1);

        for(String string : successList)
            System.out.println(string);

        TestStore testStore = App.getInstance().getCompany().getTestStore();

        System.out.println("olá edu");

        for(Test test : testStore.getValidatedTests())
            System.out.println(test);

//        for(Integer integer : insuccessList)
//            System.out.println(integer);

    }
}

