package Exceptions;

public class LevelNotFound extends RuntimeException {
    
    public LevelNotFound() {
        super();
    }
    
    public LevelNotFound(String message) {
        super(message);
    }
    
}
