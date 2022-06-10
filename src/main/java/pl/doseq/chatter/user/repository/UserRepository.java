package pl.doseq.chatter.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.doseq.chatter.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
