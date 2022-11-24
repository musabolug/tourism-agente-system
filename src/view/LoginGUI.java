package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import helper.*;
import model.*;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JLabel login_header;
    private JTextField fld_lgn_username;
    private JTextField fld_lgn_password;
    private JButton btn_login;
    private DefaultTableModel mdl_hotel_list;

    public LoginGUI(){
    add(wrapper);
    setSize(400,400);
    setLocation(Help.screenCenterPoint("x",getSize()),Help.screenCenterPoint("y",getSize()));
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle(Config.PROJECT_TITLE);
    setVisible(true);



        btn_login.addActionListener(e -> {
            if (Help.isFieldEmpty(fld_lgn_username)|| Help.isFieldEmpty(fld_lgn_password)){
            Help.showMessage("fill");
            }else{
                User u = User.getFetch(fld_lgn_username.getText(),fld_lgn_password.getText());
                if (u != null){

                /*switch (u.getRole()){
                    case 1:
                       // ManagerGUI managerGUI = new ManagerGUI();
                        break;
                    case 2:
                       // OperatorGUI operatorGUI = new OperatorGUI();
                        break;
                }*/
                }else {
                    Help.showMessage("Kullanıcı bulunamadı !");
                }
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        Help.setLayout();
        LoginGUI loginGUI = new LoginGUI();
    }
}

//2483 satır controller + helper + model
