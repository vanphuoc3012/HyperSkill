package account.exception;

public class PasswordFailCheckException extends RuntimeException{
    public PasswordFailCheckException(String msg) {
        super(msg);
    }
}
