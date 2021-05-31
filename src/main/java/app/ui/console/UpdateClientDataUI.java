package app.ui.console;

import app.controller.UpdateClientDataController;

/**
 * Represents update client data UI.
 *
 * @author Eduardo Gonçalves, Rita Lello
 */
public class UpdateClientDataUI implements Runnable {

    /**
     * Update client data Controller.
     */
    private UpdateClientDataController ctrl;

    /**
     * Allows access to update client data controller methods.
     */
    public UpdateClientDataUI (){

        ctrl = new UpdateClientDataController();
    }


    public void run() {

    }
}
