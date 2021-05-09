package app.ui.console;

import app.controller.CreateParameterCategoryController;
import app.controller.CreateTestTypeController;
import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;
import app.domain.model.TestType;
import app.ui.console.utils.Utils;
import auth.mappers.ParameterCategoryMapper;
import auth.mappers.dto.ParameterCategoryDto;

import java.util.ArrayList;
import java.util.List;

public class CreateTestTypeUI implements Runnable {

    private CreateTestTypeController ttController;
    private CreateParameterCategoryController pcController;

    public CreateTestTypeUI() {
        this.ttController = new CreateTestTypeController();
        this.pcController = new CreateParameterCategoryController();
    }

    public void run(){
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create a new type of test", new ShowTextUI("You have chosen to specify a new type of test.")));
        options.add(new MenuItem("See the available Test Types", new ShowTextUI("You have chosen to see the available test types.")));

        int option = 0;

        do{
            option = Utils.showAndSelectIndex(options, "\n\nTest Type menu:");

            if(option == 0 && pcController.getAllParameterCategories().isEmpty())
                System.out.printf("\nThere aren't any Parameter Categories available at the moment. Once you have one, you'll be able to create a test type.\n");

            else if(option == 0 && !pcController.getAllParameterCategories().isEmpty()) {
                String code, description, collectingMethod;

                System.out.printf("\nYou are now creating a Test Type!\n");

                do {
                    code = Utils.readLineFromConsole("Please insert the code of the test type:");
                } while (!Utils.confirm("Are you sure your code is correct? If so, press \"s\", if not, press \"n\"."));

                do {
                    description = Utils.readLineFromConsole("Please insert the description of the test type:");
                } while (!Utils.confirm("Are you sure your description is correct? If so, press \"s\", if not, press \"n\"."));

                do {
                    collectingMethod = Utils.readLineFromConsole("Please insert the collecting method of the test type:");
                } while (!Utils.confirm("Are you sure your collecting method is correct? If so, press \"s\", if not, press \"n\"."));


                List<ParameterCategoryDto> categoryDto = ttController.getParameterCategoryDto();


                boolean createSuccess = false, save = false, saveSuccess = false;


                if (categoryDto.size() == 1) {
                    if (Utils.showAndSelectIndex(categoryDto, "Available Parameter Category:") == 0) {
                        createSuccess = ttController.createTestType(code, description, collectingMethod, pcController.getParameterCategoryByCode(categoryDto.get(0).getCode()));
                    }

                } else if (categoryDto.size() > 1) {

                    System.out.printf("\nChoose at least one or more Parameter Categories. To stop choosing, insert -1");

                    int[] chosenCategoriesNumberList = new int[categoryDto.size()];
                    int x = 0;
                    int chosenCategories;

                    do {
                        chosenCategories = Utils.showAndSelectIndex(categoryDto, "Available Parameter Categories:");
                        if (chosenCategories != -1)
                            chosenCategoriesNumberList[x] = chosenCategories;

                    } while (x < categoryDto.size() && chosenCategories != -1);

                    List<ParameterCategory> chosenCategoriesList = new ArrayList<ParameterCategory>();

                    for (int i = 0; i < x; i++)
                        chosenCategoriesList.add(pcController.getParameterCategoryByCode(categoryDto.get(chosenCategoriesNumberList[i]).getCode()));

                    createSuccess = ttController.createTestType(code, description, collectingMethod, chosenCategoriesList);
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

            if(option == 1) {
                List<TestType> availableTestTypes = ttController.getAllTestTypes();

                if (availableTestTypes.isEmpty())
                    System.out.printf("\nThere are no available Test Types at the moment.");
                else
                    Utils.showList(ttController.getAllTestTypes(), "Available Test Types:");

            }

        } while (option != -1);

    }
}