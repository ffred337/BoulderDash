package main;

import contract.IController;
import contract.IModel;
import contract.IView;
import controller.Controller;
import model.Model;
import view.View;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        IModel model = new Model();
        IView view = new View(model, new Controller());
        IController controller = new Controller(view, model);
        view.setController(controller);
        controller.control();
        controller.play();

    }
}
