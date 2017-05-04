/*
 * This class not only produces the global org.hibernate.SessionFactory reference in its static initializer;
 * it also hides the fact that it uses a static singleton
*/

package hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.icastle.Orders.model.OrdersVO;
import com.icastle.hotels.model.HotelVO;
import com.icastle.members.model.MembersVO;
import com.icastle.rooms.model.RoomsVO;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
    	// 註冊服務
    	Configuration cfg = new Configuration()
    			.addClass(OrdersVO.class).addResource("com/icastle/Orders/model/OrdersVO.hbm.xml")
    			.addClass(MembersVO.class).addResource("com/icastle/members/model/MembersVO.hbm.xml")
    			.addClass(HotelVO.class).addResource("com/icastle/hotels/model/HotelVO.hbm.xml")
    			.addClass(RoomsVO.class).addResource("com/icastle/rooms/model/RoomsVO.hbm.xml")
    			.configure();
    	ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
        try {
        	// 創建SessionFactory
			sessionFactory = cfg.buildSessionFactory(registry);
			System.out.println("成功建造工廠");
        } catch (Throwable ex) {
        	// Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}