package geocoding;

import connection.ISimpleHttpClient;

import org.apache.http.client.utils.URIBuilder;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class AddressResolverTest {

    
    @Mock
    private ISimpleHttpClient mockClient;

    @InjectMocks
    AddressResolverService resolver;

    private String response = "{\"info\":{\"statuscode\":0,\"copyright\":{\"text\":\"© 2024 MapQuest, Inc.\",\"imageUrl\":\"http://api.mqcdn.com/res/mqlogo.gif\",\"imageAltText\":\"© 2024 MapQuest, Inc.\"},\"messages\":[]},\"options\":{\"maxResults\":1,\"ignoreLatLngInput\":false},\"results\":[{\"providedLocation\":{\"latLng\":{\"lat\":40.63436,\"lng\":-8.65616}},\"locations\":[{\"street\":\"Avenida da Universidade\",\"adminArea6\":\"Aveiro\",\"adminArea6Type\":\"Neighborhood\",\"adminArea5\":\"Aveiro\",\"adminArea5Type\":\"City\",\"adminArea4\":\"Aveiro\",\"adminArea4Type\":\"County\",\"adminArea3\":\"\",\"adminArea3Type\":\"State\",\"adminArea1\":\"PT\",\"adminArea1Type\":\"Country\",\"postalCode\":\"3810-489\",\"geocodeQualityCode\":\"B1AAA\",\"geocodeQuality\":\"STREET\",\"dragPoint\":false,\"sideOfStreet\":\"L\",\"linkId\":\"0\",\"unknownInput\":\"\",\"type\":\"s\",\"latLng\":{\"lat\":40.63437,\"lng\":-8.65625},\"displayLatLng\":{\"lat\":40.63437,\"lng\":-8.65625},\"mapUrl\":\"\"}]}]}";

    @BeforeEach
    public void setUp() {
        // Service to Mock
        mockClient = Mockito.mock(ISimpleHttpClient.class);
        // SuT with the injected mock
        resolver = new AddressResolverService(mockClient);
    }


    

    @Test
    void whenResolveDetiGps_returnJacintoMagalhaeAddress() throws ParseException, IOException, URISyntaxException {

        //todo: implement test; remove Disabled annotation
        URIBuilder uriBuilder = new URIBuilder("https://www.mapquestapi.com/geocoding/v1/reverse?key=fjpDDX0NAqWObtm30nW1w2njohV84QWs&location=40.63436%2C-8.65616&outFormat=json&thumbMaps=false");
        Mockito.when(mockClient.doHttpGet(uriBuilder.build().toString())).thenReturn(response);
        // will crash for now...need to set the resolver before using it
        Optional<Address> result = resolver.findAddressForLocation(40.63436, -8.65616);

        //retunr
        Address expected = new Address( "Avenida da Universidade", "Aveiro","3810-489", "");

        assertTrue( result.isPresent());
        assertEquals( expected, result.get());

    }

    
    @Test
    public void whenBadCoordidates_thenReturnNoValidAddress() throws IOException, URISyntaxException, ParseException {

        String response2 = "{\n" + //
        "  \"info\": {\n" + //
        
        "    \"statuscode\": 400,\n" + //
        "    \"copyright\": {\n" + //
        "      \"text\": \"© 2024 MapQuest, Inc.\",\n" + //
        "      \"imageUrl\": \"http://api.mqcdn.com/res/mqlogo.gif\",\n" + //
        "      \"imageAltText\": \"© 2024 MapQuest, Inc.\"\n" + //
        "    },\n" + //
        "    \"messages\": [\n" + //
        "      \"Illegal argument from request: Invalid LatLng specified.\"\n" + //
        "    ]\n" + //
        "  },\n" + //
        "  \"options\": {\n" + //
        "    \"maxResults\": 1,\n" + //
        "    \"ignoreLatLngInput\": false\n" + //
        "  },\n" + //
        "  \"results\": [\n" + //
        "    {\n" + //
        "      \"providedLocation\": {},\n" + //
        "      \"locations\": []\n" + //
        "    }\n" + //
        "  ]\n" + //
        "}";

        ///todo: implement test
        
        Mockito.when(mockClient.doHttpGet(anyString())).thenReturn(response2);

        Optional<Address> result = resolver.findAddressForLocation( -361,-361);
        // verify no valid result
        assertFalse( result.isPresent());

        

    }
}