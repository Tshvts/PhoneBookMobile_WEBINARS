package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.PanelUI;

public class BaseScreen
{
    AppiumDriver<AndroidElement> driver;

    public BaseScreen(AppiumDriver<AndroidElement> driver)
    {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void pause(int time)
    {
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean textInElementPresent(AndroidElement element, String text, int time)
    {
        try
        {
            return new WebDriverWait(driver,time).until(ExpectedConditions.textToBePresentInElement(element,text));
        }

        catch(org.openqa.selenium.TimeoutException e)
        {
            System.out.println("Created exception");
            return false;
        }
    }

    public void clickWait(AndroidElement element, int time)
    {
        try
        {
            new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(element)).click();
        }

        catch(org.openqa.selenium.TimeoutException e)
        {
            System.out.println("Created exception");
        }
    }
}
