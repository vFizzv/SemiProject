package com.myboot01.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myboot01.web.vo.ArticleVO;
import com.myboot01.web.vo.MemberVO;

@Mapper
@Repository("BoardDAO")
public interface BoardDAO  {
	public ArrayList<ArticleVO> listArticles();
	
	public ArticleVO viewArticle(int articleNO);

	public void insertNewArticle(ArticleVO vo);
	
	public void deleteArticle(int articleNO);

	public ArticleVO updateArticle(ArticleVO vo);

}
