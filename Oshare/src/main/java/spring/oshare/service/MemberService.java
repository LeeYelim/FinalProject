package spring.oshare.service;

import java.util.List;

import spring.oshare.dto.MemberDTO;
import spring.oshare.dto.SharingDTO;

public interface MemberService {
	
	/**
	 * ID중복체크
	 */
	boolean serviceIdCheck(String memberId);

	/**
	 * 회원가입
	 * */
	void signUp(MemberDTO user);

	/**
	 * 로그인
	 * */
	int login(MemberDTO user);
	
	/**
	 * 로그인 체크(아이디/비밀번호 타당성 확인) 
	 */
	MemberDTO loginCheck(MemberDTO member);
	
	/**
	 * 회원 정보 조회
	 * */
	MemberDTO selectByMember(String memberId);
	
	/**
	 * 회원정보 수정
	 * */
	int updateMember(MemberDTO member);
	
	/**
	 * 거래상태 확인
	 */
	public SharingDTO deleteMemberConfirm(String transactionState , String memberId);

	/**
	 * 회원 탈퇴
	 */
	int deleteMember(String memberId);
	
	/**
	 * 수신자 유효성검사
	 * */
	MemberDTO receiverValidation(String receiver);
	
	
	
}
