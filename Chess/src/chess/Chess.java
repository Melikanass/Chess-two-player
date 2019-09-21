/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import apcsgametester.APCSGameTester;
import javax.swing.JComponent;

/**
 *
 * @author S1718034
 */
public class Chess {

    /**
     * @param args the command line arguments
     */
    /*
    public MouseEvent() {
        addMouseListener(this);
    }
    */
    public static void main(String[] args) {
        ChessGame parent;
        
        String [] players = { "White - Melika", "Black - Ala" };
        
        /*
        APCSGameTester.launchTextBasedGame( 
                ChessGame.class, 
                ChessTextView.class,
                players );  
        */
        
        ///*
        //launchGUIGameFromLog
        APCSGameTester.launchGUIGameFromLog(
                ChessGame.class, 
                ChessGUI.class,
                "Chess",
                players
                //,1527181270653L idk 
                //,1527183242543L //the pawn switch 
                //,1527183723734L //check mate, check can't move other pawns
                ,1527184080136L //castle 
        );
        //*/
        
        
        //JComponent newContentPane = new MouseEvent();
  
    }
    
}
