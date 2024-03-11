package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class MYSQLConnection {
    private static final Connection CONNECTION;
    private static Logger log=Logger.getLogger(MYSQLConnection.class.getName());

    static{

        InputStream is=MYSQLConnection.class.getClassLoader().getResourceAsStream("mysql.properties");
       Properties p=new Properties();
        try {
            p.load(is);
            CONNECTION= DriverManager.getConnection("jdbc:mysql://"+p.getProperty("url")+":"+p.getProperty("port")+"/"+p.getProperty("nombreBD"),p.getProperty("user"),p.getProperty("password"));
            log.info("Conexión realizada con éxito");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            log.severe("Fallo en la conexión");
            throw new RuntimeException(e);
        }

    }
    public static Connection getConexion(){
        return CONNECTION;
    }
}
