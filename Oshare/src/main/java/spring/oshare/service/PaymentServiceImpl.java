package spring.oshare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.oshare.dao.PaymentDAO;
import spring.oshare.dto.ExtraChargeDTO;
import spring.oshare.dto.SharingDTO;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDAO dao; 
	
	@Override
	public int insertExtraCharge(ExtraChargeDTO dto) {
		return dao.insertExtraCharge(dto);
	}

	@Override
	public SharingDTO selectExtraChargeInfo(int sharingNo) {
		return dao.selectExtraChargeInfo(sharingNo);
	}

}
