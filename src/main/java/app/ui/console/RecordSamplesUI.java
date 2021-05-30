package app.ui.console;

import app.controller.RecordSamplesController;
import app.ui.console.utils.Utils;
import auth.mappers.dto.TestDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Record Samples UI.
 *
 * @author Rita Lello
 */
public class RecordSamplesUI implements Runnable{

    /**
     * The UI's Record Samples Controller.
     */
    private RecordSamplesController rsc;

    /**
     * Allows access to record samples controller methods.
     * @throws Exception
     */
    public RecordSamplesUI() throws Exception {
        this.rsc=new RecordSamplesController();
    }

    /**
     * It allows you to enter the data necessary to record samples in a test, make the confirmation and see if the operation was successful or not.
     */
    public void run(){
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Record new Samples.", new ShowTextUI("You have chosen to record new samples.")));


        int option =0;
        do{
            boolean exceptionThrown =false;
            try{
                option = Utils.showAndSelectIndex(options, "\n\nSamples Menu:");
            }catch(Exception e){
                System.out.printf("\n\nUnavailable option.");
                exceptionThrown = true;
            }
            try {
                RecordSamplesController recordSamplesController = new RecordSamplesController();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int nSamples;
            if(!exceptionThrown){
                if(option==0){
                    if(rsc.getRegisteredTests().isEmpty())
                        System.out.println("\nThere are no tests without samples to be collected.");
                    else{
                        System.out.println("\n...recording samples...\n");
                        List<TestDto> testDtos = rsc.getRegisteredTests();
                        System.out.println("Available Tests:");
                        for(int i=0; i<testDtos.size();i++){
                            System.out.println(i+1+". "+testDtos.get(i).toString(1));
                        }
                        TestDto opt = null;
                        do{
                            exceptionThrown=false;
                            try{
                                opt=(TestDto) Utils.selectsObject(testDtos);
                            }catch(NumberFormatException e){
                                System.out.println("There was a mistake while choosing the test");
                                exceptionThrown=true;
                            }
                        }while(exceptionThrown);
                        do{
                            nSamples=Utils.readIntegerFromConsole("Type the number of samples to be collected.");
                        }while(nSamples<1);
                        boolean save;
                        TestDto testDto = null;
                        try {
                            testDto = rsc.recordSample(opt, nSamples);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        save=Utils.confirm(String.format("%d barcode(s) was(were) generated, to you want to associate it(them) with the test?\nIf so, press \"s\", if not, press \"n\".", nSamples));
                        boolean saveSuccess=false;
                        if(save)
                            saveSuccess=rsc.save();
                        else{
                            try {
                                rsc.deleteBarcode(nSamples);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if(saveSuccess)
                            System.out.println("Your barcodes were generated and saved.");
                        else
                            System.out.println("Your barcodes were not saved.");



                    }
                }
            }
        }while(option!=-1);
    }
}
