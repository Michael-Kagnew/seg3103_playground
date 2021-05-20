import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DateTest {

  @Test
  void nextDate_t1() {
    Date d = new Date(1700,6,20);
    assertEquals(new Date(1700,6,21), d.nextDate());
  }
  
  @Test
  void nextDate_t2() {
    Date d = new Date(2005,4,15);
    assertEquals(new Date(2005,4,16), d.nextDate());
  }
  
  @Test
  void nextDate_t3() {
    Date d = new Date(1901,7,20);
    assertEquals(new Date(1901,7,21), d.nextDate());
  }
  
  @Test
  void nextDate_t4() {
    Date d = new Date(3456,3,27);
    assertEquals(new Date(3456,3,28), d.nextDate());
  }
  
  @Test
  void nextDate_t5() {
    Date d = new Date(1500,2,17);
    assertEquals(new Date(1500,2,18), d.nextDate());
  }
  
  @Test
  void nextDate_t6() {
    Date d = new Date(1700,6,29);
    assertEquals(new Date(1700,6,30), d.nextDate());
  }
  
  @Test
  void nextDate_t7() {
    Date d = new Date(1800,11,29);
    assertEquals(new Date(1800,11,30), d.nextDate());
  }
  
  @Test
  void nextDate_t8() {
    Date d = new Date(3453,1,29);
    assertEquals(new Date(3453,1,30), d.nextDate());
  }
  
  @Test
  void nextDate_t9() {
    Date d = new Date(444,2,29);
    assertEquals(new Date(444,3,1), d.nextDate());
  }
  
  @Test
  void nextDate_t10() {
    Date d = new Date(2005,4,30);
    assertEquals(new Date(2005,5,1), d.nextDate());
  }
  
  @Test
  void nextDate_t11() {
    Date d = new Date(3453,1,30);
    assertEquals(new Date(3453,1,31), d.nextDate());
  }
  
  @Test
  void nextDate_t12() {
    Date d = new Date(3456,3,30);
    assertEquals(new Date(3456,3,31), d.nextDate());
  }
  
  @Test
  void nextDate_t13() {
    Date d = new Date(1901,7,31);
    assertEquals(new Date(1901,8,1), d.nextDate());
  }
  
  @Test
  void nextDate_t14() {
    Date d = new Date(3453,1,31);
    assertEquals(new Date(3453,2,1), d.nextDate());
  }
  
  @Test
  void nextDate_t15() {
    Date d = new Date(3456,12,31);
    assertEquals(new Date(3457,1,1), d.nextDate());
  }
  
  @Test
  void nextDate_t16() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      Date d = new Date(1500,2,31);
    });
    
    String expectedMessage = "day must less than 28 for month February on a non leap year.";
    String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  void nextDate_t17() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      Date d = new Date(1500,2,29);
    });
    
    String expectedMessage = "day must less than 28 for month February on a non leap year.";
    String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  void nextDate_t18() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      Date d = new Date(-1,10,20);
    });
    
    String expectedMessage = "year must be greater or equal to 0.";
    String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  void nextDate_t19() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      Date d = new Date(1458,15,12);
    });
    
    String expectedMessage = "month must be between 1 and 12.";
    String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }
  
  @Test
  void nextDate_t20() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      Date d = new Date(1975,6,-50);
    });
    
    String expectedMessage = "day must greater or equal to 1.";
    String actualMessage = exception.getMessage();
    
    assertTrue(actualMessage.contains(expectedMessage));
  }

}