package owt.challenge.contactAPI.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "contact_skill")
@IdClass(ContactSkillId.class)
public class ContactSkill {

    @Id
    private String contactId;

    @Id
    private String skillName;

    @Column(name = "level")
    private int level;

    @ManyToOne
    @JoinColumn(name = "contactId", referencedColumnName = "contact_id", insertable = false, updatable = false)
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "skillName", referencedColumnName = "name", insertable = false, updatable = false)
    private Skill skill;



}
