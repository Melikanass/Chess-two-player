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
public class King extends Piece{
    boolean castleAllowed = false;
    
    public King (Boolean Color){
     super (Color, "Pawn");
     score = 1000;
     
    }
    boolean isCastleAllowed(){
        return castleAllowed; 
    }
    
    @Override
    boolean isMovePermitted (int currentCol, int currentRow, int newColumn, int newRow, ChessGame s){
    boolean b = super.isMovePermitted(currentCol, currentRow, newColumn, newRow, s);
    if (!b) return false;  
    //can move only one, if not one == castle 
    int yunitsTillDestin = Math.abs(newRow - currentRow);
    int xunitsTillDestin = Math.abs(newColumn - currentCol);
    castleAllowed = false;
    if ((1< xunitsTillDestin) || (1<yunitsTillDestin)){
        //The right to castle has been lost: 
           //[a] if the king has already moved, +done
           //[b] with a rook that has already moved. +done 
        //Castling is prevented temporarily: 
           //[a] if the square on which the king stands, or the square which it must cross, 
           //or the square which it is to occupy, is attacked by one or more of the opponent's pieces,  
           //[b] if there is any piece between the king and the rook with which castling is to be effected. +done
        
        // if the king has moved 
        if (s.getBoard().getPiece(currentCol, currentRow).hasMoved){
            return false; 
        }
        if (currentRow != newRow){
            return false; 
        }
        if ((currentRow !=7) && (currentRow !=0)){
            return false; 
        }
        //if the units are occupied  or if the rook has moved 
        if ((newColumn - currentCol) == 2) {
            for (int i=currentCol+1; i<7; i++){
                if ((s.getBoard().getPiece(i, currentRow)) != null){
                return false; 
                }
                if ((this.willbeDanger(i, currentRow, s)) !=null){
                return false; 
                }
            }
            if ((s.getBoard().getPiece(7, currentRow) == null) || (s.getBoard().getPiece(7, currentRow).ifMoved())){
                 return false;
            }
            castleAllowed = true;
        }
        else if ((newColumn - currentCol) == -2){
            for (int i=currentCol-1; i>0; i--){
                if ((s.getBoard().getPiece(i, currentRow)) != null){
                return false; 
                } 
            }
            for (int i=currentCol-1; i>1; i--){
                if ((this.willbeDanger(i, currentRow, s)) !=null){
                return false; 
                }
            }
            if ((s.getBoard().getPiece(0, currentRow) == null) || (s.getBoard().getPiece(0, currentRow).ifMoved())){
                 return false;
            }
            castleAllowed = true;
        }
        
        
        
        
        else {
            castleAllowed = false; 
            return false; 
        }  
        
    }
      if ((s.getBoard().getPiece(currentCol, currentRow).willbeDanger(newColumn, newRow, s)) != null){
            return false;
        }
        return true;
    }
    
    
    
}
