defmodule Grades.CalculatorTest do
  use ExUnit.Case
  alias Grades.Calculator

  describe "parameters/1" do
      setup do
      [values: [
        {["A+", 10, 99], %{homework: [0.99], labs: [0.99, 0.99, 0.99], midterm: 0.99, final: 0.99}},
        {["A", 9], %{homework: [0.85], labs: [0.85, 0.85, 0.85], midterm: 0.85, final: 0.85}},
        {["A-", 8], %{homework: [0.8], labs: [0.8, 0.8, 0.8], midterm: 0.8, final: 0.8}},
        {["B+", 7], %{homework: [0.75], labs: [0.75, 0.75, 0.75], midterm: 0.75, final: 0.75}},
        {["B", 6], %{homework: [0.7], labs: [0.7,0.7,0.7], midterm: 0.7, final: 0.7}},
        {["C+", 5], %{homework: [0.65], labs: [0.65,0.65,0.65], midterm: 0.65, final: 0.65}},
        {["C", 4],%{homework: [0.6], labs: [0.6,0.6,0.6], midterm: 0.6, final: 0.6}},
        {["D+", 3], %{homework: [0.55], labs: [0.55, 0.55, 0.55], midterm: 0.55, final: 0.55}},
        {["D", 2], %{homework: [0.5], labs: [0.5,0.5, 0.5], midterm: 0.5, final: 0.5}},
        {["E", 1], %{homework: [0.4], labs: [0.4, 0.4, 0.4], midterm: 0.4, final: 0.4}},
        {["F", 0,], %{homework: [0.4], labs: [0,0,0,0.25,0.25,0.25,0.25,0,0,0,0,0,0,0,0], midterm: 0.4, final: 0.4}},
        {["EIN", 0, 15], %{homework: [], labs: [], midterm: 0.3, final: 0.3}}
          ]
      ]
      end

      # percentage_grade

      test "percentage_grade_valid/1", param do
        branch = Enum.at(param[:values], 0)
        assert Enum.at(elem(branch, 0), 2) == Calculator.percentage_grade(elem(branch, 1))
      end

      test "percentage_grade_0/1" , param do
        branch = Enum.at(param[:values], 11)
        assert Enum.at(elem(branch, 0), 2) == Calculator.percentage_grade(elem(branch, 1))
      end

      #letter_grade

      test "letter_grade_A+/1", param do
        branch = Enum.at(param[:values], 0)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end

      test "letter_grade_A/1", param do
        branch = Enum.at(param[:values], 1)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end

      test "letter_grade_A-/1", param do
        branch = Enum.at(param[:values], 2)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end

      test "letter_grade_B+/1", param do
        branch = Enum.at(param[:values], 3)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end

      test "letter_grade_B/1", param do
        branch = Enum.at(param[:values], 4)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end

      test "letter_grade_C+/1", param do
        branch = Enum.at(param[:values], 5)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end

      test "letter_grade_C/1", param do
        branch = Enum.at(param[:values], 6)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end

      test "letter_grade_D+/1", param do
        branch = Enum.at(param[:values], 7)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end

      test "letter_grade_D/1", param do
        branch = Enum.at(param[:values], 8)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end

      test "letter_grade_E/1", param do
        branch = Enum.at(param[:values], 9)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end

      test "letter_grade_F/1", param do
        branch = Enum.at(param[:values], 10)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end

      test "letter_grade_EIN/1", param do
        branch = Enum.at(param[:values], 11)
        assert Enum.at(elem(branch, 0), 0) == Calculator.letter_grade(elem(branch,1))
      end


      # numeric_grade

      test "numeric_grade_10/1", param do
        branch = Enum.at(param[:values], 0)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end

      test "numeric_grade_9/1", param do
        branch = Enum.at(param[:values], 1)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end

      test "numeric_grade_8/1", param do
        branch = Enum.at(param[:values], 2)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end

      test "numeric_grade_7/1", param do
        branch = Enum.at(param[:values], 3)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end

      test "numeric_grade_6/1", param do
        branch = Enum.at(param[:values], 4)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end

      test "numeric_grade_5/1", param do
        branch = Enum.at(param[:values], 5)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end

      test "numeric_grade_4/1", param do
        branch = Enum.at(param[:values], 6)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end

      test "numeric_grade_3/1", param do
        branch = Enum.at(param[:values], 7)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end

      test "numeric_grade_2/1", param do
        branch = Enum.at(param[:values], 8)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end

      test "numeric_grade_1/1", param do
        branch = Enum.at(param[:values], 9)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end

      test "numeric_grade_0/1", param do
        branch = Enum.at(param[:values], 10)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end

      test "numeric_grade_0_EIN/1", param do
        branch = Enum.at(param[:values], 11)
        assert Enum.at(elem(branch, 0), 1) == Calculator.numeric_grade(elem(branch,1))
      end
  end
end
