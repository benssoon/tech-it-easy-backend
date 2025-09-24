package nl.benzelinsky.techiteasybackend.exceptions;

public class RecordNotFoundException extends RuntimeException {

    //Default exception

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }
}
