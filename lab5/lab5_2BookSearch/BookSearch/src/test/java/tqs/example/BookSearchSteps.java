package tqs.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tqs.example.Library;
import tqs.example.Book;


public class BookSearchSteps {

    private Book book;
    private Library library = new Library();
    private List<Book> searchResult;

    @DataTableType
    public Book bookEntry ( Map<String, String> entry ) {
        LocalDateTime date = LocalDateTime.of(Integer.parseInt(entry.get("Published")),1,1,0,0);
        Date newDate = Date.from(date.toInstant(ZoneOffset.UTC));
        return new Book(entry.get("Title"), entry.get("Author"), newDate) ; 
    }


    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public Date iso8601Date(String year, String month, String day) {
        LocalDateTime date = LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
        return Date.from(date.toInstant(ZoneOffset.UTC));
    }

    @Given("the library has the following books")
    public void the_library_has_the_following_books(List<Book> books) {
        books.forEach(book -> library.addBook(book));
    }

    @When("I search for books by title {string}")
    public void i_search_for_books_by_title(String title) {
        searchResult = library.findBooksByTitle(title);
    }

    @Then("the found book should have the title {string}")
    public void the_found_book_should_have_the_title(String title) {
        assertEquals(title, searchResult.get(0).getTitle());
    }

    @And("the found book should have the author {string}")
    public void the_found_book_should_have_the_author(String author) {
        assertEquals(author, searchResult.get(0).getAuthor());
    }

    @When("I search for books published beetwen {iso8601Date} and {iso8601Date}")
    public void i_search_for_books_published_between_and(Date from, Date to) {
        searchResult = library.findBooksByDate(from, to);
    }


    @Then("I should find {int} books")
    public void i_should_find_book(int count) {
        assertEquals(count, searchResult.size());
    }
    
}
