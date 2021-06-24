defmodule Grades.Calculator do

  def letter_grade(%{final: final, homework: homework, labs: labs, midterm: midterm}) do
    random_let = ["A", "B", "C"]
    Enum.random(random_let)
  end

  def percentage_grade(%{final: final, homework: homework, labs: labs, midterm: midterm}) do
    random_let = [100, 80, 60]
    Enum.random(random_let)
  end

    def numeric_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
        random_let = [10, 8, 7]
        Enum.random(random_let)
    end
end
