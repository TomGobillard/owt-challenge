package owt.challenge.contactAPI.input;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class SkillInput {

    @NotNull
    private String name;

    private String description;
}
