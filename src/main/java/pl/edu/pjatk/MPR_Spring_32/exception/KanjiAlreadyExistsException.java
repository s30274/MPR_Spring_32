package pl.edu.pjatk.MPR_Spring_32.exception;

public class KanjiAlreadyExistsException extends RuntimeException{
    public KanjiAlreadyExistsException() {
        super("Kanji already exists!");
    }
}
