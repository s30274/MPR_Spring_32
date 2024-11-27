package pl.edu.pjatk.MPR_Spring_32.exception;

public class KanjiWrongInputException extends RuntimeException{
    public KanjiWrongInputException() {
        super("Wrong or incomplete input");
    }
}
