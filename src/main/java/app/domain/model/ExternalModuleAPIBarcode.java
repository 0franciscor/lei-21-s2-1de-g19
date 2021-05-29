package app.domain.model;

import net.sourceforge.barbecue.Barcode;

import java.util.List;

/**
 * Represents the External Module interface
 *
 * @author Rita Lello
 */
public interface ExternalModuleAPIBarcode {
    List<Barcode> generate(int nSamples, boolean a) throws Exception;
}
