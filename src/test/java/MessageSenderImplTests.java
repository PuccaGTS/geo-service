import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;


import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTests {

    @Test
    void message_sender_impl_send_ru_text_for_russia (){
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.matches("^172.")))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.172.32.11");

        String message = messageSender.send(headers);
        String expected = "Добро пожаловать";

        Assertions.assertEquals(expected, message);
    }

    @Test
    void message_sender_impl_send_eng_text_for_usa (){
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.matches("^96.")))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.96.32.11");

        String message = messageSender.send(headers);
        String expected = "Welcome";

        Assertions.assertEquals(expected, message);
    }
}
