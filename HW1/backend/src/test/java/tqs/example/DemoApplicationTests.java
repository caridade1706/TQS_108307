package tqs.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        // Simple test to ensure the application context loads without errors
        assertThatCode(() -> BusTicketApplication.main(new String[]{})).doesNotThrowAnyException();
    }
}
