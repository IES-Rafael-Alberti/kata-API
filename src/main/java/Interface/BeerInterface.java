import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {
    List<Beer> findByName(String name);
    List<Beer> findByAbvBetween(Double minAbv, Double maxAbv);
}
