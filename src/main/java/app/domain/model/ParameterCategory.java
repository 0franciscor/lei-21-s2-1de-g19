package app.domain.model;

public class ParameterCategory {

    private String code;
    private String description;
    private String nhsID;

    public ParameterCategory(String code, String description, String nhsID){
        this.code = code;
        this.description = description;
        this.nhsID = nhsID;

    }
}
