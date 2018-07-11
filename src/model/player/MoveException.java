package model.player;

public class MoveException extends RuntimeException {
    public MoveException() {
        super();
    }

    public MoveException(String message) {
        super(message);
    }
}
