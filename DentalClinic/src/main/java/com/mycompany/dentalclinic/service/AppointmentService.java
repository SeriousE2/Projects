/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.service;

import com.mycompany.dentalclinic.data.AppointmentsDao;
import com.mycompany.dentalclinic.data.DataException;
import com.mycompany.dentalclinic.models.Appointment;
import com.mycompany.dentalclinic.models.SpecialtyType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eddy
 */
public class AppointmentService {

    private AppointmentsDao appointmentDao;

    public AppointmentService(AppointmentsDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    public List<Appointment> findByProLastNameAndDate(LocalDate date, String lastName) throws DataException {
        Response response = new Response();

        List<Appointment> readAppointments = appointmentDao.findByLocalDateAndProLast(date, lastName);
        if (readAppointments.isEmpty()) {
            response.addError("No Resulsts!");
        } else {
            return readAppointments;
        }

        return new ArrayList<>();
    }

    public Response upDateAppointment(Appointment appointment, LocalDate date) {
        Response response = new Response();

        if (response.hasError()) {
            return response;
        }
        appointmentDao.updateAppointment(appointment, date);
        return response;
    }

    public Response cancelAppointment(int customerId, LocalDate date, SpecialtyType type) {
        Response response = new Response();
        
        if (response.hasError()) {
            return response;
        }
        try {
            appointmentDao.deleteAppointment(customerId, date, type);
        } catch (DataException ex) {
            response.hasError();
        }
        return response;
    }

    public List<Appointment> findCustomerIdAndDate(int customerId, LocalDate date) {
        Response response = new Response();
        try {
            List<Appointment> readAppointmentByIdAndDate = appointmentDao.findCustomerIdAndDate(customerId, date);
            return readAppointmentByIdAndDate;
        } catch (DataException ex) {
            response.addError("");
        }
        return new ArrayList<>();
    }

}
//    public serviceResponse<List<Appointment>> findByProLastNameAndDate(LocalDate date, String lastName) throws DataException  {
//        serviceResponse<List<Appointment>> response = new serviceResponse<>();
//            
//           List<Appointment> readAppointments = appointmentDao.findByLocalDateAndProLast(date, lastName);
//            if (readAppointments.isEmpty()){
//                response.addError("No Resulsts!");
//            }
//            return response;
//            
//        } 
