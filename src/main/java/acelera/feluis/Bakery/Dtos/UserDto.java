package acelera.feluis.Bakery.Dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private int id;
    private String ip;
   // @NotEmpty(message = "Cargo não pode estar vazio")
    private byte role;
   // @NotEmpty(message = "Senha não pode estar vazia")
    private String password;
}
