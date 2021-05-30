package app.domain.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    private PrintWriter out;

    /**
     * @throws FileNotFoundException
     *
     * The notification builder.
     */
    public Notification() throws FileNotFoundException {
        out = new PrintWriter("emailAndSMSMessages.txt");
    }

    /**
     * @param message The message they want to print.
     *
     * The method responsible for writing to a file.
     */
    public void writeToFile(String message) {
        out.println(new Date() + ": " + message);
    }

    /**
     * Method responsible for closing the PrintWriter
     */
    public void close(){
        out.close();
    }
}
