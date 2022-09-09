package owt.challenge.contactAPI.entity;

import lombok.Data;
import owt.challenge.contactAPI.entity.Contact;
import owt.challenge.contactAPI.entity.Skill;

import javax.persistence.*;
import java.io.Serializable;

//@Embeddable
@Data
public class ContactSkillId implements Serializable {

    private String contactId;


    private String skillName;
}