package automation.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * This is the dto, it holds temporarily the data that will be used to compare with
 * when we are asserting employee's table.
 * Dto -> Because it does not persist the information anywhere
 */
@Getter
@Setter
public class EmployeeDto
{
    private String id;
    private String firstname;
    private String lastname;
    private String jobTitle;
    private String employmentStatus;
    private String subUnit;
}
