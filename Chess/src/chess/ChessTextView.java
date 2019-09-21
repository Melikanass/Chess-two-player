/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import apcsgametester.SimpleTextView;

/**
 *
 * @author S1718034
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import apcsgametester.SimpleTextView;
import java.io.OutputStream;

/**
 *
 * @author S1718034
 */
public class ChessTextView extends SimpleTextView<ChessGame>{

    public ChessTextView(String pn){
        super (pn);
    }
    public ChessTextView (String pn, OutputStream os){
        super (pn, os);
    }
    
    public String colorToString(Piece a){
     if (a.getColor()){
         return "  "; // add B for black 
     }  
     else {
         return "  "; // add W for white
     }   
    } 
    public String pieceToString(Piece a){
        if ( a == null ) return "    ";
        
        if ( a.getColor()== false){
        if ( a instanceof King ) return "" + colorToString(a) + "K ";
        if ( a instanceof Queen ) return "" + colorToString(a) + "Q " ;
        if ( a instanceof Bishop ) return "" + colorToString(a) + "B ";
        if ( a instanceof Knight ) return "" + colorToString(a)+ "N ";
        if ( a instanceof Rook ) return "" + colorToString(a) + "R " ;
        if ( a instanceof Pawn ) return "" + colorToString(a) + "P " ;
        }
        else if ( a.getColor()== true){
        if ( a instanceof King ) return "" + colorToString(a) + "k ";
        if ( a instanceof Queen ) return "" + colorToString(a) + "q " ;
        if ( a instanceof Bishop ) return "" + colorToString(a) + "b ";
        if ( a instanceof Knight ) return "" + colorToString(a)+ "n ";
        if ( a instanceof Rook ) return "" + colorToString(a) + "r " ;
        if ( a instanceof Pawn ) return "" + colorToString(a) + "p " ;
        }
        return "    ";
    }
    
   
    public void boardToString(ChessGame u){
    getOut().println("  |----+----+----+----+----+----+----+----|" ); 
    int i = 8;
    for ( int col = 0; col <= 7; col++ ) {
        getOut().print(i + " |");
        i--;
        for ( int row = 0; row <= 7; row++ ) {   
                Piece t = u.getBoard().getPiece(row, col);
                getOut().print(pieceToString(t) + "|" );               
        }    
        getOut().println();
        getOut().println("  |----+----+----+----+----+----+----+----|" );
    }
    //getOut().println("+----+----+----+----+----+----+----+----|" ); 
    //+------+------+------+------+------+------+------+------+
    }
    
    public void letterToString (ChessGame u){
       getOut().println("     a   b    c    d    e    f    g    h   " ); 
    }
    
    @Override
    public void print(ChessGame gm) {
        getOut().println( "Chess text view for " + getPlayerName() );
        
        getOut().println("============================================");
        letterToString(gm);
        boardToString( gm );
        getOut().println("============================================");

    }
 
}