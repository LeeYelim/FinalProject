package spring.oshare.service;

import java.util.List;

import spring.oshare.dto.ReservationDTO;
import spring.oshare.dto.SharingDTO;

public interface ReservationService{

	/**
	 * 예약 신청
	 * */
	int insertReservation(ReservationDTO reservation, SharingDTO sharing);
	
	/**
	 * 예약 취소
	 * */
	int deleteReservation(int reservationNo);
	
	/**
	 * 게시글에 해당하는 예약 날짜 조회
	 * */
	List<ReservationDTO> selectReservationByBoardNo(int boardNo);
	
	/**
	 * sharing 상태 변경
	 * ("오늘 날짜보다 sharing_start가 작고, 현재 상태가 대여 대기라면 sharing_state 대여중으로 변경")
	 * */
	int updateSharingState();
	
	/**
	 * 반납 신청(상태 : 반납, 파손, 분실)
	 * 반납 : reservation_end 오늘 날짜로 & 반납 신청으로 업데이트
	 * 파손 : reservation_end 오늘 날짜로 & 파손 업데이트
	 * 분실 : reservation_end 오늘 날짜로 & 분실 업데이트
	 * */
	int applyReturn(int sharingNo, String returnState);
	
	 /**
	   * 판매자)
	   * 완료 : 거래 완료로 업데이트
	   * 
	   * */
	int responseReturn(int sharingNo, String responseState);
	
	/**
	 * 청구하기 폼
	 * */
	SharingDTO demandPayment(int sharingNo);
	
	/**
	 * 거래 상태 변경
	 * */
	int updateTransactionState(int sharingNo, String state);
	
}
