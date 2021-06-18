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


    private LinearRegression linearRegression;

    public NHSReport(){}


    /**
     * Calculates all the data needed to generate a report.
     *
     * @return all the data needed to generate a report.
     */
    public String calculateData(double[] x, double[] y){

        this.linearRegression = new LinearRegression(x,y);



        LinearRegression linearRegression = new LinearRegression(x, y);


        StringBuilder stringSaida = new StringBuilder();



        stringSaida.append("The regression model fitted using data from the interval:\n" +
                String.format(
                        "^y= %.2fx + %.2f\n", linearRegression.slope(), linearRegression.intercept()
                )
                +
                "\n//" +
                "Other statistics");

        //double asd = new Fdistribution(3.51, 3.78);

        double SR= 0, SE = 0, ST = 0;

        //CALCULO ST

        double media = 0;
        for(int i = 0; i<y.length; i++){
            media += y[i];
        }
        media /= y.length;

        for(int i = 0; i<y.length; i++){
            ST += Math.pow((y[i] - media), 2);
        }

        //CALCULO SR

        for(int i = 0; i<x.length; i++){
            double Y = linearRegression.intercept() + linearRegression.slope()*x[i];
            SR += Math.pow((Y - media), 2);
        }

        SE = ST - SR;

        double degreeFreedom = x.length -1;
        double degreeFreedomErro = degreeFreedom-1;
        double degreeFreedomRegressao = degreeFreedom - degreeFreedomErro;
        double MSR= SR/degreeFreedomRegressao;
        double MSE= SE/degreeFreedomErro;
        double fRegressao = MSR/MSE;




        double R2 = linearRegression.R2();
        double R2adj = 1-((SE/degreeFreedomErro)/(ST/degreeFreedom));

        stringSaida.append("\n" +
                String.format(
                        "R2= %.4f\nR2adjusted= %.4f\nR= %.4f", R2, R2adj, Math.sqrt(linearRegression.R2()))
                + "\n\n//");



        stringSaida.append(
                "\nSignificance model with Anova\n" +
                        "H0: b=0  H1:b<>0 \n\n" +
                        "\t\t\tdf\t\tSS\t\tMS\t\t\tF\t\n" +
                        String.format(
                                "Regression\t%.0f\t%.4f\t%.4f\t%.4f\t\n", degreeFreedomRegressao, SR, MSR, fRegressao
                        ) +
                        String.format(
                                "Residual\t%.0f\t%.4f\t\t%.4f\n", degreeFreedomErro, SE, MSE
                        ) +
                        String.format(
                                "Total\t\t%.0f\t%.4f\n", degreeFreedom, (SE+SR)
                        ));






        System.out.println(stringSaida.toString());


        return stringSaida.toString();

    }

    /*
    public boolean validateNHSReport(){

    }

     */




}
