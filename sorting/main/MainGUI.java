package sorting.main;

import sorting.gui.SortingGUI;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MainGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new SortingGUI().setVisible(true);
        });
    }
}