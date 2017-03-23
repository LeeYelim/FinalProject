package spring.oshare.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.oshare.dto.ExtraChargeDTO;
import spring.oshare.dto.SharingDTO;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertExtraCharge(ExtraChargeDTO dto) {
		return sqlSession.insert("paymentMapper.insertExtraCharge", dto);
	}

	@Override
	public SharingDTO selectExtraChargeInfo(int sharingNo) {
		return sqlSession.selectOne("paymentMapper.selectExtraChargeInfo", sharingNo);
	}

}
