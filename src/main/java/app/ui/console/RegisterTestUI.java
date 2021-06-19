package app.ui.console;

import app.controller.RegisterTestController;
import app.domain.model.Test;
import app.ui.console.utils.Utils;
import auth.mappers.dto.ClientDto;
import auth.mappers.dto.ParameterCategoryDto;
import auth.mappers.dto.ParametersDto;
import auth.mappers.dto.TestTypeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the register test UI.
 *
 * @author Eduardo Gonçalves
 */
public class RegisterTestUI implements Runnable {


    /**
     * Register Test Controller.
     */
    private RegisterTestController ctrl;


    /**
     * Allows access to register test controller methods.
     */
    public RegisterTestUI (){

        ctrl = new RegisterTestController();

    }

    /**
     * It allows you to enter the data necessary to register a test associated to a registered client, select the test type and the parameter(s) to register a test, make the confirmation and see if the operation was successful or not.
     */
    public void run() {

        int option = 0;

        System.out.printf("\n--- Requested data to register a test ---");
        String TIN = Utils.readLineFromConsole("\nType the Client's Tax Identification Number:");
        String nhsCode = Utils.readLineFromConsole("Type the NHS Code test:");

        if (ctrl.testStore.getNhsCodeList().contains(nhsCode))
            System.out.println("There is already a test for this National Healthcare Service number.");
        else {

            ClientDto cl;
            try {
                cl = ctrl.getClient(TIN);
            } catch (Exception e) {
                System.out.println("\nInvalid Tax Identification Number or the client has not been registered yet.");
                return;
            }

            if (cl != null) {

                boolean confirmation = Utils.confirm(String.format("Is this information right? If so type s, if not type n. \n\n Citizen card number: %s \n National Healthcare Service number (NHS): %s " +
                        "\n Birth date: %s \n Sex: %s \n Tax Identification number (TIN): %s \n Phone number: %s \n email: %s \n name: %s ", cl.getCitizenID(), cl.getNhsID(), cl.getBirthDate(), cl.getSex(), cl.getTIN(), cl.getPhoneNumber(), cl.getEmail(), cl.getName()));

                if (confirmation) {

                    if (ctrl.getAllTestType().isEmpty())
                        System.out.printf("\nThere aren't any test type list at the moment.\n");
                    else {

                        List<TestTypeDto> listTestTypeDto = ctrl.getAllTestType();


                        System.out.println("\nAvailable test types:");

                        int index1 = 0;
                        for (TestTypeDto testTypeDto: listTestTypeDto){
                            index1 ++;

                            System.out.println(index1 + ". " + testTypeDto.toString());
                        }

                        TestTypeDto opcao = null;
                        boolean exceptionThrown = false;
                        try {
                            opcao = (TestTypeDto) Utils.selectsObject(listTestTypeDto);
                        } catch (Exception e) {
                            System.out.println("Unavailable option. Try to register the test again.");
                            exceptionThrown = true;
                        }

                        if (!exceptionThrown) {

                            if (opcao != null){
                                List<ParameterCategoryDto> listParameterCategoryDto = ctrl.getAllParameterCategoriesByTestType(opcao);

                                System.out.println("\nAvailable parameter Categories associated to the test type:");

                                int index = 0;
                                for (ParameterCategoryDto parameterCategoryDto : listParameterCategoryDto)
                                {
                                    index++;

                                    System.out.println(index + ". " + parameterCategoryDto.toString());
                                }

                                String aux = Utils.readLineFromConsole("Type your option:");
                                String [] aux1 = aux.trim().split(" ");


                                int validarOpcoes = 0;
                                for (int x=0; x<aux1.length; x++){

                                    if (Integer.parseInt(aux1[x]) <= 0 || Integer.parseInt(aux1[x]) > listParameterCategoryDto.size())
                                        validarOpcoes++;
                                }

                                List<ParametersDto> listParametersDto = ctrl.getAllParametersByParameterCategory(listParameterCategoryDto);

                                System.out.println("\nAvailable parameter(s) associated to the parameter category(ies):");

                                int index2 = 0;
                                for (ParametersDto parametersDto: listParametersDto){
                                    index2++;

                                    System.out.println(index2 + ". " + parametersDto.toString());
                                }

                                String aux2 = Utils.readLineFromConsole("Type your option:");
                                String [] aux3 = aux2.trim().split(" ");

                                int validarOpcoes1 = 0;
                                for (int x=0; x<aux3.length; x++){

                                    if (Integer.parseInt(aux3[x]) <= 0 || Integer.parseInt(aux3[x]) > listParametersDto.size())
                                        validarOpcoes1++;
                                }


                                if (validarOpcoes == 0 && validarOpcoes1 == 0) {

                                    System.out.println("\n"+opcao.toString());

                                    boolean confirmation1 = Utils.confirm("Are you sure this is the info of the test type ? If so type s, if not type n.");

                                    System.out.println();
                                    for (int i=0; i<aux1.length; i++){
                                        if (Integer.parseInt(aux1[i]) == i+1){
                                            System.out.println(listParameterCategoryDto.get(i));
                                        }
                                    }

                                    boolean confirmation2 = Utils.confirm("Are you sure this is the info of parameter category(ies) associated to the test type ? If so type s, if not type n.");

                                    System.out.println();
                                    for (int i=0; i<aux3.length; i++){
                                        if (Integer.parseInt(aux3[i]) == i+1){
                                            System.out.println(listParametersDto.get(i));
                                        }
                                    }

                                    boolean confirmation3 = Utils.confirm("Are you sure this is the info of parameter(s) associated to the parameter category(ies) ? If so type s, if not type n.");


                                    if (confirmation1 && confirmation2 && confirmation3){

                                        Test test;

                                        try {
                                            test = ctrl.createTest(listParametersDto,opcao, listParameterCategoryDto,cl.getTIN(),nhsCode);
                                        } catch (Exception e) {
                                            System.out.println("Impossible to register the test due to a problem with National Healthcare Service number. Check if it is right.");
                                            return;

                                        }
                                        ctrl.saveTest(test, cl);

                                        System.out.println("Operation was a success and the test was registered.");
                                        ctrl.testStore.getNhsCodeList().add(nhsCode);

                                        //VER SE O TEST ESTA NA TEST LIST

                                        List<Test> x = ctrl.testStore.SeeList();
                                        for (Test yy: x ) {
                                            System.out.printf(yy.toString());
                                        }


                                    }

                                } else {
                                    System.out.println("\nThe test was not registered due to invalid option(s). Check the selected option(s).");
                                }
                            } else {
                                System.out.println("The selected option does not exist and therefore the test registration has been canceled.");
                            }
                        }
                    }
                } else {
                    System.out.println("Verify the information.");
                }
            }
        }
    }
}
