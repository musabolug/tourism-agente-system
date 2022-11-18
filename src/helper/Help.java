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
}
