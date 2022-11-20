package model;

import com.sun.jdi.request.StepRequest;
import helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelRoomType {
    private  int id;
    private int hotelId;
    private String roomType;
    private int stock;
    private int price;

    public HotelRoomType(){

    }
    public HotelRoomType(int id, int hotelId, String roomType,int stock, int price){
        this.id = id;
        this.hotelId = hotelId;
        this.roomType = roomType;
        this.stock = stock;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public boolean addHotelRoomType(int hotelId,String roomType, int stock, int price){
        String query= "INSERT INTO public.hotel_room_type (hotel_id,room_type,stock,price) VALUES (?,?,?,?)";
        boolean isHotelRoomTypeAdded;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setString(2,roomType);
            pr.setInt(3,stock);
            pr.setInt(4,price);
            isHotelRoomTypeAdded = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  isHotelRoomTypeAdded;
    }
    public HotelRoomType getFetch(int hotelId,String roomType){
        String query = "SELECT * FROM public.hotel_room_type WHERE hotel_id =? AND room_type =?" ;
        HotelRoomType hotelRoomType = null;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setString(2,roomType);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                String ROOM_TYPE = rs.getString("room_type");
                int STOCK = rs.getInt("stock");
                int PRICE = rs.getInt("price");
                hotelRoomType = new HotelRoomType(ID,HOTEL_ID,ROOM_TYPE,STOCK,PRICE);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelRoomType;
    }
    public HotelRoomType getFetch(int id){
        String query = "SELECT * FROM public.hotel_room_type WHERE id =?" ;
        HotelRoomType hotelRoomType = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                String ROOM_TYPE = rs.getString("room_type");
                int STOCK = rs.getInt("stock");
                int PRICE = rs.getInt("price");
                hotelRoomType = new HotelRoomType(ID,HOTEL_ID,ROOM_TYPE,STOCK,PRICE);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelRoomType;
    }
    public boolean deleteHotelRoomType(int id){
        String query = "DELETE FROM public.hotel_room_type WHERE id =?";
        boolean isHotelRoomTypeDeleted;

        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            isHotelRoomTypeDeleted = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  isHotelRoomTypeDeleted;
    }
    public boolean updateHotelRoomType(int id,int hotelId, String roomType,int stock, int price ){
        String query ="UPDATE public.hotel_room_type SET (id,hotel_id,room_type,stock,price) VALUES (?,?,?,?,?)";
        boolean isHotelRoomTypeUpdated;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            pr.setInt(2,hotelId);
            pr.setString(3,roomType);
            pr.setInt(4,stock);
            pr.setInt(5,price);
            isHotelRoomTypeUpdated = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isHotelRoomTypeUpdated;
    }
    public boolean deleteALlHotelRoomType(int hotelId){
        String query = "DELETE FROM public.hotel_room_type WHERE hotel_id =?";
        boolean isAllHotelRoomTypeDeleted;

        try {
            PreparedStatement pr =DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            isAllHotelRoomTypeDeleted = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  isAllHotelRoomTypeDeleted;
    }

    public ArrayList<HotelRoomType> getList(int hotelId){
        String query =" SELECT * FROM public.hotel_room_type WHERE hotel_id =?";
        ArrayList<HotelRoomType> hotelRoomTypes = new ArrayList<>();
        HotelRoomType hotelRoomType = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                String ROOM_TYPE = rs.getString("room_type");
                int STOCK = rs.getInt("stock");
                int PRICE = rs.getInt("price");
                hotelRoomType = new HotelRoomType(ID,HOTEL_ID,ROOM_TYPE,STOCK,PRICE);
                hotelRoomTypes.add(hotelRoomType);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelRoomTypes;
    }
}
