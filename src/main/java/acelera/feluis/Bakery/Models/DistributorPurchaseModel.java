/*package acelera.feluis.Bakery.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "distributors_purchases", schema = "bakery")
public class DistributorPurchaseModel
{
    //region Sale Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double totalPrice;

    @CreationTimestamp
    private LocalDate date;

    @CreationTimestamp
    private LocalTime time;

    @OneToMany(mappedBy = "distributorPurchase", cascade = CascadeType.REMOVE)
    private Set<ItemPurchaseModel> itemPurchase = new HashSet<>();

    //endregion
}*/
