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


    describe "parameters/1" do 
      setup do
      # [
      #   {["A+", 10], %{homework: [0.99], labs: [0.99, 0.99, 0.99], midterm: 0.99, final: 0.99}},
      #   {["A", 9], %{homework: [0.85], labs: [0.85, 0.85, 0.85], midterm: 0.85, final: 0.85}},
      #   {["A-", 8], %{homework: [0.8], labs: [0.8, 0.8, 0.8], midterm: 0.8, final: 0.8}},
      #   {["B+", 7], %{homework: [0.75], labs: [0.75, 0.75, 0.75], midterm: 0.75, final: 0.75}},
      #   {["B", 6], %{homework: [0.7], labs: [0.7,0.7,0.7], midterm: 0.7, final: 0.7}},
      #   {["C+", 5], %{homework: [0.65], labs: [0.65,0.65,0.65], midterm: 0.65, final: 0.65}},
      #   {["C", 4],%{homework: [0.6], labs: [0.6,0.6,0.6], midterm: 0.6, final: 0.6}},
      #   {["D+", 3], %{homework: [0.55], labs: [0.55, 0.55, 0.55], midterm: 0.55, final: 0.55}},
      #   {["D", 2], %{homework: [0.5], labs: [0.5,0.5, 0.5], midterm: 0.5, final: 0.5}},
      #   {["E", 1], %{homework: [0.4], labs: [0.4, 0.4, 0.4], midterm: 0.4, final: 0.4}},
      #   {["F", 0], %{homework: [0.4], labs: [0,0,0,0.25,0.25,0.25,0.25,0,0,0,0,0,0,0], midterm: 0.4, final: 0.4}}
      # ]
      {:ok, values: [{["A+", 10], %{homework: [0.99], labs: [0.99, 0.99, 0.99], midterm: 0.99, final: 0.99}},
        {["A", 9], %{homework: [0.85], labs: [0.85, 0.85, 0.85], midterm: 0.85, final: 0.85}}] }
      end
    end
  describe "paramaterized_testing/1" do

    test "parmater1s/1", parameters_list do
      # parameters_list = [
      #   {["A+", 10], %{homework: [0.99], labs: [0.99, 0.99, 0.99], midterm: 0.99, final: 0.99}},
      #   {["A", 9], %{homework: [0.85], labs: [0.85, 0.85, 0.85], midterm: 0.85, final: 0.85}},
      #   {["A-", 8], %{homework: [0.8], labs: [0.8, 0.8, 0.8], midterm: 0.8, final: 0.8}},
      #   {["B+", 7], %{homework: [0.75], labs: [0.75, 0.75, 0.75], midterm: 0.75, final: 0.75}},
      #   {["B", 6], %{homework: [0.7], labs: [0.7,0.7,0.7], midterm: 0.7, final: 0.7}},
      #   {["C+", 5], %{homework: [0.65], labs: [0.65,0.65,0.65], midterm: 0.65, final: 0.65}},
      #   {["C", 4],%{homework: [0.6], labs: [0.6,0.6,0.6], midterm: 0.6, final: 0.6}},
      #   {["D+", 3], %{homework: [0.55], labs: [0.55, 0.55, 0.55], midterm: 0.55, final: 0.55}},
      #   {["D", 2], %{homework: [0.5], labs: [0.5,0.5, 0.5], midterm: 0.5, final: 0.5}},
      #   {["E", 1], %{homework: [0.4], labs: [0.4, 0.4, 0.4], midterm: 0.4, final: 0.4}},
      #   {["F", 0], %{homework: [0.4], labs: [0,0,0,0.25,0.25,0.25,0.25,0,0,0,0,0,0,0,0], midterm: 0.4, final: 0.4}},
      #   {[]}
      # ]
      for {result, input} <- parameters_list[] do
        assert Enum.at(result, 0) == Calculator.letter_grade(input)
        assert Enum.at(result, 1) == Calculator.numeric_grade(input)
      end
    end
  end
end
