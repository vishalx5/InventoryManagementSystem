package gui;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center on screen

        JTabbedPane tabs = new JTabbedPane();

        // Add the Product panel
        tabs.addTab("Products", new ProductPanel());

        // Add the Sales panel
        tabs.addTab("Sales", new SalesPanel());

        // (You can add more panels later like Reports, Login, etc.)

        add(tabs);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
