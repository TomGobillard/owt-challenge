package owt.challenge.contactAPI.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import owt.challenge.contactAPI.assembler.ContactAssembler;
import owt.challenge.contactAPI.entity.Contact;
import owt.challenge.contactAPI.input.ContactInput;
import owt.challenge.contactAPI.service.ContactService;
import owt.challenge.contactAPI.validator.ContactValidator;

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
@RequestMapping(value = "/contacts")
@RequiredArgsConstructor
public class ContactController {

    private ContactService contactService;
    private ContactValidator contactValidator;
    private ContactAssembler contactAssembler;

    @Autowired
    public ContactController(ContactService contactService, ContactValidator contactValidator, ContactAssembler contactAssembler) {
        this.contactService = contactService;
        this.contactValidator = contactValidator;
        this.contactAssembler = contactAssembler;
    }

    @GetMapping
    public ResponseEntity<?> getAllContacts(){
        Iterable<Contact> contacts = contactService.findAll();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping(value = "/{contactId}")
    public ResponseEntity<?> getContactById(@PathVariable("contactId") String contactId){

        return Optional.of(contactService.findById(contactId)).filter(Optional::isPresent)
                .map(i -> ResponseEntity.ok(contactAssembler.toModel(i.get())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> addContact(@RequestBody @Valid ContactInput contactInput) {
        Contact contact = new Contact(
            contactInput.getFirstName(),
                contactInput.getLastName(),
                contactInput.getFullName(),
                contactInput.getCity(),
                contactInput.getPostalCode(),
                contactInput.getCountry(),
                contactInput.getStreetName(),
                contactInput.getStreetNumber(),
                contactInput.getEmail(),
                contactInput.getMobilePhoneNumber()
        );
        Contact newContact = contactService.addContact(contact);
        URI location = linkTo(methodOn(ContactController.class).getContactById(newContact.getContactId())).slash(newContact.getContactId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping(value = "/{contactId}")
    @Transactional
    public ResponseEntity<?> updatePartialContact(@RequestBody Map<Object, Object> fields, @PathVariable String contactId) {

        Optional<Contact> body = contactService.findById(contactId);

        if(body.isPresent()) {
            Contact contact = body.get();

            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Contact.class, key.toString());
                Objects.requireNonNull(field).setAccessible(true);
                ReflectionUtils.setField(field, contact, value);
            });

            contactValidator.validate(new ContactInput(contact.getFirstName(),
                    contact.getLastName(),
                    contact.getFullName(),
                    contact.getCity(),
                    contact.getPostalCode(),
                    contact.getCountry(),
                    contact.getStreetName(),
                    contact.getStreetNumber(),
                    contact.getEmail(),
                    contact.getMobilePhoneNumber()));

            contact.setContactId(contactId);
            contactService.updateContact(contact);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{contactId}")
    @Transactional
    public ResponseEntity<?> updateContact(@RequestBody Contact contact, @PathVariable("contactId") String contactId){
        Optional<Contact> body = Optional.ofNullable(contact);

        if(body.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        if(!contactService.existsById(contactId)){
            return ResponseEntity.notFound().build();
        }

        contact.setContactId(contactId);
        contactService.addContact(contact);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{contactId}")
    @Transactional
    public ResponseEntity<?> deleteContact(@PathVariable("contactId") String contactId) {
        if(!contactService.existsById(contactId)){
            return ResponseEntity.notFound().build();
        } else {
            contactService.deleteContact(contactId);
            return ResponseEntity.ok().build();
        }
    }
}
