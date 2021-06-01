import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTesting {

    WebDriver driver;
    WebElement element;
    String userName = "tomsmith";
    String userPassword = "SuperSecretPassword!";
    String invalidUsername = "kkbkrb";
    String invalidPassword = "!password";

    //--Positive testing

    //1. Open the browser (Safari, Chrome, Edge)
    //2. Navigate to the login page
    //3. Type the username
    //4. Type the password
    //5. Tap login button
    //6. Verify the logged in page

    @Test
    public void testSafariBrowser() {
        openSafariBrowser();
        navigateLoginPage();
        typeUsername(userName);
        typePassword(userPassword);
        pushLoginButton();
        verifyLoggedPage();
        quitBrowser();
    }

    @Test
    public void testChromeBrowser() {
        openChromeBrowser();
        navigateLoginPage();
        typeUsername(userName);
        typePassword(userPassword);
        pushLoginButton();
        verifyLoggedPage();
        quitBrowser();
    }

    @Test
    public void testEdgeBrowser() {
        openEdgeBrowser();
        navigateLoginPage();
        typeUsername(userName);
        typePassword(userPassword);
        pushLoginButton();
        verifyLoggedPage();
        quitBrowser();
    }

    //--Negative testing - Invalid login

    //1. Open the browser (Safari, Chrome, Edge)
    //2. Navigate to the login page
    //3. Type invalid username
    //4. Type the password
    //5. Tap login button
    //6. Verify the username error page

    @Test
    public void testWrongUsernameSafari() {
        openSafariBrowser();
        navigateLoginPage();
        typeUsername(invalidUsername);
        typePassword(userPassword);
        pushLoginButton();
        verifyUsernameErrorPage();
        quitBrowser();
    }

    @Test
    public void testWrongUsernameChrome() {
        openChromeBrowser();
        navigateLoginPage();
        typeUsername(invalidUsername);
        typePassword(userPassword);
        pushLoginButton();
        verifyUsernameErrorPage();
        quitBrowser();
    }

    @Test
    public void testWrongUsernameEdge() {
        openEdgeBrowser();
        navigateLoginPage();
        typeUsername(invalidUsername);
        typePassword(userPassword);
        pushLoginButton();
        verifyUsernameErrorPage();
        quitBrowser();
    }

    //--Negative testing - Invalid password

    //1. Open the browser (Safari, Chrome, Edge)
    //2. Navigate to the login page
    //3. Type the username
    //4. Type invalid password
    //5. Tap login button
    //6. Verify the password error page

    @Test
    public void testWrongPasswordSafari() {
        openSafariBrowser();
        navigateLoginPage();
        typeUsername(userName);
        typePassword(invalidPassword);
        pushLoginButton();
        verifyPasswordErrorPage();
        quitBrowser();
    }

    @Test
    public void testWrongPasswordChrome() {
        openChromeBrowser();
        navigateLoginPage();
        typeUsername(userName);
        typePassword(invalidPassword);
        pushLoginButton();
        verifyPasswordErrorPage();
        quitBrowser();
    }

    @Test
    public void testWrongPasswordEdge() {
        openEdgeBrowser();
        navigateLoginPage();
        typeUsername(userName);
        typePassword(invalidPassword);
        pushLoginButton();
        verifyPasswordErrorPage();
        quitBrowser();
    }

    private void quitBrowser() {
        driver.quit();
    }

    private void verifyPasswordErrorPage() {
        element = driver.findElement(By.xpath("//div[contains(text(), " + "password" + ")]"));
        Assert.assertTrue(element.isDisplayed());
    }

    private void verifyUsernameErrorPage() {
        element = driver.findElement(By.xpath("//div[contains(text(), " + "username" + ")]"));
        Assert.assertTrue(element.isDisplayed());
    }

    private void verifyLoggedPage() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        String logoutButtonText = "Logout";
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(logoutButtonText)));
        element = driver.findElement(By.linkText(logoutButtonText));
        Assert.assertTrue(element.isDisplayed());
    }

    private void pushLoginButton() {
        String selector = "#login";
        element = driver.findElement(By.cssSelector(selector));
        element.submit();
    }

    private void typePassword(String userPassword) {
        String selector = "#password";
        element = driver.findElement(By.cssSelector(selector));
        element.sendKeys(userPassword);
    }

    private void typeUsername(String userName) {
        String selector = "#username";
        element = driver.findElement(By.cssSelector(selector));
        element.sendKeys(userName);
    }

    private void navigateLoginPage() {
        driver.get("https://the-internet.herokuapp.com/login");
        String selector = "#content";
        element = driver.findElement(By.cssSelector(selector));
        Assert.assertTrue(element.isDisplayed());
    }

    private void openSafariBrowser() {
        driver = new SafariDriver();
    }

    private void openChromeBrowser() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/mac64/chromedriver");
        driver = new ChromeDriver();
    }

    private void openEdgeBrowser() {
        System.setProperty("webdriver.edge.driver","src/test/resources/driver/mac64/msedgedriver");
        driver = new EdgeDriver();
    }

}
