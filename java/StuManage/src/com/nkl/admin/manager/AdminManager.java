package com.nkl.admin.manager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.nkl.admin.dao.ClazzDao;
import com.nkl.admin.dao.CourseDao;
import com.nkl.admin.dao.CplanDao;
import com.nkl.admin.dao.EvaluateDao;
import com.nkl.admin.dao.PlanDao;
import com.nkl.admin.dao.ScoreDao;
import com.nkl.admin.dao.ScourceDao;
import com.nkl.admin.dao.UserDao;
import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Course;
import com.nkl.admin.domain.Cplan;
import com.nkl.admin.domain.Evaluate;
import com.nkl.admin.domain.Plan;
import com.nkl.admin.domain.Score;
import com.nkl.admin.domain.Scource;
import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;

public class  AdminManager {

	ClazzDao clazzDao = new ClazzDao();
	CourseDao courseDao = new CourseDao();
	CplanDao cplanDao = new CplanDao();
	EvaluateDao evaluateDao = new EvaluateDao();
	PlanDao planDao = new PlanDao();
	ScoreDao scoreDao = new ScoreDao();
	ScourceDao scourceDao = new ScourceDao();
	UserDao userDao = new UserDao();
	 

	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public  List<User> listUsers(User user, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = userDao.listUsersCount(user, conn);
		}
		List<User> users = userDao.listUsers(user, conn);

		BaseDao.closeDB(null, null, conn);
		return users;
	}

	/**
	 * @Title: queryUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public  User queryUser(User user) {
		Connection conn = BaseDao.getConnection();
		User _user = userDao.getUser(user, conn);
		BaseDao.closeDB(null, null, conn);
		return _user;
	}

	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public  void addUser(User user) {
		Connection conn = BaseDao.getConnection();
		user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		userDao.addUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: addUserBatch
	 * @Description: 添加用户
	 * @param List<Score>
	 * @return void
	 * @throws SQLException 
	 */
	public  void addUserBatch(List<User> users) throws SQLException {
		Connection conn = BaseDao.getConnection();
		conn.setAutoCommit(false);
		for (int i = 0; i < users.size(); i++) {
			userDao.addUser(users.get(i), conn);
			if ((i+1) % 500==0) {//每500行提交一次
				conn.commit();
			}
		}
		conn.commit();
		conn.setAutoCommit(true);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public  void updateUser(User user) {
		Connection conn = BaseDao.getConnection();
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public  void delUsers(User user) {
		Connection conn = BaseDao.getConnection();
		userDao.delUsers(user.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: listClazzs
	 * @Description: 班级查询
	 * @param clazz
	 * @return List<Clazz>
	 */
	public  List<Clazz> listClazzs(Clazz clazz, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = clazzDao.listClazzsCount(clazz, conn);
		}
		List<Clazz> clazzs = clazzDao.listClazzs(clazz, conn);

		BaseDao.closeDB(null, null, conn);
		return clazzs;
	}

	/**
	 * @Title: queryClazz
	 * @Description: 班级查询
	 * @param clazz
	 * @return Clazz
	 */
	public  Clazz queryClazz(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		Clazz _clazz = clazzDao.getClazz(clazz, conn);
		BaseDao.closeDB(null, null, conn);
		return _clazz;
	}

	/**
	 * @Title: addClazz
	 * @Description: 添加班级
	 * @param clazz
	 * @return void
	 */
	public  void addClazz(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		clazzDao.addClazz(clazz, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateClazz
	 * @Description: 更新班级信息
	 * @param clazz
	 * @return void
	 */
	public  void updateClazz(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		clazzDao.updateClazz(clazz, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delClazzs
	 * @Description: 删除班级信息
	 * @param clazz
	 * @return void
	 */
	public  void delClazzs(Clazz clazz) {
		Connection conn = BaseDao.getConnection();
		clazzDao.delClazzs(clazz.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: listCourses
	 * @Description: 课程查询
	 * @param course
	 * @return List<Course>
	 */
	public  List<Course> listCourses(Course course, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = courseDao.listCoursesCount(course, conn);
		}
		List<Course> courses = courseDao.listCourses(course, conn);

		BaseDao.closeDB(null, null, conn);
		return courses;
	}

	/**
	 * @Title: queryCourse
	 * @Description: 课程查询
	 * @param course
	 * @return Course
	 */
	public  Course queryCourse(Course course) {
		Connection conn = BaseDao.getConnection();
		Course _course = courseDao.getCourse(course, conn);
		BaseDao.closeDB(null, null, conn);
		return _course;
	}

	/**
	 * @Title: addCourse
	 * @Description: 添加课程
	 * @param course
	 * @return void
	 */
	public  void addCourse(Course course) {
		Connection conn = BaseDao.getConnection();
		courseDao.addCourse(course, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateCourse
	 * @Description: 更新课程信息
	 * @param course
	 * @return void
	 */
	public  void updateCourse(Course course) {
		Connection conn = BaseDao.getConnection();
		courseDao.updateCourse(course, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delCourses
	 * @Description: 删除课程信息
	 * @param course
	 * @return void
	 */
	public  void delCourses(Course course) {
		Connection conn = BaseDao.getConnection();
		courseDao.delCourses(course.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listPlans
	 * @Description: 教学计划查询
	 * @param plan
	 * @return List<Plan>
	 */
	public  List<Plan> listPlans(Plan plan, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = planDao.listPlansCount(plan, conn);
		}
		List<Plan> plans = planDao.listPlans(plan, conn);

		BaseDao.closeDB(null, null, conn);
		return plans;
	}
	
	/**
	 * @Title: queryPlan
	 * @Description: 教学计划查询
	 * @param plan
	 * @return Plan
	 */
	public  Plan queryPlan(Plan plan) {
		Connection conn = BaseDao.getConnection();
		Plan _plan = planDao.getPlan(plan, conn);
		BaseDao.closeDB(null, null, conn);
		return _plan;
	}

	/**
	 * @Title: addPlan
	 * @Description: 添加教学计划
	 * @param plan
	 * @return void
	 */
	public  void addPlan(Plan plan) {
		Connection conn = BaseDao.getConnection();
		planDao.addPlan(plan, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updatePlan
	 * @Description: 更新教学计划信息
	 * @param plan
	 * @return void
	 */
	public  void updatePlan(Plan plan) {
		Connection conn = BaseDao.getConnection();
		planDao.updatePlan(plan, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delPlans
	 * @Description: 删除教学计划信息
	 * @param plan
	 * @return void
	 */
	public  void delPlans(Plan plan) {
		Connection conn = BaseDao.getConnection();
		planDao.delPlans(plan.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listCplans
	 * @Description: 班级课表查询
	 * @param cplan
	 * @return List<Cplan>
	 */
	public  List<Cplan> listCplans(Cplan cplan, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = cplanDao.listCplansCount(cplan, conn);
		}
		List<Cplan> cplans = cplanDao.listCplans(cplan, conn);

		BaseDao.closeDB(null, null, conn);
		return cplans;
	}
	
	/**
	 * @Title: listCplansByClazz
	 * @Description: 班级课表一周视图查询
	 * @param cplan
	 * @return List<Cplan>
	 */
	public  List<Cplan> listCplansByClazz(Cplan cplan) {
		Connection conn = BaseDao.getConnection();
		List<Cplan> cplans = cplanDao.listCplansByClazz(cplan, conn);

		BaseDao.closeDB(null, null, conn);
		return cplans;
	}
	
	/**
	 * @Title: queryCplan
	 * @Description: 班级课表查询
	 * @param cplan
	 * @return Cplan
	 */
	public  Cplan queryCplan(Cplan cplan) {
		Connection conn = BaseDao.getConnection();
		Cplan _cplan = cplanDao.getCplan(cplan, conn);
		BaseDao.closeDB(null, null, conn);
		return _cplan;
	}

	/**
	 * @Title: addCplan
	 * @Description: 添加班级课表
	 * @param cplan
	 * @return void
	 */
	public  void addCplan(Cplan cplan) {
		Connection conn = BaseDao.getConnection();
		cplanDao.addCplan(cplan, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateCplan
	 * @Description: 更新班级课表信息
	 * @param cplan
	 * @return void
	 */
	public  void updateCplan(Cplan cplan) {
		Connection conn = BaseDao.getConnection();
		cplanDao.updateCplan(cplan, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delCplans
	 * @Description: 删除班级课表信息
	 * @param cplan
	 * @return void
	 */
	public  void delCplans(Cplan cplan) {
		Connection conn = BaseDao.getConnection();
		cplanDao.delCplans(cplan.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listScources
	 * @Description: 学生选课查询
	 * @param scource
	 * @return List<Scource>
	 */
	public  List<Scource> listScources(Scource scource, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = scourceDao.listScourcesCount(scource, conn);
		}
		List<Scource> scources = scourceDao.listScources(scource, conn);

		BaseDao.closeDB(null, null, conn);
		return scources;
	}
	
	/**
	 * @Title: queryScource
	 * @Description: 学生选课查询
	 * @param scource
	 * @return Scource
	 */
	public  Scource queryScource(Scource scource) {
		Connection conn = BaseDao.getConnection();
		Scource _scource = scourceDao.getScource(scource, conn);
		BaseDao.closeDB(null, null, conn);
		return _scource;
	}
	
	/**
	 * @Title: addScource
	 * @Description: 添加学生选课
	 * @param scource
	 * @return void
	 */
	public  void addScource(Scource scource) {
		Connection conn = BaseDao.getConnection();
		scourceDao.addScource(scource, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delScources
	 * @Description: 删除学生选课信息
	 * @param scource
	 * @return void
	 */
	public  void delScources(Scource scource) {
		Connection conn = BaseDao.getConnection();
		scourceDao.delScources(scource.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listScores
	 * @Description: 成绩查询
	 * @param score
	 * @return List<Score>
	 */
	public  List<Score> listScores(Score score, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = scoreDao.listScoresCount(score, conn);
		}
		List<Score> scores = scoreDao.listScores(score, conn);

		BaseDao.closeDB(null, null, conn);
		return scores;
	}
	
	/**
	 * @Title: listScoresSum
	 * @Description: 成绩查询
	 * @param score
	 * @return List<Score>
	 */
	public  List<Score> listScoresSum(Score score, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = scoreDao.listScoresSumCount(score, conn);
		}
		List<Score> scores = scoreDao.listScoresSum(score, conn);

		BaseDao.closeDB(null, null, conn);
		return scores;
	}
	
	/**
	 * @Title: queryScore
	 * @Description: 成绩查询
	 * @param score
	 * @return Score
	 */
	public  Score queryScore(Score score) {
		Connection conn = BaseDao.getConnection();
		Score _score = scoreDao.getScore(score, conn);
		BaseDao.closeDB(null, null, conn);
		return _score;
	}

	/**
	 * @Title: addScore
	 * @Description: 添加成绩
	 * @param score
	 * @return void
	 */
	public  void addScore(Score score) {
		Connection conn = BaseDao.getConnection();
		scoreDao.addScore(score, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: addScoreBatch
	 * @Description: 添加成绩
	 * @param List<Score>
	 * @return void
	 * @throws SQLException 
	 */
	public  void addScoreBatch(List<Score> scores) throws SQLException {
		Connection conn = BaseDao.getConnection();
		conn.setAutoCommit(false);
		for (int i = 0; i < scores.size(); i++) {
			scoreDao.importScore(scores.get(i), conn);
			if ((i+1) % 500==0) {//每500行提交一次
				conn.commit();
			}
		}
		conn.commit();
		conn.setAutoCommit(true);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: updateScore
	 * @Description: 更新成绩信息
	 * @param score
	 * @return void
	 */
	public  void updateScore(Score score) {
		Connection conn = BaseDao.getConnection();
		scoreDao.updateScore(score, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delScores
	 * @Description: 删除成绩信息
	 * @param score
	 * @return void
	 */
	public  void delScores(Score score) {
		Connection conn = BaseDao.getConnection();
		scoreDao.delScores(score.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
	
	/**
	 * @Title: listEvaluates
	 * @Description: 教务评定查询
	 * @param evaluate
	 * @return List<Evaluate>
	 */
	public  List<Evaluate> listEvaluates(Evaluate evaluate, int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum != null) {
			sum[0] = evaluateDao.listEvaluatesCount(evaluate, conn);
		}
		List<Evaluate> evaluates = evaluateDao.listEvaluates(evaluate, conn);

		BaseDao.closeDB(null, null, conn);
		return evaluates;
	}
	
	/**
	 * @Title: listEvaluatesSum
	 * @Description: 教务评定平均成绩查询
	 * @param evaluate
	 * @return List<Evaluate>
	 */
	public  List<Evaluate> listEvaluatesSum(Evaluate evaluate,int[] sum) {
		Connection conn = BaseDao.getConnection();
		if (sum!=null) {
			sum[0] = evaluateDao.listEvaluatesCount(evaluate, conn);
		}
		List<Evaluate> evaluates = evaluateDao.listEvaluatesSum(evaluate, conn);

		BaseDao.closeDB(null, null, conn);
		return evaluates;
	}
	
	/**
	 * @Title: queryEvaluate
	 * @Description: 教务评定查询
	 * @param evaluate
	 * @return Evaluate
	 */
	public  Evaluate queryEvaluate(Evaluate evaluate) {
		Connection conn = BaseDao.getConnection();
		Evaluate _evaluate = evaluateDao.getEvaluate(evaluate, conn);
		BaseDao.closeDB(null, null, conn);
		return _evaluate;
	}

	/**
	 * @Title: addEvaluate
	 * @Description: 添加教务评定
	 * @param evaluate
	 * @return void
	 */
	public  void addEvaluate(Evaluate evaluate) {
		Connection conn = BaseDao.getConnection();
		evaluateDao.addEvaluate(evaluate, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: updateEvaluate
	 * @Description: 更新教务评定信息
	 * @param evaluate
	 * @return void
	 */
	public  void updateEvaluate(Evaluate evaluate) {
		Connection conn = BaseDao.getConnection();
		evaluateDao.updateEvaluate(evaluate, conn);
		BaseDao.closeDB(null, null, conn);
	}

	/**
	 * @Title: delEvaluates
	 * @Description: 删除教务评定信息
	 * @param evaluate
	 * @return void
	 */
	public  void delEvaluates(Evaluate evaluate) {
		Connection conn = BaseDao.getConnection();
		evaluateDao.delEvaluates(evaluate.getIds().split(","), conn);
		BaseDao.closeDB(null, null, conn);
	}
}
