package com.myboot01.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.myboot01.web.dao.BoardDAO;
import com.myboot01.web.vo.ArticleVO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;

	public ArrayList<ArticleVO> listArticles() {
		return boardDAO.listArticles();
	}

	public ArticleVO viewArticle(int articleNO) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insertNewArticle(ArticleVO vo) {
		// TODO Auto-generated method stub
		
	}

	public void deleteArticle(int articleNO) {
		// TODO Auto-generated method stub
		
	}

	public ArticleVO updateArticle(ArticleVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addReply(ArticleVO vo) {
		boardDAO.insertNewArticle(vo);
	}
	

}
