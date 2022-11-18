package model;

import helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    int id;
    String name;
    String username;
    String password;
    int role;
    public User(String username,String password){

    }
    public User(int id, String name,String username,String password, int role){
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    public User getFetch(int userId){
        String query = "SELECT * FROM public.user WHERE id = ?";
        User user = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,userId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                int ID = rs.getInt("id");
                String NAME = rs.getString("name");
                String USERNAME = rs.getString("username");
                String PASSWORD = rs.getString("password");
                int ROLE = rs.getInt("role");
                user = new User(ID,NAME,USERNAME,PASSWORD,ROLE);
            }
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User getFetch(String username,String password){
        String query = "SELECT * FROM public.user WHERE username = ? AND password = ?";
        User user = null;

        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                int ID = resultSet.getInt("id");
                String NAME = resultSet.getString("name");
                String USERNAME = resultSet.getString("username");
                String PASSWORD = resultSet.getString("password");
                int ROLE = resultSet.getInt("role");
                user = new User(ID,NAME,USERNAME,PASSWORD,ROLE);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
    public ArrayList<User> getList(){
        String query = "SELECT * FROM public.user";
        ArrayList<User> userList = new ArrayList<>();
        User user = null;
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int ID = resultSet.getInt("id");
                String NAME = resultSet.getString("name");
                String USERNAME = resultSet.getString("username");
                String PASSWORD = resultSet.getString("password");
                int ROLE = resultSet.getInt("role");
                user = new User(ID,NAME,USERNAME,PASSWORD,ROLE);
                userList.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
    public ArrayList<User> getListByRole (int role){
        String query = "SELECT * FROM public.user WHERE role = ?";
        ArrayList<User> userListByRole = new ArrayList<>();
        User user = null;

        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int ID = resultSet.getInt("id");
                String NAME = resultSet.getString("name");
                String USERNAME = resultSet.getString("username");
                String PASSWORD = resultSet.getString("password");
                int ROLE = resultSet.getInt("role");
                user = new User(ID,NAME,USERNAME,PASSWORD,ROLE);
                userListByRole.add(user);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return userListByRole;
    }

    public boolean add (String name ,String username, String password, int role){
        String query ="INSERT INTO public.user (name,username,password,role) VALUES (?,?,?,?)";
        boolean isAdd;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,username);
            pr.setString(3,password);
            pr.setInt(4,role);

            isAdd = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdd;
    }
    public boolean delete(int userId){
        String query = "DELETE FROM public.user WHERE id = ?";
        boolean isDelete;

        try {
            PreparedStatement preparedStatement = DBConnector.getInstance().prepareStatement(query);
            preparedStatement.setInt(1,userId);
            isDelete = preparedStatement.executeUpdate() != -1;
            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isDelete;
    }

    public boolean update(int id, String name,String username,String password,int role){
        String query ="UPDATE public.user SET name = ? , username = ?, password =?, role = ? WHERE id =?";
        boolean isUpdate;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,username);
            pr.setString(3,password);
            pr.setInt(4,role);
            pr.setInt(5,id);
            isUpdate = pr.executeUpdate() != -1;
            pr.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isUpdate;
    }
}
