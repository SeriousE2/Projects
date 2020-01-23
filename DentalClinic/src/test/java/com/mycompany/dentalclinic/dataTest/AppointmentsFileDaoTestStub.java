/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.dataTest;

import com.mycompany.dentalclinic.data.AppointmentsDao;
import com.mycompany.dentalclinic.data.DataException;
import com.mycompany.dentalclinic.models.Appointment;
import com.mycompany.dentalclinic.models.SpecialtyType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Eddy
 */
public class AppointmentsFileDaoTestStub implements AppointmentsDao {

    private List<Appointment> appointments = new ArrayList<>();
    
    Appointment appointment1 = new Appointment(23, "Buzek", SpecialtyType.DENTIST, LocalTime.of(2, 30), LocalTime.of(3, 30), new BigDecimal("100.00"), "Test Data");
     Appointment appointment2 = new Appointment(24, "Stebbing", SpecialtyType.HYGIENIST, LocalTime.of(7, 30), LocalTime.of(8, 30), new BigDecimal("150.00"), "Test Data");
     Appointment appointment3 = new Appointment(25, "Robins", SpecialtyType.HYGIENIST, LocalTime.of(9, 30), LocalTime.of(10, 30), new BigDecimal("175.00"), "Test Data");
    
    @Override
    public void addAppointment(Appointment appointment, LocalDate date) throws DataException {
        LocalDate date2 = LocalDate.of(04, Month.MARCH, 2022);
        appointments.add(appointment);
    }

    @Override
    public boolean deleteAppointment(int customerId, LocalDate date, SpecialtyType type) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateAppointment(Appointment appointment, LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Appointment> findByDate(LocalDate date) throws DataException {
        return new ArrayList<>(appointments);
    }

    @Override
    public List<Appointment> findByDateAndSpecialty(LocalDate date, SpecialtyType type) throws DataException {
      List<Appointment> forDate = findByDate(date);
      List<Appointment> dateAndSpecialty = new ArrayList<Appointment>();
        for (Appointment a : forDate) {
            if(a.getType() == type) {
                dateAndSpecialty.add(a);
            }
        }
      
        return dateAndSpecialty;
    }

    @Override
    public List<Appointment> findByLocalDateAndProLast(LocalDate date, String lastName) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Appointment> findCustomerIdAndDate(int customerId, LocalDate date) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
