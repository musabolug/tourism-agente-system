package controller;

import helper.Role;
import model.HostelType;
import model.User;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;

public class HostelController {
    private HostelType hostelType;
    public HostelController(){
    this.hostelType = new HostelType();
    }

    public HostelType getHostelType(){
        return hostelType;
    }
    public void setHostelType(HostelType hostelType){
        this.hostelType = hostelType;
    }
    public boolean addHostelType(int hotelId,String hostelType,int price){
    HotelController hotelController = new HotelController();
    if (hotelController.getFetch(hotelId)!= null){
        if (getHostelType().getFetchHostelType(hotelId,hostelType)== null){
            if (getHostelType().addHostelType(hotelId,hostelType,price)){
                System.out.println("Hostel type added to" +hotelController.getFetch(hotelId).getName());
                return true;
            }else{
                System.out.println("An error occurred during adding");
                return false;
            }
        }else {
            System.out.println("This hostel type is already exist");
            return false;
        }
    }else {
        System.out.println("This Hotel doesn't exist");
        return false;
    }
    }
    public boolean updateHostelType(int hostelTypeId,String hostelType,int price){
        if (getHostelType().getFetchHostelType(hostelTypeId) != null){
            if (getHostelType().updateHostelType(hostelTypeId,hostelType,price)){
                System.out.println("Hostel type updated");
                return true;
            }else{
                System.out.println("An error occurred during update");
                return false;
            }
        }else{
            System.out.println("This hostel type doesn't exist");
            return false;
        }
    }

    public boolean deleteHostelType(User user,int hotelFacilityId){
        if (user.getRole() == Role.MANAGER.getRole()){
            if (getHostelType().getFetchHostelType(hotelFacilityId) != null){
                if (getHostelType().deleteHostelType(hotelFacilityId)){
                    System.out.println("Hostel Type deleted ");
                    return true;
                }else {
                    System.out.println("An error occurred during the deletion");
                    return false;
                }
            }else{
                System.out.println("HostelType doesn't exist");
                return false;
            }
        }else{
            System.out.println("You don't have permission to delete Hostel Type");
            return false;
        }
    }
    public ArrayList<HostelType> getAll(int hotelId){
        return getHostelType().getList(hotelId);
    }
    public boolean deleteAllHostelTypeOfHotel(int hotelId){
        if (getHostelType().deleteAllHostelType(hotelId)){
            System.out.println("All hostel types deleted");
            return true;
        }else {
            return false;
        }
    }
}
