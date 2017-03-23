package spring.oshare.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.oshare.dto.BoardDTO;
import spring.oshare.dto.CartDTO;
import spring.oshare.dto.CommentDTO;
import spring.oshare.dto.GradeDTO;
import spring.oshare.dto.ReviewDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertBoard(BoardDTO board) {
		System.out.println("===="+board.toString());
		return sqlSession.insert("boardMapper.insertBoard", board);
	}

	@Override
	public int selectBoardSeqNo() {
		return sqlSession.selectOne("boardMapper.selectBoardSeqNo");
	}

	@Override
	public void updateViewCount(int boardNo) {
		sqlSession.update("boardMapper.updateViewCount", boardNo);
	}
	
	@Override
	public BoardDTO selectByBoardNo(int boardNo) {
		return sqlSession.selectOne("boardMapper.selectByBoardNo", boardNo);
	}
	
	@Override
	public List<BoardDTO> pageList(Map<String, Object> map  , String productCategory) {
		Map<String , String> sharingMap = new HashMap<String, String>();
		sharingMap.put("boardType", "sharing");
		sharingMap.put("productCategory" , productCategory);
		 
		return sqlSession.selectList("boardMapper.selectList", sharingMap , new RowBounds((Integer)map.get("start"), 12));
	}
	
	@Override
	public List<BoardDTO> pageRentalList(Map<String, Object> map , String productCategory) {
		Map<String , String> rentalMap = new HashMap<String, String>();
		rentalMap.put("boardType", "rental");
	
			rentalMap.put("productCategory" , productCategory);

		return sqlSession.selectList("boardMapper.selectRentalList", rentalMap, new RowBounds((Integer)map.get("start"), 12));
	}

	@Override
	public int getBoardCount(Map<String, Object> map , String boardType , String productCategory) {
		Map<String,String> boardListCount = new HashMap<>();
		boardListCount.put("boardType", boardType);
		
			boardListCount.put("productCategory" , productCategory);
		
		return sqlSession.selectOne("boardMapper.selectCount" , boardListCount);
	}
	
	@Override
	public BoardDTO detailBoard(int boardNo) {
		return sqlSession.selectOne("boardMapper.boardDetail", boardNo);
	}
	
	@Override
	public void selectAll(String boardType) {
		
	}
	
	@Override
	public int updateBoard(BoardDTO board) {
		// TODO Auto-generated method stub
		System.out.println("LYL  : " +  board.getBoardNo() + " , "+ board.getPrice() + board.getCondition()+ board.getProductName()+ board.getDetail()+ board.getMemberId());
		return sqlSession.update("boardMapper.boardUpdate", board);
	}

	@Override
	public int deleteBoard(int boardNo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("boardMapper.boardDelete", boardNo);
	}

	@Override
	public void selectByCategory(String boardType, String category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectByDetailInfo(String category, String productName, String loc, String startDate, String endDate) {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * 댓글생성
	 * if : 첫번째글
	 * else : 이후 댓글
	 */
	@Override
	public int insertComment(CommentDTO comment) {
		if(comment.getParentCommentNo()==0){
			return sqlSession.insert("CommentMapper.pinsert", comment);
		}else{
			return sqlSession.insert("CommentMapper.insert", comment);
		}
	}
	
	/*
	 * 댓글삭제
	 */
	@Override
	public int deleteComment(CommentDTO commentDTO) {
		
		return sqlSession.delete("CommentMapper.delete", commentDTO);
	}
	
	/*
	 * 전체 댓글리스트
	 */
	@Override
	public List<CommentDTO> selectAllComments(int boardNo) {
		List<CommentDTO> list = sqlSession.selectList("CommentMapper.selectAll", boardNo);
		return list ;
	}

	/*
	 * 후기 생성
	 * if : 첫번째 글
	 * else : 이후 후기
	 */
	@Override
	public int insertReview(ReviewDTO review) {
		if(review.getParentNo()==0){
			return sqlSession.delete("ReviewMapper.reviewPinsert", review);
		}else{
			return sqlSession.delete("ReviewMapper.reviewinsert", review);
		}
	}

	/*
	 * 후기삭제
	 */
	@Override
	public int deleteReview(ReviewDTO reviewDTO) {
		
		return sqlSession.delete("ReviewMapper.reviewdelete", reviewDTO);
	}
	
	/*
	 * 후기 전체리스트
	 */
	@Override
	public List<ReviewDTO> selectAllReviews(int boardNo) {
		List<ReviewDTO> list = sqlSession.selectList("ReviewMapper.reviewSelectAll", boardNo);
		return list;
	}


	@Override
	public int insertWishList(String boardNo, String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<GradeDTO> boardDetailSaleReview(int boardNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.boardDetailSaleReview",boardNo);
	}

	@Override
	public List<BoardDTO> boardDetailSaleList(String memberId) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("boardMapper.boardDetailSaleList",memberId);
	}

	@Override
	public List<BoardDTO> selectTopList(String boardType) {
		return sqlSession.selectList("boardMapper.selectTopList", boardType);
	}
	
	/**
	 * 장바구니 추가
	 */
	@Override
	public int insertCart(CartDTO cartDTO) {
		return sqlSession.insert("boardMapper.cartInsert", cartDTO);
	}
	

	@Override
	public GradeDTO boardDetailSaleGrade(String sellerId) {
		
		return sqlSession.selectOne("boardMapper.boardDetailSaleGrade",sellerId);
	}
	
	/**
	 * 물품검색
	 */
	@Override
	public List<BoardDTO> searchBoard(String productName) {
		return sqlSession.selectList("boardMapper.searchBoard", productName);
	}
	
	/**
	 *  검색 게시글 갯수
	 */
	@Override
	public int getBoardSearchCount(Map<String, Object> map) {
		return sqlSession.selectOne("boardMapper.selectSearchCount", map);
	}


}
