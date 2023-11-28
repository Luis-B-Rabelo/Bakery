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
@Table(name = "users", schema = "bakery")
public class  UserModel {

    //region User Properties

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String ip;

    private byte role;

    private String password;

    @OneToMany(mappedBy = "cashier", cascade = CascadeType.REMOVE)
    private Set<SaleModel> sales = new HashSet<>();

    //endregion



}
