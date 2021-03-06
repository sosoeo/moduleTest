package myPackage;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class App extends JFrame {

    private JProgressBar progressBar = new JProgressBar();
    private JTextField text = new JTextField(10);
    private JButton start = new JButton("Start");
    private JButton end = new JButton("End");
    private boolean flag = false;
    private int count;
 
    private GoThread t = null;

    public App() {
        this.setLayout(new FlowLayout());
        add(progressBar);
        text.setEditable(false);
        add(text);
        add(start);
        add(end);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = true;
                if (t == null || count == 100) {
                    count = 0;
                    t = new GoThread();
                    t.start();
                }
            }
        });
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = false;
            }
        });
    }
 

 
    class GoThread extends Thread {
        public void run() {
            while (count < 100) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (flag) {
                    count++;
                    SwingUtilities.invokeLater(
                            new Runnable() {
                                public void run() {
                                    progressBar.setValue(count);
                                    text.setText("completed:" + String.valueOf(count) + "%");
                                }
                            }
                    );
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel( new FlatLightLaf() );
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }

                App fg = new App();
                fg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fg.setSize(300, 100);
                fg.setVisible(true);
                 
            }
        });
         
    }
}
