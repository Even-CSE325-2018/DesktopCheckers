
package checkers;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Board extends JFrame
{
   private JPanel[][] Tiles = new JPanel[8][8] ; 
   private JPanel test = new JPanel() ; 
   private JPanel test2 = new JPanel() ; 
   private JPanel Data = new JPanel() ;
   private JLabel p1 = new JLabel("PLAYER 1") ;
   private JLabel p2 = new JLabel("PLAYER 2") ; 
   private JLabel[] RedChecker = new JLabel[8] ;
   private JLabel[] WhiteChecker = new JLabel[8] ; 
  
   private ImageIcon RedIcon ;
   private ImageIcon WhiteIcon ;
   private boolean[][] initFlag = new boolean[8][8] ; // Array of boolean for each pannel to see if it has a checker or no    
    
    public Board() 
    {
        init() ; 
    }
    
    public void init()
    {
        Container c = getContentPane();  
        setLayout(null);
     
         BufferedImage Redimg = null;
         BufferedImage Whiteimg = null;
   
       try {      // Reading the Image file 
           Redimg = ImageIO.read(new File("Red.png"));
           Whiteimg = ImageIO.read(new File("White.png"));
       } catch (IOException ex) {
           Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
       }
        c.add(test) ;
        c.add(test2) ; 
        
        for (int i = 0; i <8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                Tiles[i][j] = new JPanel();
                Tiles[i][j].setBounds(j * 80, i * 80, 80, 80);
                Tiles[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                Tiles[i][j].setBackground(Color.WHITE); // initially all pannels have white backgroud color
                c.add(Tiles[i][j]) ; 
                initFlag[i][j] = false ; 
            }
             
        } 
        for (int i = 0, j = 1; j < 8; i += 2){
             Tiles[j][i].setBackground(Color.BLACK);
            if ((i + 2) % 8 == 0){
                j += 2;
                i = -2;
            }
        }
         for (int i = 1, j = 0; j < 8; i += 2){
            Tiles[j][i].setBackground(Color.BLACK);
            if ((i + 1) % 8 == 0){
                j += 2;
                i = -1;
            }
        }
         for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 8; j+=2) 
            {
                if (i==1)
                {
                    j+=1 ; 
                }
                RedChecker[j] = new JLabel();
                // Resizing the Buffred image to fit into the pannel 
                Image scaledImage = Redimg.getScaledInstance(Tiles[i][j].getWidth(),Tiles[i][j].getHeight(),Image.SCALE_SMOOTH);
                RedIcon = new ImageIcon(scaledImage) ;
                RedChecker[j].setIcon(RedIcon); // Seting the label to the resized image 
                Tiles[i][j].add(RedChecker[j]) ; // Adding the label to the pannel 
                Tiles[i][j].repaint(); 
                initFlag[i][j]  = true ; 
            }
            
        }
         for (int i = 5; i < 8; i++) 
        {
            for (int j = 0; j < 8; j+=2) 
            {
                if (i==6)
                {
                    j+=1 ; 
                }
                WhiteChecker[j] = new JLabel();
                Image scaledImage = Whiteimg.getScaledInstance(Tiles[i][j].getWidth(),Tiles[i][j].getHeight(),Image.SCALE_SMOOTH);
                WhiteIcon = new ImageIcon(scaledImage) ;
                WhiteChecker[j].setIcon(WhiteIcon);
                Tiles[i][j].add(WhiteChecker[j]) ;
                Tiles[i][j].repaint();  
                initFlag[i][j]  = true ; 
            } 
        }
         
         for (int i = 0; i <8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                Tiles[i][j].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    for (int k = 0; k < 8; k++)
                    {
                        for (int l = 0; l < 8; l++) 
                        {
                             if(initFlag[k][l])
                            {
                             // Tiles[k][l].removeAll(); 
                              initFlag[k][l]  = false ;
                              break ; 
                            }
                             else 
                             {
                               // Image scaledImage = Redimg.getScaledInstance(Tiles[k][l].getWidth(),Tiles[k][l].getHeight(),Image.SCALE_SMOOTH);
                               // RedIcon = new ImageIcon(scaledImage) ;
                                RedChecker[l].setIcon(RedIcon);
                                Tiles[k][l].add(RedChecker[l]) ;
                                Tiles[k][l].repaint(); 
                                initFlag[k][l]  = true ; 
                             }
                        }
                    }
                   
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                   
                }
            });
                c.add(Tiles[i][j]) ; 
            }
             
        } 
        
        c.add(Data) ; 
        Data.add(p1) ; 
        Data.add(p2) ;
        Data.setLayout(new GridLayout());
        Data.setBounds(900, 100, 1100, 500);
        setSize(1100,1100);
        setTitle("Checkers"); 
        setLocation(150,0);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
}
