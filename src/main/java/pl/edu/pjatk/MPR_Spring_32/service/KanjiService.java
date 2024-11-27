package pl.edu.pjatk.MPR_Spring_32.service;

import org.springframework.stereotype.Service;
import pl.edu.pjatk.MPR_Spring_32.model.Kanji;
import pl.edu.pjatk.MPR_Spring_32.repository.KanjiRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KanjiService {
    private List<Kanji> kanjiList = new ArrayList<>();
    private KanjiRepository repository;
    private StringUtilsService stringUtilsService;

    public KanjiService(KanjiRepository repository, StringUtilsService stringUtilsService){
        this.repository = repository;
        this.stringUtilsService = stringUtilsService;
        this.repository.save(new Kanji("HI", "NICHI"));
        this.repository.save(new Kanji("TSUKI", "GETSU"));
        this.repository.save(new Kanji("KAWA", "SEN"));
    }

    private Optional<Kanji> optionalSetFirstCapital(Optional<Kanji> kanji){
        kanji.get().setKunyomi(this.stringUtilsService.firstCapital(kanji.get().getKunyomi()));
        kanji.get().setOnyomi(this.stringUtilsService.firstCapital(kanji.get().getOnyomi()));
        return kanji;
    }
    private List<Kanji> setFirstCapitalList(List<Kanji> kanjiList){
        for(Kanji kanji : kanjiList) {
            kanji.setKunyomi(this.stringUtilsService.firstCapital(kanji.getKunyomi()));
            kanji.setOnyomi(this.stringUtilsService.firstCapital(kanji.getOnyomi()));
        }
        return kanjiList;
    }
    private Kanji setWholeCapital(Kanji kanji){
        kanji.setKunyomi(this.stringUtilsService.wholeCapital(kanji.getKunyomi()));
        kanji.setOnyomi(this.stringUtilsService.wholeCapital(kanji.getOnyomi()));
        return kanji;
    }

    public List<Kanji> getKanjiByKunyomi(String kunyomi) {
        return setFirstCapitalList(this.repository.findByKunyomi(stringUtilsService.wholeCapital(kunyomi)));
    }

    public List<Kanji> getKanjiByOnyomi(String onyomi) {
        return setFirstCapitalList(this.repository.findByOnyomi(stringUtilsService.wholeCapital(onyomi)));
    }

    public List<Kanji> getKanjiByIdentifier(Long identifier) {
        return setFirstCapitalList(this.repository.findByIdentifier(identifier));
    }

    public List<Kanji> getKanjiList() {
        return setFirstCapitalList((List<Kanji>) this.repository.findAll());
    }

    public void addKanji(Kanji kanji) {
        kanji = prepareKanji(kanji);
        this.repository.save(kanji);
    }

    public Kanji prepareKanji(Kanji kanji){
        setWholeCapital(kanji);
        kanji.setIdentifier();
        return kanji;
    }

    public Optional<Kanji> getKanjiById(Long id) {
        return optionalSetFirstCapital(this.repository.findById(id));
    }

    public void removeKanjiById(Long id) {
        this.repository.deleteById(id);
    }

    public void putKanji(Kanji kanji, Long id){
        this.repository.deleteById(id);
        kanji = prepareKanji(kanji);
        this.repository.save(kanji);
    }
}
