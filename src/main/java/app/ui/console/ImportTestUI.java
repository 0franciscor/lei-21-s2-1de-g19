package app.ui.console;

import app.controller.ImportTestController;
import app.ui.console.utils.Utils;

import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImportTestUI implements Runnable{

    private ImportTestController importTestController;
    private static JFileChooser chooser = new JFileChooser(".");

    public ImportTestUI(){
        this.importTestController = new ImportTestController();
    }

    public void run(){
        System.out.println("\n\n");
        String pathName;
        do{
            pathName = Utils.readLineFromConsole("Please insert the name of the file.");
        }while(!new File("\\"+pathName).exists());

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

        for(String string : insuccessList)
            System.out.println(string);

    }
}

