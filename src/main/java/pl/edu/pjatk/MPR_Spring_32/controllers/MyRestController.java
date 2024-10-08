package pl.edu.pjatk.MPR_Spring_32.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjatk.MPR_Spring_32.model.Jajco;
import pl.edu.pjatk.MPR_Spring_32.services.JajcoService;

import java.util.List;

@RestController
public class MyRestController {
    private JajcoService jajcoService;

    @Autowired
    public MyRestController(JajcoService jajcoService) {
        this.jajcoService = jajcoService;
    }

    @GetMapping("jajco/all") // <- endpoint
    public List<Jajco> getAll() {
        return this.jajcoService.getJajcoList();
    }

    @GetMapping("jajco/{id}") // <- endpoint
    public Jajco get(@PathVariable Integer id) {
        return this.jajcoService.getJajco(id);
    }

    @PostMapping("jajco") // <- endpoint
    public void addJajco(@RequestBody Jajco jajco) {
        this.jajcoService.add(jajco);
    }

    @DeleteMapping("jajco/remove/{id}") // <- endpoint
    public void removeJajco(@PathVariable Integer id){
        this.jajcoService.remove(id);
    }
}