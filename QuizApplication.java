import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private String question;
    private String[] options;
    private int correctOption; // 0‑based index
    private String explanation;

    public Question(String question, String[] options, int correctOption, String explanation) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
        this.explanation = explanation;
    }

    public String getQuestion() { return question; }
    public String[] getOptions() { return options; }
    public int getCorrectOption() { return correctOption; }
    public String getExplanation() { return explanation; }

    public boolean isCorrect(int choice) {
        return choice == correctOption;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;
    private Scanner scanner;

    public Quiz() {
        questions = new ArrayList<>();
        scanner = new Scanner(System.in);
        loadQuestions();
    }

    private void loadQuestions() {
        // General Knowledge questions
        questions.add(new Question("What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 2,
                "Paris is the capital of France."));

        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"Venus", "Mars", "Jupiter", "Saturn"}, 1,
                "Mars has a reddish appearance due to iron oxide."));

        questions.add(new Question("Who wrote 'Hamlet'?",
                new String[]{"Charles Dickens", "William Shakespeare", "Mark Twain", "Leo Tolstoy"}, 1,
                "Shakespeare wrote Hamlet."));

        questions.add(new Question("What is the largest ocean on Earth?",
                new String[]{"Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean"}, 3,
                "Pacific Ocean is the largest."));

        questions.add(new Question("In which year did the Titanic sink?",
                new String[]{"1912", "1905", "1898", "1923"}, 0,
                "Titanic sank in 1912."));

        questions.add(new Question("What is the chemical symbol for gold?",
                new String[]{"Go", "Gd", "Au", "Ag"}, 2,
                "Au from Latin 'aurum'."));

        questions.add(new Question("Which country is home to the kangaroo?",
                new String[]{"South Africa", "Australia", "New Zealand", "Brazil"}, 1,
                "Kangaroos are native to Australia."));

        questions.add(new Question("Who painted the Mona Lisa?",
                new String[]{"Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"}, 2,
                "Leonardo da Vinci painted the Mona Lisa."));

        questions.add(new Question("What is the hardest natural substance on Earth?",
                new String[]{"Gold", "Iron", "Diamond", "Platinum"}, 2,
                "Diamond is the hardest."));

        questions.add(new Question("Which element has the atomic number 1?",
                new String[]{"Helium", "Oxygen", "Hydrogen", "Carbon"}, 2,
                "Hydrogen has atomic number 1."));

        questions.add(new Question("What is the smallest prime number?",
                new String[]{"0", "1", "2", "3"}, 2,
                "2 is the smallest prime number."));

        questions.add(new Question("Which continent is the Sahara Desert located in?",
                new String[]{"Asia", "Africa", "Australia", "South America"}, 1,
                "Sahara Desert is in Africa."));

        questions.add(new Question("Who developed the theory of relativity?",
                new String[]{"Isaac Newton", "Albert Einstein", "Galileo Galilei", "Nikola Tesla"}, 1,
                "Einstein developed relativity."));

        questions.add(new Question("What is the capital of Japan?",
                new String[]{"Seoul", "Beijing", "Bangkok", "Tokyo"}, 3,
                "Tokyo is the capital of Japan."));

        questions.add(new Question("How many bones are in the adult human body?",
                new String[]{"206", "201", "210", "196"}, 0,
                "Adult humans have 206 bones."));
    }

    public void start() {
        System.out.println("Welcome to the General Knowledge Quiz!");
        System.out.println("You will be presented with " + questions.size() + " multiple-choice questions.");
        System.out.println("Please enter the number of your answer (1-4).\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            displayQuestion(i + 1, q);
            int choice = getUserChoice();
            if (q.isCorrect(choice - 1)) { // convert 1‑based input to 0‑based
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " +
                        (q.getCorrectOption() + 1) + ". " + q.getOptions()[q.getCorrectOption()]);
                if (q.getExplanation() != null && !q.getExplanation().isEmpty()) {
                    System.out.println("Explanation: " + q.getExplanation());
                }
                System.out.println();
            }
        }

        displayResult();
        scanner.close();
    }

    private void displayQuestion(int number, Question q) {
        System.out.println("Question " + number + ": " + q.getQuestion());
        String[] options = q.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
        System.out.print("Your answer: ");
    }

    private int getUserChoice() {
        int choice = -1;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 4) {
                    break;
                } else {
                    System.out.print("Invalid choice. Please enter a number between 1 and 4: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
        return choice;
    }

    private void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your score: " + score + "/" + questions.size());
        double percentage = (score * 100.0) / questions.size();
        System.out.printf("Percentage: %.2f%%\n", percentage);
        if (percentage >= 80) {
            System.out.println("Excellent work!");
        } else if (percentage >= 60) {
            System.out.println("Good job!");
        } else if (percentage >= 40) {
            System.out.println("Not bad, but you could improve.");
        } else {
            System.out.println("Better luck next time. Keep learning!");
        }
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.start();
    }
}