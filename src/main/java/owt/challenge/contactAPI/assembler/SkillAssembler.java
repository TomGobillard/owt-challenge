package owt.challenge.contactAPI.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import owt.challenge.contactAPI.entity.Contact;
import owt.challenge.contactAPI.entity.Skill;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class SkillAssembler implements RepresentationModelAssembler<Skill, EntityModel<Skill>> {
    @Override
    public EntityModel<Skill> toModel(Skill entity) {
        return EntityModel.of(entity, linkTo(Contact.class).slash(entity.getName()).withRel("skill"));
    }

    @Override
    public CollectionModel<EntityModel<Skill>> toCollectionModel(Iterable<? extends Skill> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
