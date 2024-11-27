package pl.edu.pjatk.MPR_Spring_32.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Kanji {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long identifier;
    private String kunyomi;
    private String onyomi;

    public Kanji() {}

    public Kanji(String kunyomi, String onyomi){
        this.kunyomi = kunyomi;
        this.onyomi = onyomi;
        this.identifier = genIdentifier(kunyomi, onyomi);
    }

    private long genIdentifier(String onyomi, String kunyomi){
        char[] charKunyomi = kunyomi.toCharArray();
        char[] charOnyomi = onyomi.toCharArray();
        long sum = 0;
        for(char c : charKunyomi){
            sum += c;
        }
        for(char c : charKunyomi){
            sum += c;
        }
        return sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKunyomi(String kunyomi) {
        this.kunyomi = kunyomi;
    }
    public String getKunyomi() {
        return kunyomi;
    }
    public void setOnyomi(String onyomi) {
        this.onyomi = onyomi;
    }
    public String getOnyomi() {
        return onyomi;
    }
    public void setIdentifier() {
        this.identifier = genIdentifier(this.kunyomi, this.onyomi);
    }
    public long getIdentifier() {
        return identifier;
    }
}
