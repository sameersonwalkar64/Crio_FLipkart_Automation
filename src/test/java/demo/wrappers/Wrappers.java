package demo.wrappers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.EclipseInterface;


public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public void navigateToFlipkart(WebDriver driver)  {
        driver.get("https://www.flipkart.com/");
     }
     public void searchItem(String searchCriteria,WebDriver driver) throws InterruptedException  {
        driver.findElement(By.xpath("//input[@class='Pke_EE']")).sendKeys(searchCriteria);
        Thread.sleep(2000);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='_2iLD__']"))));
       driver.findElement(By.xpath("//button[@class='_2iLD__']")).click();
    //    try {
    //     Robot r= new Robot() ;
    //     r.keyPress(KeyEvent.VK_ENTER);
    //    }catch(Exception e){
    //     System.out.println(e);
    //    }
     }

     public void sortByPopularity(WebDriver driver)  {
        driver.findElement(By.xpath("//div[contains(text(),'Popularity')]")).click();
     }

     public int getCountRatings(WebDriver driver)  {
        List<WebElement> ratingsListWebElement=driver.findElements(By.xpath("//span[contains(@id,'productRating_')]//div"));

        List<String> ratingsList=new ArrayList<>();
        int count=0;
        for(WebElement e: ratingsListWebElement)  {
            ratingsList.add(e.getText());
            if(Double.parseDouble(e.getText())<=4.0)  {
                count++;
            }
        }
        return count;
     }

     public  void getIphoneTitles(WebDriver driver)  {
        LinkedHashMap<String,Integer> discountAndTitleMap=new LinkedHashMap<>();
        List<WebElement>titlElements=driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
        List<WebElement> discouElements=driver.findElements(By.xpath("//div[@class='UkUFwK']//span"));

        for(int i=0;i<discouElements.size();i++)  {
            String arr[]=discouElements.get(i).getText().split("%");
            discountAndTitleMap.put(titlElements.get(i).getText(), Integer.parseInt(arr[0]));
        }

        
        for(Map.Entry<String,Integer> entry:discountAndTitleMap.entrySet())  {
            if(entry.getValue()>17)  {
                System.out.println(entry.getKey());
            }
        }

     }

     public  void getHighestRatings(WebDriver driver) throws InterruptedException  {
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,250)");
        driver.findElement(By.xpath("//div[@class='_6i1qKy' and contains(text(),'4')]/preceding-sibling::div")).click();
        Thread.sleep(2000);
        List<WebElement>ratingsList=driver.findElements(By.xpath("//span[@class='Wphh3N']"));
        List<Long>ratings=new ArrayList<>();
       HashMap<String,String>titleAndImageURL=new HashMap<>();
        for(WebElement e:ratingsList)  {
          String s=e.getText().replace(",", "");
            s=s.replace("(", "");
            s=s.replace(")", "");
            ratings.add(Long.parseLong(s));
        }
        ratings.sort(Collections.reverseOrder());
        for(int i=0;i<5;i++)  {
            String s=ratings.toString();
            NumberFormat numberFormat=java.text.NumberFormat.getInstance(Locale.US);
            System.out.println(numberFormat.format(ratings.get(i)));
            String formattedRating=numberFormat.format(ratings.get(i));
            WebElement ratingElement=driver.findElement(By.xpath("//span[@class='Wphh3N' and contains(text(),"+"'"+formattedRating+"'"+")]/parent::div/preceding-sibling::a"));
            titleAndImageURL.put(ratingElement.getAttribute("title"), ratingElement.getAttribute("href"));
            System.out.println(ratingElement.getAttribute("title"));
            System.out.println(ratingElement.getAttribute("href"));

        }
       
     }

     public void closePopup(WebDriver driver)  {
        if(driver.findElement(By.xpath("//span[@class='_30XB9F']")).isDisplayed())  {
            driver.findElement(By.xpath("//span[@class='_30XB9F']")).click();
        }
     }

}
