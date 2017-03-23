package spring.oshare.dao;

import java.util.List;

import spring.oshare.dto.MemberDTO;
import spring.oshare.dto.SharingDTO;

public interface MemberDAO {
	
	/**
	 * ID중복체크
	 * */
	MemberDTO idCheck(String memberId);
	
	/**
	 * 회원가입
	 * */
	void signUp(MemberDTO member);
	
	/**
	 * 로그인
	 * */
	int login(MemberDTO member);
	
	/**
	 * 로그인 체크(아이디/비밀번호 확인)
	 */
	MemberDTO loginCheck(MemberDTO member);
	
	
	/**
	 * 회원 정보 조회
	 * */
	MemberDTO selectByMemberId(String memberId);
	
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
	 * @return 
	 * */
	int deleteMember(String memberId);
	
	/**
	 * 수신자 유효성검사
	 * */
	MemberDTO receiverValidation(String receiver);
	
	/**
	 * 회원탈퇴 탈퇴된  게시물 삭제
	 * */
	int deleteMemberBoard(String memberId);
	
	/**
	 * 회원탈퇴 탈퇴된 평점삭제
	 * */
	int deleteMemberGrade(String memberId);

	
}
