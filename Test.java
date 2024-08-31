package test;

import javax.swing.JFrame;

public class Test extends JFrame
{

    public static void main(String[] args)
    {
       JFrame frame =new JFrame();
       frame.setSize(1000,1000);
       frame.setVisible(true);
       MyPanel panel=new MyPanel();
       frame.setContentPane(panel);
    }
    
}
