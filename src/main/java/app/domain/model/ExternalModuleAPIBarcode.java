package app.domain.model;

import net.sourceforge.barbecue.Barcode;

import java.util.List;

public interface ExternalModuleAPIBarcode {
    List<Barcode> generate(int nSamples, boolean a) throws Exception;
}
