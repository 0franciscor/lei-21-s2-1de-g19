package app.controller;

import app.domain.model.APIBarcodeAdapter;
import app.domain.model.Company;
import app.domain.model.Test;
import auth.domain.store.TestStore;
import auth.mappers.TestMapper;
import auth.mappers.dto.TestDto;
import net.sourceforge.barbecue.Barcode;

import java.util.List;

/**
 * Represents the Record Samples Controller.
 *
 * @author Rita Lello
 */
public class RecordSamplesController {

    /**
     * The controller's App object.
     */
    private App app;

    /**
     * The controller's Company.
     */
    private Company company;

    /**
     * The controller's Test Store.
     */
    private TestStore testStore;

    /**
     * The controller's Test.
     */
    private Test test;

    /**
     * The controller's List of Tests.
     */
    private List<Test> testList;

    /**
     * The controller's List of Tests in data transfer objects.
     */
    private List<TestDto> testDtoList;

    /**
     * The controller's Test Code.
     */
    private String tCode;

    /**
     * A controller's boolean that helps in the execution.
     */
    private boolean bool1=false;

    /**
     * Another controller's boolean that helps in the execution.
     */
    private boolean bool2=false;

    /**
     * The controller's List of Barcodes.
     */
    private List<Barcode> listBarcodes;

    /**
     * The controller's Test in data transfer object.
     */
    private TestDto testDto;

    /**
     * The controller's API Barcode Adapter.
     */
    private APIBarcodeAdapter adapter;

    /**
     * Builds a RecordSamplesController without receiving parameters.
     * @throws Exception
     */
    public RecordSamplesController() throws Exception {
        this.app=app.getInstance();
        this.company=app.getCompany();
        this.testStore=company.getTestStore();
        this.adapter=new APIBarcodeAdapter();
    }

    /**
     * Calls the method from test store that gets all the registered tests and then calls the mapper method that converts the tests in data transfer objects.
     *
     * @return a list with the data transfer objects.
     */
    public List<TestDto> getRegisteredTests(){
        testList=testStore.getRegisteredTests();
        testDtoList= TestMapper.ModelToDto(testList);
        return testDtoList;
    }

    /**
     * Calls the method from the mapper to convert a testDto in an domain object and then calls the method from the test store that gets a test by its code.
     * Once it has the test, it calls the adapter method that generate barcodes and add them to a list, sees if the generated barcodes are unique in the test store and in the test.
     * If so, the list is added to the test and the test is again converted, by calling the mapper method, in a data transfer object.
     *
     * @param tDto
     * @param nSamples
     * @return test in data transfer object
     * @throws Exception
     */
    public TestDto recordSample(TestDto tDto, int nSamples) throws Exception {
        tCode=TestMapper.DtoToModel(tDto);
        test=testStore.getTestByCode(tCode);
        do{
            listBarcodes= adapter.generateBarcodes(nSamples, true);
            bool1=testStore.globallyUnique(listBarcodes);
            bool2=test.isListUnique(listBarcodes);
        }while(!bool1||!bool2);
        test.addAll(listBarcodes);
        testDto=TestMapper.ModelToDto(test);
        return testDto;
    }

    /**
     * Calls the method from the adapter to delete the generated barcodes.
     * @param nSamples
     * @throws Exception
     */
    public void deleteBarcode(int nSamples) throws Exception{
        adapter.generateBarcodes(nSamples, false);
    }

    /**
     * Calls the method from the test and tries to save the controller's test.
     *
     * @return true if the test was successfully updated and saved
     * @return false if the test was not updated and saved
     */
    public boolean save(){
        return test.validate();
    }




}
