import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Main extends JFrame implements ActionListener {
    //All Global Components
    JLabel heading,subheading,licensor,licensee,Email1,Email2,software,date;
    JTextField licenseename,licensorname,email1name,email2name,softwarename;
    JPanel subpanel;
    JDateChooser dateChooser;
    JButton next;


    public Main(){
        setName("License Generator");
        setSize(1000,700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.decode("#E7EDF9"));

        //All Components in Frame
       heading=new JLabel("License Generator");
       heading.setBounds(280,10,2000,100);
       heading.setFont(new Font(Font.MONOSPACED,Font.BOLD,50));
       heading.setForeground(Color.decode("#0068C8"));
       add(heading);

       subheading=new JLabel("License Information");
       subheading.setBounds(60,100,500,100);
       subheading.setFont(new Font(Font.SERIF,Font.BOLD,30));
       subheading.setForeground(Color.decode("#0068C8"));
       add(subheading);

        subpanel=new JPanel();
        subpanel.setBounds(60,180,880,420);
        subpanel.setBorder(new EmptyBorder(10,10,10,10));
        subpanel.setBackground(Color.decode("#ffffff"));
        subpanel.setLayout(null);
        add(subpanel);

        licensor=new JLabel("Licensor");
        licensor.setBounds(60,40,200,80);
        licensor.setFont(new Font(Font.SERIF,Font.BOLD,20));
        licensor.setForeground(Color.decode("#0068C8"));
        subpanel.add(licensor);



        Email1=new JLabel("Email");
        Email1.setBounds(500,40,200,80);
        Email1.setFont(new Font(Font.SERIF,Font.BOLD,20));
        Email1.setForeground(Color.decode("#0068C8"));
        subpanel.add(Email1);


        licensorname=new JTextField("Owner/Developer name");
        licensorname.setBounds(60,100,300,40);
        subpanel.add(licensorname);

        email1name=new JTextField("Owner/Developer Email");
        email1name.setBounds(500,100,300,40);
        subpanel.add(email1name);


        licensee=new JLabel("Licensee");
        licensee.setBounds(60,140,200,80);
        licensee.setFont(new Font(Font.SERIF,Font.BOLD,20));
        licensee.setForeground(Color.decode("#0068C8"));
        subpanel.add(licensee);

        Email2=new JLabel("Email");
        Email2.setBounds(500,140,200,80);
        Email2.setFont(new Font(Font.SERIF,Font.BOLD,20));
        Email2.setForeground(Color.decode("#0068C8"));
        subpanel.add(Email2);

        licenseename=new JTextField("Individual/Entity getting rights");
        licenseename.setBounds(60,200,300,40);
        subpanel.add(licenseename);

        email2name=new JTextField("Individual/Entity Enail");
        email2name.setBounds(500,200,300,40);
        subpanel.add(email2name);

        software=new JLabel("Software");
        software.setBounds(60,240,200,80);
        software.setFont(new Font(Font.SERIF,Font.BOLD,20));
        software.setForeground(Color.decode("#0068C8"));
        subpanel.add(software);

        date=new JLabel("Date");
        date.setBounds(500,240,200,80);
        date.setFont(new Font(Font.SERIF,Font.BOLD,20));
        date.setForeground(Color.decode("#0068C8"));
        subpanel.add(date);

        softwarename=new JTextField("Software Name");
        softwarename.setBounds(60,300,300,40);
        subpanel.add(softwarename);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(500, 300, 300, 40);
        subpanel.add(dateChooser);

        next=new JButton("Next");
        next.setBounds(800,610,90,40);
        next.setFont(new Font(Font.MONOSPACED,Font.BOLD,15));
        next.setBackground(Color.decode("#0068C8"));
        next.setForeground(Color.white);
        next.addActionListener(this);
        add(next);
        setVisible(true);


    }


    public static void main(String[] args){

        new Main();

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==next){

            String inputFile = "LicenseInput.txt";
            String outputFile = "License.txt";
            String licensee=licenseename.getText();
            String licensor=licensorname.getText();
            String soft=softwarename.getText();
            String dateoflicense= ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();

            String searchlicensee="[Licensee]";
            String searchLicensor="[Licensor]";
            String searchSoft="[Software]";
            String searchdate="[Date]";

            //Adding user input in Template File/Replacing Template Values
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    // Check if the line contains the specific text to replace
                    if (line.contains(searchlicensee)) {
                        // Replace the specific text with the new text
                        line = line.replace(searchlicensee, licensee);
                    }
                    if (line.contains(searchLicensor)){
                        line=line.replace(searchLicensor,licensor);
                    }
                    if (line.contains(searchSoft)){
                        line=line.replace(searchSoft,soft);
                    }
                    if (line.contains(searchdate)){
                        line=line.replace(searchdate,dateoflicense);
                    }
                    // Write the modified or unmodified line to the output file
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("File modification completed successfully.");

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            //Changing Frame
            setVisible(false);
            new Conversion().setVisible(true);


        }
    }
}