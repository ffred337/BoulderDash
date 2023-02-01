package test.view;

import contract.IModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.View;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Fred X2026
 *
 */

class ViewTest {
private View view;
private IModel model;
    @BeforeEach
    void setUp() throws IOException {
        this.view = new View(model);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void keyCodeToLevelController() {
    }

    @Test
    void keyCodeToUserOrder() {
    }

    @Test
    void printMessage() {
    }

    @Test
    void setController() {

    }
}