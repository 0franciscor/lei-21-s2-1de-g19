package app.ui.console;

import app.controller.PerformanceController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;


/**
 * Represents the Performance UI.
 *
 * @author Rita Lello
 */
public class PerformanceUI implements Runnable{

    /**
     * The UI's Performance Controller.
     */
    private PerformanceController pc;

    /**
     * Allows access to performance controller methods.
     * @throws Exception
     */
    public PerformanceUI(){
        this.pc=new PerformanceController();
    }

    /**
     * It allows you to enter the data necessary to see the performance of the company and get the contiguous subsequence.
     */
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
                    int i;
                    do{
                        i=1;

                        System.out.println("Please insert the dates so we can only have a week to validate.");
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
                        beginningDate.setHours(Constants.MIN_HORAS);
                        endDate.setHours(Constants.MAX_HORAS);
                        if(endDate.before(beginningDate)){
                            dates=false;
                            System.out.println("There was a mistake while typing the dates, try again");
                        }
                        Date beginningDateAux=beginningDate;
                        if(beginningDateAux.getDate()!=endDate.getDate()||beginningDateAux.getMonth()!= endDate.getMonth()||beginningDateAux.getYear()!=endDate.getYear()){
                            do{
                                beginningDateAux.setDate(beginningDateAux.getDate()+1);
                                i++;
                            }while(beginningDateAux.getDate()!=endDate.getDate()||beginningDateAux.getMonth()!= endDate.getMonth()||beginningDateAux.getYear()!=endDate.getYear());
                        }
                    }while(!dates||i!=7);
                    System.out.println(String.format("Number of clients: %d",pc.getAllClients()));
                    System.out.println(String.format("Number of tests waiting for analysis: %d",pc.getAllTestsWaitResult(beginningDate, endDate)));
                    System.out.println(String.format("Number of tests waiting for diagnosis: %d",pc.getAllTestsWaitDiagnosis(beginningDate, endDate)));
                    System.out.println(String.format("Number of tests performed: %d",pc.getAllTestsValidated(beginningDate, endDate)));
                    List<Date> dateListAux = Utils.getDays(endDate,i);
                    List<Date> dateList=new ArrayList<>();
                    for(Date date:dateListAux){
                        LocalDate d =Utils.convertToLocalDateViaInstant(date);
                        if(!d.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                            date.setHours(Constants.MIN_HORAS);
                            dateList.add(date);
                        }
                    }
                    int [] seq1 = new int[144];
                    int [] seq2 = new int[144];
                    int [] seq = new int [144];
                    Date aux;
                    Date aux1=beginningDate;
                    for(int j=0;j<seq.length;j++){
                        if(j<24){
                            aux=dateList.get(0);
                            aux1.setMinutes(beginningDate.getMinutes()+Constants.INTERVALO);
                            seq1[j]=pc.getAllTestsWaitResult(aux,aux1);
                            seq2[j]=pc.getAllTestsWaitDiagnosis(aux,aux1);
                            seq[j]=seq1[j]-seq2[j];
                        }else if(j>23&&j<48){
                            aux=dateList.get(1);
                            aux1.setMinutes(beginningDate.getMinutes()+Constants.INTERVALO);
                            seq1[j]=pc.getAllTestsWaitResult(aux,aux1);
                            seq2[j]=pc.getAllTestsWaitDiagnosis(aux,aux1);
                            seq[j]=seq1[j]-seq2[j];
                        }else if(j>47&&j<72){
                            aux=dateList.get(2);
                            aux1.setMinutes(beginningDate.getMinutes()+Constants.INTERVALO);
                            seq1[j]=pc.getAllTestsWaitResult(aux,aux1);
                            seq2[j]=pc.getAllTestsWaitDiagnosis(aux,aux1);
                            seq[j]=seq1[j]-seq2[j];
                        }else if(j>71&&j<96){
                            aux=dateList.get(3);
                            aux1.setMinutes(beginningDate.getMinutes()+Constants.INTERVALO);
                            seq1[j]=pc.getAllTestsWaitResult(aux,aux1);
                            seq2[j]=pc.getAllTestsWaitDiagnosis(aux,aux1);
                            seq[j]=seq1[j]-seq2[j];
                        }else if(j>95&&j<120){
                            aux=dateList.get(4);
                            aux1.setMinutes(beginningDate.getMinutes()+Constants.INTERVALO);
                            seq1[j]=pc.getAllTestsWaitResult(aux,aux1);
                            seq2[j]=pc.getAllTestsWaitDiagnosis(aux,aux1);
                            seq[j]=seq1[j]-seq2[j];
                        }else{
                            aux=dateList.get(5);
                            aux1.setMinutes(beginningDate.getMinutes()+Constants.INTERVALO);
                            seq1[j]=pc.getAllTestsWaitResult(aux,aux1);
                            seq2[j]=pc.getAllTestsWaitDiagnosis(aux,aux1);
                            seq[j]=seq1[j]-seq2[j];
                        }
                    }
                    List<String> option2 = new ArrayList<>();
                    option2.add("Benchmark Algorithm");
                    option2.add("BruteForce Algorithm");
                    int option3 = 0;
                    try {
                        option3 = Utils.showAndSelectIndex(option2,"\nSelect an option from the list.");
                    } catch (Exception e){
                        System.out.println("Invalid option");
                        return;
                    }
                    if(option3==0){
                        System.out.println(Arrays.toString(pc.getSubsequenceBenchmark(seq)));
                    }else if(option3==1){
                        System.out.println(Arrays.toString(pc.getSubsequenceBruteForce(seq)));
                    }

                }
            }
        }while (option!=-1);
    }
}
