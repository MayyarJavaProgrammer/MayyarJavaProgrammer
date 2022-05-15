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
    byte requiredSystem = 0;
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
                if(!((textField.getText().equals("") || textField.getText().equals(" ")) 
                || fromSystemComboBox.getSelectedItem().equals(toSystemComboBox.getSelectedItem())
                )) 
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
        image.setIcon(new ImageIcon("binary.jpg"));

        ImageIcon img = new ImageIcon("calculator-icon.png");
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
    

    //from binary to OctalSystem/HexadecimalSystem
    public String BinaryTo_OctalSystem_HexadecimalSystem() {      
        /*if you want to convert to octal system you should div the number to 3 group to end all number 
         *and get the result that do when mod 2 ^ n(0) in the first number from right and n + 1 */
        int sizeof_setof_numbers = (requiredSystem == 8) ? 3 : 4;
        String resultNumber = "";
        int makeNumber = 0;
        
        //to Hit the desired number
        int binaryNumber = 1;
        char[]letters = {'A', 'B', 'C', 'D', 'E', 'F'};
        StringBuilder str = new StringBuilder(number);

            while( str.length() % sizeof_setof_numbers != 0)              
                    str.insert(0, '0');  

            for(int i = str.length() - 1 ; i >=  0 ; i--) {
                int letterNumber = Character.getNumericValue(str.charAt(i));

                makeNumber += letterNumber * binaryNumber;              
                if(i % sizeof_setof_numbers == 0){
                    if(makeNumber >= 10) 
                        resultNumber = letters[makeNumber - 10] + resultNumber;
                    else  
                        resultNumber =  String.valueOf(makeNumber) + resultNumber;

                    makeNumber = 0;
                }

                binaryNumber = (i % sizeof_setof_numbers == 0) ? 1 : binaryNumber * 2;
            }

            return resultNumber;       
    }

    //from any system to DecimalSystem
    public String anyToDecimalSystem() {
        long makeNumber = 0;
        int powerNumber = this.number.length() -1;
        long letterNumber;
        for(int i = 0 ; i  <= this.number.length() -1 ; i++) {

                letterNumber = Long.parseLong( String.valueOf(this.number.charAt(i)) );
           
            makeNumber +=  letterNumber * Math.pow(systemBasis, powerNumber);
            powerNumber--;
        }
        String resultNumber = String.valueOf(makeNumber);
        return resultNumber;
    }

    public String octalToBinarySystem() {
        
        String resultNumber = "";
        
        //result of calc 2^0....2^1... 2^2 with sum of result...
        char []binaryNumber = {'0', '1', '2', '3', '4', '5', '6', '7'};

        //result in binary system
        String []binary_Number = {"000", "001", "010", "011", "100", "101", "110", "111"};
              int i = number.length() -1 ;
                for(int j = 0 ; j < binaryNumber.length - 1 && i >= 0 ; j++) {
                    
                    if(number.charAt(i) == binaryNumber[j]) {
                        resultNumber = binary_Number[j] + resultNumber;
                        i--;
                        //To avoid the increment and make it equal to zero
                        j = -1;
                    }                 
                }
            return resultNumber;             
    }

    public String hexaToBinarySystem() {
        
        String resultNumber = "";
        char letterNumber;
        number = number.toUpperCase();
        
        //result of calc 2^0....2^1... 2^2 with sum of result...and letters in that system...
        char []binaryNumber = {'0', '1', '2', '3', '4', '5', '6', '7',
                                 '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        //result in binary system
        String []binary_Number = {"0000", "0001", "0010", "0011", "0100", "0101",
                                 "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
   
                //to get charAt(i) from input number....
                int i = number.length() -1 ;

                for(int j = 0 ; j < binaryNumber.length && i >= 0 ; j++) {
                    letterNumber =number.charAt(i);
                    
                    if(letterNumber == binaryNumber[j]) {
                        resultNumber = binary_Number[j] + resultNumber;
                        i--;
                        //To avoid the increment and make it equal to zero
                        j = -1;
                    }                 
                }
            return resultNumber;             
    }
    
    public boolean checkNumber() {

        for(int i = 0 ; i < number.length() ; i++) {
            /*if any character in input number bigger than system basis
             *print error to user... and retrun true to end the program */
            try{ 
                if(Integer.parseInt(String.valueOf(number.charAt(i)) ) >= this.systemBasis){ 
                    makeError();
                    return true;
                }
            }
            catch(NumberFormatException e) {
                makeError();
                return true;
            }
            catch(Exception e) {
                makeError();
                return true;
            }
            
        }

        cancelColorError();
        return false;
    }
  
    public boolean checkHexaNumber() {

        number = number.toUpperCase();
        
        for(int i = 0 ; i < number.length() ; i++) {
            
            if(Character.isDigit(number.charAt(i)))
               continue;

            else if((int)number.charAt(i)  > (int)'F') {
                makeError();
                return true;
            }
    
        }
        cancelColorError();
        return false;
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

    public byte replaceHexaLetters(char letter) {
        char[]letters = {'A', 'B', 'C', 'D', 'E', 'F'};
        byte[]letters_Number = {10, 11, 12, 13, 14, 15};
        for(byte i = 0 ; i < letters.length ; i++) {
            if(Character.toUpperCase(letter) == letters[i])
                return letters_Number[i];
        }
        return 0;
    }

    public String hexaToDecimalSystem() {
        int makeNumber = 0;
        int powerNumber = number.length() -1;
        int letterNumber;
        try { 
            for(int i = 0 ; i  <= number.length() -1 ; i++) {
                // if charcter in input number is a letter in hexadcimal system rplace it to number....
                if( Character.isLetter( number.charAt(i) ))
                    letterNumber = replaceHexaLetters(number.charAt(i));
                else
                    letterNumber = Integer.parseInt( String.valueOf(number.charAt(i)) );
            

                makeNumber +=  letterNumber * Math.pow(systemBasis, powerNumber);
                powerNumber--;
            }
        } 
        catch(NumberFormatException e) {
            makeError();
            return "";
        }
        catch(Exception e) {
            makeError();
            return "";
        }

       return String.valueOf(makeNumber);     
    }

    public String decimalToAnySytem() {
        String resultNumber = "";
        char[]letters = {'A', 'B', 'C', 'D', 'E', 'F'};
        long check = Long.parseLong(number);
        /*to get any system you have to div the decimal number on the required system basis even 
         *the result equals 0 you will sum all % of results and you will get the resutl */
        while(check != 0) {
            long item = check % requiredSystem;
            //if the number bigger than 9 you have to replace to latter in hexa system
            if(requiredSystem == 16 && item >= 10)
                resultNumber = String.valueOf(letters[(int)item-10]) + resultNumber;
            else
                resultNumber = String.valueOf(item) + resultNumber;

            check /= requiredSystem;
        }

        return resultNumber;
    }
 

    public String binaryChoices(Object choice) {
        systemBasis = 2;  
        if(checkNumber()) return "";
   
             // octal system 
            if(choice.equals(systems[1])) { 
                requiredSystem = 8;
                return BinaryTo_OctalSystem_HexadecimalSystem();
            }
            // hexa system
            else if(choice.equals(systems[2])) { 
                requiredSystem = 16;
                return BinaryTo_OctalSystem_HexadecimalSystem();
            }
            // decimal system
            else if(choice.equals(systems[3])) { 
                requiredSystem = 10;
                return anyToDecimalSystem();
            }
            // binary to binary -> return number
            else
                return number;
    }

    public String octalChoices(Object choice) {
        systemBasis = 8;
        if(checkNumber()) return "";

            // binary system 
            if(choice.equals(systems[0])) {
                requiredSystem = 2;
                return octalToBinarySystem();
            }
            // hexa system
            else if(choice.equals(systems[2])) {
                requiredSystem = 16;
                number = anyToDecimalSystem();         
                return decimalToAnySytem();
            }
            //decimal system 
            else if(choice.equals(systems[3])) { 
                requiredSystem = 10;
                return anyToDecimalSystem();
            }
            // octal to octal -> return number  
            else
                return number;    
    }

    public String hexadecimalChoices(Object choice) {
        systemBasis = 16;
        if(checkHexaNumber()) return "";

            // binary system 
            if(choice.equals(systems[0])) { 
                requiredSystem = 2;
                return hexaToBinarySystem();
            }
            // octal system
            else if(choice.equals(systems[1])) { 
                requiredSystem = 8;
                number = hexaToDecimalSystem();         
                return decimalToAnySytem();
            }
            //decimal system 
            else if(choice.equals(systems[3])) { 
                requiredSystem = 10;
                return hexaToDecimalSystem();
            }
            // hexa to hexa -> return number
            else 
                return number;
    }

    public String decimalChoices(Object choice) {
        systemBasis = 10;

            // binary system 
            if(choice.equals(systems[0])) { 
                requiredSystem = 2;
                return decimalToAnySytem();
            }
            // octal system
            else if(choice.equals(systems[1])) { 
                requiredSystem = 8;      
                return decimalToAnySytem();
            }
            //hexa system 
            else if(choice.equals(systems[2])) { 
                requiredSystem = 16;
                return decimalToAnySytem();
            }
            // decimal to decimal -> return number
            else
                return number;
    }

    public String getChoices() {

        if(fromSystemComboBox.getSelectedItem().equals(systems[0]))
            return binaryChoices(toSystemComboBox.getSelectedItem());
        
        else if(fromSystemComboBox.getSelectedItem().equals(systems[1]))
            return octalChoices(toSystemComboBox.getSelectedItem());
        
        else if(fromSystemComboBox.getSelectedItem().equals(systems[2]))
            return hexadecimalChoices(toSystemComboBox.getSelectedItem());
        
        else 
            return decimalChoices(toSystemComboBox.getSelectedItem());
    }

}
