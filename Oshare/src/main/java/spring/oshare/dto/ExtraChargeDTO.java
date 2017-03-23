package spring.oshare.dto;

import java.util.Date;

public class ExtraChargeDTO {

	private int chargeNo; // 추가 금액 번호
	private int charge; // 추가 금액
	private String chargeReason; // 추가 금액 이유
	private Date regDate; // 추가 금액 청구 날짜
	
	public int getChargeNo() {
		return chargeNo;
	}
	public void setChargeNo(int chargeNo) {
		this.chargeNo = chargeNo;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public String getChargeReason() {
		return chargeReason;
	}
	public void setChargeReason(String chargeReason) {
		this.chargeReason = chargeReason;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
