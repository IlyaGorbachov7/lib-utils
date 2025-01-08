package by.gorbachevid.perse.util;

import java.nio.file.Path;

public class NotAccessForWriteToFileException extends NotAccessToFileException {
    public NotAccessForWriteToFileException(Path path) {
        super(String.format("Don't access to write data into the %s", path), path);
    }
}
