## Lab02
| Outline | Value |
| --- | --- |
| Course | SEG 3103 |
| Date | Summer 2021 |
| Name | Gabe Cordovado (300110852) and Michael Kagnew(300113347)  |
| Professor | Andrew Forward, aforward@uottawa.ca |
| TA | Henry Chen, zchen229@uottawa.ca |

## Exercise 1
Run the sample test cases created from the tutorial, and
report the results in the table below with the following
header

 Test Case | Expected Result | Actual Results | Verdict(Pass, Fail, Inconclusive) 
 --- | --- | --- | --- 
1 | registration request accepted | Err4: Wrong Firstname Format, Err5: Wrong Last name format, Err6: Wrong email format         | Fail
2 |    registration request accepted   |      Err6: Wrong email format       | Fail
3 | registration request accepted |Err4: Wrong Firstname Format, Err5: Wrong Last name format | Fail
4 |    registration request accepted   |      registration request accepted          | Pass
5 |    Err 1: Wrong UserName format   | Err1:Wrong UserName format, Err3: Size must be between 6 and 12, Err4: Wrong FirstName format, Err5: Wrong LastName format           | Fail
6 | Err3: Size must be between 6 and 12 | Err1:Wrong UserName format, Err3: Size must be between 6 and 12, Err6: Wrong Email format | Fail
7 | Err3:Size must be between 6 and 12  | Err1:Wrong UserName format, Err3: Size must be between 6 and 12, Err4: Wrong FirstName format, Err5: Wrong LastName format  | Fail
8 | Err1:Wrong UserName format | Err1:Wrong UserName format | Pass


# JUnit Paramaterized

Proof of compilation by Michael Kagnew:

![image](assets\bitTest.png)

Proof of compilation by Gabriel Cordavado:

![image](assets\)


# DATE NEXTDATE METHOD
The class Date (Data.java) provides a basic implementation
of a data structure for dates. Method nextDate returns an
instance of Date corresponding to the date of the day after
the executing instance.

![image](assets/test_cases_nextdate.png)