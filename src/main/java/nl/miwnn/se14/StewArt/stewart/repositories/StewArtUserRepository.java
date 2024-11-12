package nl.miwnn.se14.StewArt.stewart.repositories;

import nl.miwnn.se14.StewArt.stewart.model.StewArtUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StewArtUserRepository extends JpaRepository<StewArtUser, Long> {
    Optional<StewArtUser> findByUsername(String username);
}
