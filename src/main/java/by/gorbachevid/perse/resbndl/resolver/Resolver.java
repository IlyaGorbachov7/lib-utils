package by.gorbachevid.perse.resbndl.resolver;

public interface Resolver<T> {

    T assemble(String value);

    String assembleToString(T object);
}
