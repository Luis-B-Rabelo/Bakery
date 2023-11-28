package acelera.feluis.Bakery.Repositories;

import acelera.feluis.Bakery.Models.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<SaleModel, Long>
{
    SaleModel findById(long id);
    SaleModel findLastByCashierId(int cashier_id);
    List<SaleModel> findByDate(LocalDate date);
    List<SaleModel> findByTime(LocalTime time);
    List<SaleModel> findByTotalPrice(double total_price);
    List<SaleModel> findByCashierId(int cashier_id);

}
