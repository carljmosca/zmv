/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.vaadin.touchkit.ui.NavigationView;
import org.vaadin.touchkit.ui.VerticalComponentGroup;

/**
 *
 * @author moscac
 */
@Component
public class FormView extends NavigationView {

    public FormView() {
    }

    @PostConstruct
    private void init() {
        setCaption("Form");
        VerticalComponentGroup content = new VerticalComponentGroup();

        TextField nameField = new TextField("Name");
        //nameField.setInputPrompt("Enter your name...");
        content.addComponent(nameField);

        //final DatePicker dateField = new DatePicker("Date of Birth");
        //content.addComponent(dateField);
        //final EmailField emailField = new EmailField("Email");
        //emailField.setInputPrompt("Enter your email address...");
        //content.addComponent(emailField);
        final Button submitButton = new Button("Submit");
        submitButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                Notification.show("Thanks!");
                getNavigationManager().navigateBack();
            }
        });

        setContent(new CssLayout(content, submitButton));
    }

}
