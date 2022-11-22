package model;

import helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelFacility {
    int id;
    int hotelId;
    String facilityName;

    public HotelFacility(){
    }
    public HotelFacility(int id,int hotelId, String facilityName){
        this.id = id;
        this.hotelId =hotelId;
        this.facilityName=facilityName;
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

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }
    public boolean addFacility(int hotelId, String facilityName){
        String query = "INSERT INTO public.hotel_facility (hotel_id,facility_name) VALUES (?,?)";
        boolean isAdded;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setString(1,facilityName);
            isAdded = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdded;
    }

    public boolean deleteFacility(int id){
        String query ="DELETE FROM public.hotel_facility WHERE id =?";
        boolean isDeleted;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            isDeleted = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isDeleted;
    }

    public boolean updateFacility(int id,String facilityName){
        String query = "UPDATE public.hotel_facility SET (id,facility_name) VALUES (?,?)";
        boolean isUpdated;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            pr.setString(2,facilityName);
            isUpdated = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isUpdated;
    }
    public HotelFacility getFetch (int hotelId,String facilityName){
        String query ="SELECT * FROM public.hotel_facility WHERE hotel_id =? AND facility_name =?";
        HotelFacility facility = null;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setString(2,facilityName);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                String FACILITY_NAME = rs.getString("facility_name");
                facility = new HotelFacility(ID,HOTEL_ID,FACILITY_NAME);
            }
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return facility;
    }
    public HotelFacility getFetch (int id){
        String query ="SELECT * FROM public.hotel_facility WHERE id =? ";
        HotelFacility facility = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                String FACILITY_NAME = rs.getString("facility_name");
                facility = new HotelFacility(ID,HOTEL_ID,FACILITY_NAME);
            }
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return facility;
    }
    public boolean deleteAllFAcilities(int hotelId){
        String query = "DELETE FROM public.hotel_facility WHERE hotel_id =?";
        boolean isAllDeleted;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            isAllDeleted = pr.executeUpdate()!=-1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAllDeleted;
    }

    public ArrayList<HotelFacility> getList(int hotelId){
        String query="SELECT * FROM public.hotel_facility WHERE hotel_id =?";
        ArrayList<HotelFacility> facilities = new ArrayList<>();
        HotelFacility facility = null;
        try {
            PreparedStatement statement = DBConnector.getInstance().prepareStatement(query);
            statement.setInt(1,hotelId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                String FACILITY_NAME = rs.getString("facility_name");
                facility = new HotelFacility(ID,HOTEL_ID,FACILITY_NAME);
                facilities.add(facility);
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return facilities;
    }
}
