package spring.oshare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.oshare.dao.MemberDAO;
import spring.oshare.dto.MemberDTO;
import spring.oshare.dto.SharingDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDao;
	
	/**
	 * ID중복체크
	 */
	@Override
	public boolean serviceIdCheck(String memberId){

		MemberDTO memberDTO = memberDao.idCheck(memberId);
		
		//존재여부 확인
		boolean result = false;
				
		//ID중복
		if(memberDTO!=null) return true;
		
		//ID사용가능
		return result;
	}
	
	/**
	 * 회원가입
	 */
	@Override
	public void signUp(MemberDTO member) {
		memberDao.signUp(member);
	}

	   /**
	    * 로그인
	    * */
	   @Override
	   public int login(MemberDTO member) {
	      // TODO Auto-generated method stub
	      return 0;
	   }

	   /**
	    * 로그인 체크(아이디/비밀번호 타당성 확인) 
	    */
	   @Override
	   public MemberDTO loginCheck(MemberDTO member) {
	      return memberDao.loginCheck(member);
	   }
	   
	   /**
	    * 회원 정보 조회
	    * */
	   @Override
	   public MemberDTO selectByMember(String memberId) {
	      
	      return memberDao.selectByMemberId(memberId);
	   }
	   
	   /**
	    * 회원정보 수정
	    */
	   @Override
	   public int updateMember(MemberDTO member) {
	      
	      int result = memberDao.updateMember(member);
	      
	      return result;
	   }

	   /**
		 * 거래상태 확인
		 */
		public SharingDTO deleteMemberConfirm(String transactionState , String memberId) {
			return memberDao.deleteMemberConfirm(transactionState , memberId);
		}

		/**
		 * 회원탈퇴
		 */
		@Override
		public int deleteMember(String memberId) {
			
			int result = memberDao.deleteMember(memberId);
			result = memberDao.deleteMemberBoard(memberId);
			 
		
		
			
			return result;
		}




	/**
	 * 쪽지수신자 유효성검사 
	 * */
	@Override
	public MemberDTO receiverValidation(String receiver) {
		// TODO Auto-generated method stub
		return memberDao.receiverValidation(receiver);
	}



}
