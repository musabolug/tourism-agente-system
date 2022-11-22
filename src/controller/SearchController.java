package controller;

import model.Hotel;
import model.Search;

import java.util.ArrayList;
import java.util.Date;

public class SearchController {
    public SearchController(){

    }
    public ArrayList<Hotel> searchHotelBySeasonEnd(Date startDate, Date endDate, String hotelAdress, String hotelName){
        Search search = new Search();
        return search.searchHotelBySeasonEnd((java.sql.Date) startDate, (java.sql.Date) endDate,hotelAdress,hotelName);
    }
}
