/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import apcsgame.GameBase;
import java.util.Scanner;

/**
 *
 * @author S1718034
 */
public class ChessGame extends GameBase {

    private Board board;
    private int playerTurn; // 0= player one/bottom, 1= player two/top
    private boolean moveExecuted;
    private boolean whiteCheck;
    private boolean blackCheck;
    private boolean pawnChanged;
    private Piece markerPawn;
    private Piece lastPiece; 
    private String messageStr;
    private boolean isGameOver;

    public ChessGame() {
        super();
        board = new Board();
        moveExecuted = false;
        whiteCheck = false;
        blackCheck = false;
        markerPawn = null;
        lastPiece = null; 
        messageStr = "Make a Move";
        isGameOver= false;
        
    }

    Piece getLastPlayer(){
        return lastPiece;  
    }
    
    Board getBoard() {
        return board;
    }

    protected boolean gameover() {
        return isGameOver;
    }

    protected int[] parseMoveMessage(String msg) {
        String nowlocation = msg.substring(0, 2); //piece1 location 
        String targetlocation = msg.substring(6, 8);//target location
        int[] result = new int[4];
        // the type Pawn is going to turn into        
        result[0] = getCol(nowlocation);
        result[1] = getRow(nowlocation);
        result[2] = getCol(targetlocation);
        result[3] = getRow(targetlocation); 
        
        return result;

    }
    
    protected int textpawnInto(String msg){
        if (msg.equalsIgnoreCase("QUEEN")){
            return 0;
        } else if (msg.equalsIgnoreCase("KING")){
            return 2; 
        } else if (msg.equalsIgnoreCase("ROOK")){
            return 1; 
        } else if (msg.equalsIgnoreCase("BISHOP")){
            return 3; }
        
        return -50; 
    }

    protected Integer getCol(String MC) {
        MC = MC.toUpperCase();
        char x = MC.charAt(0); // This gives the character 'a'
        int num = ((int) x) - 65;
        return num;
    }

    protected Integer getRow(String MC) {
        return 8 - Integer.parseInt(MC.substring(1));
    }

    @Override
    protected void playGame() {
        setup();
        playerTurn = 0;
        String move;
        String blackorwhite;
        while (!gameover()) { 
            if (playerTurn ==0)  {
                blackorwhite = "White- ";
            }
            else {
                blackorwhite = "Black- ";
            }
            move = requestInputFromPlayer(playerTurn, blackorwhite + messageStr); 
            if (markerPawn != null){
                if (isTextForPawn(move)){  
                  changePawn( markerPawn, move);
                  markerPawn = null; 
                  lastPiece = null; 
                  updateTurn();
                }
                else {
                    messageStr = "Text is not valid, please type in Queen, Knight, Rook, or Bishop: ";
                }                
            }
            else if (isTextValid(move)) {
                int[] colandrow = parseMoveMessage(move);
                if (isNumValid(colandrow)) {
                    excuteMove(colandrow); 
                    if (gameover()) {
                        break;
                    }
                        
                    if ( moveExecuted && canPawnChange(lastPiece) ){
                        markerPawn = lastPiece;
                    }
                    else {markerPawn = null; }
                    if (moveExecuted) {
                        if (markerPawn == null) {
                            updateTurn();  
                        }
                        else messageStr = "Please type in Queen, Knight, Rook, or Bishop: ";
                    }
                    else if (messageStr.equalsIgnoreCase("Make a Move")){
                       messageStr = "Invalid move, try again!";              
                    }
                    moveExecuted = false;
                }
            } 
            else messageStr = "Is not valid, try again!";
        }
        move = requestInputFromPlayer(playerTurn, messageStr); 
        
    }

    protected boolean isTextForPawn(String msg){
        if (msg.equalsIgnoreCase("Queen")){
            return true;
        } else if (msg.equalsIgnoreCase("Knight")){
            return true; 
        } else if (msg.equalsIgnoreCase("Rook")){
            return true; 
        } else if (msg.equalsIgnoreCase("Bishop")){
            return true; 
        }
        else  return false;
    }
    
    protected boolean isTextValid(String msg) {
        if (msg == null) {
            return false;
        } 
        else if (5<msg.length()) {
            if (!msg.substring(2, 6).equalsIgnoreCase(" to ")){
            return false;  }
        } else if (msg.length() != 8){
            return false; 
        }
        msg = msg.toUpperCase();
        char[] letter = new char[2];
        letter[0] = msg.charAt(0);
        letter[1] = msg.charAt(6); //changed 7 into 6 from before  
        Integer s[] = new Integer[2];
        
        s[0] = 8 - Integer.parseInt(msg.substring(1, 2));
        s[1] = 8 - Integer.parseInt(msg.substring(7,8));
        for (int z = 0; z < 1; z++) {
            if (((((int) letter[z]) - 65) < 0) || (7 < (((int) letter[z]) - 65))) {
                return false;
            }
            if ((s[z] < 0) || (7 < s[z])) {
                return false;
            }
        }      
        return true;
    }

    // double check 
    protected boolean isNumValid(int[] colandrow) {
        for (int i = 0; i <= 3; i++) {
            if (!(colandrow[i] <= 7) || !(0 <= colandrow[i])) {
                return false;
            }
        }  
        return true;
    }

    protected boolean isValid(int currentCol, int currentRow, int newColumn, int newRow, ChessGame s) {
        return s.getBoard().getPiece(currentCol, currentRow).isMovePermitted(currentCol, currentRow, newColumn, newRow, s);
    }

    public void updateTurn() {
        if (blackCheck ){
            messageStr = "king is in check !!!";
        }else if (whiteCheck){
            messageStr = "king is in check !!!";
        }
        else messageStr = "Make a Move";
        if (playerTurn == 0) {
            playerTurn = 1;
        } else {
            playerTurn = 0;
        }
    }

    public void excuteMove(int[] s) { 
        Piece rightnow = board.getPiece(s[0], s[1]);
        int col = s[2]; //newCol
        int row = s[3]; //newRow 
        if (rightnow != null){
            //giving player 0 the color white 
            // player 0 is white 
            if (!(board.getPiece(s[0], s[1]).getColor()) // is not black 
                    == (playerTurn != 0) // is not white 
                    ){
                    return;               
            }
            //
            if (rightnow.isMovePermitted(s[0], s[1], s[2], s[3], this)) { 
                board.copy_board();
                board.setPiece(col, row, rightnow); 
                
                //checking for check, doesn't allow the move to happen 
                if (wouldBeChecked(!rightnow.getColor())){                   
                    board.undo();
                    messageStr = "King would be checked, you are not allowed";
                    return;           
                }
                
                rightnow.setMoved(true); 
               
               if (rightnow instanceof King){
                    if (((King) rightnow).isCastleAllowed()){
                        Castle( rightnow, col, row);
                    }
                }
//               if (rightnow instanceof Pawn){
//                   if (((Pawn) rightnow).can_enpassante()){
//                       enpassante(rightnow, col, row);
//                   }                   
//               }   
                moveExecuted = true;
                
                lastPiece = rightnow;                 
                isKingCheck();
            }
        }
    }
    
    public void enpassante(Piece k, int newColumn, int newRow){
        if (k.getDirection()){
            k = board.getPiece(newColumn, k.Row-1);
            k.isalive= false; 
            board.setPiece(newColumn ,k.Row-1,null);        
        }
        else {
            k = board.getPiece(newColumn, k.Row+1);
            k.isalive= false; 
            board.setPiece(newColumn ,k.Row+1,null); 
        }
        
    }
    
    public void Castle( Piece k, int newColumn, int newRow){
        if (k.getDirection()){
           if (newColumn == 6){
               board.setPiece(5,7,(board.getPiece(7, 7)));
           } 
           if (newColumn == 2){
               board.setPiece(3,7,(board.getPiece(0, 7)));
           } 
        }
        else {
           if (newColumn == 6){
               board.setPiece(5,0,(board.getPiece(7, 0)));
           } 
           if (newColumn == 2){
               board.setPiece(3,0,(board.getPiece(0, 0)));
           } 
        }
    }
    
    public boolean isKingCheckMate(Piece b){
        Piece[] p;
        if (!b.isBlack){
          p=getBoard().whiteList;    
        }
        else{
          p=getBoard().blackList;   
        }
        for (int i= 0; i<p.length; i++){
            if (p[i].isalive){
                for (int row = 0; row<=7; row++){
                    for (int col = 0; col <=7; col++){
                        if(p[i].isMovePermitted(p[i].Col, p[i].Row, col, row, this)){
                            board.copy_board();
                            board.setPiece(col, row, p[i]);
                            if (!wouldBeChecked(!b.getColor())){
                              board.undo();
                              return false;   
                            }
                            board.undo();       
                        }
                    }
                }
            }
        }
        return true; 
    }
    
    public boolean canPawnChange(Piece k){
        if (!(k instanceof Pawn)){
            return false;
        }
        else if (k.getDirection()){
            if ((k.Row !=0)){ //(k.Row !=0)
                return false; 
            } 
        }
        else if (!k.getDirection()){
            if ((k.Row !=7)){
                return false; 
            }
        }
        return true; 
    }
    
    public void changePawn( Piece k, String msg){
        //0= queen 
        //1= Rook  
        //2= knight
        //3= bishop 
        if (msg.equalsIgnoreCase("Queen")){
            board.replacePiece(k, new Queen(k.getColor()) );     
        }
        else if (msg.equalsIgnoreCase("Rook")){
            board.replacePiece(k, new Rook(k.getColor()));
        }
        else if (msg.equalsIgnoreCase("Knight")){
            board.replacePiece(k, new Knight(k.getColor()));
        }
        else if (msg.equalsIgnoreCase("Bishop")){
            board.replacePiece(k, new Bishop(k.getColor()));
        }
        else return;  
    }
     
    public void isKingCheck(){  
        Piece a = this.getBoard().getWhiteKing(this); 
        if ((a.threateningMe(this)!=null)){
            whiteCheck =true;
            if (isKingCheckMate(a)){
                messageStr = "   !!black Player Won, white king is checkmate!!";
                isGameOver = true;
                return;
            }  
        }
        else {
            whiteCheck =false;
        }
        Piece b = this.getBoard().getBlackKing(this);
        if ((b.threateningMe(this) !=null)){
            blackCheck =true; 
            if (isKingCheckMate(b)){
                messageStr = "   !!White Player Won, black king is checkmate!!";
                isGameOver = true;
                return;
            }
            
        }
        else {
            blackCheck =false;
        } 
    }
    
    public boolean wouldBeChecked(boolean s){
        Piece a;
        if (s) a= this.getBoard().getWhiteKing(this); 
        else a = this.getBoard().getBlackKing(this);
        return (a.threateningMe(this)!= null);
             
    }
    

    @Override
    public int getMaxPlayerCount() {
        return 2;
    }

    @Override
    public int getMinPlayerCount() {
        return 2;
    }
    protected void createandSetPieces() {
        createPieces(false, 7);
        createPieces(true, 0);
    }

    protected void setup() {
        createandSetPieces();
    }

    protected void createPieces(boolean color, int row) {
        //creates a whole team of a color and initiates them on the board 

        Piece[] b;
        if (color) {
            b = board.blackList;
        } else {
            b = board.whiteList;
        }

        board.setPiece(0, row, b[1] = new Rook(color));
        board.setPiece(7, row, b[2] = new Rook(color));
        board.setPiece(1, row, b[3] = new Knight(color));
        board.setPiece(6, row, b[4] = new Knight(color));
        board.setPiece(2, row, b[5] = new Bishop(color));
        board.setPiece(5, row, b[6] = new Bishop(color));
        board.setPiece(3, row, b[7] = new Queen(color));
        board.setPiece(4, row, b[0] = new King(color));

        for (int i = 0; i <= 7; i++) {
            if (row == 0) {
                board.setPiece(i, 1 + row, b[i + 8] = new Pawn(color));
            } else {
                board.setPiece(i, row - 1, b[i + 8] = new Pawn(color));
            }
        }

    }

}
