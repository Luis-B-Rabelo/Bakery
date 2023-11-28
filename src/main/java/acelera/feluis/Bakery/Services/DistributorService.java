package acelera.feluis.Bakery.Services;


import acelera.feluis.Bakery.Dtos.DistributorDto;

import java.util.List;

public interface DistributorService
{
    List<DistributorDto> getAllDistributors();
    DistributorDto  getDistributorByName(String name);

    boolean createDistributor(String name, String email, String cellphone, String phone);
}
