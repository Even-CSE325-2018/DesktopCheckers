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

    public Piece(int i , int j) {
        this.i = i;
        this.j = j;
        final int row = this.i;
        final int col = this.j;
        selected = false;
        crowned = false;
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                System.out.println("selected :-");
                selected = true;
                System.out.println(selected);
                System.out.println("fel listener ba2a : " + row + ", " + col);
            }
        });
    }

    public void unSelect() {
        this.selected = false;
    }
    
    public boolean isValidMove(int newI, int newJ) {
        System.out.println("i : " + this.i + ", j: " + this.j);
        System.out.println("newI : " + newI + ", j : " + newJ);
        if(newI == i - 1 && newJ == j - 1) {
            return true;
        } else if(newI == i - 1 && newJ == j + 1) {
            return true;
        } else if(newI == i + 1 && newJ == j - 1) {
            return true;
        } else if(newI == i + 1 && newJ == j + 1) {
            return true;
        }
        
        return false;
    }
    
    public void move(int newI, int newJ) {
        this.i = newI;
        this.j = newJ;
        unSelect();
    }
}
