package com.myboot01.web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myboot01.web.service.BoardService;
import com.myboot01.web.vo.ArticleVO;
import com.myboot01.web.vo.MemberVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardservice;
	
	@ResponseBody
	@RequestMapping("/listArticles")
	public String listArticles(HttpServletRequest request, HttpServletResponse response) {
		JSONObject json = new JSONObject();		
		try {
			ArrayList<ArticleVO> articleList= boardservice.listArticles();
			JSONArray jsonArray = new JSONArray();
			for (ArticleVO vo: articleList) {
				json.put("level", vo.getLevel());
				json.put("articleNO", vo.getArticleNO());
				json.put("parentNO", vo.getParentNO());
				json.put("title", vo.getTitle());
				json.put("writeDate", vo.getWriteDate().toString());
				json.put("id", vo.getId());
				jsonArray.add(json);
			}
			return jsonArray.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "게시판 목록 보기 실패");
			return json.toJSONString();
		}

	}
	
	@ResponseBody
	@RequestMapping("/insertNewArticle")
	public String insertNewArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session=request.getSession(false);
		JSONObject json = new JSONObject();
		if(session==null) {				
			json.put("msg","먼저 로그인 하세요");
			return json.toJSONString();
		}else {
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			String id=(String) session.getAttribute("id");
			ArticleVO vo=new ArticleVO(1, 0, 0, title, content, null, id);
			System.out.println(vo);
			try {
				boardservice.insertNewArticle(vo);
				json.put("ok","글이 등록되었습니다.");
				return json.toJSONString();
			} catch (Exception e) {
				json.put("fail",e.getMessage());
				return json.toJSONString();
			}
		}
	}

	@ResponseBody
	@RequestMapping("/viewArticle")
	public String viewArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject json = new JSONObject();
		int articleNO=Integer.parseInt(request.getParameter("articleNO"));
		try {
			ArticleVO vo= boardservice.viewArticle(articleNO);
			if(vo != null) {
				RequestDispatcher disp=request.getRequestDispatcher("jsp/viewArticle.jsp");
				request.setAttribute("vo", vo);
				disp.forward(request, response);
				json.put("articleNO",articleNO);
				return json.toJSONString();
			}else {
				json.put("no","해당글이 없습니다");
				return json.toJSONString();
			}
		} catch (Exception e) {
			json.put("fail",e.getMessage());
			return json.toJSONString();
		}
	}

	@ResponseBody
	@RequestMapping("/deleteArticle")
	public String deleteArticle(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession(false);
		String id=(String)session.getAttribute("id");
		int articleNO=Integer.parseInt(request.getParameter("articleNO"));
		ArticleVO vo= boardservice.viewArticle(articleNO);
		JSONObject json = new JSONObject();
		if (vo.getId() != id) {
			json.put("no","본인의 글만 삭제할 수 있습니다.");
			return json.toJSONString();
		} else {
			boardservice.deleteArticle(articleNO);
			json.put("ok","해당 게시글이 삭제 되었습니다.");
			return json.toJSONString();
		}
	}

	@ResponseBody
	@RequestMapping("/updateArticle")
	public String updateArticle(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession(false);
		String id=(String)session.getAttribute("id");
		int articleNO=Integer.parseInt(request.getParameter("articleNO"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		ArticleVO vo1= new ArticleVO(1, articleNO, 0, title, content, null, id);
		JSONObject json = new JSONObject();
		
		try {
			ArticleVO vo2 = boardservice.viewArticle(articleNO);
			if (vo2.getId() != id) {
				json.put("no","본인의 글만 수정할 수 있습니다.");
				return json.toJSONString();
			} else {
				vo1 = boardservice.updateArticle(vo1);
				RequestDispatcher disp=request.getRequestDispatcher("jsp/updateArticle.jsp");
				request.setAttribute("vo", vo1);
				disp.forward(request, response);
				json.put("articleNO",articleNO);
				return json.toJSONString();
			}
		} catch (Exception e) {
			json.put("fail",e.getMessage());
			return json.toJSONString();
		}
	}


}
