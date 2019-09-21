/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;

/**
 *
 * @author S1718034
 */
public class Board {
    
    public Piece[][] board;
    public Piece[] whiteList;
    public Piece[] blackList; 
    public Piece[][] boardList; 
    
    public Board(){
      board = new Piece[8][8];
      boardList = new Piece[8][8];
      for ( int i = 0; i <= 7; i++ ) {
            for ( int j = 0;  j<= 7; j++ ) {
                board[i][j] = null;
            }
        }
      blackList = new Piece[16];
      whiteList = new Piece[16];
      
      for ( int j = 0;  j<= 15; j++ ) {
            whiteList[j] = null;
        }
      for ( int j = 0;  j<= 15; j++ ) {
            blackList[j] = null;
        } 
   }
    
    public void copy_board(){
        for ( int i = 0; i <= 7; i++ ) {
            for ( int j = 0;  j<= 7; j++ ) {
                boardList[i][j] = board[i][j]; 
            }
        } 
    }
    
    Piece[] getWhiteList(){
        return whiteList;
    }
    Piece[] getBlackList(){
        return blackList;
    }
    Piece[][] getBoard(){
    return board;
    }
        
    public void replacePiece(Piece k, Piece g){
        board[k.Row][k.Col] = g;
        g.isalive = true; 
        k.isalive =false;  
        g.Row= k.Row; 
        g.Col = k.Col;
        g.setDirection(k.getDirection());
        for (int i=0; i<16; i++){
            if (whiteList[i]==k){
                whiteList[i]= g;                
                return; 
            }
            if (blackList[i]==k){
                blackList[i]= g;                
                return; 
            }
        }
    }
    
   public Piece getPiece ( int col, int row ) {
        return board[row][col];
    }
   
   public Piece getWhiteKing (ChessGame z){
       return z.getBoard().getWhiteList()[0];
   }
   public Piece getBlackKing (ChessGame z){
       return z.getBoard().getBlackList()[0];
   }

   void setPiece(int col, int row, Piece k){
       if (row<=7 && col<=7 && row>=0 && col>=0){
           Piece deadPiece = getPiece(col, row );
           board [row][col] = k;
           if (k!=null) {             
             if ( (deadPiece != null) && (deadPiece != k) ){
                deadPiece.isalive = false; 
             }
             setPiece(k.Col, k.Row, null); 
             k.setXY(col, row);   
           } 
       }      
    }
   
    void undo (){
        for ( int i = 0; i <= 7; i++ ) {
            for ( int j = 0;  j<= 7; j++ ) {
                board[i][j] = boardList[i][j];
                if (board[i][j] !=null){
                    board[i][j].setXY(j, i);
                    board[i][j].isalive= true; 
                }
            }
        }
    }
    
   
  
}