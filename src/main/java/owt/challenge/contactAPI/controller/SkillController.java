package owt.challenge.contactAPI.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import owt.challenge.contactAPI.assembler.ContactAssembler;
import owt.challenge.contactAPI.assembler.SkillAssembler;
import owt.challenge.contactAPI.entity.Contact;
import owt.challenge.contactAPI.entity.Skill;
import owt.challenge.contactAPI.input.ContactInput;
import owt.challenge.contactAPI.input.SkillInput;
import owt.challenge.contactAPI.service.SkillService;
import owt.challenge.contactAPI.validator.SkillValidator;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/skills")
@RequiredArgsConstructor
public class SkillController {

    private SkillValidator skillValidator;
    private SkillService skillService;
    private SkillAssembler skillAssembler;


    @Autowired
    public SkillController(SkillValidator skillValidator, SkillService skillService, SkillAssembler skillAssembler) {
        this.skillService = skillService;
        this.skillValidator = skillValidator;
        this.skillAssembler = skillAssembler;
    }

    @GetMapping
    public ResponseEntity<?> getAllSkills(){
        Iterable<Skill> skills = skillService.findAll();
        return ResponseEntity.ok(skills);
    }

    @GetMapping(value = "/{skillId}")
    public ResponseEntity<?> getSkillById(@PathVariable("skillId") String skillId){
        return Optional.of(skillService.findById(skillId)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(skillAssembler.toModel(i.get())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addSkill(@RequestBody @Valid SkillInput skillInput) {
        if(skillService.existsById(skillInput.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            Skill skill = new Skill(
                    skillInput.getName(),
                    skillInput.getDescription()
            );
            Skill newSkill = skillService.addSkill(skill);
            URI location = linkTo(methodOn(SkillController.class).getSkillById(String.valueOf(newSkill.getName()))).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    //Only one attribute for skill, use PUT method instead
    @PatchMapping(value = "/{skillId}")
    @Transactional
    public ResponseEntity<?> updatePartialSkill(@PathVariable String skillId) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).build();
    }

    @PutMapping(value = "/{skillId}")
    @Transactional
    public ResponseEntity<?> updateSkill(@RequestBody Skill skill, @PathVariable("skillId") String skillId){
        Optional<Skill> body = Optional.ofNullable(skill);

        if(body.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        if(!skillService.existsById(skillId)){
            return ResponseEntity.notFound().build();
        }

        skill.setName(skillId);
        skillService.addSkill(skill);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{skillId}")
    @Transactional
    public ResponseEntity<?> deleteSkill(@PathVariable("skillId") String skillId) {
        if(!skillService.existsById(skillId)){
            return ResponseEntity.notFound().build();
        } else {
            skillService.deleteSkill(skillId);
            return ResponseEntity.ok().build();
        }
    }
}
