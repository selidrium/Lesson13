import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;

public class NumberGuessingGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new GameFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class GameFrame extends JFrame {

    // Must use a Layout Manager with a JFrame.
    private static final int DEFAULT_WIDTH = 900;
    private static final int DEFAULT_HEIGHT = 900;

    public GameFrame() {
        setTitle("Number Guessing Game");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLayout(new BorderLayout());

        // Components
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Guess a number between 1 and 100:");
        JTextField textField = new JTextField(5);
        JButton button = new JButton("Submit");

        // Image
        ImagePanel imagePanel = new ImagePanel("images/dollars.jpeg");


        // Components
        mainPanel.add(label);
        mainPanel.add(textField);
        mainPanel.add(button);
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(imagePanel);

        button.addActionListener(new ActionListener() {
            private int targetNumber = (int) (Math.random() * 100) + 1;
            private LinkedList<Integer> guesses = new LinkedList<>();

            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    int guess = Integer.parseInt(textField.getText());
                    if (guess < 1 || guess > 100) {
                        // Must have at least one class that creates a custom exception that makes sense for your game.
                        throw new InvalidInputException("Invalid input: number must be between 1 and 100.");
                    }
                    guesses.add(guess);
                    if (guess == targetNumber) {
                        saveResults();
                        JOptionPane.showMessageDialog(null, "Congratulations, you guessed the number! It took you " + guesses.size() + " attempts.");
                        System.exit(0);
                    } else if (guess > targetNumber) {
                        JOptionPane.showMessageDialog(null, "Your guess is too high. Try again.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Your guess is too low. Try again.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input: please enter a valid integer.");
                } catch (InvalidInputException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                textField.setText("");
            }

            private void saveResults() {
                // Must read/write something to a File (Suggestion:  If you can't think of anything else, what about high scores?)
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true))) {
                    writer.write("Number of attempts: " + guesses.size() + "\nGuesses: " + guesses.toString() + "\n\n");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error saving results to file.");
                }
            }
        });
    }
}
