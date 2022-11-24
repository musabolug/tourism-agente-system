package model;

import helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomFeature {
    private int id;
    private int roomId;
    private String feature;

    public RoomFeature(){

    }
    public RoomFeature(int id,int roomId,String feature){
        this.id = id;
        this.roomId = roomId;
        this.feature = feature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
    public boolean addFeature(int roomId,String feature){
        String query = "INSERT INTO public.room_feature (room_id,feature) VALUES (?,?)";
        boolean isFeatureAdded ;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,roomId);
            pr.setString(2,feature);
            isFeatureAdded = pr.executeUpdate() !=-1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  isFeatureAdded;
    }
    public boolean deleteFeature(int id){
        String query = "DELETE FORM public.room_feature WHERE id = ?";
        boolean isFeatureDeleted;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            isFeatureDeleted = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  isFeatureDeleted;
    }
    public boolean updateFeature(int id, int roomId,String feature){
        String query = "UPDATE public.room_feature SET (id,room_id,feature) VALUES (?,?,?)";
        boolean isFeatureUpdated;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            pr.setInt(2,roomId);
            pr.setString(3,feature);
            isFeatureUpdated = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isFeatureUpdated;
    }
    public boolean deleteAllFeature(int roomId){
        String query = "DELETE FORM public.room_feature WHERE room_id = ?";
        boolean isFeatureDeleted;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,roomId);
            isFeatureDeleted = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  isFeatureDeleted;
    }
    public RoomFeature getFetch(int roomId, String feature){
        String query ="SELECT * FROM public.room_feature WHERE room_id =? AND feature =?";
        RoomFeature roomFeature = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,roomId);
            pr.setString(2,feature);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int ROOM_ID = rs.getInt("room_id");
                String FEATURE = rs.getString("feature");
                roomFeature = new RoomFeature(ID,ROOM_ID,FEATURE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomFeature;
    }
    public RoomFeature getFetch(int id){
        String query ="SELECT * FROM public.room_feature WHERE id =? ";
        RoomFeature roomFeature = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int ROOM_ID = rs.getInt("room_id");
                String FEATURE = rs.getString("feature");
                roomFeature = new RoomFeature(ID,ROOM_ID,FEATURE);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomFeature;
    }
    public ArrayList<RoomFeature> getList(int roomId){
        String query = "SELECT * FROM public.room_feature WHERE room_id = ?";
        ArrayList<RoomFeature> roomFeatures = new ArrayList<>();
        RoomFeature roomFeature = null;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,roomId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int ROOM_ID = rs.getInt("room_id");
                String FEATURE = rs.getString("feature");
                roomFeature = new RoomFeature(ID,ROOM_ID,FEATURE);
                roomFeatures.add(roomFeature);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roomFeatures;
    }
}
