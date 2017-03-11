/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.vaadin.touchkit.ui.NavigationView;
import org.vaadin.touchkit.ui.VerticalComponentGroup;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MenuView extends NavigationView {

    @Autowired
    private SearchView searchView;

    public MenuView() {
    }

    @PostConstruct
    private void init() {
        setCaption("Menu");

        final VerticalComponentGroup content = new VerticalComponentGroup();
        Button btnSearch = new Button("Search");
        btnSearch.addClickListener((ClickEvent event) -> {
            getNavigationManager().navigateTo(searchView);
            Notification.show("navigateTo");
        });
        content.addComponent(btnSearch);
        setContent(content);
    }

}
