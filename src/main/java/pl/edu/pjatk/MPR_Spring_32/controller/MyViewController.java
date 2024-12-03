package pl.edu.pjatk.MPR_Spring_32.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.edu.pjatk.MPR_Spring_32.exception.KanjiNotFoundException;
import pl.edu.pjatk.MPR_Spring_32.model.Kanji;
import pl.edu.pjatk.MPR_Spring_32.service.KanjiService;

import java.util.List;

@Controller
public class MyViewController {

    private KanjiService kanjiService;

    @Autowired
    public MyViewController(KanjiService kanjiService) {
        this.kanjiService = kanjiService;
    }

    @GetMapping("view/all")
    public String viewAllKanji(Model model){
        List<Kanji> kanjiList = this.kanjiService.getKanjiList();
        if(kanjiList.isEmpty()){
            throw new KanjiNotFoundException();
        }
//        return new ResponseEntity<>(this.kanjiService.getKanjiList(), HttpStatus.OK);
        model.addAttribute("kanjiList", kanjiList);
        return "viewAll";
    }

    @GetMapping("addForm")
    public String displayAddForm(Model model){
        model.addAttribute("kanji", new Kanji());
        return "addForm";
    }

    @PostMapping("addForm")
    public String displayAddForm(@ModelAttribute Kanji kanji){
        this.kanjiService.addKanji(kanji);
        return "redirect:/view/all";
    }
}
