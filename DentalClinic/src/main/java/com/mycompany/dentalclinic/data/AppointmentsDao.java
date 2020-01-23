/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.data;

import com.mycompany.dentalclinic.models.Appointment;
import com.mycompany.dentalclinic.models.Customer;
import com.mycompany.dentalclinic.models.SpecialtyType;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Eddy
 */
public interface AppointmentsDao {
    
    public void addAppointment(Appointment appointment, LocalDate date) throws DataException;
    
    public boolean deleteAppointment(int customerId, LocalDate date, SpecialtyType type)throws DataException;
    
//    boolean updateAppointment(Appointment appointment)throws DataException;
    
    public boolean updateAppointment(Appointment appointment, LocalDate date);
    
    public List<Appointment> findByDate (LocalDate date)throws DataException;
      
    public List<Appointment> findByDateAndSpecialty(LocalDate date, SpecialtyType type)throws DataException;
    
    public List<Appointment> findByLocalDateAndProLast(LocalDate date, String lastName)throws DataException;
    
    public List<Appointment> findCustomerIdAndDate(int customerId, LocalDate date)throws DataException;
    
}
