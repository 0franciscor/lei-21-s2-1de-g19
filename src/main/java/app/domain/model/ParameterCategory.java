package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ParameterCategory {

    private String name;
    private String code;

    private static final int NAME_MAX_LENGTH = 10;
    private static final int CODE_LENGTH = 5;

    public ParameterCategory(String name, String code){
        setName(name);
        setCode(code);
    }

    public void setName(String name){
        if(StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be empty.");
        if(name.length() > NAME_MAX_LENGTH)
            throw new IllegalArgumentException("The name length is not correct.");
        this.name = name;
    }

    public void setCode(String code){
        if(StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be empty.");
        if(code.length() != CODE_LENGTH)
            throw new IllegalArgumentException("The code length is not correct.");

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
        return String.format("The parameter category name is %s and it's code is.", this.name, this.code);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;

        if(obj == null)
            return false;

        ParameterCategory obj2 = (ParameterCategory) obj;

        return this.name.equals(obj2.name) && this.code.equals(obj2.code);
    }


}
