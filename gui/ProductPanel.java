package gui;

import model.Product;
import service.InventoryManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProductPanel extends JPanel {
    private InventoryManager manager;
    private DefaultTableModel tableModel;

    private JTextField idField, nameField, qtyField, priceField;
    private JTable table;

    public ProductPanel() {
        manager = new InventoryManager();
        setLayout(new BorderLayout());

        // FORM Panel (North)
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        idField = new JTextField();
        nameField = new JTextField();
        qtyField = new JTextField();
        priceField = new JTextField();

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Quantity:"));
        formPanel.add(qtyField);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(priceField);

        add(formPanel, BorderLayout.NORTH);

        // BUTTON Panel (Center)
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.CENTER);

        // TABLE Panel (South)
        String[] columns = {"ID", "Name", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(750, 300));
        add(scrollPane, BorderLayout.SOUTH);

        refreshTable();

        // Button actions
        addButton.addActionListener(e -> {
            try {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                int qty = Integer.parseInt(qtyField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());

                if (manager.findById(id) != null) {
                    JOptionPane.showMessageDialog(this, "Product ID already exists!");
                    return;
                }

                Product p = new Product(id, name, qty, price);
                manager.addProduct(p);
                refreshTable();
                clearFields();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid values.");
            }
        });

        updateButton.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                int qty = Integer.parseInt(qtyField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());

                Product existing = manager.findById(id);
                if (existing != null) {
                    existing.setName(name);
                    existing.setQuantity(qty);
                    existing.setPrice(price);
                    manager.saveToFile();
                    refreshTable();
                    clearFields();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a product to update.");
            }
        });

        deleteButton.addActionListener(e -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                String id = (String) tableModel.getValueAt(selected, 0);
                manager.removeProduct(id);
                refreshTable();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Select a product to delete.");
            }
        });

        // Table row selection
        table.getSelectionModel().addListSelectionListener(event -> {
            int selected = table.getSelectedRow();
            if (selected >= 0) {
                idField.setText((String) tableModel.getValueAt(selected, 0));
                nameField.setText((String) tableModel.getValueAt(selected, 1));
                qtyField.setText(tableModel.getValueAt(selected, 2).toString());
                priceField.setText(tableModel.getValueAt(selected, 3).toString());
            }
        });
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        List<Product> products = manager.getAllProducts();
        for (Product p : products) {
            tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getQuantity(), p.getPrice()});
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        qtyField.setText("");
        priceField.setText("");
    }
}
