package selenium;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.jetty.websocket.common.WebSocketFrame;
import org.junit.jupiter.api.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
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
    driver.manage().deleteAllCookies(); // hopefully remove any stored data from past sessions

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

  
  private String customerBrowseHelper(WebDriver driver, String category)  {

    WebElement categoryInput = driver.findElement(By.id("search"));
    categoryInput.clear();
    categoryInput.sendKeys(category);
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();
    WebElement searchResult = driver.findElement(By.xpath("/html/body/div/div[3]/h1"));
    String actual1 = searchResult.getText();
    return actual1;
  }

  private void addToCart(WebDriver driver, String order_button_id) throws NoSuchElementException{

    customerBrowseHelper(driver, "");
    WebElement orderButton = driver.findElement(By.id(order_button_id ));
    orderButton.click();
    
  }
  private void adminAddBook(String title, String category, String id, String[] authors,  String cost ){
    loginAdmin(driver);
    driver.get("http://localhost:8080/admin"); // switch to the login page otherwise the ID's will not exist
    WebElement bookInputCategory = driver.findElement(By.id("addBook-category")); // enter the book category
    bookInputCategory.sendKeys(category);
    WebElement bookInputId = driver.findElement(By.id("addBook-id")); // enter the book id
    bookInputId.sendKeys(id);
    WebElement bookInputTitle = driver.findElement(By.id("addBook-title")); // enter the book title
    bookInputTitle.sendKeys(title);
    WebElement bookInputAuthor = driver.findElement(By.id("addBook-authors")); // enter the book author
    bookInputAuthor.sendKeys(authors);
    WebElement bookInputCost = driver.findElement(By.id("cost")); // enter the book cost
    bookInputCost.sendKeys(cost);
    // the only value left empty is the "description", since it is an optional field

    WebElement bookSubmitButton = driver.findElement(By.name("addBook"));
    bookSubmitButton.click(); // submit the book to the book store
  }

  private void gotoOrderPage(WebDriver driver) {
    //customerBrowseHelper(driver, ""); //navigate to the catalog page with no search requirments
    
    WebElement addToCartButton = driver.findElement(By.id("cartLink")); // click the book's add-to-cart button
    addToCartButton.click();
  }
  
  //Actual tests

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
  public void f1_posi_admin_add_book(){
    loginAdmin(driver);
    driver.get("http://localhost:8080/admin"); // switch to the login page otherwise the ID's will not exist

    WebElement bookInputCategory = driver.findElement(By.id("addBook-category")); // enter the book category
    bookInputCategory.sendKeys("Sports");
    WebElement bookInputId = driver.findElement(By.id("addBook-id")); // enter the book id
    bookInputId.sendKeys("1001054");
    WebElement bookInputTitle = driver.findElement(By.id("addBook-title")); // enter the book title
    bookInputTitle.sendKeys("Sports Illustrated");
    WebElement bookInputAuthor = driver.findElement(By.id("addBook-authors")); // enter the book author
    bookInputAuthor.sendKeys("Michael");
    WebElement bookInputCost = driver.findElement(By.id("cost")); // enter the book cost
    bookInputCost.sendKeys("5.10");
    // the only value left empty is the "description", since it is an optional field

    WebElement bookSubmitButton = driver.findElement(By.name("addBook"));
    bookSubmitButton.click(); // submit the book to the book store

    WebElement bookSucessfulSubmitMessage = driver.findElement(By.id("feedback"));
    String actual = bookSucessfulSubmitMessage.getText();

    String expected = "Successfully added book";
    assertEquals(expected, actual); // on unsuccessful login, the
  }


  @Test
  public void f1_negi_admin_add_book(){
    loginAdmin(driver);
    driver.get("http://localhost:8080/admin"); // switch to the login page otherwise the ID's will not exist

    WebElement bookInputCategory = driver.findElement(By.id("addBook-category")); // enter the book category
    bookInputCategory.sendKeys("Sports");
    WebElement bookInputId = driver.findElement(By.id("addBook-id")); // enter the book id
    bookInputId.sendKeys("10011");
    // the other MANDETORY elements are left alone to cause a missing-fields error

    WebElement bookSubmitButton = driver.findElement(By.name("addBook"));
    bookSubmitButton.click(); // submit the book to the book store

    WebElement bookUnSucessfulSubmitMessage = driver.findElement(By.xpath("//*[@id='feedback']/h2"));
    String actual = bookUnSucessfulSubmitMessage.getText();

    String expected = "Validation errors";
    assertEquals(expected, actual ); // on unsuccessful login, the
  }

  @Test
  public void f2_posi_customer_browse() throws InterruptedException {
    driver.get("http://localhost:8080/");
    String expected1 = "We currently have the following items in category 'Sports'";

    adminAddBook("f2Book", "Sports", "101013", new String[] {"Bouf", "test"},"1.22"); //Before test, needs to be done

    String actual1 = customerBrowseHelper(driver, "Sports");
    assertEquals(expected1, actual1);

    // This part will now assert the clearing of the search
    String expected2 = "We currently have the following items in category ''";
    String actual2 = customerBrowseHelper(driver, "");
    assertEquals(expected2, actual2);

  }

  @Test
  public void f2_negi_customer_browse() {
    driver.get("http://localhost:8080/");

    String expected = "Sorry we do not have any item matching category 'abc12a' at this moment";

    String actual = customerBrowseHelper(driver, "abc12a");
    assertEquals(expected, actual);
  }


    @Test
    public void f3_posi_customer_add_book_order(){
      Boolean expected = true;
      driver.get("http://localhost:8080/");
      addToCart(driver, "order-hall002");
      driver.get("http://localhost:8080/orderPage");
      Boolean actual = !driver.findElements(By.xpath("//*[contains(text(),'hall002')]")).isEmpty(); //Cheeck if the this book id exists

      assertEquals(expected, actual);
      
    }

    @Test
    public void f3_negi_customer_add_book_order(){
      
      driver.get("http://localhost:8080/");
      boolean expected = true;
      boolean actual = false;
      try {
        addToCart(driver, "order-hall0");
      } catch(Exception e) {
        actual = true;
      }
      assertEquals(expected, actual);
      driver.get("http://localhost:8080/orderPage");
      
        
    }

    @Test
    public void f4_posi_customer_view_order(){
      String bookIdExpectd = "hall002";
      String descExpected = "Core Web Programming, 2nd Edition";
      String costExpectd = "$49.99";
      String numCopiesExpected = "1";
      String totalCostExpected = "$49.99";
      driver.get("http://localhost:8080/");
      addToCart(driver, "order-hall002");
      driver.get("http://localhost:8080/orderPage");
      String bookIdActual = driver.findElement(By.xpath("//*[contains(text(),'hall002')]")).getText();
      String descActual = driver.findElement(By.xpath("//*[contains(text(),'Core Web Programming, 2nd Edition')]")).getText(); 
      String costActual = driver.findElement(By.xpath("//*[contains(text(),'$49.99')]")).getText();
      String numCopiesActual = driver.findElement(By.id("hall002")).getAttribute("value");
      String totalCostActual = driver.findElement(By.id("tothall002")).getText();

      assertEquals(bookIdExpectd, bookIdActual);
      assertEquals(descExpected, descActual);
      assertEquals(costExpectd, costActual);
      assertEquals(numCopiesExpected, numCopiesActual);
      assertEquals(totalCostExpected, totalCostActual);
    }

    @Test
    public void f4_negi_customer_view_order(){
      driver.get("http://localhost:8080/");
      customerBrowseHelper(driver, "");
      driver.get("http://localhost:8080/orderPage");
      boolean actual = false;
      boolean expected = true;
      try{
        driver.findElement(By.id("hall002"));
        } catch(Exception e){
          actual = true;
        }

        assertEquals(expected, actual);
    }

    @Test 
    public void f5_posi_customer_update_copies(){
      String totalCostExpected = "$99.98";
      driver.get("http://localhost:8080/");
      addToCart(driver,  "order-hall002");
      driver.get("http://localhost:8080/orderPage");
      WebElement copiesInput  = driver.findElement(By.id("hall002"));

      copiesInput.clear();
      copiesInput.sendKeys("2");
      WebElement updateBtn = driver.findElement(By.name("updateOrder"));
      updateBtn.click();
      String totalCostActual = driver.findElement(By.id("tothall002")).getText();

      assertEquals(totalCostExpected, totalCostActual);
    }

    @Test
    public void f5_negi_customer_update_copies(){
      String totalCostExpected = "$49.99";

      driver.get("http://localhost:8080/");
      addToCart(driver,  "order-hall002");
      driver.get("http://localhost:8080/orderPage");
      WebElement copiesInput  = driver.findElement(By.id("hall002"));
      WebElement updateBtn = driver.findElement(By.name("updateOrder"));

      copiesInput.clear();
      copiesInput.sendKeys("1"); //Reset from any other test case, so only dealing with one copy of book now before sending update
      updateBtn.click();

      copiesInput.sendKeys("a");
      updateBtn.click();
      String totalCostActual = driver.findElement(By.id("tothall002")).getText();

      assertEquals(totalCostExpected, totalCostActual);
    }

    @Test 
    public void f6_posi_present_on_checkout(){
      String dateExpected = new SimpleDateFormat("yyyy/MM/dd").format(new Date()).toString();
      String totalExpected = "$68.99";
      String totalTaxesExpected ="$" + String.format("%.2f",( Math.round(49.99*0.13*100.0)/100.0));

      String totalShippingExpected = "$" + String.format("%.2f", (Math.round((49.99 * 0.05 + 10) *100.0)/100.0));
      

      driver.get("http://localhost:8080/");
      addToCart(driver,  "order-hall002");
      driver.get("http://localhost:8080/orderPage");
      WebElement copiesInput  = driver.findElement(By.id("hall002"));
      WebElement updateBtn = driver.findElement(By.name("updateOrder"));
      WebElement checkoutBtn = driver.findElement(By.name("checkout"));

      copiesInput.clear();
      copiesInput.sendKeys("1"); //Reset from any other test case, so only dealing with one copy of book now before sending update
      updateBtn.click();
      checkoutBtn.click();
      
      String dateActual = driver.findElement(By.xpath("//*[@id='order_date']/b")).getText();
      String totalActual = driver.findElement(By.id("order_total")).getText();
      String totalTaxesActual = driver.findElement(By.id("order_taxes")).getText();
      String totalShippingActual = driver.findElement(By.id("order_shipping")).getText();

      assertEquals(dateExpected, dateActual);
      assertEquals(totalExpected, totalActual);
      assertEquals(totalTaxesExpected, totalTaxesActual);
      assertEquals(totalShippingExpected, totalShippingActual);
    }

    @Test 
    public void f6_negi_present_on_checkout(){
      boolean expected = false;

      driver.get("http://localhost:8080/");
      customerBrowseHelper(driver, "");
      gotoOrderPage(driver);

      boolean actual = driver.findElements(By.xpath("//*[@id='checkoutTable']/tbody/tr/td[2]/p[1]/span[1]")).isEmpty();
      assertEquals(expected, actual);

    }

    public void f7_posi_remove_book(){
      driver.get("http://localhost:8080/");
      loginAdmin(driver);
      driver.get("http://localhost:8080/admin/catalog");
    
      WebElement deleteBtn = driver.findElement(By.id("del-lewis001"));
      deleteBtn.click();

      //driver.navigate().refresh();
      driver.get("http://localhost:8080/admin/catalog");

      boolean actual = driver.findElements(By.id("del-lewis001")).isEmpty();
      assertEquals(false, actual);
    }

  @Test
  public void f7_negi_remove_book(){
    loginAdmin(driver);
    driver.get("http://localhost:8080/admin/catalog");
    boolean expected = true;
    boolean actual = false;
    try{
      WebElement deleteBtn = driver.findElement(By.id("del-rowling1"));
    } catch(Exception e){
      actual = true;
    } 
    assertEquals(expected, actual);
   }

   @Test
   public void f8_posi_authenticate_admin(){
    String expectedURL = "http://localhost:8080/admin/";

    loginAdmin(driver);
    driver.get("http://localhost:8080/admin/");
    String actualURL = driver.getCurrentUrl();
    assertEquals(expectedURL, actualURL);
   }

   @Test
   public void f8_negi_authenticate_admin(){
    driver.get("http://localhost:8080/login"); // switch to the login page - otherwise the ID's will not exist

    String expected = "Invalid username and/or password";
    WebElement usernameInput = driver.findElement(By.id("loginId")); // fill-in the login text box
    usernameInput.sendKeys("gabe");
    WebElement passwordInput = driver.findElement(By.id("loginPasswd")); // fill-in the password text box
    passwordInput.sendKeys("king");
    WebElement loginSelector = driver.findElement(By.id("loginBtn")); // submit the login request
    loginSelector.click();
    
    String actual = driver.findElement(By.xpath("/html/body/div/div[3]/div")).getText();

    assertEquals(expected, actual);
   }

  @Test
  public void tc4_admin_add_book() {
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
    assertEquals(expected, actual); // on unsuccessful login, the
  }

  @Test
  public void tc5_missing_book_field_when_adding() {
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

    WebElement bookUnSucessfulSubmitMessage = driver.findElement(By.xpath("//*[@id='feedback']/h2"));
    String actual = bookUnSucessfulSubmitMessage.getText();

    String expected = "Validation";
    assertEquals(expected, getWords(actual)[0]); // on unsuccessful login, the
  }

  @Test
  public void tc6_duplicate_book_entry() {
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

    WebElement bookDuplicateSubmitMessage = driver.findElement(By.id("feedback"));
    String actual = bookDuplicateSubmitMessage.getText();

    String expected = "Book";
    assertEquals(expected, getWords(actual)[0]); // on unsuccessful login, the
  }

  @Test
  public void tc7_browse_store_sports_catalogue() {
    WebElement categoryInput = driver.findElement(By.id("search"));
    categoryInput.clear();
    categoryInput.sendKeys("horror");
    
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();

    WebElement bookDuplicateSubmitMessage = driver.findElement(By.xpath("/html/body/div/div[3]/h1"));
    String actual = bookDuplicateSubmitMessage.getText();

    String expected = "Sorry"; 
    assertEquals(expected, getWords(actual)[0]); // we should get a message confirming there is a category item
  }

  @Test
  public void tc8_browse_store_empty_catalogue() {
    WebElement categoryInput = driver.findElement(By.id("search"));
    categoryInput.clear();
    
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();

    WebElement bookDuplicateSubmitMessage = driver.findElement(By.xpath("/html/body/div/div[3]/h1"));
    String actual = bookDuplicateSubmitMessage.getText();

    String expected = "We";
    assertEquals(expected, getWords(actual)[0]); // we should get a message confirming our GENERIC search
  }

  @Test
  public void tc9_browse_store_notfound_catalogue() {    
    WebElement categoryInput = driver.findElement(By.id("search"));
    categoryInput.clear();
    categoryInput.sendKeys("football manga");
    
    WebElement searchBtn = driver.findElement(By.id("searchBtn"));
    searchBtn.click();

    WebElement bookDuplicateSubmitMessage = driver.findElement(By.xpath("/html/body/div/div[3]/h1"));
    String actual = bookDuplicateSubmitMessage.getText();

    String expected = "Sorry";
    assertEquals(expected, getWords(actual)[0]); // we should get a message confirming there is a category item
  }

  @Test
  public void tc10_admin_deletes_book() {
    String[] authors = {"Samuel Beckett"};
    adminAddBook("Waiting for Godot", "Play", "10203", authors, "16.00"); // we will use this as a reference
    driver.get("http://localhost:8080/admin/catalog");

    WebElement bookDeleteButton = driver.findElement(By.id("del-10203"));
    bookDeleteButton.click();

    driver.navigate().refresh(); // just in case the page breaks

    boolean actual = driver.findElements(By.id("del-10203")).isEmpty(); // the book title should be gone from the table
    assertEquals(true, actual);
  }

  @Test
  public void tc11_customer_add_book_to_order() {
    String[] authors = {"Samuel Beckett"};
    adminAddBook("Waiting for Godot", "Play", "10203", authors, "16.00"); // we will use this as a reference
    
    driver.get("http://localhost:8080/");
    customerBrowseHelper(driver, "Play"); //navigate to the catalog page with no search requirments
    addToCart(driver, "order-10203");
    gotoOrderPage(driver);
    
    WebElement addedBookIDText = driver.findElement(By.xpath("//*[contains(text(),'10203')]")); 
    String actual = addedBookIDText.getText();

    String expected = "10203";
    assertEquals(expected, getWords(actual)[0]); // the value we find from the xpath should match the first entry of the order page
  }
  
  @Test
  public void tc12_customer_duplicate_add_order_increments() {
    String[] authors = {"Samuel Beckett"};
    adminAddBook("Waiting for Godot", "Play", "10203", authors, "16.00"); // we will use this as a reference 
    
    driver.get("http://localhost:8080/");
    //customerBrowseHelper(driver, "Play"); //navigate to the catalog page with no search requirments
    addToCart(driver, "order-10203");
    addToCart(driver, "order-10203");
    gotoOrderPage(driver);

    WebElement addedBookIDText = driver.findElement(By.id("10203"));
    String actual = addedBookIDText.getAttribute("value").toString();

    String expected = "2";
    assertEquals(expected, actual); // the book we already added in tc11 should have resulted in the 
                                                 // second attempt incrementing the number of books beign ordered
  }

  @Test
  public void tc13_view_book_order() {
    driver.get("http://localhost:8080/");
    customerBrowseHelper(driver, ""); //navigate to the catalog page with no search requirments
    
    WebElement addToCartButton = driver.findElement(By.id("cartLink")); // click the book's add-to-cart button
    addToCartButton.click();

    String actual = driver.getCurrentUrl();
    String expected = "http://localhost:8080/orderPage";
    assertEquals(expected, actual);
  }

  @Test
  public void tc14_increment_book_order() {
    String[] authors = {"Samuel Beckett"};
    adminAddBook("Waiting for Godot", "Play", "10203", authors, "16.00"); // we will use this as a reference
    driver.get("http://localhost:8080/");
    customerBrowseHelper(driver, "Play"); //navigate to the catalog page with no search requirments
    addToCart(driver, "order-10203");
    gotoOrderPage(driver);

    WebElement bookOrderCountTextBox = driver.findElement(By.name("numItems"));
    bookOrderCountTextBox.clear();
    bookOrderCountTextBox.sendKeys("3");

    WebElement bookOrderCountButton = driver.findElement(By.name("updateOrder"));
    bookOrderCountButton.click();

    WebElement bookOrderCost = driver.findElement(By.id("tot10203"));
    Float totalBookCost = Float.parseFloat(bookOrderCost.getText().substring(1));

    float expected = 48.f; // the books cost (per unit), multiplied by 3 (the number of books we set to be in the order)
    assertEquals(expected, totalBookCost);
  }

  @Test
  public void tc15_remove_book_through_copies_less_than_zero() {
    String[] authors = {"Samuel Beckett"};
    adminAddBook("Waiting for Godot", "Play", "10203", authors, "16.00"); // we will use this as a reference
    driver.get("http://localhost:8080/");
    //customerBrowseHelper(driver, "Play"); //navigate to the catalog page with no search requirments
    addToCart(driver, "order-10203");
    gotoOrderPage(driver);

    WebElement bookOrderCountTextBox = driver.findElement(By.id("10203"));
    bookOrderCountTextBox.clear();
    bookOrderCountTextBox.sendKeys("-1");

    WebElement bookOrderCountButton = driver.findElement(By.xpath("//*[contains(text(),'Update')]")) ;
    bookOrderCountButton.click();

    driver.navigate().refresh();

    boolean actual = driver.findElements(By.id("tot10203")).isEmpty();
    assertEquals(true, actual); 
  }

  @Test
  public void tc16_proceed_to_checkout() {
    driver.get("http://localhost:8080/orderPage"); // navigate to the orderPage where the book should now be

    WebElement proceedToCheckoutButton = driver.findElement(By.name("checkout"));
    proceedToCheckoutButton.click();

    String actual = driver.getCurrentUrl();
    String expected = "http://localhost:8080/checkout";
    assertEquals(expected, actual);
  }

  @Test
  public void tc17_switch_language_french() {
    // the same as tc1 - at the top of the page
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
}
