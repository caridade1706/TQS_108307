package integration;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolverService;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressResolverIT {


    private TqsBasicHttpClient tqsBasicHttpClient;

    private AddressResolverService addressResolver;

    @BeforeEach
    public void init(){
        tqsBasicHttpClient = new TqsBasicHttpClient();
        addressResolver = new AddressResolverService(tqsBasicHttpClient);
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        //todo

        // repeat the same tests conditions from AddressResolverTest, without mocks
        java.util.Optional<Address> result = addressResolver.findAddressForLocation( 40.63436, -8.65616);

        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");

        assertTrue( result.isPresent());
        assertEquals( expected, result.get());

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        //todo
        java.util.Optional<Address> result = addressResolver.findAddressForLocation( -361,-361);
        // repeat the same tests conditions from AddressResolverTest, without mocks
        assertFalse(result.isPresent());
    }



}
