import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ParkingCalculator extends Driver {

    public static final By LOT_COMBO_BOX = By.id("Lot");
    public static final By ENTRY_TIME = By.id("EntryTime");
    public static final By LEAVING_TIME = By.id("ExitTime");
    public static final By ENTRY_DATE = By.id("EntryDate");
    public static final By LEAVING_DATE = By.id("ExitDate");
    public static final By ENTRY_RADIO_BUTTON_AM = By.xpath(".//*[@name='EntryTimeAMPM' and @value='AM']");
    public static final By ENTRY_RADIO_BUTTON_PM = By.xpath(".//*[@name='EntryTimeAMPM' and @value='PM']");
    public static final By LEAVING_RADIO_BUTTON_AM = By.xpath(".//*[@name='ExitTimeAMPM' and @value='AM']");
    public static final By LEAVING_RADIO_BUTTON_PM = By.xpath(".//*[@name='ExitTimeAMPM' and @value='PM']");
    public static final By CALCULATE_BUTTON = By.xpath(".//*[@name='Submit' and @value='Calculate']");
    public static final By DAYS_HOURS_MINUTES = By.cssSelector("span.BodyCopy b");
    public static final By MESSAGE = By.cssSelector("span.SubHead b");


    public void setEntrarTempo(String time, String ampm) throws Exception{
        setElement(ENTRY_TIME, time);
        if (ampm.equals("PM")) {
            selectEntrarHrRadioPm();
        } else {
            selectEntrarHrRadioAm();
        }
    }


    public void setSairTempo(String time, String ampm) throws Exception{
        setElement(LEAVING_TIME, time);
        if (ampm.equals("PM")) {
            selectSairHrRadioRadioPm();
        } else {
            selectSairHrRadioAm();
        }
    }

    public void setEntrarData(String date) throws Exception{
        setElement(ENTRY_DATE, date);
    }

    public void setSairData(String date) throws Exception{
        setElement(LEAVING_DATE, date);
    }

    public void selectEntrarHrRadioAm() throws Exception {
        WebElement element = driver.findElement(ENTRY_RADIO_BUTTON_AM);
        element.click();
    }

    public void selectEntrarHrRadioPm() throws Exception {
        WebElement element = driver.findElement(ENTRY_RADIO_BUTTON_PM);
        element.click();
    }


    public String getDaysHoursMinutes() throws Exception {
        WebElement element = driver.findElement(DAYS_HOURS_MINUTES);
        return element.getText().trim();
    }

    public String getMessage() throws Exception {
        WebElement element = driver.findElement(MESSAGE);
        return element.getText().trim();
    }

    public void selectSairHrRadioAm() throws Exception {
        WebElement element = driver.findElement(LEAVING_RADIO_BUTTON_AM);
        element.click();
    }

    public void selectSairHrRadioRadioPm() throws Exception {
        WebElement element = driver.findElement(LEAVING_RADIO_BUTTON_PM);
        element.click();
    }

    public void calculate() throws Exception {
        clickElement(CALCULATE_BUTTON);
    }

    public void clickElement (By elementId) throws Exception {
        WebElement element = driver.findElement(elementId);
        element.click();
    }

    public void setElement(By elementId, String text) throws Exception {
        WebElement element = driver.findElement(elementId);
        element.clear();
        element.sendKeys(text);
    }

    public void setLot(String value) throws Exception {
        WebElement combo = driver.findElement(LOT_COMBO_BOX);
        Select lot = new Select(combo);
        lot.selectByVisibleText(value);
    }

    public String dataAtual() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String dateFormatted = dateFormat.format(date);
        return dateFormatted;
    }

    public String dataFutura() throws Exception {
        DateFormat formatData = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date((new Date()).getTime() + (10 * 86400000));
        String dataFormatada = formatData.format(date);
        return dataFormatada;
    }

    public void calcularParking(String lot, String entryTime, String entryAmpm, String exitTime, String exitAmpm, String entryDate, String exitDate) throws Exception {
        setLot(lot);
        setEntrarTempo(entryTime, entryAmpm);
        setSairTempo(exitTime, exitAmpm);
        setEntrarData(entryDate);
        setSairData(exitDate);
        calculate();
    }


}