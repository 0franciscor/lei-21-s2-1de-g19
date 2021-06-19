package app.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * ParameterCategory class, which is responsible for creating the ParameterCategory object.
 *
 * @author Francisco Redol (1201239)
 */
public class ParameterCategory implements Serializable {

    /**
     * The ParameterCategory name.
     */
    private String name;

    /**
     * The ParameterCategory code.
     */
    private String code;

    /**
     * The maximum name length, specified by the client.
     */
    private static final int NAME_MAX_LENGTH = 15;

    /**
     * The maximum code length, specified by the client.
     */
    private static final int CODE_LENGTH = 5;

    /**
     * Builds the ParameterCategory object.
     *
     * @param name the ParameterCategory name
     * @param code the ParameterCategory code
     */
    public ParameterCategory(String name, String code){
        setName(name);
        setCode(code);
    }

    /**
     * @param name
     * Modifies and checks the inserted parameter.
     *
     */
    public void setName(String name){
        if(StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be empty.");
        if(name.length() > NAME_MAX_LENGTH)
            throw new IllegalArgumentException("The name length is not correct.");

        this.name = name;
    }

    /**
     * @param code
     * Modifies and checks the inserted parameter.
     *
     */
    public void setCode(String code){
        if(StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be empty.");
        if(code.length() != CODE_LENGTH)
            throw new IllegalArgumentException("The code length is not correct.");

        this.code = code;
    }

    /**
     * Returns the ParameterCategory name.
     *
     * @return ParameterCategory name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the ParameterCategory code.
     *
     * @return ParameterCategory code
     */
    public String getCode(){
        return this.code;
    }

    /**
     * Returns a textual representation of the object, which contains all of its attributes.
     *
     * @return ParameterCategory characteristics
     */
    @Override
    public String toString() {
        return String.format("Parameter Category name is %s and it's code is %s", this.name, this.code);
    }

    /**
     * @param obj
     * Verifies if a certain object is equal to other.
     *
     * @return boolean result stating if the compared objects are equal
     */
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