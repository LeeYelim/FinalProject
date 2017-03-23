package spring.oshare.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import spring.oshare.dto.BoardDTO;
import spring.oshare.dto.CartDTO;
import spring.oshare.dto.CommentDTO;
import spring.oshare.dto.GradeDTO;
import spring.oshare.dto.ReviewDTO;
import spring.oshare.dto.WishlistDTO;
import spring.oshare.service.BoardService;
import spring.oshare.service.MyPageService;
import spring.oshare.util.PagingUtil;

/**
 * BoardController - rental board - sharing board
 */
@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MyPageService myPageService;

	// 페이지 하나당 게시글 수 
	private int pageSize = 12;
	// pagination block count
	private int blockCount = 3;

	/**
	 * Sharing 게시판 이동
	 */
	@RequestMapping("goodsList")
	public ModelAndView goodsListForm(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
			@RequestParam(value = "keyField", defaultValue = "") String keyField,
			@RequestParam(value = "keyWord", defaultValue = "") String keyWord,
			@RequestParam(value = "searchBar",defaultValue = "") String searchBar,
			String productCategory,
			HttpServletRequest request) {
		int count=0;
		String pagingHtml = "";
		String path = request.getSession().getServletContext().getContextPath() + "/board/goodsList";
		List<BoardDTO> list = null;
		
		System.out.println("@@@@@@@@@@@@"+searchBar);
		if(searchBar.equals("")){

			HashMap<String, Object> map = new HashMap<String, Object>();
			//map.put("keyField", keyField);
			//map.put("keyWord", keyWord);
	
			// 총 글의 개수 또는 검색된 글의 개수
			count = boardService.getBoardCount(map , "sharing" , productCategory);
	
			
			// 페이징 처리
			//PagingUtil page = new PagingUtil(keyField, keyWord, currentPage, count, pageSize, blockCount, "board/goodsList");
			PagingUtil page = new PagingUtil(null, null, currentPage, count, pageSize, blockCount, path);
	
			pagingHtml = page.getPagingHtml().toString();
	
			map.put("start", page.getStartCount()-1);
			map.put("end", page.getEndCount());
	
			if (count > 0) {
				list = boardService.pageList(map , productCategory);
			} else {
				list = Collections.emptyList();
			}
		}else{
			
			System.out.println("@@@@@@@@@@@@"+searchBar);
			HashMap<String, Object> map = new HashMap<String, Object>();
			//map.put("keyField", keyField);
			
			map.put("keyWord", searchBar);
	

			count = boardService.getBoardSearchCount(map);
			// 페이징 처리
			//PagingUtil page = new PagingUtil(keyField, keyWord, currentPage, count, pageSize, blockCount, "board/goodsList");
			PagingUtil page = new PagingUtil(null, searchBar, currentPage, count, pageSize, blockCount, path);
	
			pagingHtml = page.getPagingHtml().toString();
	/*
			map.put("start", page.getStartCount()-1);
			map.put("end", page.getEndCount());*/
	
			if (count > 0) {
				list = boardService.searchBoard(searchBar);
			} else {
				list = Collections.emptyList();
			}
		}
		// 글 목록에 표시할 연번
		int number = count - (currentPage - 1) * pageSize;

		ModelAndView mav = new ModelAndView();
		// 위시리스트
				String memberId = (String)request.getSession().getAttribute("loginMemberId");
				List<WishlistDTO> wishs = null;
				if(memberId!=null) {
					wishs = myPageService.selectWishList(memberId);
				}

		mav.addObject("wishs", wishs);
		mav.setViewName("list/goodsList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", pagingHtml);
		mav.addObject("currentPage",currentPage);
		mav.addObject("number", number);
		mav.addObject("boardType", "sharing");

		return mav;
	}
	
	/**
	 * Rental 게시판 이동
	 */
	@RequestMapping("goodsRentalList")
	public ModelAndView goodsListRentalForm(@RequestParam(value = "pageNum", defaultValue = "1") int currentPage,
			@RequestParam(value = "keyField", defaultValue = "") String keyField,
			@RequestParam(value = "keyWord", defaultValue = "") String keyWord,
			@RequestParam(value = "searchBar",defaultValue = "") String searchBar,
			String productCategory,
			HttpServletRequest request) {
		int count=0;
		String pagingHtml = "";
		String path = request.getSession().getServletContext().getContextPath() + "/board/goodsList";
		List<BoardDTO> list = null;
		
		System.out.println("@@@@@@@@@@@@"+searchBar);
		if(searchBar.equals("")){

			HashMap<String, Object> map = new HashMap<String, Object>();
			//map.put("keyField", keyField);
			//map.put("keyWord", keyWord);
	
			// 총 글의 개수 또는 검색된 글의 개수
			count = boardService.getBoardCount(map , "rental" ,productCategory);
	
			
			// 페이징 처리
			//PagingUtil page = new PagingUtil(keyField, keyWord, currentPage, count, pageSize, blockCount, "board/goodsList");
			PagingUtil page = new PagingUtil(null, null, currentPage, count, pageSize, blockCount, path);
	
			pagingHtml = page.getPagingHtml().toString();
	
			map.put("start", page.getStartCount()-1);
			map.put("end", page.getEndCount());
	
			if (count > 0) {
				list = boardService.pageRentalList(map , productCategory);
			} else {
				list = Collections.emptyList();
			}
		}else{
			
			System.out.println("@@@@@@@@@@@@"+searchBar);
			HashMap<String, Object> map = new HashMap<String, Object>();
			//map.put("keyField", keyField);
			
			map.put("keyWord", searchBar);
	

			count = boardService.getBoardSearchCount(map);
			// 페이징 처리
			//PagingUtil page = new PagingUtil(keyField, keyWord, currentPage, count, pageSize, blockCount, "board/goodsList");
			PagingUtil page = new PagingUtil(null, searchBar, currentPage, count, pageSize, blockCount, path);
	
			pagingHtml = page.getPagingHtml().toString();
	/*
			map.put("start", page.getStartCount()-1);
			map.put("end", page.getEndCount());*/
	
			if (count > 0) {
				list = boardService.searchBoard(searchBar);
			} else {
				list = Collections.emptyList();
			}
		}
		// 글 목록에 표시할 연번
		int number = count - (currentPage - 1) * pageSize;

		ModelAndView mav = new ModelAndView();
		// 위시리스트
				String memberId = (String)request.getSession().getAttribute("loginMemberId");
				List<WishlistDTO> wishs = null;
				if(memberId!=null) {
					wishs = myPageService.selectWishList(memberId);
				}

		mav.addObject("wishs", wishs);
		mav.setViewName("list/goodsList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", pagingHtml);
		mav.addObject("currentPage",currentPage);
		mav.addObject("number", number);
		mav.addObject("boardType","rental");
		return mav;
	}


	/**
	 * 제품 상세 게시판 이동 게시글 번호 boardNo에 맞는 상세페이지
	 */
	@RequestMapping("goodsDetail")
	public ModelAndView goodsDetailFrom(int boardNo, HttpSession session) {
		BoardDTO boardDTO = boardService.selectByBoardNo(boardNo, true);
		GradeDTO gradeDTO = boardService.boardDetailSaleGrade(boardDTO.getMemberId());
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardDTO", boardDTO);
		mv.addObject("gradeDTO",gradeDTO);
		mv.setViewName("detail/goodsDetail");

		return mv;
	}
	
	/**
	 * 프로필 상품후기
	 * */
	@RequestMapping("boardSaleReview")
	@ResponseBody
	public List<GradeDTO> boardDetailSaleReview(int boardNo){
		List<GradeDTO> list  =boardService.boardDetailSaleReview(boardNo);
		System.out.println("gradeDTO LIST : "+ list);
		return list;
	}
	
	/**
	 * 프로필 판매제품 
	 * */
	@RequestMapping("boardSaleList")
	@ResponseBody
	public List<BoardDTO> boardDetailSaleList(String memberId){
		return boardService.boardDetailSaleList(memberId);
	}
	
	/**
	 * 댓글 창
	 * 
	 * 댓글 전체 리스트
	 */
	@RequestMapping("select")
	@ResponseBody
	public List<CommentDTO> selectAllComments(int boardNo){
		List<CommentDTO> list = boardService.selectAllComments(boardNo);
		
		return list;
	}
	
	/**
	 * 댓글추가
	 */
	@RequestMapping("insert")
	@ResponseBody
	public int insert(HttpSession session, HttpServletRequest request, String subject, int boardNo, String contents, String parentCommentNo){
		CommentDTO dto = new CommentDTO();
		String mId = (String)session.getAttribute("loginMemberId");
		
		dto.setMemberId(mId);
		dto.setBoardNo(boardNo);
		dto.setContents(contents);
		if(subject != null) dto.setSubject(subject);
		
		if(parentCommentNo!=null) dto.setParentCommentNo(Integer.parseInt(parentCommentNo));
		
		int re = boardService.insertComment(dto);
		return re;
	}
	
	/**
	 * 댓글삭제
	 */
	@RequestMapping("delete")
	@ResponseBody
	public int delete(CommentDTO commentDTO){
		return boardService.deleteComment(commentDTO);
	}
	
	/**
	 * 상품후기 전체리스트
	 */
	@RequestMapping("rselect")
	@ResponseBody
	public List<ReviewDTO> reviewAllSelect(int boardNo){
		List<ReviewDTO> list = boardService.selectAllReview(boardNo);
		return list;
	}
	
	/**
	 * 상품후기 추가
	 */
	@RequestMapping("rinsert")
	@ResponseBody
	public int reviewInsert(HttpSession session, HttpServletRequest request, String subject, int boardNo, String contents, String parentNo){
		ReviewDTO dto = new ReviewDTO();
		String mId = (String)session.getAttribute("loginMemberId");
		
		dto.setMemberId(mId);
		dto.setBoardNo(boardNo);
		dto.setContents(contents);
		if(subject!=null) dto.setSubject(subject);
		if(parentNo!=null) dto.setParentNo(Integer.parseInt(parentNo));
		
		int re = boardService.insertReview(dto);
		
		return re;
		
	}
	
	/**
	 * 상품후기 삭제
	 */
	@RequestMapping("rdelete")
	@ResponseBody
	public int reviewDelete(ReviewDTO reviewDTO){
		
		return boardService.deleteReview(reviewDTO);
	}
	
	/** 
	 * 장바구니 추가
	 */
	@RequestMapping("cartinsert")
	public String insertCart(HttpSession session, CartDTO cartDTO) throws Exception{
		
		String mId = (String)session.getAttribute("loginMemberId");
		cartDTO.setMemberId(mId);
		
		long diffDays=0;
		
			try{
				
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		        Date beginDate = formatter.parse(cartDTO.getCartStart());
		        Date endDate = formatter.parse(cartDTO.getCartEnd());
		         
		        // 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
		        long diff = endDate.getTime() - beginDate.getTime();
		        diffDays = diff / (24 * 60 * 60 * 1000) + 1;
		        
			}catch (Exception e) {
				 e.printStackTrace();
				 
			}
		
		cartDTO.setCartPrice((int)diffDays*cartDTO.getCartPrice());
		
		System.out.println(cartDTO.getCartPrice());
		
		int result = boardService.insertCart(cartDTO);
		
		if(result == 0){
			
			return "error/errorMessage";
			
		}else{
			
		 return "redirect:/mypage/cartList";
		}
	}

	/**
	 * 물품검색
	 */
	@RequestMapping("searchBoard")
	@ResponseBody
	public List<BoardDTO> searchBoard(String productName){
		
		return boardService.searchBoard(productName);
	}
	
	/**
	 * 상품삭제
	 * */
	@RequestMapping("boardDelete")
	public String boardDelete(HttpServletRequest request,int boardNo) throws Exception{
		
		if(boardService.deleteBoard(boardNo) <=0){
			request.setAttribute("errorMsg", "삭제실패");
			throw new Exception();
		}
		
		return "redirect:/board/goodsList";
	}
	

}
