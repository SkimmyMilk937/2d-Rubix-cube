import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RubikGUI implements ActionListener
{
    JFrame frame;
    JPanel panel;
    JPanel movePanel;
    JPanel rotPanel;

    JButton U;
    JButton UPrime;
    JButton D;
    JButton DPrime;
    JButton R;
    JButton RPrime;
    JButton L;
    JButton LPrime;
    JButton F;
    JButton FPrime;
    JButton randomize;

    JPanel yellowFace;
    JPanel orangeFace;
    JPanel blueFace;
    JPanel redFace;
    JPanel greenFace;
    JPanel whiteFace;

    Cube cube;

    public RubikGUI()
    {
        cube = new Cube();

        frame = new JFrame();
        //setting title
        frame.setTitle("Rubiks GUI");
        //close();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setting size
        frame.setSize(1000, 800);
        //making non-resizable
        frame.setResizable(false);
        //centers windows
        frame.setLocationRelativeTo(null);

        //settings faces
        ImageIcon img = rescaleImg(new ImageIcon("D'.png"), frame.getWidth()/4/2, frame.getHeight()/4/2);
        DPrime = new JButton(img);
        DPrime.addActionListener(this);
        img = rescaleImg(new ImageIcon("D.png"), frame.getWidth()/4/2, frame.getHeight()/4/2);
        D = new JButton(img);
        D.addActionListener(this);
        img = rescaleImg(new ImageIcon("U.png"), frame.getWidth()/4/3, frame.getHeight()/4/2); 
        U = new JButton(img);
        U.addActionListener(this);
        img = rescaleImg(new ImageIcon("U'.png"), frame.getWidth()/4/3, frame.getHeight()/4/2); 
        UPrime = new JButton(img);
        UPrime.addActionListener(this);
        img = rescaleImg(new ImageIcon("R.png"), frame.getWidth()/4/3, frame.getHeight()/4/2); 
        R = new JButton(img);
        R.addActionListener(this);
        img = rescaleImg(new ImageIcon("R'.png"), frame.getWidth()/4/3, frame.getHeight()/4/2); 
        RPrime = new JButton(img);
        RPrime.addActionListener(this);
        img = rescaleImg(new ImageIcon("F.png"), frame.getWidth()/4/3, frame.getHeight()/4/2); 
        F = new JButton(img);
        F.addActionListener(this);
        img = rescaleImg(new ImageIcon("F'.png"), frame.getWidth()/4/3, frame.getHeight()/4/2); 
        FPrime = new JButton(img);
        FPrime.addActionListener(this);
        img = rescaleImg(new ImageIcon("L.png"), frame.getWidth()/4/3, frame.getHeight()/4/2); 
        L = new JButton(img);
        L.addActionListener(this);
        img = rescaleImg(new ImageIcon("L'.png"), frame.getWidth()/4/3, frame.getHeight()/4/2); 
        LPrime = new JButton(img);
        LPrime.addActionListener(this);
        randomize = new JButton("Scramble"); 
        randomize.addActionListener(this);


        panel = new JPanel(new GridLayout(3, 4));
    
        //R.setBackground(Color.black);

        movePanel = new JPanel(new GridLayout(4, 2));
        movePanel.add(R);
        movePanel.add(RPrime);
        movePanel.add(U);
        movePanel.add(UPrime);
        movePanel.add(D);
        movePanel.add(DPrime);
        movePanel.add(L);
        movePanel.add(LPrime);
    
        rotPanel = new JPanel(new GridLayout(1, 3));
        rotPanel.add(F);
        rotPanel.add(FPrime);
        rotPanel.add(randomize);
        

        
        frame.add(panel);
        rePaint();
    }

    public void show()
    {
        frame.setVisible(true);
    }

    public JPanel drawFace(Face face)
    {
        JPanel temp = new JPanel(new GridLayout(3, 3));
        Tile[][] tiles = face.getTiles();


        for(int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                JPanel tile = new JPanel();
                tile.setBackground(tiles[i][j].getColor());
                System.out.println(tiles[i][j].getColor());
                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                temp.add(tile);
            }
        }

        return temp;
    }

    public ImageIcon rescaleImg(ImageIcon img, int width, int height)
    {
        Image imgScaled = img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(imgScaled);
    }

    public void rePaint(){
        panel.removeAll();
        blueFace = drawFace(cube.getFaces()[2]);
        redFace = drawFace(cube.getFaces()[4]);
        greenFace = drawFace(cube.getFaces()[5]);
        whiteFace = drawFace(cube.getFaces()[3]);
        yellowFace = drawFace(cube.getFaces()[0]);
        orangeFace = drawFace(cube.getFaces()[1]);

        panel.add(new JLabel(""));
        panel.add(orangeFace);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(yellowFace);
        panel.add(blueFace);
        panel.add(redFace);
        panel.add(greenFace);
        panel.add(new JLabel(""));
        panel.add(whiteFace);

        panel.add(movePanel);
        panel.add(rotPanel);

        panel.revalidate();
        panel.repaint();
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == U)
        {
            System.out.println("U Move");
            cube.rotateRow(true, false);
        }
        else if(e.getSource() == UPrime)
        {
            System.out.println("UPrime Move");
            cube.rotateRow(true, true);
        }
        else if(e.getSource() == R)
        {
            cube.rotateCol(true, true);
            System.out.println("R Move");
        }
        else if(e.getSource() == RPrime)
        {
            cube.rotateCol(true, false);
            System.out.println("RPrime Move");
        }
        else if(e.getSource() == F)
        {
            System.out.println("Face Rotation");
            System.out.println(cube);
            cube.rotateFaceClockwise(false);
            System.out.println(cube);

            System.out.println("Rotating face clockwise");

            
        }
        else if(e.getSource() == FPrime)
        {
            System.out.println("Rotating face counter clockwise");
            cube.rotateFaceClockwise(true);
        }
        else if(e.getSource() == L)
        {
            cube.rotateCol(false, false);
            System.out.println("L Move");
        }
        else if(e.getSource() == LPrime)
        {
            cube.rotateCol(false, true);
            System.out.println("LPrime Move");
        }
        else if(e.getSource() == DPrime)
        {
            cube.rotateRow(false, false);
            System.out.println("Rot UPrime Move");
        }
        else if(e.getSource() == D)
        {
            cube.rotateRow(false, true);
            System.out.println("Rot U Move");
        }
        else if(e.getSource() == randomize){
            cube.randomize();
        }

         rePaint();
    }
}