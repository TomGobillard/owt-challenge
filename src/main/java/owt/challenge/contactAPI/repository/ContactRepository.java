package owt.challenge.contactAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import owt.challenge.contactAPI.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

}
