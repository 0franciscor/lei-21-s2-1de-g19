package app.domain.model;

import java.io.*;
import java.util.Date;


/**
 * The notification service.
 *
 * @author Francisco Redol
 */
public class Notification {

    /**
     * Responsible for printing to a file.
     */
    private File ficheiro;

    /**
     * @throws IOException
     *
     * The notification builder.
     */
    public Notification() throws IOException {
        ficheiro = new File("emailAndSMSMessages.txt");

        if(ficheiro.exists())
            ficheiro.delete();
        else
            ficheiro.createNewFile();

    }

    /**
     * @param message The message they want to print.
     *
     * The method responsible for writing to a file.
     */
    public void writeToFile(String message) throws IOException {
        Writer output  = new BufferedWriter(new FileWriter(ficheiro, true));
        output.append(new Date() + message + "\n");
        output.close();

    }
}
