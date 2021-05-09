package auth.mappers.dto;

/**
 * ClinicalAnalysisLaboratoryDto class, which is responsible for converting a
 * ClinicalAnalysisLaboratory List in a ClinicalAnalysisLaboratoryDto List.
 *
 * @author Nuno Pinho (1190918)
 */
public class ClinicalAnalysisLaboratoryDto {

    /**
     * The name
     */
    private String name;

    /**
     * The address
     */
    private String address;

    /**
     * The phone number
     */
    private String phoneNumber;

    /**
     * The TIN
     */
    private String TIN;

    /**
     * The lab ID
     */
    private String labID;

    /**
     * Constructs the ClinicalAnalysisLaboratory object.
     *
     * @param name the ParameterCategoryDto name
     * @param address
     * @param phoneNumber
     * @param TIN
     * @param labID
     */
    public ClinicalAnalysisLaboratoryDto(String name, String address, String phoneNumber, String TIN, String labID) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.TIN = TIN;
        this.labID = labID;
    }

    /**
     * Gives the lab ID
     *
     * @return labID
     */
    public String getLabID() {
        return this.labID;
    }

    /**
     * Returns a textual representation of the object, which contains all of its
     * attributes.
     *
     * @return ClinicalAnalysisLaboratoryDto characteristics
     */
    @Override
    public String toString() {
        return String.format("The laboratory's id is %s.", this.labID);
    }
}
