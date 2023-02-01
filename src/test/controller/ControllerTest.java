package test.controller;

import controller.Controller;
import controller.GameState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Fred X2026
 *
 */

class ControllerTest {
private Controller controller;
    @BeforeEach
    void setUp() {
        this.controller = new Controller();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getGameState() {
        GameState expected = GameState.MainMenu;
        assertEquals(expected, this.controller.getGameState());
    }
}