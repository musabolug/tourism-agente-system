package model;

import helper.DBConnector;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelSeason {
   private int id;
    private int hotelId;
    private Date seasonStart;
    private Date seasonEnd;
    private String seasonName;
    public HotelSeason(){

    }
    public HotelSeason(int id, int hotelId,Date seasonStart, Date seasonEnd,String seasonName){
        this.id = id;
        this.hotelId = hotelId;
        this.seasonStart = seasonStart;
        this.seasonEnd = seasonEnd;
        this.seasonName = seasonName;
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

    public Date getSeasonStart() {
        return seasonStart;
    }

    public void setSeasonStart(Date seasonStart) {
        this.seasonStart = seasonStart;
    }

    public Date getSeasonEnd() {
        return seasonEnd;
    }

    public void setSeasonEnd(Date seasonEnd) {
        this.seasonEnd = seasonEnd;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }
    public  boolean addHotelSeason(int hotelId,Date seasonStart, Date seasonEnd, String seasonName){
        String query ="INSERT INTO public.hotel_seasons (hotel_id,season_start,season_end,season_name) VALUES (?,?,?,?)";
        boolean isAdded;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setDate(2,seasonStart);
            pr.setDate(3,seasonEnd);
            pr.setString(4,seasonName);
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
    public boolean updateHotelSeasons(int id ,int hotelId, Date seasonStart, Date seasonEnd, String seasonName){
        String query = "UPDATE public.hotel_seasons SET (id,hotel_id,season_start,season_end,season_name) VALUES (?,?,?,?,?)";
        boolean isUpdated;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,id);
            pr.setInt(2,hotelId);
            pr.setDate(3,seasonStart);
            pr.setDate(4,seasonEnd);
            pr.setString(5,seasonName);
            isUpdated = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isUpdated;
    }

    public HotelSeason getFetchHotelSeason(int hotelId,String seasonName){
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
    }
    public ArrayList<HotelSeason> getList(int hotelId){
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
    }
}
