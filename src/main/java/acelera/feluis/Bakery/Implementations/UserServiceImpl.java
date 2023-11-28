package acelera.feluis.Bakery.Implementations;

import acelera.feluis.Bakery.Dtos.UserDto;
import acelera.feluis.Bakery.Models.UserModel;
import acelera.feluis.Bakery.Repositories.UserRepository;
import acelera.feluis.Bakery.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    private final UserRepository user_repository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.user_repository = userRepository;
    }


    private UserDto mapToUserDto(UserModel user)
    {
        UserDto userDto = UserDto.builder()
                .id(user.getId())
                .ip(user.getIp())
                .role(user.getRole())
                .build();

        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers()
    {
        List<UserModel> users = user_repository.findAll();

        return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByRole(byte role)
    {
        List<UserModel> users = user_repository.findByRole(role);

        return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(int id)
    {
        return this.mapToUserDto(user_repository.findById(id));
    }

    @Override
    public boolean confirmUserLogin(int id, String password)
    {
        boolean test = false;

        if(user_repository.existsById(id))
        {
            if(Objects.equals(user_repository.findById(id).getPassword(), password))
            {
                if(user_repository.findById(id).getIp() == null)
                {
                    try(final DatagramSocket socket = new DatagramSocket())
                    {
                        socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                        String ip = socket.getLocalAddress().getHostAddress();

                        user_repository.findById(id).setIp(ip);

                        test = true;
                    } catch (SocketException | UnknownHostException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
                else
                {
                    try(final DatagramSocket socket = new DatagramSocket())
                    {
                        socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                        String ip = socket.getLocalAddress().getHostAddress();

                        if(Objects.equals(user_repository.findById(id).getIp(), ip))
                        {
                            test = true;
                        }
                    } catch (SocketException | UnknownHostException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return test;
    }

    public boolean createUser(byte role, String password)
    {
        boolean confirmation = true;

        UserModel user = new UserModel();
        user.setRole(role);
        user.setPassword(password);

        if(user_repository.save(user) != user)
        {
            confirmation = false;
        }

        return confirmation;
    }
}
