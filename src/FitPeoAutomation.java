import org.openqa.selenium.chrome.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FitPeoAutomation {
;

	public static void main(String[] args) throws InterruptedException {
        // Set up WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the FitPeo Homepage
            driver.get("https://www.fitpeo.com"); 
            driver.manage().window().maximize();
            Thread.sleep(3000);

            driver.findElement(By.xpath("//button[@aria-label=\"open drawer\"]//*[name()=\"svg\"]")).click();
            Thread.sleep(3000);

            // Navigate to the Revenue Calculator Page
            WebElement revenueCalculatorLink = driver.findElement(By.xpath("//span[normalize-space()=\"Revenue Calculator\"]")); // link text or locator
            revenueCalculatorLink.click();

            // Scroll down to the slider section
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement sliderSection = driver.findElement(By.cssSelector(".MuiSlider-rail.css-3ndvyc")); // Replace with the actual ID or locator
            js.executeScript("arguments[0].scrollIntoView(true);", sliderSection);

            Thread.sleep(2000);

            // Adjust the slider to 820
            WebElement slider = driver.findElement(By.cssSelector("span[data-index=\"0\"]")); // Replace with the actual ID or locator
            Actions actions = new Actions(driver);
            Thread.sleep(5000);

            actions.dragAndDropBy(slider,  93, 28).perform(); 
            Thread.sleep(3000);

            WebElement textField = driver.findElement(By.xpath("//*[@id=\":R57alklff9da:\"]")); // Replace with the actual ID or locator
            String sliderValue = null;

            try {
                sliderValue = textField.getAttribute("value");
            } catch (NullPointerException e) {
                System.out.println("Failed to get slider value. Element might not be present: " + e.getMessage());
            }

            if (sliderValue != null && !sliderValue.equals("816")) {
                System.out.println("Slider value is not updated to 816!");
            }

            // Update the text field to 560
            textField.click();
            textField.clear();
            textField.sendKeys("560");

            // Verify the updated slider value
            try {
                String updatedSliderValue = textField.getAttribute("value");
                if (!updatedSliderValue.equals("560")) {
                    System.out.println("Slider did not update to 560!");
                }
            } catch (NullPointerException e) {
                System.out.println("Failed to get updated slider value: " + e.getMessage());
            }

            // Scroll down and select CPT codes
            WebElement cptCheckbox99091 = driver.findElement(By.xpath("//div[@class='MuiBox-root css-rfiegf']//div[1]//label[1]//span[1]//input[1]")); //99091
            WebElement cptCheckbox99453 = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(3) > div:nth-child(4) > div:nth-child(2) > label:nth-child(4) > span:nth-child(1) > input:nth-child(1)"));//99453
            WebElement cptCheckbox99454 = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(3) > div:nth-child(4) > div:nth-child(3) > label:nth-child(4) > span:nth-child(1) > input:nth-child(1)"));//99454
            WebElement cptCheckbox99474 = driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(3) > div:nth-child(4) > div:nth-child(8) > label:nth-child(4) > span:nth-child(1) > input:nth-child(1)"));

            cptCheckbox99091.click();
            Thread.sleep(2000);
            cptCheckbox99453.click();
            Thread.sleep(2000);
            cptCheckbox99454.click();
            Thread.sleep(2000);
            cptCheckbox99474.click();
            
            Thread.sleep(5000);

            // Validate Total Recurring Reimbursement
            WebElement reimbursementHeader = driver.findElement(By.id("reimbursementHeaderId")); // Replace with the actual ID or locator
            String reimbursementText = reimbursementHeader.getText();
            if (!reimbursementText.contains("$110700")) {
                System.out.println("Total Recurring Reimbursement value is incorrect!");
            } else {
                System.out.println("Test passed: Total Recurring Reimbursement is correct.");
            }

        } catch (Exception e) {
      
      e.printStackTrace();
            
      WebElement reimbursementHeader = driver.findElement(By.cssSelector(".MuiBox-root.css-19zjbfs"));
      
      Thread.sleep(5000); 
            
      String reimbursementText = reimbursementHeader.getText();
      
      
      if (reimbursementText.contains("$110700")) { System.out.println("Test passed: Total Recurring Reimbursement is correct."); } 
      else { 
    	  System.out.println("Total Recurring Reimbursement value is incorrect!");
    	  }
            
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

