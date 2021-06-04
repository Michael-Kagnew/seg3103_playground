# Lab 4

| Outline | Value |
| --- | --- |
| Course | SEG 3103 |
| Date | Summer 2021 |
| Name | Gabe Cordovado (300110852) and Michael Kagnew(300113347)  |
| Professor | Andrew Forward, aforward@uottawa.ca |
| TA | Nazanin Bayati Chaleshtari, n.bayati@uottawa.ca |

Repo link https://github.com/Michael-Kagnew/seg3103_playground

## @Michael-Kagnew

![image info](./assets/michael_commits.PNG)

#### Commit 4648f71
buggy code

---

#### Commit baf6fe5
Failed Test Commit Mike … 

---

#### Commit 87d52d5
Actual Buggy Code …

---

#### Commit c309c05
Fixed Buggy Code …

---

#### Commit c5e2bfe
Refactored Code …

---

## Gabriel Cordovado

![image info](./assets/gabe_commits.PNG)

#### Commit 6d75cdc
buggy code

![image info](./assets/gabe_buggy_code.PNG)

---

#### Commit 86e85eb
failed test cases

![image info](./assets/gabe_failed_test.PNG)

---

#### Commit a9ac169
fixed code all tests pass …

  the old code for tic next move was misplacing the X and Y coordinates such that the row was mistaken for the column and vice versa, in addition to the case of as   _| character not being replaced with X| or O| where the old code would forget the line

![image info](./assets/gabe_passed_test.PNG)

---

#### Commit 745d941
refractored conditional check on _| case …

  in the old code there were two checks for _| in either switch case, we will use an append  variable which will hold the value of | if the condition is met before   the switch so we avoid the duplicate code
  
![image info](./assets/gabe_ref_1.PNG)

---

#### Commit 6fd450a
refractt switch statement and bounds check …

  swapped the use of the switch statement with if-else in order to increase readability of the code, on line 58 removed a useless bracked around the posX arguments   since they are all OR statements it won't matter

![image info](./assets/gabe_ref_2.PNG)
