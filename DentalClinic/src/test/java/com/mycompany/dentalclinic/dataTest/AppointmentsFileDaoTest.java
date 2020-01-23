/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.dataTest;

import com.mycompany.dentalclinic.data.AppointmentsDao;
import com.mycompany.dentalclinic.data.AppointmentsFileDao;
import com.mycompany.dentalclinic.data.DataException;
import com.mycompany.dentalclinic.models.Appointment;
import com.mycompany.dentalclinic.models.SpecialtyType;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Eddy
 */
public class AppointmentsFileDaoTest {
    
    AppointmentsFileDao dao = new AppointmentsFileDao();

    public AppointmentsFileDaoTest() {
    }
    
    @Test
    public void testFindByDate() {
        
    }

    @Test
    public void testAddAppointment() throws Exception {
    }

    @Test
    public void testDeleteAppointment() throws Exception {
    }

    @Test
    public void testUpdateAppointment() {
        
    }

    @Test
    public void testFindByDateAndSpecialty() {
        LocalDate date = LocalDate.of(2019, Month.DECEMBER, 16);
        List<Appointment> dateAndSpecialtyList = new ArrayList<>();
        dateAndSpecialtyList = dao.findByDateAndSpecialty(date, SpecialtyType.ORAL_SURGEON);
        assertEquals(1, dateAndSpecialtyList.size());
    }

    @Test
    public void testFindByLocalDateAndProLast() throws DataException {
        assertNotNull(dao.findByLocalDateAndProLast(LocalDate.of(2019, Month.DECEMBER, 16), "Buzek"));
        dao.findByLocalDateAndProLast(LocalDate.of(2019, Month.DECEMBER, 30), "Murray");
        
    }

    @Test
    public void testFindCustomerIdAndDate() throws Exception {
        
    }

    @Test
    public void testFilePathStuff() {
    }
    
}
