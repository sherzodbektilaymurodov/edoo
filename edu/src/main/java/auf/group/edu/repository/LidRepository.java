package auf.group.edu.repository;

import auf.group.edu.entity.Lid;
import auf.group.edu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface LidRepository extends JpaRepository<Lid, UUID> {
}
