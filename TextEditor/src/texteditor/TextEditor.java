package texteditor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.io.*;
public class TextEditor extends JFrame implements ActionListener{
JTextArea area;
String text;
    TextEditor(){
    
    setTitle("TextEditor");//setting title
    ImageIcon texteditorIcon=new ImageIcon(ClassLoader.getSystemResource("texteditor/icons/TextEditor.png"));  
    Image icon=texteditorIcon.getImage();//converting to img
    setIconImage(icon);//setting image 
    
    JMenuBar menubar =new JMenuBar();//creating obj menubar
    menubar.setBackground(Color.WHITE);//setting bg color of menubar as white
    
    
    JMenu file=new JMenu("File");//creating file menu on menubar
    file.setFont(new Font("AERIAL",Font.PLAIN,14));//setting font font family,font type,font size 
 
    JMenuItem newdoc=new JMenuItem("New");
    newdoc.addActionListener(this);
    newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
    
    JMenuItem open=new JMenuItem("Open");
     open.addActionListener(this);
    open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
    
     JMenuItem save=new JMenuItem("Save");
      save.addActionListener(this);
    save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
    
     JMenuItem print=new JMenuItem("Print");
      print.addActionListener(this);
    print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
    
     JMenuItem exit=new JMenuItem("Exit");
      exit.addActionListener(this);
    exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,ActionEvent.CTRL_MASK));
    
     
    file.add(newdoc);
    file.add(open);
    file.add(save);
    file.add(print);
    file.add(exit);
    
    menubar.add(file);//adding file menu to menubar
    
    JMenu edit=new JMenu("Edit");//creating edit menu on menubar
    edit.setFont(new Font("AERIAL",Font.PLAIN,14));//setting font- font family,font type,font size 
 
    JMenuItem copy=new JMenuItem("Copy");
     copy.addActionListener(this);
    copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
    
    JMenuItem paste=new JMenuItem(" Paste");
     paste.addActionListener(this);
    paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
    
     JMenuItem cut=new JMenuItem("Cut");
      cut.addActionListener(this);
    cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
    
     JMenuItem selectall=new JMenuItem("Select All");
     selectall.addActionListener(this);
    selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
    
    edit.add(copy);
    edit.add(paste);
    edit.add(cut);
    edit.add(selectall);
    
    menubar.add(edit);
    
    JMenu helpmenu=new JMenu("Help");//creating edit menu on menubar
    helpmenu.setFont(new Font("AERIAL",Font.PLAIN,14));//setting font- font family,font type,font size 
 
    JMenuItem about=new JMenuItem("About");
   about.addActionListener(this);
    about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
    
    helpmenu.add(about);
   
    menubar.add(helpmenu); 
    
    setJMenuBar(menubar);//adding menubar to screen
    
    area=new JTextArea();
    area.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
    area.setLineWrap(true);
    area.setWrapStyleWord(true);
    
    JScrollPane pane=new JScrollPane(area);
    pane.setBorder(BorderFactory.createEmptyBorder());
    add(pane);
    
    setExtendedState(JFrame.MAXIMIZED_BOTH);//set frame to full size
    
   setVisible(true);//reflects the changes
}
@Override
  public void actionPerformed(ActionEvent ae){
      if(ae.getActionCommand().equals("New")){
     area.setText(""); 
      }
      else if(ae.getActionCommand().equals("Open")){
          JFileChooser chooser=new JFileChooser();
          chooser.setAcceptAllFileFilterUsed(false);
          FileNameExtensionFilter restrict=new FileNameExtensionFilter("Only .txt files", "txt");
         chooser.addChoosableFileFilter(restrict); 
         
        int action= chooser.showOpenDialog(this);
        if(action != JFileChooser.APPROVE_OPTION){
            return;
        }
        File file=chooser.getSelectedFile();
        try{
            BufferedReader reader=new BufferedReader(new FileReader(file));
        area.read(reader,null);
        }catch (Exception e){
            e.printStackTrace();
      }
}else if(ae.getActionCommand().equals("Save")){
              JFileChooser saveas = new JFileChooser();
              saveas.setApproveButtonText("Save");
              
              int action=saveas.showOpenDialog(this);
              if(action !=JFileChooser.APPROVE_OPTION){
              return;
              }
              File filename=new File(saveas.getSelectedFile() + ".txt");
              BufferedWriter outFile=null;
              try{
              outFile=new BufferedWriter(new FileWriter(filename));
              area.write(outFile);
              }catch(Exception e){
              e.printStackTrace();
              }
              }else if(ae.getActionCommand().equals("Print")){
                try{
                    area.print();
                }
                  catch(Exception e){
                          e.printStackTrace();
              }
      
  }else if(ae.getActionCommand().equals("Exit")){
      System.exit(0);
  }else if(ae.getActionCommand().equals("Copy")){
      text = area.getSelectedText();
  }else if(ae.getActionCommand().equals("Paste")){
      area.insert(text,area.getCaretPosition());
  }else if(ae.getActionCommand().equals("Cut")){
      text=area.getSelectedText();
      area.replaceRange("",area.getSelectionStart(),area.getSelectionEnd());
  }else if(ae.getActionCommand().equals("Select All")){
       area.selectAll();
  }else if(ae.getActionCommand().equals("About")){
      new About().setVisible(true);
  }
      
  }
  
    public static void main(String[] args) {
     new TextEditor();
    }
    
}
