package com.projectapi.backend.repository;

import com.projectapi.backend.model.Personnel;
import com.projectapi.backend.model.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProgrammeRepository extends JpaRepository<Programme,Long> {

    @Query("select prog from Programme prog inner join Personnel personnel on personnel.id = prog.personnel.id where personnel.id =: personnelId ")
    List<Programme> findByPersonnel(@Param("personnelId")Long personnelId);

    @Query("select prog from Programme prog inner join Personnel pers on pers.id=prog.personnel.id inner join Jour j on j.id = prog.jour.id where pers.id =:personnelId and j.id =:jourId")
    List<Programme> findByPersonnelAndJour(@Param("personnelId")Long personnelId,
                                           @Param("jourId") Long jourId);

    @Query("select prog from Programme prog inner join Jour j on j.id = prog.jour.id where j.id =:jourId")
    List<Programme> findProgrammeByJour(@Param("jourId") Long jourId);
}
