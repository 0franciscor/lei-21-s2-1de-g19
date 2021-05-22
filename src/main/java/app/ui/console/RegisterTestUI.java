package app.ui.console;

import app.controller.RegisterTestController;
import app.domain.model.Test;
import app.ui.console.utils.Utils;
import auth.domain.store.TestStore;
import auth.mappers.dto.ClientDto;
import auth.mappers.dto.ParametersDto;
import auth.mappers.dto.TestTypeDto;

import java.util.List;

public class RegisterTestUI implements Runnable {


    private RegisterTestController ctrl;


    public RegisterTestUI (){

        ctrl = new RegisterTestController();


    }

    public void run() {

        int option = 0;

        System.out.printf("\n--- Requested data to register a test ---");
        String citizenID = Utils.readLineFromConsole("\nType the client's citizen card numer:");

        ClientDto cl = ctrl.getClient(citizenID);

        if (cl != null) {

            boolean confirmation = Utils.confirm(String.format("Is this information right? If so type s, if not type n. \n\n Citizen card number: %s \n National Healthcare Service number (NHS): %s " +
            "\n Birth date: %s \n Sex: %s \n Tax Identification number (TIN): %s \n Phone number: %s \n email: %s \n name: %s ", cl.getCitizenID(), cl.getNhsID(), cl.getBirthDate(), cl.getSex(), cl.getTIN(), cl.getPhoneNumber(), cl.getEmail(), cl.getName()));

            if (confirmation) {

                if (ctrl.getAllTestType().isEmpty())
                    System.out.printf("\nThere aren't any test type list at the moment.\n");
                else {

                    List<TestTypeDto> listTestTypeDto = ctrl.getAllTestType();

                    Utils.showList(listTestTypeDto, "Available test types.");
                    TestTypeDto opcao = (TestTypeDto) Utils.selectsObject(listTestTypeDto);

                    if (opcao != null){
                        List<ParametersDto> listParametersDto = ctrl.getAllParametersByTestType(opcao);

                        System.out.println("Available parameters associated to the test type.");

                        int index = 0;
                        for (ParametersDto parametersDto : listParametersDto)
                        {
                            index++;

                            System.out.println(index + ". " + parametersDto.toString());
                        }
                        Utils.readLineFromConsole("Choose the parameters that you want to associate to the test type.");



                        boolean confirmation1 = Utils.confirm(String.format("Are you sure this is the info of the test type ? If so type s, if not type n. \n\n Test type: %s", opcao.toString()));

                        for (ParametersDto parametersDto: listParametersDto) {

                            System.out.println(parametersDto.toString());
                        }

                        boolean confirmation2 = Utils.confirm("Are u sure this is the info of parameters associated to the test type ?");

                        if (confirmation1 && confirmation2){
                            Test test = ctrl.createTest(listParametersDto,opcao,cl.getCitizenID());
                            ctrl.saveTest(test);
                            System.out.println("Operation was a success and the test was registered.");

                            /* VER SE O TEST ESTA NA TEST LIST
                            List<Test> x = ctrl.testStore.ola();
                            for (Test yy: x ) {
                                System.out.printf(yy.toString());
                            }
                             */

                        }
                        //System.out.println(test.toString());

                    } else {
                        System.out.println("Não quer selecionar nenhum test type da lista.");
                    }
                }
                
            } else {
                System.out.println("Corrigir dados");
            }

        }
    }
}
