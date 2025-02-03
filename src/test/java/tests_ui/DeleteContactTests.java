package tests_ui;

import config.AppiumConfig;
import dto.UserDtoLombok;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

public class DeleteContactTests extends AppiumConfig
{
    String email = "x7f46zv@yandex.ru";
    String password = "Password123!";

    AuthenticationScreen authenticationScreen;
    SplashScreen splashScreen;
    ContactsScreen contactsScreen;
    AddNewContactScreen addNewContactScreen;

    @BeforeMethod
    public void preConditions()
    {
        splashScreen = new SplashScreen(driver);
        splashScreen.goToAuthScreen(6);
        authenticationScreen = new AuthenticationScreen(driver);
        UserDtoLombok user = UserDtoLombok.builder()
                .username(email)
                .password(password)
                .build();

        authenticationScreen.typeAuthenticationForm(user);
        authenticationScreen.clickLog();
        contactsScreen = new ContactsScreen(driver);
        addNewContactScreen = new AddNewContactScreen(driver);
    }

    @Test(invocationCount = 3)
    public void deleteFirstContactPositive()
    {
        contactsScreen.deleteContact();
        contactsScreen.clickBtnYes();
    }
}
