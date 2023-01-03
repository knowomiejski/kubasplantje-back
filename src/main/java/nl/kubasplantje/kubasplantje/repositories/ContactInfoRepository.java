package nl.kubasplantje.kubasplantje.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.kubasplantje.kubasplantje.models.ContactInfoModel;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfoModel, Long>{

}
