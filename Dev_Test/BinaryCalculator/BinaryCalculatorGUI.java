package Dev_Test.BinaryCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryCalculatorGUI {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Binary Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        // Create GUI components
        JLabel label1 = new JLabel("First Binary Number:");
        JTextField binary1Field = new JTextField();

        JLabel label2 = new JLabel("Second Binary Number:");
        JTextField binary2Field = new JTextField();

        JLabel label3 = new JLabel("Operation (+, -, *, /):");
        JTextField operationField = new JTextField();

        JButton calculateButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel("Result: ");

        // Add components to the frame
        frame.add(label1);
        frame.add(binary1Field);
        frame.add(label2);
        frame.add(binary2Field);
        frame.add(label3);
        frame.add(operationField);
        frame.add(new JLabel()); // Empty space
        frame.add(calculateButton);
        frame.add(new JLabel()); // Empty space
        frame.add(resultLabel);

        // Action listener for the calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String binary1 = binary1Field.getText();
                String binary2 = binary2Field.getText();
                String operation = operationField.getText();

                try {
                    int num1 = Integer.parseInt(binary1, 2);
                    int num2 = Integer.parseInt(binary2, 2);
                    int result = 0;

                    switch (operation) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                resultLabel.setText("Result: Division by zero!");
                                return;
                            }
                            result = num1 / num2;
                            break;
                        default:
                            resultLabel.setText("Result: Invalid operation!");
                            return;
                    }

                    // Display the result in both binary and decimal
                    resultLabel.setText("Result: " + Integer.toBinaryString(result) + " (Binary), " + result + " (Decimal)");

                } catch (NumberFormatException ex) {
                    resultLabel.setText("Result: Invalid binary input!");
                }
            }
        });
        frame.setVisible(true);
    }
}
