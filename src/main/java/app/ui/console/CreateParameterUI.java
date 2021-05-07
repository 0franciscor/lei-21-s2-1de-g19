package app.ui.console;

import app.controller.CreateParameterCategoryController;
import app.controller.CreateParameterController;
import app.domain.model.Parameter;
import app.ui.console.utils.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CreateParameterUI implements Runnable{

    private CreateParameterController cpc;
    private CreateParameterCategoryController cpcc;

    public CreateParameterUI(){
        this.cpc=new CreateParameterController();
        this.cpcc=new CreateParameterCategoryController();
    }

    public void run(){
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Create a new parameter",new ShowTextUI("You have chosen to specify a new parameter.")));
        options.add(new MenuItem("See all the available parameters",new ShowTextUI("You have chosen to show all the existing parameters.")));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");
            CreateParameterController parameter = new CreateParameterController();
            parameter.createParameter();

            if (option==0) {
                if(cpcc.getAllParameterCategories().isEmpty())
                    System.out.printf("\nYou need to create a parameter category first.");
                else{
                    System.out.printf("\n...creating a new parameter...\n");
                    do{
                        String code = Utils.readLineFromConsole("Type the code of the new parameter.");
                    }while(!Utils.confirm("Is the code of the new parameter correct? If so, press \"s\", if not, press \"n\""));
                    do{
                        String description = Utils.readLineFromConsole("Type the description of the new parameter.");
                    }while(!Utils.confirm("Is the code of the new parameter correct? If so, press \"s\", if not, press \"n\""));
                    do{
                        String designation = Utils.readLineFromConsole("Type the designation of the new parameter.");
                    }while(!Utils.confirm("Is the code of the new parameter correct? If so, press \"s\", if not, press \"n\""));
                    parameter=
                }
            }
            if(option==1){
                List<Parameter> allParameters= cpc.getAllParameters();
                if(allParameters.isEmpty())
                    System.out.printf("\nThere are no parameters to be shown");
                else{
                    System.out.printf("\n...showing all the existing parameters...\n");
                    for(Parameter parameter: allParameters)
                        System.out.println(parameter);
                }
            }
        }
        while (option != -1 );
    }
}
