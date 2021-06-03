defmodule Grades.CalculatorTest do
  use ExUnit.Case
  alias Grades.Calculator

  describe "percentage_grade/1" do
    test "average_is_valid" do
      assert 85 ==
               Calculator.percentage_grade(%{
                 homework: [0.8],
                 labs: [1, 1, 1],
                 midterm: 0.70,
                 final: 0.9
               })
    end
    test "homework_count_0" do
      assert 52 ==
              Calculator.percentage_grade(%{
                homework: [],
                labs: [0.62, 0.89, 0.56],
                midterm: 0.82,
                final: 0.73
              })
    end
    test "lab_count_0" do
      assert 59 ==
        Calculator.percentage_grade(%{
          homework: [0.62, 0.89, 0.56],
          labs: [],
          midterm: 0.82,
          final: 0.73
        })
    end
  end

  describe "letter_grade/1" do
    test "get_ein_zero_labs" do
      assert "EIN" ==
                Calculator.letter_grade(%{
                  homework: [0.2],
                  labs: [],
                  midterm: 0.78,
                  final: 0.62
                })
    end
    test "get_ein_zero_homework" do
      assert "EIN" ==
                Calculator.letter_grade(%{
                  homework: [],
                  labs: [0.62, 0.53, 0.73],
                  midterm: 0.60,
                  final: 0.62
                })
    end
  end

  describe "numeric_grade/1" do

  end

  describe "paramaterized_testing/1" do 

    test "parmater1s/1" do 
        parameters_list = [{["EIN", 10], %{
      homework: [],
      labs: [0.62, 0.53, 0.73],
      midterm: 0.60,
      final: 0.62
    }}]
      for {result, input} <- parameters_list do
        assert Enum.at(result, 0) == Calculator.letter_grade(input)
                assert Enum.at(result, 1) == Calculator.letter_grade(input)

      end
    end
  end 

end
