package model;

import helper.DBConnector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class HotelSeason {

    private int hotelId;
    private LocalDate seasonStart;
    private LocalDate seasonEnd;
    private int startDay,startMonth,startYear,endDay,endMonth,endYear;
    public HotelSeason(){

    }
    public HotelSeason(int id, int hotelId,LocalDate seasonStart, LocalDate seasonEnd,int startDay,int startMonth,int startYear,int endDay,int endMonth,int endYear){

        this.hotelId = hotelId;
        this.seasonStart = LocalDate.of(startDay,startMonth,startYear);
        this.seasonEnd = LocalDate.of(endDay,endMonth,endYear);
    }

    public  boolean addHotelSeason(int hotelId,int startDay,int startMonth,int startYear,int endDay,int endMonth,int endYear){
        String query ="INSERT INTO public.hotel_seasons (hotel_id,s_day,s_month,s_year,e_day,e_month,e_year) VALUES (?,?,?,?,?,?,?)";
        boolean isAdded;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setInt(2,startDay);
            pr.setInt(3,startMonth);
            pr.setInt(4,startYear);
            pr.setInt(5,endDay);
            pr.setInt(6,endMonth);
            pr.setInt(7,endYear);
            isAdded = pr.executeUpdate() !=-1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdded;
    }
    public boolean deleteHotelSeason(int id){
        String query = "DELETE FROM public.hotel_seasons WHERE id =?";
        boolean isDeleted;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            isDeleted = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  isDeleted;
    }
    public boolean deleteAllHotelSeason(int hotelId){
        String query = "DELETE FROM public.hotel_seasons WHERE hotel_id =?";
        boolean isAllDeleted;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            isAllDeleted = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  isAllDeleted;
    }
    public boolean updateHotelSeasons(int hotelId,int startDay,int startMonth,int startYear,int endDay,int endMonth,int endYear){
        String query ="UPDATE public.hotel_seasons SET (hotel_id,s_day,s_month,s_year,e_day,e_month,e_year) VALUES (?,?,?,?,?,?,?)";
        boolean isUpdated;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setInt(2,startDay);
            pr.setInt(3,startMonth);
            pr.setInt(4,startYear);
            pr.setInt(5,endDay);
            pr.setInt(6,endMonth);
            pr.setInt(7,endYear);
            isUpdated = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isUpdated;
    }

  /*  public HotelSeason getFetchHotelSeason(int hotelId,String seasonName){
        String query= "SELECT * FROM public.hotel_seasons WHERE hotel_id =? AND season_name =?";
        HotelSeason hotelSeason = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setString(2,seasonName);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                Date SEASON_START = rs.getDate("season_start");
                Date SEASON_END = rs.getDate("season_end");
                String SEASON_NAME = rs.getString("season_name");
                hotelSeason = new HotelSeason(ID,HOTEL_ID,SEASON_START,SEASON_END,SEASON_NAME);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelSeason;
    }
    public HotelSeason getFetchHotelSeason(int seasonId){
        String query= "SELECT * FROM public.hotel_seasons WHERE id =?";
        HotelSeason hotelSeason = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                Date SEASON_START = rs.getDate("season_start");
                Date SEASON_END = rs.getDate("season_end");
                String SEASON_NAME = rs.getString("season_name");
                hotelSeason = new HotelSeason(ID,HOTEL_ID,SEASON_START,SEASON_END,SEASON_NAME);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelSeason;
    }*/
   /* public ArrayList<HotelSeason> getList(int hotelId){
        String query = "SELECT * FROM public.hotel_seasons WHERE hotel_id";
        ArrayList<HotelSeason> hotelSeasonsList = new ArrayList<>();
        HotelSeason hotelSeason = null;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                int ID = rs.getInt("id");
                int HOTEL_ID = rs.getInt("hotel_id");
                Date SEASON_START = rs.getDate("season_start");
                Date SEASON_END = rs.getDate("season_end");
                String SEASON_NAME = rs.getString("season_name");
                hotelSeason = new HotelSeason(ID,HOTEL_ID,SEASON_START,SEASON_END,SEASON_NAME);
                hotelSeasonsList.add(hotelSeason);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelSeasonsList;
    }*/

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getSeasonStart() {
        return seasonStart;
    }

    public void setSeasonStart(LocalDate seasonStart) {
        this.seasonStart = seasonStart;
    }

    public LocalDate getSeasonEnd() {
        return seasonEnd;
    }

    public void setSeasonEnd(LocalDate seasonEnd) {
        this.seasonEnd = seasonEnd;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }
}
