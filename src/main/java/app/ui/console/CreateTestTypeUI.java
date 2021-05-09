package app.ui.console;

import app.controller.CreateParameterCategoryController;
import app.controller.CreateTestTypeController;
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
            option = Utils.showAndSelectIndex(options, "\n\nTest Type menu:");

            if(option == 0 && pcController.getAllParameterCategoriesDto().isEmpty())
                System.out.printf("\nThere aren't any Parameter Categories available at the moment. Once you have one, you'll be able to create a test type.\n");

            else if(option == 0 && !pcController.getAllParameterCategoriesDto().isEmpty()) {
                System.out.printf("\nYou are now creating a Test Type!\n");

                List<ParameterCategoryDto> categoryDto = pcController.getAllParameterCategoriesDto();

                boolean createSuccess, save = false, saveSuccess = false;

                System.out.printf("\nChoose at least one or more Parameter Categories.\n");

                List<ParameterCategory> chosenCategoriesList = new ArrayList<ParameterCategory>();
                int chosenCategories, x = 0;

                do {

                    System.out.printf("\nAvailable Parameter Categories:\n");

                    for (int i = 0; i< categoryDto.size(); i++)
                        if(categoryDto != null && !chosenCategoriesList.contains(pcController.getParameterCategoryByCode(categoryDto.get(i).getCode())))
                            System.out.printf("\n" + (i+1)+ ". " + categoryDto.get(i));

                    chosenCategories = Utils.readIntegerFromConsole("Insert your option (Press -1 to exit selecting categories):");
                    chosenCategories = chosenCategories - 1;

                    if (chosenCategories > -1) {
                        chosenCategoriesList.add(pcController.getParameterCategoryByCode(categoryDto.get(chosenCategories).getCode()));
                        x++;
                    }

                } while (x== 0 || (x < categoryDto.size() && chosenCategories != -2));


                String code, description, collectingMethod;


                do {
                    code = Utils.readLineFromConsole("Please insert the code of the test type:");
                } while (!Utils.confirm("Are you sure your code is correct? If so, press \"s\", if not, press \"n\"."));

                do {
                    description = Utils.readLineFromConsole("Please insert the description of the test type:");
                } while (!Utils.confirm("Are you sure your description is correct? If so, press \"s\", if not, press \"n\"."));

                do {
                    collectingMethod = Utils.readLineFromConsole("Please insert the collecting method of the test type:");
                } while (!Utils.confirm("Are you sure your collecting method is correct? If so, press \"s\", if not, press \"n\"."));


                if(chosenCategoriesList.size() == 1)
                    createSuccess = ttController.createTestType(code, description, collectingMethod, chosenCategoriesList.get(0));
                else
                    createSuccess = ttController.createTestType(code, description, collectingMethod, chosenCategoriesList);


                if (createSuccess)
                    save = Utils.confirm("The test type was created successfully. Do you want to add it to the system? If so, press \"s\", if not, press \"n\".");

                if (save)
                    saveSuccess = ttController.saveTestType();

                if (saveSuccess)
                    System.out.printf("\nYour test type has been successfully saved!");
                else
                    System.out.printf("\nYour test types has not been successfully saved.");

            }

            if(option == 1) {
                List<TestTypeDto> availableTestTypesDto = ttController.getAllTestTypesDto();

                if (availableTestTypesDto.isEmpty())
                    System.out.printf("\nThere are no available Test Types at the moment.");
                else {
                    System.out.printf("\nAvailable Test Types:");
                    for (int i = 0; i< availableTestTypesDto.size(); i++)
                        System.out.printf("\n" + (i+1)+ ". " + availableTestTypesDto.get(i));
                }
            }

        } while (option != -1);

    }
}