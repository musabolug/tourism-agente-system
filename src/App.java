import helper.Role;
import model.User;

public class App {
    public static void main(String[] args) {
        String username = "";
        String password ="";
        int test = 2;
        for (int i = 0; i<test;i++){
            System.out.println("####################");
            if(i==0){
                System.out.println("MANAGER TEST");
                 username = "Test_user_name";
                 password = "1234";
            } else if (i == 1) {
                System.out.println("OPERATOR TEST");
                username = "test_operator";
                password= "321";
            }
            User user = new User(username,password);

            user = user.getFetch(username,password);
            if (user != null){
                System.out.println("Signed in account" + user.getName());
                if (user.getRole() == Role.MANAGER.getRole()){

                }
            }
        }
    }
}
