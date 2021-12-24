package CustomExceptions;

public class InvalidCredentialsInputException extends Exception{

    public InvalidCredentialsInputException(String message){
        super(message);
    }
}
