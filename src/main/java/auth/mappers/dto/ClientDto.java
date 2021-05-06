package auth.mappers.dto;

public class ClientDto {

    private String citizenID;
    private String nhsID;
    private String birthDate;
    private String sex;
    private String TIN;
    private String phoneNumber;
    private String email;
    private String name;

    public ClientDto (String citizenID, String nhsID, String birthDate, String sex, String TIN, String phoneNumber, String email, String name){

        this.citizenID = citizenID;
        this.nhsID = nhsID;
        this.birthDate = birthDate;
        this.sex = sex;
        this.TIN = TIN;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;

    }

    public String getCitizenID(){
        return citizenID;
    }

    public String getNhsID(){
        return nhsID;
    }

    public String getBirthDate(){
        return birthDate;
    }

    public String getSex(){
        return sex;
    }

    public String getTIN(){
        return TIN;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getEmail(){
        return email;
    }

    public String getName(){
        return name;
    }
}
