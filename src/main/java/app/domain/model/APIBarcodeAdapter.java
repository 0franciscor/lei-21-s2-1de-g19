package app.domain.model;

import net.sourceforge.barbecue.Barcode;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * Represents an API Barcode Adapter
 */
public class APIBarcodeAdapter {

    /**
     * Adapter's External Module.
     */
    private ExternalModuleAPIBarcode barcode;

    /**
     * Builds an instance of a API Barcode Adapter
     * @throws Exception
     */
    public APIBarcodeAdapter() throws Exception{
        Properties props = new Properties();
        InputStream in = new FileInputStream("config.properties");
        props.load(in);
        in.close();
        String bClass = props.getProperty("barcodeApi");
        Class<?> oClass=Class.forName(bClass);
        barcode =(ExternalModuleAPIBarcode) oClass.getConstructor().newInstance();
    }

    /**
     * Calls the method in the ExternalModule to create or delete as many barcodes as the integer passed as parameter
     * @param nSamples
     * @param a
     * @return a list with the generated barcodes
     * @throws Exception
     */
    public List<Barcode> generateBarcodes(int nSamples, boolean a) throws Exception {
        return	this.barcode.generate(nSamples, a);
    }

}
