@book_search
Feature: Book Search
    

    Scenario: Search books by title having books
        Given the library has the following books
            |Title|Author|Published|
            |The Hobbit|J.R.R. Tolkien|1937|
            |Clean Code|Robert C. Martin|2008|
            |The Clean Coder|Robert C. Martin|2011|
            |The Art of Computer Programming|Donald E. Knuth|1968|
        When I search for books by title "The Hobbit"
        Then the found book should have the title "The Hobbit"
        And the found book should have the author "J.R.R. Tolkien"

    Scenario: Search books by title having no books
        Given the library has the following books
            |Title|Author|Published|
        When I search for books by title "The Hobbit"
        # Then I should find 0 books

    Scenario: Search books by publication year having books
        Given the library has the following books
            |Title|Author|Published|
            |The Hobbit|J.R.R. Tolkien|1937|
            |Clean Code|Robert C. Martin|2008|
            |The Clean Coder|Robert C. Martin|2011|
            |The Art of Computer Programming|Donald E. Knuth|1968|
        When I search for books published beetwen 2000-07-12 and 2015-09-14
        Then I should find 2 books