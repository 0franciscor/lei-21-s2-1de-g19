package auth.domain.store;

import app.domain.model.ParameterCategory;

import java.util.ArrayList;
import java.util.List;

public class ParameterCategoryStore {

    private List<ParameterCategory> parameterCategoryList;

    public ParameterCategoryStore(){
        this.parameterCategoryList = new ArrayList<>();
    }

    public ParameterCategory createParameterCategory(String name, String code){
        return new ParameterCategory(name, code);
    }

    public boolean saveParameterCategory(ParameterCategory pc){
        return addParameterCategory(pc);
    }

    public boolean validateParameterCategory(ParameterCategory pc){
        if(pc == null)
            return false;
        return !this.parameterCategoryList.contains(pc);
    }

    public boolean addParameterCategory(ParameterCategory pc) {
        if (!validateParameterCategory(pc))
            return false;
        return this.parameterCategoryList.add(pc);
    }

    public ParameterCategory getParameterCategoryByCode(String code){
        for(ParameterCategory pc : parameterCategoryList)
            if(pc.getCode().equalsIgnoreCase(code))
                return pc;
        return new ParameterCategory ("NOT_FOUND", "NOT_FOUND");
    }

    public List<ParameterCategory> getAllParameterCategories(){
        return parameterCategoryList;
    }
}
