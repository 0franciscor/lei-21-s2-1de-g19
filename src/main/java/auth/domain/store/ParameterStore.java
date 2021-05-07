package auth.domain.store;

import app.domain.model.Parameter;
import app.domain.model.ParameterCategory;

import java.util.ArrayList;
import java.util.List;

public class ParameterStore {

    private List<Parameter> parameterStoreList;

    public ParameterStore(){
        this.parameterStoreList=new ArrayList<>();
    }

    public Parameter create(String code, String description, String designation, ParameterCategory pcat){
        Parameter parameter = new Parameter(code, description, designation, pcat);
        return parameter;
    }

    public boolean saveParameter(Parameter parameter){
        if(validate(parameter)){
            parameterStoreList.add(parameter);
            return true;
        }
        return false;
    }

    public boolean validate(Parameter parameter){
        if(parameterStoreList.contains(parameter))
            return false;
        return true;
    }

    public List<Parameter> getAllParameters(){
        return parameterStoreList;
    }


}
