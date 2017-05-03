import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.icastle.hotels.model.HotelDAOHibernate;
import com.icastle.hotels.model.HotelDAO_Interface;
import com.icastle.members.model.MembersDAO_interface;
import com.icastle.members.model.MembersHibernateDAO;
import com.icastle.members.model.MembersVO;
import com.icastle.rooms.model.RoomsDAO_interface;
import com.icastle.rooms.model.RoomsHibernateDAO;
import com.icastle.rooms.model.RoomsVO;

public class Test {

	public static void main(String[] args) {

//		MembersDAO_interface mh = new MembersHibernateDAO();
//		
//		List<MembersVO> members = mh.findByPrimaryKey("Sally@gmail.com");
//		
//		for(MembersVO result: members){
//			System.out.println(result.getAddr());
//			System.out.println(result.getCountry());
//			System.out.println(result.getEmail());
//			System.out.println(result.getGender());
//			System.out.println(result.getName());
//			System.out.println(result.getPassport());
//			System.out.println(result.getPersonId());
//			System.out.println(result.getPw());
//			System.out.println(result.getTel());
//			System.out.println(result.getMemberId());
//			System.out.println(result.getBdate());
//		}
		
//		HotelDAO_Interface hi = new HotelDAOHibernate();
//		
//		List<Integer> hotelId = hi.getId("德立莊酒店", "台北");
//		Integer result = hotelId.get(0);
//		
//		System.out.println(result);
		
		RoomsDAO_interface ri = new RoomsHibernateDAO();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		java.sql.Date start = null;
		java.sql.Date end = null;
		try {
			start = new java.sql.Date(sdf.parse("2017/6/2").getTime());
			end = new java.sql.Date(sdf.parse("2017/6/4").getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		RoomsVO room = ri.findRoom(1, 4, start, end, "雅緻");
		
		System.out.println(room);
		
	}

}
