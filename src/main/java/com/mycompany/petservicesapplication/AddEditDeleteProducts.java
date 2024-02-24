package com.mycompany.petservicesapplication;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Kavya
 */
public class AddEditDeleteProducts extends javax.swing.JFrame {

    /**
     * Creates new form AddProducts
     */
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement preparedStatement;
    private Item item;
    private final String DEFAULT_QUERY = "SELECT * FROM ITEMS";
    private final String REGULAR_INSERT_QUERY = "INSERT INTO ITEMS (ITEMNAME, PRICE, QUANTITY, PETTYPE, IMAGE) VALUES (?,?,?,?,?)";
    private final String FOOD_INSERT_QUERY = "INSERT INTO ITEMS (ITEMNAME, PRICE, QUANTITY, PETTYPE, IMAGE, FLAVOR, FOODTYPE, EXPIRY) VALUES (?,?,?,?,?,?,?,?)";
    private final String DELETE_QUERY = "DELETE FROM ITEMS WHERE IDITEM=";
    private final String UPDATE_REGULAR_ITEM_QUERY = "UPDATE ITEMS SET ITEMNAME=?, PRICE=?, QUANTITY=?, PETTYPE=?, IMAGE=?, FLAVOR=NULL, FOODTYPE=NULL, EXPIRY=NULL WHERE IDITEM=?";
    private final String UPDATE_FOOD_ITEM_QUERY = "UPDATE ITEMS SET ITEMNAME=?, PRICE=?, QUANTITY=?, PETTYPE=?, IMAGE=?, FLAVOR=?, FOODTYPE=?, EXPIRY=? WHERE IDITEM=?";

    SimpleDateFormat dateFormatText = new SimpleDateFormat("MM/dd/yy");
    SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public AddEditDeleteProducts() {
        initComponents();
        fillTable();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new AdminHome().setVisible(true);
            }
        });

    }

    private void fillTable() {
        try {
            con = MyConnection.connectdb();
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(DEFAULT_QUERY);
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void closeDB() {
        try {
            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void clear() {

        try {
            ID.setText("Auto Generated");
            name.setText("");
            price.setText("");
            quantity.setText("");
            flavor.setText("");
            expiry.setText("MM/dd/yy");
            image.setText("");
            buttonGroup1.clearSelection();
            buttonGroup2.clearSelection();
            buttonGroup3.clearSelection();
            flavor.setEditable(false);
            expiry.setEditable(false);
            jLabelItem.setIcon(new ImageIcon(getClass().getResource("/images/default.jpeg")));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setStatementRegular() {

//        String image = item.getImage().replace(File.separator, "///");
//        item.setImage(image);

        try {

            preparedStatement.setString(1, item.getItemName());
            preparedStatement.setDouble(2, item.getItemPrice());
            preparedStatement.setInt(3, item.getItemQuantity());
            preparedStatement.setString(4, item.getPetType());
            preparedStatement.setString(5, item.getImage());

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void setStatementFood() {

        String dateSql = sqlDateFormat.format(((FoodItem) item).getExpiryDate());

        try {
            preparedStatement.setString(6, ((FoodItem) item).getFlavour());
            preparedStatement.setString(7, ((FoodItem) item).getFoodType());
            preparedStatement.setDate(8, Date.valueOf(dateSql));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private boolean createItem() {

        if (name.getText().equals("") || quantity.getText().equals("") || price.getText().equals("") || image.getText().equals("")
                || (!cat.isSelected() && !dog.isSelected())) {
            JOptionPane.showMessageDialog(null, "Please enter all fields.");
            return false;
        }

        try {

            item = new Item(name.getText(), Integer.parseInt(quantity.getText()), Double.parseDouble(price.getText()), image.getText());
            item.setPetType(cat.isSelected() ? "CAT" : "DOG");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong format for fields. Quantity should be an integer and Price should be an integer/decimal.");
            return false;
        }
        return true;

    }

    private boolean createFoodItem() {

        if (name.getText().equals("") || quantity.getText().equals("") || price.getText().equals("") || image.getText().equals("")
                || flavor.getText().equals("") || expiry.getText().equals("")
                || (!wet.isSelected() && !dry.isSelected())) {
            JOptionPane.showMessageDialog(null, "Please enter all fields.");
            return false;
        }

        java.util.Date expiryDate = null;
        try {
            expiryDate = dateFormatText.parse(expiry.getText());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Wrong date format.");
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong date format.");
            return false;
        }

        try {
            item = new FoodItem(flavor.getText(), expiryDate, image.getText(), name.getText(), Integer.parseInt(quantity.getText()), Double.parseDouble(price.getText()));
            item.setPetType(cat.isSelected() ? "CAT" : "DOG");
            ((FoodItem) item).setFoodType(wet.isSelected() ? "WET" : "DRY");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong format for fields. Quantity should be an integer and Price should be an integer/decimal.");
            return false;
        }
        return true;

    }

    private ImageIcon resizeImage(String path) {

        ImageIcon logoIcon = null;
        if (pathExists(path)) {
            Image logo = new ImageIcon(path).getImage().getScaledInstance(jLabelItem.getWidth(), jLabelItem.getHeight(), Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(logo);

        }
        return logoIcon;

    }

    private boolean pathExists(String path) {

        Path p = Paths.get(path);
        return Files.exists(p);

    }

    private void copyImage(String sourcePath) {

        File source = new File(sourcePath);
        sourcePath = sourcePath.substring(sourcePath.lastIndexOf(File.separator) + 1);

        String currentPath = currentPath();
        File destination = new File(currentPath + sourcePath);

        try {
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving file.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private String currentPath() {

        Path currentWorkingDirectory = Paths.get("").toAbsolutePath();
        currentWorkingDirectory.normalize();
        String currentPath = currentWorkingDirectory.toString();
        currentPath = currentPath + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "images" + File.separator;
        return currentPath;

    }

    private boolean IDexists() {

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (ID.getText().equals(jTable1.getModel().getValueAt(i, 0).toString())) {
                JOptionPane.showMessageDialog(null, "ITEM ID ALREADY EXISTS.");
                return true;

            }
        }
        return false;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        regular = new javax.swing.JRadioButton();
        food = new javax.swing.JRadioButton();
        ID = new javax.swing.JTextField();
        name = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        cat = new javax.swing.JRadioButton();
        dog = new javax.swing.JRadioButton();
        wet = new javax.swing.JRadioButton();
        dry = new javax.swing.JRadioButton();
        flavor = new javax.swing.JTextField();
        expiry = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        image = new javax.swing.JTextField();
        jLabelItem = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(132, 194, 217));
        setPreferredSize(new java.awt.Dimension(900, 750));
        setSize(new java.awt.Dimension(900, 750));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(132, 194, 217));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 225));

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ADDING NEW ITEM");

        jLabel12.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Logo1.png"))); // NOI18N
        jLabel12.setText("PAWFECT PETS");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButtonAdd.setBackground(new java.awt.Color(148, 99, 61));
        jButtonAdd.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jButtonAdd.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAdd.setText("ADD");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonUpdate.setBackground(new java.awt.Color(148, 99, 61));
        jButtonUpdate.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jButtonUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setText("UPDATE");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(148, 99, 61));
        jButtonDelete.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jButtonDelete.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelete.setText("DELETE");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonRefresh.setBackground(new java.awt.Color(148, 99, 61));
        jButtonRefresh.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jButtonRefresh.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRefresh.setText("REFRESH");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRefresh)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd)
                    .addComponent(jButtonUpdate)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonRefresh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 900, 210);

        jPanel2.setBackground(new java.awt.Color(242, 178, 73));
        jPanel2.setPreferredSize(new java.awt.Dimension(860, 590));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Item Type");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Item ID");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Name");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Price");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Quantity");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Pet Type");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Flavor");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Food Type");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Expiry Date");

        buttonGroup1.add(regular);
        regular.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        regular.setForeground(new java.awt.Color(0, 0, 0));
        regular.setText("Regular");
        regular.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                regularItemStateChanged(evt);
            }
        });

        buttonGroup1.add(food);
        food.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        food.setForeground(new java.awt.Color(0, 0, 0));
        food.setText("Food");
        food.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                foodItemStateChanged(evt);
            }
        });

        ID.setEditable(false);
        ID.setFont(new java.awt.Font("Times New Roman", 2, 13)); // NOI18N
        ID.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        ID.setText("Auto Generated");

        name.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        name.setForeground(new java.awt.Color(0, 0, 0));

        price.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        price.setForeground(new java.awt.Color(0, 0, 0));
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });

        quantity.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        quantity.setForeground(new java.awt.Color(0, 0, 0));

        buttonGroup2.add(cat);
        cat.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cat.setForeground(new java.awt.Color(0, 0, 0));
        cat.setText("Cat");

        buttonGroup2.add(dog);
        dog.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        dog.setForeground(new java.awt.Color(0, 0, 0));
        dog.setText("Dog");
        dog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dogActionPerformed(evt);
            }
        });

        buttonGroup3.add(wet);
        wet.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        wet.setForeground(new java.awt.Color(0, 0, 0));
        wet.setText("Wet Food");
        wet.setEnabled(false);
        wet.setFocusPainted(false);
        wet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wetActionPerformed(evt);
            }
        });

        buttonGroup3.add(dry);
        dry.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        dry.setForeground(new java.awt.Color(0, 0, 0));
        dry.setText("Dry Food");
        dry.setActionCommand("");
        dry.setEnabled(false);

        flavor.setEditable(false);
        flavor.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        flavor.setForeground(new java.awt.Color(0, 0, 0));

        expiry.setEditable(false);
        expiry.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        expiry.setText("M/d/yy");
        expiry.setFont(new java.awt.Font("Times New Roman", 2, 13)); // NOI18N
        expiry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expiryActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Image URL");

        image.setEditable(false);
        image.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        image.setForeground(new java.awt.Color(0, 0, 0));
        image.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageMouseClicked(evt);
            }
        });

        jLabelItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/default.jpeg"))); // NOI18N

        jTable1.setBackground(new java.awt.Color(242, 242, 239));
        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel14.setText("* Click on the Image URL field on the right to upload a file.");

        jLabel15.setText("* If no image is displayed then the image path does not exists.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(9, 9, 9)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel7))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(24, 24, 24)
                                                    .addComponent(jLabel5))))
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(cat)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dog))
                                    .addComponent(ID)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(regular)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(food))
                                    .addComponent(name)
                                    .addComponent(price)
                                    .addComponent(quantity)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(wet)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dry))
                                    .addComponent(flavor)
                                    .addComponent(expiry, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(55, 55, 55))
                            .addComponent(jLabel13))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelItem, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(223, 223, 223))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(regular)
                            .addComponent(food))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(dog)
                            .addComponent(cat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(flavor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(wet)
                            .addComponent(dry))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(expiry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelItem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)))
                .addGap(126, 126, 126))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 204, 900, 550);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void dogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dogActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed

        boolean flag = false;
        if (IDexists()) {
            return;
        }

        try {

            if (regular.isSelected()) {

                preparedStatement = con.prepareStatement(REGULAR_INSERT_QUERY);
                flag = createItem();
                if (!flag) {
                    return;
                }
                setStatementRegular();

            } else if (food.isSelected()) {

                preparedStatement = con.prepareStatement(FOOD_INSERT_QUERY);
                flag = createFoodItem();
                if (!flag) {
                    return;
                }
                setStatementRegular();
                setStatementFood();

            } else {
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, item.toString(), "CONFIRM ITEM DETAILS", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "ITEM HAS BEEN ADDED!");
//                    copyImage(image.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "NOT ADDED.");
                }
            }

            closeDB();
            fillTable();
            clear();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_jButtonAddActionPerformed


    private void expiryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expiryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expiryActionPerformed

    private void wetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wetActionPerformed

    private void foodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_foodItemStateChanged

        flavor.setEditable(true);
        wet.setEnabled(true);
        dry.setEnabled(true);
        expiry.setText("M/d/yy");
        expiry.setEditable(true);
    }//GEN-LAST:event_foodItemStateChanged

    private void regularItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_regularItemStateChanged

        flavor.setText("");
        flavor.setEditable(false);
        buttonGroup3.clearSelection();
        wet.setEnabled(false);
        dry.setEnabled(false);
        expiry.setText("");
        expiry.setEditable(false);
    }//GEN-LAST:event_regularItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        try {

            int row = jTable1.getSelectedRow();

            String rId = jTable1.getModel().getValueAt(row, 0).toString();
            ID.setText(rId);
            String rName = jTable1.getModel().getValueAt(row, 1).toString();
            name.setText(rName);
            String rPrice = jTable1.getModel().getValueAt(row, 2).toString();
            price.setText(rPrice);
            String rQuantity = jTable1.getModel().getValueAt(row, 3).toString();
            quantity.setText(rQuantity);
            String rPetType = jTable1.getModel().getValueAt(row, 4).toString();
            cat.setSelected(rPetType.equals("CAT"));
            dog.setSelected(rPetType.equals("DOG"));
            String rImage = jTable1.getModel().getValueAt(row, 5).toString();
//            rImage = rImage.replace("///", File.separator);
            image.setText(rImage);


            String currentPath = currentPath();
            currentPath=currentPath+rImage;

//            rImage = rImage.substring(rImage.lastIndexOf(File.separator) + 1);
//            currentPath = currentPath + rImage;
            

            try {
                jLabelItem.setIcon(resizeImage(currentPath));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading image.");
            }

            if (jTable1.getModel().getValueAt(row, 6) != null && jTable1.getModel().getValueAt(row, 7) != null
                    && jTable1.getModel().getValueAt(row, 8) != null) {

                food.setSelected(true);
                String rFlavor = jTable1.getModel().getValueAt(row, 6).toString();
                flavor.setText(rFlavor);
                String rFoodType = jTable1.getModel().getValueAt(row, 7).toString();
                wet.setSelected(rFoodType.equals("WET"));
                dry.setSelected(rFoodType.equals("DRY"));
                String rExpiry = jTable1.getModel().getValueAt(row, 8).toString();

                java.util.Date d = null;
                try {
                    d = sqlDateFormat.parse(rExpiry);
                    expiry.setText(dateFormatText.format(d));
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Wrong date format.");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Wrong date format.");
                }

            } else {

                regular.setSelected(true);
                flavor.setText("");
                flavor.setEditable(false);
                buttonGroup3.clearSelection();
                wet.setEnabled(false);
                dry.setEnabled(false);
                expiry.setText("");
                expiry.setEditable(false);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed

        boolean flag = false;
        try {

            if (regular.isSelected()) {
                flag = createItem();
                if (!flag) {
                    return;
                }
            } else if (food.isSelected()) {
                flag = createFoodItem();
                if (!flag) {
                    return;
                }
            } else {
                return;
            }

            int delete = JOptionPane.showConfirmDialog(null, "Permanently delete this record? \n" + item.toString(), "CONFIRM DELETE", JOptionPane.YES_NO_OPTION);
            if (delete == JOptionPane.YES_OPTION) {

                int result = st.executeUpdate(DELETE_QUERY + ID.getText());
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "ITEM DELETED");
                } else {
                    JOptionPane.showMessageDialog(null, "NOT DELETED. REFRESH AND CHECK ID.");
                }
            }

            closeDB();
            fillTable();
            clear();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed

        boolean flag = false;
        try {

            if (regular.isSelected()) {

                preparedStatement = con.prepareStatement(UPDATE_REGULAR_ITEM_QUERY);
                flag = createItem();
                if (!flag) {
                    return;
                }
                setStatementRegular();
                preparedStatement.setString(6, ID.getText());

            } else if (food.isSelected()) {

                preparedStatement = con.prepareStatement(UPDATE_FOOD_ITEM_QUERY);
                flag = createFoodItem();
                if (!flag) {
                    return;
                }
                setStatementRegular();
                setStatementFood();
                preparedStatement.setString(9, ID.getText());

            } else {
                return;
            }

            int update = JOptionPane.showConfirmDialog(null, "Update this record? \n" + item.toString(), "CONFIRM UPDATE", JOptionPane.YES_NO_OPTION);
            if (update == JOptionPane.YES_OPTION) {

                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    JOptionPane.showMessageDialog(null, "ITEM UPDATED");
//                    copyImage(image.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "NOT UPDATED. REFRESH AND CHECK ID. ID CAN'T BE CHANGED.");
                }
            }

            clear();
            closeDB();
            fillTable();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void imageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageMouseClicked

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            image.setText(file.toString().substring(file.toString().lastIndexOf(File.separator)+1));
            try {
                jLabelItem.setIcon(resizeImage(file.toString()));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error loading image.");
            }
             copyImage(file.toString());
            

        }


    }//GEN-LAST:event_imageMouseClicked

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed

        clear();
        closeDB();
        fillTable();
    }//GEN-LAST:event_jButtonRefreshActionPerformed

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
            java.util.logging.Logger.getLogger(AddEditDeleteProducts.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEditDeleteProducts.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEditDeleteProducts.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEditDeleteProducts.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddEditDeleteProducts().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JRadioButton cat;
    private javax.swing.JRadioButton dog;
    private javax.swing.JRadioButton dry;
    private javax.swing.JFormattedTextField expiry;
    private javax.swing.JTextField flavor;
    private javax.swing.JRadioButton food;
    private javax.swing.JTextField image;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelItem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField price;
    private javax.swing.JTextField quantity;
    private javax.swing.JRadioButton regular;
    private javax.swing.JRadioButton wet;
    // End of variables declaration//GEN-END:variables

}
