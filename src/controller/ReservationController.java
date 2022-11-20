package controller;

import model.Reservation;

public class ReservationController {
    private Reservation reservation;

    public ReservationController(){
        this.reservation = new Reservation();
    }
    public Reservation getReservation(){
        return reservation;
    }
    public void setReservation(Reservation reservation){
        this.reservation = reservation;
    }
    public boolean addReservation(int hotelId, int roomId, int hostelId){
        if (getReservation().addReservation(hotelId,roomId,hostelId)){
            System.out.println(" Reservation added");
            return  true;
        }else{
            System.out.println(" Error occured (reservation add)");
            return false;
        }
    }
    public int getLastReservationId(){
        int lastReservationId;
        if (getReservation().getLastReservationId() >0){
            lastReservationId = getReservation().getLastReservationId();
        }else{
            System.out.println(" Reservation not found");
            lastReservationId =-1;
        }
        return  lastReservationId;
    }
    public Reservation getFetchReservationById(int reservationId){
        return reservation.getFetch(reservationId);
    }

}
