package app.domain.model;

public abstract class ExternalModule {

    public abstract ReferenceValue getReferenceValue(TestParameter testParameter);

    @Override
    public String toString(){
        return String.format("External module");
    }

}
