package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Course;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class  CourseDao {

	public int addCourse(Course course, Connection conn){
		String sql = "INSERT INTO course(course_id,course_name,course_type,teacher_id,note) values(null,?,?,?,?)";
		Object[] params = new Object[] {
			course.getCourse_name(),
			course.getCourse_type(),
			course.getTeacher_id(),
			course.getNote()
 
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delCourse(String course_id, Connection conn){
		String sql = "DELETE FROM score WHERE course_id=?";

		Object[] params = new Object[] { new Integer(course_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delCourses(String[] course_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <course_ids.length; i++) {
			sBuilder.append("?");
			if (i !=course_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM course WHERE course_id IN(" +sBuilder.toString()+")";

		Object[] params = course_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateCourse(Course course, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE course SET course_id = " + course.getCourse_id() +" ");
		if (!StringUtil.isEmptyString(course.getCourse_name())) {
			sBuilder.append(" ,course_name = '" + course.getCourse_name() +"' ");
		}
		if (course.getCourse_type()!=0) {
			sBuilder.append(" ,course_type = " + course.getCourse_type() +" ");
		}
		if (course.getTeacher_id()!=0) {
			sBuilder.append(" ,teacher_id = " + course.getTeacher_id() +" ");
		}
		if (!StringUtil.isEmptyString(course.getNote())) {
			sBuilder.append(" ,note = '" + course.getNote() +"' ");
		}
		sBuilder.append("where course_id = " + course.getCourse_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public  Course getCourse(Course course, Connection conn){
		Course _course=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT c.*,u.real_name teacher_name FROM course c left join user u on c.teacher_id=u.user_id WHERE 1=1");
		if (course.getCourse_id()!=0) {
			sBuilder.append(" and course_id = " + course.getCourse_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Course.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _course = (Course)list.get(0);
		}
		return _course;
	}

	public  List<Course>  listCourses(Course course, Connection conn){
		List<Course> courses = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT c.*,u.real_name teacher_name FROM course c left join user u on c.teacher_id=u.user_id WHERE 1=1");

		if (course.getCourse_id()!=0) {
			sBuilder.append(" and course_id = " + course.getCourse_id() +" ");
		}
		if (!StringUtil.isEmptyString(course.getCourse_name())) {
			sBuilder.append(" and course_name like '%" + course.getCourse_name() +"%' ");
		}
		if (course.getCourse_type()!=0) {
			sBuilder.append(" and course_type = " + course.getCourse_type() +" ");
		}
		if (course.getTeacher_id()!=0) {
			sBuilder.append(" and teacher_id = " + course.getTeacher_id() +" ");
		}
		if (!StringUtil.isEmptyString(course.getTeacher_name())) {
			sBuilder.append(" and real_name like '%" + course.getTeacher_name() +"%' ");
		}
		if (course.getUser_id()!=0) {
			sBuilder.append(" and course_id in (select course_id from plan where user_id = " + course.getUser_id() 
					+" and plan_year=" + course.getScore_year()+" and plan_year_half=" + course.getScore_year_half() +") ");
		}
		
		sBuilder.append(" order by course_id asc) t");

		if (course.getStart() != -1) {
			sBuilder.append(" limit " + course.getStart() + "," + course.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Course.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			courses = new ArrayList<Course>();
			for (Object object : list) {
				courses.add((Course)object);
			}
		}
		return courses;
	}

	public int  listCoursesCount(Course course, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM course c left join user u on c.teacher_id=u.user_id WHERE 1=1");

		if (course.getCourse_id()!=0) {
			sBuilder.append(" and course_id = " + course.getCourse_id() +" ");
		}
		if (!StringUtil.isEmptyString(course.getCourse_name())) {
			sBuilder.append(" and course_name like '%" + course.getCourse_name() +"%' ");
		}
		if (course.getCourse_type()!=0) {
			sBuilder.append(" and course_type = " + course.getCourse_type() +" ");
		}
		if (course.getTeacher_id()!=0) {
			sBuilder.append(" and teacher_id = " + course.getTeacher_id() +" ");
		}
		if (!StringUtil.isEmptyString(course.getTeacher_name())) {
			sBuilder.append(" and real_name like '%" + course.getTeacher_name() +"%' ");
		}
		if (course.getUser_id()!=0) {
			sBuilder.append(" and course_id in (select course_id from plan where user_id = " + course.getUser_id() 
					+" and plan_year=" + course.getScore_year()+" and plan_year_half=" + course.getScore_year_half() +") ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
