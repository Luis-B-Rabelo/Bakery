package acelera.feluis.Bakery.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto {

    //region SaleDto Properties

    private long id;
    private int cashierId;
    private double totalPrice;
    private byte paymentType;
    private LocalDate date;
    private LocalTime time;

    //endregion



}
