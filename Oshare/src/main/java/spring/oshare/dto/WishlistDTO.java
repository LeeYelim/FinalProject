package spring.oshare.dto;

public class WishlistDTO {
	private int wishlistNo;
	private int boardNo;
	private String memberId;
	private BoardDTO board;
	
	public WishlistDTO() {	}
	public WishlistDTO(int wishlistNo, int boardNo, String memberId) {
		super();
		this.wishlistNo = wishlistNo;
		this.boardNo = boardNo;
		this.memberId = memberId;
	}
	
	public int getWishlistNo() {
		return wishlistNo;
	}
	public void setWishlistNo(int wishlistNo) {
		this.wishlistNo = wishlistNo;
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
	public BoardDTO getBoard() {
		return board;
	}
	public void setBoard(BoardDTO board) {
		this.board = board;
	}
	
}
