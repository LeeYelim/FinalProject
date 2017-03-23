package spring.oshare.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import spring.oshare.dto.MessageDTO;
import spring.oshare.service.BoardService;
import spring.oshare.service.MyPageService;
import spring.oshare.service.ReservationService;

@RestController
@RequestMapping("notify")
public class NotifyController {
	
	private List<SseEmitter> emitters = new CopyOnWriteArrayList<SseEmitter>();
	
	@Autowired
	private MyPageService myPageService;

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("connect")
	public SseEmitter connectSSE(HttpServletRequest request) {
		System.out.println("알림");
		SseEmitter emitter = new SseEmitter();
		
		emitters.add(emitter);		
		emitter.onCompletion(() -> emitters.remove(emitter));
		
		return emitter;
	}
	
	@RequestMapping(value="sendMessageInsert")
	public void insertMessage(MessageDTO message , HttpServletRequest request){
		System.out.println("실행");

		/*
		 * if(myPageService.insertMessage(message) <= 0) {
		 * request.setAttribute("errorMsg", "수신자가 존재하지 않습니다 다시 확인해주세요."); }
		 */

		String recipient = myPageService.insertMessage(message);

		// 성공이면 수신자 아이디 반환
		// 실패하면 에러 메세지 반환

		for (SseEmitter emitter : emitters) {
			try {
				System.out.println("이벤트실행");
				emitter.send(SseEmitter.event().name("spring").data(recipient));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("alarmPayment")
	public void alarmPayment(HttpServletRequest request) {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String recipient = boardService.selectByBoardNo(boardNo,false).getMemberId(); // 게시글 등록자
		
		for (SseEmitter emitter : emitters) {
			try {
				System.out.println("이벤트실행");
				emitter.send(SseEmitter.event().name("payment").data(recipient)); // 등록자에게 결제 알람보내기
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
