package model;

import helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelFacility {

    int hotelId;
    boolean freeParking,freeWifi,swimmingPool,fitness,SPA,roomService;

    public HotelFacility(){
    }
    public HotelFacility(int hotelId,boolean freeParking,boolean freeWifi,boolean swimmingPool,boolean fitness,boolean SPA, boolean roomService){
        this.hotelId = hotelId;
        this.freeParking = freeParking;
        this.freeWifi = freeWifi;
        this.swimmingPool = swimmingPool;
        this.fitness = fitness;
        this.SPA = SPA;
        this.roomService = roomService;
    }

    public boolean addFacility(int hotelId,boolean freeParking,boolean freeWifi,boolean swimmingPool,boolean fitness,boolean SPA, boolean roomService){
        String query = "INSERT INTO public.hotel_facility (hotel_id,free_parking,free_wifi,swimming_pool,fitness,spa,room_service) VALUES (?,?,?,?,?,?,?)";
        boolean isAdded;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setBoolean(2,freeParking);
            pr.setBoolean(2,freeWifi);
            pr.setBoolean(2,swimmingPool);
            pr.setBoolean(2,fitness);
            pr.setBoolean(2,SPA);
            pr.setBoolean(2,roomService);
            isAdded = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdded;
    }

    public boolean deleteFacility(int id){
        String query ="DELETE FROM public.hotel_facility WHERE hotel_id =?";
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

    public boolean updateFacility(int hotelId,boolean freeParking,boolean freeWifi,boolean swimmingPool,boolean fitness,boolean SPA, boolean roomService){
        String query = "UPDATE public.hotel_facility SET (hotel_id,free_parking,free_wifi,swimming_pool,fitness,spa,room_service) VALUES (?,?,?,?,?,?,?)";
        boolean isUpdated;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setBoolean(2,freeParking);
            pr.setBoolean(2,freeWifi);
            pr.setBoolean(2,swimmingPool);
            pr.setBoolean(2,fitness);
            pr.setBoolean(2,SPA);
            pr.setBoolean(2,roomService);
            isUpdated = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isUpdated;
    }
  /*  public HotelFacility getFetch (int hotelId,String facilityName){
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
        }*
    return facility;
    }*/
    public HotelFacility getFetch (int id){
        String query ="SELECT * FROM public.hotel_facility WHERE hotel_id =? ";
        HotelFacility facility = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int HOTEL_ID = rs.getInt("hotel_id");
                boolean FREE_PARKING = rs.getBoolean("free_parking");
                boolean FREE_WIFI = rs.getBoolean("free_wifi");
                boolean SWIMMING_POOL = rs.getBoolean("swimming_pool");
                boolean FITNESS = rs.getBoolean("fitness");
                boolean SPA = rs.getBoolean("spa");
                boolean ROOM_SERVICE = rs.getBoolean("room_service");
                facility = new HotelFacility(HOTEL_ID,FREE_PARKING,FREE_WIFI,SWIMMING_POOL,FITNESS,SPA,ROOM_SERVICE);
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
                int HOTEL_ID = rs.getInt("hotel_id");
                boolean FREE_PARKING = rs.getBoolean("free_parking");
                boolean FREE_WIFI = rs.getBoolean("free_wifi");
                boolean SWIMMING_POOL = rs.getBoolean("swimming_pool");
                boolean FITNESS = rs.getBoolean("fitness");
                boolean SPA = rs.getBoolean("spa");
                boolean ROOM_SERVICE = rs.getBoolean("room_service");
                facility = new HotelFacility(HOTEL_ID,FREE_PARKING,FREE_WIFI,SWIMMING_POOL,FITNESS,SPA,ROOM_SERVICE);
                facilities.add(facility);
            }
            statement.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return facilities;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public boolean isFreeParking() {
        return freeParking;
    }

    public void setFreeParking(boolean freeParking) {
        this.freeParking = freeParking;
    }

    public boolean isFreeWifi() {
        return freeWifi;
    }

    public void setFreeWifi(boolean freeWifi) {
        this.freeWifi = freeWifi;
    }

    public boolean isSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public boolean isFitness() {
        return fitness;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    public boolean isSPA() {
        return SPA;
    }

    public void setSPA(boolean SPA) {
        this.SPA = SPA;
    }

    public boolean isRoomService() {
        return roomService;
    }

    public void setRoomService(boolean roomService) {
        this.roomService = roomService;
    }
}
