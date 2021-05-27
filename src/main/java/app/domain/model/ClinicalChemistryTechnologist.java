package app.domain.model;

public class ClinicalChemistryTechnologist extends Employee{
    /**
     * Calls the method responsible for creating an Employee object from the Employee class.
     * @param name Employee's name.
     * @param role Employee's role.
     * @param address Employee's address.
     * @param email Employee's email.
     * @param phoneNumber Employee's phone number.
     * @param socCode Employee's Standard Occupational Classification code.
     * @param doctorIndexNumb Employee's Doctor Index Number.
     *
     * @return ClinicalChemistryTechnologist which is also an Employee object.
     */
    public ClinicalChemistryTechnologist(String name, OrgRole role, String address, String email, String phoneNumber, int socCode, int doctorIndexNumb) {
        super(name, role, address, email, phoneNumber, socCode, doctorIndexNumb);
    }
}
