package woojin.bookmaker.handler.service.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query("select count(u) > 0 from Users u where u.email = :email")
    boolean existsByUsersEmail(@Param("email") String email);
}
