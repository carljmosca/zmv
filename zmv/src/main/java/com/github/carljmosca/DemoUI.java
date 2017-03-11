/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca;

import com.github.carljmosca.ui.MenuView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
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
@Theme("touchkit")
public class DemoUI extends UI {

    @Autowired
    MenuView menuView;
    
    @Override
    protected void init(VaadinRequest request) {
    }
    
    
    @PostConstruct
    public void init() {
        setContent(new NavigationManager(menuView));
    }

//    @PostConstruct
//    private void buildUI() {
//        setContent(mainTabsheet);
//    }
}
