/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package firstproject;

/**
 *
 * @author Pawel
 */

import javax.swing.*; // - pakiet do tworzenia ramek
import java.awt.*; // awt - abstract window toolikt
import java.awt.event.*;

public class FirstProject extends JFrame implements WindowListener
{
    
    public FirstProject()
    {
    super("First Project");
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.setSize(600, 600);
    this.setJMenuBar(pasekMenu);
    JMenu plik = pasekMenu.add(new JMenu("Plik"));
    JMenu oProgramie = pasekMenu.add(new JMenu("O programie"));
    JMenuItem zapisz = plik.add(new JMenuItem ("Zapisz"));
    JMenuItem info = oProgramie.add(new JMenuItem ("Informacje"));
    plik.add(doOdczytu);
    
    doOdczytu.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
        if (doOdczytu.isSelected())
            zapisz.setEnabled(false);
        else
            zapisz.setEnabled(true);
        }
    });
    
    info.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            JOptionPane.showMessageDialog(rootPane, "Ten program pozwoli Ci na zmianę wyglądu napisu" + " '" + "Zmień mnie" + "'" + ".");
        }
    });
    
    
    plik.addSeparator();
    JMenuItem zakoncz = plik.add(new JMenuItem ("Zakończ"));
    
    zakoncz.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
        int opcja = JOptionPane.showConfirmDialog(rootPane, "Czy na pewno chcesz zamknąć program?");
            if (opcja == 0)
                System.exit(0);
        }
    });


    int ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    int ProjectWidth = this.getSize().width;
    int ProjectHeight = this.getSize().height;
    this.setLocation((ScreenWidth-ProjectWidth)/2,(ScreenHeight-ProjectHeight)/2);
    initComponents();
    }
   
      public void createBackgroundButtons (String nazwa, Color kolor)
    {
    JButton backgroundButton = new JButton (nazwa);
    
    backgroundButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
           panel9.setBackground(kolor);
        }
    });
    backgroundColorGroup.add(backgroundButton);
    pasekNarzedzi.add(backgroundButton);
    }
    
    public void initComponents()
    {
        
        this.getContentPane().setLayout(new GridLayout (9,1));
        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.getContentPane().add(panel4);
        this.getContentPane().add(panel5);
        this.getContentPane().add(panel3);
        this.getContentPane().add(panel6);
        this.getContentPane().add(panel7);
        this.getContentPane().add(panel8);
        this.getContentPane().add(panel9);
        this.addWindowListener(this);
        
        createButtons ("Mały", 15);
        createButtons ("Średni", 25);
        createButtons ("Duży", 35);
        
        createRadioButtons ("Czerwony", Color.RED);
        createRadioButtons ("Niebieski", Color.BLUE);
        createRadioButtons ("Zielony", Color.GREEN);
        

              
        panel2.add(pasekNarzedzi);
        pasekNarzedzi.add(kolorTla);
        panel5.add(napis);
        panel6.add(bold, BorderLayout.LINE_START);
        panel6.add(italic, BorderLayout.LINE_END);
        panel7.add(list);
        panel8.add(pytanie);
        panel8.add(odpowiedz);
        list.addItem(new FontHandler("Czcionka Monospaced", Font.MONOSPACED));
        list.addItem(new FontHandler("Czcionka Serif", Font.SERIF));
        list.addItem(new FontHandler("Czcionka Sans Serif", Font.SANS_SERIF));
        
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
           
            napis.setFont(new Font(((FontHandler)((JComboBox)ae.getSource()).getSelectedItem()).font, napis.getFont().getStyle(), napis.getFont().getSize()));
            }
        });
        
        
               
        ActionListener listener = new CheckBoxHandler();
        bold.addActionListener(listener);
        italic.addActionListener(listener);
        
             
     
    }

    @Override
    public void windowOpened(WindowEvent we) {
         JOptionPane.showMessageDialog(rootPane, "Witaj w moim super hiper programie :P Wykorzystaj różnego rodzaju opcje, aby zmienić wygląd poniższego tekstu!");
    }

    @Override
    public void windowClosing(WindowEvent we) {
         int opcja = JOptionPane.showConfirmDialog(rootPane, "Czy na pewno chcesz zamknąć ten super ekstra program?? :(");
        if (opcja == 0)
            this.dispose(); 
    }

    @Override
    public void windowClosed(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent we) {
        this.isActive();
    }

    @Override
    public void windowDeactivated(WindowEvent we) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public class FontHandler
    {
        public FontHandler (String fontName, String font)
        {
            this.fontName = fontName;
            this.font = font;
        }

         @Override
        public String toString()
        {
            return this.fontName;
        }
    
        String fontName;
        String font;
        
    }
    
   
    public class CheckBoxHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae)
        {
            int style = Font.PLAIN;
            
            if(bold.isSelected()) style += Font.BOLD;
            if(italic.isSelected()) style += Font.ITALIC;
            
            napis.setFont(new Font(napis.getFont().getFamily(), style, napis.getFont().getSize()));
        }
    }
    
    public void createButtons (String nazwa, final int rozmiar)
    {
    JButton button = new JButton (nazwa);
    
    button.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
           napis.setFont(new Font(napis.getFont().getFamily(), napis.getFont().getStyle(), rozmiar));
        }
    });
    sizeGroup.add(button);
    panel3.add(button);
    }
    
    public void createRadioButtons (String nazwa, Color kolor)
    {
    JRadioButton radioButton = new JRadioButton (nazwa);
    
    radioButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
           napis.setForeground(kolor);
        }
    });
    colorGroup.add(radioButton);
    panel4.add(radioButton);
    }
    
   
   
    JCheckBox bold = new JCheckBox("Pogrubiony");
    JCheckBox italic = new JCheckBox("Pochylony");
   
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel();
    JPanel panel7 = new JPanel();
    JPanel panel8 = new JPanel();
    JPanel panel9 = new JPanel();
    JToolBar pasekNarzedzi = new JToolBar();   
    JLabel kolorTla = new JLabel("Naciśnij aby zmienić kolor dolnego panelu: ");
    JLabel dolnyPanel = new JLabel("Dolny Panel");
    JMenuBar pasekMenu = new JMenuBar();
    JCheckBoxMenuItem doOdczytu = new JCheckBoxMenuItem("Tylko do odczytu");
    JComboBox list = new JComboBox();
    JLabel napis = new JLabel("Zmień mnie");
    JLabel pytanie = new JLabel("Do you like it :D?   ");
    JTextField odpowiedz = new JTextField(15);
    ButtonGroup sizeGroup = new ButtonGroup();
    ButtonGroup colorGroup = new ButtonGroup();
    ButtonGroup styleGroup = new ButtonGroup();
    ButtonGroup backgroundColorGroup = new ButtonGroup();
   
     
    
    
    public static void main(String[] args) {
        new FirstProject().setVisible(true);
    }
    
}
