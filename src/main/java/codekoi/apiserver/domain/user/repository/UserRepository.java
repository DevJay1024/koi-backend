package codekoi.apiserver.domain.user.repository;

import codekoi.apiserver.domain.user.domain.User;
import codekoi.apiserver.global.error.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailValue(String email);

    default User findUserById(Long userId) {
        final User user = this.findById(userId).orElseThrow(() -> {
            throw new EntityNotFoundException();
        });
        if (user.getCanceledAt() != null) {
            throw new EntityNotFoundException();
        }
        return user;
    }
}
