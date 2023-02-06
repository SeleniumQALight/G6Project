package pages;


import org.openqa.selenium.WebDriver;



//тут все наслідується від CommonActionsWithElement
//все що пишется в класі CommonActionsWithElement передається сюди, а потім наслідується в інші класи
public class ParentPage extends CommonActionsWithElement{
    public ParentPage(WebDriver webDriver) {
        super(webDriver);
    }
}
