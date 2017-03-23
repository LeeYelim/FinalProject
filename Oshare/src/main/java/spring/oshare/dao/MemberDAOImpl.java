package spring.oshare.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.oshare.dto.MemberDTO;
import spring.oshare.dto.SharingDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * ID중복
	 */
	@Override
	public MemberDTO idCheck(String memberId) {
		return sqlSession.selectOne("memberMapper.idCheck", memberId);
	}

	/**
	 * 회원가입
	 */
	@Override
	public void signUp(MemberDTO member) {
		sqlSession.insert("memberMapper.joinMember", member);
	}

	/**
	 * 로그인
	 */
	@Override
	public int login(MemberDTO member) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 로그인 체크 (아이디/비밀번호)
	 */
	@Override
	public MemberDTO loginCheck(MemberDTO member) {
		return sqlSession.selectOne("memberMapper.loginCheck", member);
	}

   /**
    * 회원정보 조회
    */
   @Override
   public MemberDTO selectByMemberId(String memberId) {
      return sqlSession.selectOne("memberMapper.selectByMemberId", memberId);
   }
	
   /**
    * 회원정보 수정
    */
   @Override
   public int updateMember(MemberDTO member) {
      return sqlSession.update("memberMapper.updateMember", member);
   }

   /**
    * 거래상태 확인
    */
	@Override
	public SharingDTO deleteMemberConfirm(String transactionState , String memberId) {
		Map<String , String>  deleteMemberConfirmMap = new HashMap<>();
		deleteMemberConfirmMap.put("transactionState", transactionState);
		deleteMemberConfirmMap.put("memberId", memberId);
		
		return sqlSession.selectOne("memberMapper.sharingState", deleteMemberConfirmMap);
	}
   

   /**
    * 회원탈퇴
    */
	@Override
	public int deleteMember(String memberId) {
		return sqlSession.delete("memberMapper.deleteMember", memberId);
	}


	/**
	 * 쪽지 수신자 유효성 검사
	 * */
	@Override
	public MemberDTO receiverValidation(String receiver) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("memberMapper.receiverValidation", receiver);
	}

	@Override
	public int deleteMemberBoard(String memberId) {
		// TODO Auto-generated method stub
		return sqlSession.delete("memberMapper.deleteMemberBoard",memberId);
	}

	@Override
	public int deleteMemberGrade(String memberId) {
		// TODO Auto-generated method stub
		return sqlSession.delete("memberMapper.deleteMemberGrade",memberId);
	}
	




}
