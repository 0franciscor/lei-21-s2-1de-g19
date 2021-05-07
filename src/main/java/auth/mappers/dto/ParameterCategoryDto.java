package auth.mappers.dto;

public class ParameterCategoryDto {

    private String name;
    private String code;

    public ParameterCategoryDto(String name, String code){
        this.name = name;
        this.code = code;
    }

    public String getName(){
        return this.name;
    }

    public String getCode(){
        return this.code;
    }

}