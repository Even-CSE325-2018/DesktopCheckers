
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
   static Piece[] RedChecker = new Piece[12] ;
   static Piece[] WhiteChecker = new Piece[12] ; 
  
   private ImageIcon RedIcon ;
   private ImageIcon WhiteIcon ;
   static boolean[][] initFlag = new boolean[8][8] ; // Array of boolean for each pannel to see if it has a checker or no 
    private int playerTurn = 0;
    
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
        
        int pieceCounter = 0;
        
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 8; j+=2) 
            {
                if ((i==0 || i ==2) && j==0)
                {
                    j+=1 ; 
                }
                
                RedChecker[pieceCounter] = new Piece(i,j, false);
                Piece p = (Piece) RedChecker[pieceCounter];
                // Resizing the Buffred image to fit into the pannel 
                Image scaledImage = Redimg.getScaledInstance(Tiles[i][j].getWidth(),Tiles[i][j].getHeight(),Image.SCALE_SMOOTH);
                RedIcon = new ImageIcon(scaledImage) ;
                RedChecker[pieceCounter].setIcon(RedIcon);
                // Seting the label to the resized image 
                Tiles[i][j].add(RedChecker[pieceCounter]) ; // Adding the label to the pannel 
                Tiles[i][j].repaint(); 
                initFlag[i][j]  = true;
                pieceCounter++;
            }

        }
        
        pieceCounter = 0;
        
        for (int i = 5; i < 8; i++) 
        {
            for (int j = 0; j < 8; j+=2) 
            {
                if ((i==6) && j ==0)
                {
                    j+=1 ; 
                }
                WhiteChecker[pieceCounter] = new Piece(i,j, true);
                Image scaledImage = Whiteimg.getScaledInstance(Tiles[i][j].getWidth(),Tiles[i][j].getHeight(),Image.SCALE_SMOOTH);
                WhiteIcon = new ImageIcon(scaledImage) ;
                WhiteChecker[pieceCounter].setIcon(WhiteIcon);
                Tiles[i][j].add(WhiteChecker[pieceCounter]) ;
                Tiles[i][j].repaint();  
                initFlag[i][j]  = true;
                pieceCounter++;
            } 
        }
         
        for (int i = 0; i <8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                 int currRow = i;
                 int currCol = j;
                Tiles[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                    }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                   
                }
                
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("I'm in mouseClicked at panel : " + 
                            currRow + " " + currCol);
                    if(playerTurn == 0){
                    for(int i = 0; i < 12; i++) {
                        Piece p = (Piece) RedChecker[i];
//                        System.out.println("piece : " + i + " at : " + p.i 
//                                + ", " + p.j);
                        if(p.isSelected()) {
                            System.out.println("p : " + i + " is selected");
                            System.out.println("p pos : " + p.getI() + ", " + p.getJ());
                        }
                        if(p.isSelected()) {
                            if(p.isValidMove(currRow, currCol)) {
                             Tiles[p.getI()][p.getJ()].remove(p);
                             Tiles[p.getI()][p.getJ()].repaint();
                             
                             if ((currRow - p.getI())==2 && (currCol - p.getJ())==2){
                             Piece opponent = (Piece)Tiles[p.getI()+1][p.getJ()+1].getComponent(0);
                             Tiles[p.getI()+1][p.getJ()+1].remove(opponent);
                             Tiles[p.getI()+1][p.getJ()+1].repaint();
                             } else if ((currRow - p.getI())==2 && (currCol - p.getJ())==-2){
                             Piece opponent = (Piece)Tiles[p.getI()+1][p.getJ()-1].getComponent(0);
                             Tiles[p.getI()+1][p.getJ()-1].remove(opponent);
                             Tiles[p.getI()+1][p.getJ()-1].repaint();
                             }
                            System.out.println("Current piece is : " + 
                                    i);
                                System.out.println("Valid");
                                p.movePiece(currRow, currCol);
                                
                                System.out.println("Piece selected val : " + 
                                        p.isSelected());
                                RedChecker[i].setIcon(RedIcon);
                                Tiles[currRow][currCol].add(RedChecker[i]);
                                Tiles[currRow][currCol].repaint();
                               
                                
                                
                                initFlag[currRow][currCol]  = true;
                                playerTurn = 1;
                                p1.setText("PLAYER 2");
                                break;
                            } else {
                                
                            }
                        }
                    }} else {
                    for(int i = 0; i < 12; i++) {
                        Piece p = (Piece) WhiteChecker[i];
//                        System.out.println("piece : " + i + " at : " + p.i 
//                                + ", " + p.j);
                        if(p.isSelected()) {
                            System.out.println("p : " + i + " is selected");
                            System.out.println("p pos : " + p.getI() + ", " + p.getJ());
                        }
                        if(p.isSelected()) {
                            if(p.isValidMove(currRow, currCol)) {
                             Tiles[p.getI()][p.getJ()].remove(p);
                             Tiles[p.getI()][p.getJ()].repaint();
                             
                             if ((currRow - p.getI())==-2 && (currCol - p.getJ())==2){
                             Piece opponent = (Piece)Tiles[p.getI()-1][p.getJ()+1].getComponent(0);
                             Tiles[p.getI()-1][p.getJ()+1].remove(opponent);
                             Tiles[p.getI()-1][p.getJ()+1].repaint();
                             } else if ((currRow - p.getI())==-2 && (currCol - p.getJ())==-2){
                             Piece opponent = (Piece)Tiles[p.getI()-1][p.getJ()-1].getComponent(0);
                             Tiles[p.getI()-1][p.getJ()-1].remove(opponent);
                             Tiles[p.getI()-1][p.getJ()-1].repaint();
                             }
                             
                            System.out.println("Current piece is : " + 
                                    i);
                                System.out.println("Valid");
                                p.movePiece(currRow, currCol);
                                
                                System.out.println("Piece selected val : " + 
                                        p.isSelected());
                                WhiteChecker[i].setIcon(WhiteIcon);
                                Tiles[currRow][currCol].add(WhiteChecker[i]);
                                Tiles[currRow][currCol].repaint();
                               
                                initFlag[currRow][currCol]  = true;
                                playerTurn = 0;
                                p1.setText("PLAYER 1");
                                break;
                            }
                        }
                        
                        
                    }
                }}
                
                
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
