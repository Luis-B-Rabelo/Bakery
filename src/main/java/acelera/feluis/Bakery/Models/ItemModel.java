package acelera.feluis.Bakery.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "items", schema = "bakery")
public class ItemModel {
    //region Item Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private byte type;
    private String name;
    private String description;
    private double unitPrice;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "distributor_id", nullable = false)
    private DistributorModel distributor;

    //@OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    //private Set<ItemPurchaseModel> itemPurchase = new HashSet<>();

    @ManyToMany(mappedBy = "items")
    private List<SaleModel> sales = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="item_ingredients",
            joinColumns={@JoinColumn(name="ingredient_id")},
            inverseJoinColumns={@JoinColumn(name="item_id")})
    private List<ItemModel> ingredient = new ArrayList<>();

    //endregion



}
