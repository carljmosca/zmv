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
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Grid;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
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
    private SimpleDateFormat sdf;

    public EventsView() {
    }

    @PostConstruct
    private void init() {
        sdf = new SimpleDateFormat("MM/dd/yy HH:mm");
        setCaption("Events");
        grid = new Grid<>(Events.class);
        grid.addColumn(p -> p.getName() + ": "
                + sdf.format(p.getStartTime()) + " "
                + p.getCause()).setCaption("Name/Start").setId("nameAndStart");
        grid.setColumns("nameAndStart");
        grid.setHeightMode(HeightMode.ROW);
        grid.setHeightByRows(10.0d);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener((SelectionEvent<Events> event) -> {
            DemoUI demoUI = (DemoUI) UI.getCurrent();
            demoUI.setEventId(grid.getSelectedItems().stream().findFirst().get().getEventsPK().getId());
            getNavigationManager().navigateTo(framesView);
        });
        grid.setSizeUndefined();
        VerticalLayout gridLayout = new VerticalLayout(grid);
        gridLayout.setSizeFull();
        SimpleDateFormat dtf = new SimpleDateFormat("yy-MM-dd hh:mm");
        setContent(gridLayout);
    }

    @Override
    public void onBecomingVisible() {
        populateGrid();

    }

    private void populateGrid() {
        DemoUI demoUI = (DemoUI) this.getUI();
        if (demoUI.getEventStartTime() != null && demoUI.getMonitorId() > 0) {
            grid.setDataProvider(
                    (sortorder, offset, limit) -> eventsRepository.findByMonitorAndStartTime(
                            offset, limit, demoUI.getMonitorId(), demoUI.getEventStartTime()).stream(),
                    () -> (int) eventsRepository.count()
            );
            grid.setHeightMode(HeightMode.ROW);
            grid.setHeightByRows(10.0d);
        }
    }

}
