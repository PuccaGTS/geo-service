import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceTest {
    LocalizationServiceImpl sut;

    @BeforeEach
    void localizationService_init(){
        sut = new LocalizationServiceImpl();
    }

    @AfterEach
    void localizationService_null(){
        sut = null;
    }


    @ParameterizedTest
    @EnumSource(value = Country.class, names = {"GERMANY","USA","BRAZIL"})
    void localization_service_test_without_rus(Country country){
        String result = sut.locale(country);
        String expected = "Welcome";
        Assertions.assertEquals(expected, result);
    }

    @Test
    void localization_service_test_only_rus(){
        Country country = Country.RUSSIA;
        String result = sut.locale(country);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected, result);
    }
}
