package app.domain.model;


import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class MultilinearRegression {

    private double SQT, SQR,SQE,beta0,beta1,beta2,degreeFreedom,degreeFreedomErro,degreeFreedomRegressao,r2,r2adj,MQR,MQE,F;

    private double[][] xtxinv, betas;

    /**
     * Performs a multilinear regression on the data points (y[i], x[i][i]).
     *
     * @param  x the values of the predictor variable
     * @param  y the corresponding values of the response variable
     * @throws IllegalArgumentException if the lengths of the two arrays are not equal
     */
    public MultilinearRegression(double[][]x,double[]y){
        if (x.length != y.length) {
            throw new IllegalArgumentException("array lengths are not equal");
        }

        double [][] xt=new double[x[0].length][x.length];
        for(int i=0;i<xt.length;i++){
            for(int j=0;j<xt[0].length;j++){
                xt[i][j]=x[j][i];
            }
        }

        double [][] xtx = multiplication(xt,x);

        RealMatrix xtxmat = new Array2DRowRealMatrix(xtx);
        RealMatrix xtxinvmat = MatrixUtils.inverse(xtxmat);
        xtxinv=xtxinvmat.getData();

        double [][]yt=new double[1][y.length];
        double [][]yy=new double[y.length][1];
        for(int i=0; i<y.length;i++){
            yy[i][0]=y[i];
            yt[0][i]=y[i];
        }

        double[][] ytymat=multiplication(yt,yy);
        double yty=ytymat[0][0];

        double [][]xty=multiplication(xt,yy);
        betas=multiplication(xtxinv,xty);

        double [][] betast=new double[1][betas.length];
        for(int i=0;i<betas.length;i++){
            betast[0][i]=betas[i][0];
        }

        double [][]btxt =multiplication(betast,xt);
        double[][] btxtymat=multiplication(btxt,yy);

        double btxty=btxtymat[0][0];

        double mediaY=0;
        for(int i=0;i<y.length;i++){
            mediaY+=y[i];
        }
        mediaY/= y.length;

        SQT=yty-y.length*Math.pow(mediaY,2);
        SQR=btxty-y.length*Math.pow(mediaY,2);
        SQE=yty-btxty;
        beta0=betas[0][0];
        beta1=betas[1][0];
        beta2=betas[2][0];
        degreeFreedomRegressao = x[0].length-1;
        degreeFreedomErro = y.length-(degreeFreedomRegressao+1);
        degreeFreedom = degreeFreedomErro+degreeFreedomRegressao;
        r2=SQR/SQT;
        r2adj=1-(SQE/degreeFreedomErro)/(SQT/degreeFreedom);
        MQR=SQR/degreeFreedomRegressao;
        MQE=SQE/degreeFreedomErro;
        F=MQR/MQE;
    }

    /**
     * Returns the sum of squares error.
     *
     * @return the sum of squares error
     */
    public double SQE(){
        return SQE;
    }

    /**
     * Returns the sum of squares regression.
     *
     * @return the sum of squares regression
     */
    public double SQR(){
        return SQR;
    }

    /**
     * Returns the coefficient without association to the predictor variables.
     *
     * @return the coefficient without association to the predictor variables
     *
     */
    public double beta0(){
        return beta0;
    }

    /**
     * Returns the coefficient associated to the predictor variable x1.
     *
     * @return the coefficient associated to the predictor variable x1
     *
     */
    public double beta1(){
        return beta1;
    }

    /**
     * Returns the coefficient associated to the predictor variable x2.
     *
     * @return the coefficient associated to the predictor variable x2
     *
     */
    public double beta2(){
        return beta2;
    }

    /**
     * Returns the degree of freedom.
     *
     * @return the degree of freedom
     *
     */
    public double degreeFreedom(){
        return degreeFreedom;
    }

    /**
     * Returns the degree of freedom error.
     *
     * @return the degree of freedom error
     *
     */
    public double degreeFreedomErro(){
        return degreeFreedomErro;
    }

    /**
     * Returns the degree of freedom regression.
     *
     * @return the degree of freedom regression
     *
     */
    public double degreeFreedomRegressao(){
        return degreeFreedomRegressao;
    }

    /**
     * Returns the coefficient of determination R^2.
     *
     * @return the coefficient of determination R^2
     *
     */
    public double r2(){
        return r2;
    }

    /**
     * Returns the coefficient of determination R^2 adjusted.
     *
     * @return the coefficient of determination R^2 adjusted
     *
     */
    public double r2adj(){
        return r2adj;
    }

    /**
     * Returns the mean squared regression.
     *
     * @return the mean squared regression
     *
     */
    public double MQR(){
        return MQR;
    }

    /**
     * Returns the mean squared error.
     *
     * @return the mean squared error
     *
     */
    public double MQE(){
        return MQE;
    }

    /**
     * Returns the test statistic.
     *
     * @return test statistic
     *
     */
    public double F(){
        return F;
    }

    /**
     * returns the multiplication of the transposed matrix by the inverse matrix.
     *
     * @return the multiplication of the transposed matrix by the inverse matrix
     *
     */
    public double[][] xtxinv() {
        return xtxinv;
    }

    /**
     *  returns the regression coefficients.
     *
     * @return the regression coefficients
     *
     */
    public double [][] betas() {
        return betas;
    }


    /**
     * Returns the expected response y given the value of the predictor
     * variable x1 and x2.
     *
     * @param  x1 the value of the predictor variable
     * @param  x2 the value of the predictor variable
     * @return the expected response y given the value of the predictor
     *         variable x1 and x2
     */
    public double predict (double x1, double x2){
        return beta2*x2 + beta1*x1 + beta0;

    }


    /**
     * Returns the matrix resulting from the multiplication of the received matrices as a parameter.
     *
     * @param  a matrix
     * @param  b matrix
     * @return the matrix resulting from the multiplication of the received matrices as a parameter.
     */
    public double[][] multiplication(double[][] a, double[][] b) {

        if (a[0].length != b.length) throw new RuntimeException("Impossible to calculate.");

        double[][] result = new double[ a.length ][ b[0].length ];

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < a[0].length; k++)
                    result[i][j] += (a[i][k] * b[k][j]);
        return result ;
    }
}

