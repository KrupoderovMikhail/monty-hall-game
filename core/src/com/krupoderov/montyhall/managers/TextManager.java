package com.krupoderov.montyhall.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Krupoderov Mikhail
 * @version 1.0
 */
public class TextManager {
    static BitmapFont font; // we draw the text to the screen using this variable
    static GlyphLayout glyphLayout;
    // text corresponding to different states
    static String start = "Select a door";
    static StringBuffer confirm;
    static String win = "You Win!";
    static String lose = "You Lose!";
    // viewport width and height
    static float width, height;

    public static void initialize(float width, float height) {
        TextManager.width = width;
        TextManager.height = height;
        // set the font color to cyan
        font = new BitmapFont();
        font.setColor(Color.CYAN);

        // scale the font size according to screen width
        font.getData().scale(width/1600f);

        glyphLayout = new GlyphLayout();

        confirm = new StringBuffer((String) "You selected door no. Do you want to switch or stay?");
    }

    public static void displayMessage(SpriteBatch batch) {
        // draw the text based on the game state
        switch (GameManager.level) {
            case START:
                // calculations to center the text on the screen
                glyphLayout.setText(font, start);
                font.draw(
                        batch,
                        start,
//                        (width/2 - font.getBounds(start).width/2),
//                        GameManager.doors.first().closeSprite.getY()/2 + font.getBounds(start).height/2);
                        (width/2 - glyphLayout.width/2),
                        GameManager.doors.first().closeSprite.getY()/2 + glyphLayout.height/2);
                break;
            case CONFIRM:
                glyphLayout.setText(font, confirm);
                font.draw(
                        batch,
                        confirm,
//                        (width/2 - font.getBounds(confirm).width/2),
//                        GameManager.doors.first().closeSprite.getY()/2 + font.getBounds(confirm).height/2);
                        (width/2 - glyphLayout.width/2),
                        GameManager.doors.first().closeSprite.getY()/2 + glyphLayout.height/2);
                break;
            case END:
                // draw win/lose text based on the status
                if (GameManager.hasWon) {
                    glyphLayout.setText(font, win);
                    font.draw(
                            batch,
                            win,
                            (width/2 - glyphLayout.width/2),
                            GameManager.doors.first().closeSprite.getY()/2 + glyphLayout.height/2);
                } else {
                    glyphLayout.setText(font, lose);
                    font.draw(
                            batch,
                            lose,
                            (width/2 - glyphLayout.width/2),
                            GameManager.doors.first().closeSprite.getY()/2 + glyphLayout.height/2);
                }
                break;
        }
    }

    public static void setSelectedDoor(int doorIndex) {
        // insert selected door number into confirm display text
        confirm.insert(confirm.indexOf("door no") + "door no".length(), " " + (doorIndex + 1));
    }
}
