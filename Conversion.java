import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class Conversion extends JFrame implements ActionListener {
    //All Global Components
    JComboBox format;
    JTextArea textArea;
    JLabel formattype,filelocation;
    JTextField location;
    JButton back,convertformat;

    Conversion(){
        setSize(1000,700);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);


        //Adding the TextArea with ScrollPane
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.decode("#E7EDF9"));
        JLabel heading=new JLabel("License Agreement");
        heading.setBounds(280,0,2000,100);
        heading.setFont(new Font(Font.MONOSPACED,Font.BOLD,40));
        heading.setForeground(Color.decode("#0068C8"));
        add(heading);


        getContentPane().setLayout(new FlowLayout());
        textArea = new JTextArea(30, 80);
        textArea.setMargin(new Insets(20, 20, 20, 20));
        //Adding the template file data in textArea for user to make further changes
        try{
        File obj = new File("License.txt");
        Scanner robj = new Scanner(obj);

        while (robj.hasNextLine()){

            String dataInfo = robj.nextLine();
            textArea.append(dataInfo+"\n");

        }

        robj.close();}
catch(FileNotFoundException e){
    System.out.println("Error occurred.");

    e.printStackTrace();


    }
        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setBounds(100,150,200,200);
        getContentPane().add(scrollableTextArea);

        filelocation=new JLabel("File Location :");
        filelocation.setBounds(20,20,40,40);

        add(filelocation);

        location=new JTextField("Enter your destination Directory :");
        location.setBounds(20,20,1000,40);

        add(location);

        formattype=new JLabel("File Format :");
        formattype.setBorder(BorderFactory.createEmptyBorder(10,200,10,10));

        add(formattype);

        String[] str={"TXT","DOCX","PDF"};
        format=new JComboBox(str);
        format.setBorder(BorderFactory.createEmptyBorder(10,10,10,100));
        add(format);

        back=new JButton("Back");
        back.setBorder(BorderFactory.createEmptyBorder(10,80,10,80));
        back.setBackground(Color.decode("#0068C8"));
        back.setForeground(Color.white);
        back.addActionListener(this);

        add(back);

        convertformat=new JButton("Save");
        convertformat.setBorder(BorderFactory.createEmptyBorder(10,80,10,80));
        convertformat.setBackground(Color.decode("#0068C8"));
        convertformat.setForeground(Color.white);
        convertformat.addActionListener(this);
        add(convertformat);




        setVisible(true);




    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource()==back){
            setVisible(false);
            new Main();
        }
        else if(actionEvent.getSource()==convertformat){
            String formatting=format.getSelectedItem().toString();
            String address=location.getText();
            String text=textArea.getText();
            //Updating the template file with further changes in textArea
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("License.txt"))){
                    System.out.println(text);
                    writer.write(text);
                    writer.newLine();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            try{

                if (formatting=="TXT"){
                    FileInputStream fin=new FileInputStream("./License.txt");
                    FileOutputStream fout=new FileOutputStream(address+"/License.txt");
                    int c;
                    while ((c = fin.read()) != -1) {
                        fout.write(c);
                    }
                    JOptionPane.showMessageDialog(null,"File Succesfully created at :"+address+"/License.txt");

                    fin.close();
                    fout.close();
                }
                else if (formatting=="DOCX"){
                   if( new toDOCX().convertDOCX(new File("./License.txt"),address)){
                       JOptionPane.showMessageDialog(null,"File Succesfully created at :"+address+"/License.docx");
                   }
                   else{
                       JOptionPane.showMessageDialog(null,"Error! Directory doesn't exist.");

                   }
                }
                else if (formatting=="PDF"){
                    if (new toPDF().convertpdf(new File("./License.txt"),address)){
                        JOptionPane.showMessageDialog(null,"File Succesfully created at :"+address+"/License.pdf");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Error! Directory doesn't exist.");

                    }
                }

            }
            catch(IOException e){
                e.printStackTrace();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}