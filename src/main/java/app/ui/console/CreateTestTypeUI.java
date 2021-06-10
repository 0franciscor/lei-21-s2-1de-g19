package app.ui.console;

import app.controller.App;
import app.controller.CreateParameterCategoryController;
import app.controller.CreateTestTypeController;
import app.domain.shared.ExternalModule;
import app.domain.model.ParameterCategory;
import app.ui.console.utils.Utils;
import auth.mappers.dto.ParameterCategoryDto;
import auth.mappers.dto.TestTypeDto;

import java.util.ArrayList;
import java.util.List;

/**
 * CreateTestTypeUI class, which is the interface who allows to create a test type.
 *
 * @author Francisco Redol (1201239)
 */
public class CreateTestTypeUI implements Runnable {

    /**
     * The CreateTestTypeUI CreateTestTypeController.
     */
    private CreateTestTypeController ttController;

    /**
     *The CreateTestTypeUI CreateParameterCategoryController
     */
    private CreateParameterCategoryController pcController;


    /**
     * Builds the CreateTestTypeUI Object.
     */
    public CreateTestTypeUI() {
        this.ttController = new CreateTestTypeController();
        this.pcController = new CreateParameterCategoryController();
    }

    /**
     * Responsible for allowing the UI to run.
     */
    public void run(){
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create a new type of test", new ShowTextUI("You have chosen to specify a new type of test.")));
        options.add(new MenuItem("See the available Test Types", new ShowTextUI("You have chosen to see the available test types.")));

        int option = 0;


        do{
            boolean exceptionThrown = false;

            try {
                option = Utils.showAndSelectIndex(options, "\n\nTest Type menu:");
            } catch (Exception e) {
                System.out.printf("\n\nUnavailable option.");
                exceptionThrown = true;
            }

            if (!exceptionThrown) {
                if (option == 0 && pcController.getAllParameterCategoriesDto().isEmpty())
                    System.out.printf("\nThere aren't any Parameter Categories available at the moment. Once you have one, you'll be able to create a test type.\n");

                else if (option == 0 && !pcController.getAllParameterCategoriesDto().isEmpty()) {
                    System.out.printf("\nYou are now creating a Test Type!\n");

                    List<ParameterCategoryDto> parameterCategoriesListDto = pcController.getAllParameterCategoriesDto();


                    boolean createSuccess = false, save = false, saveSuccess = false;

                    System.out.printf("\nChoose at least one or more Parameter Categories.\n");

                    List<ParameterCategory> chosenCategoriesList = new ArrayList<ParameterCategory>();
                    int chosenCategories = 0, x = 0;

                    do {

                        System.out.printf("\nAvailable Parameter Categories:\n");
                        for (int i = 0; i < parameterCategoriesListDto.size(); i++)
                            if (parameterCategoriesListDto != null && !chosenCategoriesList.contains(pcController.getParameterCategoryByCode(parameterCategoriesListDto.get(i).getCode())))
                                System.out.printf("\n" + (i + 1) + ". " + parameterCategoriesListDto.get(i));

                        try {
                            chosenCategories = Utils.readIntegerFromConsole("Insert your option (Press -1 to exit selecting categories):");
                            chosenCategories = chosenCategories - 1;

                            if (chosenCategories > -1) {
                                chosenCategoriesList.add(pcController.getParameterCategoryByCode(parameterCategoriesListDto.get(chosenCategories).getCode()));
                                x++;
                            }

                        } catch (Exception e) {
                            System.out.printf("\n\nUnavailable number, please select again.\n");
                        }

                    } while (x == 0 || (x < parameterCategoriesListDto.size() && chosenCategories != -2));


                    ExternalModule externalModule;
                    try {
                        List<ExternalModule> externalModulesList = ttController.getExternalModules();
                        Utils.showList(externalModulesList, "\nPlease choose an external module.");
                        externalModule = (ExternalModule) Utils.selectsObject(App.getInstance().getCompany().getExternalModules());
                    } catch(Exception e){
                        System.out.println("There was an error when choosing the intended external module");
                        return;
                    }

                    String code, description, collectingMethod;

                    code = leituraDados("code");
                    description = leituraDados("description");
                    collectingMethod = leituraDados("collecting method");


                    try {
                        createSuccess = ttController.createTestType(code, description, collectingMethod, chosenCategoriesList, externalModule);

                    } catch (Exception e) {
                        System.out.printf("\n\nError when creating a new type of test, please try again.\n");
                    }

                    if (createSuccess)
                        save = Utils.confirm("The test type was created successfully. Do you want to add it to the system? If so, press \"s\", if not, press \"n\".");

                    if (save)
                        saveSuccess = ttController.saveTestType();

                    if (saveSuccess)
                        System.out.printf("\nYour test type has been successfully saved!");
                    else
                        System.out.printf("\nYour test types has not been successfully saved.");

                }

                if (option == 1) {
                    List<TestTypeDto> availableTestTypesDto = ttController.getAllTestTypesDto();

                    if (availableTestTypesDto.isEmpty())
                        System.out.printf("\nThere are no available Test Types at the moment.");
                    else {
                        System.out.printf("\nAvailable Test Types:");
                        for (int i = 0; i < availableTestTypesDto.size(); i++)
                            System.out.printf("\n" + (i + 1) + ". " + availableTestTypesDto.get(i));
                    }
                }
            }
        } while (option != -1);
    }

    public static String leituraDados(String atributo){
        String dadoInserido;

        do {
            dadoInserido = Utils.readLineFromConsole("Please insert the " + atributo + " of the test type:");
        } while (!Utils.confirm("Are you sure your " + atributo + " is correct? If so, press \"s\", if not, press \"n\"."));

        return dadoInserido;
    }

}