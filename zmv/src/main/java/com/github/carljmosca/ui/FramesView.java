/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.ui;

import com.github.carljmosca.DemoUI;
import com.github.carljmosca.repository.FramesRepository;
import com.github.carljmosca.zmv.entity.Frames;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;
import java.io.File;
import java.util.ArrayList;
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
    private final int PAGE_SIZE = 10;
    private int offset;
    private HorizontalLayout buttonLayout;
    private HorizontalLayout imageLayout;
    private VerticalLayout mainLayout;
    private Button btnPrevious;
    private Button btnNext;

    public FramesView() {
        frames = new ArrayList<>();
    }

    @PostConstruct
    private void init() {
        setCaption("Frames");
        btnPrevious = new Button("Previous");
        btnNext = new Button("Next");
        buttonLayout = new HorizontalLayout(btnPrevious, btnNext);
        btnPrevious.setWidth("45%");
        btnNext.setCaption("45%");
        imageLayout = new HorizontalLayout();
    }

    @Override
    public void onBecomingVisible() {
        getFrames();
        mainLayout = new VerticalLayout(buttonLayout, imageLayout);
        setContent(mainLayout);
    }

    private void getFrames() {
        DemoUI demoUI = (DemoUI) this.getUI();
        frames.addAll(framesRepository.findByEventId(offset, PAGE_SIZE, demoUI.getEventId()));
        displayFrame();
    }

    private void displayFrame() {
        imageLayout = new HorizontalLayout(new Image(null, new FileResource(new File("/Users/moscac/Downloads/GetMedia.jpeg"))));
    }

}
