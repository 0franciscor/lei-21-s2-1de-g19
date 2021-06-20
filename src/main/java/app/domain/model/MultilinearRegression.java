package app.domain.model;


import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class MultilinearRegression {

    private double SQT, SQR,SQE,beta0,beta1,beta2,degreeFreedom,degreeFreedomErro,degreeFreedomRegressao,r2,r2adj,MQR,MQE,F;

    private double[][] xtxinv, betas;

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

    public double[][] xtxinv() {
        return xtxinv;
    }

    public double [][] betas() {
        return betas;
    }

    public double predict (double x1, double x2){
        return beta2*x2 + beta1*x1 + beta0;

    }


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

