package passwordservices1;
 
import java.util.Scanner;
 
public class PasswordServices1 {
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
 
        System.out.println("Welcome to our password services project");
 
        // variables 
        double length;
        int enteredServiceNumber;
        String[] passwords;
 
        // do while to show the menu
        do {
 
            // print the menu
            System.out.println("To generate passwords, please enter 1.");
 
            System.out.println("To check the strength of your password, please enter 2.");
 
            System.out.println("To generate passwords and check their strengths, please enter 3.");
 
            System.out.println("To exit the program, please enter 0");
 
            enteredServiceNumber = input.nextInt();
            input.nextLine();
 
            switch (enteredServiceNumber) { // switch statement
 
                // exit program
                case 0:
                    System.out.println("Message: Program ended");
                    break;
 
                // generate and print passwords
                case 1:
                    System.out.println("enter the password length: ");
                    length = input.nextDouble();
                    if (length % 1 != 0 || length < 0) {
                        System.out.println("Length should be a positive integer");
 
                    } else {
 
                        passwords = generatePasswords(length);
                        printPasswords(passwords);
 
                    }
                    break;
 
                // check and print strength
                case 2:
                    System.out.println("Enter your password");
                    String userPassword = input.nextLine();
                    int score = checkStrength(userPassword);
                    printStrength(score);
                    break;
 
                // generate passwords, check strength, and print passwords with strength    
                case 3:
                    System.out.println("Enter the password length");
                    length = input.nextDouble();
                    if (length % 1 != 0 && length < 0) {
                        System.out.println("Length should be a positive integer");
                    } else {
                        passwords = generatePasswords(length);
                        int[] scores = new int[3];
                        for (int p = 0; p < 3; p++) {
                            scores[p] = checkStrength(passwords[p]);
                        }
                        printPasswords(passwords, scores);
                    }
 
                    break;
 
                // default case
                default:
                    System.out.println("Error: Invalid entry");
            }
        } while (enteredServiceNumber != 0);
    }
 
 
    /* *********************************************** */
    /**
     * method generatePasswords uses the Math.random() method to randomly
     * generate three passwords of the chosen length
     *
     * @param len
     */
    public static String[] generatePasswords(double len) {
        String lowerCese = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String symbole = "!@><?%/#$&*+=_";
        String numbers = "0123456789";
        String allThePossibleCharacters = lowerCese + upperCase + symbole + numbers;
        String[] passwords = new String[3];
        for (int i = 0; i < 3; i++) {
            String password1 = "";
            for (int j = 0; j < len; j++) {
                int k = (int) (Math.random() * allThePossibleCharacters.length());
                password1 += allThePossibleCharacters.charAt(k);
            }
            passwords[i] = password1;
        }
        return passwords;
    }
 
    public static void printPasswords(String[] passwords) {
        System.out.println("Here are a few options:");
        for (int p = 0; p < 3; p++) {
            System.out.println(passwords[p]);
        }
    }
 
    public static void printPasswords(String[] passwords, int[] scores) {
        for (int p = 0; p < 3; p++) {
            System.out.print(passwords[p] + " ");
            printStrength(scores[p]);
        }
    }
 
    /* ********************************************** */
    /**
     * method checkStrength calculates and returns the score of the given
     * password
     *
     * @param s
     */
    public static int checkStrength(String s) {
        boolean upperCaseLetter = false;
        boolean lowerCaseLetter = false;
        boolean digit = false;
        boolean symbol = false;
        int score = 0;
        int i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
 
            if (ch >= 'A' && ch <= 'Z') {
                upperCaseLetter = true;
 
            }
            if (ch >= 'a' && ch <= 'z') {
                lowerCaseLetter = true;
            }
            if (ch >= '0' && ch <= '9') {
                digit = true;
            } else {
                symbol = true;
            }
            i++;
        }
 
        if (upperCaseLetter) {
            score++;
        }
        if (lowerCaseLetter) {
            score++;
        }
        if (digit) {
            score++;
        }
        if (symbol) {
            score++;
        }
 
        if (s.length() >= 8) {
            score++;
        }
        return score;
 
    }
 
    /* ********************************************** */
    /**
     * method printStrength prints the corresponding strength to the given score
     *
     * @param score
     */
    public static void printStrength(int score) {
 
        if (score == 5) {
            System.out.println("This is a very good password!");
        } else if (score == 4) {
            System.out.println("This is a good password, but you can still do better");
        } else if (score == 3) {
            System.out.println("This is a medium password, try making it better");
        } else {
            System.out.println("This is a weak password, you should find a new one!");
        }
 
    }
}


