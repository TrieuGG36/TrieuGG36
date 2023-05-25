package javaweb.callapi.demo.repository;

import javaweb.callapi.demo.entities.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewRepository extends JpaRepository <NewEntity, Long> {

}
