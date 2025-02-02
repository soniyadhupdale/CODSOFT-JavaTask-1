import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class QuizQuestion 
{
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public QuizQuestion(String question, List<String> options, int correctAnswerIndex) 
    {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() 
    {
        return question;
    }

    public List<String> getOptions() 
    {
        return options;
    }

    public int getCorrectAnswerIndex() 
    {
        return correctAnswerIndex;
    }
}

public class Quiz 
{
    public static void main(String[] args) 
    {
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the capital of India?", List.of("Delhi", "Mumbai", "Kolkata", "Chennai"), 0));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?", List.of("Earth", "Mars", "Venus", "Jupiter"), 1));

        int score = 0;
        Scanner scanner = new Scanner(System.in);

        for (QuizQuestion question : questions) 
        {
            System.out.println(question.getQuestion());
            for (int i = 0; i < question.getOptions().size(); i++) 
            {
                System.out.println((i + 1) + ". " + question.getOptions().get(i));
            }

            System.out.print("Enter your answer (1-" + question.getOptions().size() + "): ");
            int userAnswer = scanner.nextInt() - 1;

            if (userAnswer == question.getCorrectAnswerIndex()) 
            {
                System.out.println("Correct!");
                score++;
            } 
            else 
            {
                System.out.println("Incorrect. The correct answer is: " + question.getOptions().get(question.getCorrectAnswerIndex()));
            }
        }

        System.out.println("Your final score is: " + score + "/" + questions.size());
    }
}
