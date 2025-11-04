package automation.pages;

import automation.basepage.JobBasePage;
import automation.strategy.DriverSingleton;
import automation.utils.WaitUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class JobPage extends JobBasePage
{
    public void clickJobTab()
    {
        try
        {
            WaitUtil.waitUntilClickable(jobTab);
            try
            {
                jobTab.click();
            } catch (Exception e)
            {
                WebDriver driver = DriverSingleton.getDriver();
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", jobTab);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", jobTab);
            }
        } catch (Exception ex)
        {
            log.error("An exception has occurred in clickJobTab()", ex);
        }
    }


    public boolean isUserOnJobPageTab()
    {
        try
        {
            WaitUtil.waitUntilVisible(jobPageTitle);
            return !jobPageTitle.getText().isBlank();
        } catch (Exception ex)
        {
            log.error("An exception has occurred", ex);
            return false;
        }
    }

    public void selectJobTitle(String jobTitle)
    {
        WebDriver driver = DriverSingleton.getDriver();

        By listboxLocator = By.xpath("//div[@role='listbox']");
        By optionsLocator = By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-select-option')]");

        try
        {
            WebElement dropdown = selectInputs.getFirst();

            WaitUtil.waitUntilClickable(dropdown);
            dropdown.click();

            WaitUtil.waitUntilVisible(driver.findElement(listboxLocator));
            WaitUtil.waitUntilVisible(driver.findElement(optionsLocator));

            List<WebElement> options = driver.findElements(optionsLocator);
            boolean selected = false;

            for (WebElement option : options)
            {
                if (option.getText().trim().equalsIgnoreCase(jobTitle))
                {
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block:'center'});", option);
                    WaitUtil.waitUntilClickable(option);
                    option.click();
                    selected = true;
                    break;
                }
            }

            if (!selected)
            {
                throw new NoSuchElementException("Job title '" + jobTitle + "' not found");
            }

        } catch (StaleElementReferenceException e)
        {
            selectJobTitle(jobTitle);
        } catch (Exception ex)
        {
            throw new RuntimeException("Error selecting job title '" + jobTitle + "'", ex);
        }
    }


    public void selectJoinedDate(String date)
    {
        if (!date.matches("^\\d{4}-\\d{2}-\\d{2}$"))
        {
            throw new IllegalArgumentException("Invalid date format: " + date + ". Expected format is yyyy-dd-mm");
        }

        try
        {
            WaitUtil.waitUntilVisible(joinedDateInput);
            joinedDateInput.sendKeys(date);
        } catch (Exception ex)
        {
            log.error("An exception has occurred", ex);
        }
    }

    public void selectJobCategory(String category)
    {
        WebDriver driver = DriverSingleton.getDriver();

        By listboxLocator = By.xpath("//div[@role='listbox']");
        By optionsLocator = By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-select-option')]");

        try
        {
            WebElement dropdown = selectInputs.getFirst();
            WaitUtil.waitUntilClickable(dropdown);
            dropdown.click();

            WaitUtil.waitUntilVisible(driver.findElement(listboxLocator));
            WaitUtil.waitUntilVisible(driver.findElement(optionsLocator));

            List<WebElement> options = driver.findElements(optionsLocator);
            boolean selected = false;

            for (WebElement option : options)
            {
                if (option.getText().trim().equalsIgnoreCase(category))
                {
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block:'center'});", option);
                    WaitUtil.waitUntilClickable(option);
                    option.click();
                    selected = true;
                    break;
                }
            }

            if (!selected)
            {
                throw new NoSuchElementException("Job category '" + category + "' not found");
            }

        } catch (StaleElementReferenceException e)
        {
            selectJobCategory(category);
        } catch (Exception ex)
        {
            throw new RuntimeException("Error selecting job category '" + category + "'", ex);
        }
    }

    public void selectSubUnit(String subUnit)
    {
        WebDriver driver = DriverSingleton.getDriver();

        By listboxLocator = By.xpath("//div[@role='listbox']");
        By optionsLocator = By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-select-option')]");

        try
        {
            WebElement dropdown = selectInputs.getFirst();
            WaitUtil.waitUntilClickable(dropdown);
            dropdown.click();

            WaitUtil.waitUntilVisible(driver.findElement(listboxLocator));
            WaitUtil.waitUntilVisible(driver.findElement(optionsLocator));

            List<WebElement> options = driver.findElements(optionsLocator);
            boolean selected = false;

            for (WebElement option : options)
            {
                if (option.getText().trim().equalsIgnoreCase(subUnit))
                {
                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView({block:'center'});", option);
                    WaitUtil.waitUntilClickable(option);
                    option.click();
                    selected = true;
                    break;
                }
            }

            if (!selected)
            {
                throw new NoSuchElementException("Sub Unit '" + subUnit + "' not found");
            }

        } catch (StaleElementReferenceException e)
        {
            selectSubUnit(subUnit);
        } catch (Exception ex)
        {
            throw new RuntimeException("Error selecting sub unit '" + subUnit + "'", ex);
        }
    }

    public void selectEmploymentStatus(String status)
    {
        WebDriver driver = DriverSingleton.getDriver();

        By listboxLocator = By.xpath("//div[@role='listbox']");
        By optionsLocator = By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-select-option')]");

        try
        {
            WebElement dropdown = selectInputs.getLast();
            WaitUtil.waitUntilClickable(dropdown);
            dropdown.click();

            WaitUtil.waitUntilVisible(driver.findElement(listboxLocator));
            WaitUtil.waitUntilVisible(driver.findElement(optionsLocator));

            List<WebElement> options = driver.findElements(optionsLocator);
            boolean selected = false;

            for (WebElement option : options)
            {
                if (option.getText().trim().equalsIgnoreCase(status))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", option);
                    WaitUtil.waitUntilClickable(option);
                    option.click();
                    selected = true;
                    break;
                }
            }

            if (!selected)
            {
                throw new NoSuchElementException("Employment status '" + status + "' not found");
            }

        } catch (StaleElementReferenceException e)
        {
            selectEmploymentStatus(status);
        } catch (Exception ex)
        {
            throw new RuntimeException("Error selecting employment status '" + status + "'", ex);
        }
    }

    public void clickSaveButton()
    {
        try
        {
            WaitUtil.waitUntilVisible(saveButton);
            saveButton.click();
        } catch (Exception ex)
        {
            log.error("An exception has occurred", ex);
        }
    }

    public void selectLocation()
    {
        WebDriver driver = DriverSingleton.getDriver();

        By listboxLocator = By.xpath("//div[@role='listbox']");
        By optionsLocator = By.xpath("//div[@role='listbox']//div[contains(@class,'oxd-select-option')]");

        try
        {
            WebElement dropdown = selectInputs.getFirst();
            WaitUtil.waitUntilClickable(dropdown);
            dropdown.click();

            WaitUtil.waitUntilVisible(driver.findElement(listboxLocator));
            WaitUtil.waitUntilVisible(driver.findElement(optionsLocator));

            List<WebElement> options = driver.findElements(optionsLocator);
            List<WebElement> validOptions = options.stream()
                    .filter(opt -> !opt.getText().trim().isEmpty() && !opt.getText().trim().equalsIgnoreCase("-- Select --"))
                    .toList();

            if (validOptions.isEmpty())
            {
                throw new NoSuchElementException("No valid locations found in dropdown");
            }

            WebElement chosenOption = validOptions.get(new Random().nextInt(validOptions.size()));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", chosenOption);
            WaitUtil.waitUntilClickable(chosenOption);
            chosenOption.click();

        } catch (StaleElementReferenceException e)
        {
            selectLocation();
        } catch (Exception ex)
        {
            throw new RuntimeException("Error selecting random location", ex);
        }
    }

    public boolean detailsSavedSuccessfully()
    {
        try
        {
            WaitUtil.waitUntilVisible(successfullyUpdated);

            Actions actions = new Actions(DriverSingleton.getDriver());
            actions.scrollToElement(successfullyUpdated);
            return successfullyUpdated.isDisplayed();
        } catch (Exception ex)
        {
            log.error("An exception has occurred", ex);
            return false;
        }
    }

    private JobPage()
    {

    }
}
