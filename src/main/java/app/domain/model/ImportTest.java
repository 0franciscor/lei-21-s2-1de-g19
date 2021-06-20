package app.domain.model;

import app.controller.App;
import app.domain.shared.ExternalModule;
import auth.domain.store.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportTest {

    private Company company;

    private File ficheiroInput;

    private BufferedReader bufferedReader;

    private String[] firstLine;

    private int[] importRules;

    public ImportTest(){
        this.company = App.getInstance().getCompany();
    }

    public boolean getFile(String pathName) throws IOException {
        this.ficheiroInput = new File(pathName);

        if(ficheiroInput.exists()) {
            this.bufferedReader = new BufferedReader(new FileReader(ficheiroInput));
            importRules = getRules();
            return true;
        } else
            return false;
    }

    public int[] getRules () throws IOException {
        String linha = bufferedReader.readLine();
        this.firstLine = linha.split(";");

        int [] contaCategoriasParametros = null;

        for (String string : firstLine) {
            if (string.equalsIgnoreCase("TestType")) {
                contaCategoriasParametros = contaCategorias(firstLine);
            }
        }
        contaParametros(firstLine, contaCategoriasParametros);

        return contaCategoriasParametros;
    }

    public String readListFromCSV() throws IOException {

        String linhaFicheiro = bufferedReader.readLine();
        while(linhaFicheiro != null){
            String [] linhaSplit = linhaFicheiro.split(";");
            try {
                checkIfExists(linhaSplit);
                //TEM DE HAVER AQUI UM RETURN
            } catch (Exception e){
                //Tem de ter coisas
            }

            linhaFicheiro = bufferedReader.readLine();
        }

        return "The List is finished.";
    }

    public void checkIfExists(String [] linhaSplit){
        Client client;
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        List<TestParameter> testParameterList = new ArrayList<>();
        TestType testType;
        boolean testExists;
        Date registerDate = null;
        Date chemicalAnalysisDate = null;
        Date diagnosisDate = null;
        Date validationDate = null;


        try {

            String[] conteudoClient = new String[8];
            for(int i = 3; i< 11; i++)
                conteudoClient[i-3] = linhaSplit[i];

            client = checkIfClientExists(conteudoClient);

            checkIfLabExists(linhaSplit[2]);

            testType = checkIfTestTypeExists(linhaSplit[11]);

            int somaCategoria = 0;
            for(int i = 0; i< importRules.length; i++){
                String categoria;
                int indiceCategoria = 12;

                if(i == 0)
                    categoria = linhaSplit[indiceCategoria];
                else
                    categoria = linhaSplit[indiceCategoria + i + somaCategoria];

                if(!categoria.equalsIgnoreCase("NA")) {
                    parameterCategoryList.add(checkIfCategoryExists(categoria));
                    for (int j = 0; j < importRules[i]; j++) {
                        String parameter = linhaSplit[13 + somaCategoria  + j + i];
                        double parameterValue;
                        if(!parameter.equalsIgnoreCase("NA")) {
                            parameterValue = Double.parseDouble(parameter);
                            testParameterList.add(checkIfTestParameterExists(parameterValue, i, (j+1), testType));
                        }

                    }
                }
                somaCategoria += importRules[i];
            }

            testExists = checkIfTestExists(linhaSplit[0], linhaSplit[1]);

            int numIteracao = 0;
            for(int i = linhaSplit.length-1; i>linhaSplit.length-5; i--){
                switch(numIteracao){
                    case 0:
                        validationDate = checkIfDateExists(linhaSplit[i]);
                        break;
                    case 1:
                        diagnosisDate = checkIfDateExists(linhaSplit[i]);
                        break;
                    case 2:
                        chemicalAnalysisDate = checkIfDateExists(linhaSplit[i]);
                        break;
                    case 3:
                        registerDate = checkIfDateExists(linhaSplit[i]);
                        break;
                }
                numIteracao++;
            }

            addTest(client, parameterCategoryList, testParameterList, testType, linhaSplit[0], linhaSplit[1], testExists, registerDate, chemicalAnalysisDate, diagnosisDate, validationDate);

        } catch (Exception e){
            System.out.println("The test that was currently being imported did not meet the necessary requisites for it to be stored. It has been ignored.");
        }

    }

    public Client checkIfClientExists(String[] conteudoClient){
        ClientStore clientStore = company.getClientStore();
        Client client = clientStore.getClient(conteudoClient[2]);

        if(client == null)
            throw new IllegalArgumentException();
        else{
            if(!client.getCitizenID().equalsIgnoreCase(conteudoClient[0]))
                throw new IllegalArgumentException("The Citizen ID does not match.");
            if(!client.getNhsID().equalsIgnoreCase(conteudoClient[1]))
                throw new IllegalArgumentException("The NHS ID does not match.");
            if(!client.getBirthDate().equalsIgnoreCase(conteudoClient[3]))
                throw new IllegalArgumentException("The birth Date does not match.");
            if(!client.getPhoneNumber().equalsIgnoreCase(conteudoClient[4]))
                throw new IllegalArgumentException("The Phone number does not match.");
            if(!client.getName().equalsIgnoreCase(conteudoClient[5]))
                throw new IllegalArgumentException("The name does not match.");
            if(!client.getEmail().equalsIgnoreCase(conteudoClient[6]))
                throw new IllegalArgumentException("The email does not match.");
            if(!client.getAddress().equalsIgnoreCase(conteudoClient[7]))
                throw new IllegalArgumentException("the address does not match.");

            return client;
        }
    }

    public void checkIfLabExists(String labID){
        ClinicalAnalysisLaboratoryStore clinicalAnalysisLaboratoryStore = company.getClinicalAnalysisLaboratoryStore();
        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = clinicalAnalysisLaboratoryStore.getClinicalAnalysisLaboratoryByID(labID);

        if(clinicalAnalysisLaboratory == null)
            throw new IllegalArgumentException("There is no Laboratory which corresponds to that name.");
    }

    public ParameterCategory checkIfCategoryExists(String name){
        ParameterCategoryStore parameterCategoryStore = company.getParameterCategoryStore();
        ParameterCategory parameterCategory = parameterCategoryStore.getParameterCategoryByName(name);

        if(parameterCategory == null)
            throw new IllegalArgumentException("There is no Parameter Category that corresponds to that name");
        else
            return parameterCategory;
    }

    public TestParameter checkIfTestParameterExists(double result, int numCategoria, int numParameter, TestType testType){

        int sum = 0;

        for(int i = 0; i<numCategoria; i++){
            sum += importRules[i];
            if(numCategoria >= 1)
                numParameter++;
        }

        String parameterCode = firstLine[12 + sum + numParameter];

        ParameterStore parameterStore = company.getParameterStore();
        Parameter parameter = parameterStore.getParameterByCode(parameterCode);

        if(parameter == null)
            throw new IllegalArgumentException("There is no Parameter that corresponds to that name");
        else {
            TestParameter testParameter = new TestParameter(parameterCode);
            ExternalModule externalModule = testType.getExternalModule();
            String metric = externalModule.getMetric(parameter.getCode());
            ReferenceValue referenceValue = externalModule.getReferenceValue(testParameter);
            if(metric.equalsIgnoreCase("-1.0"))
                throw new IllegalArgumentException("That Category does not have an external module associated. The system is not able to proceed.");
            testParameter.addResult(result, metric, referenceValue);
            return testParameter;
        }

    }

    public TestType checkIfTestTypeExists(String code){
        TestTypeStore testTypeStore = company.getTestTypeStore();
        TestType testType = testTypeStore.getTestTypeByCode(code);

        if(testType == null)
            throw new IllegalArgumentException("There is no Test Type that corresponds to that code");
        else
            return testType;
    }


    public boolean checkIfTestExists(String code, String nhsCode){
        TestStore testStore = company.getTestStore();

        Test test = testStore.getTestByCode(code);

        if(test == null)
            return false;

        else
            if(test.getNhsCode().equalsIgnoreCase(nhsCode))
                throw new IllegalArgumentException("The imported test must not have an equal Nhs code.");
            else
                return true;
    }

    public Date checkIfDateExists(String date) throws ParseException {
        if(date == null || date.isEmpty())
            throw new IllegalArgumentException("A date is wrong.");
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date dateReturn = formatter.parse(date);
            return dateReturn;
        }
    }

    public boolean addTest(Client client, List<ParameterCategory> parameterCategoryList, List<TestParameter> testParameterList, TestType testType, String code, String nhsCode, boolean existsTest, Date registerDate, Date chemicalAnalysisDate, Date diagnosisDate, Date validationDate) throws Exception {
        TestStore testStore = new TestStore();

        Test test = testStore.createTest(client, parameterCategoryList, testParameterList, testType, code, nhsCode, existsTest, registerDate, chemicalAnalysisDate, diagnosisDate, validationDate);

        return testStore.saveTest(test);
    }



    // MÉTODOS ALGORITMIA

    public static int[] contaCategorias (String[] linhaRegra){ //FUNCIONA
        int numCategorias = 0, i = 12;
        do{
            if(linhaRegra[i].equalsIgnoreCase("Category")){
                numCategorias++;
            }
            i++;
        }while(!linhaRegra[i].equalsIgnoreCase("Test_Reg_DateHour"));

        return new int[numCategorias];
    }

    public static void contaParametros(String[] linhaRegra, int[] contaCategorias){
        int indiceParametro = 0, i = 13, numParametros = 0;

        do{
            if (!linhaRegra[i].equalsIgnoreCase("Category") && !linhaRegra[i].equalsIgnoreCase("Test_Reg_DateHour")) {
                numParametros++;
            }
            if (linhaRegra[i].equalsIgnoreCase("Category") || linhaRegra[i].equalsIgnoreCase("Test_Reg_DateHour")) {
                contaCategorias[indiceParametro] = numParametros;
                indiceParametro++;
                numParametros = 0;
            }
            i++;
        } while(indiceParametro < contaCategorias.length);
    }
}