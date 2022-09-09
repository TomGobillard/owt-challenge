package owt.challenge.contactAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import owt.challenge.contactAPI.entity.Contact;
import owt.challenge.contactAPI.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, String> {

//    public Iterable<Skill> findAllByContactsExists(String contactId);
}
