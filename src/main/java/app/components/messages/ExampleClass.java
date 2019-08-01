package app.components.messages;

import app.components.annotations.IgnoreOnCompare;
import app.components.messages.core.MetaObject;

public class ExampleClass extends MetaObject {

    @Override
    @IgnoreOnCompare
    public Class getClassName() {
        return ExampleClass.class;
    }

    private String prueba;
    private String prueba2;
    private ExampleClass2 class2;

    public ExampleClass2 getClass2() {
        return class2;
    }

    public void setClass2(ExampleClass2 class2) {
        set("class2",class2);
        this.class2 = class2;
    }

    public String getPrueba() {
        return prueba;
    }

    public void setPrueba(String prueba) {
        set("prueba",prueba);
        this.prueba = prueba;
    }

    public String getPrueba2() {
        return prueba2;
    }

    public void setPrueba2(String prueba2) {
        set("prueba2",prueba2);
        this.prueba2 = prueba2;
    }
}
