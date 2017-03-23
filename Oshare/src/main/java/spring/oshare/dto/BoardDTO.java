package spring.oshare.dto;


import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class BoardDTO {
	private int boardNo; // 게시물 번호
	private String productName; // 물품명
	private String memberId;	// 등록한 계정ID
	private String category; // 카테고리
	private int price; // 1일당 대여가격
	private String condition; // 물품 상태
	private String boardType; // 게시물 유형(쉐어링 or 렌탈)
	private String dealingType; // 거래 종류(택배 or 직거래)
	private String detail; // 상품 상세
	private String regDate; // 글 등록 날짜
	private int viewCount; // 조회수
	private List<SharingDTO> sharing;
	
	// board - member join 1:1 association
	private MemberDTO memberDTO;
	
	private ReviewDTO reviewDTO;
	
	private MultipartFile file; // 썸네일 대표 이미지
	private String filePath;
	
	// Default Constructor
	public BoardDTO() {}
	// Constructor for inserting Product
	public BoardDTO(String productName, String category, String memberId, int price, String condition, String boardType, String detail, String filePath) {
		this.productName = productName;
		this.category = category;
		this.memberId = memberId;
		this.price = price;
		this.condition = condition;
		this.boardType = boardType;
		this.detail = detail;
		this.filePath = filePath;
		this.regDate = regDate;
		this.viewCount = viewCount;
	}
	

		public BoardDTO(String productName,  String memberId, int price, String condition,  String detail) {
			this.productName = productName;
			this.memberId = memberId;
			this.price = price;
			this.condition = condition;
			this.detail = detail;
			this.filePath = filePath;
		}
	
	
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getDealingType() {
		return dealingType;
	}
	public void setDealingType(String dealingType) {
		this.dealingType = dealingType;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public List<SharingDTO> getSharing() {
		return sharing;
	}
	public void setSharing(List<SharingDTO> sharing) {
		this.sharing = sharing;
	}
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}
	public void setMemberDTO(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public ReviewDTO getReviewDTO() {
		return reviewDTO;
	}
	public void setReviewDTO(ReviewDTO reviewDTO) {
		this.reviewDTO = reviewDTO;
	}
	@Override
	public String toString() {
		return "BoardDTO [boardNo=" + boardNo + ", productName=" + productName + ", memberId=" + memberId
				+ ", category=" + category + ", price=" + price + ", condition=" + condition + ", boardType="
				+ boardType + ", dealingType=" + dealingType + ", detail=" + detail + ", regDate=" + regDate
				+ ", viewCount=" + viewCount + ", sharing=" + sharing + ", memberDTO=" + memberDTO + ", file=" + file
				+ ", filePath=" + filePath + "]";
	}
		
	
}
