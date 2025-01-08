package by.gorbachevid.perse.util;

import java.nio.file.Path;

public class NotAccessForReadToFileException extends NotAccessToFileException {
    public NotAccessForReadToFileException(Path path) {
        super(String.format("Don't access to read data into the %s", path), path);
    }
}
