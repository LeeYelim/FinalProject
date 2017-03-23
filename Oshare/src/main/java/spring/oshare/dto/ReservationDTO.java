package spring.oshare.dto;

public class ReservationDTO {

	private int reservationNo; // 예약 번호
	private int boardNo; // 게시글 번호
	private String startDate; // 예약 시작일 
	private String endDate; // 예약 종료일
	private String regDate; // 예약 등록일
	
	public ReservationDTO() {	}
	public ReservationDTO(int boardNo, String startDate, String endDate) {
		this.boardNo = boardNo;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public ReservationDTO(int reservationNo, int boardNo, String startDate, String endDate, String regDate) {
		super();
		this.reservationNo = reservationNo;
		this.boardNo = boardNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.regDate = regDate;
	}


	public int getReservationNo() {
		return reservationNo;
	}
	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
