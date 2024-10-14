package pl.edu.pjatk.MPR_Spring_32.services;

import org.springframework.stereotype.Component;
import pl.edu.pjatk.MPR_Spring_32.model.Jajco;

import java.util.ArrayList;
import java.util.List;

@Component
public class JajcoService {
    private List<Jajco> jajcoList = new ArrayList<>();

    public JajcoService(){
        this.jajcoList.add(new Jajco("Jajco1", "Kurnik"));
        this.jajcoList.add(new Jajco("Jajco2", "Trawnik"));
        this.jajcoList.add(new Jajco("Jajco3", "Sklep"));
    }

    public List<Jajco> getJajcoList() {
        return this.jajcoList;
    }

    public void add(Jajco jajco) {
        this.jajcoList.add(jajco);
    }

    public Jajco getJajco(Integer id) {
        return this.jajcoList.get(id);
    }
}
