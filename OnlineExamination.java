
import java.util.Scanner;

public class OnlineExamination {
    private static String[] usernames = {"user1", "user2"};
    private static String[] passwords = {"password1", "password2"};
    private static String[] profiles = {"Default Profile 1", "Default Profile 2"};
    private static String loggedInUser = null;
    private static int loggedInUserIndex = -1;
    private static Scanner scanner = new Scanner(System.in);
    private static final int EXAM_DURATION = 300; // in seconds (5 minutes)

    public static void main(String[] args) {
        while (true) {
            if (loggedInUser == null) {
                showLoginMenu();
            } else {
                showUserMenu();
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("Welcome to Online Examination System");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean loginSuccessful = false;

        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(username) && passwords[i].equals(password)) {
                loggedInUser = username;
                loggedInUserIndex = i;
                loginSuccessful = true;
                break;
            }
        }

        if (loginSuccessful) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void showUserMenu() {
        System.out.println("\n1. Update Profile and Password");
        System.out.println("2. Start Exam");
        System.out.println("3. Logout");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                updateProfile();
                break;
            case 2:
                startExam();
                break;
            case 3:
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void updateProfile() {
        System.out.print("Enter new profile information: ");
        String newProfile = scanner.nextLine();
        profiles[loggedInUserIndex] = newProfile;
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        passwords[loggedInUserIndex] = newPassword;
        System.out.println("Profile and password updated successfully.");
    }

    private static void startExam() {
        String[] questions = {
            "Q1. Which of the following is a valid keyword in Java?\n1. interface\n2. string\n3. Float\n4. unsigned",
            "Q2. What is the size of int in Java?\n1. 2 bytes\n2. 4 bytes\n3. 8 bytes\n4. Depends on the machine",
            "Q3. Which method is used to start a thread in Java?\n1. init()\n2. start()\n3. run()\n4. resume()",
            "Q4. Which of these is not a valid Java access modifier?\n1. public\n2. protected\n3. private\n4. package",
            "Q5. Which of the following is not a primitive data type in Java?\n1. int\n2. Float\n3. boolean\n4. char",
            "Q6. What is the default value of a boolean variable in Java?\n1. true\n2. false\n3. 0\n4. null",
            "Q7. Which of these cannot be used for a variable name in Java?\n1. identifier\n2. keyword\n3. identifier_1\n4. $identifier",
            "Q8. Which method is used to get the length of a string in Java?\n1. getSize()\n2. length()\n3. getLength()\n4. size()",
            "Q9. Which of the following is used to handle exceptions in Java?\n1. try-catch\n2. include-except\n3. do-while\n4. if-else",
            "Q10. Which of the following is a superclass of every class in Java?\n1. Object\n2. Class\n3. Interface\n4. Parent"
        };
        int[] correctAnswers = {1, 2, 2, 4, 2, 2, 2, 2, 1, 1};
        int[] userAnswers = new int[questions.length];
        long startTime = System.currentTimeMillis();

        System.out.println("Exam started. You have " + EXAM_DURATION + " seconds to complete.");

        for (int i = 0; i < questions.length; i++) {
            long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            long remainingTime = EXAM_DURATION - elapsedTime;
            if (remainingTime <= 0) {
                System.out.println("Time's up! Exam is auto-submitted.");
                break;
            }

            System.out.println(questions[i]);
            System.out.println("Time remaining: " + remainingTime + " seconds");
            System.out.print("Your answer: ");
            userAnswers[i] = scanner.nextInt();
        }

        System.out.println("Exam submitted. Here are your results:");
        int score = 0;
        for (int i = 0; i < questions.length; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
        System.out.println("You scored " + score + " out of " + questions.length);
    }

    private static void logout() {
        System.out.println("Logging out...");
        loggedInUser = null;
        loggedInUserIndex = -1;
    }
}
