package app.domain.model;


import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class MultilinearRegression {

    private double SQT,SQR,SQE,beta0,beta1,beta2,degreeFreedom,degreeFreedomErro,degreeFreedomRegressao,r2,r2adj,MQR,MQE,F;

    public static void main(String[] args) {
        double[][] x = { {  1,  4.2,  4,3},
                {  1,  6.5,6.5,5 },
                {  1,  3,3.5,4 },
                {  1,  2.1,2,3 },
                {  1, 2.9,3,4 },
                {  1, 7.2,7,3 },
                {  1, 4.8,6,4.5},
                {  1, 4.3,4,5},
                {  1, 2.6,2.5,5},
                {  1, 3.1,3,4},
                {  1, 6.2,5.5,4.5},
                {  1, 5.5,2,5},
                {  1,2.2,2.8,4},
                {  1,3,3,3}};
        double[] y = { 8.26,14.7,9.73,5.62,7.84,12.18,8.56,10.77,7.56,8.9,12.51,10.46,7.15,6.74 };
        MultilinearRegression regression = new MultilinearRegression(x, y);
    }

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
        double [][] xtxinv;
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
        double [][] betas=multiplication(xtxinv,xty);

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

    public double SQT(){
        return SQT;
    }

    public double SQE(){
        return SQE;
    }

    public double SQR(){
        return SQR;
    }

    public double beta0(){
        return beta0;
    }

    public double beta1(){
        return beta1;
    }

    public double beta2(){
        return beta2;
    }

    public double degreeFreedom(){
        return degreeFreedom;
    }

    public double degreeFreedomErro(){
        return degreeFreedomErro;
    }

    public double degreeFreedomRegressao(){
        return degreeFreedomRegressao;
    }

    public double r2(){
        return r2;
    }

    public double r2adj(){
        return r2adj;
    }

    public double MQR(){
        return MQR;
    }

    public double MQE(){
        return MQE;
    }

    public double F(){
        return F;
    }


    public static double[][] multiplication(double[][] a, double[][] b) {

        if (a[0].length != b.length) throw new RuntimeException("Impossible to calculate.");

        double[][] result = new double[ a.length ][ b[0].length ];

        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < b[0].length; j++)
                for (int k = 0; k < a[0].length; k++)
                    result[i][j] += (a[i][k] * b[k][j]);
        return result ;
    }


/*
    public MultilinearRegression(double[][] x, double[] y) {
        if (x.length != y.length) {
            throw new IllegalArgumentException("matrix dimensions don't agree");
        }


        // number of observations
        int n = y.length;

        Matrix matrixX = new Matrix(x);

        // create matrix from vector
        Matrix matrixY = new Matrix(y, n);

        // find least squares solution
        QRDecomposition qr = new QRDecomposition(matrixX);
        beta = qr.solve(matrixY);


        // mean of y[] values
        double sum = 0.0;
        for (int i = 0; i < n; i++)
            sum += y[i];
        double mean = sum / n;

        // total variation to be accounted for
        for (int i = 0; i < n; i++) {
            double dev = y[i] - mean;
            sst += dev*dev;
        }

        // variation not accounted for
        Matrix residuals = matrixX.times(beta).minus(matrixY);
        sse = residuals.norm2() * residuals.norm2();

    }


    public double beta(int j) {
        return beta.get(j, 0);
    }


    public double R2() {
        return 1.0 - sse/sst;
    }


    public static void main(String[] args) {
        double[][] x = { {  1,  10,  20 },
                {  1,  20,  40 },
                {  1,  40,  15 },
                {  1,  80, 100 },
                {  1, 160,  23 },
                {  1, 200,  18 } };
        double[] y = { 243, 483, 508, 1503, 1764, 2129 };
        MultilinearRegression regression = new MultilinearRegression(x, y);

        System.out.printf("%.2f + %.2f beta1 + %.2f beta2  (R^2 = %.2f)\n",
                regression.beta(0), regression.beta(1), regression.beta(2), regression.R2());
    }

 */
}

