package app.domain.model;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an External Module API Barcode
 *
 * @author Rita Lello
 */
public class ExternalModuleAPIBarcode1 implements ExternalModuleAPIBarcode{

    /**
     * External Module's number of samples.
     */
    private int nSamples;

    private int nBarcodes=1;

    /**
     * External Module's boolean.
     */
    private boolean a;

    /**
     * External Module's file name.
     */
    private String fileName;

    /**
     * External Module's output file.
     */
    private File outputFile;

    /**
     * External Module's path.
     */
    private String path;

    /**
     * External Module's barcode code.
     */
    private String barcodeCode;

    /**
     * External Module's list of generated barcodes.
     */
    private List<Barcode> barcodeGenerated;


    /**
     * Builds a External Module instance
     * @throws Exception
     */
    public ExternalModuleAPIBarcode1 () throws Exception{

        this.barcodeGenerated= generate(nSamples, a);
    }

    /**
     * Method that calls another methods to create barcodes or delete them.
     * @param nSamples
     * @param a
     * @return a list with the generated barcodes
     * @throws Exception
     */
    public List<Barcode> generate(int nSamples, boolean a) throws Exception {
        List<Barcode> barcodes = new ArrayList<>();
        for(int i=0; i<nSamples; i++){
            this.fileName=generateFileName(nBarcodes);
            this.outputFile=new File(fileName);
            this.path=outputFile.getAbsolutePath();
            this.barcodeCode=String.format("%011d",nBarcodes);
            if(a){
                Barcode barcode = BarcodeFactory.createUPCA(barcodeCode);
                barcode.setPreferredBarHeight(150);
                BufferedImage barcodeImage = BarcodeImageHandler.getImage(barcode);
                writeBarcode(barcodeImage);
                barcodes.add(barcode);
                nBarcodes++;
            }else{
                if(i==0){
                    nBarcodes--;
                    this.fileName=generateFileName(nBarcodes);
                    this.outputFile=new File(fileName);
                }
                deleteBarcode();
                nBarcodes--;
            }
        }
        return barcodes;
    }


    /**
     * Generates the file name
     * @param totalBarcodes
     * @return name of the file
     */
    public static String generateFileName(int totalBarcodes){
        return String.format("barcode%03d.jpg",totalBarcodes);
    }

    /**
     * Writes in the console that the barcodes are being created
     * @param image
     */
    public void writeBarcode(BufferedImage image){
        System.out.println("...creating image file...");
        try {
            ImageIO.write(image, "jpg", this.outputFile);
        }catch(Exception e){
            System.out.println("There was a mistake: "+e.getMessage());
        }
        System.out.println("Image was created successfully");
    }

    /**
     * Deletes the barcode and informs the operation success
     */
    public void deleteBarcode(){
        if(this.outputFile.delete())
            System.out.println(String.format("File %s was deleted",this.fileName));
        else
            System.out.println("Occurred an error while deleting the file "+this.fileName);
    }
    public String getBarcodeCode() {
        return barcodeCode;
    }
}
