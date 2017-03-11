/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.ui;

import com.github.carljmosca.repository.FramesRepository;
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

    public FramesView() {
    }

    @PostConstruct
    private void init() {
        setCaption("Frames");

    }
}
