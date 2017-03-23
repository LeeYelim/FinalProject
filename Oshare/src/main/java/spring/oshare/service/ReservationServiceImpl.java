package spring.oshare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.oshare.dao.ReservationDAO;
import spring.oshare.dto.ReservationDTO;
import spring.oshare.dto.SharingDTO;

@Service
public class ReservationServiceImpl implements ReservationService, Runnable{
	
	@Autowired
	private ReservationDAO dao;
	
	@Override
	public int insertReservation(ReservationDTO reservation, SharingDTO sharing) {
		return dao.insertReservation(reservation, sharing);
	}

	@Override
	public int deleteReservation(int reservationNo) {
		return dao.deleteReservation(reservationNo);
	}

	@Override
	public List<ReservationDTO> selectReservationByBoardNo(int boardNo) {
		return dao.selectReservationByBoardNo(boardNo);
	}

	@Override
	public int updateSharingState() {
		return dao.updateSharingState();
	}

	@Override
	public void run() {
		while(true) {
			updateSharingState();
			try {
				Thread.sleep(10000000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int applyReturn(int sharingNo, String returnState) {
		return dao.applyReturn(sharingNo, returnState);
	}

	@Override
	public int responseReturn(int sharingNo, String responseState) {
		return dao.responseReturn(sharingNo, responseState);
	}

	@Override
	public SharingDTO demandPayment(int sharingNo) {
		return dao.demandPayment(sharingNo);
	}
	
	@Override
	public int updateTransactionState(int sharingNo, String state) {
		return dao.updateTransactionState(sharingNo, state);
	}	
}
