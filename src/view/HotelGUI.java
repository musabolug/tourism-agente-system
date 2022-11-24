package view;

import helper.Config;
import helper.Help;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HotelGUI extends JFrame {

    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JPanel wleft;
    private JTextField fld_src_hotelplace;
    private JTextField fld_src_sdate;
    private JTextField fld_src_edate;
    private JTextField fld_src_adultnumber;
    private JTextField fld_src_kidnumber;
    private DefaultTableModel mdl_hotel_list;

    public HotelGUI(){
            Help.setLayout();
            add(wrapper);
            setSize(1000,600);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setTitle(Config.PROJECT_TITLE);
            setLocation(Help.screenCenterPoint("x",getSize()),Help.screenCenterPoint("y",getSize()));
            setVisible(true);

        mdl_hotel_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        Object[] hotelcol = {"ID","NAME","ADRESS","EMAIL","PHONE","STAR"};
        mdl_hotel_list.setColumnIdentifiers(hotelcol);

        }
}
