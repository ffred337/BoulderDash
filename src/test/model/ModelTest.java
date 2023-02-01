package test.model;

import model.Model;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Fred X2026
 *
 */

class ModelTest {
    private Model model;
    int Width = 30;
    int Height = 16;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        this.model = new Model();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getWidth() {
        int expected = this.Width;
        assertEquals(expected, this.model.getMap().getMapWidth());
    }

    @org.junit.jupiter.api.Test
    void getHeight() {
        int expected = this.Height;
        assertEquals(expected, this.model.getMap().getMapHeight());
    }

    @org.junit.jupiter.api.Test
    void loadMap() {
         boolean expected = true;
         this.model.loadMap(2);
        assertEquals(expected, this.model.getIsAlive());
    }
}