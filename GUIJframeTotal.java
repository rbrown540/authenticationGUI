package sample;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Project: CMSC 495 Capstone
 * Author: Jennifer Hicks, Group 3
 * Date: 11/18/2020-12/01/2020
 * 'Amazing Travel Package Planner'
 * Filename: GUIJframe.java
 */



// import org.apache.derby.impl.tools.ij.Main;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Locale;

public class GUIJframeTotal extends JFrame {
    private JPanel MainPanel;
    private JTabbedPane SelectionTabs;
    private JComboBox DomesticDropdown;
    private JButton DestinationSubmitButton;
    private JLabel USA;
    private JLabel Overseas;
    private JComboBox InternationalDropdown;
    private JPanel HotelBoston;
    private JPanel HotelDC;
    private JPanel HotelDublin;
    private JPanel HotelTokyo;
    private JLabel TrumpInt;
    private JLabel AmanTokyo;
    private JLabel BostonHotel;
    private JLabel Westbury;
    private JButton SelectBoston;
    private JButton SelectDC;
    private JButton SelectDublin;
    private JButton SelectTokyo;
    private JLabel CarBoston;
    private JLabel CarDC;
    private JLabel CarTokyo;
    private JLabel CarDublin;
    private JButton hertzDCButton;
    private JButton hertzTokyoButton;
    private JButton hertzDublinButton;
    private JButton hertzBostonButton;
    private JPanel ActivityFoodie;
    private JPanel ActivityHistory;
    private JPanel ActivitySpa;
    private JPanel ActivityNature;
    private JPanel ExcursionsTab;
    private JPanel RentalTab;
    private JPanel HotelTab;
    private JLabel InternationalTitle;
    private JLabel DomesticTitle;
    private JPanel DestinationsTab;
    private JButton FoodieButton;
    private JButton NatureButton;
    private JButton SpaButton;
    private JButton HistoryButton;
    private JLabel FoodiePic;
    private JLabel NaturePic;
    private JLabel SpaPic;
    private JLabel HistoryPic;
    private JPanel SummaryTab;
    private JTextArea SummaryDestinationField;
    private JTextArea SummaryHotelField;
    private JTextArea SummaryCarField;
    private JTextArea SummaryExcursionsField;
    // connection absolute URL
    private static final String dbURL = "jdbc:derby:/Users/jenniferhicks/Desktop/495_db";
    private Connection connectionTRA;
    // End of variables declaration


    public GUIJframeTotal() {
        DestinationSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DomesticDropdown.getSelectedItem() != "Select Destination") {
                    DomesticDropdownActionPerformed(e);
                } else if (InternationalDropdown.getSelectedItem() != "Select Destination") {
                    InternationalDropdownActionPerformed(e);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "You are required to select a destination in order to go somewhere");
                }

            }
        });
        SelectBoston.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BostonHotelActionPerformed(e);
            }
        });
        SelectDC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WashingtonHotelActionPerformed(e);
            }
        });
        SelectTokyo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TokyoHotelActionPerformed(e);
            }
        });
        SelectDublin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DublinHotelActionPerformed(e);
            }
        });
        hertzBostonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BostonCarRentalActionPerformed(e);
            }
        });
        hertzDCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WashingtonCarRentalActionPerformed(e);
            }
        });
        hertzTokyoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TokyoCarRentalActionPerformed(e);
            }
        });
        hertzDublinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DublinCarRentalActionPerformed(e);
            }
        });
        NatureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NatureExcursionActionPerformed(e);
            }
        });
        SpaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SpaExcursionActionPerformed(e);
            }
        });
        HistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistoryExcursionActionPerformed(e);
            }
        });
        FoodieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FoodieExcursionActionPerformed(e);
            }
        });
    }

    /**
     * For: Summary Tab (updates after each action event)
     * FOUR Equals methods; one for each GUI tab
     */
    private void fillSummaryTab(int key, String dataIn) {
        switch (key) {
            case 1:
                SummaryDestinationField.setText(dataIn);
                break;
            case 2:
                SummaryHotelField.setText(dataIn);
                break;
            case 3:
                SummaryCarField.setText(dataIn);
                break;
            case 4:
                SummaryExcursionsField.setText(dataIn);
                break;
            default:
                // something of course
        }
    }

    private void airlineSummaryEquals(String air, String address, String phone) {
        String airlineSummary = "\nYour airline: " + air + "\nAddress: " + address + "\nPhone: " + phone + "\n";
        fillSummaryTab(1, airlineSummary);
    }

    private void hotelSummaryEquals(String hotel, String address, String phone) {
        String hotelSummary = "\nYour hotel: " + hotel + "\nLocation: " + address + "\nPhone Number: " + phone + "\n";
        fillSummaryTab(2, hotelSummary);

    }

    private void carRentalSummaryEquals(String car, String address, String phone) {
        String carSummary = "\nYour automobile: " + car + "\nLocation: " + address + "\nPhone Number: " + phone + "\n";
        fillSummaryTab(3, carSummary);
    }

    private void excursionSummaryEquals(String excursion, String address, String phone) {
        String excursionSummary = "\nYour excursion: " + excursion + "\nLocation: " + address + "\nPhone Number" + phone + "\n";
        fillSummaryTab(4, excursionSummary);
    }

// Domestic Dropdown Action

    private void DomesticDropdownActionPerformed(ActionEvent evt) {
        // TODO: handling code goes here
        createConnection();

        if (DomesticDropdown.getSelectedItem() == "Boston (United Airlines)") {

            try {
                Statement retrieveAirB = connectionTRA.createStatement();
                ResultSet airResult = retrieveAirB.executeQuery("SELECT * FROM Domestic_Airline WHERE DomesticAirlineID = 1");
                while (airResult.next()) {
                    String airFromQ = airResult.getString("DomesticAirline");
                    String addressFromQ = airResult.getString("DomesticAirAddress");
                    String phoneFromQ = airResult.getString("DomesticAirPhone");
                    airlineSummaryEquals(airFromQ, addressFromQ, phoneFromQ);
                }
            } catch (SQLException airE1) {
                airE1.printStackTrace();
            }
        } else if (DomesticDropdown.getSelectedItem() == "D.C. (American Airlines)") {
            try {
                Statement retrieveAirW = connectionTRA.createStatement();
                ResultSet airResult = retrieveAirW.executeQuery("SELECT * FROM Domestic_Airline WHERE DomesticAirlineID = 2");
                while (airResult.next()) {
                    String airFromQ = airResult.getString("DomesticAirline");
                    String addressFromQ = airResult.getString("DomesticAirAddress");
                    String phoneFromQ = airResult.getString("DomesticAirPhone");
                    airlineSummaryEquals(airFromQ, addressFromQ, phoneFromQ);
                }
            } catch (SQLException airE2) {
                airE2.printStackTrace();
            }


        }
        /** if nothing is selected, then what?  **/
//        else {}

        try {
            connectionTRA.close();
        } catch (SQLException airE3) {
            airE3.printStackTrace();
        }


    }


// International Dropdown Action

    private void InternationalDropdownActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        createConnection();

        if (InternationalDropdown.getSelectedItem() == "Tokyo (Japan Airlines)") {

            try {
                Statement retrieveAirT = connectionTRA.createStatement();
                ResultSet airResult = retrieveAirT.executeQuery("SELECT * FROM International_Airline WHERE InternationalAirlineID = 1");
                while (airResult.next()) {
                    String airFromQ = airResult.getString("InternationalAirline");
                    String addressFromQ = airResult.getString("InternationalAirAddress");
                    String phoneFromQ = airResult.getString("InternationalAirPhone");
                    airlineSummaryEquals(airFromQ, addressFromQ, phoneFromQ);
                }
            } catch (SQLException airE4) {
                airE4.printStackTrace();
            }
        } else if (InternationalDropdown.getSelectedItem() == "Dublin (Aer Lingus)") {
            try {
                Statement retrieveAirD = connectionTRA.createStatement();
                ResultSet airResult = retrieveAirD.executeQuery("SELECT * FROM International_Airline WHERE InternationalAirlineID = 2");
                while (airResult.next()) {
                    String airFromQ = airResult.getString("InternationalAirline");
                    String addressFromQ = airResult.getString("InternationalAirAddress");
                    String phoneFromQ = airResult.getString("InternationalAirPhone");
                    airlineSummaryEquals(airFromQ, addressFromQ, phoneFromQ);
                }
            } catch (SQLException airE5) {
                airE5.printStackTrace();
            }


        }
        /** if nothing is selected, then what?  **/
//        else {}
        try {
            connectionTRA.close();
        } catch (SQLException airE6) {
            airE6.printStackTrace();
        }
    }


// Hotel Selections

// Note: Needs modifications due to change from radio button to JButton

// Boston

    private void BostonHotelActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        createConnection();
        try {
            Statement bostonHotelButton = connectionTRA.createStatement();
            ResultSet bostonHotelResult = bostonHotelButton.executeQuery("SELECT * FROM Domestic_Hotel WHERE DomesticHotelID = 1");
            while (bostonHotelResult.next()) {
                String hotelFromQ = bostonHotelResult.getString("DomesticHotel");
                String addressFromQ = bostonHotelResult.getString("DomesticHotelAddress");
                String phoneFromQ = bostonHotelResult.getString("DomesticHotelPhone");
                hotelSummaryEquals(hotelFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException hotE1) {
            hotE1.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException hotE2) {
            hotE2.printStackTrace();
        }

    }


// D.C.

    private void WashingtonHotelActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        createConnection();
        try {
            Statement washingtonHotelButton = connectionTRA.createStatement();
            ResultSet washingtonHotelResult = washingtonHotelButton.executeQuery("SELECT * FROM Domestic_Hotel WHERE DomesticHotelID = 2");
            while (washingtonHotelResult.next()) {
                String hotelFromQ = washingtonHotelResult.getString("DomesticHotel");
                String addressFromQ = washingtonHotelResult.getString("DomesticHotelAddress");
                String phoneFromQ = washingtonHotelResult.getString("DomesticHotelPhone");
                hotelSummaryEquals(hotelFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException hotE3) {
            hotE3.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException hotE4) {
            hotE4.printStackTrace();
        }


    }


// Tokyo


    private void TokyoHotelActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        createConnection();
        try {
            Statement tokyoHotelButton = connectionTRA.createStatement();
            ResultSet tokyoHotelResult = tokyoHotelButton.executeQuery("SELECT * FROM International_Hotel WHERE InternationalHotelID = 1");
            while (tokyoHotelResult.next()) {
                String hotelFromQ = tokyoHotelResult.getString("InternationalHotel");
                String addressFromQ = tokyoHotelResult.getString("InternationalHotelAddress");
                String phoneFromQ = tokyoHotelResult.getString("InternationalHotelPhone");
                hotelSummaryEquals(hotelFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException hotE5) {
            hotE5.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException hotE6) {
            hotE6.printStackTrace();
        }


    }


// Dublin


    private void DublinHotelActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        createConnection();
        try {
            Statement dublinHotelButton = connectionTRA.createStatement();
            ResultSet dublinHotelResult = dublinHotelButton.executeQuery("SELECT * FROM International_Hotel WHERE InternationalHotelID = 2");
            while (dublinHotelResult.next()) {
                String hotelFromQ = dublinHotelResult.getString("InternationalHotel");
                String addressFromQ = dublinHotelResult.getString("InternationalHotelAddress");
                String phoneFromQ = dublinHotelResult.getString("InternationalHotelPhone");
                hotelSummaryEquals(hotelFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException hotE7) {
            hotE7.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException hotE8) {
            hotE8.printStackTrace();
        }
    }


// Car Rental Selections


// Boston


    private void BostonCarRentalActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        createConnection();
        try {
            Statement bostonCarButton = connectionTRA.createStatement();
            ResultSet bostonCarResult = bostonCarButton.executeQuery("SELECT * FROM Domestic_Car WHERE DomesticCarID = 1");
            while (bostonCarResult.next()) {
                String carFromQ = bostonCarResult.getString("DomesticCar");
                String addressFromQ = bostonCarResult.getString("DomesticCarAddress");
                String phoneFromQ = bostonCarResult.getString("DomesticCarPhone");
                carRentalSummaryEquals(carFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException carE1) {
            carE1.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException carE2) {
            carE2.printStackTrace();
        }
    }


// Washington DC


    private void WashingtonCarRentalActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        createConnection();
        try {
            Statement WashingtonCarButton = connectionTRA.createStatement();
            ResultSet WashingtonCarResult = WashingtonCarButton.executeQuery("SELECT * FROM Domestic_Car WHERE DomesticCarID = 2");
            while (WashingtonCarResult.next()) {
                String carFromQ = WashingtonCarResult.getString("DomesticCar");
                String addressFromQ = WashingtonCarResult.getString("DomesticCarAddress");
                String phoneFromQ = WashingtonCarResult.getString("DomesticCarPhone");
                carRentalSummaryEquals(carFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException carE3) {
            carE3.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException carE4) {
            carE4.printStackTrace();
        }

    }


// Tokyo


    private void TokyoCarRentalActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        createConnection();
        try {
            Statement TokyoCarButton = connectionTRA.createStatement();
            ResultSet TokyoCarResult = TokyoCarButton.executeQuery("SELECT * FROM International_Car WHERE InternationalCarID = 1");
            while (TokyoCarResult.next()) {
                String carFromQ = TokyoCarResult.getString("InternationalCar");
                String addressFromQ = TokyoCarResult.getString("InternationalCarAddress");
                String phoneFromQ = TokyoCarResult.getString("InternationalCarPhone");
                carRentalSummaryEquals(carFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException carE5) {
            carE5.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException carE6) {
            carE6.printStackTrace();
        }

    }


// Dublin


    private void DublinCarRentalActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
        createConnection();
        try {
            Statement DublinCarButton = connectionTRA.createStatement();
            ResultSet DublinCarResult = DublinCarButton.executeQuery("SELECT * FROM International_Car WHERE InternationalCarID = 2");
            while (DublinCarResult.next()) {
                String carFromQ = DublinCarResult.getString("InternationalCar");
                String addressFromQ = DublinCarResult.getString("InternationalCarAddress");
                String phoneFromQ = DublinCarResult.getString("InternationalCarPhone");
                carRentalSummaryEquals(carFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException carE7) {
            carE7.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException carE8) {
            carE8.printStackTrace();
        }

    }


// EXCURSION SECTION

    private void FoodieExcursionActionPerformed(ActionEvent evt) {
        createConnection();
        try {
            Statement FoodButton = connectionTRA.createStatement();
            ResultSet FoodResult = null;
            if (DomesticDropdown.getSelectedItem() == "Boston (United Airlines)") {
                FoodResult = FoodButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 1");
            } else if (DomesticDropdown.getSelectedItem() == "D.C. (American Airlines)") {
                FoodResult = FoodButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 2");
            } else if (InternationalDropdown.getSelectedItem() == "Tokyo (Japan Airlines)") {
                FoodResult = FoodButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 3");
            } else if (InternationalDropdown.getSelectedItem() == "Dublin (Aer Lingus)") {
                FoodResult = FoodButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 4");
            }
            while (FoodResult.next()) {
                String excursionFromQ = FoodResult.getString("Excursion");
                String addressFromQ = FoodResult.getString("ExcursionAddress");
                String phoneFromQ = FoodResult.getString("ExcursionPhone");
                excursionSummaryEquals(excursionFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException carE7) {
            carE7.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException carE8) {
            carE8.printStackTrace();
        }

    }

    private void NatureExcursionActionPerformed(ActionEvent evt) {
        createConnection();
        try {
            Statement NutureButton = connectionTRA.createStatement();
            ResultSet NatureRusult = null;
            if (DomesticDropdown.getSelectedItem() == "Boston (United Airlines)") {
                NatureRusult = NutureButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 5");
            } else if (DomesticDropdown.getSelectedItem() == "D.C. (American Airlines)") {
                NatureRusult = NutureButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 6");
            } else if (InternationalDropdown.getSelectedItem() == "Tokyo (Japan Airlines)") {
                NatureRusult = NutureButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 7");
            } else if (InternationalDropdown.getSelectedItem() == "Dublin (Aer Lingus)") {
                NatureRusult = NutureButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 8");
            }
            while (NatureRusult.next()) {
                String excursionFromQ = NatureRusult.getString("Excursion");
                String addressFromQ = NatureRusult.getString("ExcursionAddress");
                String phoneFromQ = NatureRusult.getString("ExcursionPhone");
                excursionSummaryEquals(excursionFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException carE7) {
            carE7.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException carE8) {
            carE8.printStackTrace();
        }
    }

    private void SpaExcursionActionPerformed(ActionEvent evt) {
        createConnection();
        try {
            Statement SpaButton = connectionTRA.createStatement();
            ResultSet SpaResult = null;
            if (DomesticDropdown.getSelectedItem() == "Boston (United Airlines)") {
                SpaResult = SpaButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 9");
            } else if (DomesticDropdown.getSelectedItem() == "D.C. (American Airlines)") {
                SpaResult = SpaButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 10");
            } else if (InternationalDropdown.getSelectedItem() == "Tokyo (Japan Airlines)") {
                SpaResult = SpaButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 11");
            } else if (InternationalDropdown.getSelectedItem() == "Dublin (Aer Lingus)") {
                SpaResult = SpaButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 12");
            }
            while (SpaResult.next()) {
                String excursionFromQ = SpaResult.getString("Excursion");
                String addressFromQ = SpaResult.getString("ExcursionAddress");
                String phoneFromQ = SpaResult.getString("ExcursionPhone");
                excursionSummaryEquals(excursionFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException carE7) {
            carE7.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException carE8) {
            carE8.printStackTrace();
        }

    }

    private void HistoryExcursionActionPerformed(ActionEvent evt) {
        createConnection();
        try {
            Statement HistoryButton = connectionTRA.createStatement();
            ResultSet HistoryResult = null;
            if (DomesticDropdown.getSelectedItem() == "Boston (United Airlines)") {
                HistoryResult = HistoryButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 13");
            } else if (DomesticDropdown.getSelectedItem() == "D.C. (American Airlines)") {
                HistoryResult = HistoryButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 14");
            } else if (InternationalDropdown.getSelectedItem() == "Tokyo (Japan Airlines)") {
                HistoryResult = HistoryButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 15");
            } else if (InternationalDropdown.getSelectedItem() == "Dublin (Aer Lingus)") {
                HistoryResult = HistoryButton.executeQuery("SELECT * FROM Excursion_Table WHERE ExcursionID = 16");
            }
            while (HistoryResult.next()) {
                String excursionFromQ = HistoryResult.getString("Excursion");
                String addressFromQ = HistoryResult.getString("ExcursionAddress");
                String phoneFromQ = HistoryResult.getString("ExcursionPhone");
                excursionSummaryEquals(excursionFromQ, addressFromQ, phoneFromQ);
            }
        } catch (SQLException carE7) {
            carE7.printStackTrace();
        }
        try {
            connectionTRA.close();
        } catch (SQLException carE8) {
            carE8.printStackTrace();
        }

    }


// create connection

    private void createConnection() {

        try {
            String driver = "org.apache.derby.jdbc.EmbeddedDriver";
            Class.forName(driver).newInstance();
            connectionTRA = DriverManager.getConnection(dbURL);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e2) {
            System.out.printf("error");
            e2.printStackTrace();
        }

    }


//////////////


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new GridBagLayout());
        MainPanel.setBackground(new Color(-2171407));
        MainPanel.setFocusTraversalPolicyProvider(true);
        MainPanel.setFocusable(false);
        MainPanel.setMinimumSize(new Dimension(90, 100));
        MainPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        SelectionTabs = new JTabbedPane();
        SelectionTabs.setBackground(new Color(-8817257));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        MainPanel.add(SelectionTabs, gbc);
        SelectionTabs.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        DestinationsTab = new JPanel();
        DestinationsTab.setLayout(new GridBagLayout());
        DestinationsTab.setBackground(new Color(-2171407));
        SelectionTabs.addTab("Destinations", DestinationsTab);
        DestinationsTab.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        DomesticDropdown = new JComboBox();
        DomesticDropdown.setBackground(new Color(-8817257));
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Select Destination");
        defaultComboBoxModel1.addElement("Boston (United Airlines)");
        defaultComboBoxModel1.addElement("D.C. (American Airlines)");
        DomesticDropdown.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DestinationsTab.add(DomesticDropdown, gbc);
        DomesticTitle = new JLabel();
        DomesticTitle.setText("Domestic Destinations");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        DestinationsTab.add(DomesticTitle, gbc);
        InternationalTitle = new JLabel();
        InternationalTitle.setText("International Destinations");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        DestinationsTab.add(InternationalTitle, gbc);
        USA = new JLabel();
        USA.setIcon(new ImageIcon(getClass().getResource("/USAicon.png")));
        USA.setInheritsPopupMenu(false);
        USA.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        DestinationsTab.add(USA, gbc);
        Overseas = new JLabel();
        Overseas.setIcon(new ImageIcon(getClass().getResource("/Earthicon.png")));
        Overseas.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        DestinationsTab.add(Overseas, gbc);
        InternationalDropdown = new JComboBox();
        InternationalDropdown.setBackground(new Color(-8817257));
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Select Destination");
        defaultComboBoxModel2.addElement("Tokyo (Japan Airlines)");
        defaultComboBoxModel2.addElement("Dublin (Aer Lingus)");
        InternationalDropdown.setModel(defaultComboBoxModel2);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DestinationsTab.add(InternationalDropdown, gbc);
        DestinationSubmitButton = new JButton();
        DestinationSubmitButton.setBackground(new Color(-8817257));
        DestinationSubmitButton.setText("Submit");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 6;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        DestinationsTab.add(DestinationSubmitButton, gbc);
        HotelTab = new JPanel();
        HotelTab.setLayout(new GridBagLayout());
        HotelTab.setBackground(new Color(-2171407));
        SelectionTabs.addTab("Hotel", HotelTab);
        HotelTab.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        HotelBoston = new JPanel();
        HotelBoston.setLayout(new GridBagLayout());
        HotelBoston.setBackground(new Color(-2171407));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        HotelTab.add(HotelBoston, gbc);
        HotelBoston.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Hotel Commonwealth", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        BostonHotel = new JLabel();
        BostonHotel.setHorizontalAlignment(0);
        BostonHotel.setHorizontalTextPosition(0);
        BostonHotel.setIcon(new ImageIcon(getClass().getResource("/HotelCommonwealth.jpg")));
        BostonHotel.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        HotelBoston.add(BostonHotel, gbc);
        SelectBoston = new JButton();
        SelectBoston.setLabel("Select");
        SelectBoston.setText("Select");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        HotelBoston.add(SelectBoston, gbc);
        HotelDC = new JPanel();
        HotelDC.setLayout(new GridBagLayout());
        HotelDC.setBackground(new Color(-2171407));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        HotelTab.add(HotelDC, gbc);
        HotelDC.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Trump International", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, null));
        TrumpInt = new JLabel();
        TrumpInt.setIcon(new ImageIcon(getClass().getResource("/DCHotel.png")));
        TrumpInt.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        HotelDC.add(TrumpInt, gbc);
        SelectDC = new JButton();
        SelectDC.setText("Select");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        HotelDC.add(SelectDC, gbc);
        HotelDublin = new JPanel();
        HotelDublin.setLayout(new GridBagLayout());
        HotelDublin.setBackground(new Color(-2171407));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        HotelTab.add(HotelDublin, gbc);
        HotelDublin.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "The Westbury", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, null, null));
        Westbury = new JLabel();
        Westbury.setIcon(new ImageIcon(getClass().getResource("/HotelDublin.jpg")));
        Westbury.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        HotelDublin.add(Westbury, gbc);
        SelectDublin = new JButton();
        SelectDublin.setText("Select");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        HotelDublin.add(SelectDublin, gbc);
        HotelTokyo = new JPanel();
        HotelTokyo.setLayout(new GridBagLayout());
        HotelTokyo.setBackground(new Color(-2171407));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        HotelTab.add(HotelTokyo, gbc);
        HotelTokyo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Aman Tokyo", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        AmanTokyo = new JLabel();
        AmanTokyo.setIcon(new ImageIcon(getClass().getResource("/HotelTokyo.jpg")));
        AmanTokyo.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        HotelTokyo.add(AmanTokyo, gbc);
        SelectTokyo = new JButton();
        SelectTokyo.setText("Select");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        HotelTokyo.add(SelectTokyo, gbc);
        RentalTab = new JPanel();
        RentalTab.setLayout(new GridBagLayout());
        RentalTab.setBackground(new Color(-2171407));
        SelectionTabs.addTab("Car Rental", RentalTab);
        CarBoston = new JLabel();
        CarBoston.setIcon(new ImageIcon(getClass().getResource("/BostonCar.png")));
        CarBoston.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        RentalTab.add(CarBoston, gbc);
        CarDC = new JLabel();
        CarDC.setIcon(new ImageIcon(getClass().getResource("/DCcar.jpg")));
        CarDC.setInheritsPopupMenu(true);
        CarDC.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        RentalTab.add(CarDC, gbc);
        CarTokyo = new JLabel();
        CarTokyo.setIcon(new ImageIcon(getClass().getResource("/TokyoCar.jpg")));
        CarTokyo.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        RentalTab.add(CarTokyo, gbc);
        CarDublin = new JLabel();
        CarDublin.setIcon(new ImageIcon(getClass().getResource("/DublinCar.jpg")));
        CarDublin.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        RentalTab.add(CarDublin, gbc);
        hertzDCButton = new JButton();
        hertzDCButton.setText("Hertz D.C.");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        RentalTab.add(hertzDCButton, gbc);
        hertzTokyoButton = new JButton();
        hertzTokyoButton.setText("Hertz Tokyo");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        RentalTab.add(hertzTokyoButton, gbc);
        hertzDublinButton = new JButton();
        hertzDublinButton.setText("Hertz Dublin");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        RentalTab.add(hertzDublinButton, gbc);
        hertzBostonButton = new JButton();
        hertzBostonButton.setText("Hertz Boston");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        RentalTab.add(hertzBostonButton, gbc);
        ExcursionsTab = new JPanel();
        ExcursionsTab.setLayout(new GridBagLayout());
        ExcursionsTab.setBackground(new Color(-2171407));
        SelectionTabs.addTab("Excursions", ExcursionsTab);
        ActivityFoodie = new JPanel();
        ActivityFoodie.setLayout(new GridBagLayout());
        ActivityFoodie.setBackground(new Color(-2171407));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        ExcursionsTab.add(ActivityFoodie, gbc);
        ActivityFoodie.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Foodie Package", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, null, null));
        FoodieButton = new JButton();
        FoodieButton.setText("Select");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ActivityFoodie.add(FoodieButton, gbc);
        FoodiePic = new JLabel();
        FoodiePic.setIcon(new ImageIcon(getClass().getResource("/FOODIE.png")));
        FoodiePic.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        ActivityFoodie.add(FoodiePic, gbc);
        ActivityHistory = new JPanel();
        ActivityHistory.setLayout(new GridBagLayout());
        ActivityHistory.setBackground(new Color(-2171407));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 11;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        ExcursionsTab.add(ActivityHistory, gbc);
        ActivityHistory.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "History Package", TitledBorder.LEFT, TitledBorder.TOP, this.$$$getFont$$$(null, -1, -1, ActivityHistory.getFont()), null));
        HistoryButton = new JButton();
        HistoryButton.setText("Select");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ActivityHistory.add(HistoryButton, gbc);
        HistoryPic = new JLabel();
        HistoryPic.setIcon(new ImageIcon(getClass().getResource("/history.png")));
        HistoryPic.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        ActivityHistory.add(HistoryPic, gbc);
        ActivitySpa = new JPanel();
        ActivitySpa.setLayout(new GridBagLayout());
        ActivitySpa.setBackground(new Color(-2171407));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 11;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        ExcursionsTab.add(ActivitySpa, gbc);
        ActivitySpa.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Spa Package", TitledBorder.LEFT, TitledBorder.TOP, null, null));
        SpaButton = new JButton();
        SpaButton.setText("Select");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ActivitySpa.add(SpaButton, gbc);
        SpaPic = new JLabel();
        SpaPic.setIcon(new ImageIcon(getClass().getResource("/Spa.png")));
        SpaPic.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        ActivitySpa.add(SpaPic, gbc);
        ActivityNature = new JPanel();
        ActivityNature.setLayout(new GridBagLayout());
        ActivityNature.setBackground(new Color(-2171407));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        ExcursionsTab.add(ActivityNature, gbc);
        ActivityNature.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Nature Package", TitledBorder.LEFT, TitledBorder.TOP, null, null));
        NatureButton = new JButton();
        NatureButton.setText("Select");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        ActivityNature.add(NatureButton, gbc);
        NaturePic = new JLabel();
        NaturePic.setIcon(new ImageIcon(getClass().getResource("/nature.jpg")));
        NaturePic.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        ActivityNature.add(NaturePic, gbc);
        SummaryTab = new JPanel();
        SummaryTab.setLayout(new GridBagLayout());
        SummaryTab.setBackground(new Color(-2171407));
        SelectionTabs.addTab("Selection Summary", SummaryTab);
        SummaryDestinationField = new JTextArea();
        SummaryDestinationField.setBackground(new Color(-2171407));
        SummaryDestinationField.setOpaque(true);
        SummaryDestinationField.setPreferredSize(new Dimension(500, 100));
        SummaryDestinationField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        SummaryTab.add(SummaryDestinationField, gbc);
        SummaryHotelField = new JTextArea();
        SummaryHotelField.setBackground(new Color(-2171407));
        SummaryHotelField.setPreferredSize(new Dimension(500, 100));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        SummaryTab.add(SummaryHotelField, gbc);
        SummaryCarField = new JTextArea();
        SummaryCarField.setBackground(new Color(-2171407));
        SummaryCarField.setPreferredSize(new Dimension(500, 100));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        SummaryTab.add(SummaryCarField, gbc);
        SummaryExcursionsField = new JTextArea();
        SummaryExcursionsField.setBackground(new Color(-2171407));
        SummaryExcursionsField.setPreferredSize(new Dimension(500, 100));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        SummaryTab.add(SummaryExcursionsField, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MainPanel;
    }

    public void runThisThing() {
        JFrame FrameOne = new JFrame("Welcome to the most AWESOME travel planner!");
        FrameOne.setDefaultCloseOperation(EXIT_ON_CLOSE);
        FrameOne.setSize(698, 515);
        FrameOne.setLocationRelativeTo(null);
        FrameOne.setResizable(false);
        GUIJframeTotal gui = new GUIJframeTotal();
        FrameOne.add(gui.MainPanel);
        FrameOne.setVisible(true);
    }

    public void main(String[] args) {
        runThisThing();
    }
}






