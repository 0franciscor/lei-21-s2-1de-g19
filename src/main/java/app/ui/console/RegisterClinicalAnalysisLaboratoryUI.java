package app.ui.console;

import app.controller.CreateTestTypeController;
import app.controller.RegisterClinicalAnalysisLaboratoryController;
import app.domain.model.TestType;
import app.ui.console.utils.Utils;
import auth.mappers.dto.ClinicalAnalysisLaboratoryDto;
import auth.mappers.dto.TestTypeDto;

import java.util.ArrayList;
import java.util.List;

class RegisterClinicalAnalysisLaboratoryUI implements Runnable {

    /**
     * The RegisterClinicalAnalysisLaboratoryUI
     * RegisterClinicalAnalysisLaboratoryController.
     */
    private RegisterClinicalAnalysisLaboratoryController rcalController;

    /**
     * The RegisterClinicalAnalysisLaboratoryUI CreateTestTypeController.
     */
    private CreateTestTypeController ttController;

    /**
     * Builds the RegisterClinicalAnalysisLaboratoryUI Object.
     */
    public RegisterClinicalAnalysisLaboratoryUI() {
        this.rcalController = new RegisterClinicalAnalysisLaboratoryController();
        this.ttController = new CreateTestTypeController();
    }

    /**
     * Responsible for allowing the UI to run.
     */
    @Override
    public void run() {

        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create a new Clinical Analysis Laboratory", new ShowTextUI("You have chosen to register a new clinical analysis laboratory.")));
        options.add(new MenuItem("See the current Clinical Analysis Laboratories", new ShowTextUI("You have chosen to register a new clinical analysis laboratory.")));

        int option = 0;

        do {
            option = Utils.showAndSelectIndex(options, "\n\nClinical Analysis Laboratory menu:");

            if (option == 0 && ttController.getAllTestTypesDto().isEmpty()) {
                System.out.printf("\nThere aren't any Test Types available at the moment. Once you have one, you'll be able to register a clinical analysis laboratory.\n");
            } else if (option == 0 && !ttController.getAllTestTypesDto().isEmpty()) {
                System.out.printf("\nYou are now registering a clinical analysis laboratory!\n");

                List<TestTypeDto> TestTypeDto = ttController.getAllTestTypesDto();

                boolean createSuccess, save = false, saveSuccess = false;

                System.out.printf("\nChoose one or more test types.\n");

                List<TestType> chosenTestTypesList = new ArrayList<TestType>();
                int chosenTestTypes, x = 0;

                do {

                    System.out.printf("\nAvailable Test Types:\n");

                    for (int i = 0; i < TestTypeDto.size(); i++) {
                        if (TestTypeDto != null && !chosenTestTypesList.contains(ttController.getTestTypeByCode(TestTypeDto.get(i).getCode()))) {
                            System.out.printf("\n" + (i + 1) + ". " + TestTypeDto.get(i));
                        }
                    }

                    chosenTestTypes = Utils.readIntegerFromConsole("Insert your option (Press -1 to exit selecting categories):");
                    chosenTestTypes = chosenTestTypes - 1;

                    if (chosenTestTypes > -1) {
                        chosenTestTypesList.add(ttController.getTestTypeByCode(TestTypeDto.get(chosenTestTypes).getCode()));
                        x++;
                    }

                } while (x == 0 || (x < TestTypeDto.size() && chosenTestTypes != -2));

                String name, address, phoneNumber, TIN, labID;

                do {
                    name = Utils.readLineFromConsole("Please insert the name of the lab:");
                } while (!Utils.confirm("Are you sure the name is correct? If so, press \"s\", if not, press \"n\"."));

                do {
                    address = Utils.readLineFromConsole("Please insert the address of the lab:");
                } while (!Utils.confirm("Are you sure the address is correct? If so, press \"s\", if not, press \"n\"."));

                do {
                    phoneNumber = Utils.readLineFromConsole("Please insert the phone number of the lab:");
                } while (!Utils.confirm("Are you sure the phone number is correct? If so, press \"s\", if not, press \"n\"."));

                do {
                    TIN = Utils.readLineFromConsole("Please insert the TIN of the lab:");
                } while (!Utils.confirm("Are you sure the TIN is correct? If so, press \"s\", if not, press \"n\"."));

                do {
                    labID = Utils.readLineFromConsole("Please insert the ID of the lab:");
                } while (!Utils.confirm("Are you sure the ID is correct? If so, press \"s\", if not, press \"n\"."));

                createSuccess = rcalController.registerClinicalAnalysisLaboratory(name, address, phoneNumber, TIN, labID, chosenTestTypesList);

                if (createSuccess) {
                    save = Utils.confirm("The clinical analysis laboratory was registered successfully. Do you want to add it to the system? If so, press \"s\", if not, press \"n\".");
                }

                if (save) {
                    saveSuccess = rcalController.saveClinicalAnalysisLaboratory();
                }

                if (saveSuccess) {
                    System.out.printf("\nYour clinical analysis laboratory has been successfully registered!");
                } else {
                    System.out.printf("\nYour clinical analysis laboratory has not been successfully saved.");
                }

            }

            if (option == 1) {
                List<ClinicalAnalysisLaboratoryDto> availableClinicalAnalysisLaboratoriesDto = rcalController.getAllClinicalAnalysisLaboratoriesDto();

                if (availableClinicalAnalysisLaboratoriesDto.isEmpty()) {
                    System.out.printf("\nThere are no clinical analysis laboratories at the moment.");
                } else {
                    System.out.printf("\nCurrent clinical analysis laboratories:");
                    for (int i = 0; i < availableClinicalAnalysisLaboratoriesDto.size(); i++) {
                        System.out.printf("\n" + (i + 1) + ". " + availableClinicalAnalysisLaboratoriesDto.get(i));
                    }
                }
            }

        } while (option != -1);

    }
}