package controller;

import model.Customer;
import model.Reservation;

public class CustomerController {
    private Customer customer;
    public CustomerController(){

    }
    public CustomerController(Customer customer){
        this.customer = customer;
    }
    public boolean addCustomer(int reservationId,int idNumber, String firstName, String lastName){
        ReservationController reservationController = new ReservationController();
        if (reservationController.getFetchReservationById(reservationId) != null){
            if (customer.add(reservationId,idNumber,firstName,lastName)){
                System.out.println("+ "+firstName+" added");
                return true;
            }else{
                System.out.println(" An error occurred(customer add)");
                return false;
            }
        }else {
            System.out.println("Resercation not found");
            return false;
        }
    }
}
