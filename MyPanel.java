
package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
    private static boolean isDotted = false; 
    
    public MyPanel()
    {
        setBackground(Color.WHITE);
        addMouseListener(new MouseListener() );
        addMouseMotionListener(new MouseMotionListener());
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
        JButton freeHand =new JButton("Free Hand");
        JButton eraser =new JButton("Eraser");
        JButton clearAll =new JButton("Clear All");
        JCheckBox dottedCheckbox = new JCheckBox("Dotted Line");
        
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
        
        freeHand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="freeHand";
            }
        }           
        );
        
        eraser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                type="eraser";
            }
        }           
        );
        
        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapes.clear();
                repaint();
            }
        }           
        );
        
        
        dottedCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDotted = dottedCheckbox.isSelected();
                repaint(); 
            }
        });
        
        add(red);
        add(green);
        add(blue);
        add(rectangle);
        add(oval);
        add(line);
        add(freeHand);
        add(eraser);
        add(clearAll);
        add(dottedCheckbox);
        
        
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
        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0 ; i < shapes.size() ; i++)
        {
            Shape shape=shapes.get(i);
            g.setColor(shape.color);
            setStrokeStyle(g2d);
            
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
                
                case "freeHand":
                    g.drawLine(shape.startX, shape.startY, shape.endX, shape.endY);
                    break;
                 
                case "eraser":
                    g.setColor(Color.WHITE); 
                    g.fillRect(shape.startX, shape.startY, 20, 20); 
                    break;    
                    
            }    
        }
        
        
        g.setColor(color);
        setStrokeStyle(g2d);
            switch(type)
            {
                case "rectangle":
                    g.drawRect(Math.min(startX,endX),Math.min(startY,endY),
                            Math.abs(startX-endX),Math.abs(startY-endY) );
                    break;
                    
                case "oval":
                    g.drawOval(Math.min(startX,endX),Math.min(startY,endY),
                            Math.abs(startX-endX),Math.abs(startY-endY) );
                    break;
                    
                case "line":
                    g.drawLine(startX, startY, endX, endY);
                    break;
                    
                   
            }   
    }
    
    private void setStrokeStyle(Graphics2D g2d) {
        if (isDotted) {
            float[] dashPattern = {5, 5}; 
            g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dashPattern, 0));
        } else {
            g2d.setStroke(new BasicStroke(1));
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
    
    public class MouseMotionListener extends MouseMotionAdapter {
        @Override 
        public void mouseDragged (MouseEvent e){
        endX = e.getX();
        endY = e.getY();
        
        if(type.equals("freeHand") || type.equals("eraser"))
        {
            shapes.add(new Shape(startX, startY, endX, endY, type, color));
            startX=endX;
            startY=endY;
        }
        repaint();
        }
        
    }
    
}
