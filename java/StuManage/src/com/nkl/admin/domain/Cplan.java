package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class  Cplan extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2314005237608205836L;
	private int cplan_id; // 
	private int cplan_year; // 
	private int cplan_year_half; // 1-上半年 2-下半年
	private int clazz_id; // 
	private int cplan_week; // 1-5 星期一到星期五
	private int cplan_lesson; // 1-8 第1节课到第8节课
	private int course_id; // 
	private  String note; // 
 
	private  String clazz_name; 
	private  String course_name; //
	
	private  String week1_lesson; //
	private  String week2_lesson; //
	private  String week3_lesson; //
	private  String week4_lesson; //
	private  String week5_lesson; //
	
	private  String ids;
	private  String random;

	public  String getCplan_weekDesc(){
		String[] weeks = {"","星期一","星期二","星期三","星期四","星期五"};
		return weeks[cplan_week];
	}
	
	public  String getCplan_lessonDesc(){
		String[] lessons = {"","第1节课","第2节课","第3节课","第4节课","第5节课","第6节课","第7节课","第8节课"};
		return lessons[cplan_lesson];
	}
	
	public  String getCplan_year_halfDesc(){
		switch (cplan_year_half) {
		case 1:
			return "上半年";
		case 2:
			return "下半年";
		default:
			return "";
		}
	}
	
	public  void setCplan_id(int cplan_id){
		this.cplan_id=cplan_id;
	}

	public int getCplan_id(){
		return cplan_id;
	}

	public  void setCplan_year(int cplan_year){
		this.cplan_year=cplan_year;
	}

	public int getCplan_year(){
		return cplan_year;
	}

	public  void setCplan_year_half(int cplan_year_half){
		this.cplan_year_half=cplan_year_half;
	}

	public int getCplan_year_half(){
		return cplan_year_half;
	}

	public  void setClazz_id(int clazz_id){
		this.clazz_id=clazz_id;
	}

	public int getClazz_id(){
		return clazz_id;
	}

	public  void setCourse_id(int course_id){
		this.course_id=course_id;
	}

	public int getCourse_id(){
		return course_id;
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

	public  String getClazz_name() {
		return clazz_name;
	}

	public  void setClazz_name(String clazz_name) {
		this.clazz_name = clazz_name;
	}

	public  String getCourse_name() {
		return course_name;
	}

	public  void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public int getCplan_week() {
		return cplan_week;
	}

	public  void setCplan_week(int cplan_week) {
		this.cplan_week = cplan_week;
	}

	public int getCplan_lesson() {
		return cplan_lesson;
	}

	public  void setCplan_lesson(int cplan_lesson) {
		this.cplan_lesson = cplan_lesson;
	}

	public  String getWeek1_lesson() {
		return week1_lesson;
	}

	public  void setWeek1_lesson(String week1_lesson) {
		this.week1_lesson = week1_lesson;
	}

	public  String getWeek2_lesson() {
		return week2_lesson;
	}

	public  void setWeek2_lesson(String week2_lesson) {
		this.week2_lesson = week2_lesson;
	}

	public  String getWeek3_lesson() {
		return week3_lesson;
	}

	public  void setWeek3_lesson(String week3_lesson) {
		this.week3_lesson = week3_lesson;
	}

	public  String getWeek4_lesson() {
		return week4_lesson;
	}

	public  void setWeek4_lesson(String week4_lesson) {
		this.week4_lesson = week4_lesson;
	}

	public  String getWeek5_lesson() {
		return week5_lesson;
	}

	public  void setWeek5_lesson(String week5_lesson) {
		this.week5_lesson = week5_lesson;
	}

}
