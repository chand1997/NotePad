
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.plaf.metal.*;

public class NotePad extends JFrame implements ActionListener{
    JTextArea t;
    JFrame f;
    JScrollPane sp;
    NotePad() {
        t = new JTextArea();
        f = new JFrame("NotePad");
        sp=new JScrollPane(t);

        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("file");
        JMenuItem f1 = new JMenuItem("new");
        JMenuItem f2 = new JMenuItem("open");
        JMenuItem f3 = new JMenuItem("save");
        JMenuItem f4 = new JMenuItem("print");

        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);

        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);


        JMenu edit = new JMenu("edit");
        JMenuItem f5 = new JMenuItem("cut");
        JMenuItem f6 = new JMenuItem("copy");
        JMenuItem f7 = new JMenuItem("paste");


        f5.addActionListener(this);
        f6.addActionListener(this);
        f7.addActionListener(this);


        edit.add(f5);
        edit.add(f6);
        edit.add(f7);

        JMenuItem close=new JMenuItem("close");
        close.addActionListener(this);
        menu.add(file);
        menu.add(edit);
        menu.add(close);

        f.getContentPane().add(sp);
        f.setJMenuBar(menu);
        f.setSize(1000,1000);
        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand();
        switch(s){
            case "new":
                t.setText("");
                break;
            case "open":
                JFileChooser j=new JFileChooser("C:");
                int r=j.showOpenDialog(null);
                if(r==JFileChooser.APPROVE_OPTION){
                    File fi=new File(j.getSelectedFile().getAbsolutePath());
                    String s1,s2;
                    try {
                        FileReader fr=new FileReader(fi);
                        BufferedReader br=new BufferedReader(fr);
                        s1=br.readLine();
                        while((s2=br.readLine())!=null){
                            s1=s1+"\n"+s2;
                        }
                        t.setText(s1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(f,"operation canceled");
                }
                break;
            case "save":
                JFileChooser ji=new JFileChooser("C:");
                int ri=ji.showSaveDialog(null);
                if(ri==JFileChooser.APPROVE_OPTION){
                    File fi=new File(ji.getSelectedFile().getAbsolutePath());

                    try {
                        FileWriter fr=new FileWriter(fi);
                        BufferedWriter br=new BufferedWriter(fr);
                        br.write(t.getText());
                        br.flush();
                        br.close();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(f,"operation canceled");
                }
                break;
            case "print":
                try {
                    t.print();
                } catch (PrinterException ex) {
                    ex.printStackTrace();
                }
                break;
            case "cut":
                t.cut();
                break;
            case "copy":
                t.copy();
                break;
            case "paste":
                t.paste();
                break;
            case "close":
                f.setVisible(false);
                break;
        }

    }

    public static void main(String[] args) {
        NotePad pad=new NotePad();
    }

}
