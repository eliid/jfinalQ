package com.uikoo9.fore.controller;

import com.jfinal.plugin.activerecord.Db;
import com.uikoo9.manage.blog.model.BlogArticleModel;
import com.uikoo9.manage.blog.model.BlogTypeModel;
import com.uikoo9.manage.pro.model.ProDetailModel;
import com.uikoo9.util.jfinal.QController;
import com.uikoo9.util.jfinal.QControllerUrl;

@QControllerUrl("/home")
public class HomeController extends QController{
	
	/**
	 * 未登录跳转
	 */
	public void index(){
		render("/WEB-INF/view/fore/home-index.ftl");
	}
	
	/**
	 * 跳转到项目展示页面
	 */
	public void project(){
		try {
			Integer id = getParaToInt(0);
			if(id != null){
				ProDetailModel detail = ProDetailModel.dao.findById(id); 
				if(detail != null){
					setAttr("detail", detail);	
					render("/WEB-INF/view/fore/home-project.ftl");
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		redirect("/home");
	}
	
	/**
	 * 跳转到博客首页 
	 */
	public void blogs(){
		setAttr("blogTypes", BlogTypeModel.dao.findAllByCache());
		
		try {
			Integer typeId = getParaToInt(0);
			if(typeId != null){
				setAttr("blogTypeId", typeId);
				setAttr("blogs", Db.find("select * from t_blog_article tba where tba.type_id=?", typeId));
			}else{
				setAttr("blogs", BlogArticleModel.dao.findAll("order by cdate desc"));
			}
			
			render("/WEB-INF/view/fore/blog-index.ftl");
		} catch (Exception e) {
			e.printStackTrace();
			
			redirect("/blog/fore");
		}
	}
	
	/**
	 * 跳转到博客详情页面
	 */
	public void blog(){
		try {
			Integer blogId = getParaToInt(0);
			if(blogId != null){
				setAttr("blog", BlogArticleModel.dao.findById(blogId));
				render("/WEB-INF/view/fore/blog-detail.ftl");
			}else{
				redirect("/blog/fore");
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirect("/blog/fore");
		}
	}
	
	/**
	 * 跳转到版本更新
	 */
	public void version(){
		render("/WEB-INF/view/fore/home-version.ftl");
	}
	
}
