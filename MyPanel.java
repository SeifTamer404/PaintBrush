
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;


public class MyPanel extends JPanel
{
    Color color = Color.BLACK;
    String type="line";
    int startX;
    int startY;
    int endX;
    int endY;
    private  List <Shape> shapes = new ArrayList<>();
    
    public MyPanel()
    {
        addMouseListener(new MouseListener() );
        functions();
    }
    
    public void functions ()
    {
        JButton red =new JButton("Red");
        JButton green =new JButton("Green");
        JButton blue =new JButton("Blue");
        JButton rectangle =new JButton("Rectangle");
        JButton oval =new JButton("Oval");
        JButton line =new JButton("Line");
        
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color=Color.RED;
            }
        }           
        );
        
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color=Color.GREEN;
            }
        }           
        );
        
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color=Color.BLUE;
            }
        }           
        );
        
        rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="rectangle";
            }
        }           
        );
        
        oval.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="oval";
            }
        }           
        );
        
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="line";
            }
        }           
        );
        
        add(red);
        add(green);
        add(blue);
        add(rectangle);
        add(oval);
        add(line);
        
        
    }
    
    private static class Shape
    {
        String type;
        Color color;
        int startX;
        int startY;
        int endX;
        int endY;
        
        public Shape(int startX,int startY,int endX,int endY,String type,Color color)
        {
            this.startX=startX;
            this.startY=startY;
            this.endX=endX;
            this.endY=endY;
            this.color=color;
            this.type=type;
        }
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        for(int i = 0 ; i < shapes.size() ; i++)
        {
            Shape shape=shapes.get(i);
            
            g.setColor(shape.color);
            
            switch(shape.type)
            {
                case "rectangle":
                    g.drawRect(Math.min(shape.startX,shape.endX),Math.min(shape.startY,shape.endY),
                            Math.abs(shape.startX-shape.endX),Math.abs(shape.startY-shape.endY) );
                    break;
                    
                case "oval":
                    g.drawOval(Math.min(shape.startX,shape.endX),Math.min(shape.startY,shape.endY),
                            Math.abs(shape.startX-shape.endX),Math.abs(shape.startY-shape.endY) );
                    break;
                    
                case "line":
                    g.drawLine(shape.startX, shape.startY, shape.endX, shape.endY);
                    break;
            }
  
            
        }
    }
    
    public class MouseListener extends MouseAdapter
    {
        @Override
        public void mousePressed (MouseEvent e)
        {
            startX=e.getX();
            startY=e.getY();
        }
        
        @Override
        public void mouseReleased(MouseEvent e)
        {
            endX=e.getX();
            endY=e.getY();
            
            shapes.add(new Shape(startX,startY,endX,endY,type,color) );
            repaint();
        }
        
        
        
    }
    
    
    
    
}
