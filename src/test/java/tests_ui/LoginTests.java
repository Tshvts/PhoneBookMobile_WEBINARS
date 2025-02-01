package tests_ui;

import config.AppiumConfig;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactsScreen;
import screens.ErrorScreen;
import screens.SplashScreen;
import static helper.RandomUtils.*;

public class LoginTests extends AppiumConfig
{
    String email = "x7f46zv@yandex.ru";
    String password = "Password123!";

    AuthenticationScreen authenticationScreen;
    SplashScreen splashScreen;

    @BeforeMethod
    public void  openLoginForm()
    {
        splashScreen = new SplashScreen(driver);
        splashScreen.goToAuthScreen(6);
        authenticationScreen = new AuthenticationScreen(driver);
    }

    @Test
    public void loginPositiveTest() {
        UserDtoLombok user = UserDtoLombok.builder()
                .username(email)
                .password(password)
                .build();

        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickLog();
        Assert.assertTrue(new ContactsScreen(driver).validateHeader());
    }

    @Test
    public void loginNegativeTest_wrongEmail()
    {
        UserDtoLombok user = UserDtoLombok.builder()
                .username(generateString(10))
                .password(password)
                .build();

        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickLog();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Login or Password incorrect",5));
    }

    @Test
    public void loginNegativeTest_wrongPassword()
    {
        UserDtoLombok user = UserDtoLombok.builder()
                .username(email)
                .password("2kw")
                .build();

        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickLog();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("Login or Password incorrect",5));
    }
}
