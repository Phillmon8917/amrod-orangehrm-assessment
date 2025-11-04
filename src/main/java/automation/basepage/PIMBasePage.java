package automation.basepage;

import automation.basepagelifecycle.BasePageLifeCycle;
import automation.strategy.DriverSingleton;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * This is a parent to child relationship, it handles some operations for the child
 * Also reduces the need for the child perform operations all operations on its own
 * Called -> IS-A Relationship
 */
public abstract class PIMBasePage implements BasePageLifeCycle
{
    @FindBy(xpath = "//span[text()='PIM']")
    protected WebElement pimNav;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    protected WebElement addEmployeeButton;

    @FindBy(name = "firstName")
    protected WebElement firstnameInput;

    @FindBy(name = "middleName")
    protected WebElement middleNameInput;

    @FindBy(name = "lastName")
    protected WebElement lastnameInput;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    protected WebElement saveButton;

    @FindBy(xpath = "//p[text()='Successfully Saved']")
    protected WebElement successfullySavedPopUp;

    @FindBy(xpath = "//div[text()='-- Select --']")
    protected List<WebElement> selectsDropdownInput;

    @FindBy(xpath = "//label[normalize-space()='Male']")
    protected WebElement genderRadioMale;

    @FindBy(xpath = "//label[normalize-space()='Female']")
    protected WebElement femaleRadio;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    protected List<WebElement> personalDetailsSaveButtons;

    @FindBy(xpath = "//input[@placeholder='yyyy-dd-mm']")
    protected List<WebElement> dateInputs;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    protected WebElement addAttachmentButton;

    @FindBy(xpath = "//input[@type='file']")
    protected WebElement inputFile;

    @FindBy(xpath = "//div[contains(@class,'oxd-table-cell')]//div[starts-with(text(), '')]")
    protected List<WebElement> fileAttachmentTableRows;

    @FindBy(xpath = "//span[text()='File type not allowed']")
    protected WebElement fileTypeNotAllowedValidationError;

    @FindBy(xpath = "(//input[contains(@class,'oxd-input')])[5]")
    protected WebElement employeeId;

    protected WebDriver driver;

    @PostConstruct
    public void init()
    {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
}
