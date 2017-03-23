package spring.oshare.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spring.oshare.dto.BoardDTO;
import spring.oshare.dto.CartDTO;
import spring.oshare.dto.DeclarationDTO;
import spring.oshare.dto.MemberDTO;
import spring.oshare.dto.MessageDTO;
import spring.oshare.dto.WishlistDTO;
import spring.oshare.service.MemberService;
import spring.oshare.service.MyPageService;

/**
 * 마이페이지 - 위시리스트 - 대여중인목록 - 판매중인목록 - 쪽지함 - 결제관리 - 회원정보수정 - 장바구니 - 어드민[회원 관리]
 */
@Controller
@RequestMapping("mypage")
public class MyPageController {

	@Autowired
	private MyPageService myPageService;

	@Autowired
	private MemberService memberService;

	/**
	 * 빌려준 물품 목록
	 */
	// @RequestMapping("salesItem")
	/*
	 * public String salesItem(){ return "mypage/salesItem/saleItemList"; }
	 */

	@RequestMapping("salesItem")
	public ModelAndView salesItem(HttpSession session) {
		// 회원 ID가져오기
		String memberId = (String) session.getAttribute("loginMemberId");

		// ID에 해당하는 판매(빌려준) 목록 조회
		List<BoardDTO> saleslist = myPageService.salesItemList(memberId);
		/*
		 * for(BoardDTO board : saleslist) { List<SharingDTO> sharings =
		 * board.getSharing(); for(SharingDTO sharing : sharings) { MemberDTO
		 * member = sharing.getMember(); System.out.println("게시물 번호 : " +
		 * board.getBoardNo() + ", 제품명 : " + board.getProductName() +
		 * ", 쉐어링 번호 : " + sharing.getSharingNo() + ", 판매자 id : " +
		 * sharing.getSellerId() + ", 대여자 id : " + sharing.getBuyerId() +
		 * ", 대여자 폰번호 : " + member.getMemberPhone() + ", 쉐어링 시작 : " +
		 * sharing.getSharingStart() + ", 쉐어링 종료 : " + sharing.getSharingEnd() +
		 * ", 거래 상태 : " + sharing.getTransactionState()); } }
		 */

		ModelAndView mv = new ModelAndView();
		mv.addObject("saleslist", saleslist);
		mv.setViewName("mypage/salesItem/saleItemList");
		return mv;
	}

	/**
	 * 빌린 물품 목록
	 */
	@RequestMapping("rentalItem")
	public ModelAndView rentalItemList(HttpSession session) {
		// 회원 ID가져오기
		String memberId = (String) session.getAttribute("loginMemberId");

		// ID에 해당하는 판매(빌려준) 목록 조회
		List<BoardDTO> rentallist = myPageService.rentalItemList(memberId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("rentallist", rentallist);
		mv.setViewName("mypage/rentalItem/rentalItemList");
		return mv;
	}

	/**
	 * 메세지 보관함 목록
	 */
	@RequestMapping("message")
	public String messageBox(HttpSession session, HttpServletRequest request) throws Exception {
		if (session.getAttribute("loginMemberId") == null) {
			request.setAttribute("errorMsg", "로그인하고 오십시오");
			throw new Exception();
		}
		return "mypage/messageBox/sendMessage";
	}

	/**
	 * 쪽지 보내기
	 */
	/*
	 * @RequestMapping("sendMessageInsert") public String
	 * insertMessage(MessageDTO message, HttpServletRequest request){
	 * myPageService.insertMessage(message); return "redirect:message"; }
	 */

	/**
	 * 상세보기 쪽지 보내기
	 */
	@RequestMapping("DetailSendMessageInsert")
	@ResponseBody
	public String DetailInsertMessage(MessageDTO message, HttpServletRequest request) {
		System.out.println("detail message : " + message.getReceiver() + message.getSender() + message.getContent());
		return myPageService.insertMessage(message);
	}

	/**
	 * 수신 송신
	 */
	@RequestMapping("sendMessageSelect")
	@ResponseBody
	public List<MessageDTO> selectMessage(HttpServletRequest request, String posts, String division) {
		List<MessageDTO> list = myPageService.selectMessage(posts, division);
		return list;

	}

	/**
	 * 쪽지 삭제
	 */
	@RequestMapping("sendMessageDelete")
	@ResponseBody
	public int deleteMessage(String[] messageNo) {
		return myPageService.deleteMessage(messageNo);
	}

	/**
	 * 장바구니
	 */
	@RequestMapping("shoppingBasket")
	public String shoppingBasketForm() {
		return "shoppingBasket/shoppingBasket";
	}

	/**
	 * 어드민[회원 관리]
	 */
	@RequestMapping("userManagement")
	@ResponseBody
	public ModelAndView userManagementForm(HttpSession session, HttpServletRequest request) throws Exception {
		List<MemberDTO> allUserList = myPageService.adminAllUserSelect();
		ModelAndView mv = new ModelAndView();
		mv.addObject("allUserMemberDTO", allUserList);
		mv.setViewName("mypage/admin/userManagement");
		if (session.getAttribute("loginMemberId") == null) {
			request.setAttribute("errorMsg", "접근금지 구역입니다 돌아가주십시오");
			throw new Exception();
		} else if (!session.getAttribute("loginMemberId").equals("admin")) {
			request.setAttribute("errorMsg", "접근금지 구역입니다 돌아가주십시오");
			throw new Exception();
		}

		return mv;

	}

	/**
	 * 어드민 [신고 정지회원 관리]
	 */
	@RequestMapping("userBlacklist")
	@ResponseBody
	public List<MemberDTO> adminUserBlacklistSelect(int memberGrade) {
		return myPageService.adminUserBlacklistSelect(memberGrade);
	}

	/**
	 * 어드민 [버튼]
	 */
	@RequestMapping("userBtn")
	@ResponseBody
	public int adminUserGradeChage(int[] memberNo, int memberGrade) {

		return myPageService.adminUserGradeChage(memberNo, memberGrade);
	}

	/**
	 * 회원정보 수정폼 이동
	 */
	@RequestMapping("updateMemberForm")
	public ModelAndView updateMemberForm(HttpServletRequest request) {

		// @RequestMapping("loginCheck") 에 저장된 session (memberId) 값을 받아서
		// 받은 데이터를 가지고 수정페이지로 이동

		// session
		HttpSession session = request.getSession();
		String memberId = (String) session.getAttribute("loginMemberId");

		// call
		MemberDTO dbMemberDTO = memberService.selectByMember(memberId);

		// '회원수정' 클릭시 나타나는 목록
		String dbmemberId = dbMemberDTO.getMemberId();
		String dbmemberName = dbMemberDTO.getMemberName();
		String dbmemberAddr = dbMemberDTO.getMemberAddr();
		String dbmemberPhone = dbMemberDTO.getMemberPhone();
		String dbmemberAccount = dbMemberDTO.getMemberAccount();
		int dbmemberValidYear = dbMemberDTO.getMemberValidYear();

		// 목록들을 문자열로 저장
		String dbmember = dbmemberId + "_" + dbmemberName + "_" + dbmemberAddr + "_" + dbmemberPhone + "_"
				+ dbmemberAccount + "_" + dbmemberValidYear;

		// 문자열을 '_' 기준으로 배열로 저장
		String[] splitMemberDTO = dbmember.split("_");

		// updateMember.jsp 에 value를 배열로 바로 입력
		request.setAttribute("splitMemberDTO", splitMemberDTO);

		return new ModelAndView("mypage/updateMember/updateMemberForm");

	}
	
	/**
	 * 회원탈퇴 전 로그인체크폼 이동
	 */
	@RequestMapping("deleteLoginForm")
	public String loginForm() {
		return "mypage/deleteMember/deleteLoginForm";
	}

	/**
	 * 장바구니 리스트
	 */
	@RequestMapping("cartList")
	public ModelAndView selectCart(HttpSession session) {
		String memberId = (String) session.getAttribute("loginMemberId");

		List<CartDTO> cartList = myPageService.selectCart(memberId);

		int total = 0;
		for (CartDTO dto : cartList) {
			total += dto.getCartPrice();
		}

		ModelAndView mv = new ModelAndView("shoppingBasket/shoppingBasket", "cartList", cartList);
		mv.addObject("total", total);

		return mv;
	}

	/**
	 * 장바구니 삭제
	 */
	public int deleteCart(int boardNo) {

		return myPageService.deleteCart(boardNo);
	}

	/**
	 * 위시리스트 추가
	 */
	@RequestMapping("insertWishList")
	@ResponseBody
	public int insertWishList(HttpSession session, WishlistDTO dto) {
		String userId = (String) session.getAttribute("loginMemberId");
		dto.setMemberId(userId);
		System.out.println("=================================" + dto.getBoardNo());
		return myPageService.insertWishList(dto);
	}

	/**
	 * 위시리스트 삭제
	 */
	@RequestMapping("deleteWishList")
	@ResponseBody
	public int deleteWishList(HttpSession session, WishlistDTO dto) {
		String userId = (String) session.getAttribute("loginMemberId");
		dto.setMemberId(userId);
		System.out.println("=================================" + dto.getBoardNo());
		return myPageService.deleteWishList(dto);
	}

	/**
	 * 위시리스트 이동 및 조회
	 */
	@RequestMapping("wishList")
	public ModelAndView selectWishList(HttpSession session) {
		String memberId = (String) session.getAttribute("loginMemberId");
		List<WishlistDTO> list = myPageService.selectWishList(memberId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("mypage/wishlist/wishList");
		return mv;
	}

	/**
	 * 신고하기 , 판매자 등급 변경
	 */
	@RequestMapping("declarationInsert")
	@ResponseBody
	public int declarationInsert(DeclarationDTO declarationDTO) {
		int declarationInsertResult = myPageService.declarationInsert(declarationDTO);
		myPageService.declarationUserGradeChage(1, declarationDTO.getDeclarationReporter());
		return declarationInsertResult;
	}

	/**
	 * 신고/정지 사유
	 */
	@RequestMapping("reasonForFiling")
	@ResponseBody
	public List<DeclarationDTO> reasonForFiling(String declarationReporter) {
		return myPageService.reasonForFiling(declarationReporter);
	}
}
