package app.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the NHSReport class.
 *
 * @author Eduardo Gonçalves
 */
public class NHSReport {

    /**
     * The list of tests that have result covid positive.
     */
    private List<Test> listTestWithResultCovidPositive;
    private LinearRegression linearRegression;

    public NHSReport(List<Test> listTestWithResultCovidPositive){

        this.listTestWithResultCovidPositive = listTestWithResultCovidPositive;
    }


    /**
     * Calculates all the data needed to generate a report.
     *
     * @return all the data needed to generate a report.
     */
    public String calculateData(){


    }

    /*
    public boolean validateNHSReport(){

    }

     */




}
