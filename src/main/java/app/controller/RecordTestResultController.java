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
 *
 * @author Francisco Redol
 */
public class RecordTestResultController {

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
        Test test = store.getTestBybarcodeWithPendingResults(barcode);
        return TestMapper.toDtoCCT(test);
    }

}
