package hackathon;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;


class GUI extends JFrame implements ActionListener {
    static JLabel l;

    public static void run()
    {
        JFrame f = new JFrame("Marks Upload");
        f.setSize(600, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        JButton button2 = new JButton("open");
        GUI f1 = new GUI();
 
        button2.addActionListener(f1);
 
        JPanel p = new JPanel();
 
        p.add(button2);
 
        l = new JLabel("no file selected");
        // add panel to the frame
        p.add(l);
        f.add(p);
 
        f.show();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        String com = evt.getActionCommand();
 
        if (com.equals("open")) {
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int r = j.showOpenDialog(null);
 
            if (r == JFileChooser.APPROVE_OPTION){
                String filePath = j.getSelectedFile().getAbsolutePath();
                String fileName = j.getSelectedFile().getName();
                System.out.println(filePath);
                l.setText(fileName + " (Uploading...)");
                if(HandleExcel.processFile(filePath)){
                    l.setText("Uploaded!");
                }else{
                    l.setText("Error: Something went wrong. Try againg.");
                }
            }
            else{
                l.setText("Choose the file");
            }
        }
    }
}