package contract;

public interface IView {
    void printMessage(final String message);
    void setController(IController controller);
    void setController();
}
