package spring.oshare.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.oshare.dto.BoardDTO;
import spring.oshare.dto.CartDTO;
import spring.oshare.dto.DeclarationDTO;
import spring.oshare.dto.GradeDTO;
import spring.oshare.dto.LocationDTO;
import spring.oshare.dto.MemberDTO;
import spring.oshare.dto.MessageDTO;
import spring.oshare.dto.WishlistDTO;

@Repository
public class MyPageDAOImpl implements MyPageDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardDTO> salesItemList(String memberId) {
		return sqlSession.selectList("myPageMapper.salesItemList", memberId);
	}
	
	@Override
	public List<BoardDTO> rentalItemList(String memberId) {
		return sqlSession.selectList("myPageMapper.rentalItemList", memberId);
	}
	
	@Override
	public int insertGrade(GradeDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void selectGrade(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectAllDeliveryLocation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertDeliveryLocation(LocationDTO location) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertMessage(MessageDTO message) {
		return sqlSession.insert("sendMessageMapper.sendMessageInsert", message);
	}

	@Override
	public int deleteMessage(String[] messageNo) {
		int result =0;
		for(String messageNumber : messageNo){
			result =sqlSession.update("sendMessageMapper.sendMessageDelete", messageNumber);
		}
		return result;
	}

	@Override
	public List<MessageDTO> selectSenderMessage(String sender) {
		System.out.println("sender : " +sender);
		return sqlSession.selectList("sendMessageMapper.senedMessageSenderSelect",sender);
	}

	@Override
	public List<MessageDTO> selectReceiverMessage(String receiver) {
		System.out.println("recevier : "+receiver);
		return sqlSession.selectList("sendMessageMapper.senedMessageReceiverSelect",receiver);
	}
	
	@Override
	public int insertMemberGrade(GradeDTO dto) {
		return sqlSession.insert("myPageMapper.insertMemberGrade", dto);
	}
	
	/**
	 * 장바구니 리스트
	 */
	@Override
	public List<CartDTO> selectCart(String memberId) {
		return sqlSession.selectList("myPageMapper.cartSelect",memberId);
	}

	/**
	 * 장바구니 삭제
	 */
	@Override
	public int deleteCart(int boardNo) {
		return sqlSession.delete("myPageMapper.cartDelete", boardNo);
	}
	
	@Override
	public List<MemberDTO> adminAllUserSelect() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("myPageMapper.adminAllUserSelect");
	}

	@Override
	public List<MemberDTO> adminUserBlacklistSelect(int memberGrade) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("myPageMapper.adminUserBlacklistSelect", memberGrade);
	}

	@Override
	public int adminUserGradeChage(int[] memberNo, int memberGrade) {
		// TODO Auto-generated method stub
		Map<String, Integer> userMap = new HashMap<>();
		int adminUserGradeChageResult = 0;
		
		for(int UserMemberNo : memberNo){
		userMap.put("memberGrade", memberGrade);
		userMap.put("memberNo", UserMemberNo);
		adminUserGradeChageResult = sqlSession.update("myPageMapper.adminUserGradeChage",userMap);
		}
		return adminUserGradeChageResult;
	}
	
	/**
	 * 위시리스트 추가
	 * */
	@Override
	public int insertWishList(WishlistDTO dto) {
		return sqlSession.insert("myPageMapper.insertWishList", dto);
	}

	/**
	 * 위시리스트 삭제
	 * */
	@Override
	public int deleteWishList(WishlistDTO dto) {
		return sqlSession.delete("myPageMapper.deleteWishList", dto);
	}

	/**
	 * 위시리스트 조회
	 * */
	@Override
	public List<WishlistDTO> selectWishList(String memberId) {
		return sqlSession.selectList("myPageMapper.selectWIshList", memberId);
	}
	
	@Override
	public int declarationInsert(DeclarationDTO declarationDTO) {
		// TODO Auto-generated method stub
		return sqlSession.insert("myPageMapper.declarationInsert", declarationDTO);
	}

	@Override
	public int declarationUserGradeChage(int memberGrade, String memberId) {
	    Map<String,Object> declarationUserMap = new HashMap<>();
	    declarationUserMap.put("memberGrade", memberGrade);
	    declarationUserMap.put("memberId", memberId);
	    return sqlSession.update("myPageMapper.declarationUserGradeChage", declarationUserMap);
	}

	@Override
	public List<DeclarationDTO> reasonForFiling(String declarationReporter) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("myPageMapper.reasonForFiling", declarationReporter);
	}
}
