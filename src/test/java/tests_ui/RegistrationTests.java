package tests_ui;

import config.AppiumConfig;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactsScreen;
import screens.ErrorScreen;
import screens.SplashScreen;

import static helper.RandomUtils.*;

public class RegistrationTests extends AppiumConfig
{
    AuthenticationScreen authenticationScreen;

    @Test
    public void positiveRegistrationTest()
    {
        authenticationScreen = new AuthenticationScreen(driver);
        new SplashScreen(driver).goToAuthScreen(3);
        authenticationScreen.typeAuthenticationForm
                (
                        UserDtoLombok.builder()
                        .username(generateEmail(7))
                        .password("Password123!")
                .build()
                );
        authenticationScreen.clickBtnReg();
        Assert.assertTrue(new ContactsScreen(driver).validateHeader());
    }

    @Test
    public void negativeRegistrationTest_WrongEmail()
    {
        authenticationScreen = new AuthenticationScreen(driver);
        new SplashScreen(driver).goToAuthScreen(3);
        authenticationScreen.typeAuthenticationForm
                (
                        UserDtoLombok.builder()
                        .username(generateString(12))
                        .password("Password123!")
                .build()
                );
        authenticationScreen.clickBtnReg();
       Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("username=must be a well-formed email address", 5));
    }


    @Test
    public void negativeRegistrationTest_WrongPassword()
    {
        authenticationScreen = new AuthenticationScreen(driver);
        new SplashScreen(driver).goToAuthScreen(5);
        authenticationScreen.typeAuthenticationForm
                (
                        UserDtoLombok.builder()
                                .username(generateEmail(12))
                                .password("P")
                                .build()
                );
        authenticationScreen.clickBtnReg();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("{password= At least 8 characters; Must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number; Can contain special characters [@$#^&*!]}", 5));
    }
}
