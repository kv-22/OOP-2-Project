/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.petservicesapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Kavya
 */
public class CustomerProducts extends javax.swing.JFrame {

    /**
     * Creates new form CustomerProducts
     */
    Customer customer;

    public CustomerProducts() {
        initComponents();
    }

    ArrayList<Item> items;

    public CustomerProducts(Customer customer) {
        initComponents();
        this.customer = customer;
        items = new ArrayList<>();
// moved it below
        bringITemsFromDB();
        addItemsToTable();
        registerSelectionListener();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new CustomerMenu(customer).setVisible(true);
            }
        });
    }

    private String currentPath() {

        Path currentWorkingDirectory = Paths.get("").toAbsolutePath();
        currentWorkingDirectory.normalize();
        String currentPath = currentWorkingDirectory.toString();
        currentPath = currentPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "images" + File.separator;
        return currentPath;

    }

    public void registerSelectionListener() {
        menuTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
                int selectedRowIndex = menuTable.getSelectedRow();
                if (selectedRowIndex >= 0) { // else throws null 
                    for (int i = 0; i < items.size(); i++) {
                        if (items.get(i).getItemName().equalsIgnoreCase(menuTable.getValueAt(selectedRowIndex, 0).toString())) {
                            imageLbl.setIcon(resizeImage(currentPath() + items.get(i).getImage()));
                        }
                    }
                }

            }
        });
    }

    public void bringITemsFromDB() {
        String query = "Select * from ITEMS";
        try {
            Connection con = MyConnection.connectdb();
            ResultSet rs;
            PreparedStatement ps;

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                String itsAFood = rs.getString("FoodType");
                if (itsAFood != null) {
                    FoodItem foodItem = new FoodItem();
//                    foodItem.setItemID(rs.getInt("idItem"));
                    foodItem.setItemName(rs.getString("ItemName"));
                    foodItem.setItemPrice(rs.getDouble("Price"));
                    foodItem.setItemQuantity(rs.getInt("Quantity"));
                    foodItem.setImage(rs.getString("Image"));
                    foodItem.setPetType(rs.getString("PetType"));
                    foodItem.setFlavour(rs.getString("Flavor"));
                    foodItem.setFoodType(rs.getString("FoodType"));
                    Date date = rs.getDate("Expiry");
                    if (date != null) {
//                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                        String dateStr = dateFormat.format(date);
                        foodItem.setExpiryDate(date);
                    }
                    items.add(foodItem);
                } else {
                    Item newItem = new Item();
//                    newItem.setItemID(rs.getInt("idItem"));
                    newItem.setItemName(rs.getString("ItemName"));
                    newItem.setItemPrice(rs.getDouble("Price"));
                    newItem.setItemQuantity(rs.getInt("Quantity"));
                    newItem.setImage(rs.getString("Image"));
                    newItem.setPetType(rs.getString("PetType"));
                    items.add(newItem);
                }

            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addItemsToTable() {
        DefaultTableModel model = (DefaultTableModel) menuTable.getModel();
        EachRowEditor rowEditor = new EachRowEditor(menuTable);
        ArrayList<JComboBox<String>> comboList = new ArrayList<>();
        for (Item item : items) {
            comboList.add(new JComboBox<>(createQuantityList(item.getItemQuantity())));
        }
        for (int i = 0; i < items.size(); i++) {
            model.addRow(new Object[]{
                items.get(i).getItemName(), 1, items.get(i).getItemPrice()
            });
            rowEditor.setEditorAt(i, new DefaultCellEditor(comboList.get(i)));
        }
        menuTable.getColumn("Quantity").setCellEditor(rowEditor);
    }

    private void filterTable(String filterTxt) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) menuTable.getModel());
        menuTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(filterTxt));
    }

    public String[] createQuantityList(int num) {
        String[] list = new String[num];
        for (int i = 0; i < num; i++) {
            int x = i + 1;
            list[i] = String.valueOf(x);
        }
        return list;
    }

    private ImageIcon resizeImage(String path) {
        Image img = new ImageIcon(path).getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
        ImageIcon imgIcon = new ImageIcon(img);
        return imgIcon;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menuTable = new javax.swing.JTable();

        JTableHeader header = menuTable.getTableHeader();
        header.setBackground(Color.decode("#84c2d9"));
        header.setForeground(Color.black);
        header.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        menuTable .setDefaultRenderer(String.class, centerRenderer);
        menuTable.setAutoCreateRowSorter(true);
        addToCartBtn = new javax.swing.JButton();
        viewCartBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        searchTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        imageLbl = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(650, 500));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(242, 178, 73));
        jPanel1.setAutoscrolls(true);

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 1, 16)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo1.png"))); // NOI18N
        jLabel2.setText("PAWFECT PETS");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setPreferredSize(new java.awt.Dimension(200, 150));
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jLabel3.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BROWSE YOUR FAVORITE PRODUCTS");

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 26)); // NOI18N
        jLabel1.setText("Products Menu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(119, 119, 119)
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, -2, 650, 190);

        jPanel2.setBackground(new java.awt.Color(132, 194, 217));

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        menuTable.setBackground(new java.awt.Color(132, 194, 217));
        menuTable.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        menuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Quantity", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        menuTable.setRowHeight(40);
        menuTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(menuTable);
        if (menuTable.getColumnModel().getColumnCount() > 0) {
            menuTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(jComboBox1));
        }

        addToCartBtn.setBackground(new java.awt.Color(132, 194, 217));
        addToCartBtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        addToCartBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-shopping-cart-20.png"))); // NOI18N
        addToCartBtn.setText("Add to cart");
        addToCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartBtnActionPerformed(evt);
            }
        });

        viewCartBtn.setBackground(new java.awt.Color(132, 194, 217));
        viewCartBtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        viewCartBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-shopping-cart-20.png"))); // NOI18N
        viewCartBtn.setText("View Cart");
        viewCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCartBtnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Search for a product:");

        searchTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTxtActionPerformed(evt);
            }
        });

        searchBtn.setBackground(new java.awt.Color(132, 194, 217));
        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-search-20.png"))); // NOI18N
        searchBtn.setMaximumSize(new java.awt.Dimension(26, 25));
        searchBtn.setPreferredSize(new java.awt.Dimension(26, 25));
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        imageLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/default.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(addToCartBtn)
                        .addGap(18, 18, 18)
                        .addComponent(viewCartBtn))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(91, 91, 91)
                        .addComponent(imageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(imageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToCartBtn)
                    .addComponent(viewCartBtn))
                .addGap(27, 27, 27))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 180, 650, 290);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "   1", "   2", "   3", "   4", "   5", "   6", "   7", "   8" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(20, 480, 72, 23);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void viewCartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCartBtnActionPerformed
        new Cart(customer).setVisible(true);
//        setVisible(false);
        this.dispose();
    }//GEN-LAST:event_viewCartBtnActionPerformed

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String filter = searchTxt.getText().toUpperCase();
        if (filter.equals("")) {
            JOptionPane.showMessageDialog(null, "Filter field can't be empty.");
            return;
        }

        filterTable(filter);
    }//GEN-LAST:event_searchBtnActionPerformed

    private void menuTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTableMouseClicked


    }//GEN-LAST:event_menuTableMouseClicked

    private void searchTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTxtActionPerformed
        String filter = searchTxt.getText().toUpperCase();
        filterTable(filter);
    }//GEN-LAST:event_searchTxtActionPerformed

    private void addToCartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartBtnActionPerformed
        int selectedRowIndex = menuTable.getSelectedRow();
        if (selectedRowIndex >= 0) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getItemName().equalsIgnoreCase(menuTable.getValueAt(selectedRowIndex, 0).toString())) {
                    try {
                        Item item = items.get(i);
                        int quantity = Integer.parseInt(menuTable.getValueAt(selectedRowIndex, 1).toString());
                        customer.addToCart(item, quantity);
                        String msg = item.getItemName() + " x" + quantity + "\nhas been added Successfully to your cart";
                        JOptionPane.showMessageDialog(this, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    } catch (Exception ex) {

                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select item from the menu", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_addToCartBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerProducts.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerProducts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCartBtn;
    private javax.swing.JLabel imageLbl;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable menuTable;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JButton viewCartBtn;
    // End of variables declaration//GEN-END:variables
}
