package pl.edu.pjatk.MPR_Spring_32.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pjatk.MPR_Spring_32.model.Kanji;

import java.util.List;

@Repository
public interface KanjiRepository extends CrudRepository<Kanji, Long>{
    public List<Kanji> findByKunyomi(String kunyomi);
    public List<Kanji> findByOnyomi(String onyomi);
}
