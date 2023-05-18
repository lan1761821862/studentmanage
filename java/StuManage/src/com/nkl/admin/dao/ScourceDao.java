package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Scource;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class  ScourceDao {

	public int addScource(Scource scource, Connection conn){
		String sql = "INSERT INTO scource(scource_id,scource_year,scource_year_half,course_id,user_id) values(null,?,?,?,?)";
		Object[] params = new Object[] {
			scource.getScource_year(),
			scource.getScource_year_half(),
			scource.getCourse_id(),
			scource.getUser_id()
 
		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delScource(String scource_id, Connection conn){
		String sql = "DELETE FROM scource WHERE scource_id=?";

		Object[] params = new Object[] { new Integer(scource_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delScources(String[] scource_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <scource_ids.length; i++) {
			sBuilder.append("?");
			if (i !=scource_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM scource WHERE scource_id IN(" +sBuilder.toString()+")";

		Object[] params = scource_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateScource(Scource scource, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE scource SET scource_id = " + scource.getScource_id() +" ");
		if (scource.getScource_year()!=0) {
			sBuilder.append(" ,scource_year = " + scource.getScource_year() +" ");
		}
		if (scource.getScource_year_half()!=0) {
			sBuilder.append(" ,scource_year_half = " + scource.getScource_year_half() +" ");
		}
		if (scource.getCourse_id()!=0) {
			sBuilder.append(" ,course_id = " + scource.getCourse_id() +" ");
		}
		if (scource.getUser_id()!=0) {
			sBuilder.append(" ,user_id = " + scource.getUser_id() +" ");
		}
		sBuilder.append("where scource_id = " + scource.getScource_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public  Scource getScource(Scource scource, Connection conn){
		Scource _scource=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT p.*,co.course_name,u.real_name FROM scource p ");
		sBuilder.append("  join course co on p.course_id=co.course_id ");
		sBuilder.append("  join user u on p.user_id=u.user_id WHERE 1=1 ");
		if (scource.getScource_id()!=0) {
			sBuilder.append(" and scource_id = " + scource.getScource_id() +" ");
		}
		if (scource.getScource_year()!=0) {
			sBuilder.append(" and scource_year = " + scource.getScource_year() +" ");
		}
		if (scource.getScource_year_half()!=0) {
			sBuilder.append(" and scource_year_half = " + scource.getScource_year_half() +" ");
		}
		if (scource.getCourse_id()!=0) {
			sBuilder.append(" and p.course_id = " + scource.getCourse_id() +" ");
		}
		if (scource.getUser_id()!=0) {
			sBuilder.append(" and p.user_id = " + scource.getUser_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Scource.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _scource = (Scource)list.get(0);
		}
		return _scource;
	}

	public  List<Scource>  listScources(Scource scource, Connection conn){
		List<Scource> scources = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT p.*,co.course_name,u.real_name,u2.real_name teacher_name FROM scource p ");
		sBuilder.append("  join course co on p.course_id=co.course_id ");
		sBuilder.append("  join user u on p.user_id=u.user_id ");
		sBuilder.append("  join user u2 on co.teacher_id=u2.user_id WHERE 1=1 ");

		if (scource.getScource_id()!=0) {
			sBuilder.append(" and scource_id = " + scource.getScource_id() +" ");
		}
		if (scource.getScource_year()!=0) {
			sBuilder.append(" and scource_year = " + scource.getScource_year() +" ");
		}
		if (scource.getScource_year_half()!=0) {
			sBuilder.append(" and scource_year_half = " + scource.getScource_year_half() +" ");
		}
		if (scource.getCourse_id()!=0) {
			sBuilder.append(" and p.course_id = " + scource.getCourse_id() +" ");
		}
		if (!StringUtil.isEmptyString(scource.getCourse_name())) {
			sBuilder.append(" and co.course_name like '%" + scource.getCourse_name() +"%' ");
		}
		if (scource.getUser_id()!=0) {
			sBuilder.append(" and p.user_id = " + scource.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(scource.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + scource.getReal_name() +"%' ");
		}
		sBuilder.append(" order by scource_id asc) t");

		if (scource.getStart() != -1) {
			sBuilder.append(" limit " + scource.getStart() + "," + scource.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Scource.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			scources = new ArrayList<Scource>();
			for (Object object : list) {
				scources.add((Scource)object);
			}
		}
		return scources;
	}

	public int  listScourcesCount(Scource scource, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM scource p ");
		sBuilder.append("  join course co on p.course_id=co.course_id ");
		sBuilder.append("  join user u on p.user_id=u.user_id ");
		sBuilder.append("  join user u2 on co.teacher_id=u2.user_id WHERE 1=1 ");

		if (scource.getScource_id()!=0) {
			sBuilder.append(" and scource_id = " + scource.getScource_id() +" ");
		}
		if (scource.getScource_year()!=0) {
			sBuilder.append(" and scource_year = " + scource.getScource_year() +" ");
		}
		if (scource.getScource_year_half()!=0) {
			sBuilder.append(" and scource_year_half = " + scource.getScource_year_half() +" ");
		}
		if (scource.getCourse_id()!=0) {
			sBuilder.append(" and p.course_id = " + scource.getCourse_id() +" ");
		}
		if (!StringUtil.isEmptyString(scource.getCourse_name())) {
			sBuilder.append(" and co.course_name like '%" + scource.getCourse_name() +"%' ");
		}
		if (scource.getUser_id()!=0) {
			sBuilder.append(" and p.user_id = " + scource.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(scource.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + scource.getReal_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
