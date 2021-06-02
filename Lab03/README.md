# Lab03
| Outline | Value |
| --- | --- |
| Course | SEG 3103 |
| Date | Summer 2021 |
| Name | Gabe Cordovado (300110852) and Michael Kagnew(300113347)  |
| Professor | Andrew Forward, aforward@uottawa.ca |
| TA | Nazanin Bayati Chaleshtari, n.bayati@uottawa.ca |

Repo link https://github.com/Michael-Kagnew/seg3103_playground

### Lab 3 Objectives
- Running code coverage tools
- Applying white-box coverage techniques 
- Refactoring in systematic steps 
- Continue practice of Git/GitHub

### Jacoco Coverage Analysis
Jacoco is a free code coverage library for Java, and during this lab, we utilized it in the Eclipse IDE. Below are the results for the computation test class, as well as screenshots for the Date class before and after adding test classes and refactoring.

# Part One
Generate the code coverage metrics for the Computation package and analyze them in the browser. 

## Computation Code Coverage Metrics

Here are the metrics for the computation code coverage:
![image](./assets/computation_coverage.png)

# Part Two
Derive a test suite for 100% coverage for class Date

1. Statement coverage
2. Branch coverage
3. Condition coverage
4. Condition/branch coverage

## Date Code Coverage Metrics
Here are the metrics for the date class before any changes were made:
![image](./assets/date_before_tests.png)

Here are the metrics after adding in more tests: 
![image](./assets/Lab3AddedTestsCoverage.png)

### Manual Condition Coverage
After counting all the conditions, there are 27/28 conditions being covered, resulting in 96% condition coverage

### Discussion
For the Date class, we were able to bring up the code coverage from 79% to 98.5%. It was not possible to achieve 100% code coverage due to the condition below:

![image](./assets/FailedCondition.png)

Jacoco is looking at all the possible combinations of branches under isEndOfMonth(), including the "Day is 29" and "is NOT leap year" boolean combinations. The issue that arises is because we have an AND combination between these two values, Jacoco is attempting to find "Day is 29" and "is NOT leap year" which is NOT feasible and "Day is 29" and "is A leap year". The reason we cannot gain 100% coverage is because Jacoco is not taking into account non-feasible branches that would result in an Error value.

# Part Three
Clean up Date.java

- Small Commits
- No changes to tests should be required
- Re-run tests after each small change (you don't need to rerun coverage)
- Run coverage after refactoring? (Dit it improve? Did it degrade? Why?)

## Refactoring
This single condition was not refactorable in order to effectively achieve complete coverage, therefore it's not always possible to achieve 100% code coverage. Our refactoring was limited, and did not change the function in a meaningful way, even after our best attempts to determine what could be done. It could be the software having problems, or a truly underlying logic issue that we missed, but my partner and I suspect Jacoco making some errors.

As mentioned, the refactoring was limited since every other part of the code was covered successfully, except for that single condition. Therefore, no changes were made. However, at 98.5% branch coverage, with many other coverages at 100%, we believe this number achieves a satisfactory amount of testing. 

Below is the screnshot of the coverage after refactoring. As seen, there is no change after any refactoring, especially since it is impossible to get that one condition to sucessfully pass.
![image](./assets/Lab3AddedTestsCoverage.png)
