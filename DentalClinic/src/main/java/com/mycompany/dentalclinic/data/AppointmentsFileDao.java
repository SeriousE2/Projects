/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.data;

import com.mycompany.dentalclinic.models.Appointment;
import com.mycompany.dentalclinic.models.SpecialtyType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Eddy
 */
public class AppointmentsFileDao extends FileDao<Appointment> implements AppointmentsDao {

    private static final String HEADER = "customer_id,dental_pro_lastname,specialty,start_time,end_time,total_cost,notes";

//    public AppointmentsFileDao(String FILE_PATH) {
//        super(FILE_PATH, 7, true);
//    }
    public AppointmentsFileDao() {
        super("", 7, true);
    }

    @Override
    public List<Appointment> findByDate(LocalDate date) {
        filePathStuff(date);
        try {
            
            List<Appointment> apptForDate = read(this::mapToAppointment).stream()
                    .collect(Collectors.toList());
            for (Appointment a : apptForDate) {
                a.setDate(date);
            }
            return apptForDate;
        } catch (DataException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public void addAppointment(Appointment appointment, LocalDate date) throws DataException {
        filePathStuff(date);
        List<Appointment> appointments = findByDate(date);
        appointments.add(appointment);
//        write((Collection<Appointment>)appointments, this::mapToString);
        write(appointments, this::mapToString, HEADER);
//        append(appointment, this::mapToString);
    }

    @Override
    public boolean deleteAppointment(int customerId, LocalDate date, SpecialtyType type) throws DataException {

        filePathStuff(date);
        read(this::mapToAppointment);
        List<Appointment> list = findByDate(date);
        list.removeIf(a -> a.getCustomerId() == customerId && a.getType().equals(type));
        write(list, (appointment) -> this.mapToString(appointment), "customer_id,dental_pro_lastname,specialty,start_time,end_time,total_cost,notes");
        return true;
    }

    @Override
    public boolean updateAppointment(Appointment appointment, LocalDate date) {
        List<Appointment> appointments = findByDate(appointment.getDate());
        int index = 0;
        for (; index < appointments.size(); index++) {
            Appointment current = appointments.get(index);
            if (current.getCustomerId() == appointment.getCustomerId() && current.getType() == appointment.getType()) {
                break;
            }
        }
        if (index < appointments.size()) {
            appointments.set(index, appointment);
            try {
                write(appointments, (appointment1) -> this.mapToString(appointment1), "customer_id,dental_pro_lastname,specialty,start_time,end_time,total_cost,notes");
            } catch (DataException ex) {
            }
            return true;
        }
        return false;
    }

//    @Override
//    public boolean updateAppointment(Appointment appointment) throws DataException {
//
//        List<Appointment> allappointment = findByDate(appointment.getDate());
//
//        int index = 0;
//        for (; index < allappointment.size(); index++) {
//            if (allappointment.get(index).getCustomerId() == appointment.getCustomerId()
//                    && allappointment.get(index).getType() == appointment.getType()) {
//                    allappointment.remove(index);
//                    write (allappointment, this::mapToString);
//                    return true;
//            }
//        }
//        return false;
//    }
    @Override
    public List<Appointment> findByDateAndSpecialty(LocalDate date, SpecialtyType type) {

        return findByDate(date).stream()
                .filter(s -> s.getType().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public List<Appointment> findByLocalDateAndProLast(LocalDate date, String lastName) {

        filePathStuff(date);

        try {
            // tmp mean temp

            List<Appointment> apptForDate = read(this::mapToAppointment).stream()
                    .filter((a) -> a.getDentalProLastname().equalsIgnoreCase(lastName))
                    .collect(Collectors.toList());
            for (Appointment a : apptForDate) {
                a.getDate();
            }
            return apptForDate;
        } catch (DataException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Appointment> findCustomerIdAndDate(int customerId, LocalDate date) throws DataException {
        filePathStuff(date);
        return findByDate(date).stream()
                .filter((a) -> a.getCustomerId() == (customerId))
                .collect(Collectors.toList());
    }

    private String mapToString(Appointment appointments) {
        return String.format("%s,%s,%s,%s,%s,%s,%s",
                appointments.getCustomerId(),
                appointments.getDentalProLastname(),
                appointments.getType(),
                appointments.getStart(),
                appointments.getEnd(),
                appointments.getTotalCost(),
                appointments.getNotes());

    }

    private Appointment mapToAppointment(String[] tokens) {
        return new Appointment(
                Integer.parseInt(tokens[0]),
                tokens[1],
                SpecialtyType.valueOf(tokens[2]),
                LocalTime.parse(tokens[3]),
                LocalTime.parse(tokens[4]),
                new BigDecimal(tokens[5]),
                tokens[6]);
    }

    public void filePathStuff(LocalDate date) {
        // date.format()
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateToString = date.toString();
        String[] dateArray = dateToString.split("-");

//        // Remove whatever in the date
//        dateToString = dateToString.replace("_", "");
//        super.setFILE_PATH(String.format("My Path", dateToString));
        super.setFILE_PATH("appointments" + "_" + date.format(formatDate) + ".txt");
    }

}
