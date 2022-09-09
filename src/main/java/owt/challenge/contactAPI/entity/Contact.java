package owt.challenge.contactAPI.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contact {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String contactId;

    private String firstName;
    private String lastName;
    private String fullName;
    private String city;
    private int postalCode;
    private String country;
    private String streetName;
    private int streetNumber;
    private String email;
    private String mobilePhoneNumber;

//    @ManyToMany
//    @JoinTable(name = "contact_skill",
//    joinColumns = @JoinColumn(name = "contact_id", referencedColumnName = "contact_id"),
//    inverseJoinColumns = @JoinColumn(name = "skill_name", referencedColumnName = "name"))
    @OneToMany(mappedBy = "contact")
    private Collection<ContactSkill> skills;

    public ContactSkill addContactSkill(Skill skill, int level) {
        ContactSkill contactSkill = new ContactSkill();
        contactSkill.setContact(this);
        contactSkill.setSkill(skill);
        contactSkill.setLevel(level);
        if(this.skills == null) {
            this.skills = new ArrayList<>();
        }
        this.skills.add(contactSkill);
        skill.getContacts().add(contactSkill);
        return contactSkill;
    }


    public Contact(String firstName, String lastName, String fullName, String city, int postalCode, String country, String streetName, int streetNumber, String email, String mobilePhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.email = email;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }
}
