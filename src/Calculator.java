import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Calculator implements ActionListener, KeyListener {

    JFrame frame;
    JTextField textField;

    MyButton[] numberButtons = new MyButton[10];
    MyButton[] functionButtons = new MyButton[9];
    MyButton addButton, subButton, mulButton, divButton;
    MyButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 60);
        textField.setFont(new Font("Verdana", Font.PLAIN, 35));
        textField.setEditable(false);
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.GREEN);
        textField.addKeyListener(this);

        addButton = new MyButton("+");
        subButton = new MyButton("-");
        mulButton = new MyButton("*");
        divButton = new MyButton("/");
        decButton = new MyButton(".");
        equButton = new MyButton("=");
        delButton = new MyButton("Del");
        clrButton = new MyButton("Clr");
        negButton = new MyButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].addKeyListener(this);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new MyButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].addKeyListener(this);
            numberButtons[i].setFocusable(false);
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 110, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setTitle("Calculator app by Dobro");
        frame.setVisible(true);
    }

    //Actions when button is pressed with the mouse
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == addButton) {
            setOperator('+');
        }
        if (e.getSource() == subButton) {
            setOperator('-');
        }
        if (e.getSource() == mulButton) {
            setOperator('*');
        }
        if (e.getSource() == divButton) {
            setOperator('/');
        }

        if (e.getSource() == decButton) {
            setDecimal();
        }
        if (e.getSource() == equButton) {
            setResult();
        }
        if (e.getSource() == clrButton) {
            clearTextField();
        }
        if (e.getSource() == delButton) {
            deleteChar();
        }
        if (e.getSource() == negButton) {
            String tempText = textField.getText();
            if (!tempText.isEmpty()) {
                Double tempDouble = Double.parseDouble(tempText);
                tempDouble *= -1;
                textField.setText(String.valueOf(tempDouble));
            }
        }
    }

    //Actions when buttons is pressed from the keyboard
    @Override
    public void keyTyped(KeyEvent e) {

        char digit = e.getKeyChar();
        if (Character.isDigit(digit)) {
            textField.setText(textField.getText() + digit);
        }
        if ((e.getKeyChar() == '+') || (e.getKeyChar() == '-') || (e.getKeyChar() == '*') || (e.getKeyChar() == '/')) {
            setOperator(e.getKeyChar());
        }
        if (e.getKeyChar() == '.') {
            setDecimal();
        }
        if (e.getKeyChar() == '\n') {
            setResult();
        }
        if (e.getKeyChar() == '\u001B') {
            clearTextField();
        }
        if (e.getKeyChar() == '\b') {
            deleteChar();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //Calcul methods

    //Setting what kind of operation is
    public void setOperator(char character) {
        if (!textField.getText().isEmpty()) {
            num1 = Double.parseDouble(textField.getText());
        } else {
            num1 = 0;
        }
        operator = character;
        textField.setText("");
    }

    //Calculating and showing the result
    public void setResult() {
        if (!textField.getText().isEmpty()) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
            }//Transforming the double result in integer result (if it is necesary)
            double tempInt = (double) (int) result;
            double tempDec = result - tempInt;
            if (tempDec == 0) {
                textField.setText(String.valueOf((int) result));
            } else {
                textField.setText(String.valueOf(result));
            }
            operator = ' ';
            num1 = 0;
            num2 = 0;
        }
    }

    public void setDecimal() {
        textField.setText(textField.getText().concat("."));
    }

    public void clearTextField() {
        textField.setText("");
        num1 = 0;
        num2 = 0;
    }

    public void deleteChar() {
        String string = textField.getText();
        if (!string.isEmpty()) {
            textField.setText(string.substring(0, string.length() - 1));
        }
    }

}