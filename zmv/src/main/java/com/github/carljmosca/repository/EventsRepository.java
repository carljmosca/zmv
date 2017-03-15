/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.repository;

import com.github.carljmosca.zmv.entity.Events;
import com.github.carljmosca.zmv.entity.EventsPK;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

/**
 *
 * @author moscac
 */
@Service
public interface EventsRepository extends JpaRepository<Events, EventsPK> {

//    @Query("SELECT e FROM Events e where e.eventsPK.monitorId = :monitorId and e.startTime <= :startTime order by e.startTime desc")
//    List<Events> findByMonitorAndStartTime(@Param("monitorId") int monitorId,
//            @Param("startTime") Date startTime);

//    @Query("SELECT e FROM Events e where e.eventsPK.monitorId = :monitorId and e.startTime <= :startTime order by e.eventsPK.id desc limit :offset, :rowcount")
    @Query(value="SELECT * FROM Events e where e.monitorId = :monitorId and e.startTime <= :startTime order by e.id desc limit :offset, :rowcount", nativeQuery = true)
    List<Events> findByMonitorAndStartTime(@Param("offset") int offset, @Param("rowcount") int rowcount, @Param("monitorId") int monitorId,
            @Param("startTime") Date startTime);

}
