/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dentalclinic.data;

import com.mycompany.dentalclinic.models.Professional;
import com.mycompany.dentalclinic.models.SpecialtyType;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Eddy
 */
public class ProfessionalFileDao extends FileDao<Professional> implements ProfessionalDao {

    public ProfessionalFileDao(String path) {
        super(path, 5, true);    
    }

    @Override
    public List<Professional> findAllProfessional() throws DataException {
        return read(this::mapToProfessional).stream()
                .collect(Collectors.toList());
    }

    @Override
    public Professional findByProfessionalId(int professionalId) throws DataException {
        return findAllProfessional().stream()
                .filter(p -> p.getProfessionalId() == (professionalId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Professional> findByProfessionalFirstName(String professionalFirstName) throws DataException {
        return findAllProfessional().stream()
                .filter(p -> p.getProfessionalFirstName().equals(professionalFirstName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Professional> findByProfessionalLastName(String professionalLastName) throws DataException {
          return findAllProfessional().stream()
                .filter(p -> p.getProfessionalFirstName().startsWith(professionalLastName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Professional> findByProfessionalSpecialty(SpecialtyType type) throws DataException {
        return findAllProfessional().stream()
                .filter(p -> p.getType().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public List<Professional> findByProfessionalHourlyRate(BigDecimal hourlyRate) throws DataException {
        return findAllProfessional().stream()
                .filter(p -> p.getHourlyRate().equals(hourlyRate))
                .collect(Collectors.toList());
    }
    
    private String mapToString(Professional professional) {

        return String.format("%s,%s,%s,%s,%s,",

                 professional.getProfessionalId(),

                 professional.getProfessionalFirstName(),

                 professional.getProfessionalLastName(),

                 professional.getType(),

                 professional.getHourlyRate());

    }

    private Professional mapToProfessional(String[] tokens) {

        return new Professional(
                Integer.parseInt(tokens[0]),
                tokens[1],
                tokens[2],
                SpecialtyType.valueOf(tokens[3]),
                new BigDecimal(tokens[4]));

    }

    @Override
    public Professional findByProfessionalLastNameNumberTwo(String professionalLastName) throws DataException {
        return findAllProfessional().stream()
                .filter(p -> p.getProfessionalLastName().equalsIgnoreCase(professionalLastName))
                .findFirst().orElse(null);
    }

}
