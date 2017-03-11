/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.carljmosca;

import com.vaadin.spring.server.SpringVaadinServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.vaadin.touchkit.settings.TouchKitSettings;

/**
 *
 * @author moscac
 */
public class SpringAwareTouchKitServlet extends SpringVaadinServlet {

    TouchKitSettings touchKitSettings;

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        touchKitSettings = new TouchKitSettings(getService());
    }

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo != null) {
            if (pathInfo.endsWith("themes/touchkit/styles.css")) {
                serveDummyFile(response, "max-age=1000000");
                return;
            } else if (pathInfo.endsWith("PING/")) {
                serveDummyFile(response, "no-store, no-cache, max-age=0, must-revalidate");
                return;
            }
        }
        super.service(request, response);
    }

    private void serveDummyFile(HttpServletResponse response, String cacheControl)
            throws IOException {
        response.setContentType("text/css");
        response.setHeader("Cache-Control", cacheControl);
        response.getOutputStream().write("\n".getBytes());
    }

}
