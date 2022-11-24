package model;

import helper.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HostelType {
    private int hotelId;
    private boolean ultraAllInclusive;
    private boolean allInclusive;
    private boolean roomBreakfast;
    private boolean fullPension;
    private boolean halfBoard;
    private boolean onyBad;
    private boolean noAlcoholFullCredit;

    public HostelType(){

    }
    public HostelType( int hotelId, boolean ultraAllInclusive,boolean allInclusive, boolean roomBreakfast, boolean fullPension,boolean halfBoard,boolean onyBad, boolean noAlcoholFullCredit){

        this.hotelId = hotelId;
        this.ultraAllInclusive = ultraAllInclusive;
        this.allInclusive = allInclusive;
        this.roomBreakfast = roomBreakfast;
        this.fullPension = fullPension;
        this.halfBoard = halfBoard;
        this.onyBad = onyBad;
        this.noAlcoholFullCredit = noAlcoholFullCredit;
    }



    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public boolean isUltraAllInclusive() {
        return ultraAllInclusive;
    }

    public void setUltraAllInclusive(boolean ultraAllInclusive) {
        this.ultraAllInclusive = ultraAllInclusive;
    }

    public boolean isAllInclusive() {
        return allInclusive;
    }

    public void setAllInclusive(boolean allInclusive) {
        this.allInclusive = allInclusive;
    }

    public boolean isRoomBreakfast() {
        return roomBreakfast;
    }

    public void setRoomBreakfast(boolean roomBreakfast) {
        this.roomBreakfast = roomBreakfast;
    }

    public boolean isFullPension() {
        return fullPension;
    }

    public void setFullPension(boolean fullPension) {
        this.fullPension = fullPension;
    }

    public boolean isHalfBoard() {
        return halfBoard;
    }

    public void setHalfBoard(boolean halfBoard) {
        this.halfBoard = halfBoard;
    }

    public boolean isOnyBad() {
        return onyBad;
    }

    public void setOnyBad(boolean onyBad) {
        this.onyBad = onyBad;
    }

    public boolean isNoAlcoholFullCredit() {
        return noAlcoholFullCredit;
    }

    public void setNoAlcoholFullCredit(boolean noAlcoholFullCredit) {
        this.noAlcoholFullCredit = noAlcoholFullCredit;
    }

    public boolean addHostelType(int hotelId, boolean ultraAllInclusive,boolean allInclusive, boolean roomBreakfast, boolean fullPension,boolean halfBoard,boolean onyBad, boolean noAlcoholFullCredit ){
        String query ="INSERT INTO public.hotel_hostel_type (hostel_id,ultra_All,all_inclusive,room_breakfast,full_pension,half_board,only_bad,no_alcohol) VALUES (?,?,?,?,?,?,?,?)";
        boolean isAdd;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setBoolean(2,ultraAllInclusive);
            pr.setBoolean(3,allInclusive);
            pr.setBoolean(4,roomBreakfast);
            pr.setBoolean(5,fullPension);
            pr.setBoolean(6,halfBoard);
            pr.setBoolean(7,onyBad);
            pr.setBoolean(8,noAlcoholFullCredit);
            isAdd = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAdd;
    }

    public HostelType getFetchHostelType(int hostelTypeId){
        String query = "SELECT * FROM public.hotel_hostel_type WHERE hotel_id= ?";
        HostelType hType = new HostelType();

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hostelTypeId);
            ResultSet rs = pr.executeQuery();
            rs.next();
            hType.setHotelId(rs.getInt("hotel_id"));
            hType.setUltraAllInclusive(rs.getBoolean("ultra_all"));
            hType.setAllInclusive(rs.getBoolean("all_inclusive"));
            hType.setRoomBreakfast(rs.getBoolean("room_breakfast"));
            hType.setFullPension(rs.getBoolean("full_pension"));
            hType.setHalfBoard(rs.getBoolean("half_board"));
            hType.setOnyBad(rs.getBoolean("only_bad"));
            hType.setNoAlcoholFullCredit(rs.getBoolean("no_alcohol"));
            rs.close();
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hType;
    }
    public boolean updateHostelType(int hotelId, boolean ultraAllInclusive,boolean allInclusive, boolean roomBreakfast, boolean fullPension,boolean halfBoard,boolean onyBad, boolean noAlcoholFullCredit){
        String query ="INSERT INTO public.hotel_hostel_type (hostel_id,ultra_All,all_inclusive,room_breakfast,full_pension,half_board,only_bad,no_alcohol) VALUES (?,?,?,?,?,?,?,?)";
        boolean isupdate;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            pr.setBoolean(2,ultraAllInclusive);
            pr.setBoolean(3,allInclusive);
            pr.setBoolean(4,roomBreakfast);
            pr.setBoolean(5,fullPension);
            pr.setBoolean(6,halfBoard);
            pr.setBoolean(7,onyBad);
            pr.setBoolean(8,noAlcoholFullCredit);
            isupdate = pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isupdate;
    }

    public boolean deleteHostelType(int hostelTypeId){
        String query = "DELETE FROM public hotel_hostel_type WHERE hotel_id =?";
        boolean isDelete;

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hostelTypeId);
            isDelete = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isDelete;
    }

    public boolean deleteAllHostelType(int hotelId){
        String query = "DELETE FROM public.hotel_hostel_type WHERE hotel_id =?";
        boolean isDelete;
        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            isDelete = pr.executeUpdate() != -1;
            pr.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isDelete;
    }
    public ArrayList<HostelType> getList(int hotelId){
        String query = "SELECT * FROM public.hotel_hostel_type WHERE hotel_id= ?";
    ArrayList<HostelType> hostelTypeList = new ArrayList<>();
    HostelType hType = new HostelType();

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setInt(1,hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                hType.setHotelId(rs.getInt("hotel_id"));
                hType.setUltraAllInclusive(rs.getBoolean("ultra_all"));
                hType.setAllInclusive(rs.getBoolean("all_inclusive"));
                hType.setRoomBreakfast(rs.getBoolean("room_breakfast"));
                hType.setFullPension(rs.getBoolean("full_pension"));
                hType.setHalfBoard(rs.getBoolean("half_board"));
                hType.setOnyBad(rs.getBoolean("only_bad"));
                hType.setNoAlcoholFullCredit(rs.getBoolean("no_alcohol"));
                hostelTypeList.add(hType);
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hostelTypeList;
    }
}

