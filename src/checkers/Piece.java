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
    boolean selected;
    boolean crowned;
    int i;
    int j;

    public Piece(int i , int j) {
        selected = false;
        crowned = false;
      addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent me) {
        selected = true;
      }
    });
    }

    public void unSelect() {
        this.selected = false;
    }
    
}
