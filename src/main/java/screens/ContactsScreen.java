package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
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
    @FindBy(id = "com.sheygam.contactapp:id/rowContainer")
    AndroidElement firstContact;
    @FindBy(id = "android:id/button1")
    AndroidElement btnYes;
    @FindBy(xpath = "//*[@text='Logout']")
    AndroidElement btnLogout;
    @FindBy(xpath = "//*[@text='Date picker']")
    AndroidElement btnDatePicker;
    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    AndroidElement btnMoreOptions;

    int yLeftUpCorner = firstContact.getLocation().getY();
    int heightElement = firstContact.getSize().getHeight();
    int widthElement = firstContact.getSize().getWidth();

    TouchAction<?> touchAction = new TouchAction<>(driver);

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

    public void goToEditScreen()
    {
    System.out.println("Y ==> " + firstContact.getLocation().getY());
    System.out.println("X ==> " + firstContact.getLocation().getX());
    System.out.println("H ==> " + firstContact.getSize().getHeight());
    System.out.println("W ==> " + firstContact.getSize().getWidth());

    touchAction.longPress(PointOption.point(widthElement/6*5, yLeftUpCorner+heightElement/2)).moveTo(PointOption.point(widthElement/6,yLeftUpCorner+heightElement/2)).release().perform();
     }

     public void deleteContact()
     {
        touchAction.longPress(PointOption.point(widthElement/6,yLeftUpCorner+heightElement/2)).moveTo(PointOption.point(widthElement/6*5,yLeftUpCorner+heightElement/2)).release().perform();
     }

     public void clickBtnYes()
     {
         clickWait(btnYes,5);
     }

     public void goToDatePicker()
     {
         btnMoreOptions.click();
         clickWait(btnDatePicker,3);
     }

     public void logout()
     {
         clickWait(btnMoreOptions,3);
         clickWait(btnLogout,3);
     }

}
