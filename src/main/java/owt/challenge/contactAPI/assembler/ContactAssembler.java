package owt.challenge.contactAPI.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import owt.challenge.contactAPI.entity.Contact;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class ContactAssembler implements RepresentationModelAssembler<Contact, EntityModel<Contact>> {
    @Override
    public EntityModel<Contact> toModel(Contact entity) {
        return EntityModel.of(entity, linkTo(Contact.class).withSelfRel());
    }

    @Override
    public CollectionModel<EntityModel<Contact>> toCollectionModel(Iterable<? extends Contact> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
