package app.domain.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Notification {

    private PrintWriter out;

    public Notification() throws FileNotFoundException {
        out = new PrintWriter(new File("emailAndSMSMessages.txt"));
    }

    public void writeToFile(String message){
        out.println(message);
    }
}
