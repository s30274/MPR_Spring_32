package pl.edu.pjatk.MPR_Spring_32;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pjatk.MPR_Spring_32.model.Kanji;
import pl.edu.pjatk.MPR_Spring_32.repository.KanjiRepository;
import pl.edu.pjatk.MPR_Spring_32.service.KanjiService;
import pl.edu.pjatk.MPR_Spring_32.service.StringUtilsService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MprSpring32ApplicationTests {

	private StringUtilsService stringUtilsService = new StringUtilsService();
//	@Autowired
//	private KanjiService kanjiService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void stringUtilsTurnsAllSmallLettersWordIntoAllCapitalLettersWord() {
		String string = stringUtilsService.wholeCapital("test");

		assertEquals("TEST", string);
	}

	@Test
	public void stringUtilsTurnsAllCapitalLettersWordIntoAllCapitalLetters() {
		String string = stringUtilsService.wholeCapital("TEST");

		assertEquals("TEST", string);
	}

	@Test
	public void stringUtilsTurnsFirstCapitalRestSmallLettersWordIntoAllCapitalLetters() {
		String string = stringUtilsService.wholeCapital("test");

		assertEquals("TEST", string);
	}

	@Test
	public void stringUtilsTurnsAllCapitalLettersWordIntoFirstCapitalRestSmallLettersWord(){
		String string = stringUtilsService.firstCapital("TEST");

		assertEquals("Test", string);
	}

	@Test
	public void stringUtilsTurnsAllSmallLettersWordIntoFirstCapitalRestSmallLettersWord(){
		String string = stringUtilsService.firstCapital("test");

		assertEquals("Test", string);
	}

	@Test
	public void stringUtilsTurnsFirstSmallRestCapitalLettersWordIntoFirstCapitalRestSmallLettersWord(){
		String string = stringUtilsService.firstCapital("tEST");

		assertEquals("Test", string);
	}

//	@Test
//	public void kanjiServiceTurnsLettersToAllCapitalWhenAddingAnObject(){
//		kanjiService.add(new Kanji("Onna", "Jyo"));
//		List<Kanji> list = kanjiService.getKanjiByKunyomi("Onna");
//		assertEquals("Onna", list.getFirst().getKunyomi());
//	}

}
