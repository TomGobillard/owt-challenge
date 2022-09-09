package owt.challenge.contactAPI.input;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ContactSkillInput {

    @NotNull
    private String name;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    private int level;
}
