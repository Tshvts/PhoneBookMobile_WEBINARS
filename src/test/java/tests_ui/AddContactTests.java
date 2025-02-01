package tests_ui;

import config.AppiumConfig;
import dto.ContactDtoLombok;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static helper.RandomUtils.*;

public class AddContactTests extends AppiumConfig
{
    String email = "x7f46zv@yandex.ru";
    String password = "Password123!";

    AuthenticationScreen authenticationScreen;
    SplashScreen splashScreen;
    ContactsScreen contactsScreen;
    AddNewContactScreen addNewContactScreen;

    @BeforeMethod
    public void  openLoginForm()
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

    @Test
    public void addNewContactPositiveTest()
    {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name(generateString(4))
                .lastName(generateString(7))
                .email(generateEmail(8))
                .phone(generateNumbers(10))
                .address(generateString(10))
                .description(generateString(15))
                .build();
        contactsScreen.clickBtnAddContact();
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(contactsScreen.validatePopUpMessage("Contact was added!",5));
    }

    @Test
    public void addNewContactNegativeTest_emptyLastName()
    {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name(generateString(4))
                .lastName("")
                .email(generateEmail(8))
                .phone(generateNumbers(10))
                .address(generateString(10))
                .description(generateString(15))
                .build();
        contactsScreen.clickBtnAddContact();
        addNewContactScreen.typeContactForm(contact);
        addNewContactScreen.clickBtnCreate();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("{lastName=must not be blank}",5));
    }
}
