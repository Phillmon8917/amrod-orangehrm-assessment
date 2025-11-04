package automation.basepage;

import automation.basepagelifecycle.BasePageLifeCycle;
import automation.strategy.DriverSingleton;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * This is a parent to child relationship, it handles some operations for the child
 * Also reduces the need for the child perform operations all operations on its own
 * Called -> IS-A Relationship
 */
public abstract class JobBasePage implements BasePageLifeCycle
{
    @FindBy(xpath = "//a[text()='Job']")
    protected WebElement jobTab;

    @FindBy(xpath = "//h6[text()='Job Details']")
    protected WebElement jobPageTitle;

    @FindBy(xpath = "//div[text()='-- Select --']")
    protected List<WebElement> selectInputs;

    @FindBy(xpath = "//input[@placeholder='yyyy-dd-mm']")
    protected WebElement joinedDateInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    protected WebElement saveButton;

    @FindBy(xpath = "//p[text()='Successfully Updated']")
    protected WebElement successfullyUpdated;

    @PostConstruct
    public void init()
    {
        PageFactory.initElements(DriverSingleton.getDriver(), this);
    }
}
