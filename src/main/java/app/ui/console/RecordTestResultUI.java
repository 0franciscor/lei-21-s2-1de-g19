package app.ui.console;

import app.controller.RecordTestResultController;
import app.domain.model.Parameter;
import app.ui.console.utils.Utils;
import auth.mappers.dto.TestDto;
import net.sourceforge.barbecue.Barcode;

import java.util.List;

/**
 * The Record Test Result UI
 *
 * @author Francisco Redol
 */
public class RecordTestResultUI implements Runnable {

    private RecordTestResultController recordTestResultController;

    public RecordTestResultUI() {
        recordTestResultController = new RecordTestResultController();
    }

    public void run(){
        try {
            List<Barcode> barcodeList = recordTestResultController.getAllBarcodes();
            Barcode barcode;

            if(barcodeList != null && !barcodeList.isEmpty()) {
                barcode = ((Barcode) Utils.showAndSelectOne(barcodeList, "Please choose a barcode from the list:"));
                TestDto testDto = recordTestResultController.getTestByBarcode(barcode);

                for(Parameter parameter : testDto.getParameters()){
                    System.out.print(parameter);
                    boolean successAdd;
                    double result;
                    String metric;
                    try {
                        result = Utils.readDoubleFromConsole("Please insert the result.");
                        metric = Utils.readLineFromConsole("Please insert the metric.");
                    } catch (Exception e){
                        System.out.println("You have inserted a type of data which is not supported by the system.");
                        return;
                    }
                    successAdd = recordTestResultController.addTestResult(parameter.getCode(), result, metric);

                    if(successAdd)
                        System.out.println("The result for the parameter was successfully saved!");
                }
            } else{
                System.out.println("There are no tests ready for recording the results at the moment. Please come back later.");
            }
        } catch (Exception e){
            System.out.println("There was an error when Recording a test result.");
        }
    }
}

