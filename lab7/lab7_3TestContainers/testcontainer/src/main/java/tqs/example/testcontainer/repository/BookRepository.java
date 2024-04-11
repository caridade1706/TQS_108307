package tqs.example.testcontainer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.example.testcontainer.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    
}
