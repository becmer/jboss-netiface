package lab;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.logging.Logger;

@Singleton
@Startup
@LocalBean
public class NetIfaceBean {
    private static final Logger logger = Logger.getLogger("lab.netiface");

    @PostConstruct
    @PostActivate
    public void enumerate() throws Exception {
        logger.info(System.getProperty("java.home"));
        for (final Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
            final NetworkInterface iface = en.nextElement();
            if (iface.isUp()) {
                logger.info(String.valueOf(iface));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new NetIfaceBean().enumerate();
    }
}
