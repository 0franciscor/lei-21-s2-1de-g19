package app.ui.console;

import app.controller.CreateParameterCategoryController;
import app.domain.model.ParameterCategory;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class CreateParameterCategoryUI implements Runnable{

    private CreateParameterCategoryController pcController;
    private List<ParameterCategory> parameterCategoriesList;

    public CreateParameterCategoryUI() {
            this.pcController = new CreateParameterCategoryController();
        }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create a new Parameter Category", new ShowTextUI("You have chosen to specify a new parameter.")));
        options.add(new MenuItem("See the available Parameter Categories", new ShowTextUI("You have chosen to specify a new parameter.")));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nParameter Category menu:");

            if (option == 0) {

                String name, code;
                System.out.printf("\nYou are now creating a Parameter Category!\n");
                do {
                    name = Utils.readLineFromConsole("Please insert the name of the category:");
                } while (!Utils.confirm("Are you sure your name is correct? If so, press \"s\", if not, press \"n\"."));

                do {
                    code = Utils.readLineFromConsole("Please insert the code of the category:");
                } while (!Utils.confirm("Are you sure your code is correct? If so, press \"s\", if not, press \"n\"."));

                boolean save;

                if (pcController.createParameterCategory(name, code)) {
                    save = Utils.confirm("The parameter category has been created successfully!\nDo you wish to save it? If so, press \"s\", if not, press \"n\".");

                    boolean saveSuccess = false;
                    if (save)
                        saveSuccess = pcController.saveParameterCategory();

                    if (saveSuccess)
                        System.out.printf("\nYour parameter category has been successfully saved!");
                    else
                        System.out.printf("\nYour parameter category has not been successfully saved.");
                }
            }

            if(option == 1) {
                parameterCategoriesList = pcController.getAllParameterCategories();
                if (!parameterCategoriesList.isEmpty()) {
                    System.out.printf("\nAvailable Parameter Categories:\n");
                    for(ParameterCategory pc: parameterCategoriesList)
                        System.out.println(pc);
                } else
                    System.out.printf("\nThere are no Parameter categories available.");
            }

        } while (option != -1);
    }
}
