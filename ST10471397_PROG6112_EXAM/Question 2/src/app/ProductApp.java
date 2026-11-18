package app;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductApp extends JFrame {

    private final JTextField txtCustomerName = new JTextField(20);
    private final JComboBox<String> cboProduct = new JComboBox<>(new String[]{
        "Cozy Nights Monarch",
        "Restonic Barbados",
        "Sealy Phuket"
    });
    private final JTextField txtPrice = new JTextField(20);
    private final JButton btnProcess = new JButton("PROCESS");

    public ProductApp() {
        super("Product App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        BuildMenu();
        BuildForm();

        pack();
        setSize(450, 300);
        setLocationRelativeTo(null);
    }

    private void BuildMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        processItem.addActionListener(e -> ProcessSale());
        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(e -> ClearForm());
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);
    }

    private void BuildForm() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 20));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        panel.add(new JLabel("Customer Name:"));
        panel.add(txtCustomerName);
        panel.add(new JLabel("Select Product:"));
        panel.add(cboProduct);
        panel.add(new JLabel("Enter Price:"));
        panel.add(txtPrice);
        panel.add(new JLabel(""));
        panel.add(btnProcess);

        btnProcess.addActionListener(e -> ProcessSale());

        add(panel);
    }

    // Shared logic used by both the PROCESS button and the Tools > Process menu item
    private void ProcessSale() {
        String customerName = txtCustomerName.getText().trim();
        String productName = (String) cboProduct.getSelectedItem();

        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer name.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric price.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProductCalculations calc = new ProductCalculations(price);
        double vat = calc.GetVat();
        double commission = calc.GetCommission();
        double total = calc.GetTotal();

        String message = "Customer Name: " + customerName + "\n"
                + "Product: " + productName + "\n"
                + "VAT: R " + vat + "\n"
                + "COMMISSION: R " + commission + "\n"
                + "TOTAL: R " + total;

        JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);

        SaveToFile(customerName, productName, vat, commission, total);
    }

    private void SaveToFile(String customerName, String productName,
                            double vat, double commission, double total) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt"))) {
            writer.println("DATA LOG");
            writer.println("********************************");
            writer.println("Customer Name: " + customerName);
            writer.println("Product: " + productName);
            writer.println("VAT: R " + vat);
            writer.println("COMMISSION: R " + commission);
            writer.println("TOTAL: R " + total);
            writer.println("********************************");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Could not save data.txt: " + ex.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ClearForm() {
        txtCustomerName.setText("");
        txtPrice.setText("");
        cboProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductApp().setVisible(true));
    }
}
