package com.myboot01.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myboot01.web.service.MemberService;
import com.myboot01.web.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberservice;
	
	@ResponseBody
	@RequestMapping("/memberInsert")
	public String memberInsert(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("memberInsert() 호출됨");
		String id = request.getParameter("id");
		String pw= request.getParameter("pw");
		String name = request.getParameter("name");
		String address= request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		
		MemberVO vo =new MemberVO(id,pw,name,address,phoneNumber);
		System.out.println(vo);
		
		try {
			memberservice.insertMember(vo);
			return "회원 가입 ok";
		} catch (Exception e) {
			return "회원 가입에 실패하셨습니다.";
		}
	}
	
	@ResponseBody
	@RequestMapping("/loginById")
	public String loginById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String pw= request.getParameter("pw");
		MemberVO memberVO = new MemberVO(id,pw,null);
		memberVO = memberservice.loginById(memberVO);

		JSONObject json = new JSONObject();
		try {
			if(memberVO!=null) {//ok
				HttpSession session=request.getSession();
				session.setAttribute("id", id);
				json.put("id", id);
				return json.toJSONString();
			}else {//fail
				json.put("msg", "login 실패");
				return json.toJSONString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "login 실패");
			return json.toJSONString();
		}
	}

	@ResponseBody
	@RequestMapping("/logOut")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession();
		session.invalidate();
		return "logout ok";
	}

	@ResponseBody
	@RequestMapping("/deleteMember")
	public String deleteMember(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession(false);
		String id=(String)session.getAttribute("id");

		try {
			memberservice.deleteMember(id);
			return "회원 삭제 완료";
		} catch (Exception e) {
			e.printStackTrace();
			return "회원 삭제 실패";
		}
	}

	@ResponseBody
	@RequestMapping("/updateMember")
	public String updateMember(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw= request.getParameter("pw");
		String name= request.getParameter("name");
		String address= request.getParameter("address");
		String phoneNumber= request.getParameter("phoneNumber");
		
		MemberVO memberVO = new MemberVO(id,pw,name,address,phoneNumber);
		
		try {
			memberservice.updateMember(memberVO);
			return "회원정보 수정 완료";
		} catch (Exception e) {
			e.printStackTrace();
			return "회원정보 수정 실패";
		}
	}
	
	@RequestMapping("/selectAllMemberList")
	public String selectAllMemberList(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<MemberVO> list = memberservice.selectAllMemberList();
			return "ok.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail.jsp";
		}
	}

}
