package model;

import helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Hotel {
    private int id;
    private String name;
    private String adress;
    private String mail;
    private String phone;
    private String star;

    public Hotel(){

    }
    public Hotel(int id,String name,String adress,String mail,String phone,String star){
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.mail = mail;
        this.phone = phone;
        this.star = star;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
    ArrayList<Hotel> getList(){
        String qery= "SELECT * FROM public.hotel";
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel hotel;
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet rs = statement.executeQuery(qery);
            while (rs.next()){
                int ID = rs.getInt("id");
                String NAME = rs.getString("name");
                String ADRESS = rs.getString("adress");
                String MAIL = rs.getString("mail");
                String PHONE = rs.getString("phone");
                String STAR = rs.getString("star");
                hotel = new Hotel(ID,NAME,ADRESS,MAIL,PHONE,STAR);
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }
    public Hotel getFetch(int id){
        String query = "SELECT * FROM public.hotel WHERE id = ?";
        Hotel hotel = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                String NAME = rs.getString("name");
                String ADRESS = rs.getString("adress");
                String MAIL = rs.getString("mail");
                String PHONE = rs.getString("phone");
                String STAR = rs.getString("star");
                hotel = new Hotel(ID,NAME,ADRESS,MAIL,PHONE,STAR);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotel;
    }
    public Hotel getFetch(String name,String adress ){
        String query = "SELECT * FROM public.hotel WHERE name = ? AND adress = ?";
        Hotel hotel = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,adress);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                String NAME = rs.getString("name");
                String ADRESS = rs.getString("adress");
                String MAIL = rs.getString("mail");
                String PHONE = rs.getString("phone");
                String STAR = rs.getString("star");
                hotel = new Hotel(ID,NAME,ADRESS,MAIL,PHONE,STAR);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotel;
    }
    public boolean add(String name, String adress, String mail, String phone,String star){
        String query = "INSERT INTO public.hotel (name,adress,mail,phone,star) VALUES (?,?,?,?,?)";
        boolean isAdded;
        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,adress);
            pr.setString(3,mail);
            pr.setString(3,phone);
            pr.setString(4,star);
            isAdded = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdded;
    }
    public boolean delete (int hotelId){
        String query ="DELETE FORM public.hotel WHERE id = ?";
        boolean isDeleted;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            isDeleted = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isDeleted;
    }

    public boolean update(int id, String name,String adress,String mail,String phone, String star ){
        String query = "UPDATE public.hotel SET (id,name,adress,mail,phone,star) VALUES (?,?,?,?,?,?)";
        boolean isUpdated;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            pr.setString(2,name);
            pr.setString(3,adress);
            pr.setString(4,mail);
            pr.setString(5,phone);
            pr.setString(6,star);
            isUpdated = pr.executeUpdate() !=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isUpdated;
    }
}
