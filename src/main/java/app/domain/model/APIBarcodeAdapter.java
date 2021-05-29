package app.domain.model;

import net.sourceforge.barbecue.Barcode;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class APIBarcodeAdapter {

    private ExternalModuleAPIBarcode barcode;

    public APIBarcodeAdapter() throws Exception{
        Properties props = new Properties();
        InputStream in = new FileInputStream("config.properties");
        props.load(in);
        in.close();
        String bClass = props.getProperty("barcodeApi");
        Class<?> oClass=Class.forName(bClass);
        barcode =(ExternalModuleAPIBarcode) oClass.getConstructor().newInstance();
    }

    public List<Barcode> generateBarcodes(int nSamples, boolean a) throws Exception {
        return	this.barcode.generate(nSamples, a);
    }

}
