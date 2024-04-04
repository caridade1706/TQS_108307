
import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class AppTest {

    String baseURI = "https://jsonplaceholder.typicode.com";

    //the endpoint to list all ToDos is available (status code 200)
    @Test
    public void testGet() {
        given().get(baseURI + "/todos").then().statusCode(200);
    }

    // when querying for ToDo #4, the API returns an object with title “et porro tempora”
    @Test
    public void testPost() {
        given().get(baseURI + "/todos/4").then().body("title", Matchers.equalTo("et porro tempora"));
    }
    
    //When listing all “todos”, you get id #198 and #199 in the JSON results.
    @Test
    public void testGetId198And199() {
        given().get(baseURI + "/todos").then().body("id", Matchers.hasItems(198, 199));
        
    }

    //When listing all “todos”, you the results in less than 2sec
    @Test
    public void testGetInLessThan2Sec() {
        given().get(baseURI + "/todos").then().time(Matchers.lessThan(2000L));
    }
}
