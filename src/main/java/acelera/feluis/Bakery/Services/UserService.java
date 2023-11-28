package acelera.feluis.Bakery.Services;

import acelera.feluis.Bakery.Dtos.UserDto;

import java.util.List;

public interface UserService
{
    List<UserDto> getAllUsers();
    List<UserDto>  getUsersByRole(byte role);

    UserDto getUserById(int id);

    boolean confirmUserLogin(int id, String password);

    boolean createUser(byte role, String password);
}
