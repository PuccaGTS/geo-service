import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceTest {
    GeoServiceImpl sut;

    @BeforeEach
    void geo_service_init(){
        sut = new GeoServiceImpl();
    }
    @AfterEach
    void geo_service_end(){
        sut = null;
    }

    @MethodSource("source")
    @ParameterizedTest
    void geo_service_test_by_name(String ip, String expected){
        Location location = sut.byIp(ip);
        String result = location.getCity();
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> source(){
        return Stream.of(Arguments.of("127.0.0.1", null),
                Arguments.of("172.0.32.11", "Moscow"),
                Arguments.of("96.44.183.149", "New York"),
                Arguments.of("172.0.111.0", "Moscow"),
                Arguments.of("96.22.22.222", "New York"));
    }

    @MethodSource("source1")
    @ParameterizedTest
    void geo_sevice_test_null_exeption(String ip){
        Location location = sut.byIp(ip);
        Assertions.assertNull(location);
    }

    public static Stream<Arguments> source1(){
        return Stream.of(Arguments.of("15.0.0.1"),
                Arguments.of("87.0.32.11"),
                Arguments.of("0.0.0.0"),
                Arguments.of("65.0.111.0"),
                Arguments.of("77.22.22.222"),
                Arguments.of("56.65.45.33"));
    }
}
