package app.domain.model;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExternalModuleAPIBarcode1 implements ExternalModuleAPIBarcode{
    private int nSamples;
    private boolean a;
    private String fileName;
    private File outputFile;
    private String path;
    private String barcodeCode;
    private static int totalBarcodes=0;
    private List<Barcode> barcodeGenerated;


    public ExternalModuleAPIBarcode1 () throws Exception{

        this.barcodeGenerated= generate(nSamples, a);
    }

    public List<Barcode> generate(int nSamples, boolean a) throws Exception {
        List<Barcode> barcodes = new ArrayList<>();
        for(int i=0; i<nSamples; i++){
            this.fileName=generateFileName(i);
            this.outputFile=new File("out\\"+fileName);
            this.path=outputFile.getAbsolutePath();
            this.barcodeCode=generateCode();
            if(a){
                Barcode barcode = BarcodeFactory.createUPCA(barcodeCode);
                barcode.setPreferredBarHeight(150);
                BufferedImage barcodeImage = BarcodeImageHandler.getImage(barcode);
                writeBarcode(barcodeImage);
                barcodes.add(barcode);
            }else
                deleteBarcode();
        }
        return barcodes;
    }

    public String generateCode(){

        Random random = new Random();

        String str = "0123456789";
        String strAux = "";

        for (int i=0; i<11; i++){

            int x = 1 + random.nextInt(9);
            char aux = str.charAt(x);
            strAux += aux;
        }
        return strAux;
    }

    public static String generateFileName(int totalBarcodes){
        return String.format("barcode%03d.jpg",totalBarcodes);
    }

    public void writeBarcode(BufferedImage image){
        System.out.println("...writing image file...");
        try {
            ImageIO.write(image, "jpg", this.outputFile);
        }catch(Exception e){
            System.out.println("There was a mistake: "+e.getMessage());
        }
        System.out.println("Image was written successfully");
    }

    public void deleteBarcode(){
        if(this.outputFile.delete())
            System.out.println(String.format("File %s was deleted",this.fileName));
        else
            System.out.println("Occurred an error while deleting the file "+this.fileName);
        totalBarcodes--;
    }

}
