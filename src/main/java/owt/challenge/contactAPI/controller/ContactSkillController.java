package owt.challenge.contactAPI.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import owt.challenge.contactAPI.assembler.ContactAssembler;
import owt.challenge.contactAPI.entity.Contact;
import owt.challenge.contactAPI.entity.ContactSkill;
import owt.challenge.contactAPI.entity.Skill;
import owt.challenge.contactAPI.input.ContactSkillInput;
import owt.challenge.contactAPI.service.ContactService;
import owt.challenge.contactAPI.service.SkillService;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/contacts/{contactId}/skills")
@RequiredArgsConstructor
public class ContactSkillController {

    private SkillService skillService;
    private ContactService contactService;
    private ContactAssembler contactAssembler;

    @Autowired
    public ContactSkillController(ContactService contactService, SkillService skillService, ContactAssembler contactAssembler) {
        this.contactService = contactService;
        this.skillService = skillService;
        this.contactAssembler = contactAssembler;
    }

    @GetMapping
    public ResponseEntity<?> getAllSkillsFromContact(@PathVariable String contactId){
        if(!contactService.existsById(contactId)) {
            Optional<Contact> contactBody = contactService.findById(contactId);
            if (contactBody.isPresent()) {
                Contact contact = contactBody.get();
                return ResponseEntity.ok(contact.getSkills());
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addSkillToContact(@RequestBody @Valid ContactSkillInput contactSkillInput, @PathVariable String contactId) {
        if(!contactService.existsById(contactId) || !skillService.existsById(contactSkillInput.getName())) {
            return ResponseEntity.notFound().build();
        } else {
            Optional<Skill> skillBody = skillService.findById(contactSkillInput.getName());
            if(skillBody.isPresent()) {
                Skill skill = skillBody.get();
                Optional<Contact> contactBody = contactService.findById(contactId);
                if (contactBody.isPresent()) {
                    Contact contact = contactBody.get();
                    contact.addContactSkill(skill, contactSkillInput.getLevel());
                    return ResponseEntity.created(linkTo(methodOn(Contact.class).getContactId()).slash(skill.getName()).toUri()).build();
                } else {
                    System.out.println("contact non recup");
                    return ResponseEntity.notFound().build();
                }
            } else {
                System.out.println("skill non recup");
                return ResponseEntity.notFound().build();
            }
        }
    }
}
