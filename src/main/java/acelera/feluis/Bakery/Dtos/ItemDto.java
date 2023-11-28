package acelera.feluis.Bakery.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    //region Item Properties

    private int id;
    private byte type;
    private String name;
    private String description;
    private double unitPrice;
    private int quantity;
    private int distributorId;

    //endregion



}
