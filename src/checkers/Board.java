
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Board extends JFrame
{
    Tile[][] Tiles = new Tile[8][8] ; 
   private JPanel test = new JPanel() ; 
   private JPanel test2 = new JPanel() ; 
   private JPanel Data = new JPanel() ;
   private JLabel p1 = new JLabel("PLAYER 1") ;
   private JLabel p2 = new JLabel("PLAYER 2") ; 
   static Piece[] RedChecker = new Piece[12] ;
   static Piece[] WhiteChecker = new Piece[12] ; 
    
   private ImageIcon RedIcon ;
   private ImageIcon WhiteIcon ;
   private ImageIcon RedCrownIcon ;
   private ImageIcon WhiteCrownIcon ;
   
   private int redCheckerCount = 12 ; 
   private int whiteCheckerCount = 12 ; 
   
   static boolean[][] initFlag = new boolean[8][8] ; // Array of boolean for each pannel to see if it has a checker or no 
   boolean playerTurn = false;
    
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
         BufferedImage RedCrownimg = null;
         BufferedImage WhiteCrownimg = null;
   
       try {      // Reading the Image file 
           Redimg = ImageIO.read(new File("Red.png"));
           Whiteimg = ImageIO.read(new File("White.png"));
           RedCrownimg = ImageIO.read(new File("RedCrown.png"));
           WhiteCrownimg = ImageIO.read(new File("WhiteCrown.png"));
           
       } catch (IOException ex) {
           Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
       }
        c.add(test) ;
        c.add(test2) ; 
        
        for (int i = 0; i <8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                Tiles[i][j] = new Tile(Color.WHITE);
                Tiles[i][j].setBounds(j * 80, i * 80, 80, 80);
                Tiles[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                Tiles[i][j].setBackground(Color.WHITE); // initially all pannels have white backgroud color
                c.add(Tiles[i][j]) ; 
                initFlag[i][j] = false ; 
            }
             
        } 
        for (int i = 0, j = 1; j < 8; i += 2){
             Tiles[j][i].setBackground(Color.BLACK);
             Tiles[j][i].bkColor = Color.BLACK;
            if ((i + 2) % 8 == 0){
                j += 2;
                i = -2;
            }
        }
         for (int i = 1, j = 0; j < 8; i += 2){
            Tiles[j][i].setBackground(Color.BLACK);
            Tiles[j][i].bkColor = Color.BLACK;
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
                
                RedChecker[pieceCounter] = new Piece(i,j, false, this);
                Piece p = (Piece) RedChecker[pieceCounter];
                // Resizing the Buffred image to fit into the pannel 
                Image scaledImage = Redimg.getScaledInstance(Tiles[i][j].getWidth(),Tiles[i][j].getHeight(),Image.SCALE_SMOOTH);
                Image scaledRedCrownImage = RedCrownimg.getScaledInstance(Tiles[i][j].getWidth(),Tiles[i][j].getHeight(),Image.SCALE_SMOOTH);
                RedIcon = new ImageIcon(scaledImage) ;
                RedCrownIcon = new ImageIcon(scaledRedCrownImage) ;
                
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
                WhiteChecker[pieceCounter] = new Piece(i,j, true, this);
                Image scaledImage = Whiteimg.getScaledInstance(Tiles[i][j].getWidth(),Tiles[i][j].getHeight(),Image.SCALE_SMOOTH);
                Image scaledWhiteCrownImage = WhiteCrownimg.getScaledInstance(Tiles[i][j].getWidth(),Tiles[i][j].getHeight(),Image.SCALE_SMOOTH);
                WhiteIcon = new ImageIcon(scaledImage) ;
                WhiteCrownIcon = new ImageIcon(scaledWhiteCrownImage) ;
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
                    if(playerTurn == false){ //Red
                    for(int i = 0; i < 12; i++) {
                        Piece p = (Piece) RedChecker[i];
//                        System.out.println("piece : " + i + " at : " + p.i 
//                                + ", " + p.j);

                        if(redCheckerCount == 0 )
                      {
                           endGameForRed(); 
                      }

                        if(p.isSelected()) {
                            System.out.println("p : " + i + " is selected");
                            System.out.println("p pos : " + p.getI() + ", " + p.getJ());
                        }
                        if(p.isSelected()) {
                            if(p.isValidMove(currRow, currCol)) {
                                moveAndUpdateRedCheckers(p, currRow, currCol);
                            /*System.out.println("Current piece is : " + 
                                    i);
                                System.out.println("Valid");
                                p.movePiece(currRow, currCol);
                                
                                System.out.println("Piece selected val : " + 
                                        p.isSelected());
                                RedChecker[i].setIcon(RedIcon);
                                Tiles[currRow][currCol].add(RedChecker[i]);
                                Tiles[currRow][currCol].repaint();
                               
                                
                                
                                initFlag[currRow][currCol]  = true;
                                //p1.setText("PLAYER 2");*/
                                break;
                            } else {
                                
                            }
                        }
                    }} else {
                    for(int i = 0; i < 12; i++) {
                        Piece p = (Piece) WhiteChecker[i];
//                        System.out.println("piece : " + i + " at : " + p.i 
//                                + ", " + p.j);
                        if(whiteCheckerCount== 0 )
                        {
                            endGameForWhite(); 
                        }
                          
                        if(p.isSelected()) {
                            System.out.println("p : " + i + " is selected");
                            System.out.println("p pos : " + p.getI() + ", " + p.getJ());
                        }
                        if(p.isSelected()) {
                            if(p.isValidMove(currRow, currCol)) {
                                moveAndUpdateWhiteCheckers(p, currRow, currCol);
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
    
    public void moveAndUpdateRedCheckers(Piece p, int currRow, int currCol) {
        Tiles[p.getI()][p.getJ()].remove(p);
        Tiles[p.getI()][p.getJ()].repaint();
        initFlag[p.getI()][p.getJ()] = false;
        if(redCheckerCount==0)
        {
            System.out.println(whiteCheckerCount+"mafrod 27mar yksab");
             JOptionPane.showMessageDialog(this,"Player 1 Wins."); 
        }
        else
        {

        if ((currRow - p.getI())==2 && (currCol - p.getJ())==2){
            
            Piece opponent = (Piece)Tiles[p.getI()+1][p.getJ()+1].getComponent(0);
            Tiles[p.getI()+1][p.getJ()+1].remove(opponent);
            Tiles[p.getI()+1][p.getJ()+1].repaint();
            whiteCheckerCount -- ; 
            initFlag[p.getI() + 1][p.getJ() + 1] = false;
            p.movePiece(currRow, currCol);
            
        } else if ((currRow - p.getI())==2 && (currCol - p.getJ())==-2){
            
            Piece opponent = (Piece)Tiles[p.getI()+1][p.getJ()-1].getComponent(0);
            Tiles[p.getI()+1][p.getJ()-1].remove(opponent);
            Tiles[p.getI()+1][p.getJ()-1].repaint();
            whiteCheckerCount -- ; 
            initFlag[p.getI() + 1][p.getJ() - 1] = false;
            p.movePiece(currRow, currCol);
            
        } else if ((currRow - p.getI())==-2 && (currCol - p.getJ())==-2){
            
            Piece opponent = (Piece)Tiles[p.getI()-1][p.getJ()-1].getComponent(0);
            Tiles[p.getI() - 1][p.getJ() - 1].remove(opponent);
            Tiles[p.getI() - 1][p.getJ() - 1].repaint();
            whiteCheckerCount--; 
            initFlag[p.getI() - 1][p.getJ() - 1] = false;
            p.movePiece(currRow, currCol);
            
        } else if ((currRow - p.getI())==-2 && (currCol - p.getJ())==2){
            
            Piece opponent = (Piece)Tiles[p.getI()-1][p.getJ()+1].getComponent(0);
            Tiles[p.getI() - 1][p.getJ() + 1].remove(opponent);
            Tiles[p.getI() - 1][p.getJ() + 1].repaint();
            whiteCheckerCount --;
            initFlag[p.getI() - 1][p.getJ() + 1] = false;
            p.movePiece(currRow, currCol);
            
        }
        
        if((Math.abs(currRow - p.getI()) == 1) || !(checkMultipleTurns(p,
                currRow, currCol))) {
            playerTurn = !(playerTurn);
            p.movePiece(currRow, currCol);
            if(p.isCrowned())
            {
                p.setIcon(RedCrownIcon);
                System.out.println("feh Crown henaa ya3m ");
            }
            else
            {
                p.setIcon(RedIcon);
            }
           // p.setIcon(RedIcon);
            Tiles[currRow][currCol].add(p);
            Tiles[currRow][currCol].repaint();

            initFlag[currRow][currCol]  = true;
            if(playerTurn == false) {
                p1.setText("Player 1");
            } else {
                p1.setText("Player 2");
            }
        } else {
            lightBoard(p);
            if(p.isCrowned())
            {
                p.setIcon(RedCrownIcon);
                System.out.println("feh Crown henaa ya3m ");
            }
            else
            {
                p.setIcon(RedIcon);
            }
            Tiles[currRow][currCol].add(p);
            Tiles[currRow][currCol].repaint();

            initFlag[currRow][currCol]  = true;
        }
        }
    }
    
    public void moveAndUpdateWhiteCheckers(Piece p, int currRow, int currCol) {
        Tiles[p.getI()][p.getJ()].remove(p);
        Tiles[p.getI()][p.getJ()].repaint();
        initFlag[p.getI()][p.getJ()] = false;
        
       
        
        if(whiteCheckerCount == 0 )
        { JOptionPane.showMessageDialog(this,"Player 1 is the winner "); 
          System.out.println(whiteCheckerCount+"mafrod 27mar yksab");
        }
        else
        {
        

        if ((currRow - p.getI())==-2 && (currCol - p.getJ())==2) {
            
            Piece opponent = (Piece)Tiles[p.getI()-1][p.getJ()+1].getComponent(0);
            Tiles[p.getI()-1][p.getJ()+1].remove(opponent);
            Tiles[p.getI()-1][p.getJ()+1].repaint();
            redCheckerCount--;
            initFlag[p.getI() - 1][p.getJ() + 1] = false;
            p.movePiece(currRow, currCol);
            
        } else if ((currRow - p.getI())==-2 && (currCol - p.getJ())==-2) {
            
            Piece opponent = (Piece)Tiles[p.getI()-1][p.getJ()-1].getComponent(0);
            Tiles[p.getI()-1][p.getJ()-1].remove(opponent);
            Tiles[p.getI()-1][p.getJ()-1].repaint();
            redCheckerCount--;
            initFlag[p.getI() - 1][p.getJ() - 1] = false;
            p.movePiece(currRow, currCol);
            
        } else if ((currRow - p.getI())==2 && (currCol - p.getJ())==2) {
            
            Piece opponent = (Piece)Tiles[p.getI() + 1][p.getJ() + 1].getComponent(0);
            Tiles[p.getI() + 1][p.getJ() + 1].remove(opponent);
            Tiles[p.getI() + 1][p.getJ() + 1].repaint();
            redCheckerCount--;
            initFlag[p.getI() + 1][p.getJ() + 1] = false;
            p.movePiece(currRow, currCol);
            
        } else if ((currRow - p.getI())==2 && (currCol - p.getJ())==-2) {
            
            Piece opponent = (Piece)Tiles[p.getI() + 1][p.getJ()-1].getComponent(0);
            Tiles[p.getI()+1][p.getJ()-1].remove(opponent);
            Tiles[p.getI()+1][p.getJ()-1].repaint();
            redCheckerCount--;
            initFlag[p.getI() + 1][p.getJ() - 1] = false;
            p.movePiece(currRow, currCol);
        }
        
        if((Math.abs(currRow - p.getI()) == 1) || !(checkMultipleTurns(p,
                currRow, currCol))) {
            playerTurn = !(playerTurn);
            p.movePiece(currRow, currCol);
           if(p.isCrowned())
            {
                p.setIcon(WhiteCrownIcon);
                System.out.println("feh Crown henaa ya3m ");
            }
            else
            {
                p.setIcon(WhiteIcon);
            }
            Tiles[currRow][currCol].add(p);
            Tiles[currRow][currCol].repaint();

            initFlag[currRow][currCol]  = true;
            if(playerTurn == false) {
                p1.setText("Player 1");
            } else {
                p1.setText("Player 2");
            }
        } else {
            lightBoard(p);
            if(p.isCrowned())
            {
                p.setIcon(WhiteCrownIcon);
                System.out.println("feh Crown henaa ya3m ");
            }
            else
            {
                p.setIcon(WhiteIcon);
            }
            Tiles[currRow][currCol].add(p);
            Tiles[currRow][currCol].repaint();

            initFlag[currRow][currCol]  = true;
        }
        }
        
    }
    
    public boolean checkMultipleTurns(Piece p, int currRow, int currCol) {
        System.out.println("Fe multi turns fn");
        if(p.isValidMove(currRow + 2, currCol + 2) || 
                p.isValidMove(currRow + 2, currCol - 2) ||
                p.isValidMove(currRow - 2, currCol + 2) ||
                p.isValidMove(currRow - 2, currCol - 2)) {
            p.canMoveMultipleTimes = true;
            return true;
        }
        p.canMoveMultipleTimes = false;
        return false;
    }
    
    public void lightBoard(Piece p) {
        dimBoard();
        boolean[][] availMoves = p.getAvailMoves();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(availMoves[i][j]) {
                    Tiles[i][j].setBackground(Color.GREEN);
                }
            }
        }
    }
    
    public void dimBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(Tiles[i][j].bkColor.equals(Color.BLACK)) {
                    Tiles[i][j].setBackground(Color.BLACK);
                } else {
                    Tiles[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
    
    public void endGameForRed()
    {
        JOptionPane.showMessageDialog(this,"Player 2 is the winner "); 
                     System.out.println(redCheckerCount+"mafrod 27mar yksab");
                     System.exit(0); 
    }
    
     public void endGameForWhite()
    {
        JOptionPane.showMessageDialog(this,"Player 1 is the winner "); 
                     System.out.println(redCheckerCount+"mafrod 27mar yksab");
                     System.exit(0); 
    }
    
    
}
