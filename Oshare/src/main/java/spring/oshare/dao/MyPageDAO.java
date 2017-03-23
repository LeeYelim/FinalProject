package spring.oshare.dao;

import java.util.List;

import spring.oshare.dto.BoardDTO;
import spring.oshare.dto.CartDTO;
import spring.oshare.dto.DeclarationDTO;
import spring.oshare.dto.GradeDTO;
import spring.oshare.dto.LocationDTO;
import spring.oshare.dto.MemberDTO;
import spring.oshare.dto.MessageDTO;
import spring.oshare.dto.WishlistDTO;

public interface MyPageDAO {

	/**
	 * 빌려준 물품 목록
	 */
	public List<BoardDTO> salesItemList(String memberId);
	
	/**
	 * 대여한 물품 목록
	 * */
	public List<BoardDTO> rentalItemList(String memberId);
	
	/**
	 * 판매자 평가
	 * */
	int insertGrade(GradeDTO dto);
	
	/**
	 * 평가 내역 조회
	 * */
	void selectGrade(String userId);
	
	/**
	 * 배송지 조회
	 * */
	void selectAllDeliveryLocation();
	
	/**
	 * 배송지 추가
	 * */
	int insertDeliveryLocation(LocationDTO location);
	
	/**
	 * 쪽지 보내기
	 * */
	int insertMessage(MessageDTO message);
	
	/**
	 * 쪽지 삭제
	 * */
	int deleteMessage(String[] messageNo);
	
	/**
	 * 송신자 쪽지 조회
	 * */
	List<MessageDTO> selectSenderMessage(String sender);
	
	/**
	 * 수신자 쪽지 조회
	 * */
	List<MessageDTO> selectReceiverMessage(String receiver);

	/**
	 * 별점(평가) 삽입
	 * */
	int insertMemberGrade(GradeDTO dto);
	
	/**
	 * 장바구니 리스트
	 */
	List<CartDTO> selectCart(String memberId);

	/**
	 * 장바구니 삭제
	 */
	int deleteCart(int boardNo);
	
	
	/**
	 * 관리자 회원관리 전체회원
	 * */
	List<MemberDTO> adminAllUserSelect();
	
	/**
	 * 관리자 신고 정지회원
	 * */
	List<MemberDTO> adminUserBlacklistSelect(int memberGrade);
	
	/**
	 * 관리자 회원 계정 등급 변경
	 * */
	int adminUserGradeChage(int[] memberNo ,  int memberGrade);
	
	/**
	 * 위시리스트 삽입
	 * */
	int insertWishList(WishlistDTO dto);
	
	/**
	 * 위시리스트 삭제
	 * */
	int deleteWishList(WishlistDTO dto);
	
	/**
	 * 위시리스트 조회
	 * */
	List<WishlistDTO> selectWishList(String memberId);
	
	/**
	 * 판매자 신고하기;
	 * */
	int declarationInsert(DeclarationDTO declarationDTO);

	/**
	 * 판매자 신고 회원등급 변경
	 * */
	int declarationUserGradeChage(int memberGrade , String memberId);
	
	/**
	 * 신고/정지 사유 
	 * */
	List<DeclarationDTO> reasonForFiling(String declarationReporter);
}
