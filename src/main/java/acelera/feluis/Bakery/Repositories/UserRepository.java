package acelera.feluis.Bakery.Repositories;

import acelera.feluis.Bakery.Models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Integer>
{
    UserModel findById(int id);
    List<UserModel> findByRole(byte role);
}
