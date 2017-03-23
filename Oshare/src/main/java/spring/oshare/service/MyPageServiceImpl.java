package spring.oshare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.oshare.dao.MyPageDAO;
import spring.oshare.dto.BoardDTO;
import spring.oshare.dto.CartDTO;
import spring.oshare.dto.DeclarationDTO;
import spring.oshare.dto.GradeDTO;
import spring.oshare.dto.MemberDTO;
import spring.oshare.dto.MessageDTO;
import spring.oshare.dto.WishlistDTO;

@Service
public class MyPageServiceImpl implements MyPageService{
	
	@Autowired
	private MyPageDAO myPageDAO;

	@Override
	public List<BoardDTO> salesItemList(String memberId) {
		return myPageDAO.salesItemList(memberId);
	}

	@Override
	public List<BoardDTO> rentalItemList(String memeberId) {
		return myPageDAO.rentalItemList(memeberId);
	}
	
	@Override
	public String insertMessage(MessageDTO message) {
		if(myPageDAO.insertMessage(message)>0) {
			return message.getReceiver();
		}
		return "";
	}

	@Override
	public List<MessageDTO> selectMessage(String posts , String division) {
		List<MessageDTO> messageList;
		if(posts.equals("receiver")){
			messageList = myPageDAO.selectReceiverMessage(division);//수신자
		}else{	
			messageList = myPageDAO.selectSenderMessage(division);//송신자
		}
		return messageList;
	}

	@Override
	public int deleteMessage(String[] messageNo) {
		return myPageDAO.deleteMessage(messageNo);
	}
	
	@Override
	public int insertMemberGrade(GradeDTO dto) {
		return myPageDAO.insertMemberGrade(dto);
	}
	
	/**
	 * 장바구니 리스트
	 */
	@Override
	public List<CartDTO> selectCart(String memberId) {
		return myPageDAO.selectCart(memberId);
	}

	/**
	 * 장바구니 삭제
	 */
	@Override
	public int deleteCart(int boardNo) {
		return myPageDAO.deleteCart(boardNo);
	}
	
	
	@Override
	public List<MemberDTO> adminAllUserSelect() {
		// TODO Auto-generated method stub
		return myPageDAO.adminAllUserSelect();
	}

	@Override
	public List<MemberDTO> adminUserBlacklistSelect(int memberGrade) {
		// TODO Auto-generated method stub
		return myPageDAO.adminUserBlacklistSelect(memberGrade);
	}

	@Override
	public int adminUserGradeChage(int[] memberNo, int memberGrade) {
		// TODO Auto-generated method stub
		return myPageDAO.adminUserGradeChage(memberNo, memberGrade);
	}
	
	/**
	 * 위시리스트 추가
	 * */
	@Override
	public int insertWishList(WishlistDTO dto) {
		return myPageDAO.insertWishList(dto);
	}
	
	/**
	 * 위시리스트 삭제
	 * */
	@Override
	public int deleteWishList(WishlistDTO dto) {
		return myPageDAO.deleteWishList(dto);
	}

	/**
	 * 위시리스트 조회
	 * */
	@Override
	public List<WishlistDTO> selectWishList(String memberId) {
		return myPageDAO.selectWishList(memberId);
	}
	
	@Override
	public int declarationInsert(DeclarationDTO declarationDTO) {
		// TODO Auto-generated method stub
		return myPageDAO.declarationInsert(declarationDTO);
	}

	@Override
	public int declarationUserGradeChage(int memberGrade, String memberId) {
		// TODO Auto-generated method stub
		return myPageDAO.declarationUserGradeChage(memberGrade, memberId);
	}

	@Override
	public List<DeclarationDTO> reasonForFiling(String declarationReporter) {
		// TODO Auto-generated method stub
		return myPageDAO.reasonForFiling(declarationReporter);
	}
}
