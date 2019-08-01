package app.components.messages.core;

import app.components.annotations.IgnoreOnCompare;
import app.components.messages.core.Delta;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public abstract class MetaObject implements Serializable {
    private UUID uuid;
    private Class className;
    private Delta delta;

    @IgnoreOnCompare
    public UUID getUuid() {
        if (uuid == null)
            this.uuid = UUID.randomUUID();
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public abstract Class getClassName();

    public void setClassName(Class className) {
        this.className = className;
    }

    public void processChanges(Object objectChanges) {
        try {
            Arrays.asList(Introspector.getBeanInfo(getClassName(), Object.class)
                    .getPropertyDescriptors())
                    .stream()
                    // filter out properties with setters only
                    .filter(pd -> Objects.nonNull(pd.getWriteMethod()))
                    .forEach(pd -> { // invoke method to get value
                        try {
                            if (pd.getReadMethod().getAnnotation(IgnoreOnCompare.class) != null) return;
                            Object value = pd.getReadMethod().invoke(objectChanges);
                            if (MetaObject.class.isAssignableFrom(pd.getReadMethod().getReturnType())) {
                                MetaObject subObject = (MetaObject) pd.getReadMethod().invoke(this);
                                if (subObject != null)
                                    subObject.processChanges(value);
                                else pd.getWriteMethod().invoke(this, value);

                                if (subObject != null && subObject.changed())
                                    this.initDelta().setChange(pd.getReadMethod().getName());
                            } else
                                pd.getWriteMethod().invoke(this, value);

                        } catch (Exception e) {
                            // add proper error handling here
                        }
                    });
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    @IgnoreOnCompare
    public Delta getDelta() {
        Delta temp = delta;
        delta = null;
        return temp;
    }

    public Delta initDelta() {
        if (delta == null)
            delta = new Delta();
        return delta;
    }

    public Boolean changed() {
        return delta != null;
    }

    public void set(String paramName, Object NewValue) {
        if (getClassName() == null) return;
        try {
            Method method = getClassName().getMethod("get" + paramName.substring(0, 1).toUpperCase() + paramName.substring(1), null);
            Object OldValue = method.invoke(this, null);
            if (!(OldValue == null && NewValue == null) && (OldValue == null || !OldValue.equals(NewValue)))
                initDelta().setChange(paramName.substring(0, 1).toUpperCase() + paramName.substring(1));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
