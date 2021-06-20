package app.controller;

import app.domain.model.Company;
import app.domain.shared.Constants;
import auth.AuthFacade;
import auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    private Company company;
    private AuthFacade authFacade;

    public App()
    {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.company.lerDeFicheiroBinario();
        this.authFacade = company.getAuthFacade();
        bootstrap();
    }

    public Company getCompany()
    {
        return this.company;
    }


    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd)
    {
        return this.authFacade.doLogin(email,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.authFacade.doLogout();
    }

    private Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "Many Labs");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {

        }
        return props;
    }


    private void bootstrap()
    {
        this.authFacade.addUserRole(Constants.ROLE_ADMIN,Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST,Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_SPECDOCTOR,Constants.ROLE_SPECDOCTOR);
        this.authFacade.addUserRole(Constants.ROLE_MEDLABTECH,Constants.ROLE_MEDLABTECH);
        this.authFacade.addUserRole(Constants.ROLE_LABCOORDINATOR,Constants.ROLE_LABCOORDINATOR);
        this.authFacade.addUserRole(Constants.ROLE_CLINICALCHEMTECH,Constants.ROLE_CLINICALCHEMTECH);
        this.authFacade.addUserRole(Constants.ROLE_CLIENT,Constants.ROLE_CLIENT);
        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456",Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Receptionist", "receptionist@lei.sem2.pt", "123456",Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("Specialist Doctor", "specdoctor@lei.sem2.pt", "123456",Constants.ROLE_SPECDOCTOR);
        this.authFacade.addUserWithRole("Laboratory Coordinator", "labc@lei.sem2.pt", "123456",Constants.ROLE_LABCOORDINATOR);
        this.authFacade.addUserWithRole("Medical Lab Technician","mlt@lei.sem2.pt","123456",Constants.ROLE_MEDLABTECH);
        this.authFacade.addUserWithRole("Client","client@lei.sem2.pt","123456",Constants.ROLE_CLIENT);
        this.authFacade.addUserWithRole("Clinical Chemistry Technologist","cct@lei.sem2.pt","123456",Constants.ROLE_CLINICALCHEMTECH);
    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }
}
