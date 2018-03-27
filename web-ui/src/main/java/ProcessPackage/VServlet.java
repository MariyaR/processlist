package ProcessPackage;


import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet( value = "/*", asyncSupported = true)
@VaadinServletConfiguration(productionMode = false, ui = VaadinWindow.class)
public class VServlet extends VaadinServlet {

}
