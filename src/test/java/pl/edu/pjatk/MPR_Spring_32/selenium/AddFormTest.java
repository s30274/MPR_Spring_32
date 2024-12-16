package pl.edu.pjatk.MPR_Spring_32.selenium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddFormTest {
    private WebDriver webDriver;

    @BeforeEach
    public void setUp(){
        this.webDriver = new FirefoxDriver();
//        this.webDriver = new ChromeDriver();
//        this.webDriver = new EdgeDriver();
    }

    @Test
    public void testAddForm(){
        AddFormPage addFormPage = new AddFormPage(webDriver);
        addFormPage.open()
                .fillInKunyomiInput("Kin")
                .fillInOnyomiInput("Kane");
        ViewAllPage viewAllPage = addFormPage.submitForm();

        assertTrue(viewAllPage.getData().contains("Kin Kane"));
    }


}