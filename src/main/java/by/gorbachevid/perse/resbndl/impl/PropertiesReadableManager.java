package by.gorbachevid.perse.resbndl.impl;

import by.gorbachevid.perse.resbndl.ReadableProperties;
import lombok.NonNull;

import java.util.Hashtable;
import java.util.Properties;

public abstract class PropertiesReadableManager extends ReadableProperties {

    protected Properties prop;

    public PropertiesReadableManager(@NonNull Properties prop) {
        this.prop = prop;
        initProperties();
    }

    @Override
    protected void initProperties() {
        properties = prop;
    }

    @Override
    public void setProperties(Hashtable<Object, Object> properties) {
        super.setProperties(properties);
        prop = (Properties) properties;
    }
}
