package controller;

import helper.Role;
import model.HotelRoomType;
import model.RoomFeature;
import model.User;

import java.security.PublicKey;
import java.util.ArrayList;

public class RoomFeatureController {
    RoomFeature roomFeature;
    public RoomFeatureController(){
        this.roomFeature = new RoomFeature();
    }

    public RoomFeature getRoomFeature() {
        return roomFeature;
    }

    public void setRoomFeature(RoomFeature roomFeature) {
        this.roomFeature = roomFeature;
    }
        public boolean addRoomFeature(int roomId,String feature){
            HotelRoomType hotelRoomType = new HotelRoomType();
            if (hotelRoomType.getFetch(roomId) != null){
                if (roomFeature.getFetch(roomId,feature) == null){
                    if (roomFeature.addFeature(roomId,feature)){
                        System.out.println("Room feature added ");
                        return true;
                    }else{
                        System.out.println("Something went wrong while adding feature");
                        return false;
                    }
                }else{
                    System.out.println("This feature is already exist");
                    return false;
                }
            }else{
                System.out.println("This room doesn't exist");
                return false;
            }
        }
        public boolean updateRoomFeature(int id, int roomId,String feature){
        if (roomFeature.getFetch(id) != null){
            if (roomFeature.updateFeature(id,roomId,feature)){
                System.out.println("Room feature updated ");
                return true;
            }else{
                System.out.println("Something went wrong while updating");
                return false;
            }
        }else{
            System.out.println("This feature doesn't exist ");
            return false;
        }
        }
        public  boolean deleteRoomFeature(User user,int id ){
        if (user.getRole() == Role.MANAGER.getRole()){
            if (roomFeature.getFetch(id) != null){
                if (roomFeature.deleteFeature(id)){
                    System.out.println("Room feature deleted");
                    return true;
                }else{
                    System.out.println("Something went wrong while deleting");
                    return false;
                }
            }else{
                System.out.println("This room feature is doesn't exist");
                return false;
            }
        }else{
            System.out.println("You don't have permission to delete room feature");
            return false;
        }
        }
        public boolean deleteAllRoomFeatureOfHotelRoomType(int roomId){
        if (getRoomFeature().deleteAllFeature(roomId)){
            System.out.println("All room features deleted");
            return true;
        }else{
            return false;
        }
        }
        public ArrayList<RoomFeature> getAll(int roomId){
        return getRoomFeature().getList(roomId);
        }
}
