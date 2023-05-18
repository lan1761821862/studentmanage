package com.nkl.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Course;
import com.nkl.admin.domain.Cplan;
import com.nkl.admin.domain.Evaluate;
import com.nkl.admin.domain.Plan;
import com.nkl.admin.domain.Score;
import com.nkl.admin.domain.Scource;
import com.nkl.admin.domain.User;
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Param;

public class  AdminAction  extends BaseAction {
 
	private static final long serialVersionUID = 1L;
	AdminManager adminManager = new AdminManager();
	
	//抓取页面参数
	User paramsUser; 
	Clazz paramsClazz;
	Course paramsCourse;
	Plan paramsPlan;
	Cplan paramsCplan;
	Scource paramsScource;
	Score paramsScore;
	Evaluate paramsEvaluate;
	
	
	String tip;
	
	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public  String saveAdmin(){
		try {
			//验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人信息
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.queryUser(admin);
			Param.setSession("admin", admin);
			
			setSuccessTip("编辑成功", "modifyInfo.jsp");
			
		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public  String saveAdminPass(){
		try {
			//验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			 //保存修改个人密码
			//paramsUser.setUser_pass(Md5.makeMd5(paramsUser.getUser_pass()));
			adminManager.updateUser(paramsUser);
			//更新session
			User admin = (User)Param.getSession("admin");
			if (admin!=null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}
			
			setSuccessTip("修改成功", "modifyPwd.jsp");
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: queryQuestion
	 * @Description: 根据用户名查找密保问题
	 * @return String
	 */
	public  String inputUserName(){
		return "inputUserName";
	}
	public  String queryQuestion(){
		try {
			 //得到账号信息
			User user = adminManager.queryUser(paramsUser);
			if (user==null) {
				tip = "该用户名不存在!";
				Param.setAttribute("user_name", paramsUser.getUser_name());
				return "inputUserName";
			}
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			tip = "查找用户名异常!";
			Param.setAttribute("user_name", paramsUser.getUser_name());
			return "inputUserName";
		}
		
		return "inputUserAnswer";
	}
	
	/**
	 * @Title: validAnswer
	 * @Description: 验证密保问题
	 * @return String
	 */
	public  String validAnswer(){
		try {
			 //得到账号信息
			String answer = paramsUser.getUser_answer();
			User user = adminManager.queryUser(paramsUser);
			if (!answer.equals(user.getUser_answer())) {
				tip = "密保答案错误!";
				Param.setAttribute("user_answer", answer);
				Param.setAttribute("user", user);
				return "inputUserAnswer";
			}
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			tip = "密保答案错误!";
			Param.setAttribute("user_answer", paramsUser.getUser_answer());
			Param.setAttribute("user", adminManager.queryUser(paramsUser));
			return "inputUserAnswer";
		}
		
		return "resetPass";
	}
	
	/**
	 * @Title: resetPass
	 * @Description: 重置密码
	 * @return String
	 */
	public  String resetPass(){
		try {
			 //重置密码
			adminManager.updateUser(paramsUser);
			
		} catch (Exception e) {
			tip = "密码重置异常!";
			Param.setAttribute("user", adminManager.queryUser(paramsUser));
			return "resetPass";
		}
		setSuccessTip("密码重置成功", "login.jsp");
		return "infoTip";
	}
	
	/**
	 * @Title: listUsers
	 * @Description: 查询学生
	 * @return String
	 */
	public  String listUsers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			//查看学生信息
			paramsUser.setUser_type(1);
			
			//设置分页信息
			setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询学生列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询学生异常", "main.jsp");
			return "infoTip";
		}
		
		return "userShow";
	}
	
	/**
	 * @Title: queryCourse
	 * @Description: 查询课程
	 * @return String
	 */
	public  String queryCourse(){
		try {
			if (paramsCourse==null) {
				paramsCourse = new Course();
			}
			//限制为教师身份
			User admin = (User)Param.getSession("admin");
			paramsCourse.setStart(-1);
			paramsCourse.setUser_id(admin.getUser_id());
			//查询课程列表
			List<Course> courses = adminManager.listCourses(paramsCourse,null); 
			
			setResult("courses", courses);
			
		} catch (Exception e) {
			setErrorReason("查询课程信息失败，服务器异常！",e);
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: queryStudent
	 * @Description: 查询学生
	 * @return String
	 */
	public  String queryStudent(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			paramsUser.setUser_type(1);
			paramsUser.setStart(-1);
			//查询学生列表
			List<User> users = adminManager.listUsers(paramsUser,null); 
			
			setResult("users", users);
			
		} catch (Exception e) {
			setErrorReason("查询学生信息失败，服务器异常！",e);
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: queryStudent2
	 * @Description: 查询学生（选修）
	 * @return String
	 */
	public  String queryStudent2(){
		try {
			if (paramsScource==null) {
				paramsScource = new Scource();
			}
			paramsScource.setStart(-1);
			//查询学生（选修）列表
			List<Scource> users = adminManager.listScources(paramsScource,null); 
			
			setResult("users", users);
			
		} catch (Exception e) {
			setErrorReason("查询学生信息失败，服务器异常！",e);
			return "error";
		}
		
		return "success";
	}
	
	/**
	 * @Title: addUserShow
	 * @Description: 显示添加学生页面
	 * @return String
	 */
	public  String addUserShow(){
		//查询班级字典
		Clazz clazz = new Clazz();
		clazz.setStart(-1);
		List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
		Param.setAttribute("clazzs", clazzs);
		
		return "userEdit";
	}
	
	/**
	 * @Title: addUser
	 * @Description: 添加学生
	 * @return String
	 */
	public  String addUser(){
		try {
			//检查学生是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user!=null) {
				tip="失败，该学号已经存在！";
				Param.setAttribute("user", paramsUser);
				
				//查询班级字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);
				
				return "userEdit";
			}
			
			 //添加学生
			paramsUser.setUser_type(1);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加学生成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("添加学生异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editUser
	 * @Description: 编辑学生
	 * @return String
	 */
	public  String editUser(){
		try {
			 //得到学生
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);
			
			//查询班级字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
		} catch (Exception e) {
			setErrorTip("查询学生异常", "Admin_listUsers.action");
			return "infoTip";
		}
		
		return "userEdit";
	}
	
	/**
	 * @Title: saveUser
	 * @Description: 保存编辑学生
	 * @return String
	 */
	public  String saveUser(){
		try {
			 //保存编辑学生
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listUsers.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			
			//查询班级字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			return "userEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除学生
	 * @return String
	 */
	public  String delUsers(){
		try {
			 //删除学生
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除学生成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("删除学生异常", "Admin_listUsers.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listTeachers
	 * @Description: 查询教师
	 * @return String
	 */
	public  String listTeachers(){
		try {
			if (paramsUser==null) {
				paramsUser = new User();
			}
			paramsUser.setUser_type(2);
			
			//设置分页信息
			setPagination(paramsUser);
			//总的条数
			int[] sum={0};
			//查询教师列表
			List<User> users = adminManager.listUsers(paramsUser,sum); 
			
			Param.setAttribute("users", users);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询教师异常", "main.jsp");
			return "infoTip";
		}
		
		return "teacherShow";
	}
	
	/**
	 * @Title: addTeacherShow
	 * @Description: 显示添加教师页面
	 * @return String
	 */
	public  String addTeacherShow(){
		return "teacherEdit";
	}
	
	/**
	 * @Title: addTeacher
	 * @Description: 添加教师
	 * @return String
	 */
	public  String addTeacher(){
		try {
			//检查登录名是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user!=null) {
				tip="失败，该用户名已经存在！";
				Param.setAttribute("user", paramsUser);
				return "teacherEdit";
			}
			 //添加教师
			paramsUser.setUser_type(2);
			paramsUser.setReg_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			adminManager.addUser(paramsUser);
			
			setSuccessTip("添加成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			setErrorTip("添加教师异常", "Admin_listTeachers.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editTeacher
	 * @Description: 编辑教师
	 * @return String
	 */
	public  String editTeacher(){
		try {
			 //得到教师
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);
			
		} catch (Exception e) {
			setErrorTip("查询教师异常", "Admin_listTeachers.action");
			return "infoTip";
		}
		
		return "teacherEdit";
	}
	
	/**
	 * @Title: saveTeacher
	 * @Description: 保存编辑教师
	 * @return String
	 */
	public  String saveTeacher(){
		try {
			 //保存编辑教师
			adminManager.updateUser(paramsUser);
			
			setSuccessTip("编辑成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delTeachers
	 * @Description: 删除教师
	 * @return String
	 */
	public  String delTeachers(){
		try {
			 //删除教师
			adminManager.delUsers(paramsUser);
			
			setSuccessTip("删除教师成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			setErrorTip("删除教师异常", "Admin_listTeachers.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listClazzs
	 * @Description: 查询班级
	 * @return String
	 */
	public  String listClazzs(){
		try {
			if (paramsClazz==null) {
				paramsClazz = new Clazz();
			}
			//设置分页信息
			setPagination(paramsClazz);
			//总的条数
			int[] sum={0};
			//查询班级列表
			List<Clazz> clazzs = adminManager.listClazzs(paramsClazz,sum); 
			
			Param.setAttribute("clazzs", clazzs);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询班级异常", "main.jsp");
			return "infoTip";
		}
		
		return "clazzShow";
	}
	
	/**
	 * @Title: addClazzShow
	 * @Description: 显示添加班级页面
	 * @return String
	 */
	public  String addClazzShow(){
		return "clazzEdit";
	}
	
	/**
	 * @Title: addClazz
	 * @Description: 添加班级
	 * @return String
	 */
	public  String addClazz(){
		try {
			 //添加班级
			adminManager.addClazz(paramsClazz);
			
			setSuccessTip("添加成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			setErrorTip("添加班级异常", "Admin_listClazzs.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editClazz
	 * @Description: 编辑班级
	 * @return String
	 */
	public  String editClazz(){
		try {
			 //得到班级
			Clazz clazz = adminManager.queryClazz(paramsClazz);
			Param.setAttribute("clazz", clazz);
		} catch (Exception e) {
			setErrorTip("查询班级异常", "Admin_listClazzs.action");
			return "infoTip";
		}
		
		return "clazzEdit";
	}
	
	/**
	 * @Title: saveClazz
	 * @Description: 保存编辑班级
	 * @return String
	 */
	public  String saveClazz(){
		try {
			 //保存编辑班级
			adminManager.updateClazz(paramsClazz);
			
			setSuccessTip("编辑成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("clazz", paramsClazz);
			return "clazzEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delClazzs
	 * @Description: 删除班级
	 * @return String
	 */
	public  String delClazzs(){
		try {
			 //删除班级
			adminManager.delClazzs(paramsClazz);
			
			setSuccessTip("删除班级成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			setErrorTip("删除班级异常", "Admin_listClazzs.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listCourses
	 * @Description: 查询课程
	 * @return String
	 */
	public  String listCourses(){
		try {
			if (paramsCourse==null) {
				paramsCourse = new Course();
			}
			//设置分页信息
			setPagination(paramsCourse);
			//总的条数
			int[] sum={0};
			//查询课程列表
			List<Course> courses = adminManager.listCourses(paramsCourse,sum); 
			
			Param.setAttribute("courses", courses);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询课程异常", "main.jsp");
			return "infoTip";
		}
		
		return "courseShow";
	}
	
	/**
	 * @Title: addCourseShow
	 * @Description: 显示添加课程页面
	 * @return String
	 */
	public  String addCourseShow(){
		//查询教师集合
		User user = new User();
		user.setUser_type(2);
		user.setStart(-1);
		List<User> teachers = adminManager.listUsers(user, null);
		Param.setAttribute("teachers", teachers);
		
		return "courseEdit";
	}
	
	/**
	 * @Title: addCourse
	 * @Description: 添加课程
	 * @return String
	 */
	public  String addCourse(){
		try {
			 //添加课程
			adminManager.addCourse(paramsCourse);
			
			setSuccessTip("添加成功", "Admin_listCourses.action");
		} catch (Exception e) {
			setErrorTip("添加课程异常", "Admin_listCourses.action");
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editCourse
	 * @Description: 编辑课程
	 * @return String
	 */
	public  String editCourse(){
		try {
			 //得到课程
			Course course = adminManager.queryCourse(paramsCourse);
			Param.setAttribute("course", course);
			
			//查询教师集合
			User user = new User();
			user.setUser_type(2);
			user.setStart(-1);
			List<User> teachers = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", teachers);
			
		} catch (Exception e) {
			setErrorTip("查询课程异常", "Admin_listCourses.action");
			return "infoTip";
		}
		
		return "courseEdit";
	}
	
	/**
	 * @Title: saveCourse
	 * @Description: 保存编辑课程
	 * @return String
	 */
	public  String saveCourse(){
		try {
			 //保存编辑课程
			adminManager.updateCourse(paramsCourse);
			
			setSuccessTip("编辑成功", "Admin_listCourses.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("course", paramsCourse);
			
			//查询教师集合
			User user = new User();
			user.setUser_type(2);
			user.setStart(-1);
			List<User> teachers = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", teachers);
			
			return "courseEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delCourses
	 * @Description: 删除课程
	 * @return String
	 */
	public  String delCourses(){
		try {
			 //删除课程
			adminManager.delCourses(paramsCourse);
			
			setSuccessTip("删除课程成功", "Admin_listCourses.action");
		} catch (Exception e) {
			setErrorTip("删除课程异常", "Admin_listCourses.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listPlans
	 * @Description: 查询教学计划
	 * @return String
	 */
	public  String listPlans(){
		try {
			if (paramsPlan==null) {
				paramsPlan = new Plan();
			}
			//设置分页信息
			setPagination(paramsPlan);
			//总的条数
			int[] sum={0};
			//查询教学计划列表
			List<Plan> plans = adminManager.listPlans(paramsPlan,sum); 
			
			Param.setAttribute("plans", plans);
			setTotalCount(sum[0]);
			
			//查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			Course course = new Course();
			course.setStart(-1);
			course.setCourse_type(1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);
			
		} catch (Exception e) {
			setErrorTip("查询教学计划异常", "main.jsp");
			return "infoTip";
		}
		
		return "planShow";
	}
	
	/**
	 * @Title: addPlanShow
	 * @Description: 显示添加教学计划页面
	 * @return String
	 */
	public  String addPlanShow(){
		//查询班级、课程字典
		Clazz clazz = new Clazz();
		clazz.setStart(-1);
		List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
		Param.setAttribute("clazzs", clazzs);
		
		Course course = new Course();
		course.setStart(-1);
		course.setCourse_type(1);
		List<Course> courses = adminManager.listCourses(course, null);
		Param.setAttribute("courses", courses);
		
		//查询教师字典
		User user = new User();
		user.setStart(-1);
		user.setUser_type(2);
		List<User> users = adminManager.listUsers(user, null);
		Param.setAttribute("users", users);
		
		return "planEdit";
	}
	
	/**
	 * @Title: addPlan
	 * @Description: 添加教学计划
	 * @return String
	 */
	public  String addPlan(){
		try {
			//判断教学计划是否已经添加
			Plan plan = adminManager.queryPlan(paramsPlan);
			if (plan!=null) {
				tip = "失败，该教师本次教学计划已经存在！";
				Param.setAttribute("plan", paramsPlan);
				
				//查询班级、课程字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);
				
				Course course = new Course();
				course.setStart(-1);
				course.setCourse_type(1);
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);
				
				//查询教师字典
				User user = new User();
				user.setStart(-1);
				user.setUser_type(2);
				List<User> users = adminManager.listUsers(user, null);
				Param.setAttribute("users", users);
				
				return "planEdit";
			}
			
			//添加教学计划
			adminManager.addPlan(paramsPlan);
			
			setSuccessTip("添加成功", "Admin_listPlans.action");
		} catch (Exception e) {
			setErrorTip("添加教学计划异常", "Admin_listPlans.action");
			e.printStackTrace();
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editPlan
	 * @Description: 编辑教学计划
	 * @return String
	 */
	public  String editPlan(){
		try {
			 //得到教学计划
			Plan plan = adminManager.queryPlan(paramsPlan);
			Param.setAttribute("plan", plan);
			
			//查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			Course course = new Course();
			course.setStart(-1);
			course.setCourse_type(1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);
			
			//查询教师字典
			User user = new User();
			user.setStart(-1);
			user.setUser_type(2);
			List<User> users = adminManager.listUsers(user, null);
			Param.setAttribute("users", users);
			
		} catch (Exception e) {
			setErrorTip("查询教学计划异常", "Admin_listPlans.action");
			return "infoTip";
		}
		
		return "planEdit";
	}
	
	/**
	 * @Title: savePlan
	 * @Description: 保存编辑教学计划
	 * @return String
	 */
	public  String savePlan(){
		try {
			 //验证教学计划是否存在
			int plan_id = paramsPlan.getPlan_id();
			paramsPlan.setPlan_id(0);
			Plan plan = adminManager.queryPlan(paramsPlan);
			paramsPlan.setPlan_id(plan_id);
			if (plan!=null && plan.getPlan_id()!=plan_id) {
				 tip = "失败，该教师本次教学计划已经存在！";
				 Param.setAttribute("plan", paramsPlan);
				 
				//查询班级、课程字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);
				
				Course course = new Course();
				course.setStart(-1);
				course.setCourse_type(1);
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);
				
				//查询教师字典
				User user = new User();
				user.setStart(-1);
				user.setUser_type(2);
				List<User> users = adminManager.listUsers(user, null);
				Param.setAttribute("users", users);
				
				return "planEdit";
			}
			
			 //保存编辑教学计划
			adminManager.updatePlan(paramsPlan);
			
			setSuccessTip("编辑成功", "Admin_listPlans.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("plan", paramsPlan);
			
			//查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			Course course = new Course();
			course.setStart(-1);
			course.setCourse_type(1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);
			
			//查询教师字典
			User user = new User();
			user.setStart(-1);
			user.setUser_type(2);
			List<User> users = adminManager.listUsers(user, null);
			Param.setAttribute("users", users);
			
			return "planEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delPlans
	 * @Description: 删除教学计划
	 * @return String
	 */
	public  String delPlans(){
		try {
			 //删除教学计划
			adminManager.delPlans(paramsPlan);
			
			setSuccessTip("删除教学计划成功", "Admin_listPlans.action");
		} catch (Exception e) {
			setErrorTip("删除教学计划异常", "Admin_listPlans.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listCplans
	 * @Description: 查询班级课表
	 * @return String
	 */
	public  String listCplans(){
		try {
			if (paramsCplan==null) {
				paramsCplan = new Cplan();
			}
			//设置分页信息
			setPagination(paramsCplan);
			//总的条数
			int[] sum={0};
			//查询班级课表列表
			List<Cplan> cplans = adminManager.listCplans(paramsCplan,sum); 
			
			Param.setAttribute("cplans", cplans);
			setTotalCount(sum[0]);
			
			//查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			Course course = new Course();
			course.setStart(-1);
			course.setCourse_type(1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);
			
		} catch (Exception e) {
			setErrorTip("查询班级课表异常", "main.jsp");
			return "infoTip";
		}
		
		return "cplanShow";
	}
	
	/**
	 * @Title: listCplansByClazz
	 * @Description: 查询班级课表一周视图
	 * @return String
	 */
	public  String listCplansByClazzShow(){
		try {
			//查询班级
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
		} catch (Exception e) {
			setErrorTip("查询班级课表一周视图异常", "main.jsp");
			return "infoTip";
		}
		
		return "cplanByClazzShow";
	}
	
	public  String listCplansByClazz(){
		try {
			if (paramsCplan==null) {
				paramsCplan = new Cplan();
			}
			//查询班级课表列表
			List<Cplan> cplans = adminManager.listCplansByClazz(paramsCplan); 
			Param.setAttribute("cplans", cplans);
			
			//查询班级
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
		} catch (Exception e) {
			setErrorTip("查询班级课表异常", "main.jsp");
			return "infoTip";
		}
		
		return "cplanByClazzShow";
	}
	
	/**
	 * @Title: addCplanShow
	 * @Description: 显示添加班级课表页面
	 * @return String
	 */
	public  String addCplanShow(){
		//查询班级、课程字典
		Clazz clazz = new Clazz();
		clazz.setStart(-1);
		List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
		Param.setAttribute("clazzs", clazzs);
		
		Course course = new Course();
		course.setStart(-1);
		course.setCourse_type(1);
		List<Course> courses = adminManager.listCourses(course, null);
		Param.setAttribute("courses", courses);
		
		return "cplanEdit";
	}
	
	/**
	 * @Title: addCplan
	 * @Description: 添加班级课表
	 * @return String
	 */
	public  String addCplan(){
		try {
			//判断班级课表是否已经添加
			Cplan cplan = adminManager.queryCplan(paramsCplan);
			if (cplan!=null) {
				tip = "失败，该班级本次班级课表已经存在！";
				Param.setAttribute("cplan", paramsCplan);
				
				//查询班级、课程字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);
				
				Course course = new Course();
				course.setStart(-1);
				course.setCourse_type(1);
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);
				
				return "cplanEdit";
			}
			
			//添加班级课表
			adminManager.addCplan(paramsCplan);
			
			setSuccessTip("添加成功", "Admin_listCplans.action");
		} catch (Exception e) {
			setErrorTip("添加班级课表异常", "Admin_listCplans.action");
			e.printStackTrace();
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editCplan
	 * @Description: 编辑班级课表
	 * @return String
	 */
	public  String editCplan(){
		try {
			 //得到班级课表
			Cplan cplan = adminManager.queryCplan(paramsCplan);
			Param.setAttribute("cplan", cplan);
			
			//查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			Course course = new Course();
			course.setStart(-1);
			course.setCourse_type(1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);
		} catch (Exception e) {
			setErrorTip("查询班级课表异常", "Admin_listCplans.action");
			return "infoTip";
		}
		
		return "cplanEdit";
	}
	
	/**
	 * @Title: saveCplan
	 * @Description: 保存编辑班级课表
	 * @return String
	 */
	public  String saveCplan(){
		try {
			 //验证班级课表是否存在
			int cplan_id = paramsCplan.getCplan_id();
			paramsCplan.setCplan_id(0);
			Cplan cplan = adminManager.queryCplan(paramsCplan);
			paramsCplan.setCplan_id(cplan_id);
			if (cplan!=null && cplan.getCplan_id()!=cplan_id) {
				 tip = "失败，该班级本次班级课表已经存在！";
				 Param.setAttribute("cplan", paramsCplan);
				 
				//查询班级、课程字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);
				
				Course course = new Course();
				course.setStart(-1);
				course.setCourse_type(1);
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);
				
				return "cplanEdit";
			}
			
			 //保存编辑班级课表
			adminManager.updateCplan(paramsCplan);
			
			setSuccessTip("编辑成功", "Admin_listCplans.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("cplan", paramsCplan);
			
			//查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			Course course = new Course();
			course.setStart(-1);
			course.setCourse_type(1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);
			
			return "cplanEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delCplans
	 * @Description: 删除班级课表
	 * @return String
	 */
	public  String delCplans(){
		try {
			 //删除班级课表
			adminManager.delCplans(paramsCplan);
			
			setSuccessTip("删除班级课表成功", "Admin_listCplans.action");
		} catch (Exception e) {
			setErrorTip("删除班级课表异常", "Admin_listCplans.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listScources
	 * @Description: 查询学生选课
	 * @return String
	 */
	public  String listScources(){
		try {
			if (paramsScource==null) {
				paramsScource = new Scource();
			}
			//设置分页信息
			setPagination(paramsScource);
			//总的条数
			int[] sum={0};
			//查询学生选课列表
			User admin = (User)Param.getSession("admin");
			paramsScource.setUser_id(admin.getUser_id());
			List<Scource> scources = adminManager.listScources(paramsScource,sum); 
			
			Param.setAttribute("scources", scources);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询学生选课异常", "main.jsp");
			return "infoTip";
		}
		
		return "scourceShow";
	}
	
	/**
	 * @Title: addScourceShow
	 * @Description: 显示添加学生选课页面
	 * @return String
	 */
	public  String addScourceShow(){
		try {
			if (paramsCourse==null) {
				paramsCourse = new Course();
			}
			//设置分页信息
			setPagination(paramsCourse);
			//总的条数
			int[] sum={0};
			//查询课程列表
			paramsCourse.setCourse_type(2);
			List<Course> courses = adminManager.listCourses(paramsCourse,sum); 
			
			Param.setAttribute("courses", courses);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询课程异常", "main.jsp");
			return "infoTip";
		}
		
		return "scourceEdit";
	}
	
	/**
	 * @Title: addScource
	 * @Description: 添加学生选课
	 * @return String
	 */
	public  String addScource(){
		try {
			//判断学生选课是否已经添加
			Scource scource = adminManager.queryScource(paramsScource);
			if (scource!=null) {
				setErrorTip("失败，该课程已经选修了！", "Admin_addScourceShow.action");
				return "infoTip";
			}
			
			//添加学生选课
			adminManager.addScource(paramsScource);
			
			setSuccessTip("选修成功", "Admin_listScources.action");
		} catch (Exception e) {
			setErrorTip("选修异常", "Admin_addScourceShow.action");
			e.printStackTrace();
		}
		
		return "infoTip";
	}
	 
	
	/**
	 * @Title: delScources
	 * @Description: 删除学生选课
	 * @return String
	 */
	public  String delScources(){
		try {
			 //删除学生选课
			adminManager.delScources(paramsScource);
			
			setSuccessTip("删除学生选课成功", "Admin_listScources.action");
		} catch (Exception e) {
			setErrorTip("删除学生选课异常", "Admin_listScources.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listScores
	 * @Description: 查询成绩
	 * @return String
	 */
	public  String listScores(){
		try {
			if (paramsScore==null) {
				paramsScore = new Score();
			}
			//设置分页信息
			setPagination(paramsScore);
			//总的条数
			int[] sum={0};
			//查询成绩列表
			User admin = (User)Param.getSession("admin");//查询当前用户
			if (admin.getUser_type()==2) {
				paramsScore.setTeacher_id(admin.getUser_id());//设置教师为当前用户
			}else if (admin.getUser_type()==1) {
				paramsScore.setUser_id(admin.getUser_id());//设置学生为当前用户
			}
			
			List<Score> scores = adminManager.listScores(paramsScore,sum); 
			
			Param.setAttribute("scores", scores);
			setTotalCount(sum[0]);
			
			//查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			Course course = new Course();
			course.setStart(-1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);
			
		} catch (Exception e) {
			setErrorTip("查询成绩异常", "main.jsp");
			return "infoTip";
		}
		
		return "scoreShow";
	}
	
	/**
	 * @Title: listScoresSum
	 * @Description: 查询成绩
	 * @return String
	 */
	public  String listScoresSum(){
		try {
			if (paramsScore==null) {
				paramsScore = new Score();
			}
			//设置分页信息
			setPagination(paramsScore);
			//总的条数
			int[] sum={0};
			//查询成绩列表
			User admin = (User)Param.getSession("admin");//查询当前用户
			if (admin.getUser_type()==2) {
				paramsScore.setTeacher_id(admin.getUser_id());//设置教师为当前用户
			}else if (admin.getUser_type()==1) {
				paramsScore.setUser_id(admin.getUser_id());//设置学生为当前用户
			}
			
			List<Score> scores = adminManager.listScoresSum(paramsScore,sum); 
			
			Param.setAttribute("scores", scores);
			setTotalCount(sum[0]);
			
			//查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
			
			Course course = new Course();
			course.setStart(-1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);
			
		} catch (Exception e) {
			setErrorTip("查询成绩异常", "main.jsp");
			return "infoTip";
		}
		
		return "scoreSumShow";
	}
	
	/**
	 * @Title: addScoreShow
	 * @Description: 显示添加成绩页面
	 * @return String
	 */
	public  String addScoreShow(){
		return "scoreEdit";
	}
	
	/**
	 * @Title: addScoreSelectShow
	 * @Description: 添加选修成绩
	 * @return String
	 */
	public  String addScoreSelectShow(){
		//查询课程
		User admin = (User)Param.getSession("admin");
		Course course = new Course();
		course.setStart(-1);
		course.setCourse_type(2);//类型为选修
		course.setTeacher_id(admin.getUser_id());//设置教师ID
		List<Course> courses = adminManager.listCourses(course, null);
		if (courses==null || courses.size()==0) {
			courses = new ArrayList<Course>();
		}
		Param.setAttribute("courses", courses);
		
		return "scoreSelectEdit";
	}
	
	/**
	 * @Title: addScore
	 * @Description: 添加成绩
	 * @return String
	 */
	public  String addScore(){
		try {
			//判断成绩是否已经添加
			Score score = adminManager.queryScore(paramsScore);
			if (score!=null) {
				tip = "失败，该学生本次成绩已经存在！";
				Param.setAttribute("score", paramsScore);
				
				return "scoreEdit";
			}
			
			//添加成绩
			adminManager.addScore(paramsScore);
			
			setSuccessTip("添加成功", "Admin_listScores.action");
		} catch (Exception e) {
			setErrorTip("添加成绩异常", "Admin_listScores.action");
			e.printStackTrace();
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: addScoreSelect
	 * @Description: 添加选修成绩
	 * @return String
	 */
	public  String addScoreSelect(){
		try {
			//判断成绩是否已经添加
			Score score = adminManager.queryScore(paramsScore);
			if (score!=null) {
				tip = "失败，该学生本次成绩已经存在！";
				Param.setAttribute("score", paramsScore);
				
				return "scoreSelectEdit";
			}
			
			//添加成绩
			adminManager.addScore(paramsScore);
			
			setSuccessTip("添加成功", "Admin_listScores.action");
		} catch (Exception e) {
			setErrorTip("添加成绩异常", "Admin_listScores.action");
			e.printStackTrace();
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editScore
	 * @Description: 编辑成绩
	 * @return String
	 */
	public  String editScore(){
		String returnPage = "scoreEdit";//返回界面
		try {
			 //得到成绩
			Score score = adminManager.queryScore(paramsScore);
			Param.setAttribute("score", score);
			
			//如果是选修课，查询课程字典
			if(score.getCourse_type()==2){
				User admin = (User)Param.getSession("admin");
				Course course = new Course();
				course.setStart(-1);
				course.setCourse_type(2);//类型为选修
				course.setTeacher_id(admin.getUser_id());//设置教师ID
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);
				
				returnPage = "scoreSelectEdit";
			}
			
		} catch (Exception e) {
			setErrorTip("查询成绩异常", "Admin_listScores.action");
			return "infoTip";
		}
		
		return returnPage;
	}
	
	/**
	 * @Title: saveScore
	 * @Description: 保存编辑成绩
	 * @return String
	 */
	public  String saveScore(){
		String returnPage = "scoreEdit";//返回界面
		try {
			//如果是选修课，修改返回界面
			if(paramsScore.getCourse_type()==2){
				returnPage = "scoreSelectEdit";
			}
			
			//判断成绩是否已经添加
			int score_id = paramsScore.getScore_id();
			paramsScore.setScore_id(0);
			Score score = adminManager.queryScore(paramsScore);
			paramsScore.setScore_id(score_id);
			if (score!=null && score.getScore_id()!=score_id) {
				tip = "失败，该学生本次成绩已经存在！";
				Param.setAttribute("score", paramsScore);
				
				//如果是选修课，查询课程字典
				if(paramsScore.getCourse_type()==2){
					User admin = (User)Param.getSession("admin");
					Course course = new Course();
					course.setStart(-1);
					course.setCourse_type(2);//类型为选修
					course.setTeacher_id(admin.getUser_id());//设置教师ID
					List<Course> courses = adminManager.listCourses(course, null);
					Param.setAttribute("courses", courses);
				}
				
				return returnPage;
			}
			 //保存编辑成绩
			adminManager.updateScore(paramsScore);
			
			setSuccessTip("编辑成功", "Admin_listScores.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("score", paramsScore);
			
			//如果是选修课，查询课程字典
			if(paramsScore.getCourse_type()==2){
				User admin = (User)Param.getSession("admin");
				Course course = new Course();
				course.setStart(-1);
				course.setCourse_type(2);//类型为选修
				course.setTeacher_id(admin.getUser_id());//设置教师ID
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);
			}
			
			return returnPage;
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delScores
	 * @Description: 删除成绩
	 * @return String
	 */
	public  String delScores(){
		try {
			 //删除成绩
			adminManager.delScores(paramsScore);
			
			setSuccessTip("删除成绩成功", "Admin_listScores.action");
		} catch (Exception e) {
			setErrorTip("删除成绩异常", "Admin_listScores.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: listEvaluates
	 * @Description: 查询教师评教
	 * @return String
	 */
	public  String listEvaluates(){
		try {
			if (paramsEvaluate==null) {
				paramsEvaluate = new Evaluate();
			}
			//设置分页信息
			setPagination(paramsEvaluate);
			//总的条数
			int[] sum={0};
			//查询教师评教列表
			User admin = (User)Param.getSession("admin");//查询当前用户
			if (admin.getUser_type()==1) {
				paramsEvaluate.setStudent_id(admin.getUser_id());//设置学生为当前用户
			}
			
			List<Evaluate> evaluates = adminManager.listEvaluates(paramsEvaluate,sum); 
			
			Param.setAttribute("evaluates", evaluates);
			setTotalCount(sum[0]);
		} catch (Exception e) {
			setErrorTip("查询教师评教异常", "main.jsp");
			return "infoTip";
		}
		
		return "evaluateShow";
	}
	
	/**
	 * @Title: listEvaluatesSum
	 * @Description: 教学评教平均得分
	 * @return String
	 */
	public  String listEvaluatesSum(){
		try {
			if (paramsEvaluate==null) {
				paramsEvaluate = new Evaluate();
			}
			//设置分页信息
			setPagination(paramsEvaluate);
			//总的条数
			int[] sum={0};
			//查询教师评教列表
			List<Evaluate> evaluates = adminManager.listEvaluatesSum(paramsEvaluate,sum); 
			
			Param.setAttribute("evaluates", evaluates);
			setTotalCount(sum[0]);
			
		} catch (Exception e) {
			setErrorTip("查询教师评教异常", "main.jsp");
			return "infoTip";
		}
		
		return "evaluateSumShow";
	}
	
	/**
	 * @Title: addEvaluateShow
	 * @Description: 显示添加教师评教页面
	 * @return String
	 */
	public  String addEvaluateShow(){
		//查询教师字典
		User user = new User();
		user.setStart(-1);
		user.setUser_type(2);
		List<User> users = adminManager.listUsers(user, null);
		Param.setAttribute("teachers", users);
		
		return "evaluateEdit";
	}
	
	/**
	 * @Title: addEvaluate
	 * @Description: 添加教师评教
	 * @return String
	 */
	public  String addEvaluate(){
		try {
			//判断教师评教是否已经添加
			Evaluate evaluate = adminManager.queryEvaluate(paramsEvaluate);
			if (evaluate!=null) {
				tip = "失败，本次教师评教已经存在！";
				Param.setAttribute("evaluate", paramsEvaluate);
				
				//查询教师字典
				User user = new User();
				user.setStart(-1);
				user.setUser_type(2);
				List<User> users = adminManager.listUsers(user, null);
				Param.setAttribute("teachers", users);
				
				return "evaluateEdit";
			}
			
			//添加教师评教
			adminManager.addEvaluate(paramsEvaluate);
			
			setSuccessTip("添加教师评教成功", "Admin_listEvaluates.action");
		} catch (Exception e) {
			setErrorTip("添加教师评教异常", "Admin_listEvaluates.action");
			e.printStackTrace();
		}
		
		return "infoTip";
	}
	
	 
	/**
	 * @Title: editEvaluate
	 * @Description: 编辑教师评教
	 * @return String
	 */
	public  String editEvaluate(){
		try {
			 //得到教师评教
			Evaluate evaluate = adminManager.queryEvaluate(paramsEvaluate);
			Param.setAttribute("evaluate", evaluate);
			
			//查询教师字典
			User user = new User();
			user.setStart(-1);
			user.setUser_type(2);
			List<User> users = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", users);
			
		} catch (Exception e) {
			setErrorTip("查询教师评教异常", "Admin_listEvaluates.action");
			return "infoTip";
		}
		
		return "evaluateEdit";
	}
	
	/**
	 * @Title: saveEvaluate
	 * @Description: 保存编辑教师评教
	 * @return String
	 */
	public  String saveEvaluate(){
		try {
			 //保存编辑教师评教
			adminManager.updateEvaluate(paramsEvaluate);
			
			setSuccessTip("编辑成功", "Admin_listEvaluates.action");
		} catch (Exception e) {
			tip="编辑失败";
			Param.setAttribute("evaluate", paramsEvaluate);
			
			//查询教师字典
			User user = new User();
			user.setStart(-1);
			user.setUser_type(2);
			List<User> users = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", users);
			
			return "evaluateEdit";
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: delEvaluates
	 * @Description: 删除教师评教
	 * @return String
	 */
	public  String delEvaluates(){
		try {
			 //删除教师评教
			adminManager.delEvaluates(paramsEvaluate);
			
			setSuccessTip("删除教师评教成功", "Admin_listEvaluates.action");
		} catch (Exception e) {
			setErrorTip("删除教师评教异常", "Admin_listEvaluates.action");
		}
		
		return "infoTip";
	}
	
	/**
	 * @Title: validateAdmin
	 * @Description: admin登录验证
	 * @return boolean
	 */
	private  boolean validateAdmin(){
		User admin = (User)Param.getSession("admin");
		if (admin!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	private  void setErrorTip(String tip,String url){
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}
	
	private  void setSuccessTip(String tip,String url){
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	public  User getParamsUser() {
		return paramsUser;
	}

	public  void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public  Clazz getParamsClazz() {
		return paramsClazz;
	}

	public  void setParamsClazz(Clazz paramsClazz) {
		this.paramsClazz = paramsClazz;
	}

	public  Course getParamsCourse() {
		return paramsCourse;
	}

	public  void setParamsCourse(Course paramsCourse) {
		this.paramsCourse = paramsCourse;
	}

	public  Score getParamsScore() {
		return paramsScore;
	}

	public  void setParamsScore(Score paramsScore) {
		this.paramsScore = paramsScore;
	}

	public  String getTip() {
		return tip;
	}

	public  void setTip(String tip) {
		this.tip = tip;
	}

	public  Plan getParamsPlan() {
		return paramsPlan;
	}

	public  void setParamsPlan(Plan paramsPlan) {
		this.paramsPlan = paramsPlan;
	}

	public  Cplan getParamsCplan() {
		return paramsCplan;
	}

	public  void setParamsCplan(Cplan paramsCplan) {
		this.paramsCplan = paramsCplan;
	}

	public  Scource getParamsScource() {
		return paramsScource;
	}

	public  void setParamsScource(Scource paramsScource) {
		this.paramsScource = paramsScource;
	}

	public  Evaluate getParamsEvaluate() {
		return paramsEvaluate;
	}

	public  void setParamsEvaluate(Evaluate paramsEvaluate) {
		this.paramsEvaluate = paramsEvaluate;
	}
	
	 
	
}
