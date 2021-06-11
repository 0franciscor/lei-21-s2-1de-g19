package app.controller;

import app.domain.model.Sample;
import app.domain.model.Test;
import auth.domain.store.TestStore;
import auth.mappers.TestMapper;
import auth.mappers.dto.TestDto;
import net.sourceforge.barbecue.Barcode;

import java.util.ArrayList;
import java.util.List;

/**
 * The Record Test Result Controller
 *
 * @author Francisco Redol
 */
public class RecordTestResultController {

    private Test test;

    public RecordTestResultController(){}

    public List<Barcode> getAllBarcodes(){
        TestStore store = App.getInstance().getCompany().getTestStore();

        List<Test> listTests = store.getCollectedTests();
        List<Barcode> listBarcodes = new ArrayList<>();

        for(Test test : listTests){
            List<Sample> listSample = test.getListSamples();
            for(Sample sample : listSample)
                listBarcodes.add(sample.getBarcode());
        }

        return listBarcodes;
    }

    public TestDto getTestByBarcode(Barcode barcode){
        TestStore store = App.getInstance().getCompany().getTestStore();
        this.test = store.getTestBybarcodeWithPendingResults(barcode);
        return TestMapper.toDtoCCT(test);
    }

    public boolean addTestResult(String parameterCode, double result, String metric){
        return test.addTestResult(parameterCode, result, metric);
    }
}
