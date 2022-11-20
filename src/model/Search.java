package model;

import helper.DBConnector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Search {
    public ArrayList<Hotel> searchHotelBySeasonEnd(Date seasonStart, Date seasonEnd, String hotelAdress , String hotelName){
        String query ="SELECT public.hotel.* FROM hotel_season "+
                "LEFT JOIN public.hotel ON public.hotel.id = public.hotel_seasons.hotel_id "+
                "WHERE public.hotel_seasons.season_start <=? "+
                "AND public.hotel_season.season_end <=? "+
                "AND public.hotel.adress ILIKE '%{{adress}}%' "+
                "AND public.hot el.name ILIKE '%{{name}}%'";

        query = query.replace("{{name}}" ,hotelName);
        query = query.replace("{{adress}}" ,hotelAdress);

        ArrayList<Hotel> resposneList = new ArrayList<>();
        Hotel response;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setDate(1,seasonStart);
            pr.setDate(2,seasonEnd);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                String NAME = rs.getString("name");
                String ADRESS = rs.getString("adress");
                String MAIL = rs.getString("mail");
                String PHONE = rs.getString("phone");
                String STAR = rs.getString("star");
                response = new Hotel(ID,NAME,ADRESS,MAIL,PHONE,STAR);
                resposneList.add(response);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resposneList;
    }
}
