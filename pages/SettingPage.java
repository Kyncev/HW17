package pages;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class SettingPage {
By tempID = By.id("com.weather.forecast.weatherchannel:id/tgTemperature_setting");
By titleSpeed = By.id("com.weather.forecast.weatherchannel:id/md_title");
By speedD = By.id("com.weather.forecast.weatherchannel:id/iv_arrow_distance");
By speedFormat = By.id("com.weather.forecast.weatherchannel:id/tv_wind_speed_format");
By timeFormat = By.id("com.weather.forecast.weatherchannel:id/tg_format_time_setting");
By notificationFormat = By.id("com.weather.forecast.weatherchannel:id/tgNotifi_settings");
By statusBarFormat = By.id("com.weather.forecast.weatherchannel:id/tg_notifi_second_settings");
By doneFormat = By.id("com.weather.forecast.weatherchannel:id/tvDone");
public SettingPage(AndroidDriver driver){
    this.driver = driver;
}
private static AndroidDriver driver;

public void changeTemp(char temp){
    MobileElement element = (MobileElement) driver.findElement(tempID);
    switch (temp) {
        case 'f':{
            if (element.getAttribute("checked").equals("false")){//false=cel
                element.click();
            }
        }
        break;
        case 'c':{
            if (element.getAttribute("checked").equals("true")){//true=cel
                element.click();
        }
    }
    break;
    }
}
    public void changeTime(String time){
        MobileElement element = (MobileElement) driver.findElement(timeFormat);
        switch (time) {
            case "24":{
                if (element.getAttribute("checked").equals("false")){//false=24
                    element.click();
                }
            }
            break;
            case "12":{
                if (element.getAttribute("checked").equals("true")){//true=24
                    element.click();
                }
            }
            break;
        }
    }
    public void changeSpeed(SPEED speed) {
        if (!driver.findElement(speedFormat).getText().equals(speed.speed)) {
            driver.findElement(speedD).click();
            MobileElement button = (MobileElement) new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("com.weather.forecast.weatherchannel:id/md_contentRecyclerView")));
            List<MobileElement> list = button.findElements(By.className("android.widget.LinearLayout"));
            for (MobileElement l : list) {

                if (l.findElement(By.className("android.widget.TextView")).getText().equals(speed.speed)) {
                    l.click();
                    break;
                }
            }
        }
    }

    public void changeNotification() {
        MobileElement element = (MobileElement) driver.findElement(notificationFormat);
        driver.findElement(notificationFormat).click();
    }

    public void changeStatusBar() {
        MobileElement element = (MobileElement) driver.findElement(statusBarFormat);
        driver.findElement(statusBarFormat).click();
    }

    public void changeDone() {
        MobileElement element = (MobileElement) driver.findElement(doneFormat);
        driver.findElement(doneFormat).click();

    }


    public enum SPEED {
        METRES("m/s"),
        KILOMETRES("km/h");
        String speed;
        SPEED(String speed) {
            this.speed = speed;
        }
    }
}


