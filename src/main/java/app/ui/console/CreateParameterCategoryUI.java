package app.ui.console;

import app.controller.CreateParameterCategoryController;
import app.ui.console.utils.Utils;
import auth.mappers.dto.ParameterCategoryDto;

import java.util.ArrayList;
import java.util.List;

public class CreateParameterCategoryUI implements Runnable{

    /**
     * The CreateParameterCategoryUI ParameterCategoryController.
     */
    private CreateParameterCategoryController pcController;

    /**
     *The CreateParameterCategoryUI ParameterCategoryDto
     */
    private List<ParameterCategoryDto> parameterCategoriesListDto;

    /**
     * Builds the CreateParameterCategoryUI Object.
     */
    public CreateParameterCategoryUI() {
            this.pcController = new CreateParameterCategoryController();
        }

    /**
     * Responsible for allowing the UI to run.
     */
    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create a new Parameter Category", new ShowTextUI("You have chosen to specify a new parameter.")));
        options.add(new MenuItem("See the available Parameter Categories", new ShowTextUI("You have chosen to specify a new parameter.")));

        int option = 0;


        do {
            boolean exceptionThrown = false;

            try {
                option = Utils.showAndSelectIndex(options, "\n\nParameter Category menu:");
            } catch (Exception e) {
                System.out.printf("\n\nUnavailable option.");
                exceptionThrown = true;
            }

            if (!exceptionThrown) {
                if (option == 0) {
                    System.out.printf("\nYou are now creating a Parameter Category!\n");

                    String name = leituraDados("name");
                    String code = leituraDados("code");

                    boolean create = false, save = false, saveSuccess = false;

                    try {
                        create = pcController.createParameterCategory(name, code);
                    } catch (Exception e) {
                        System.out.printf("\n\nError when creating a new parameter category, please try again.\n");
                    }

                    if (create)
                        save = Utils.confirm("The parameter category has been created successfully!\nDo you wish to save it? If so, press \"s\", if not, press \"n\".");

                    if (save)
                        saveSuccess = pcController.saveParameterCategory();

                    if (saveSuccess)
                        System.out.printf("\nYour parameter category has been successfully saved!");
                    else
                        System.out.printf("\nYour parameter category has not been successfully saved.");

                }

                if (option == 1) {
                    parameterCategoriesListDto = pcController.getAllParameterCategoriesDto();
                    if (!parameterCategoriesListDto.isEmpty()) {
                        System.out.printf("\nAvailable Parameter Categories:\n");
                        for (ParameterCategoryDto pc : parameterCategoriesListDto)
                            System.out.printf("\n" + pc);
                    } else
                        System.out.printf("\nThere are no Parameter categories available.");
                }

            }
        } while (option != -1);
    }

    public static String leituraDados(String atributo){
        String dadoInserido;

        do {
            dadoInserido = Utils.readLineFromConsole("Please insert the " + atributo + " of the parameter category:");
        } while (!Utils.confirm("Are you sure your " + atributo + " is correct? If so, press \"s\", if not, press \"n\"."));

        return dadoInserido;
    }
}
