package app.domain.model;

import app.controller.App;
import app.domain.shared.ExternalModuleBloodWithoutKey;
import auth.domain.store.ReportStore;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.linear.upc.UPCABarcode;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestTest {

    @Test
    public void checknhsCodeRulesValid() {

        app.domain.model.Test test = new app.domain.model.Test();

        String resultNhsCode = "123456789098";
        test.checknhsCodeRules(resultNhsCode);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checknhsCodeRulesInvalidBlank() {

        app.domain.model.Test test = new app.domain.model.Test();

        String resultNhsCode = "";
        test.checknhsCodeRules(resultNhsCode);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checknhsCodeRulesInvalidLength() {

        app.domain.model.Test test = new app.domain.model.Test();

        String resultNhsCode = "1234";
        test.checknhsCodeRules(resultNhsCode);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void checknhsCodeRulesInvalidLettersNumbers() {

        app.domain.model.Test test = new app.domain.model.Test();

        String resultNhsCode = "123456-890uh";
        test.checknhsCodeRules(resultNhsCode);
    }

    @Test
    public void generateCode() {


        Company company = new Company("Many Labs");

        ParameterCategory parameterCategory = new ParameterCategory("a", "12345");
        List <ParameterCategory> pcList = new ArrayList<>();
        pcList.add(new ParameterCategory("test","test0"));
        TestType testType = new TestType("34567","assdf","swab", pcList, new ExternalModuleBloodWithoutKey());

        List<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter("45678","asdfg","yujhk",parameterCategory);
        parameters.add(parameter);


        int codeLengthResult = 12;

        app.domain.model.Test teste = company.getTestStore().createTest(testType,parameters,pcList, "123456789098", "121212121212");
        teste.generateCode();
        int codeLengthExpected = teste.getCode().length();

        assertEquals(codeLengthResult,codeLengthExpected);
    }

    @Test
    public void getStatus(){
        List<ParameterCategory> pcList = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("covid", "c0vid");
        pcList.add(pc);

        List<Parameter> pList = new ArrayList<>();
        Parameter parameter1 = new Parameter("12345", "covid", "c0vid", pc);
        pList.add(parameter1);

        TestType testType = new TestType("c0vid", "isCovid", "swab", pcList, new ExternalModuleBloodWithoutKey());


        app.domain.model.Test test = new app.domain.model.Test(testType, pList, pcList, "1123456780", "123456781233");

        assertEquals(app.domain.model.Test.Status.Registered.toString(),test.getStatus());
    }

    @Test
    public void updateValidationDateTime(){
        List<ParameterCategory> pcList = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("covid", "c0vid");
        pcList.add(pc);

        List<Parameter> pList = new ArrayList<>();
        Parameter parameter1 = new Parameter("12345", "covid", "c0vid", pc);
        pList.add(parameter1);

        TestType testType = new TestType("c0vid", "isCovid", "swab", pcList, new ExternalModuleBloodWithoutKey());


        app.domain.model.Test test = new app.domain.model.Test(testType, pList, pcList, "1123456780", "123456781233");
        test.generateCode();

        ReportStore reportStore = App.getInstance().getCompany().getReportStore();

        reportStore.saveReport("Report", test.getCode());
        reportStore.validateReport(test.getCode());

        test.updateValidationDateTime();

        assertEquals(app.domain.model.Test.Status.Validated.toString(), test.getStatus());
    }

    @Test
    public void updateTestStatus(){
        List<ParameterCategory> pcList = new ArrayList<>();
        ParameterCategory pc = new ParameterCategory("covid", "c0vid");
        pcList.add(pc);

        List<Parameter> pList = new ArrayList<>();
        Parameter parameter1 = new Parameter("12345", "covid", "c0vid", pc);
        pList.add(parameter1);

        TestType testType = new TestType("c0vid", "isCovid", "swab", pcList, new ExternalModuleBloodWithoutKey());


        app.domain.model.Test test = new app.domain.model.Test(testType, pList, pcList, "1123456780", "123456781233");
        test.generateCode();

        ReportStore reportStore = App.getInstance().getCompany().getReportStore();

        reportStore.saveReport("Report", test.getCode());
        reportStore.validateReport(test.getCode());

        test.setChemicalAnalysisDateTime(new Date());

        test.updateTestStatus();

        assertEquals(app.domain.model.Test.Status.Analyzed.toString(), test.getStatus());
    }

    @Test
    public void isListUniqueFalse() throws BarcodeException {
        app.domain.model.Test t = new app.domain.model.Test();
        List<Barcode> list = new ArrayList<>();
        Barcode b1=new UPCABarcode("12345678901");
        Barcode b2=new UPCABarcode("12345678901");
        list.add(b1);
        list.add(b2);
        boolean expected=false;
        boolean result=t.isListUnique(list);
        assertEquals(expected,result);
    }

    @Test
    public void isListUniqueTrue() throws BarcodeException {
        app.domain.model.Test t = new app.domain.model.Test();
        List<Barcode> list = new ArrayList<>();
        Barcode b1=new UPCABarcode("12345678901");
        Barcode b2=new UPCABarcode("12364778908");
        list.add(b1);
        list.add(b2);
        boolean expected=true;
        boolean result=t.isListUnique(list);
        assertEquals(expected,result);
    }

    @Test
    public void validateTrue() throws BarcodeException {
        app.domain.model.Test t = new app.domain.model.Test();
        Barcode b1=new UPCABarcode("12345678901");
        Barcode b2=new UPCABarcode("12345678543");
        List<Barcode> listB = new ArrayList<>();
        listB.add(b1);
        listB.add(b2);
        t.addAll(listB);
        t.create();
        boolean expected=true;
        t.updateTestStatus();
        boolean result=t.validate();
        assertEquals(expected,result);
    }

    @Test
    public void validateFalse(){
        app.domain.model.Test t = new app.domain.model.Test();
        boolean expected = false;
        boolean result = t.validate();
        assertEquals(expected, result);
    }







}