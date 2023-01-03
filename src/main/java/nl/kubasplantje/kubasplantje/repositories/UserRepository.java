package nl.kubasplantje.kubasplantje.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nl.kubasplantje.kubasplantje.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{
    Optional<UserModel> findByUserName(String userName);
}
