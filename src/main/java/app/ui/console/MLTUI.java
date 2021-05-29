package app.ui.console;


import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MLTUI implements Runnable{
    public MLTUI(){}

    public void run(){
        List<MenuItem> options = new ArrayList<MenuItem>();
        try {
            options.add(new MenuItem("Record samples in a test", new RecordSamplesUI()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int option =0;
        do{
            boolean exceptionThrown=false;
            try{
                option = Utils.showAndSelectIndex(options, "\n\nMedical Lab Technician Menu:");
            }catch (Exception e){
                System.out.println("\n\nUnavailable option.");
                exceptionThrown=true;
            }
            if((option >=0) && (option < options.size()) && !exceptionThrown)
                options.get(option).run();
        }while(option !=-1);
    }

}
