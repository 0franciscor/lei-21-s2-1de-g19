package app.domain.model;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Represents the NHSReport class.
 *
 * @author Eduardo Gonçalves
 */
public class NHSReport {

    /**
     * The chosen significance level.
     */
    private double sigLevel;

    /**
     * The chosen confidence level.
     */
    private double confLevel;


    /**
     * The chosen hypothesis test.
     */
    private boolean hypTest;

    /**
     * The NHSReport's linearRegression.
     */
    private LinearRegression linearRegression;

    /**
     * The NHSReport's MultilinearRegression.
     */
    private MultilinearRegression multilinearRegression;

    /**
     * @param sigLevel the chosen significance level.
     * @param confLevel the chosen confidence level.
     * @param hypTest the hypothesis test.
     *
     * The NHSReport constructor.
     */

    public NHSReport(double sigLevel, double confLevel, boolean hypTest){

        this.sigLevel = sigLevel;
        this.confLevel = confLevel;
        this.hypTest = hypTest;

    }

    /**
     * @param sigLevel the chosen significance level.
     * @param confLevel the chosen confidence level.
     *
     * The NHSReport constructor.
     */
    public NHSReport(double sigLevel, double confLevel){

        this.sigLevel = sigLevel;
        this.confLevel = confLevel;

    }


    /**
     * @param x the chosen X array.
     * @param y the calculated Y array.
     * @param lstDateExceptSundays list of all dates except sundays.
     *
     * Calculates all the data needed to generate a report.
     *
     * @return all the data needed to generate a report.
     */
    public String calculateDataLinear(double[] x, double[] y, List<Date> lstDateExceptSundays){

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
        double R;

        if(linearRegression.slope()<0)
            R=Math.sqrt(linearRegression.R2())-2*Math.sqrt(linearRegression.R2());
        else
            R=Math.sqrt(linearRegression.R2());

        stringSaida.append("\n" +
                String.format(
                        "R2= %.4f\nR2adjusted= %.4f\nR= %.4f\n\n", R2, R2adj, R));


        // T STUDENT
        TDistribution td= new TDistribution(degreeFreedomErro);
        double alphaTD =(sigLevel/100)/2;
        double tStudent;
        if(alphaTD> 0.5) {
            tStudent = td.inverseCumulativeProbability(alphaTD);
        }
        else {
            tStudent = td.inverseCumulativeProbability(1 - alphaTD);
        }

        TDistribution td1= new TDistribution(degreeFreedomErro);
        double alphaTD1 =(1 - confLevel/100)/2;
        double tStudentIntConf;
        if(alphaTD1> 0.5) {
            tStudentIntConf = td1.inverseCumulativeProbability(alphaTD1);
        }
        else {
            tStudentIntConf = td1.inverseCumulativeProbability(1 - alphaTD1);
        }


        //DESVIO PADRÃO
        double desvPadr = Math.sqrt(SE/ (x.length-2));


        double Sxx = 0;

        //MÉDIA ARRAY X

        double mediaX = 0;
        for(int i = 0; i<x.length; i++){
            mediaX += x[i];
        }
        mediaX /= x.length;

        // Sxx

        for(int i = 0; i<x.length; i++){
            Sxx += Math.pow((x[i] - mediaX), 2);
        }

        //DELTA
        double delta, lower, upper;
        double num1 = 1;
        double lengthArray = x.length;

        String [] intConfidence = new String[x.length];

        for(int i=0; i<x.length; i++){
            double a = linearRegression.predict(x[i]);
            delta = tStudentIntConf * desvPadr * Math.sqrt(num1/lengthArray+Math.pow(x[i]-mediaX,2)/Sxx);
            lower=a-delta;
            upper=a+delta;
            intConfidence[i] = String.format("] %.2f ; %.2f [",lower,upper);
        }

        // F-SNEDECOR
        FDistribution fd= new FDistribution(degreeFreedomRegressao,degreeFreedomErro);
        double alphaFD= sigLevel/100;
        double fSnedecor= fd.inverseCumulativeProbability(1- alphaFD);

        double tObs;
        if (hypTest){
            tObs = linearRegression.intercept()/(desvPadr*Math.sqrt(1.00/x.length + Math.pow(mediaX,2)/Sxx));

            if (Math.abs(tObs) > tStudent){
                stringSaida.append("\nHypothesis tests for regression coefficient a\n" +
                        "H0: a=0  H1: a<>0\n" +
                        String.format("t_obs = %.4f\n",tObs) +
                        "Decision:\n" +
                        String.format("|%.4f| > %.4f -> reject H0\n",tObs,tStudent));
            } else {
                stringSaida.append("\nHypothesis tests for regression coefficient a\n" +
                        "H0: a=0  H1: a<>0\n" +
                        String.format("t_obs = %.4f\n",tObs) +
                        "Decision:\n" +
                        String.format("|%.4f| < %.4f -> no reject H0\n",tObs,tStudent));
            }

        } else {
            tObs = linearRegression.slope()/(desvPadr/Math.sqrt(Sxx));

            if (Math.abs(tObs) > tStudent){
                stringSaida.append("\nHypothesis tests for regression coefficient b\n" +
                        "H0: b=0  H1: b<>0\n" +
                        String.format("t_obs = %.4f\n",tObs) +
                        "Decision:\n" +
                        String.format("|%.4f| > %.4f -> reject H0\n",tObs,tStudent));
            } else {
                stringSaida.append("\nHypothesis tests for regression coefficient b\n" +
                        "H0: b=0  H1: b<>0\n" +
                        String.format("t_obs = %.4f\n",tObs) +
                        "Decision:\n" +
                        String.format("|%.4f| < %.4f -> no reject H0\n",tObs,tStudent));
            }
        }

        stringSaida.append(
                "\nSignificance model with Anova\n" +
                        "H0: b=0  H1:b<>0 \n\n" +
                        "\t\t\tdf\t\tSS\t\tMS\t\t\tF\t\n" +
                        String.format(
                                "Regression\t%.0f\t%.4f\t\t%.4f\t\t%.4f\t\n", degreeFreedomRegressao, SR, MSR, fRegressao
                        ) +
                        String.format(
                                "Residual\t%.0f\t%.4f\t\t%.4f\n", degreeFreedomErro, SE, MSE
                        ) +
                        String.format(
                                "Total\t\t%.0f\t%.4f\n", degreeFreedom, (SE+SR)
                        ));


        stringSaida.append(String.format("\nf0 = %.4f ", fRegressao) +
                String.format("\nF-Snedecor = %.4f", fSnedecor));

        if (fRegressao > fSnedecor) {
            stringSaida.append("\nDecision:"+
                    String.format("\n%.4f > %.4f -> Reject H0, the regression model is significant.\n", fRegressao,fSnedecor));

        } else {
            stringSaida.append("\nDecision:"+
                    String.format("\n%.4f < %.4f -> No reject H0, the regression model is not significant.\n", fRegressao,fSnedecor));
        }

        stringSaida.append("\n// Prediction values \n" +
                "Date\t\t\t\t\t\t\t\tNumber of OBSERVED positive cases\t\t\t\t\tNumber of ESTIMATED positive cases\t\t\t\t\t"+confLevel+"% intervals");


        for(int i = lstDateExceptSundays.size()-1 ; i>=0; i--){
            stringSaida.append(String.format("\n" + lstDateExceptSundays.get(i) + "\t\t\t\t\t\t %.0f \t\t\t\t\t\t\t\t\t\t\t %.2f \t\t\t\t\t\t\t\t\t %s", y[i], linearRegression.predict(x[i]), intConfidence[i]));
        }

        return stringSaida.toString();
    }

    /**
     * @param BiarrayX the chosen X bidimensional array.
     * @param y the calculated Y array.
     * @param lstDateExceptSundays list of all dates except sundays.
     *
     * Calculates all the data needed to generate a report.
     *
     * @return all the data needed to generate a report.
     */
    public String calculateDataMultiLinear(double[][] BiarrayX, double[] y, List<Date> lstDateExceptSundays){

        this.multilinearRegression = new MultilinearRegression(BiarrayX,y);

        StringBuilder stringSaida = new StringBuilder();

        stringSaida.append("The regression model fitted using data from the interval:\n" +
                String.format(
                        "^y= %.2fx2 + %.2fx1 + %.2f\n", multilinearRegression.beta2(), multilinearRegression.beta1(), multilinearRegression.beta0()
                )
                +
                "\n//" +
                "Other statistics");

        stringSaida.append("\n" +
                String.format(
                        "R2= %.4f\nR2adjusted= %.4f\nR= %.4f\n\n", multilinearRegression.r2(), multilinearRegression.r2adj(), Math.sqrt(multilinearRegression.r2())));

        // T STUDENT
        TDistribution td= new TDistribution(multilinearRegression.degreeFreedomErro());
        double alphaTD =(sigLevel/100)/2;
        double tStudent;
        if(alphaTD> 0.5) {
            tStudent = td.inverseCumulativeProbability(alphaTD);
        }
        else {
            tStudent = td.inverseCumulativeProbability(1 - alphaTD);
        }

        TDistribution td1= new TDistribution(multilinearRegression.degreeFreedomErro());
        double alphaTD1 =(1 - confLevel/100)/2;
        double tStudentIntConf;
        if(alphaTD1> 0.5) {
            tStudentIntConf = td1.inverseCumulativeProbability(alphaTD1);
        }
        else {
            tStudentIntConf = td1.inverseCumulativeProbability(1 - alphaTD1);
        }

        //DESVIO PADRÃO
        double desvPadr = Math.sqrt(multilinearRegression.MQE());

        //DELTA
        double delta, lower, upper, xtcx, alfay;

        String [] intConfidence = new String[BiarrayX.length];

        for(int i=0; i<BiarrayX.length; i++){
            double [][]alfayMatriz;
            double [][] xtc;
            double [][] xtcxMatriz;
            double [][] arrayAuxT = new double[1][BiarrayX[0].length];
            double [][] arrayAux = new double[BiarrayX[0].length][1];
            for (int z=0; z<arrayAuxT[0].length; z++){
                arrayAuxT[0][z] = BiarrayX[i][z];
                arrayAux[z][0] = BiarrayX [i][z];
            }

            alfayMatriz = multilinearRegression.multiplication(arrayAuxT, multilinearRegression.betas());
            alfay = alfayMatriz[0][0];

            xtc = multilinearRegression.multiplication(arrayAuxT,multilinearRegression.xtxinv());
            xtcxMatriz = multilinearRegression.multiplication(xtc, arrayAux);

            xtcx = xtcxMatriz[0][0];

            delta = tStudentIntConf * desvPadr * Math.sqrt(xtcx);

            lower=alfay-delta;
            upper=alfay+delta;
            intConfidence[i] = String.format("] %.2f ; %.2f [",lower,upper);
        }


        // F-SNEDECOR
        FDistribution fd= new FDistribution(multilinearRegression.degreeFreedomRegressao(),multilinearRegression.degreeFreedomErro());
        double alphaFD= sigLevel/100;
        double fSnedecor= fd.inverseCumulativeProbability(1- alphaFD);

        double tObs;

        double [][] xtxinvAux = multilinearRegression.xtxinv();
        tObs = multilinearRegression.beta0()/(desvPadr*Math.sqrt(xtxinvAux[0][0]));

        if (Math.abs(tObs) > tStudent){
            stringSaida.append("\nHypothesis tests for regression coefficient b0\n" +
                    "H0: b0=0  H1: b0<>0\n" +
                    String.format("t_obs = %.4f\n",tObs) +
                    "Decision:\n" +
                    String.format("|%.4f| > %.4f -> reject H0\n",tObs,tStudent));
        } else {
            stringSaida.append("\nHypothesis tests for regression coefficient b0\n" +
                    "H0: b0=0  H1: b0<>0\n" +
                    String.format("t_obs = %.4f\n",tObs) +
                    "Decision:\n" +
                    String.format("|%.4f| < %.4f -> no reject H0\n",tObs,tStudent));
        }


        tObs = multilinearRegression.beta1()/(desvPadr*Math.sqrt(xtxinvAux[1][1]));

        if (Math.abs(tObs) > tStudent){
            stringSaida.append("\nHypothesis tests for regression coefficient b1\n" +
                    "H0: b1=0  H1: b1<>0\n" +
                    String.format("t_obs = %.4f\n",tObs) +
                    "Decision:\n" +
                    String.format("|%.4f| > %.4f -> reject H0\n",tObs,tStudent));
        } else {
            stringSaida.append("\nHypothesis tests for regression coefficient b1\n" +
                    "H0: b1=0  H1: b1<>0\n" +
                    String.format("t_obs = %.4f\n",tObs) +
                    "Decision:\n" +
                    String.format("|%.4f| < %.4f -> no reject H0\n",tObs,tStudent));
        }

        tObs = multilinearRegression.beta2()/(desvPadr*Math.sqrt(xtxinvAux[2][2]));

        if (Math.abs(tObs) > tStudent){
            stringSaida.append("\nHypothesis tests for regression coefficient b2\n" +
                    "H0: b2=0  H1: b2<>0\n" +
                    String.format("t_obs = %.4f\n",tObs) +
                    "Decision:\n" +
                    String.format("|%.4f| > %.4f -> reject H0\n",tObs,tStudent));
        } else {
            stringSaida.append("\nHypothesis tests for regression coefficient b2\n" +
                    "H0: b2=0  H1: b2<>0\n" +
                    String.format("t_obs = %.4f\n",tObs) +
                    "Decision:\n" +
                    String.format("|%.4f| < %.4f -> no reject H0\n",tObs,tStudent));
        }

        stringSaida.append(
                "\nSignificance model with Anova\n" +
                        "H0: b1=b2=0  H1:bj<>0, j=1,2 \n\n" +
                        "\t\t\tdf\t\tSS\t\tMS\t\t\tF\t\n" +
                        String.format(
                                "Regression\t%.0f\t%.4f\t\t%.4f\t\t%.4f\t\n", multilinearRegression.degreeFreedomRegressao(), multilinearRegression.SQR(), multilinearRegression.MQR(), multilinearRegression.F()
                        ) +
                        String.format(
                                "Residual\t%.0f\t%.4f\t\t%.4f\n", multilinearRegression.degreeFreedomErro(), multilinearRegression.SQE(), multilinearRegression.MQE()
                        ) +
                        String.format(
                                "Total\t\t%.0f\t%.4f\n", multilinearRegression.degreeFreedom(), (multilinearRegression.SQE()+multilinearRegression.SQR())
                        ));


        stringSaida.append(String.format("\nf0 = %.4f ", multilinearRegression.F()) +
                String.format("\nF-Snedecor = %.4f", fSnedecor));

        if (multilinearRegression.F() > fSnedecor) {
            stringSaida.append("\nDecision:"+
                    String.format("\n%.4f > %.4f -> Reject H0, the regression model is significant.\n", multilinearRegression.F(),fSnedecor));

        } else {
            stringSaida.append("\nDecision:"+
                    String.format("\n%.4f < %.4f -> No reject H0, the regression model is not significant.\n", multilinearRegression.F(),fSnedecor));
        }


        stringSaida.append("\n// Prediction values \n" +
                "Date\t\t\t\t\t\t\t\tNumber of OBSERVED positive cases\t\t\t\t\tNumber of ESTIMATED positive cases\t\t\t\t\t"+confLevel+"% intervals");

        for(int i = lstDateExceptSundays.size()-1 ; i>=0; i--){
            stringSaida.append(String.format("\n" + lstDateExceptSundays.get(i) + "\t\t\t\t\t\t %.0f \t\t\t\t\t\t\t\t\t\t\t %.2f \t\t\t\t\t\t\t\t\t %s", y[i], multilinearRegression.predict(BiarrayX[i][1], BiarrayX[i][2]), intConfidence[i]));
        }

        return stringSaida.toString();
    }

    /**
     * Returns a textual representation of the object, which contains all of its attributes.
     *
     * @return NHSReport characteristics.
     */
    public String toString (){
        return String.format("\nSignificance level: %.2f \nConfidence level: %.2f \nHypothesis test: %b ",this.sigLevel, this.confLevel, this.hypTest);
    }
}
