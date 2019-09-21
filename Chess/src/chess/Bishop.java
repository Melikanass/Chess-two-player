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
public class Bishop extends Piece {
    //Elephant 
    public Bishop (Boolean Color){
     super(Color, "Bishop");
     score = 3; 
    }   
   
    
    @Override
    boolean isMovePermitted (int currentCol, int currentRow, int newColumn, int newRow, ChessGame s){
    boolean b = super.isMovePermitted(currentCol, currentRow, newColumn, newRow, s);
    if (!b) return false;  
    // can't move up/down or left/right 
    if(currentRow == newRow || currentCol == newColumn){
	return false;
    }
    
    
    // every unit till the destination needs to be checked if it's null or not 
    return hasPieceonDiagnal(currentCol,  currentRow, newColumn,  newRow, s); 
    
    }
}