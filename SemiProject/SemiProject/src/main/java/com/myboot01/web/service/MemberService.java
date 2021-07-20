package com.myboot01.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myboot01.web.dao.MemberDAO;
import com.myboot01.web.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	MemberDAO memberDAO;
	
	public void insertMember(MemberVO memberVO) throws DataAccessException {
		memberDAO.insertMember(memberVO);
	}
	
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException{
		return memberDAO.loginById(memberVO);
	}
	
	public MemberVO logOut(MemberVO memberVO) throws DataAccessException{
		return memberDAO.logOut(memberVO);
	}

	public void deleteMember(String id) throws DataAccessException{
		memberDAO.deleteMember(id);
	}

	public void updateMember(MemberVO memberVO) throws DataAccessException{
		memberDAO.updateMember(memberVO);
	}
	
	public List<MemberVO> selectAllMemberList() throws DataAccessException{
		return memberDAO.selectAllMemberList();
	}
	

}
