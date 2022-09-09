package owt.challenge.contactAPI.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import owt.challenge.contactAPI.entity.Skill;
import owt.challenge.contactAPI.repository.SkillRepository;

import java.util.Optional;

@Service
@Data
public class SkillService {

    private SkillRepository skillRepository;

    @Autowired
    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public Iterable<Skill> findAll(){
        return skillRepository.findAll();
    }

    public Optional<Skill> findById(String id){
        return skillRepository.findById(id);
    }

    public void deleteSkill(String id) {
        skillRepository.deleteById(id);
    }

    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Skill skill) {return skillRepository.save(skill);}

    public boolean existsById(String skillId) { return skillRepository.existsById(skillId);}

//    public Iterable<Skill> findAllFromContact(String contactId) {return skillRepository.findAllByContactsExists(contactId);}
}
