/*package acelera.feluis.Bakery.Repositories;

import acelera.feluis.Bakery.Models.ItemPurchaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ItemPurchaseRepository extends JpaRepository<ItemPurchaseModel, Long>
{
    List<ItemPurchaseModel> findByItemId(int item_id);
    List<ItemPurchaseModel> findByDate(LocalDate date);
    List<ItemPurchaseModel> findByTime(LocalTime time);
    List<ItemPurchaseModel> findByTotalPrice(double total_price);
}*/