/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author S1718034
 */
public class Queen extends Piece {
    
    public Queen (Boolean Color){
     super (Color, "Queen" );
     score = 9;
    }   
    
    
    @Override
    boolean isMovePermitted (int currentCol, int currentRow, int newColumn, int newRow, ChessGame s){
    boolean b = super.isMovePermitted(currentCol, currentRow, newColumn, newRow, s);
    if (!b) return false;  

    
    Boolean f = hasPieceonHorV( currentCol,  currentRow, newColumn,  newRow, s);
    boolean r = hasPieceonDiagnal(currentCol,  currentRow, newColumn,  newRow, s); 
    return r || f;
    
    }
}
