package javaweb.callapi.demo.repository;

import javaweb.callapi.demo.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findOneByCode(String code);
}
