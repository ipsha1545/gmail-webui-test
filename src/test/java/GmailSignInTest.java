import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailSignInTest {
    WebDriver driver = new FirefoxDriver();

    @Test
    public void gmailLoginShouldBeSuccessful(){
        //if you didn't update the Path system variable to add the full directory path to the executable as above mentioned then doing this directly through code
        System.setProperty("webdriver.gecko.driver", "geckodriver");

        //Now you can Initialize marionette driver to launch firefox
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        //WebDriver driver = new MarionetteDriver(capabilities);

        //1. Go to the Gmail website
        driver.get("http://gmail.com");

        //2.Fill in the username
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys("ipsha1545@gmail.com");

        //3.Fill in the password
        WebElement pwd = driver.findElement(By.id("Passwd"));
        pwd.clear();
        pwd.sendKeys("**********");

        //4.Click sign in
        WebElement signin = driver.findElement(By.id("signIn"));
        signin.click();
        Assert.assertTrue("Inbox should exist",driver.findElements(By.partialLinkText("Inbox")).size()>0);

        //5. Verify that the user signed in
        //Wait for some time and see that the user did sign in
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox should exist", driver.findElements(By.partialLinkText("Inbox")).size()>0);

        //6.sign out
        WebElement pb = driver.findElement(By.cssSelector("span[class='gb_da gbii']"));
        pb.click();

            WebElement sol = driver.findElement(By.id("gb_71"));
        sol.click();

        //7.Verify that the user did sign out after waiting for some time
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("signIn")));
        Assert.assertTrue("signIn button should exist", driver.findElements(By.partialLinkText("signIn")).size()>0);

    }

    @Test
    public void gmailSendAndReceiveEmailTest(){
        driver.get("http://gmail.com");

        //2.Fill in the username
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys("ipsha1545@gmail.com");

        //3.Fill in the password
        WebElement pwd = driver.findElement(By.id("Passwd"));
        pwd.clear();
        pwd.sendKeys("**********");

        //4.Click sign in
        WebElement signin = driver.findElement(By.id("signIn"));
        signin.click();
        Assert.assertTrue("Inbox should exist",driver.findElements(By.partialLinkText("Inbox")).size()>0);

        //5.Verify that the user signed in
        //Wait for some time and see that the user did sign in
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox should exist", driver.findElements(By.partialLinkText("Inbox")).size()>0);

        //6.Click compose
        WebElement compose = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        compose.click();

        //7.Fill in receipient
        WebElement toTextArea = driver.findElement(By.cssSelector("textarea[name='to']"));
        toTextArea.clear();
        toTextArea.sendKeys("ipsha1545@gmail.com");

        //8.Fill in the subject
        WebElement subjectArea = driver.findElement(By.cssSelector("textarea[name='to']"));
        final String subject = "Regarding testing of email";
        subjectArea.clear();
        subjectArea.sendKeys(subject);

        //9.Fill in the body of the email
        WebElement bodyArea = driver.findElement(By.cssSelector("textarea[name='to']"));
        final String body = "Regarding testing of email";
        bodyArea.clear();
        bodyArea.sendKeys(subject);

        //10.Click send
        WebElement send = driver.findElement(By.cssSelector("div[aria-label*=\"Send\"]"));
        send.click();

        //11.Click by clicking the new inbox
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Inbox (1)")));
        WebElement inboxlinkage = driver.findElement(By.linkText("Inbox (1)"));
        inboxlinkage.click();

        //12.Click email
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='y6'] span[id] b")));
        WebElement newemail = driver.findElement(By.cssSelector("div[class='y6'] span[id] b"));
        newemail.click();

        //13.Verify that the email subject and the email body is correct
        WebElement subjectA = driver.findElement(By.cssSelector("h2[class = 'hP']"));
        Assert.assertEquals("Email subject should be same",subject,subjectA.getText());

        WebElement bodyA = driver.findElement(By.cssSelector("div[class='nH aHU'] div[dir = 'ltr']"));
        Assert.assertEquals("Email subject should be same",subject,bodyA.getText());

        WebElement profilebutton = driver.findElement(By.cssSelector("span[class = 'gb_da' gbii']"));
        profilebutton.click();

        //14.Sign out
        WebElement signoutlinkage = driver.findElement(By.id("gb_71"));
        signoutlinkage.click();

    }
    
}
