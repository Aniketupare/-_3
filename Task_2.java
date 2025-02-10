import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Task_2 {
    private static String username = "aniket";
    private static String password = "9898";
    private static int currentQuestion = 0;
    private static int score = 0;
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel questionLabel;
    private static JRadioButton[] options;
    private static ButtonGroup group;
    private static JButton nextButton;

    private static String[][] questions = {
            {"What is Java?", "A. A programming language", "B. A coffee brand", "C. A movie", "D. A country", "A"},
            {"Who invented Java?", "A. Dennis Ritchie", "B. James Gosling", "C. Bjarne Stroustrup", "D. Guido van Rossum", "B"},
            {"What is JVM?", "A. Java Virtual Machine", "B. Java Visual Model", "C. Java Version Manager", "D. Java Verified Mode", "A"},
            {"Which keyword is used to define a class in Java?", "A. function", "B. define", "C. class", "D. method", "C"}
    };

    public static void main(String[] args) {
        showInstructions();
    }

    private static void showInstructions() {
        JOptionPane.showMessageDialog(null, "Welcome to the Online Examination!\n\nInstructions:\n1. Login with your credentials.\n2. Answer all multiple-choice questions.\n3. Click 'Next' to move to the next question.\n4. Your score will be displayed at the end.\n\nClick OK to continue.", "Instructions", JOptionPane.INFORMATION_MESSAGE);
        showLogin();
    }

    private static void showLogin() {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(3, 2));
        frame.setLocationRelativeTo(null); // Center the window

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JLabel statusLabel = new JLabel();

        loginButton.addActionListener(e -> {
            if (userField.getText().equals(username) && new String(passField.getPassword()).equals(password)) {
                frame.dispose();
                startExam();
            } else {
                statusLabel.setText("Invalid Credentials!");
            }
        });

        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(loginButton);
        frame.add(statusLabel);

        frame.setVisible(true);
    }

    private static void startExam() {
        frame = new JFrame("Online Examination");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);
        frame.setLocationRelativeTo(null); // Center the window

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(questionLabel);

        options = new JRadioButton[4];
        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            panel.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                if (currentQuestion < questions.length - 1) {
                    currentQuestion++;
                    loadQuestion();
                } else {
                    showResult();
                }
            }
        });

        panel.add(nextButton);
        frame.add(panel);
        loadQuestion();
        frame.setVisible(true);
    }

    private static void loadQuestion() {
        questionLabel.setText(questions[currentQuestion][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(questions[currentQuestion][i + 1]);
        }
        group.clearSelection();
    }

    private static void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected() && options[i].getText().startsWith(questions[currentQuestion][5])) {
                score++;
                break;
            }
        }
    }

    private static void showResult() {
        JOptionPane.showMessageDialog(frame, "Your final score: " + score + " out of " + questions.length + "\n\nThank you for taking the exam! You will now be logged out.", "Exam Completed", JOptionPane.INFORMATION_MESSAGE);
        frame.dispose();
    }
}
