package controller;

import helper.Role;
import model.Hotel;
import model.HotelFacility;
import model.User;

import java.util.ArrayList;

public class HotelFacilityController {
    private HotelFacility hotelFacility;
    public HotelFacilityController(){
        this.hotelFacility = new HotelFacility();
    }
    public void setHotelFacility(HotelFacility hotelFacility){
        this.hotelFacility=hotelFacility;
    }

    public boolean addFacility(int hotelId,String facilityName){
        HotelController hotelController = new HotelController();
        if (hotelController.getFetch(hotelId)!= null){
            if (hotelFacility.getFetch(hotelId,facilityName) == null){
                if (hotelFacility.addFacility(hotelId,facilityName)){
                    System.out.println("Hotel Facility added");
                    return true;
                }else{
                    System.out.println("An error occurred while adding facility");
                    return false;
                }
            }else{
                System.out.println("This facility is already exist");
                return false;
            }
        }else{
            System.out.println("This hotel doesn't exist");
            return false;
        }
    }
    public boolean updateFacility(int facilityId,String facilityName){
        if (hotelFacility.getFetch(facilityId) != null){
            if (hotelFacility.updateFacility(facilityId,facilityName)){
                System.out.println("Facility updated ");
                return true;
            }else{
                System.out.println("An error occurred while updating");
                return false;
            }
        }else{
            System.out.println("This facility doesn't exist");
            return false;
        }
    }
    public boolean deleteFacility(User user, int hotelFacilityId){
        if(user.getRole() == Role.MANAGER.getRole() ){
            if (hotelFacility.getFetch(hotelFacilityId)!= null){
                if (hotelFacility.deleteFacility(hotelFacilityId)){
                    System.out.println("Facility deleted");
                    return true;
                }else{
                    System.out.println("An error occurred while deleting ");
                    return false;
                }
            }else{
                System.out.println("This facility doesn't exist");
                return false;
            }
        }else{
            System.out.println("You don't have permission to delete facility");
            return false;
        }
    }
    public boolean deleteAllFacilitiesOfHotel(int hotelId){
        if (hotelFacility.deleteAllFAcilities(hotelId)){
            System.out.println("All facilities deleted");
            return true;
        }else{
            System.out.println("An error occurred");
            return false;
        }
    }
    public ArrayList<HotelFacility> getAll(int hotelId){
        return hotelFacility.getList(hotelId);
    }
}
