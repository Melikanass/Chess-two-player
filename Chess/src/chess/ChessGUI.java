/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;



import apcsgamecontrolview.GameControlView;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import com.sun.java.accessibility.util.AWTEventMonitor;
import java.lang.reflect.Array;
import java.awt.event.MouseEvent;

/**
 *
 * @author S1718034
 */
public class ChessGUI extends GameControlView<ChessGame> {
    //  public ChessGUI 

    private int width;
    private int height;
    private int topMargin;
    private int leftMargin;
    private int wCell;
    private int hCell;
    private int i;
    private boolean switchColor;

    private int curRow = -1;
    private int curCol =-1;
    private int targetRow =-1;
    private int targetCol = -1;
    private boolean curSelected;

    private BufferedImage Piece1;
    private boolean hasClicked = false;

    public ChessGUI(String pn) {
        super(pn);
        
        //hasClicked; 
    }

    /*
    @Override
    public String getPlayerName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     */
    @Override
    public String handleInputRequest(ChessGame gm, String string) {
        setInputMode(string);
        return getInputFromGUI();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleMouseClick(ChessGame gm, int ea, MouseEvent me) {

        if (!hasClicked) {
            int xinputvalue = me.getX() - leftMargin;
            int yinputvalue = me.getY() - topMargin;
            //if it's not an empty string it's not ur turn
            if (!getInputMode().equals("")) {
                xinputvalue = xinputvalue / wCell;
                yinputvalue = yinputvalue / hCell;

                if ((xinputvalue >= 0) && (xinputvalue <= 7) && (yinputvalue >= 0) && (xinputvalue <= 7)) {

                    curRow = yinputvalue;
                    curCol = xinputvalue;
                }
                
                hasClicked = true;

            }
        } else {
            int nextX = me.getX() - leftMargin;
            int nextY = me.getY() - topMargin;
            //if it's not an empty string it's not ur turn
            if (!getInputMode().equals("")) {
                nextX = nextX / wCell;
                nextY = nextY / hCell;
                if ((curRow == nextY) && (curCol == nextX)) {
                    targetRow = -1;
                    targetCol = -1;
                    curRow = -1;
                    curCol = -1;
                    hasClicked = false;
                }
                else {
                    targetRow = nextY;
                    targetCol = nextX;
                    setInputMode("");
                    setInputFromGUI(colLetter(curCol) + getRow(curRow).toString() + " to " + colLetter(nextX) + getRow(nextY).toString());
                    targetRow = -1;
                    targetCol = -1;
                    curRow = -1;
                    curCol = -1;
                    hasClicked = false;
                }
            }
            else {
                targetRow = -1;
                targetCol = -1;
            }
        }
    }

    protected String colLetter(int MC) {
        MC = MC + 65;
        char b = (char) MC;
        String s = Character.toString(b);
        s = s.toUpperCase();
        return s;
    }

    protected Integer getRow(int s) {
        return 8 - s;
    }

    public String pieceImageAddr(Piece a) {

        if (a == null) {
            return "";
        }

        if (a.getColor() == false) {
            if (a instanceof King) {
                return "WhiteKing.png";
            }
            if (a instanceof Queen) {
                return "WhiteQueen.png";
            }
            if (a instanceof Bishop) {
                return "WhiteBishop.png";
            }
            if (a instanceof Knight) {
                return "WhiteKnight.png";
            }
            if (a instanceof Rook) {
                return "WhiteRook.png";
            }
            if (a instanceof Pawn) {
                return "WhitePawn.png";
            }
        } else if (a.getColor() == true) {
            if (a instanceof King) {
                return "BlackKing.png";
            }
            if (a instanceof Queen) {
                return "BlackQueen.png";
            }
            if (a instanceof Bishop) {
                return "BlackBishop.png";
            }
            if (a instanceof Knight) {
                return "BlackKnight.png";
            }
            if (a instanceof Rook) {
                return "BlackRook.png";
            }
            if (a instanceof Pawn) {
                return "BlackPawn.png";
            }
        }
        return "";
    }

    private static BufferedImage getImage(String file) {
        try {
            return ImageIO.read(new File(file));
        } catch (IOException ex) {
            Logger.getLogger(ChessGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void paint(ChessGame gm, Graphics g, int w, int h) {
        width = w;
        height = h;
        wCell = (int) (w * .1);
        hCell = (int) (h * .1);
        leftMargin = (int) (w * .1);
        topMargin = (int) (h * .1) + 40;
        i = 0;
        switchColor = true;
        curSelected = false;

        g.setColor(Color.lightGray);
        g.drawRect(leftMargin - 1, topMargin - 1, 8 * wCell + 1, 8 * hCell + 1);
        g.drawRect(leftMargin - 1, topMargin - 1, 8 * wCell + 2, 8 * hCell + 2);
        g.drawRect(leftMargin - 1, topMargin - 1, 8 * wCell + 3, 8 * hCell + 3);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(leftMargin, topMargin, 8 * wCell, 8 * hCell);
        int row;
        int col;
        while (i < 64) {
            row = (int) (i / 8.0);
            col = (int) (i % 8);
            g.clearRect(leftMargin + col * wCell, topMargin + row * hCell, wCell, hCell);
            if (col == 6) {
                i = i + 3;
            } else if (col == 7) {
                i = i + 1;
            } else {
                i = i + 2;
            }
        }
        if ((curCol)>=0){
        g.setColor(Color.RED);
        g.drawRect(leftMargin + curCol*wCell +2, topMargin + curRow*hCell +2, wCell-4, hCell-4);
        }
        if ((targetCol)>=0){
        g.setColor(Color.RED);
        g.drawRect(leftMargin + targetCol*wCell +2, topMargin + targetRow*hCell +2, wCell-4, hCell-4);
        }
        
        g.setColor(Color.BLACK);
        String[] scarlet = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (int f = 0; f <= 7; f++) {
            g.drawString(scarlet[f], leftMargin + f * wCell + (wCell / 2) - 10, topMargin - 10);
            g.drawString(Integer.toString(f + 1), leftMargin - 20, topMargin + (7 - f) * hCell + (hCell / 2));

        }

        String path = "C:\\Users\\s1718034\\OneDrive - Houston Independent School District\\Semester Project - Nassizadeh, Melika\\ChessPictures\\";
        for (int ro = 0; ro < 8; ro++) {
            for (int co = 0; co < 8; co++) {
                if (gm.getBoard().getPiece(co, ro) != null) {
                    String s = pieceImageAddr(gm.getBoard().getPiece(co, ro));
                    //g.setColor(Color.BLACK);
                    //g.drawString(s, leftMargin + co*wCell, topMargin + ro*hCell);
                    Piece1 = getImage(path + s);
                    g.drawImage(Piece1, leftMargin + co * wCell, topMargin + ro * hCell, null);
                    //Image img =  new Image
                }
            }
        }

        //handleMouseClick(gm, h, MouseEvent.ACTION_EVENT_MASK);
        if (!getInputMode().equals("")) {
          paintInputRequest(g, w, h);
        }     
    }
}
