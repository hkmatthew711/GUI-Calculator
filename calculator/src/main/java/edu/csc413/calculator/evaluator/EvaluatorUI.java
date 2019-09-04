package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
        "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
        "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }




    private String expression = "";

    private Evaluator evaluator = new Evaluator();

    public void actionPerformed(ActionEvent arg0)
    {
        String input = arg0.getActionCommand();
        //Clicking on buttons that are not "=", "C", and "CE"
        if (!arg0.getSource().equals(buttons[14])
                && !arg0.getSource().equals(buttons[18])
                && !arg0.getSource().equals(buttons[19]))
        {
            this.expression += input;
            this.txField.setText(expression); //set to display the input
        }
        //Get the result
        else if(arg0.getSource().equals(buttons[14]))
        {
            int result = this.evaluator.eval(this.expression); //put the input expression to calculation
            this.expression = "";
            this.txField.setText(String.valueOf(result)); //set to display the result
        }
        //C -- Clears the entire input
        else if(arg0.getSource().equals(buttons[18]))
        {
            this.expression = "";
            this.txField.setText(expression);
        }
        //CE -- Clears the last entry input
        else if(arg0.getSource().equals(buttons[19]))
        {
            int endOfString = expression.length()-1;
            if(!expression.isEmpty())
            {
                this.expression = this.expression.substring(0, endOfString); //update the expression with each click on CE
            }
            this.txField.setText(expression);
        }
        //Print out the input and result on the console
        System.out.println(txField.getText());
    }
}
