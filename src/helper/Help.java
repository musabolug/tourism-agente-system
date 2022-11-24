package helper;

import javax.swing.*;
import java.awt.*;

public class Help {
    public static int screenCenterPoint(String axis , Dimension size){
        return switch (axis){
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) /2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) /2;
            default -> 0;
        };
    }
    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if (info.getName().equals("Nimbus")){
                try {
                        UIManager.setLookAndFeel(info.getClassName());
                }catch (Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }
    public static void showMessage(String str){
        String msg;
        String title;

        switch (str){
            case"fill":
            msg = "Lütfen tüm alanları doldurunuz";
            title  ="Hata!";
            optionPaneTR();
            break;
            case "done":
                msg = "İşlem Başarılı !";
                title="Message";
                optionPaneTR();
                break;
            case "error":
                msg = "Bir Hata oluştu";
                title = "Hata !";
                optionPaneTR();
                break;
            default:
                msg = str;
                title= "Mesaj";
                optionPaneTR();
                break;
        }
            JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);
    }

        public static boolean confirm(String str){
        String msg;
        switch (str) {
            case "sure":
                msg = "Bu işlemi gerçekleştirmek istediğinizden emin misiniz ?";
                break;
            default:
                msg=str;
                break;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Son kararın mı ?",JOptionPane.YES_NO_OPTION)==0;
        }

    public static void optionPaneTR(){
        UIManager.put("OptionPane.okButtonText","tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");

    }
}
