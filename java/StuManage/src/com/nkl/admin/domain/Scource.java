package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class  Scource extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2314005237608205836L;
	private int scource_id; // 
	private int scource_year; // 
	private int scource_year_half; // 1-上半年 2-下半年
	private int course_id; // 
	private int user_id; // 
 
	private  String course_name; //
	private  String real_name; // 
	private  String teacher_name; // 
	
	private  String ids;
	private  String random;

	public  String getScource_year_halfDesc(){
		switch (scource_year_half) {
		case 1:
			return "上半年";
		case 2:
			return "下半年";
		default:
			return "";
		}
	}
	
	public  void setScource_id(int scource_id){
		this.scource_id=scource_id;
	}

	public int getScource_id(){
		return scource_id;
	}

	public  void setScource_year(int scource_year){
		this.scource_year=scource_year;
	}

	public int getScource_year(){
		return scource_year;
	}

	public  void setScource_year_half(int scource_year_half){
		this.scource_year_half=scource_year_half;
	}

	public int getScource_year_half(){
		return scource_year_half;
	}

	public  void setCourse_id(int course_id){
		this.course_id=course_id;
	}

	public int getCourse_id(){
		return course_id;
	}

	public  void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
	}

	public  void setIds(String ids) {
		this.ids = ids;
	}

	public  String getIds() {
		return ids;
	}

	public  void setRandom(String random) {
		this.random = random;
	}

	public  String getRandom() {
		return random;
	}

	public  String getCourse_name() {
		return course_name;
	}

	public  void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public  String getReal_name() {
		return real_name;
	}

	public  void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public  String getTeacher_name() {
		return teacher_name;
	}

	public  void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

}
