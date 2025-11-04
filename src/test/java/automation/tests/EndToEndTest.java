package automation.tests;

import automation.basetest.BaseTest;
import automation.config.RunFrameworkConfiguration;
import automation.dto.EmployeeDto;
import automation.email.Email;
import automation.properties.ConfigPropInstance;
import automation.reportinglibrary.ExtentReportManager;
import automation.stepDefinition.StepDefinitions;
import automation.utils.ConfigUtil;
import automation.utils.FakerUtil;
import automation.utils.ScreenshotUtil;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Objects;

@SpringBootTest(classes = RunFrameworkConfiguration.class)
public class EndToEndTest extends BaseTest
{

    @Autowired
    private StepDefinitions stepDefinitions;

    @Override
    public void initializeReporting()
    {
        extentReports = ExtentReportManager.getExtentReporter("EndToEndTestResults");
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void testEndToEnd()
    {
        ExtentTest extentTest = extentReports.createTest("EndToEndTest");

        extentTest.info("Given  the user navigates to orangehrm page");
        stepDefinitions.given_the_user_navigates_to_orangehrm_page();

        extentTest.info("And User signs in with correct credentials");
        stepDefinitions.when_the_user_signs_in_with_correct_username_and_decoded_password(ConfigUtil.getUsername(), ConfigUtil.getPassword());

        extentTest.info("Verify that the dashboard is the default landing page");
        boolean isDashboardDisplayed = stepDefinitions.the_dashboard_page_is_displayed();
        if (isDashboardDisplayed)
        {
            extentTest.pass("Dashboard is displayed", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        } else
        {
            extentTest.fail("Dashboard is not displayed", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        }
        softAssert.assertTrue(isDashboardDisplayed);

        extentTest.info("When user clicks on PIM side menu");
        stepDefinitions.when_user_navigates_to_pim_section();

        extentTest.info("And clicks on the add employee button");
        stepDefinitions.and_clicks_add_employee_button();

        String firstname = FakerUtil.randomFirstName();
        String middleName = FakerUtil.randomMiddleName();
        String lastname = FakerUtil.randomLastName();
        extentTest.info("And user fills in firstname: " + firstname + ", middleName: " + middleName + ", lastname: " + lastname);
        stepDefinitions.and_enters_firstname_middleName_lastname(firstname, middleName, lastname);

        String employeeId = stepDefinitions.getEmployeeId();
        extentTest.info("And user collects employee Id : " + employeeId);

        extentTest.info("user clicks save employee button");
        stepDefinitions.and_clicks_Add_Employee_save_button();

        extentTest.info("Then saved employee successful popup appears");
        boolean savedEmpPopUpAppears = stepDefinitions.then_saved_employee_success_popup_should_appear();
        if (savedEmpPopUpAppears)
        {
            extentTest.pass("popup appeared", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        } else
        {
            extentTest.fail("popup did not appear", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        }
        softAssert.assertTrue(savedEmpPopUpAppears);

        extentTest.info("User is on the employee details page");

        extentTest.info("User set nationality to South African");
        stepDefinitions.when_user_selects_nationality("South African");

        extentTest.info("Select marital status as single");
        stepDefinitions.and_sets_marital_status("Single");

        String dateOfBirth = FakerUtil.randomDateOfBirth();
        extentTest.info("Select date of birth: " + dateOfBirth);
        stepDefinitions.and_selects_date_of_birth(dateOfBirth);

        String gender = FakerUtil.randomGender();
        extentTest.info("Select gender: " + gender);
        stepDefinitions.and_selects_gender(gender);

        extentTest.info("user clicks add attachment and attaches a file");
        stepDefinitions.when_user_uploads_file("file/pdf-test.pdf");

        extentTest.info("Uploaded file appears on the table");
        boolean attachedFileAppears = stepDefinitions.then_file_should_appear_in_table("file/pdf-test.pdf");
        if (attachedFileAppears)
        {
            extentTest.pass("Files Appears", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        } else
        {
            extentTest.fail("File does not appear", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        }
        softAssert.assertTrue(attachedFileAppears);

        extentTest.info("User goes to job tab");
        stepDefinitions.given_user_is_on_job_tab();

        extentTest.info("verify user is on job page");
        boolean userIsOnJobPage = stepDefinitions.user_is_on_job_page();
        if (!userIsOnJobPage)
        {
            extentTest.fail("User could not reach job page", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        } else
        {
            extentTest.pass("User is on job page", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        }
        softAssert.assertTrue(userIsOnJobPage);

        String joinedDate = FakerUtil.recentJoinedDate();
        extentTest.info("user sets joined date: " + joinedDate);
        stepDefinitions.and_sets_joined_date(joinedDate);

        extentTest.info("user sets job title to QA Engineer");
        stepDefinitions.when_user_fills_job_title("QA Engineer");

        extentTest.info("user selects job category as Professionals");
        stepDefinitions.and_selects_job_category("Professionals");

        extentTest.info("user selects sub unit as Quality Assurance");
        stepDefinitions.and_selects_sub_unit("Quality Assurance");

        extentTest.info("user selects any location");
        stepDefinitions.and_selects_location();

        //NB : (Fulltime-Contract) is not there in the options provided i chose something similar
        extentTest.info("user selects employment status as Full-Time Permanent");
        stepDefinitions.and_selects_employment_status("Full-Time Permanent");

        extentTest.info("user saves");
        stepDefinitions.and_clicks_save_in_job_tab();

        extentTest.info("Saved successfully pop up appears");
        boolean savedSuccessPopupAppear = stepDefinitions.then_success_popup_in_personal_details_should_be_displayed();
        if (savedSuccessPopupAppear)
        {
            extentTest.pass("Pop up appears", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        } else
        {
            extentTest.fail("Pop up failed to appear", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        }
        softAssert.assertTrue(savedEmpPopUpAppears);

        extentTest.info("User navigates to employee list page");
        stepDefinitions.user_goes_to_employment_list_page();

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employeeId);
        employeeDto.setFirstname(firstname);
        employeeDto.setLastname(lastname);
        employeeDto.setJobTitle("QA Engineer");
        employeeDto.setEmploymentStatus("Full-Time Permanent");
        employeeDto.setSubUnit("Quality Assurance");

        extentTest.info("user validates the employee registered");
        boolean isEmployeeCorrect = stepDefinitions.employMatchesTheOneICreated(employeeDto);
        if (isEmployeeCorrect)
        {
            extentTest.pass("Scenario Passed", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        } else
        {
            extentTest.fail("Scenario failed", MediaEntityBuilder.createScreenCaptureFromBase64String(Objects.requireNonNull(ScreenshotUtil.captureScreenshotAsBase64(driver))).build());
        }
        softAssert.assertTrue(isEmployeeCorrect, "Employee details do not match the values entered");

        if (ConfigPropInstance.getConfig().sendEmail().trim().equalsIgnoreCase("Yes"))
        {
            Email.sendEmail();
        }

        softAssert.assertAll();
    }

}
