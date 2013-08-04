/******************************************************************************
 * Program Name: Office Area Calculator
 * Programmers Name: Lamburt Alcantara
 * Program Description: GUI program to calculate the area of an office space.
 ******************************************************************************/

import java.text.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Container;
import java.awt.*;

public class OfficeAreaCalculator extends JFrame
{
    private JFrame mainFrame;
    private JButton calculateButton;
    private JButton exitButton;
    private JTextField lengthField;
    private JTextField widthField;
    private JTextField areaField;
    private JLabel lengthLabel;
    private JLabel widthLabel;
    private JLabel areaLabel;
    
    // Constructor
    public OfficeAreaCalculator()
    {
        mainFrame = new JFrame("Office Area Calculator");
        
        // Create all the components
        calculateButton = new JButton("Calculate area");
        exitButton = new JButton("Exit");
        lengthLabel = new JLabel("Enter the length of the office:");
        widthLabel = new JLabel("Enter the width of the office:");
        areaLabel = new JLabel("Office area:");
        lengthField = new JTextField(5);
        widthField = new JTextField(5);
        areaField = new JTextField(5);
        
        // Get the content pane
        Container c = mainFrame.getContentPane();
        // Set the layout managare
        c.setLayout(new FlowLayout());
        
        // Add the components to the ContentPane
        c.add(lengthLabel);
        c.add(lengthField);
        c.add(widthLabel);
        c.add(widthField);
        c.add(areaLabel);
        c.add(areaField);
        c.add(calculateButton);
        c.add(exitButton);
        
        // Create accelerator keys
        calculateButton.setMnemonic('C');
        exitButton.setMnemonic('x');
        
        mainFrame.setSize(250,150);
        
        // Define and register window event handler
        mainFrame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        
        // Create and register the button event handlers
        calculateButtonHandler chandler = new calculateButtonHandler();
        calculateButton.addActionListener(chandler);
        
        ExitButtonHandler ehandler = new ExitButtonHandler();
        exitButton.addActionListener(ehandler);
        
        FocusHandler fhandler = new FocusHandler();
        lengthField.addFocusListener(fhandler);
        widthField.addFocusListener(fhandler);
        areaField.addFocusListener(fhandler);
        
        mainFrame.show();
    }
    
    // Inner classes for the button event handlers
    class calculateButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            DecimalFormat num = new DecimalFormat(",###.##");
            double width, length, area;
            String instring;
            
            instring = lengthField.getText();
            if (instring.equals(""))
            {
                instring = "0";
                lengthField.setText("0");
            }
            length = Double.parseDouble(instring);
            
            instring = widthField.getText();
            if (instring.equals(""))
            {
                instring = "0";
                widthField.setText("0");
            }
            width = Double.parseDouble(instring);
            area = length * width;
            areaField.setText(num.format(area));
        }
    }  // End of inner class
    
    class ExitButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }  // End of inner class
    
    class FocusHandler implements FocusListener
    {
        public void focusGained(FocusEvent e)
        {
            if(e.getSource() == lengthField || e.getSource() == widthField)
            {
                areaField.setText("");
            }
            else if(e.getSource() == areaField)
            {
                areaField.setNextFocusableComponent(calculateButton);
                calculateButton.grabFocus();
            }
        }
        
        public void focusLost(FocusEvent e)
        {
            if(e.getSource() == widthField)
            {
                widthField.setNextFocusableComponent(calculateButton);
            }
        }
    }  // End of focus listener class
    
    public static void main(String args[])
    {
        new OfficeAreaCalculator();
    }
}
