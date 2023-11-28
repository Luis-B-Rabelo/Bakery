package acelera.feluis.Bakery.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "sales", schema = "bakery")
public class SaleModel {

    //region SaleModel Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double totalPrice;

    private byte paymentType;

    @CreationTimestamp
    private LocalDate date;

    @CreationTimestamp
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "cashier_id", nullable = false)
    private UserModel cashier;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable
            (
                    name = "sales_items",
                    joinColumns = {@JoinColumn(name = "sale_id", referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")}
            )
    private List<ItemModel> items = new ArrayList<>();
    //endregion



}
