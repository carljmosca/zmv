/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.ui;

import com.github.carljmosca.DemoUI;
import com.github.carljmosca.repository.EventsRepository;
import com.github.carljmosca.zmv.entity.Events;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
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
public class EventsView extends NavigationView {
    
    @Autowired
    EventsRepository eventsRepository;
    @Autowired
    FramesView framesView;
    private Grid<Events> grid;
    private Button btnSubmit;
    
    public EventsView() {
    }
    
    @PostConstruct
    private void init() {
        setCaption("Events");
        grid = new Grid<>(Events.class);
        grid.addColumn(Events::getName).setCaption("Name");
        grid.addColumn(Events::getCause).setCaption("Cause");
        grid.addColumn(Events::getStartTime).setCaption("Start");
        grid.setColumns("name", "cause", "startTime");
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener((SelectionEvent<Events> event) -> {
            Notification.show(event.getFirstSelectedItem().get().getName());
        });
        grid.setDescriptionGenerator(p -> p.getName());
        grid.setSizeUndefined();
        btnSubmit = new Button("Back");
        btnSubmit.addClickListener((Button.ClickEvent event) -> {
            getNavigationManager().navigateBack();
        });
        setContent(new VerticalLayout(grid, btnSubmit));
    }
    
    @Override
    public void onBecomingVisible() {
        populateGrid();
        
    }
    
    private void populateGrid() {
        DemoUI demoUI = (DemoUI) this.getUI();
        if (demoUI.getEventStartTime() != null && demoUI.getMonitorId() > 0) {
            grid.setItems(eventsRepository.findByMonitorAndStartTime(demoUI.getMonitorId(), demoUI.getEventStartTime()));
        }
    }
    
}
