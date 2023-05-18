package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Cplan;
import com.nkl.common.dao.BaseDao;

public class  CplanDao {

	public int addCplan(Cplan cplan, Connection conn){
		String sql = "INSERT INTO cplan(cplan_id,cplan_year,cplan_year_half,clazz_id,cplan_week,cplan_lesson,course_id,note) values(null,?,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			cplan.getCplan_year(),
			cplan.getCplan_year_half(),
			cplan.getClazz_id(),
			cplan.getCplan_week(),
			cplan.getCplan_lesson(),
			cplan.getCourse_id(),
			cplan.getNote()
 
		}; 
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delCplan(String cplan_id, Connection conn){
		String sql = "DELETE FROM cplan WHERE cplan_id=?";

		Object[] params = new Object[] { new Integer(cplan_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delCplans(String[] cplan_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <cplan_ids.length; i++) {
			sBuilder.append("?");
			if (i !=cplan_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM cplan WHERE cplan_id IN(" +sBuilder.toString()+")";

		Object[] params = cplan_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateCplan(Cplan cplan, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE cplan SET cplan_id = " + cplan.getCplan_id() +" ");
		if (cplan.getCplan_year()!=0) {
			sBuilder.append(" ,cplan_year = " + cplan.getCplan_year() +" ");
		}
		if (cplan.getCplan_year_half()!=0) {
			sBuilder.append(" ,cplan_year_half = " + cplan.getCplan_year_half() +" ");
		}
		if (cplan.getClazz_id()!=0) {
			sBuilder.append(" ,clazz_id = " + cplan.getClazz_id() +" ");
		}
		if (cplan.getCplan_week()!=0) {
			sBuilder.append(" ,cplan_week = " + cplan.getCplan_week() +" ");
		}
		if (cplan.getCplan_lesson()!=0) {
			sBuilder.append(" ,cplan_lesson = " + cplan.getCplan_lesson() +" ");
		}
		if (cplan.getCourse_id()!=0) {
			sBuilder.append(" ,course_id = " + cplan.getCourse_id() +" ");
		}
		
		sBuilder.append("where cplan_id = " + cplan.getCplan_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public  Cplan getCplan(Cplan cplan, Connection conn){
		Cplan _cplan=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT p.*,c.clazz_name,co.course_name FROM cplan p ");
		sBuilder.append("  join clazz c on p.clazz_id=c.clazz_id ");
		sBuilder.append("  join course co on p.course_id=co.course_id WHERE 1=1 ");
		if (cplan.getCplan_id()!=0) {
			sBuilder.append(" and cplan_id = " + cplan.getCplan_id() +" ");
		}
		if (cplan.getCplan_year()!=0) {
			sBuilder.append(" and cplan_year = " + cplan.getCplan_year() +" ");
		}
		if (cplan.getCplan_year_half()!=0) {
			sBuilder.append(" and cplan_year_half = " + cplan.getCplan_year_half() +" ");
		}
		if (cplan.getClazz_id()!=0) {
			sBuilder.append(" and p.clazz_id = " + cplan.getClazz_id() +" ");
		}
		if (cplan.getCplan_week()!=0) {
			sBuilder.append(" and cplan_week = " + cplan.getCplan_week() +" ");
		}
		if (cplan.getCplan_lesson()!=0) {
			sBuilder.append(" and cplan_lesson = " + cplan.getCplan_lesson() +" ");
		}
		if (cplan.getCourse_id()!=0) {
			sBuilder.append(" and p.course_id = " + cplan.getCourse_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Cplan.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _cplan = (Cplan)list.get(0);
		}
		return _cplan;
	}

	public  List<Cplan>  listCplans(Cplan cplan, Connection conn){
		List<Cplan> cplans = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT p.*,c.clazz_name,co.course_name FROM cplan p ");
		sBuilder.append("  join clazz c on p.clazz_id=c.clazz_id ");
		sBuilder.append("  join course co on p.course_id=co.course_id WHERE 1=1 ");

		if (cplan.getCplan_id()!=0) {
			sBuilder.append(" and cplan_id = " + cplan.getCplan_id() +" ");
		}
		if (cplan.getCplan_year()!=0) {
			sBuilder.append(" and cplan_year = " + cplan.getCplan_year() +" ");
		}
		if (cplan.getCplan_year_half()!=0) {
			sBuilder.append(" and cplan_year_half = " + cplan.getCplan_year_half() +" ");
		}
		if (cplan.getClazz_id()!=0) {
			sBuilder.append(" and p.clazz_id = " + cplan.getClazz_id() +" ");
		}
		if (cplan.getCplan_week()!=0) {
			sBuilder.append(" and cplan_week = " + cplan.getCplan_week() +" ");
		}
		if (cplan.getCplan_lesson()!=0) {
			sBuilder.append(" and cplan_lesson = " + cplan.getCplan_lesson() +" ");
		}
		if (cplan.getCourse_id()!=0) {
			sBuilder.append(" and p.course_id = " + cplan.getCourse_id() +" ");
		}
		sBuilder.append(" order by p.clazz_id,cplan_week,cplan_lesson,cplan_id asc) t");

		if (cplan.getStart() != -1) {
			sBuilder.append(" limit " + cplan.getStart() + "," + cplan.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Cplan.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			cplans = new ArrayList<Cplan>();
			for (Object object : list) {
				cplans.add((Cplan)object);
			}
		}
		return cplans;
	}

	public int  listCplansCount(Cplan cplan, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM cplan p ");
		sBuilder.append("  join clazz c on p.clazz_id=c.clazz_id ");
		sBuilder.append("  join course co on p.course_id=co.course_id WHERE 1=1 ");

		if (cplan.getCplan_id()!=0) {
			sBuilder.append(" and cplan_id = " + cplan.getCplan_id() +" ");
		}
		if (cplan.getCplan_year()!=0) {
			sBuilder.append(" and cplan_year = " + cplan.getCplan_year() +" ");
		}
		if (cplan.getCplan_year_half()!=0) {
			sBuilder.append(" and cplan_year_half = " + cplan.getCplan_year_half() +" ");
		}
		if (cplan.getClazz_id()!=0) {
			sBuilder.append(" and p.clazz_id = " + cplan.getClazz_id() +" ");
		}
		if (cplan.getCplan_week()!=0) {
			sBuilder.append(" and cplan_week = " + cplan.getCplan_week() +" ");
		}
		if (cplan.getCplan_lesson()!=0) {
			sBuilder.append(" and cplan_lesson = " + cplan.getCplan_lesson() +" ");
		}
		if (cplan.getCourse_id()!=0) {
			sBuilder.append(" and p.course_id = " + cplan.getCourse_id() +" ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}
	
	public  List<Cplan> listCplansByClazz(Cplan cplan, Connection conn){
		List<Cplan> cplans = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("select l.lesson as cplan_lesson,t.week1_lesson,t.week2_lesson,t.week3_lesson,t.week4_lesson,t.week5_lesson ");
		sBuilder.append("  from lessons l left join ( ");
		sBuilder.append("select p.cplan_lesson, ");
		sBuilder.append("  GROUP_CONCAT((case when p.cplan_week=1 then c.course_name else '' end) separator '') week1_lesson, ");
		sBuilder.append("  GROUP_CONCAT((case when p.cplan_week=2 then c.course_name else '' end) separator '') week2_lesson, ");
		sBuilder.append("  GROUP_CONCAT((case when p.cplan_week=3 then c.course_name else '' end) separator '') week3_lesson, ");
		sBuilder.append("  GROUP_CONCAT((case when p.cplan_week=4 then c.course_name else '' end) separator '') week4_lesson, ");
		sBuilder.append("  GROUP_CONCAT((case when p.cplan_week=5 then c.course_name else '' end) separator '') week5_lesson ");
		sBuilder.append("  FROM cplan p join course c on p.course_id=c.course_id ");
		sBuilder.append("  WHERE 1=1 ");
		if (cplan.getCplan_year()!=0) {
			sBuilder.append(" and cplan_year = " + cplan.getCplan_year() +" ");
		}
		if (cplan.getCplan_year_half()!=0) {
			sBuilder.append(" and cplan_year_half = " + cplan.getCplan_year_half() +" ");
		}
		if (cplan.getClazz_id()!=0) {
			sBuilder.append(" and p.clazz_id = " + cplan.getClazz_id() +" ");
		}
		sBuilder.append("  GROUP by p.cplan_lesson ORDER by p.cplan_lesson) t on l.lesson=t.cplan_lesson");
		sBuilder.append("  ORDER by l.lesson ");
		

		List<Object> list = BaseDao.executeQuery(Cplan.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			cplans = new ArrayList<Cplan>();
			for (Object object : list) {
				cplans.add((Cplan)object);
			}
		}
		return cplans;
	}

}
