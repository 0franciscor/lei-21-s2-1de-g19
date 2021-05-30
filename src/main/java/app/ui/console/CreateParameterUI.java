package app.ui.console;

import app.controller.CreateParameterCategoryController;
import app.controller.CreateParameterController;
import app.ui.console.utils.Utils;
import auth.mappers.dto.ParameterCategoryDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Create Parameter UI.
 *
 * @author Rita Lello
 */
public class CreateParameterUI implements Runnable{

    /**
     * The UI's Create Parameter Controller.
     */
    private CreateParameterController cpc;

    /**
     * The uI's Create Parameter Category Controller.
     */
    private CreateParameterCategoryController cpcc;

    /**
     * Allows access to create parameter controller and create parameter category controller methods.
     */
    public CreateParameterUI(){
        this.cpc=new CreateParameterController();
        this.cpcc=new CreateParameterCategoryController();
    }

    /**
     * It allows you to enter the data necessary to create a parameter, make the confirmation, see all the existing parameters and see if the operation was successful or not.
     */
    public void run(){
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create a new parameter",new ShowTextUI("You have chosen to specify a new parameter.")));
        //options.add(new MenuItem("See all the available parameters",new ShowTextUI("You have chosen to show all the existing parameters.")));

        int option = 0;
        do {
            boolean exceptionThrown = false;
            try{
                option = Utils.showAndSelectIndex(options, "\n\nParameter Menu:");
            } catch (Exception e){
                System.out.printf("\n\nUnavailable option.");
                exceptionThrown = true;
            }
            CreateParameterController parameter = new CreateParameterController();
            String code, description, designation;
            if(!exceptionThrown){
                if (option==0) {
                    //if(cpcc.getAllParameterCategoriesDto().isEmpty())
                    if(cpcc.getAllParameterCategories().isEmpty())
                        System.out.printf("\nYou need to create a parameter category first.");
                    else{
                        System.out.printf("\n...creating a new parameter...\n");
                        code = Utils.readLineFromConsole("Type the code of the new parameter.");
                        description = Utils.readLineFromConsole("Type the description of the new parameter.");
                        designation = Utils.readLineFromConsole("Type the designation of the new parameter.");
                        List<ParameterCategoryDto> categoryDto = cpc.toDto();
                        Utils.showList(categoryDto, "Available Parameter Categories:");
                        ParameterCategoryDto opt = null;
                        do{
                            exceptionThrown = false;
                            try{
                                opt=(ParameterCategoryDto) Utils.selectsObject(categoryDto);
                            }catch(NumberFormatException e){
                                System.out.println("There was a mistake while choosing the parameter category.");
                                exceptionThrown=true;
                            }
                        }while (exceptionThrown);
                        boolean save;
                        if(opt!=null){
                            try {
                                parameter.createParameter(code, description, designation, opt);
                            }catch(Exception e ) {
                                System.out.println(e.getMessage());
                                System.out.println("The parameter was not created. Verify the inputted data and try again.");
                                return;
                            }
                            save=Utils.confirm(String.format("Is this information right?\nIf so, press \"s\", if not, press \"n\". \n\n Code: %s \n Description: %s \n Designation: %s \n Parameter Category: %s", code, description, designation, opt));

                            boolean saveSuccess=false;
                            if(save)
                                saveSuccess=parameter.saveParameter();
                            if(saveSuccess)
                                System.out.printf("Your parameter has been successfully saved!");
                            else
                                System.out.printf("Your parameter has not been successfully saved.");

                        }else
                            System.out.printf("The creation of the parameter was cancelled.");
                    }
                }
            /*if(option==1){
                List<Parameter> allParameters= cpc.getAllParameters();
                if(allParameters.isEmpty())
                    System.out.printf("\nThere are no parameters to be shown.");
                else{
                    System.out.printf("\n...showing all the existing parameters...\n");
                    for(Parameter p: allParameters)
                        System.out.printf("\n"+p);
                }
            }*/
            }

        }
        while (option != -1 );
    }
}
