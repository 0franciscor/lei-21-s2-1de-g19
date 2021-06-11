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
        List<Barcode> barcodeList = recordTestResultController.getAllBarcodes();
        Barcode barcode;

        if(barcodeList != null && !barcodeList.isEmpty()) {
            barcode = ((Barcode) Utils.showAndSelectOne(barcodeList, "Please choose a barcode from the list:"));
            TestDto testDto = recordTestResultController.getTestByBarcode(barcode);

            for(Parameter parameter : testDto.getParameters()){
                System.out.print(parameter);
                boolean successAdd;
                try {
                    double result = Utils.readDoubleFromConsole("Please insert the result.");
                    String metric = Utils.readLineFromConsole("Please insert the metric.");
                    successAdd = recordTestResultController.addTestResult(parameter.getCode(), result, metric);
                } catch (Exception e){
                    System.out.println("There was an error");
                    return;
                }

                if(successAdd)
                    System.out.println("The result was successfully saved");
            }
        } else{
            System.out.println("There are no tests ready for validation at the moment. Please come back later.");
        }

    }
}

