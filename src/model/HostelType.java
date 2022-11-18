package model;

import helper.DBConnector;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HostelType {
    private int id;
    private int hotelId;
    private  String hostelType;
    private int price;

    public HostelType(){

    }
    public HostelType(int id, int hotelId, String hostelType, int price){
        this.id = id;
        this.hotelId = hotelId;
        this.hostelType = hostelType;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHostelType() {
        return hostelType;
    }

    public void setHostelType(String hostelType) {
        this.hostelType = hostelType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public boolean addHostelType(int hotelId,String hostelType,int price){
        String query ="INSERT INTO public.hotel_hostel_type (hostel_id,hostel_type,price) VALUES (?,?,?)";
        boolean isAdd;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setString(2,hostelType);
            pr.setInt(3,price);
            isAdd = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdd;
    }
    public HostelType getFetchHostelType(int hotelId, String hostelType){
        String query = "SELECT * FROM public.hotel_hostel_type WHERE hostel_id = ? AND hostel_type = ?";
        HostelType hType = null;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setString(2,hostelType);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                String HOSTEL_TYPE = rs.getString("hostel_type");
                int PRICE = rs.getInt("price");
                hType = new HostelType(ID,HOTEL_ID,HOSTEL_TYPE,PRICE);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hType;
    }
    public HostelType getFetchHostelType(int hostelTypeId){
        String query = "SELECT * FROM public.hotel_hostel_type WHERE id = ?";
        HostelType hType = null;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hostelTypeId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                String HOSTEL_TYPE = rs.getString("hostel_type");
                int PRICE = rs.getInt("price");
                hType = new HostelType(ID,HOTEL_ID,HOSTEL_TYPE,PRICE);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hType;
    }
    public boolean updateHostelType(int id,String hostelType,int price){
        String query = "UPDATE public.hotel_hostel_type SET hostel_type = ?, price = ? WHERE id =?";
        boolean isupdate;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,hostelType);
            pr.setInt(2,price);
            pr.setInt(3,id);
            isupdate = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isupdate;
    }

    public boolean deleteHostelType(int hostelTypeId){
        String query = "DELETE FROM public hotel_hostel_type WHERE id =?";
        boolean isDelete;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hostelTypeId);
            isDelete = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isDelete;
    }

    public boolean deleteAllHostelType(int hotelId){
        String query = "DELETE FROM public.hotel_hostel_type WHERE hotel_id =?";
        boolean isDelete;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            isDelete = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isDelete;
    }
    public ArrayList<HostelType> getList(int hotelId){
    String query = "SELECT * FROM public.hotel_hostel_type WHERE hotel_id = ?";
    ArrayList<HostelType> hostelTypeList = new ArrayList<>();
    HostelType hostelType;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                String HOSTEL_TYPE = rs.getString("hostel_type");
                int PRICE = rs.getInt("price");
                hostelType = new HostelType(ID,HOTEL_ID,HOSTEL_TYPE,PRICE);
                hostelTypeList.add(hostelType);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hostelTypeList;
    }
}

