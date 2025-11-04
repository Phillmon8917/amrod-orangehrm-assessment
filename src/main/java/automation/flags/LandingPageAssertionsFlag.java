package automation.flags;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This enum constant helps to match the scenario we are on so
 * the relevant testing can be done
 */
@AllArgsConstructor
@Getter
public enum LandingPageAssertionsFlag
{
    LOGIN_PAGE_DISPLAYED("OrangeHRM login page should be displayed"),
    INCORRECT_URL("Error page should load"),
    HTTP_TO_HTTPS("Browser should redirect to HTTPS login page");

    private final String value;
}
