package com.pandilla.finances.services;

public class DonationService {
}
/*
* @Service
public class AdopterService {
    @Autowired
    IAdopterRepository adopterRepository;
    @Autowired
    AdopterMapper adopterMapper;

    public List<AdopterDTO> getAdopters(){
        List<Adopter> adopters = adopterRepository.findAll();
        return adopters.stream().map(adopterMapper::adopterToAdopterDTO).collect(Collectors.toList());
    }
    public AdopterDTO saveAdopter(AdopterDTO adopterDTO){
        Adopter adopter = adopterMapper.adopterDTOtoAdopter(adopterDTO);
        Adopter saveAdopter = adopterRepository.save(adopter);
        return adopterMapper.adopterToAdopterDTO(saveAdopter);
    }
    public Optional<AdopterDTO> getById(Long id){
        Optional<Adopter> optionalAdopter = adopterRepository.findById(id);
        return optionalAdopter.map(adopterMapper::adopterToAdopterDTO);
    }
    public AdopterDTO updateById(AdopterDTO request, Long id){
        Optional<Adopter> optionalAdopter = adopterRepository.findById(id);
        if(optionalAdopter.isPresent()){
            Adopter adopter = optionalAdopter.get();
            adopterMapper.updateAdopterFromDTO(request,adopter);
            Adopter updateAdopter = adopterRepository.save(adopter);
            return adopterMapper.adopterToAdopterDTO(updateAdopter);
        }else{
            throw new NoSuchElementException("Adopter with ID " + id + " NOT FOUND");
        }
    }
    public Boolean deleteById(Long id){
        try {
            adopterRepository.deleteById(id);
            return true;
        } catch (Exception e) {
           System.out.println(e.getMessage());
            return false;
        }
    }

}*/