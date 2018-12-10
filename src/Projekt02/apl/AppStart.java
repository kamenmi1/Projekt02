package Projekt02.apl;

import Projekt02.controller.Controller3D;
import Projekt02.view.PGRFWindow;

import javax.swing.*;

public class AppStart {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PGRFWindow window = new PGRFWindow();
            //new Controller(window);
            new Controller3D(window.getRaster());
            window.setVisible(true);
        });
    }
}
