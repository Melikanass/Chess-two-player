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
public class Knight extends Piece {
    
    public Knight (Boolean Color){
     super (Color, "Knight");
     score = 3; 
    } 
    
    
    
    @Override
    boolean isMovePermitted (int currentCol, int currentRow, int newColumn, int newRow, ChessGame s){
    boolean b = super.isMovePermitted(currentCol, currentRow, newColumn, newRow, s);
    if (!b) return false;  
    // basically the move for bishop ... idek how to describe it  
    int yunitsTillDestin = Math.abs(newRow - currentRow);
    int xunitsTillDestin = Math.abs(newColumn - currentCol);    
    if (xunitsTillDestin== 2 && yunitsTillDestin== 1){
       return true;     
    }
    if (xunitsTillDestin== 1 && yunitsTillDestin== 2){
       return true;     
    }     
        return false; 
    }
}

