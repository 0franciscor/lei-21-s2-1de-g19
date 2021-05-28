package app.domain.model;

public abstract class ExternalModule {

    public abstract ReferenceValue getReferenceValue(Parameter parameter);

    @Override
    public String toString(){
        return String.format("External module");
    }
}
