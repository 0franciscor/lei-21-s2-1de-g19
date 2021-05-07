package app.domain.model;

import org.apache.commons.lang3.StringUtils;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> e5dc0261eaeb38494930670bc1b9881e493b982d
public class ParameterCategory {

    private String name;
    private String code;

    private static final int NAME_MAX_LENGTH = 10;
    private static final int CODE_LENGTH = 5;

<<<<<<< HEAD
    public ParameterCategory(String name, String code) {
=======
    public ParameterCategory(String name, String code){
>>>>>>> e5dc0261eaeb38494930670bc1b9881e493b982d
        setName(name);
        setCode(code);
    }

<<<<<<< HEAD
    public void setName(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be empty.");
        if (name.length() > NAME_MAX_LENGTH)
=======
    public void setName(String name){
        if(StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be empty.");
        if(name.length() > NAME_MAX_LENGTH)
>>>>>>> e5dc0261eaeb38494930670bc1b9881e493b982d
            throw new IllegalArgumentException("The name length is not correct.");
        this.name = name;
    }

<<<<<<< HEAD
    public void setCode(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be empty.");
        if (code.length() != CODE_LENGTH)
=======
    public void setCode(String code){
        if(StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be empty.");
        if(code.length() != CODE_LENGTH)
>>>>>>> e5dc0261eaeb38494930670bc1b9881e493b982d
            throw new IllegalArgumentException("The code length is not correct.");

        this.code = code;
    }

<<<<<<< HEAD
    public String getName() {
        return this.name;
    }

    public String getCode() {
=======
    public String getName(){
        return this.name;
    }

    public String getCode(){
>>>>>>> e5dc0261eaeb38494930670bc1b9881e493b982d
        return this.code;
    }

    @Override
<<<<<<< HEAD
    public String toString() {
        return String.format("The parameter category name is %s and it's code is.", this.name, this.code);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        ParameterCategory obj2 = (ParameterCategory) obj;

        return this.name.equals(obj2.name) && this.code.equals(obj2.code);
    }
}
=======
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
>>>>>>> e5dc0261eaeb38494930670bc1b9881e493b982d
