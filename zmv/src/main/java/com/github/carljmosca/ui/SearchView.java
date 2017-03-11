/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.ui;

import com.github.carljmosca.repository.MonitorsRepository;
import com.github.carljmosca.zmv.entity.Monitors;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
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
    ComboBox<Monitors> cmbMonitors;
    DatePicker datePicker;

    public SearchView() {
    }

    @PostConstruct
    private void init() {
        setCaption("Search");
        VerticalComponentGroup content = new VerticalComponentGroup();

        cmbMonitors = new ComboBox<>();
        cmbMonitors.setItems(monitorsRepository.findAll());
        cmbMonitors.setItemCaptionGenerator(p -> p.getName());
        cmbMonitors.setEmptySelectionAllowed(false);
        cmbMonitors.setEmptySelectionCaption("Select monitor");
        content.addComponent(cmbMonitors);
        datePicker = new DatePicker("Event Date");
        content.addComponent(datePicker);

        final Button submitButton = new Button("Submit");
        submitButton.addClickListener((ClickEvent event) -> {
            Notification.show("Thanks!");
            getNavigationManager().navigateTo(eventsView);
        });

        setContent(new CssLayout(content, submitButton));
    }

}
