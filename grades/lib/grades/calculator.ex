defmodule Grades.Calculator do

  # Question 2.1 (Gabriel Cordovado)
  # This function will take in the homework and lab lists seperatly to reduce the amount of code
  # that is being duplicated within the percentage_grade, letter_grade, and numeric_grade classes
  #
  # @param list_of_n_items: list of floats
  # @returns an float representing the average of N items within the list of floats
  #
  def avg(list_of_n_items) do
    if Enum.count(list_of_n_items) == 0 do
      0
    else
      Enum.sum(list_of_n_items) / Enum.count(list_of_n_items)
    end
  end
  
  # Question 2.2 (Michael Kagnew)
  # The formula used to determine if the student is able to participate in the marking scheme.
  # @param avg_homework, avg_exams, num_labs : boolean
  # @returns a boolean determining if the student is able to participate in the grade calculation.
  #
  def failed_to_participate(avg_homework, avg_exams, num_labs) do 
     avg_homework < 0.4 || avg_exams < 0.4 || num_labs < 3
  end

  # Question 2.3 (Gabriel Cordovado)
  # the formula to calculate the student's offical grade is referenced multipl times throughout
  # the program, this will help reduce the amount of duplicated lines and make the code more
  # readable to the developer
  #
  # @param avg_labs, avg_homework, midterm, final : float
  # @returns a float representing the students calculated grade
  #
  def calculate_grade(avg_labs, avg_homework, midterm, final) do
    0.2 * avg_labs + 0.3 * avg_homework + 0.2 * midterm + 0.3 * final
  end
  
 

  def percentage_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
    avg_homework = avg(homework)  # refractor 2.1

    avg_labs = avg(labs)  # refractor 2.1

    mark = calculate_grade(avg_labs, avg_homework, midterm, final)  # refractor 2.3
    round(mark * 100)
  end

  def letter_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
    avg_homework = avg(homework)  # refractor 2.1

    avg_labs = avg(labs)  # refractor 2.1

    avg_exams = (midterm + final) / 2

    num_labs =
      labs
      |> Enum.reject(fn mark -> mark < 0.25 end)
      |> Enum.count()

    if failed_to_participate(avg_homework, avg_exams, num_labs) do #refractor 2.2
      "EIN"
    else
      mark = calculate_grade(avg_labs, avg_homework, midterm, final)  # refractor 2.3

      cond do
        mark > 0.895 -> "A+"
        mark > 0.845 -> "A"
        mark > 0.795 -> "A-"
        mark > 0.745 -> "B+"
        mark > 0.695 -> "B"
        mark > 0.645 -> "C+"
        mark > 0.595 -> "C"
        mark > 0.545 -> "D+"
        mark > 0.495 -> "D"
        mark > 0.395 -> "E"
        :else -> "F"
      end
    end
  end

  def numeric_grade(%{homework: homework, labs: labs, midterm: midterm, final: final}) do
    avg_homework = avg(homework)  # refractor 2.1

    avg_labs = avg(labs)  # refractor 2.1

    avg_exams = (midterm + final) / 2

    num_labs =
      labs
      |> Enum.reject(fn mark -> mark < 0.25 end)
      |> Enum.count()

    if failed_to_participate(avg_homework, avg_exams, num_labs) do #refractor 2.2
      0
    else
      mark = calculate_grade(avg_labs, avg_homework, midterm, final)  # refractor 2.3

      cond do
        mark > 0.895 -> 10
        mark > 0.845 -> 9
        mark > 0.795 -> 8
        mark > 0.745 -> 7
        mark > 0.695 -> 6
        mark > 0.645 -> 5
        mark > 0.595 -> 4
        mark > 0.545 -> 3
        mark > 0.495 -> 2
        mark > 0.395 -> 1
        :else -> 0
      end
    end
  end



end
