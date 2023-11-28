package acelera.feluis.Bakery.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DistributorDto {

    //region Distributor Properties

    private long id;
    private String name;
    private String email;
    private String cellphone;
    private String phone;

    //endregion



}
