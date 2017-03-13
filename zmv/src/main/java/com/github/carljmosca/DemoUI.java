/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca;

import com.github.carljmosca.ui.MenuView;
import com.github.carljmosca.ui.SearchView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.UI;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.touchkit.ui.NavigationManager;

/**
 *
 * @author moscac
 */
@Widgetset("com.github.carljmosca.widgetset.DemoWidgetset")
@PreserveOnRefresh
@Title("Demo")
@SpringUI
@Theme("zmvtheme")
@UIScope
public class DemoUI extends UI {

    @Autowired
    MenuView menuView;
    @Autowired
    SearchView searchView;
    private int monitorId;
    private Date eventStartTime;
    
    @Override
    protected void init(VaadinRequest request) {
    }
    
    
    @PostConstruct
    public void init() {
        NavigationManager navigationManager = new NavigationManager();
        //navigationManager.setPreviousComponent(menuView);
        navigationManager.setCurrentComponent(searchView);
        setContent(navigationManager);
    }

    public int getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(int monitorId) {
        this.monitorId = monitorId;
    }

    public Date getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Date eventStartTime ) {
        this.eventStartTime = eventStartTime;
    }

}
