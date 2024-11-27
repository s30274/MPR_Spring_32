package pl.edu.pjatk.MPR_Spring_32.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Spring_32.exception.KanjiAlreadyExistsException;
import pl.edu.pjatk.MPR_Spring_32.exception.KanjiNotFoundException;
import pl.edu.pjatk.MPR_Spring_32.exception.KanjiWrongInputException;
import pl.edu.pjatk.MPR_Spring_32.model.Kanji;
import pl.edu.pjatk.MPR_Spring_32.service.KanjiService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class MyRestController {
    private KanjiService kanjiService;

    @Autowired
    public MyRestController(KanjiService kanjiService) {
        this.kanjiService = kanjiService;
    }

    @GetMapping("kanji/all") // <- endpoint
    public ResponseEntity<List<Kanji>> getAll() {
        List<Kanji> list = this.kanjiService.getKanjiList();
        if(list.isEmpty()){
            throw new KanjiNotFoundException();
        }
        return new ResponseEntity<>(this.kanjiService.getKanjiList(), HttpStatus.OK);
    }

    @GetMapping("kanji/{id}") // <- endpoint
    public ResponseEntity<Optional<Kanji>> get(@PathVariable Long id) {
        Optional<Kanji> kanji = this.kanjiService.getKanjiById(id);
        if(kanji.isEmpty()){
            throw new KanjiNotFoundException();
        }
        return new ResponseEntity<>(kanji, HttpStatus.OK);
    }

    @GetMapping("kanji/pdf/{id}")
    public ResponseEntity<byte[]> getPdf(@PathVariable Long id) throws IOException {
        Optional<Kanji> kanji = this.kanjiService.getKanjiById(id);
        if(kanji.isEmpty()){
            throw new KanjiNotFoundException();
        }

        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        PDPageContentStream stream = new PDPageContentStream(document, page);
        File file = new File(new Random().toString());

        stream.beginText();
        stream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN), 14);
        stream.newLineAtOffset(25, 600); // ustawiam kursor
        stream.showText("Kunyomi: "+kanji.get().getKunyomi());
        stream.newLineAtOffset(0, -50);
        stream.showText("Onyomi: "+kanji.get().getKunyomi());
        stream.endText();
        stream.close();
        document.addPage(page);
        document.save(file);
        document.close();

        byte[] contents = Files.readAllBytes(file.toPath());

        file.delete();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "kanji.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.add("Content-Disposition", "inline; filename=" + "example.pdf");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        headers.setContentDispositionFormData(filename, filename);

        return response;
    }

    @GetMapping("kanji/kunyomi/{kunyomi}")
    public ResponseEntity<List<Kanji>> getByKunyomi(@PathVariable String kunyomi) {
        List<Kanji> list = this.kanjiService.getKanjiByKunyomi(kunyomi);
        if(list.isEmpty()){
            throw new KanjiNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("kanji/onyomi/{onyomi}")
    public ResponseEntity<List<Kanji>> getByOnyomi(@PathVariable String onyomi) {
        List<Kanji> list = this.kanjiService.getKanjiByOnyomi(onyomi);
        if(list.isEmpty()){
            throw new KanjiNotFoundException();
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("kanji") // <- endpoint
    public ResponseEntity<Void> addKanji(@RequestBody Kanji kanji) {
        kanji = this.kanjiService.prepareKanji(kanji);
        List<Kanji> check = this.kanjiService.getKanjiByIdentifier(kanji.getIdentifier());
        if(!check.isEmpty()){
            throw new KanjiAlreadyExistsException();
        }
        if(kanji.getKunyomi().isEmpty() || kanji.getOnyomi().isEmpty()) {
            throw new KanjiWrongInputException();
        }
        this.kanjiService.addKanji(kanji);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("kanji/{id}") // <- endpoint
    public ResponseEntity<Void> removeKanji(@PathVariable Long id){
        Optional<Kanji> kanji = this.kanjiService.getKanjiById(id);
        if(kanji.isEmpty()){
            throw new KanjiNotFoundException();
        }
        this.kanjiService.removeKanjiById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("kanji/{id}") // <- endpoint
    public ResponseEntity<Void> putKanji(@PathVariable Long id, @RequestBody Kanji kanji) throws Exception {
        Optional<Kanji> check = this.kanjiService.getKanjiById(id);
        if(check.isEmpty()){
            throw new KanjiNotFoundException();
        }
        if(kanji.getKunyomi().isEmpty() || kanji.getOnyomi().isEmpty()){
            throw new KanjiWrongInputException();
        }
        this.kanjiService.putKanji(kanji, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}