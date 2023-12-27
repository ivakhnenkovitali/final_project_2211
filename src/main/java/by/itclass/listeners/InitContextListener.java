package by.itclass.listeners;


import by.itclass.model.db.ConnectionManager;
import by.itclass.model.services.ServiceFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;



@WebListener
public class InitContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionManager.init();
        ServiceFactory.init();
    }
}