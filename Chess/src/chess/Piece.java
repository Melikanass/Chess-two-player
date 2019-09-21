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
public abstract class Piece {
    public Boolean isBlack;
    public String pieceType; 
    public int score;
    public boolean isalive;
    public int Col;
    public int Row;
    //from down to up is true 
    public boolean southToNorth;
    public ChessGame parent;
    
    public boolean hasMoved; 
    
    public Piece (Boolean Color, String apiecetype){
     isBlack = Color;
     pieceType = apiecetype;
     score = 1;
     isalive = true;
     Col = -1;
     Row = -1;
     hasMoved= false;
    }
    
    
    String getpieceType(){
    return pieceType;
    }
    boolean getColor(){
        return isBlack;
    }
    boolean getDirection(){
        return southToNorth; 
    }
    void setDirection(boolean s){
        southToNorth = s; 
    }
    boolean ifMoved(){
        return hasMoved;
    }
    int getY(){
        return Row;
    }
    int getX(){
        return Col; 
    }
    
    void setMoved(boolean s){
        hasMoved= s; 
    }
    
    
    void setXY( int col, int row) {
      if (Row==-1){
          // down to up is south to North, if it starts at 0 or 1 then its north to south so it's false 
          if (row==0 || row== 1){
              southToNorth = false;
          }
          else { southToNorth = true;}
      }
      
        Col = col;
        Row = row;  
    }
    
    boolean isMovePermitted (int currentCol, int currentRow, int newColumn, int newRow, ChessGame s){
        
        if ( (currentCol == newColumn) && (currentRow == newRow)){
            return false ; 
        }              
        if (s.getBoard().getPiece(newColumn, newRow) != null){
            if (this.getColor() == s.getBoard().getPiece(newColumn, newRow).getColor()){
                return false; 
            }
        }
        
        return true; 
    }
   
    boolean hasPieceonHorV(int currentCol, int currentRow, int newColumn, int newRow, ChessGame s){
        if((currentRow != newRow) && (currentCol != newColumn)){
	return false;
        }
        
        int v= 0;
        int h= 0;
        if (currentRow < newRow){
            v = 1; 
        } else if (currentRow > newRow){
            v = -1;
        }
        if (currentCol < newColumn){
            h = 1; 
        } else if (currentCol > newColumn){
            h = -1;
        }       
        for (int i=currentRow+v; i != newRow;i=i+v ){
            if ((s.getBoard().getPiece(currentCol,i)) != null){
                return false; 
            } 
        }
        for (int i=currentCol+h; i != newColumn;i=i+h ){
            if ((s.getBoard().getPiece(i,currentRow)) != null){
                return false; 
            } 
        }
        return true; 
    }
    
    boolean hasPieceonDiagnal (int currentCol, int currentRow, int newColumn, int newRow, ChessGame s){
        if(Math.abs(newRow - currentRow) != Math.abs(newColumn - currentCol)){
            return false;
        }
        int v= 0;
        int h= 0;
        if (currentRow < newRow){
            v = 1; 
        } else if (currentRow > newRow){
            v = -1;
        }
        if (currentCol < newColumn){
            h = 1; 
        } else if (currentCol > newColumn){
            h = -1;
        }  
        int i=currentRow+v;
        int j=currentCol+h;
        while ( (i != newRow) && (j != newColumn) ){
            if ((s.getBoard().getPiece(j,i)) != null){
                return false; 
            } 
            j=j+h;
            i=i+v;
        }
        
        return true;    
    }
    
    
    public Piece threateningMe(ChessGame s){
        Piece[] p;
        if (isBlack){
          p=s.getBoard().whiteList;    
        }
        else{
          p=s.getBoard().blackList;  
        }
        for (int i= 0; i<p.length; i++){
            if (p[i].isalive){
                if ( p[i].isMovePermitted(p[i].Col, p[i].Row, Col, Row, s)){
                return p[i];
                }   
            }
        }
        return null;
    } 
    
    
    
    public Piece willbeDanger( int newColumn, int newRow, ChessGame s){
        Piece[] p;
        if (isBlack){
          p=s.getBoard().whiteList;    
        }
        else{
          p=s.getBoard().blackList;  
        }
        for (int i= 0; i<p.length; i++){
            if (p[i].isalive){
                if (!(p[i] instanceof King ) && (p[i].isMovePermitted(p[i].Col, p[i].Row, newColumn, newRow, s))){
                return p[i];
                }   
            }
        }
        return null;
    }
    
}

    
    

