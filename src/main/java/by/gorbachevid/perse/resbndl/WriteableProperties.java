package by.gorbachevid.perse.resbndl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

public abstract class WriteableProperties extends ReadableProperties implements Writable {

    protected void set(String key, String value) {
        getProperties().put(key, value);
    }

    @Override
    public void setValue(String key, String value) {
        set(key, value);
    }

    @Override
    public void setValue(String key, String[] values) {
        set(key, resolverSplit.assembleToString(values));
    }

    @Override
    public void setValue(String key, int value) {
        set(key, resolverToInt.assembleToString(value));
    }

    @Override
    public void setValue(String key, int[] values) {
        set(key, resolverToArrayInt.assembleToString(values));
    }

    @Override
    public void setValue(String key, long value) {
        set(key, resolverToLong.assembleToString(value));
    }

    @Override
    public void setValue(String key, long[] values) {
        set(key, resolverToArrayLong.assembleToString(values));
    }

    @Override
    public void setValue(String key, double value) {
        set(key, resolverToDouble.assembleToString(value));
    }

    @Override
    public void setValue(String key, double[] values) {
        set(key, resolverToArrayDouble.assembleToString(values));
    }

    @Override
    public void setValue(String key, boolean value) {
        set(key, resolverToBoolean.assembleToString(value));
    }

    @Override
    public void setValue(String key, boolean[] values) {
        set(key, resolverToArrayBoolean.assembleToString(values));
    }

    @Override
    public void setValue(String key, Serializable value) {
        set(key, resolverDeserialize.assembleToString(value));
    }

    @Override
    public void setValue(String key, Map<String, String> value) {
        set(key, resolverMap.assembleToString(value));
    }

    @Override
    public long remove(String... keys) {
        return Arrays.stream(keys).filter((key)-> getProperties().containsKey(key))
                .peek(key -> getProperties().remove(key)).count();
    }

    @Override
    public void clear() {
        getProperties().clear();
    }
}
