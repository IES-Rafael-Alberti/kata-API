import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/beers")
public class BeerController {
    private final BeerRepository beerRepository;
    @Autowired
    public BeerController(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }
    @GetMapping
    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }
    @PostMapping
    public Beer createBeer(@RequestBody Beer beer) {
        return beerRepository.save(beer);
    }
    @GetMapping("/{id}")
    public Beer getBeerById(@PathVariable Long id) {
        return beerRepository.findById(id).orElse(null);
    }
    @PutMapping("/{id}")
    public Beer updateBeer(@PathVariable Long id, @RequestBody Beer updatedBeer) {
        Beer existingBeer = beerRepository.findById(id).orElse(null);
        if (existingBeer != null) {
            existingBeer.setName(updatedBeer.getName());
            existingBeer.setAbv(updatedBeer.getAbv());
            // Actualiza otros campos si es necesario
            return beerRepository.save(existingBeer);
        }
        return null; // Devuelve null si no se encuentra la cerveza.
    }
    @DeleteMapping("/{id}")
    public void deleteBeer(@PathVariable Long id) {
        beerRepository.deleteById(id);
    }
}
