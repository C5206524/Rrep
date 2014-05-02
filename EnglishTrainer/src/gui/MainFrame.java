package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ilya_Shchurok
 * Date: 4/28/14
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainFrame extends JFrame {
    private JTextArea textInput = new JTextArea("Input");
    private JTextArea textOutput = new JTextArea("Output");

    public MainFrame() throws HeadlessException {
        setSize(800, 600);
        this.setTitle("English parser");
        JPanel wrap = new JPanel();
        wrap.setLayout(new BoxLayout(wrap, BoxLayout.Y_AXIS));
        this.add(wrap);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        textInput.setLineWrap(true);
        textInput.setWrapStyleWord(true);

        JButton button = new JButton("Parse");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doJob();
            }
        });

        textOutput.setLineWrap(true);
        textOutput.setWrapStyleWord(true);

        wrap.add(new JScrollPane(textInput));
        wrap.add(button);
        wrap.add(new JScrollPane(textOutput));
    }

    private void doJob(){
        String text = textInput.getText();
        text = text.toLowerCase().replaceAll("[^a-zA-Z ]"," ");
        Set<String> set = new HashSet<String>();
        for(String it : text.split(" ")){
            set.add(it);
        }

        String result = "";

        List<String> words = new ArrayList<String>();
        words.addAll(set);
        Collections.sort(words,new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for (String it : words){
            if (!it.isEmpty()){
                result += it + "\r\n";
            }
        }

        textOutput.setText(result);
    }
}
