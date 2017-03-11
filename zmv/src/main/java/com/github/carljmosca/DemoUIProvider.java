/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;
import org.springframework.stereotype.Component;

/**
 *
 * @author moscac
 */

@Component
public class DemoUIProvider extends UIProvider {

    @Override
    public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
        return DemoUI.class;
    }
    
}
