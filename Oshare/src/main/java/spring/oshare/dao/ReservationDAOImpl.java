package spring.oshare.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.oshare.dto.ReservationDTO;
import spring.oshare.dto.SharingDTO;

@Repository
public class ReservationDAOImpl implements ReservationDAO{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertReservation(ReservationDTO reservation, SharingDTO sharing) {
		int resultReservation = sqlSession.insert("reservationMapper.insertReservation", reservation);
		int resultSharing = sqlSession.insert("reservationMapper.insertSharing", sharing);
		return resultReservation + resultSharing;
	}

	@Override
	public int deleteReservation(int reservationNo) {
		int deleteRev = sqlSession.delete("reservationMapper.deleteReservation", reservationNo);
		int deleteSharing = sqlSession.delete("reservationMapper.deleteSharing", reservationNo);
		return deleteRev + deleteSharing;
	}

	@Override
	public void selectAllSharing(String userId, String boardType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReservationDTO> selectReservationByBoardNo(int boardNo) {
		return sqlSession.selectList("reservationMapper.selectReservationByBoardNo", boardNo);
	}

	@Override
	public int updateSharingState() {
		return sqlSession.update("myPageMapper.updateSharingState");
	}

	@Override
	public int applyReturn(int sharingNo, String returnState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sharingNo", sharingNo);
		map.put("returnState", returnState.trim());
		return sqlSession.update("reservationMapper.applyReturn", map);
	}

	@Override
	public int responseReturn(int sharingNo, String responseState) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sharingNo", sharingNo);
		map.put("responseState", responseState.trim());
		return sqlSession.update("reservationMapper.responseReturn", map);
	}

	@Override
	public SharingDTO demandPayment(int sharingNo) {
		System.out.println("=============================" + sharingNo);
		SharingDTO sharing =  sqlSession.selectOne("reservationMapper.selectDemandPayment", sharingNo);
		System.out.println(sharing + "=============" + sharing.getOverDue() + "===========" + sharing.getOverDueFee());
		return sharing;
	}
	
	@Override
	public int updateTransactionState(int sharingNo, String state) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sharingNo", sharingNo);
		map.put("state", state);
		return sqlSession.update("reservationMapper.updateTransactionState", map);
	}
}
