package spring.oshare.dao;

import spring.oshare.dto.ExtraChargeDTO;
import spring.oshare.dto.SharingDTO;

public interface PaymentDAO {
	
	/**
	 * 추가 금액 결제
	 * */
	int insertExtraCharge(ExtraChargeDTO dto);
	
	/**
	 * 추가금 정보 조회
	 * */
	SharingDTO selectExtraChargeInfo(int sharingNo);
}
