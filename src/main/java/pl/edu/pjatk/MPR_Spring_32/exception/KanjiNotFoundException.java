package pl.edu.pjatk.MPR_Spring_32.exception;

public class KanjiNotFoundException extends RuntimeException{
    public KanjiNotFoundException() {
        super("Kanji not found!");
    }
}
