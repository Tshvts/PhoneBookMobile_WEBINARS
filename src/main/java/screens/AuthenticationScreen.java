package screens;

import dto.UserDtoLombok;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class AuthenticationScreen extends BaseScreen
{

    public AuthenticationScreen(AppiumDriver<AndroidElement> driver)
    {
        super(driver);
    }

    @FindBy(id = "com.sheygam.contactapp:id/inputEmail")
    AndroidElement inputEmail;

    @FindBy(id = "com.sheygam.contactapp:id/inputPassword")
    AndroidElement inputPassword;

    @FindBy(id = "com.sheygam.contactapp:id/regBtn")
    AndroidElement btnReg;

    @FindBy(id = "com.sheygam.contactapp:id/loginBtn")
    AndroidElement btnLog;

    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView")
    AndroidElement textAuth;

    public void typeAuthenticationForm(UserDtoLombok userDtoLombok)
    {
        inputEmail.sendKeys(userDtoLombok.getUsername());
        inputPassword.sendKeys(userDtoLombok.getPassword());
    }

    public void clickBtnReg()
    {
        btnReg.click();
    }

    public void clickLog()
    {
        btnLog.click();
    }

    public boolean isAuthScreenOpen()
    {
        return textInElementPresent(textAuth,"Authentication", 3);
    }
}
