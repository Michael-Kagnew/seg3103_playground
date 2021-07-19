# Lab 7

| Outline | Value |
| --- | --- |
| Course | SEG 3103 |
| Date | Summer 2021 |
| Name | Gabe Cordovado (300110852) and Michael Kagnew(300113347)  |
| Professor | Andrew Forward, aforward@uottawa.ca |
| TA | Nazanin Bayati Chaleshtari, n.bayati@uottawa.ca |

Repo link https://github.com/Michael-Kagnew/seg3103_playground

## Issue 1
CalCFrame.java - line 373

#### Description
The developer is attempting to make a comparison between two strings using the default equivalence (==) operator, instead of the String.compare() function for the type.

#### How to fix
Currently line 373 is performing the action:

``` s == "" ```

to utilize the built-in compare function within java.string, we need to use the String.compare() function which will return a boolean representation of the operation. Hence, we can change the code too:

``` s.compareTo("") ```

---

## Issue 2
Main.java - line 15

#### Description
By calling the .setVisible(true) function, Swing JFrame creates an event-dispatcher thread in the background which can attempt to communicate with the listener-events hooked into the main Java Swing code. The issue arises if the event dispatcher were to communicate to the Swing JFrame before being fully-initialize "packed", resulting in the dispatcher thread "waiting" for these listeners (resources) indefinitely causing a dead-lock.

#### How to Fix
We simply need to call a pack-function on the Swing JFrame to ensure the event-listeners from the developers code are fully-set before we attempt to run the dispatcher and make calls to resource. Hence, we need to add the following line of code before calling "frame.setVisible(true)":

``` frame.pack() ```

---

## Issue 3/4
CalcCFrame.java - line 53

#### Description
On line 53, a class is being created as an inner-instance of another but this inner/nested class does not make use of any variables/fields of the calling-class, hence, it is just making the calling-class larger in size and possibly prologuing the inner-classes lifetime longer than needed because it will wait until the calling-class is killed.

#### How to Fix
No Clue

---

## Issue 5/6
CalFrame.java - line 272 and line 360

### Description
The developer has used an inefficient way of parsing a string to float value.

## How to Fix
Currently both lines 272 and 360 are performing the action:

``` Double.valueOf(input).doubleValue(); ```

which is a less efficient way to convert a string value to a double, with respect to

``` Double.parseDouble(input) ```

Hence, by using this instead, we can speed up the program.

---

## Issue 7
CalCFrame.java - line 81 and line 83

### Description
The developer has placed duplicate statements within the "else-if" and "else" branches resulting in an unecissary logic check of "i > 13 && i <= 17" when it can just be an else block.

### How to Fix

currently line 81 and line 83 contain the statements:

``` getContentPane().add( buttons[i] ); ```

representing that line 80 which contains the conditional check:

``` else if ( i >= 13 && i <= 17 ) ```

is causing the CPU to run an unnecessary logical check rather than executing a statement that whether true or false, will be executed. Hence, we can replace line 80 containing the logical check with an else block, allowing us to remove lines 82,33 (the prev. else block and repeated statement). As a result, reducing the logical complexity of the application.

---

## Issue 8
CalCFrame.java - line 329 to line 346

### Description
The switch statement that spans lines 329 to 346 contains no default statement, making the Java compiler believe that the variables 'answer' has the possibility of not being defined, despite answer being defined to a default value of 0.0.

### How to Fix
We can add a default case at the end of the switch statement that sets the answer to value 0, allowing use to leave the declaration of 'answer' on line 327 but remove the definition.

``` default:
        answer = 0.0;
        break; 
```

---

## Issue 9
CalcFrame.java - line 76

### Description
Preceding this logical statement at line 76, we check if i <=2, hence, another check of i >= 3 is unnecessary as it duplicates our logical expression checking i <=2. Hence, we can change:

``` if (i >= 3 && i <= 7) { ``` 

to

``` if (i <= 7) { ```

allowing us to reduce the complexity of the logical check.

---

## Issue 10
CalcFrame.java - line 78

### Description
Preceding this logical statement at line 78, we check if i <= 7, hence, another check of i >= 8 is unnecessary as it duplicates our logical expression checking i <= 7. Hence, we can change:

``` if (i >= 8 && i <= 12) { ``` 

to

``` if (i <= 12) { ```

allowing us to reduce the complexity of the logical check.

---

## Issue 11
CalcFrame.java - line 80

### Description
Preceding this logical statement at line 80, we check if i <= 12, hence, another check of i >= 13 is unnecessary as it duplicates our logical expression checking i <= 12. Hence, we can change:

``` if (i >= 13 && i <= 17) { ``` 

to

``` if (i <= 17) { ```

allowing us to reduce the complexity of the logical check.

---

## Issue 12
CalcCFrame - line 304

### Description
There is a useless boolean check being performed on the "else if" block. The logical condition is useless because the preceding if-block checks the boolean value of the same variable 'morenums'.

### How to Fix
We can simply change the else-if block, to an else-block given the boolean-variable we are using as the logical-condition will already be assessed in the if-block before. Allowing us to assume that the negated value (with respect to the first desired if) should always enter this block (given the first if-block cannot be entered). Hence, we can change:

``` else if (morenums) { ```

to..

``` else { ```

--- 

## Issue 13
CalcFrameTest - line 16-32

### Description
This is a private function that is never called within this called at any point, and is simply redundent. 

---

## Issue 14/15/16/17/18/19

### Description
Generally, when the use of the `final` is in a class for a variable, there should be consideration in making it `static` as well, especially since that is the convention since there would not be much need for an instance object for a non-changing field.

---

