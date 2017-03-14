/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.repository;

import com.github.carljmosca.zmv.entity.Frames;
import com.github.carljmosca.zmv.entity.FramesPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author moscac
 */
@Service
public interface FramesRepository extends JpaRepository<Frames, FramesPK> {  
    
    List<Frames> findByIdEventIdOrderByTimeStampDesc(int eventId);
    //List<Frames> findByIdEventId(int eventId);
    
}
