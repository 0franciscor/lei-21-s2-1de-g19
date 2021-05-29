package app.domain.model;

import net.sourceforge.barbecue.Barcode;

/**
 * Represents a Sample.
 *
 * @author Rita Lello
 */
public class Sample {

    /**
     * Sample's barcode.
     */
    private Barcode barcode;

    /**
     * Builds a sample instance that receives as parameter a barcode.
     * @param barcode
     */
    public Sample(Barcode barcode){
        this.barcode=barcode;
    }

    /**
     * Builds an empty sample instance.
     */
    public Sample (){
    }

    /**
     * Returns the sample barcode.
     * @return sample barcode
     */
    public Barcode getBarcode(){
        return this.barcode;
    }


}
