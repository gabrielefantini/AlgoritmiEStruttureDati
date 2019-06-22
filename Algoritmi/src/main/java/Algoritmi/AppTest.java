package Algoritmi;
import Algoritmi.App;
import javax.swing.*;
import java.awt.*;

public class AppTest{
    

    public static void main(String[] Args) {
        JFrame frame = new JFrame("DistanzaDiEditing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea text= new JTextArea("");
        text.setEditable(false);

        JScrollPane scroll = new JScrollPane(text);

        Container contPane = frame.getContentPane();
        contPane.add(scroll);

        String in = JOptionPane.showInputDialog("Inserire stringa di input");
        if(in == null)
            in = "";
        String out = JOptionPane.showInputDialog("Inserire stringa di output");
        if(out == null)
            out = "";
        text.append("Stringa di input: "+in+"\n"+"Stringa di output: "+out+"\n\n");
        App distanzaDiEditing = App.editDistance(in, out);
        text.append("Tempo di esecuzione dell'algoritmo per il calcolo dei costi in millisecondi: "+(distanzaDiEditing.getTime()/1000)+"\n\n");
        App.operationsPrint(distanzaDiEditing.getOperations(), in.length(), out.length(),text);

        frame.setSize(new Dimension(600,600));
        frame.setVisible(true);
    }
}
