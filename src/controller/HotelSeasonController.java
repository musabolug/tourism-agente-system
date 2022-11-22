package controller;

import helper.Role;
import model.Hotel;
import model.HotelSeason;
import model.User;

import java.sql.Date;
import java.util.ArrayList;

public class HotelSeasonController {
    HotelSeason hotelSeason;
    public  HotelSeasonController(){
        this.hotelSeason = new HotelSeason();
    }

    public HotelSeason getHotelSeason() {
        return hotelSeason;
    }

    public void setHotelSeason(HotelSeason hotelSeason) {
        this.hotelSeason = hotelSeason;
    }

    public boolean addHotelSeason(int hotelId, Date seasonStart, Date seasonEnd, String seasonName){
        HotelController hotelController = new HotelController();
        if(hotelController.getFetch(hotelId)!= null){
            if (getHotelSeason().getFetchHotelSeason(hotelId,seasonName) == null){
                if (getHotelSeason().addHotelSeason(hotelId,seasonStart,seasonEnd,seasonName)){
                    System.out.println("Hotel season added successfully");
                    return true;
                }else{
                    System.out.println("Something went wrong while adding season");
                    return false;
                }
            }else{
                System.out.println("This hotel season is already exist");
                return false;
            }
        }else{
            System.out.println("This hotel doesn't exist");
            return false;
        }
    }
    public boolean updateHotelSeason(int hotelSeasonId,int hotelId,Date seasonStart, Date seasonEnd, String seasonName ){
        if (getHotelSeason().getFetchHotelSeason(hotelSeasonId) != null){
            if (getHotelSeason().updateHotelSeasons(hotelSeasonId,hotelId,seasonStart,seasonEnd,seasonName)){
                System.out.println("Hotel season updated");
                return true;
            }else{
                System.out.println("Something went wrong while updating");
                return false;
            }
        }else{
            System.out.println("This hotel season doesn't exist");
            return false;
        }
    }
    public boolean deleteHotelSeason(User user, int hotelSeasonId){
        if (user.getRole() == Role.MANAGER.getRole()){
            if (hotelSeason.getFetchHotelSeason(hotelSeasonId) != null){
                if (hotelSeason.deleteHotelSeason(hotelSeasonId)){
                    System.out.println("Hotel Season deleted ");
                    return true;
                }else{
                    System.out.println("Something went wrong while deleting season");
                    return false;
                }
            }else{
                System.out.println("This hotel season doesn't exist");
                return false;
            }
        }else{
            System.out.println("You don't have permission to delete season");
            return false;
        }
    }
    public boolean deleteAllHotelSeasonOfHotel(int hotelId){
        if (getHotelSeason().deleteAllHotelSeason(hotelId)){
            System.out.println("All hotel seasons deleted ");
            return true;
        }else{
            return false;
        }
    }
    public ArrayList<HotelSeason> getAll(int hotelId){
        return getHotelSeason().getList(hotelId);
    }
}
