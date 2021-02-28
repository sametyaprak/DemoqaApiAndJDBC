import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

}
