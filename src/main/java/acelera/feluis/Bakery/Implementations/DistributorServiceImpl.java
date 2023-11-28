package acelera.feluis.Bakery.Implementations;

import acelera.feluis.Bakery.Dtos.DistributorDto;
import acelera.feluis.Bakery.Models.DistributorModel;
import acelera.feluis.Bakery.Repositories.DistributorRepository;
import acelera.feluis.Bakery.Repositories.ItemRepository;
import acelera.feluis.Bakery.Services.DistributorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistributorServiceImpl implements DistributorService
{
    private final DistributorRepository distributor_repository;

    @Autowired
    public DistributorServiceImpl(ItemRepository item_repository, DistributorRepository distributorRepository)
    {
        this.distributor_repository = distributorRepository;
    }


    private DistributorDto mapToDistributorDto(DistributorModel distributor)
    {
        DistributorDto distributor_dto = DistributorDto.builder()
                .id(distributor.getId())
                .name(distributor.getName())
                .email(distributor.getEmail())
                .cellphone(distributor.getCellphone())
                .phone(distributor.getPhone())
                .build();

        return distributor_dto;
    }

    @Override
    public List<DistributorDto> getAllDistributors() {
        List<DistributorModel> distributors = distributor_repository.findAll();

        return distributors.stream().map((distributor) -> mapToDistributorDto(distributor)).collect(Collectors.toList());
    }

    @Override
    public DistributorDto getDistributorByName(String name) {

        DistributorModel distributor = distributor_repository.findByName(name);

        return this.mapToDistributorDto(distributor);
    }

    @Override
    public boolean createDistributor(String name, String email, String cellphone, String phone) {
        boolean confirmation = true;

        DistributorModel distributor = new DistributorModel();
        distributor.setName(name);
        distributor.setEmail(email);
        distributor.setCellphone(cellphone);
        distributor.setPhone(phone);

        if(distributor_repository.save(distributor) != distributor)
        {
            confirmation = false;
        }

        return confirmation;
    }



}
