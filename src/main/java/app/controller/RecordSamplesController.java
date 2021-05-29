package app.controller;

import app.domain.model.APIBarcodeAdapter;
import app.domain.model.Company;
import app.domain.model.ExternalModuleAPIBarcode;
import app.domain.model.Test;
import auth.domain.store.TestStore;
import auth.mappers.TestMapper;
import auth.mappers.dto.TestDto;
import net.sourceforge.barbecue.Barcode;

import java.util.List;

public class RecordSamplesController {

    private App app;
    private Company company;
    private TestStore testStore;
    private Test test;
    private List<Test> testList;
    private List<TestDto> testDtoList;
    private String tCode;
    private boolean bool1=false;
    private boolean bool2=false;
    private List<Barcode> listBarcodes;
    private TestDto testDto;
    private APIBarcodeAdapter adapter;


    public RecordSamplesController() throws Exception {
        this.app=app.getInstance();
        this.company=app.getCompany();
        this.testStore=company.getTestStore();
        this.adapter=new APIBarcodeAdapter();
    }


    public List<TestDto> getRegisteredTests(){
        testList=testStore.getRegisteredTests();
        testDtoList= TestMapper.ModelToDto(testList);
        return testDtoList;
    }

    public TestDto recordSample(TestDto tDto, int nSamples) throws Exception {
        tCode=TestMapper.DtoToModel(tDto);
        test=testStore.getTest(tCode);
        do{
            listBarcodes= adapter.generateBarcodes(nSamples, true);
            bool1=testStore.globallyUnique(listBarcodes);
            bool2=test.isListUnique(listBarcodes);
        }while(!bool1||!bool2);
        test.addAll(listBarcodes);
        testDto=TestMapper.ModelToDto(test);
        return testDto;
    }

    public void deleteBarcode(int nSamples) throws Exception{
        adapter.generateBarcodes(nSamples, false);
    }

    public boolean save(){
        return test.validate();
    }




}
