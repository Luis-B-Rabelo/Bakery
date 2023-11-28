package acelera.feluis.Bakery.Repositories;

import acelera.feluis.Bakery.Models.DistributorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistributorRepository extends JpaRepository<DistributorModel, Integer>
{
    DistributorModel findById(int id);
    DistributorModel findByName(String name);
    DistributorModel findByEmail(String email);
    DistributorModel findByCellphone(String cellphone);
    DistributorModel findByPhone(String phone);
}