package model;

import helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Reservation {
    private int id;
    private int hotel_id;
    private int room_id;
    private int hostel_id;


    public Reservation (){

    }
    public Reservation(int id ,int hotel_id,int room_id,int hostel_id){
        this.id = id;
        this.hotel_id= hotel_id;
        this.room_id= room_id;
        this.hostel_id = hostel_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getHostel_id() {
        return hostel_id;
    }

    public void setHostel_id(int hostel_id) {
        this.hostel_id = hostel_id;
    }
    public boolean addReservation(int hotel_id, int room_id,int hostel_id){
        String query = "INSERT INTO public.reservation (hotel_id,room_id,hostel_id) VALUES (?,?,?)";
        boolean isReservationAdded ;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotel_id);
            pr.setInt(2,room_id);
            pr.setInt(3,hostel_id);
            isReservationAdded = pr.executeUpdate() !=-1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isReservationAdded;
    }
    public boolean deleteReservation(int id){
        String query ="DELETE FROM public.reservation WHERE id =?";
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
    public int getLastReservationId(){
        String query ="SELECT id FROM public.reservation ORDER BY DESC LIMIT 1";
        int lastReservationId = -1;
        try {
            Statement statement = DBConnector.getInstance().createStatement();
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()){
                lastReservationId = rs.getInt("id");
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  lastReservationId;
    }
    public Reservation getFetch(int id){
        String query = "SELECT * FROM public.reservation WHERE id =?";
        Reservation reservation = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                int ROOM_ID =rs.getInt("room_id");
                int HOSTEL_ID = rs.getInt("hostel_id");
                reservation = new Reservation(ID,HOTEL_ID,ROOM_ID,HOSTEL_ID);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservation;
    }
}
