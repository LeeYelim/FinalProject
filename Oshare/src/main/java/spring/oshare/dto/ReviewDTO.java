package spring.oshare.dto;

public class ReviewDTO {
	private int reviewNo; //후기 번호
	private int boardNo; //게시물 번호
	private String memberId; //사용자아이디
	private String contents; //내용
	private String regDate; //날짜 
	private String subject; //제목
	private int parentNo; //부모 번호
	
	
	public ReviewDTO() {	}
	public ReviewDTO(int reviewNo, int boardNo, String memberId, String contents, String regDate) {
		super();
		this.reviewNo = reviewNo;
		this.boardNo = boardNo;
		this.memberId = memberId;
		this.contents = contents;
		this.regDate = regDate;
	}
	
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getParentNo() {
		return parentNo;
	}
	public void setParentNo(int parentNo) {
		this.parentNo = parentNo;
	}
	
}
