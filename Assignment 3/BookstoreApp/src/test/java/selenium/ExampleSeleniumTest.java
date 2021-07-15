package selenium;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.jetty.websocket.common.WebSocketFrame;
import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

class ExampleSeleniumTest {

  static Process server;
  private WebDriver driver;

  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "bookstore5.jar").inheritIO();
    server = pb.start();
  }

  @BeforeEach
  void setUp() {
    // Pick your browser
    // driver = new FirefoxDriver();
    // driver = new SafariDriver();
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("http://localhost:8080/");
    // wait to make sure Selenium is done loading the page
    WebDriverWait wait = new WebDriverWait(driver, 60);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));
  }

  @AfterEach
  public void tearDown() {
    driver.close();
  }

  @AfterAll
  public static void tearDownAfterClass() throws Exception {
    server.destroy();
  }

  @Test
  void test1() {
    WebElement element = driver.findElement(By.id("title"));
    String expected = "YAMAZONE BookStore";
    String actual = element.getText();
    assertEquals(expected, actual);
  }

  @Test
  public void test2() {
    WebElement welcome = driver.findElement(By.cssSelector("p"));
    String expected = "Welcome";
    String actual = welcome.getText();
    assertEquals(expected, getWords(actual)[0]);
    WebElement langSelector = driver.findElement(By.id("locales"));
    langSelector.click();
    WebElement frSelector = driver.findElement(By.cssSelector("option:nth-child(3)"));
    frSelector.click();
    welcome = driver.findElement(By.cssSelector("p"));
    expected = "Bienvenu";
    actual = welcome.getText();
    assertEquals(expected, getWords(actual)[0]);
  }

  private String[] getWords(String s) {
    return s.split("\\s+");
  }

  private void loginAdmin(WebDriver testDriver) {
    driver.get("http://localhost:8080/login"); // switch to the login page - otherwise the ID's will not exist

    WebElement usernameInput = driver.findElement(By.id("loginId")); // fill-in the login text box
    usernameInput.sendKeys("admin");
    WebElement passwordInput = driver.findElement(By.id("loginPasswd")); // fill-in the password text box
    passwordInput.sendKeys("password");
    WebElement loginSelector = driver.findElement(By.id("loginBtn")); // submit the login request
    loginSelector.click();
  }

  @Test
  public void tc1_positive_admin_login_attempt() {
    loginAdmin(driver);
    WebElement welcome = driver.findElement(By.cssSelector("p"));
    String expected = "Welcome";
    String actual = welcome.getText();
    assertEquals(expected, getWords(actual)[0]); // on successful login, the user will be re-directed to the home page
                                                 // else they will stay on the same page where the <p> tag is "invalid"
  }

  @Test
  public void tc1_negative_admin_login_attempt() {
    driver.get("http://localhost:8080/login"); // switch to the login page - otherwise the ID's will not exist

    WebElement usernameInput = driver.findElement(By.id("loginId")); // fill-in the login text box
    usernameInput.sendKeys("admin");
    WebElement passwordInput = driver.findElement(By.id("loginPasswd")); // fill-in the password text box
    passwordInput.sendKeys("random_password");
    WebElement loginSelector = driver.findElement(By.id("loginBtn")); // submit the login request
    loginSelector.click();
    WebElement failed_login_message = driver.findElement(By.xpath("/html/body/div/div[3]/div"));
    String expected = "Invalid";
    String actual = failed_login_message.getText();
    assertEquals(expected, getWords(actual)[0]); // on unsuccessful login, the user will be given the "Invalid
                                                 // username.."
  }

  @Test
  public void tc2_positive_admin_logout_attempt() {
    loginAdmin(driver);
    driver.get("http://localhost:8080/admin/catalog"); // switch to the login page - otherwise the ID's will not exist

    WebElement logoutButton = driver.findElement(By.xpath("/html/body/div/div[2]/form[2]/input")); // clickable button
                                                                                                   // to logout of the
                                                                                                   // admin account
    logoutButton.click();

    WebElement logout_message = driver.findElement(By.xpath("/html/body/div/div[3]/div"));
    String expected = "You";
    String actual = logout_message.getText();
    assertEquals(expected, getWords(actual)[0]); // on successful logout, the user will be re-directed to the main page
                                                 // with the text "You have.."
  }

  @Test
  public void tc3_posi_customer_browse() throws InterruptedException {
    driver.get("http://localhost:8080/catalog");
    String expected1 = "We currently have the following items in category /'Sports/'";

    String actual1 = customer_browser_helper(driver, "Sports");
    assertEquals(expected1, actual1);

    // This part will now assert the clearing of the search
    String expected2 = "We currently have the following items in category ''";
    String actual2 = customer_browser_helper(driver, "");
    assertEquals(expected2, actual2);

  }

  // @Test
  // public void tc4_negi_customer_browse() {
  // String expected = "";
  // }

  private String customer_browser_helper(WebDriver driver, String keys) {

    // JavascriptExecutor js = (JavascriptExecutor) driver;

    // js.executeScript("document.getElementById('search').val = 'Sports'");
    WebElement categoryInput = driver.findElement(By.id("search"));
    categoryInput.sendKeys(keys);
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();
    WebElement searchResult = driver.findElement(By.xpath("/html/body/div/div[3]/h1"));
    String actual1 = searchResult.getText();
    return actual1;
  }

  @Test
  public void tc4_positive_admin_add_book() {
    loginAdmin(driver);
    driver.get("http://localhost:8080/admin"); // switch to the login page otherwise the ID's will not exist

    WebElement bookInputCategory = driver.findElement(By.id("addBook-category")); // enter the book category
    bookInputCategory.sendKeys("Sports");
    WebElement bookInputId = driver.findElement(By.id("addBook-id")); // enter the book id
    bookInputId.sendKeys("10001");
    WebElement bookInputTitle = driver.findElement(By.id("addBook-title")); // enter the book title
    bookInputTitle.sendKeys("Life of Michael Jordan");
    WebElement bookInputAuthor = driver.findElement(By.id("addBook-authors")); // enter the book author
    bookInputAuthor.sendKeys("Michael Jordan");
    WebElement bookInputCost = driver.findElement(By.id("cost")); // enter the book cost
    bookInputCost.sendKeys("15.00");
    // the only value left empty is the "description", since it is an optional field

    WebElement bookSubmitButton = driver.findElement(By.name("addBook"));
    bookSubmitButton.click(); // submit the book to the book store

    WebElement bookSucessfulSubmitMessage = driver.findElement(By.id("feedback"));
    String actual = bookSucessfulSubmitMessage.getText();

    String expected = "Successfully added book";
    assertEquals(expected, getWords(actual)[0]); // on unsuccessful login, the
  }

  @Test
  public void tc4_negative_admin_add_book() {
    loginAdmin(driver);
    driver.get("http://localhost:8080/admin"); // switch to the login page otherwise the ID's will not exist

    WebElement bookInputCategory = driver.findElement(By.id("addBook-category")); // enter the book category
    bookInputCategory.sendKeys("Sports");
    WebElement bookInputId = driver.findElement(By.id("addBook-id")); // enter the book id
    bookInputId.sendKeys("10002");
    WebElement bookInputCost = driver.findElement(By.id("cost")); // enter the book cost
    bookInputCost.sendKeys("15.02");
    // the other MANDETORY elements are left alone to cause a missing-fields error

    WebElement bookSubmitButton = driver.findElement(By.name("addBook"));
    bookSubmitButton.click(); // submit the book to the book store

    WebElement bookUnSucessfulSubmitMessage = driver.findElement(By.id("feedback"));
    String actual = bookUnSucessfulSubmitMessage.getText();

    String expected = "Validation";
    assertEquals(expected, getWords(actual)[0]); // on unsuccessful login, the
  }
}
