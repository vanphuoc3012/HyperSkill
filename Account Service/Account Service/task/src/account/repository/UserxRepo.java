package account.repository;

import account.models.Userx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserxRepo extends JpaRepository<Userx, String> {

    Userx findUserxByEmailIgnoreCase(String email);


    boolean existsByEmailIgnoreCase(String email);

    @Override
    <S extends Userx> S save(S entity);
}
