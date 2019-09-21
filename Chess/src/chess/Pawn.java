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
public class Pawn extends Piece {
    boolean enpassant;  
    
    public Pawn(Boolean Color){
     super (Color, "Pawn");
     enpassant = false;
     
    } 
    
    public boolean can_enpassante(){
        return enpassant; 
    }
   
    @Override
    boolean isMovePermitted (int currentCol, int currentRow, int newColumn, int newRow, ChessGame s){
    boolean b = super.isMovePermitted(currentCol, currentRow, newColumn, newRow, s);
    if (!b) return false;  
    // if the new place isn't empty & if the new place has a piece of the same color  = false 
    if ( (s.getBoard().getPiece(newColumn, newRow) != null) && ( s.getBoard().getPiece(newColumn, newRow).getColor() == s.getBoard().getPiece(currentCol, currentRow).getColor())){ return false; } 
    
    // if its down to up and the row number isn't smaller = false 
    else if((s.getBoard().getPiece(currentCol, currentRow).getDirection()) && !(currentRow > newRow)){ return false; }
    
    // if its up to down, the row number isnt bigger = false
    else if(!(s.getBoard().getPiece(currentCol, currentRow).getDirection()) && !(currentRow < newRow)){ return false; }
    
    //if its going up or down in the same row but the unit isn't empty 
    else if(currentCol == newColumn){
        // if its going from down to up and the given direction (row + 1 && same col) isn't empty    
        if (s.getBoard().getPiece(currentCol, currentRow).getDirection()){
            if (s.getBoard().getPiece(currentCol, currentRow -1) != null){
                return false;
            }
        }

       // going from up to down and the given direction ( same col && row -1 ) isn't empty 
        if (!s.getBoard().getPiece(currentCol, currentRow).getDirection()){
            if (s.getBoard().getPiece(currentCol, currentRow +1) != null){
                return false;
            }
        } 
        
        //if it's not it's first move it can't move more than 1 unit 
        if(Math.abs(newRow - currentRow) > 2){ return false; }
        else if (Math.abs(newRow - currentRow) == 2){
            //if it has moved 
            if ((currentRow != 1) && (currentRow != 6)){
                return false;
            }

           // if it hasn't moved and the space is taken 
           else if ((s.getBoard().getPiece(newColumn, newRow) != null)){
               return false;
           }
        }
    }
    
    // last check, new direction needs to be within one unit of the piece, if the new row isn't +2 checked up there ^
    else if(Math.abs(newColumn - currentCol) != 1 || Math.abs(newRow - currentRow) != 1){
         return false;
    }
//En passante
//        if (s.getLastPlayer() != null){
//            if (!(s.getLastPlayer() instanceof Pawn)){
//             enpassant = false;
//             return false; 
//             }
//            if(s.getLastPlayer().getColor() != this.getColor()){
//             if ((s.getLastPlayer().Row) != currentRow){
//                  enpassant = false; 
//                  return false; 
//              }
//            else if ((s.getLastPlayer().Col) != newColumn){
//                  enpassant = false; 
//                  return false; 
//              }
//            else {
//              enpassant = true; 
//              return true; 
//            }
//            }
//        }
    //if it's empty but it's a new column 
    
    else if ( s.getBoard().getPiece(newColumn, newRow) == null ){
        return false;  
    }
    
        return true;
    }
}
