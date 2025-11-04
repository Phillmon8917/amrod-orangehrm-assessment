package automation.stepDefinition;

import automation.dto.EmployeeDto;
import automation.flow.Command;
import automation.flow.CommandInvoker;
import automation.pages.EmployeeListPage;
import automation.pages.JobPage;
import automation.pages.LoginPage;
import automation.pages.PIMPage;
import automation.properties.ConfigPropInstance;
import automation.strategy.DriverSingleton;
import automation.utils.ConfigUtil;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

/**
 * This class follows cucumber approach, it serves as a service for performing operations
 * based on behaviour.
 * It's reusable, it can perform many tests, or operations with the same code.
 * We just rearrange the logic and it will work.
 */
@Service
public class StepDefinitions
{
    @Autowired
    private Command command;

    @Autowired
    private LoginPage loginPage;

    @Autowired
    private PIMPage pimPage;

    @Autowired
    private JobPage jobPage;

    @Autowired
    private EmployeeListPage employeeListPage;

    private WebDriver driver;

    @PostConstruct
    private void init()
    {
        driver = DriverSingleton.getDriver();
    }

    public void given_the_user_navigates_to_orangehrm_page()
    {
        driver.get(ConfigUtil.getUrl());
    }

    public void when_the_user_signs_in_with_correct_username_and_decoded_password(String username, String password)
    {
        CommandInvoker.submit(command, username, password);
    }

    public boolean the_dashboard_page_is_displayed()
    {
        return loginPage.dashboardIsDisplayed();
    }

    public void when_user_navigates_to_pim_section()
    {
        pimPage.goToPIMSection();
    }

    public String getEmployeeId()
    {
        return pimPage.getEmployeeId();
    }

    public void and_clicks_add_employee_button()
    {
        pimPage.clickAddEmployeeButton();
    }

    public void and_enters_firstname_middleName_lastname(String firstname, String middlename, String lastname)
    {
        pimPage.enter_firstname_middleName_lastname(firstname, middlename, lastname);
    }

    public void and_clicks_Add_Employee_save_button()
    {
        pimPage.clickAddEmployeeSaveButton();
    }

    public boolean then_saved_employee_success_popup_should_appear()
    {
        return pimPage.isSuccessPopUpDisplayed();
    }

    public void when_user_selects_nationality(String nationality)
    {
        pimPage.selectNationality(nationality);
    }

    public void and_sets_marital_status(String maritalStatus)
    {
        pimPage.selectMaritalStatus(maritalStatus);
    }

    public void and_selects_date_of_birth(String date)
    {
        pimPage.selectDate(date);
    }

    public void and_selects_gender(String gender)
    {
        pimPage.selectGender(gender);
    }

    public boolean then_success_popup_in_personal_details_should_be_displayed()
    {
        return jobPage.detailsSavedSuccessfully();
    }

    public void when_user_uploads_file(String filePath)
    {
        pimPage.attachAFIle(filePath);
    }

    public boolean then_file_should_appear_in_table(String filePath)
    {
        return pimPage.isFileAttached(filePath);
    }

    public void given_user_is_on_job_tab()
    {
        jobPage.clickJobTab();
    }

    public boolean user_is_on_job_page()
    {
        return jobPage.isUserOnJobPageTab();
    }

    public void when_user_fills_job_title(String jobTitle)
    {
        jobPage.selectJobTitle(jobTitle);
    }

    public void and_sets_joined_date(String date)
    {
        jobPage.selectJoinedDate(date);
    }

    public void and_selects_job_category(String category)
    {
        jobPage.selectJobCategory(category);
    }

    public void and_selects_sub_unit(String subUnit)
    {
        jobPage.selectSubUnit(subUnit);
    }

    public void and_selects_employment_status(String status)
    {
        jobPage.selectEmploymentStatus(status);
    }

    public void and_selects_location()
    {
        jobPage.selectLocation();
    }

    public void and_clicks_save_in_job_tab()
    {
        jobPage.clickSaveButton();
    }

    public void user_goes_to_employment_list_page()
    {
        employeeListPage.goToEmployeeListPage();
    }

    public boolean employMatchesTheOneICreated(EmployeeDto employeeDto)
    {
        return employeeListPage.employMatchesTheOneICreated(employeeDto);
    }
}
