package owt.challenge.contactAPI.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import owt.challenge.contactAPI.entity.Contact;
import owt.challenge.contactAPI.repository.ContactRepository;

import java.util.Optional;

@Service
@Data
public class ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Iterable<Contact> findAll(){
        return contactRepository.findAll();
    }

    public Optional<Contact> findById(String id){
        return contactRepository.findById(id);
    }

    public void deleteContact(String id) {
        contactRepository.deleteById(id);
    }

    public Contact addContact(Contact contact) {return contactRepository.save(contact);}

    public Contact updateContact(Contact contact) {return contactRepository.save(contact);}

    public boolean existsById(String contactId) {return contactRepository.existsById(contactId);}
}
