package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class  Plan extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2314005237608205836L;
	private int plan_id; // 
	private int plan_year; // 
	private int plan_year_half; // 1-上半年 2-下半年
	private int clazz_id; // 
	private int course_id; // 
	private int user_id; // 
	private  String note; // 

	private  String clazz_name; 
	private  String course_name; //
	private  String real_name; // 
	 
	private  String ids;
	private  String random;

	public  String getPlan_year_halfDesc(){
		switch (plan_year_half) {
		case 1:
			return "上半年";
		case 2:
			return "下半年";
		default:
			return "";
		}
	}
	
	public  void setPlan_id(int plan_id){
		this.plan_id=plan_id;
	}

	public int getPlan_id(){
		return plan_id;
	}

	public  void setPlan_year(int plan_year){
		this.plan_year=plan_year;
	}

	public int getPlan_year(){
		return plan_year;
	}

	public  void setPlan_year_half(int plan_year_half){
		this.plan_year_half=plan_year_half;
	}

	public int getPlan_year_half(){
		return plan_year_half;
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

	public  void setUser_id(int user_id){
		this.user_id=user_id;
	}

	public int getUser_id(){
		return user_id;
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

	public  String getReal_name() {
		return real_name;
	}

	public  void setReal_name(String real_name) {
		this.real_name = real_name;
	}

}
