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
import org.springframework.stereotype.Component;
import org.vaadin.touchkit.ui.NavigationView;
import org.vaadin.touchkit.ui.VerticalComponentGroup;

@Component
public class MenuView extends NavigationView {

    @Autowired
    // Lazy init a sub view using Instance
    private FormView formView;

    public MenuView() {
    }

    @PostConstruct
    private void init() {
        setCaption("Menu");

        final VerticalComponentGroup content = new VerticalComponentGroup();
        Button button = new Button("Form");
        button.setCaption("TEST");
        button.addClickListener((ClickEvent event) -> {
            getNavigationManager().navigateTo(formView);
            Notification.show("navigateTo");
        });
        content.addComponent(button);
        setContent(content);
    }
;

}
