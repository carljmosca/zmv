/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.ui;

import com.github.carljmosca.DemoUI;
import com.github.carljmosca.repository.FramesRepository;
import com.github.carljmosca.zmv.entity.Frames;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.touchkit.ui.NavigationView;

/**
 *
 * @author moscac
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FramesView extends NavigationView {

    @Autowired
    FramesRepository framesRepository;
    List<Frames> frames;

    public FramesView() {
    }

    @PostConstruct
    private void init() {
        setCaption("Frames");

    }
    
    @Override
    public void onBecomingVisible() {
        DemoUI demoUI = (DemoUI) this.getUI();
        if (demoUI.getEventId() > 0) {
            frames = framesRepository.findByIdEventIdOrderByTimeStampDesc(demoUI.getEventId());
            System.out.println(frames.size());
        }
    }
}
