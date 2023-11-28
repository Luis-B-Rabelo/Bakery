package acelera.feluis.Bakery.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "distributors", schema = "bakery")
public class DistributorModel {

    //region Distributor Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String cellphone;
    private String phone;

    @OneToMany(mappedBy = "distributor", cascade = CascadeType.REMOVE)
    private Set<ItemModel> items = new HashSet<>();

    //endregion
}
