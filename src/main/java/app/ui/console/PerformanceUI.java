package app.ui.console;

import app.controller.PerformanceController;
import app.ui.console.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PerformanceUI implements Runnable{
    private PerformanceController pc;
    public PerformanceUI(){
        this.pc=new PerformanceController();
    }

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("See the overall performance of the company.", new ShowTextUI("You have chosen to se the performance of the company.")));


        int option=0;
        do{
            boolean exceptionThrown =false;
            try{
                option = Utils.showAndSelectIndex(options, "\n\nPerformance Menu:");
            }catch(Exception e){
                System.out.printf("\n\nUnavailable option.");
                exceptionThrown = true;
            }
            if(!exceptionThrown){
                if(option==0){
                    boolean dates;
                    Date beginningDate=null;
                    Date endDate=null;
                    do{
                        dates=true;
                        String beginningDateS=Utils.readLineFromConsole("Please insert the beginning date:");
                        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                        try {
                            beginningDate = formato.parse(beginningDateS);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String endDateS=Utils.readLineFromConsole("Please insert the end date:");
                        try {
                            endDate=formato.parse(endDateS);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        endDate.setHours(23);
                        endDate.setMinutes(59);
                        endDate.setSeconds(59);
                        if(endDate.before(beginningDate)){
                            dates=false;
                            System.out.println("There was a mistake while typing the dates, try again");
                        }
                    }while(!dates);
                    System.out.println(endDate);
                    System.out.println(String.format("Number of clients: %d",pc.getAllClients()));
                    System.out.println(String.format("Number of tests waiting for analysis: %d",pc.getAllTestsWaitResult(beginningDate, endDate)));
                    System.out.println(String.format("Number of tests waiting for diagnosis: %d",pc.getAllTestsWaitDiagnosis(beginningDate, endDate)));
                    System.out.println(String.format("Number of tests performed: %d",pc.getAllTestsValidated(beginningDate, endDate)));
                }
            }
        }while (option!=-1);
    }
}
