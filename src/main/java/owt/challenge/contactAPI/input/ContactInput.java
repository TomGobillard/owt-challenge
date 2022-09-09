package owt.challenge.contactAPI.input;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@Data
public class ContactInput {

    @NotNull
    @Pattern(regexp = "^[A-Za-z]*$")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]*$")
    private String lastName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z\s]*$")
    private String fullName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]*$")
    private String city;

    @NotNull
    @Min(value = 01100L, message = "The value must be at least 01100")
    @Max(value = 99999L, message = "The value must be at maximum 99999")
    private int postalCode;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]*$")
    private String country;

    @NotNull
    private String streetName;

    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    private int streetNumber;

    @NotNull
    @Column(unique=true)
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@" +
            "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)" +
            "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\" +
            "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message="Email format is invalid.")
    private String email;

    @NotNull
    @Pattern(regexp = "((?:\\+|00)[17](?: |\\-)?|(?:\\+|00)[1-9]\\d{0,2}(?: |\\-)?|(?:\\+|00)1\\-\\d{3}(?: |\\-)?)?(0\\d|\\([0-9]{3}\\)|[1-9]{0,3})(?:((?: |\\-)[0-9]{2}){4}|((?:[0-9]{2}){4})|((?: |\\-)[0-9]{3}(?: |\\-)[0-9]{4})|([0-9]{7}))")
    private String mobilePhoneNumber;

}
