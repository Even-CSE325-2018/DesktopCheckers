/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
/**
 *
 * @author Gina Salib
 */
public class Piece extends JLabel{
    private boolean selected;
    private boolean crowned;
    private int i;
    private int j;
    private boolean white = false;
    private Piece p;
    public boolean canMoveMultipleTimes = false;
    private boolean availMoves[][] = new boolean[8][8];
    private Board board;

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setCrowned(boolean crowned) {
        this.crowned = crowned;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isCrowned() {
        return crowned;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public Piece(int i , int j, boolean isWhite, Board b) {
        board = b;
        this.i = i;
        this.j = j;
        this.white = isWhite;
         int row = this.i;
         int col = this.j;
        selected = false;
        crowned = false;
        p = this ;
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println("selected :-");
                System.out.println(selected);
                System.out.println("fel listener ba2a : " + row + ", " + col);
                for(int i = 0; i < 12; i++) {
                    if(Board.RedChecker[i].canMoveMultipleTimes) {
                        Board.RedChecker[i].selected = true;
                        return;
                    }
                    
                    if(Board.WhiteChecker[i].canMoveMultipleTimes) {
                        Board.WhiteChecker[i].selected = true;
                        return;
                    }
                }
                if(isWhite && !board.playerTurn) {
                    return;
                } else if(!isWhite && board.playerTurn) {
                    return;
                }
                selected = true;
                board.lightBoard(p);
                for (int i=0;i<12 ; i++){
                    if (Board.RedChecker[i] != p && Board.RedChecker[i].selected == true){
                        Board.RedChecker[i].selected = false;
                    }
                    if (Board.WhiteChecker[i] != p && Board.WhiteChecker[i].selected == true){
                        Board.WhiteChecker[i].selected = false;
                    }
                }
            }
        });
    }

    public void unSelect() {
        this.selected = false;
    }
    
    public boolean isValidMove(int newI, int newJ) {
        /*if(newI == i - 1 && newJ == j - 1) {
            return true;
        } else if(newI == i - 1 && newJ == j + 1) {
            return true;
        } else if(newI == i + 1 && newJ == j - 1) {
            return true;
        } else if(newI == i + 1 && newJ == j + 1) {
            return true;
        }*/
        
        if(newI >= 8 || newI < 0 || newJ >= 8 || newJ < 0) {
            return false;
        }
        
        if(!white) {        //red checker
            if(newI == i + 1 && newJ == j - 1) {
                if(!canMoveMultipleTimes) {
                    if(!Board.initFlag[i + 1][j - 1]) {
                        return true;
                    }
                }
            } else if(newI == i + 1 && newJ == j + 1) {
                if(!canMoveMultipleTimes) {
                    if(!Board.initFlag[i + 1][j + 1]) {
                        return true;
                    }
                }
            } else if(newI == i + 2 && newJ == j - 2) {
                if(Board.initFlag[i + 1][j - 1] && !Board.initFlag[i + 2][j - 2]) {
                    Piece opp = (Piece)board.Tiles[i + 1][j - 1].getComponent(0);
                    if(opp.white) {
                        return true;
                    }
                }
            } else if(newI == i + 2 && newJ == j + 2) {
                if(Board.initFlag[i + 1][j + 1] && !Board.initFlag[i + 2][j + 2]) {
                    Piece opp = (Piece)board.Tiles[i + 1][j + 1].getComponent(0);
                    if(opp.white) {
                        return true;
                    }
                }
            }
        } else {            //white checker
            if(newI == i - 1 && newJ == j - 1) {
                if(!canMoveMultipleTimes) {
                    if(!Board.initFlag[i - 1][j - 1]) {
                        return true;
                    }
                }
            } else if(newI == i - 1 && newJ == j + 1) {
                if(!canMoveMultipleTimes) {
                    if(!Board.initFlag[i - 1][j + 1]) {
                        return true;
                    }
                }
            } else if(newI == i - 2 && newJ == j - 2) {
                if(Board.initFlag[i - 1][j - 1] && !Board.initFlag[i - 2][j - 2]) {
                    Piece opp = (Piece)board.Tiles[i - 1][j - 1].getComponent(0);
                    if(!opp.white) {
                        return true;
                    }
                }
            } else if(newI == i - 2 && newJ == j + 2) {
                if(Board.initFlag[i - 1][j + 1] && !Board.initFlag[i - 2][j + 2]) {
                    Piece opp = (Piece)board.Tiles[i - 1][j + 1].getComponent(0);
                    if(!opp.white) {
                        return true;
                    }
                }
            }
        }
        
        //Red Crown
        if(crowned && !white) {
            if(newI == i - 1 && newJ == j - 1) {
                if(!canMoveMultipleTimes) {
                    if(!Board.initFlag[i - 1][j - 1]) {
                        return true;
                    }
                }
            } else if(newI == i - 1 && newJ == j + 1) {
                if(!canMoveMultipleTimes) {
                    if(!Board.initFlag[i - 1][j + 1]) {
                        return true;
                    }
                }
            } else if(newI == i - 2 && newJ == j - 2) {
                if(Board.initFlag[i - 1][j - 1] && !Board.initFlag[i - 2][j - 2]) {
                    Piece opp = (Piece)board.Tiles[i - 1][j - 1].getComponent(0);
                    if(opp.white) {
                        return true;
                    }
                }
            } else if(newI == i - 2 && newJ == j + 2) {
                if(Board.initFlag[i - 1][j + 1] && !Board.initFlag[i - 2][j + 2]) {
                    Piece opp = (Piece)board.Tiles[i - 1][j + 1].getComponent(0);
                    if(opp.white) {
                        return true;
                    }
                }
            }
            
        } else if(crowned && white) {
            if(newI == i + 1 && newJ == j - 1) {
                if(!canMoveMultipleTimes) {
                    if(!Board.initFlag[i + 1][j - 1]) {
                        return true;
                    }
                }
            } else if(newI == i + 1 && newJ == j + 1) {
                if(!canMoveMultipleTimes) {
                    if(!Board.initFlag[i + 1][j + 1]) {
                        return true;
                    }
                }
            } else if(newI == i + 2 && newJ == j - 2) {
                if(Board.initFlag[i + 1][j - 1] && !Board.initFlag[i + 2][j - 2]) {
                    Piece opp = (Piece)board.Tiles[i + 1][j - 1].getComponent(0);
                    if(!opp.white) {
                        return true;
                    }
                }
            } else if(newI == i + 2 && newJ == j + 2) {
                if(Board.initFlag[i + 1][j + 1] && !Board.initFlag[i + 2][j + 2]) {
                    Piece opp = (Piece)board.Tiles[i + 1][j + 1].getComponent(0);
                    if(!opp.white) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public void movePiece(int newI, int newJ) {
        this.i = newI;
        this.j = newJ;
        unSelect();
        if(this.white == true && this.i ==0 )
        {
            crowned = true;
        }
        else if (this.white == false && this.i ==7)
        {
            crowned = true;
        }
        
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                availMoves[i][j] = false;
            }
        }
        board.dimBoard();
    }
    
    public boolean[][] getAvailMoves() {
        if(this.canMoveMultipleTimes) {
            if(isValidMove(i + 2, j + 2)) {
            availMoves[i + 2][j + 2] = true;
            }
            
            if(isValidMove(i + 2, j - 2)) {
                availMoves[i + 2][j - 2] = true;
            }
            
            if(isValidMove(i - 2, j + 2)) {
                availMoves[i - 2][j + 2] = true;
            }
            
            if(isValidMove(i - 2, j - 2)) {
                availMoves[i - 2][j - 2] = true;
            }
            return availMoves;
        }
        
        if(isValidMove(i + 1, j + 1)) {
            availMoves[i + 1][j + 1] = true;
        }
        
        if(isValidMove(i + 1, j - 1)) {
            availMoves[i + 1][j - 1] = true;
        }
        
        if(isValidMove(i - 1, j + 1)) {
            availMoves[i - 1][j + 1] = true;
        }
        
        if(isValidMove(i - 1, j - 1)) {
            availMoves[i - 1][j - 1] = true;
        }
        
        if(isValidMove(i + 2, j + 2)) {
            availMoves[i + 2][j + 2] = true;
        }
        
        if(isValidMove(i + 2, j - 2)) {
            availMoves[i + 2][j - 2] = true;
        }
        
        if(isValidMove(i - 2, j + 2)) {
            availMoves[i - 2][j + 2] = true;
        }
        
        if(isValidMove(i - 2, j - 2)) {
            availMoves[i - 2][j - 2] = true;
        }
        
        return availMoves;
    }
}
