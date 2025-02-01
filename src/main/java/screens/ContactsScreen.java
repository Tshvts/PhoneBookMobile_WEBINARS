package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class ContactsScreen extends BaseScreen
{

    public ContactsScreen(AppiumDriver<AndroidElement> driver)
    {
        super(driver);
    }

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")
    AndroidElement headerContactList;

    @FindBy(id = "com.sheygam.contactapp:id/add_contact_btn")
    AndroidElement btnAddContact;

    @FindBy(xpath = "hierarchy/android.widget.Toast")
    AndroidElement popUpMessage;

    public boolean validateHeader()
    {
        return textInElementPresent(headerContactList,"Contact list",5);
    }

    public void clickBtnAddContact()
    {
        clickWait(btnAddContact,5);
    }

    public boolean validatePopUpMessage(String text, int time)
    {
        return textInElementPresent(popUpMessage,text,time);
    }
}
