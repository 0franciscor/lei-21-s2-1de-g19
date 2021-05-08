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

    @Override
    public String toString(){
        return String.format("The parameter category name is %s and it's code is %s.", this.name, this.code);
    }
}