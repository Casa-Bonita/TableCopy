import java.awt.*;

public class Main {
    public static void main(String[] args) {

        Dimension dimensionFrame = new Dimension(1000, 1000);
        Dimension dimensionTable = new Dimension(900, 800);
        Dimension dimensionScrollTable = new Dimension(900, 800);

        UserInterface ui = new UserInterface(dimensionFrame, dimensionTable, dimensionScrollTable);
        ui.createUI();

        
    }
}
