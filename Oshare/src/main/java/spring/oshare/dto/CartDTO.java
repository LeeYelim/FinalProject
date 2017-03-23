package spring.oshare.dto;

public class CartDTO {
	
	private int cartNo;
	private int boardNo;
	private String memberId;
	private String cartStart;
	private String cartEnd;
	private int cartPrice;
	private String productName;
	
	private String filePath;
	
	public CartDTO() {	}
	
	public CartDTO(int cartNo, int boardNo, String memberId, String cartStart,
			String cartEnd, int cartPrice, String productName) {
		super();
		this.cartNo = cartNo;
		this.boardNo = boardNo;
		this.memberId = memberId;
		this.cartStart = cartStart;
		this.cartEnd = cartEnd;
		this.cartPrice = cartPrice;
		this.productName = productName;
	}


	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
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

	public String getCartStart() {
		return cartStart;
	}

	public void setCartStart(String cartStart) {
		this.cartStart = cartStart;
	}

	public String getCartEnd() {
		return cartEnd;
	}

	public void setCartEnd(String cartEnd) {
		this.cartEnd = cartEnd;
	}

	public int getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	
	
	
	
}
