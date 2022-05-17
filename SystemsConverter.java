import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JPopupMenu.Separator;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class SystemsConverter {
    JFrame frame = new JFrame("Systems converter");
    JTextField  textField = new JTextField();
    JLabel fromSystemLabel = new JLabel(" From");
    String []systems = {"Binary System", "Octal System", "Hexadecimal System", "Decimal System"};
    JComboBox fromSystemComboBox = new JComboBox(systems);
    JLabel toSystemLabel = new JLabel(" To");
    JComboBox toSystemComboBox = new JComboBox(systems);
    JButton getResult = new JButton("=");
    JLabel resultLabel = new JLabel();
    Separator separator = new Separator();
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JLabel image = new JLabel("");
    Font newFont = new Font("Arail", Font.BOLD, 16);
    byte systemBasis = 0;
    String number = "";
    public static void main(String[] args) throws Exception {
       SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
            new SystemsConverter().makeFrame();   
            
        }
           
       });
    }

    public void makeFrame() {

        KeyListener  enterKey = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e){}   
          

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    getResult.doClick();
                }
                
            }

            @Override
            public void keyReleased(KeyEvent e){}   

        };

        textField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                textField.setBackground(Color.white);
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.setBackground(new Color(9, 255, 236));
            }

        });

       
        getResult.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!inputsNotAllowed())
                { 
                    number = textField.getText();   
                    resultLabel.setText(getChoices());
                    panel.setVisible(true);
                }
            }
        });
        
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(880, 200);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        fromSystemLabel.setBounds(125, 23, 60, 20);
        fromSystemLabel.setForeground(Color.WHITE);
        fromSystemLabel.setFont(newFont);
        fromSystemComboBox.addKeyListener(enterKey);

        toSystemLabel.setBounds(355, 23, 40, 20);
        toSystemLabel.setForeground(Color.WHITE);
        toSystemLabel.setFont(newFont);
        toSystemComboBox.setSelectedItem(systems[3]);
        toSystemComboBox.addKeyListener(enterKey);
        
        resultLabel.setForeground(Color.black);
        resultLabel.setFont(newFont);

        panel.setVisible(false);
        panel.setBounds(685, 21, 210, 23);
        panel.add(resultLabel);
        
        textField.setBounds(10, 20, 110, 25);
        textField.setFont(new Font("Arail", Font.BOLD, 15));
        textField.addKeyListener(enterKey);

        fromSystemComboBox.setBounds(195, 20, 150, 25);     
        fromSystemComboBox.setBackground(Color.WHITE);

        toSystemComboBox.setBounds(400, 20, 150, 25);
        toSystemComboBox.setBackground(Color.WHITE);

        getResult.setBounds(580, 20, 80, 25);
        getResult.setBackground(new Color(9, 255, 236));
        getResult.setFont(newFont);
        getResult.addKeyListener(enterKey);

        image.setBounds(0, 0, 880, 200);
        //""
        image.setIcon(new ImageIcon(SystemsConverter.class.getResource("binary.jpg")));

        ImageIcon img = new ImageIcon(SystemsConverter.class.getResource("calculator-icon.png"));
        frame.setIconImage(img.getImage());
        frame.add(textField);
        frame.add(fromSystemLabel);
        frame.add(fromSystemComboBox);
        frame.add(toSystemLabel);
        frame.add(toSystemComboBox);
        frame.add(getResult);
        frame.add(panel);
        frame.add(image);
    }

    public String toBinary() {
        return Long.toBinaryString(Long.parseLong(number, systemBasis));          
    }
    
    public String toOctal() {
        return Long.toOctalString(Long.parseLong(number, systemBasis));
    }
    
    public Long toDecimal() {
        return Long.parseLong(number, systemBasis) ;
    }

    public String toHexadecimal() {
        return Long.toHexString(Long.parseLong(number, systemBasis));
    }

    public void makeError() {
        textField.setBackground(new Color(255, 56, 56));
        getResult.setBackground(new Color(255, 56, 56));
        Toolkit.getDefaultToolkit().beep();
    }

    public void cancelColorError() {
        textField.setBackground(Color.WHITE);
        getResult.setBackground(new Color(9, 255, 236));
    }
    //textField.getText().isEmpty() && textField.getText().indexOf(" ") != -1 
    // && (fromSystemComboBox.getSelectedItem().equals(toSystemComboBox.getSelectedItem())

     public boolean inputsNotAllowed() {
        return textField.getText().isBlank()
        || fromSystemComboBox.getSelectedItem().equals(toSystemComboBox.getSelectedItem());
    }
    public String binaryChoices(Object choice) {
        systemBasis = 2;  
   
             // octal system 
            if(choice.equals(systems[1])) { 
                return toOctal();
            }
            // hexa system
            else if(choice.equals(systems[2])) { 
               return toHexadecimal();
            }
            // decimal system
            else if(choice.equals(systems[3])) { 
                return toDecimal() + "";
            }
            // binary to binary -> return number
            else
                return number;
    }

    public String octalChoices(Object choice) {
        systemBasis = 8;

            // binary system 
            if(choice.equals(systems[0])) {
                return toBinary();
            }
            // hexa system
            else if(choice.equals(systems[2])) {      
                return toHexadecimal();
            }
            //decimal system 
            else if(choice.equals(systems[3])) { 
                return toDecimal() + "";
            }
            // octal to octal -> return number  
            else
                return number;    
    }

    public String hexadecimalChoices(Object choice) {
        systemBasis = 16;

            // binary system 
            if(choice.equals(systems[0])) {         
                return toBinary();
            }
            // octal system
            else if(choice.equals(systems[1])) { 
                return toOctal();
            }
            //decimal system 
            else if(choice.equals(systems[3])) { 
                return toDecimal() + "";
            }
            // hexa to hexa -> return number
            else 
                return number;
    }

    public String decimalChoices(Object choice) {
        systemBasis = 10;
            // binary system 
            if(choice.equals(systems[0])) { 
                return toBinary();
            }
            // octal system
            else if(choice.equals(systems[1])) {     
                return toOctal();
            }
            //hexa system 
            else if(choice.equals(systems[2])) {
                return toHexadecimal();
            }
            // decimal to decimal -> return number
            else
                return number;
    }

    public String getChoices() {
        try {
            cancelColorError();
            if(fromSystemComboBox.getSelectedItem().equals(systems[0]))
                return binaryChoices(toSystemComboBox.getSelectedItem());
            
            else if(fromSystemComboBox.getSelectedItem().equals(systems[1]))
                return octalChoices(toSystemComboBox.getSelectedItem());
            
            else if(fromSystemComboBox.getSelectedItem().equals(systems[2]))
                return hexadecimalChoices(toSystemComboBox.getSelectedItem());
            
            else 
                return decimalChoices(toSystemComboBox.getSelectedItem());
        } catch(Exception e) {
            makeError();
            return "";
        }
    }

}
