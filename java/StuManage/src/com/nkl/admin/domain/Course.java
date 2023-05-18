package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class  Course extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 7424393039239963354L;
	private int course_id; // 
	private  String course_name; // 
	private int course_type; // 1-必修课 2-选修课
	private int teacher_id;
	private  String note; // 
	 
	private  String teacher_name;

	private int user_id; // 
	private int score_year; // 
	private int score_year_half; // 
	
	private  String ids;
	private  String random;

	public  String getCourse_typeDesc(){
		switch (course_type) {
		case 1:
			return "必修课";
		case 2:
			return "选修课";
		default:
			return "";
		}
	}
	
	public  void setCourse_id(int course_id){
		this.course_id=course_id;
	}

	public int getCourse_id(){
		return course_id;
	}

	public  void setCourse_name(String course_name){
		this.course_name=course_name;
	}

	public  String getCourse_name(){
		return course_name;
	}

	public  void setNote(String note){
		this.note=note;
	}

	public  String getNote(){
		return note;
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

	public int getUser_id() {
		return user_id;
	}

	public  void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getScore_year() {
		return score_year;
	}

	public  void setScore_year(int score_year) {
		this.score_year = score_year;
	}

	public int getScore_year_half() {
		return score_year_half;
	}

	public  void setScore_year_half(int score_year_half) {
		this.score_year_half = score_year_half;
	}

	public int getCourse_type() {
		return course_type;
	}

	public  void setCourse_type(int course_type) {
		this.course_type = course_type;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public  void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public  String getTeacher_name() {
		return teacher_name;
	}

	public  void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

}
