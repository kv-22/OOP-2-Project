package com.mycompany.petservicesapplication;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Ayesh
 */
public class Cart extends javax.swing.JFrame {

    /**
     * Creates new form cart
     */
    Customer customer;
    Order order = new Order();
    ArrayList<Item> cart;
    ArrayList<Integer> quantity;
    int selectedRowIndex = 0;
    double orderTotal;

    public Cart() {
        initComponents();
    }

    private void FileOrder() {
    }

    public Cart(Customer customer) {
        initComponents();
        this.customer = customer;
        cart = customer.getCart();
        quantity = customer.getQuantities();
        registerSelectionListener();
        addItemsToTable();
        generateTotal();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new CustomerMenu(customer).setVisible(true);
            }
        });
    }

    public void registerSelectionListener() {
        cartTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedRowIndex = cartTable.getSelectedRow();
            }
        });
    }

    public void addItemsToTable() {
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        EachRowEditor rowEditor = new EachRowEditor(cartTable);
        ArrayList<JComboBox<String>> comboList = new ArrayList<>();
        for (int i = 0; i < cart.size(); i++) {
            int avaliableQuantity = cart.get(i).getItemQuantity();
            JComboBox<String> combo = new JComboBox<>(createQuantityList(avaliableQuantity));
            combo.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    quantity.set(selectedRowIndex, combo.getSelectedIndex() + 1);
                    generateTotal();
                }
            });
            comboList.add(combo);
        }
        for (int i = 0; i < cart.size(); i++) {
            model.addRow(new Object[]{
                cart.get(i).getItemName(), quantity.get(i), cart.get(i).getItemPrice()
            });
            rowEditor.setEditorAt(i, new DefaultCellEditor(comboList.get(i)));
        }
        cartTable.getColumn("Quantity").setCellEditor(rowEditor);
    }

    public String[] createQuantityList(int num) {
        String[] list = new String[num];
        for (int i = 0; i < num; i++) {
            int x = i + 1;
            list[i] = String.valueOf(x);
        }
        return list;
    }

    public void generateTotal() {
        double price = 0;
        for (int i = 0; i < cart.size(); i++) {
            price += (quantity.get(i) * cart.get(i).getItemPrice());

        }
        orderTotal = price;
        totalLbl.setText("Total: " + price + " SAR");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        cartTable = new javax.swing.JTable();
        JTableHeader header = cartTable.getTableHeader();
        header.setBackground(Color.decode("#84c2d9"));
        header.setForeground(Color.black);
        header.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        cartTable .setDefaultRenderer(String.class, centerRenderer);
        totalLbl = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();
        deleteTxt = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        DefaultListCellRenderer dlcr = new DefaultListCellRenderer(); 
        dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER); 
        jComboBox2.setRenderer(dlcr);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(650, 500));
        setSize(new java.awt.Dimension(650, 500));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(132, 194, 217));
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 500));

        jPanel4.setBackground(new java.awt.Color(242, 178, 73));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo1.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(200, 170));

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(142, 98, 77));
        jLabel2.setText("Your Shopping cart");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(72, 72, 72))))
        );

        jPanel2.setBackground(new java.awt.Color(132, 194, 217));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jScrollPane1.setRowHeaderView(null);

        cartTable.setBackground(new java.awt.Color(132, 194, 217));
        cartTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        cartTable.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cartTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Quantity", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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
        cartTable.setGridColor(new java.awt.Color(0, 0, 0));
        cartTable.setRowHeight(50);
        cartTable.setShowGrid(false);
        cartTable.setShowHorizontalLines(true);
        jScrollPane4.setViewportView(cartTable);

        jScrollPane1.setViewportView(jScrollPane4);

        totalLbl.setBackground(new java.awt.Color(242, 178, 73));
        totalLbl.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        totalLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalLbl.setText("Total: 135 SAR");

        jButton1.setBackground(new java.awt.Color(132, 194, 217));
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-right-arrow-30 (1).png"))); // NOI18N
        jButton1.setText("Proceed to checkout");
        jButton1.setBorderPainted(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        backBtn.setBackground(new java.awt.Color(132, 194, 217));
        backBtn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-left-arrow-30.png"))); // NOI18N
        backBtn.setText("Continue Shopping");
        backBtn.setBorderPainted(false);
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        deleteTxt.setBackground(new java.awt.Color(148, 99, 61));
        deleteTxt.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        deleteTxt.setForeground(new java.awt.Color(255, 255, 255));
        deleteTxt.setText("DELETE");
        deleteTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteTxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(totalLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(backBtn)
                                    .addGap(85, 85, 85)
                                    .addComponent(deleteTxt)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(backBtn)
                    .addComponent(deleteTxt))
                .addGap(18, 18, 18))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 650, 466);

        jComboBox2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6" }));
        jComboBox2.setSelectedIndex(1);
        jComboBox2.setToolTipText("Select a value");
        getContentPane().add(jComboBox2);
        jComboBox2.setBounds(33, 472, 72, 21);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // this.setVisible(false);
//        try {
        if (orderTotal != 0) {
            order.setAmount(orderTotal);
            for (int i = 0; i < cart.size(); i++) {
                cart.get(i).setItemQuantity(quantity.get(i));
            }
            order.setCart(cart);
            new Payment(customer, orderTotal, order).setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(null, "EMPTY CART!");
        }

//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "EMPTY CART!");
//        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        new CustomerMenu(customer).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBtnActionPerformed

    private void deleteTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteTxtActionPerformed

        try {
            if (selectedRowIndex < 0) {
                JOptionPane.showMessageDialog(this, "Please select item from the cart", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                customer.removeFromCart(cart.get(selectedRowIndex));
                String msg = "The item has been deleted Successfully";
                JOptionPane.showMessageDialog(this, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
                generateTotal();
                DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
                model.setRowCount(0);
                selectedRowIndex = 0;
                addItemsToTable();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "EMPTY CART!");
        }

    }//GEN-LAST:event_deleteTxtActionPerformed

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
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cart().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.JTable cartTable;
    private javax.swing.JButton deleteTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel totalLbl;
    // End of variables declaration//GEN-END:variables
}