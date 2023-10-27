package com.mi.appCervezas.service;

/*
@Service
public class BreweryService {

    @Autowired
    private BreweryRepository breweryRepository;

    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }

    public Brewery getBreweryById(Long id) {
        return breweryRepository.findById(id).orElse(null);
    }

    public Brewery addBrewery(Brewery brewery) {
        return breweryRepository.save(brewery);
    }

    public void deleteBrewery(Long id) {
        breweryRepository.deleteById(id);
    }

    public Brewery updateBrewery(Long id, Brewery newBrewery) {
        Optional<Brewery> optionalBrewery = breweryRepository.findById(id);

        if (optionalBrewery.isPresent()) {
            Brewery existingBrewery = optionalBrewery.get();
            existingBrewery.setName(newBrewery.getName());
            // ... (actualizar otros campos según sea necesario)
            return breweryRepository.save(existingBrewery);
        } else {
            return null; // El ID no existe
        }
    }
}
*/
