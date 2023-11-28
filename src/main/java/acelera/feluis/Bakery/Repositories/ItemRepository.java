package acelera.feluis.Bakery.Repositories;

import acelera.feluis.Bakery.Models.ItemModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemModel, Integer>
{
    ItemModel findById(int integer);
    ItemModel findByName(String name);
    List<ItemModel> findByUnitPrice(double unit_price);
    List<ItemModel> findByQuantity(int quantity);
    List<ItemModel> findByType(byte type);
    List<ItemModel> findByDistributorId(int distributor_id);

    @Transactional
    @Modifying
    @Query("update ItemModel set quantity = ?1 where id = ?2")
    void updateItemQuantity(int quantity, int item_id);
}
