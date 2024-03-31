import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class toPDF
{
    public static Boolean convertpdf(File file,String address)
    {
        Boolean written=false;
        //Blank Document
        Document document = new Document();
        try
        {
            //Path of pdf file to be created if not existing already
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(address+"/License.pdf"));
            document.open();

            //Write the Document in file system
            Scanner readobj = new Scanner(file);
            //Loop until we find the EOF
            while (readobj.hasNextLine()){

                String dataInfo = readobj.nextLine();
                written=document.add(new Paragraph(dataInfo+"\n"));

            }

            readobj.close();
            document.close();
            writer.close();
        } catch (DocumentException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return written?true:false;
    }
}