package com.krupoderov.montyhall.managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.krupoderov.montyhall.gameobjects.Door;

/**
 * @author Krupoderov Mikhail
 * @version 1.0
 */
public class InputManager {
    public static void handleInput(OrthographicCamera camera) {
        // check if the screen is touched
        if (Gdx.input.justTouched()) {
            // get input touch coordinates
            // and set the temp vector with these values
            GameManager.temp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            // get the touch coordinates
            // with respect to the camera's viewport
            camera.unproject(GameManager.temp);

            float touchX = GameManager.temp.x;
            float touchY = GameManager.temp.y;
            handleRestart(touchX, touchY); //TODO: here?

            // iterate the doors array and
            // check if we trapped/touched/clicked on any door
            for (int i = 0; i < GameManager.doors.size; i++) {
                Door door = GameManager.doors.get(i);
                // only check if the door is closed
                if (!door.isOpen) {
                    if (handleDoor(door, touchX, touchY, i)) {
                        break;
                    }
                }
            }
        }
    }

    private static boolean handleDoor(Door door, float touchX, float touchY, int doorIndex) {
        // check whether is touch
        // coordinates lie on the door's bounds
        if ((touchX >= door.position.x) && touchX <= (door.position.x + door.width)
        && (touchY >= door.position.y) && touchY <= (door.position.y + door.height)) {
            switch (GameManager.level) {
                case START:
                    // open a random door from the
                    // remaining doors once the user selects a door
                    GameManager.doors.get(GameManager.getGoatIndices(doorIndex).random()).isOpen = true;
                    // change the state to confirm
                    GameManager.level = GameManager.Level.CONFIRM;
                    TextManager.setSelectedDoor(doorIndex);
                    break;
                case CONFIRM:
                    door.isOpen = true; // open the selected door
                    GameManager.level = GameManager.Level.END; // change the state to end
                    if (!door.isGoat) {
                        GameManager.hasWon = true;
                    }
                    break;
            }
            return true;
        }
        return false;
    }

    public static void handleRestart(float touchX, float touchY) {
        // determine if the user has clicked/touched the restart button
        if ((touchX >= GameManager.restartSprite.getX()) &&
                touchX <= (GameManager.restartSprite.getX() +
                        GameManager.restartSprite.getWidth()) &&
                (touchY >= GameManager.restartSprite.getY()) &&
                touchY <= GameManager.restartSprite.getY() + GameManager.restartSprite.getHeight()) {
            GameManager.restartGame();
        }
    }
}
