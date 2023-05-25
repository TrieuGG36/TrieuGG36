package javaweb.callapi.demo.repository;

import javaweb.callapi.demo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
