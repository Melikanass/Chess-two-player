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
public class Rook extends Piece {
    
    public Rook (Boolean Color){
     super (Color, "Rook");
     score = 5;
    }   
    
    
    @Override
    boolean isMovePermitted (int currentCol, int currentRow, int newColumn, int newRow, ChessGame s){
        boolean b = super.isMovePermitted(currentCol, currentRow, newColumn, newRow, s);
        if (!b) return false;  
        //can only move up/down or right/left 
        
        //check if everyone of the units till destination is empty 
        return hasPieceonHorV( currentCol,  currentRow, newColumn,  newRow, s);
    }
}
