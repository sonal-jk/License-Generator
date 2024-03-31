import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

import com.itextpdf.text.Paragraph;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class toDOCX {
 public static Boolean convertDOCX(File file,String address)throws Exception  {
  Boolean written=false;
  //Blank Document
  XWPFDocument document = new XWPFDocument();
  //Path of docx file to be created if not existing already
  FileOutputStream out = new FileOutputStream( new File(address+"/License.docx"));

  XWPFParagraph paragraph;
  XWPFRun run;

  //Write the Document in file system
  Scanner readobj = new Scanner(file);
  //Loop until we find the EOF
  while (readobj.hasNextLine()){
   paragraph = document.createParagraph();
   run= paragraph.createRun();
   String dataInfo = readobj.nextLine();
   run.setText(dataInfo+"\n");
   written=true;
  }
  document.write(out);
  out.close();
  readobj.close();
  return written?true:false;
 }
}