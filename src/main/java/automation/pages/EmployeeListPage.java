package automation.pages;

import automation.basepage.EmployeeListBasePage;
import automation.dto.EmployeeDto;
import automation.utils.WaitUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeListPage extends EmployeeListBasePage
{
    public void goToEmployeeListPage()
    {
        try
        {
            WaitUtil.waitUntilVisible(employeeList);
            employeeList.click();
        } catch (Exception ex)
        {
            log.error("An error has occurred", ex);
        }
    }

    public boolean employMatchesTheOneICreated(EmployeeDto employeeDto)
    {
        try
        {
            By rowLocator = By.xpath("//div[@role='row'][.//div[contains(normalize-space(), '" + employeeDto.getId() + "')]]");

            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            WebElement row = wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(rowLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", row);
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf(row));

            String rowText = row.getText();

            return rowText.contains(employeeDto.getId())
                    && rowText.contains(employeeDto.getFirstname())
                    && rowText.contains(employeeDto.getLastname())
                    && rowText.contains(employeeDto.getJobTitle())
                    && rowText.contains(employeeDto.getEmploymentStatus())
                    && rowText.contains(employeeDto.getSubUnit());
        }
        catch (Exception ex)
        {
            log.error("Error while verifying employee row for ID: " + employeeDto.getId(), ex);
            return false;
        }
    }

    private WebElement getRowById(String id)
    {
        String xpath = "//div[@role='row'][.//div[text()='" + id + "']]";
        return driver.findElement(By.xpath(xpath));
    }

    private EmployeeListPage()
    {
    }
}
