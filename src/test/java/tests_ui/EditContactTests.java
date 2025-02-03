package tests_ui;

import config.AppiumConfig;
import dto.ContactDtoLombok;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.*;

import static helper.RandomUtils.*;
import static helper.RandomUtils.generateString;

public class EditContactTests extends AppiumConfig
{
    String email = "x7f46zv@yandex.ru";
    String password = "Password123!";

    AuthenticationScreen authenticationScreen;
    SplashScreen splashScreen;
    ContactsScreen contactsScreen;
    AddNewContactScreen addNewContactScreen;
    EditContactScreen editContactScreen;

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
        editContactScreen = new EditContactScreen(driver);
    }

    @Test
    public void editContactPositive()
    {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name(generateString(4) + "&&")
                .lastName(generateString(7))
                .email(generateEmail(8))
                .phone(generateNumbers(10))
                .address(generateString(10))
                .description(generateString(15))
                .build();
        contactsScreen.goToEditScreen();
        editContactScreen.typeEditContactForm(contact);
        editContactScreen.clickBtnUpdate();
        Assert.assertTrue(contactsScreen.validatePopUpMessage("Contact was updated!",5));
    }

    @Test
    public void editContactNegative_emptyName()
    {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("")
                .lastName(generateString(7))
                .email(generateEmail(8))
                .phone(generateNumbers(10))
                .address(generateString(10))
                .description(generateString(15))
                .build();
        contactsScreen.goToEditScreen();
        editContactScreen.typeEditContactForm(contact);
        editContactScreen.clickBtnUpdate();
        Assert.assertTrue(new ErrorScreen(driver).validateErrorMessage("{name=must not be blank}",5));
    }
}
