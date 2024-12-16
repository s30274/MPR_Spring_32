package pl.edu.pjatk.MPR_Spring_32.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddFormPage {
    private WebDriver webDriver;

    @FindBy(id="kunyomi")
    private WebElement kunyomiInput;

    @FindBy(id="onyomi")
    private WebElement onyomiInput;

    @FindBy(id="submit")
    private WebElement submitButton;

    public AddFormPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public AddFormPage open() {
        this.webDriver.get("http://localhost:8080/addForm");
        return this;
    }

    public AddFormPage fillInKunyomiInput(String text){
        this.kunyomiInput.sendKeys(text);
        return this;
    }

    public AddFormPage fillInOnyomiInput(String text){
        this.onyomiInput.sendKeys(text);
        return this;
    }

    public ViewAllPage submitForm(){
        this.submitButton.click();
        return new ViewAllPage(this.webDriver);
    }
}
