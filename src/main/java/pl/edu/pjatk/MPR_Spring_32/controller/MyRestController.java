package pl.edu.pjatk.MPR_Spring_32.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Spring_32.model.Kanji;
import pl.edu.pjatk.MPR_Spring_32.service.KanjiService;

import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {
    private KanjiService kanjiService;

    @Autowired
    public MyRestController(KanjiService kanjiService) {
        this.kanjiService = kanjiService;
    }

    @GetMapping("kanji/name/{name}")
    public List<Kanji> getBy(@PathVariable String kunyomi){
        return this.kanjiService.getKanjiByKunyomi(kunyomi);
    }

    @GetMapping("kanji/origin/{origin}")
    public List<Kanji> getByOrigin(@PathVariable String onyomi){
        return this.kanjiService.getKanjiByOnyomi(onyomi);
    }

    @GetMapping("kanji/all") // <- endpoint
    public List<Kanji> getAll() {
        return this.kanjiService.getKanjiList();
    }

    @GetMapping("kanji/{id}") // <- endpoint
    public Optional<Kanji> get(@PathVariable Long id) {
        return this.kanjiService.getKanjiById(id);
    }

    @PostMapping("kanji") // <- endpoint
    public void addKanji(@RequestBody Kanji kanji) {
        kanji.setIdentyfikator();
        this.kanjiService.add(kanji);
    }

    @DeleteMapping("kanji/{id}") // <- endpoint
    public void removeKanji(@PathVariable Long id){
        this.kanjiService.removeKanjiById(id);
    }

    @PutMapping("kanji/{id}") // <- endpoint
    public void putKanji(@PathVariable Long id, @RequestBody Kanji kanji) throws Exception {
        Kanji update = this.kanjiService.getKanjiById(id)
                .orElseThrow(() -> new Exception("Kanji with id: " + id + " does not exist"));
        this.kanjiService.putKanji(kanji, id);
    }
}