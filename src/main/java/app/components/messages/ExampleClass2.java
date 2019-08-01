package app.components.messages;

import app.components.annotations.IgnoreOnCompare;
import app.components.messages.core.MetaObject;

import java.io.Serializable;
import java.util.Objects;

public class ExampleClass2 extends MetaObject {

    private String p;

    public ExampleClass2() {
        getUuid();
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        set("p", p);
        this.p = p;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExampleClass2)) return false;
        ExampleClass2 that = (ExampleClass2) o;
        return Objects.equals(p, that.p);
    }

    @Override
    @IgnoreOnCompare
    public Class getClassName() {
        return ExampleClass2.class;
    }
}
