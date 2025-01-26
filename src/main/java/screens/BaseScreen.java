package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BaseScreens
{
    AppiumDriver<AndroidElement> driver;

    public BaseScreens(AppiumDriver<AndroidElement> driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }


}
