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
    private long identyfikator;
    private String kunyomi;
    private String onyomi;

    public Kanji() {}

    public Kanji(String kunyomi, String onyomi){
        this.kunyomi = kunyomi;
        this.onyomi = onyomi;
        this.identyfikator = genIdentyfikator(kunyomi, onyomi);
    }

    private long genIdentyfikator(String onyomi, String kunyomi){
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
    public void setIdentyfikator() {
        this.identyfikator = genIdentyfikator(this.kunyomi, this.onyomi);
    }
    public long getIdentyfikator() {
        return identyfikator;
    }
}
