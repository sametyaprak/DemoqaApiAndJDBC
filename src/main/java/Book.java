import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Setter
@Table(name = "books")
@NoArgsConstructor
@Component
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookId;
    private String bookName;
    private String bookWriter;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "books")
    Set<User> users = new HashSet<>();

    public Book(String bookName, String bookWriter, Set<User> users) {
        this.bookName = bookName;
        this.bookWriter = bookWriter;
        this.users = users;
    }

    public Book(long bookId, String bookName, String bookWriter) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookWriter = bookWriter;
    }

    public long getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookWriter() {
        return bookWriter;
    }
}
