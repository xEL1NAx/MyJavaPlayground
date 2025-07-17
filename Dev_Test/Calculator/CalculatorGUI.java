package Dev_Test.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField display;
    private double firstOperand = 0;
    private double secondOperand = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    public CalculatorGUI() {
        // Set up the JFrame
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));

        // Define button labels
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "sqrt", "^", "sin", "cos",
            "tan", "ln", "log10", "C"
        };

        // Add buttons to panel
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        // Add panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if (command.matches("\\d") || command.equals(".")) {
                // Number or decimal point
                if (isOperatorClicked) {
                    display.setText("");
                    isOperatorClicked = false;
                }
                display.setText(display.getText() + command);
            } else if (command.equals("C")) {
                // Clear
                display.setText("");
                firstOperand = secondOperand = 0;
                operator = "";
            } else if (command.equals("=")) {
                // Calculate result
                secondOperand = Double.parseDouble(display.getText());
                double result = calculate(firstOperand, secondOperand, operator);
                display.setText(String.valueOf(result));
                isOperatorClicked = true;
            } else if (command.equals("sqrt")) {
                double value = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.sqrt(value)));
                isOperatorClicked = true;
            } else if (command.equals("sin") || command.equals("cos") || command.equals("tan")) {
                double angle = Math.toRadians(Double.parseDouble(display.getText()));
                if (command.equals("sin")) display.setText(String.valueOf(Math.sin(angle)));
                if (command.equals("cos")) display.setText(String.valueOf(Math.cos(angle)));
                if (command.equals("tan")) display.setText(String.valueOf(Math.tan(angle)));
                isOperatorClicked = true;
            } else if (command.equals("ln") || command.equals("log10")) {
                double value = Double.parseDouble(display.getText());
                if (value <= 0) throw new IllegalArgumentException("Logarithm undefined for non-positive values");
                if (command.equals("ln")) display.setText(String.valueOf(Math.log(value)));
                if (command.equals("log10")) display.setText(String.valueOf(Math.log10(value)));
                isOperatorClicked = true;
            } else {
                // Operation (+, -, *, /, ^)
                firstOperand = Double.parseDouble(display.getText());
                operator = command;
                isOperatorClicked = true;
            }
        } catch (Exception ex) {
            display.setText("Error");
        }
    }

    private double calculate(double first, double second, String operator) {
        return switch (operator) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> second != 0 ? first / second : Double.NaN;
            case "^" -> Math.pow(first, second);
            default -> 0;
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalculatorGUI::new);
    }
}
