package by.gorbachevid.perse.resbndl;

import java.io.Serializable;
import java.util.Map;

public interface Writable {

    void setValue(String key, String value);

    void setValue(String key, String[] values);

    void setValue(String key, int value);

    void setValue(String key, int[] values);

    void setValue(String key, long value);

    void setValue(String key, long[] values);

    void setValue(String key, double value);

    void setValue(String key, double[] values);

    void setValue(String key, boolean value);

    void setValue(String key, boolean[] values);

    void setValue(String key, Serializable value);

    void setValue(String key, Map<String, String> value);

    long remove(String... keys);

    void clear();
}
