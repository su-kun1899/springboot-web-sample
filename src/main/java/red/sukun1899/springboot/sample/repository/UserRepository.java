package red.sukun1899.springboot.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import red.sukun1899.springboot.sample.entity.User;

/**
 * @author su-kun1899
 */
public interface UserRepository extends JpaRepository<User, String> {
}
