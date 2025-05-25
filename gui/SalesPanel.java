package gui;

import model.Product;
import service.InventoryManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SalesPanel extends JPanel {
    private InventoryManager manager;
    private JComboBox<String> productDropdown;
    private JTextField quantityField;
    private JLabel stockLabel;

    public SalesPanel() {
        manager = new InventoryManager();
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Select Product:"));
        productDropdown = new JComboBox<>();
        populateDropdown();
        add(productDropdown);

        add(new JLabel("Current Stock:"));
        stockLabel = new JLabel("-");
        add(stockLabel);

        add(new JLabel("Quantity to Sell:"));
        quantityField = new JTextField();
        add(quantityField);

        JButton sellButton = new JButton("Sell");
        add(new JLabel()); // empty label for spacing
        add(sellButton);

        // Update stock label when selection changes
        productDropdown.addActionListener(e -> updateStockLabel());

        sellButton.addActionListener(e -> {
            String selectedId = (String) productDropdown.getSelectedItem();
            if (selectedId == null) return;

            Product p = manager.findById(selectedId);
            if (p == null) return;

            try {
                int qtyToSell = Integer.parseInt(quantityField.getText());
                if (qtyToSell <= 0) {
                    JOptionPane.showMessageDialog(this, "Enter a valid quantity.");
                    return;
                }

                if (qtyToSell > p.getQuantity()) {
                    JOptionPane.showMessageDialog(this, "Not enough stock.");
                    return;
                }

                p.setQuantity(p.getQuantity() - qtyToSell);
                manager.saveToFile();
                updateStockLabel();
                JOptionPane.showMessageDialog(this, "Sale completed!");
                quantityField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number.");
            }
        });

        updateStockLabel();
    }

    private void populateDropdown() {
        List<Product> products = manager.getAllProducts();
        for (Product p : products) {
            productDropdown.addItem(p.getId());
        }
    }

    private void updateStockLabel() {
        String selectedId = (String) productDropdown.getSelectedItem();
        if (selectedId == null) return;

        Product p = manager.findById(selectedId);
        if (p != null) {
            stockLabel.setText(String.valueOf(p.getQuantity()));
        }
    }
}
