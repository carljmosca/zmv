/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.ui;

import com.github.carljmosca.DemoUI;
import com.github.carljmosca.repository.MonitorsRepository;
import com.github.carljmosca.zmv.entity.Monitors;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.NativeSelect;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.touchkit.ui.NavigationView;
import org.vaadin.touchkit.ui.VerticalComponentGroup;
import org.vaadin.touchkit.ui.DatePicker;

/**
 *
 * @author moscac
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SearchView extends NavigationView {
    
    @Autowired
    MonitorsRepository monitorsRepository;
    @Autowired
    EventsView eventsView;
    NativeSelect<Monitors> cmbMonitors;
    DatePicker datePicker;
    
    public SearchView() {
    }
    
    @PostConstruct
    private void init() {
        setCaption("ZoneMinderV");
        VerticalComponentGroup content = new VerticalComponentGroup();
        
        cmbMonitors = new NativeSelect<>();
        if (monitorsRepository.count() > 0) {
            List<Monitors> monitors = monitorsRepository.findAll();
            cmbMonitors.setItems(monitors);
            cmbMonitors.setValue(monitors.get(0));
        }
        cmbMonitors.setItemCaptionGenerator(p -> p.getName());
        cmbMonitors.setEmptySelectionAllowed(false);
        cmbMonitors.setEmptySelectionCaption("Select monitor");
        content.addComponent(cmbMonitors);
        datePicker = new DatePicker("Event Date");
        datePicker.setValue(new Date());
        content.addComponent(datePicker);
        
        final Button btnSearch = new Button("Search");
        btnSearch.addClickListener((ClickEvent event) -> {
            DemoUI demoUI = (DemoUI) this.getUI();
            if (cmbMonitors.getValue() != null && datePicker.getValue() != null) {
                demoUI.setMonitorId(cmbMonitors.getValue().getId());
                demoUI.setEventStartTime(datePicker.getValue());
                getNavigationManager().navigateTo(eventsView);
            } else {
                Notification.show("Select monitor and date");
            }
        });
        
        setContent(new CssLayout(content, btnSearch));
    }
    
}
