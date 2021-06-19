package app.domain.model;

import app.controller.App;
import auth.domain.store.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImportTest {

    private Company company;

    private File ficheiroInput;

    private BufferedReader bufferedReader;

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
        String [] linhaSplitRegra= linha.split(";");

        int [] contaCategoriasParametros = null;

        for (String string : linhaSplitRegra) {
            if (string.equalsIgnoreCase("TestType")) {
                contaCategoriasParametros = contaCategorias(linhaSplitRegra);
            }
        }
        contaParametros(linhaSplitRegra, contaCategoriasParametros);

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
        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory;
        List<ParameterCategory> parameterCategoryList = new ArrayList<>();
        List<ParameterCategory> parameterList = new ArrayList<>();

        String[] conteudoClient = new String[8];
        for(int i = 3; i< 11; i++)
            conteudoClient[i-3] = linhaSplit[i];

        try {
            client = checkIfClientExists(conteudoClient);

            clinicalAnalysisLaboratory = checkIfLabExists(linhaSplit[2]);


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
                        if(!parameter.equalsIgnoreCase("NA"))
                             parameterValue = Double.parseDouble(parameter);

                    }
                }

                somaCategoria += importRules[i];
            }





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
                throw new IllegalArgumentException();
            if(!client.getNhsID().equalsIgnoreCase(conteudoClient[1]))
                throw new IllegalArgumentException();
            if(!client.getBirthDate().equalsIgnoreCase(conteudoClient[3]))
                throw new IllegalArgumentException();
            if(!client.getPhoneNumber().equalsIgnoreCase(conteudoClient[4]))
                throw new IllegalArgumentException();
            if(!client.getName().equalsIgnoreCase(conteudoClient[5]))
                throw new IllegalArgumentException();
            if(!client.getEmail().equalsIgnoreCase(conteudoClient[6]))
                throw new IllegalArgumentException();
            if(!client.getAddress().equalsIgnoreCase(conteudoClient[7]))
                throw new IllegalArgumentException();

            return client;
        }
    }

    public ClinicalAnalysisLaboratory checkIfLabExists(String labID){
        ClinicalAnalysisLaboratoryStore clinicalAnalysisLaboratoryStore = company.getClinicalAnalysisLaboratoryStore();
        ClinicalAnalysisLaboratory clinicalAnalysisLaboratory = clinicalAnalysisLaboratoryStore.getClinicalAnalysisLaboratoryByID(labID);

        if(clinicalAnalysisLaboratory == null)
            throw new IllegalArgumentException("There is no Laboratory which corresponds to that name.");
        else
            return clinicalAnalysisLaboratory;
    }

    public ParameterCategory checkIfCategoryExists(String name){
        ParameterCategoryStore parameterCategoryStore = company.getParameterCategoryStore();
        ParameterCategory parameterCategory = parameterCategoryStore.getParameterCategoryByName(name);

        if(parameterCategory == null)
            throw new IllegalArgumentException("There is no Parameter Category that corresponds to that name");
        else
            return parameterCategory;
    }

//    public Parameter checkIfParameterExists(double result, ){
//
//        ParameterStore parameterStore = company.getParameterStore();
//        Parameter parameter = parameterStore.getParameterByCode();
//
//        if(parameter == null)
//            throw new IllegalArgumentException("There is no Parameter that corresponds to that name");
//        else
//            return parameter;
//    }

    public TestType checkIfTestTypeExists(String code){
        TestTypeStore testTypeStore = company.getTestTypeStore();
        TestType testType = testTypeStore.getTestTypeByCode(code);

        if(testType == null)
            throw new IllegalArgumentException("There is no Test Type that corresponds to that code");
        else
            return testType;
    }


    public boolean checkIfTestExists(){
        return true;
    }







    // MÉTODOS ALGORITMIA UTEIS

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
