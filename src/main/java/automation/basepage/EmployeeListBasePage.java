package automation.basepage;

import automation.basepagelifecycle.BasePageLifeCycle;
import automation.strategy.DriverSingleton;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This is a parent to child relationship, it handles some operations for the child
 * Also reduces the need for the child perform operations all operations on its own
 * Called -> IS-A Relationship
 */
public abstract class EmployeeListBasePage implements BasePageLifeCycle
{

    @FindBy(xpath = "//a[text()='Employee List']")
    protected WebElement employeeList;

    protected WebDriver driver;

    @PostConstruct
    public void init()
    {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
}
