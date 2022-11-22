package controller;

import helper.Role;
import model.User;

import java.security.PublicKey;
import java.util.ArrayList;

public class UserController {
    private User user;
    private HotelController hotelController;

    public UserController(User user){
        this.user = user;
        this.hotelController = new HotelController();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HotelController getHotelController() {
        return hotelController;
    }

    public void setHotelController(HotelController hotelController) {
        this.hotelController = hotelController;
    }
    public ArrayList<User> getAllUser(int role){
        if (role == Role.MANAGER.getRole()){
            return user.getList();
        } else if (role == Role.OPERATOR.getRole()) {
            return user.getList(role);
        }else{
            System.out.println("User not found");
            return null;
        }
    }
    public boolean addUser(String name,String username,String password,int role){
        if (user.getRole() == Role.MANAGER.getRole()){
            if (user.getFetch(username)== null){
                if (user.add(name,username,password,role)){
                    System.out.println("User added to system");
                    return true;
                }else{
                    System.out.println("An error occurred while adding");
                    return false;
                }
            }else{
                System.out.println("This user is already exist");
                return false;
            }
        }else{
            System.out.println("You don't have permission to add user");
            return false;
        }
    }
    public boolean deleteUser(int id){
        if (user.getRole() == Role.MANAGER.getRole()){
            if (user.getFetch(id) != null){
                if (user.delete(id)){
                    System.out.println("User deleted");
                    return true;
                }else{
                    System.out.println("An error occurred while deleting");
                    return false;
                }
            }else {
                System.out.println("This user doesn't exist");
                return false;
            }
        }else{
            System.out.println("You don't have permission to delete user");
            return false;
        }
    }
    public boolean updateUser(int id,String name, String username, String password, int role){
        if (user.getRole() == Role.MANAGER.getRole() || user.getId() == id){
            if (user.getFetch(id) != null){
                if (user.update(id,name,username,password,role)){
                    System.out.println("User updated successfully");
                    return true;
                }else{
                    System.out.println("An error occurred while updating");
                    return false;
                }
            }else{
                System.out.println("This user doesn't exist");
                return false;
            }
        }else{
            System.out.println("You don't have permission to update user");
            return false;
        }
    }
}
