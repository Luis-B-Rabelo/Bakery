/*package acelera.feluis.Bakery.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "item_purchase", schema = "bakery")
public class ItemPurchaseModel {

    //region Item Purchase Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int quantity;
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemModel item;

    @ManyToOne
    @JoinColumn(name = "distributor_purchase_id", nullable = false)
    private DistributorPurchaseModel distributorPurchase;

    //endregion



}*/