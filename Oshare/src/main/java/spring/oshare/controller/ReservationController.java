package spring.oshare.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.oshare.dto.GradeDTO;
import spring.oshare.dto.MemberDTO;
import spring.oshare.dto.ReservationDTO;
import spring.oshare.dto.SharingDTO;
import spring.oshare.service.MyPageService;
import spring.oshare.service.ReservationService;

@Controller
@RequestMapping("reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private MyPageService mypageService;
	
	@RequestMapping("selectByBoardNo")
	@ResponseBody
	public List<ReservationDTO> selectReservationByBoardNo(int boardNo) {
		return reservationService.selectReservationByBoardNo(boardNo);
	}
	
	/**
	   *  일정 등록하기
	   * */
	  @RequestMapping("apply")
	  public String write(int boardNo, String start, String end, SharingDTO sharing){
		  System.out.println("========boardNo : "+boardNo +"======== s : "+start + "======== e : " + end); // 시작날이랑 끝나는 날 동일하면 end가 null이 되버림
		  System.out.println("===========boardNo : " + sharing.getBoardNo());
		  System.out.println("===========buyerId : " + sharing.getBuyerId());
		  System.out.println("===========sellerId : " + sharing.getSellerId());
		  System.out.println("===========productCount : " + sharing.getProductCount());
		  System.out.println("===========totalPrice : " + sharing.getTotalPrice());  
		  
		  sharing.setBoardNo(boardNo);
		  sharing.setSharingStart(start);
		  sharing.setSharingEnd(end);
		  
		  if(reservationService.insertReservation(new ReservationDTO(boardNo, start, end), sharing)>1) { 
			  return "redirect:/mypage/rentalItem?boardNo="+boardNo;
		  } 
		  return "error/errorMessage";
	  }
	  
	  
	  /**
	   * 예약 취소하기 
	   * */
	  @RequestMapping("deleteReservation")
	  public String deleteReservation(int reservationNo) {
		  System.out.println("==============================reservationNo : " + reservationNo);
		  if(reservationService.deleteReservation(reservationNo)>1) {
			  return "redirect:/mypage/rentalItem";
		  } 
		  return "error/errorMessage";
	  }
	
	  /**
	   * 대여자)
	   * * 반납 신청(상태 : 반납, 파손, 분실)
	   * * 반납 : reservation_end 오늘 날짜로 & 반납 신청으로 업데이트
	   * * 파손 : reservation_end 오늘 날짜로 & 파손 업데이트
	   * * 분실 : reservation_end 오늘 날짜로 & 분실 업데이트
	   * * */
	  @RequestMapping("applyReturn")
	  public String applyReturn(HttpSession session, int sharingNo, String returnState, GradeDTO dto) {
		  System.out.println("=========sharingNo" + sharingNo + "===================rentalItemList" + returnState);
		  String id = (String)session.getAttribute("loginMemberId");
		  System.out.println("===========================" + dto.getMemberReview() + dto.getMemberPoint() + id + dto.getSellerId());
		  int re1 = reservationService.applyReturn(sharingNo, returnState);
		  dto.setMemberId(id);
		  int re2 = mypageService.insertMemberGrade(dto);
		  
		  if((re1+re2) > 1) {
			  return "redirect:/mypage/rentalItem";
		  }
		  return "error/errorMessage";
	  }
	  
	  /**
	   * 판매자)
	   * 완료 : 거래 완료로 업데이트
	   * */
	  @RequestMapping("responseReturn")
	  public String responseReturn(int sharingNum, String responseState) {
		System.out.println("============================sharingNum : " + sharingNum);
		System.out.println("============================responseState : " + responseState);
		if(reservationService.responseReturn(sharingNum, responseState)>0) {
			return "redirect:/mypage/salesItem";
		}
		return "error/errorMessage";
	  }
	  
	  /**
	   * 청구하기
	   * */
	 @RequestMapping("selectDemandPayment")
	 @ResponseBody
	  public SharingDTO demandPayment(int sharingNum) {
		 System.out.println("============================sharingNo : " + sharingNum);
		 SharingDTO dto =  reservationService.demandPayment(sharingNum);
		 System.out.println("getSharingNo : " + dto.getSharingNo());
		 System.out.println("getSharingStart : " + dto.getSharingStart());
		 System.out.println("getSharingEnd : " + dto.getSharingEnd());
		 System.out.println("getSharingDays : " + dto.getSharingDays());
		 System.out.println("getOverDue : " + dto.getOverDue());
		 System.out.println("getOverDueFee : " + dto.getOverDueFee());
		 System.out.println("getTotalPrice : " + dto.getTotalPrice());
		 System.out.println("getPrice : " + dto.getBoard().getPrice());
		 return dto;
		
	  }
	 
	 @RequestMapping("updateTransactionState")
	 public String updateTransactionState(int sharingNum, String state) {
		 if(reservationService.updateTransactionState(sharingNum, state)>0) {
				return "redirect:/mypage/rentalItem";
			}
			return "error/errorMessage";
	 }
	  
	  
}
