package owt.challenge.contactAPI.entity;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Skill extends RepresentationModel<Skill> {

    @Id
    @Column(name = "name")
    private String name;

    private String description;

//    @ManyToMany(mappedBy = "skills")
    @OneToMany(mappedBy = "skill")
    private Collection<ContactSkill> contacts;

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
