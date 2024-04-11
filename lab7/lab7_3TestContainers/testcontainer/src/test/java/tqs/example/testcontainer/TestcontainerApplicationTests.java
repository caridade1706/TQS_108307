package tqs.example.testcontainer;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.assertj.core.api.Assertions.assertThat;
import tqs.example.testcontainer.entities.Book;
import tqs.example.testcontainer.repository.BookRepository;
import java.util.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@SpringBootTest
class TestContainersApplicationTests {

	@Autowired
    private BookRepository bookRepository;

	@Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("user")
            .withPassword("password")
            .withDatabaseName("test");
        

        // requires Spring Boot >= 2.2.6
        @DynamicPropertySource
        static void properties(DynamicPropertyRegistry registry) {
            registry.add("spring.datasource.url", container::getJdbcUrl);
            registry.add("spring.datasource.password", container::getPassword);
            registry.add("spring.datasource.username", container::getUsername);
        }

        @Test
        void contextLoads() {
        } 

    

        @Test
        @Order(1)
        public void testCreateBook() {
            Book book = new Book();
            book.setName("Diário de um Banana 1");
            bookRepository.saveAndFlush(book);
        }

        @Test
        @Order(2)
        public void testListBooks() {
            List<Book> books = bookRepository.findAll();
            assertThat(books).hasSize(1).extracting(Book::getName).contains("Diário de um Banana 1");
        }

        @Test
        @Order(3)
        public void testUpdateBook() {
            List<Book> books = bookRepository.findAll();

            Book newbook = new Book();
            newbook.setName("Diário de um Banana 2");
            bookRepository.saveAndFlush(newbook);

            for (Book book : books){
                if (book.getName().equals("Diário de um Banana 1")){
                    book.setName("Diário de um Banana Original");
                    bookRepository.saveAndFlush(book);
                }
                
            }
            books = bookRepository.findAll();
            assertThat(books).hasSize(2).extracting(Book::getName).contains("Diário de um Banana Original");

        }

        @Test
        @Order(4)
        public void testDeleteBook() {
            bookRepository.deleteAll();
            assertThat(bookRepository.count()).isEqualTo(0);
        }
}
