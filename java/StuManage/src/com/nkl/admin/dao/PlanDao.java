package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Plan;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class  PlanDao {

	public int addPlan(Plan plan, Connection conn){
		String sql = "INSERT INTO plan(plan_id,plan_year,plan_year_half,clazz_id,course_id,user_id,note) values(null,?,?,?,?,?,?)";
		Object[] params = new Object[] {
			plan.getPlan_year(),
			plan.getPlan_year_half(),
			plan.getClazz_id(),
			plan.getCourse_id(),
			plan.getUser_id(),
			plan.getNote()

		}; 
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delPlan(String plan_id, Connection conn){
		String sql = "DELETE FROM plan WHERE plan_id=?";

		Object[] params = new Object[] { new Integer(plan_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delPlans(String[] plan_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <plan_ids.length; i++) {
			sBuilder.append("?");
			if (i !=plan_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM plan WHERE plan_id IN(" +sBuilder.toString()+")";

		Object[] params = plan_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updatePlan(Plan plan, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE plan SET plan_id = " + plan.getPlan_id() +" ");
		if (plan.getPlan_year()!=0) {
			sBuilder.append(" ,plan_year = " + plan.getPlan_year() +" ");
		}
		if (plan.getPlan_year_half()!=0) {
			sBuilder.append(" ,plan_year_half = " + plan.getPlan_year_half() +" ");
		}
		if (plan.getClazz_id()!=0) {
			sBuilder.append(" ,clazz_id = " + plan.getClazz_id() +" ");
		}
		if (plan.getCourse_id()!=0) {
			sBuilder.append(" ,course_id = " + plan.getCourse_id() +" ");
		}
		if (plan.getUser_id()!=0) {
			sBuilder.append(" ,user_id = " + plan.getUser_id() +" ");
		}
		sBuilder.append("where plan_id = " + plan.getPlan_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public  Plan getPlan(Plan plan, Connection conn){
		Plan _plan=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT p.*,c.clazz_name,co.course_name,u.real_name FROM plan p ");
		sBuilder.append("  join clazz c on p.clazz_id=c.clazz_id ");
		sBuilder.append("  join course co on p.course_id=co.course_id ");
		sBuilder.append("  join user u on p.user_id=u.user_id WHERE 1=1 ");
		if (plan.getPlan_id()!=0) {
			sBuilder.append(" and plan_id = " + plan.getPlan_id() +" ");
		}
		if (plan.getPlan_year()!=0) {
			sBuilder.append(" and plan_year = " + plan.getPlan_year() +" ");
		}
		if (plan.getPlan_year_half()!=0) {
			sBuilder.append(" and plan_year_half = " + plan.getPlan_year_half() +" ");
		}
		if (plan.getClazz_id()!=0) {
			sBuilder.append(" and p.clazz_id = " + plan.getClazz_id() +" ");
		}
		if (plan.getCourse_id()!=0) {
			sBuilder.append(" and p.course_id = " + plan.getCourse_id() +" ");
		}
		if (plan.getUser_id()!=0) {
			sBuilder.append(" and p.user_id = " + plan.getUser_id() +" ");
		}

		List<Object> list = BaseDao.executeQuery(Plan.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _plan = (Plan)list.get(0);
		}
		return _plan;
	}

	public  List<Plan>  listPlans(Plan plan, Connection conn){
		List<Plan> plans = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT p.*,c.clazz_name,co.course_name,u.real_name FROM plan p ");
		sBuilder.append("  join clazz c on p.clazz_id=c.clazz_id ");
		sBuilder.append("  join course co on p.course_id=co.course_id ");
		sBuilder.append("  join user u on p.user_id=u.user_id WHERE 1=1 ");

		if (plan.getPlan_id()!=0) {
			sBuilder.append(" and plan_id = " + plan.getPlan_id() +" ");
		}
		if (plan.getPlan_year()!=0) {
			sBuilder.append(" and plan_year = " + plan.getPlan_year() +" ");
		}
		if (plan.getPlan_year_half()!=0) {
			sBuilder.append(" and plan_year_half = " + plan.getPlan_year_half() +" ");
		}
		if (plan.getClazz_id()!=0) {
			sBuilder.append(" and p.clazz_id = " + plan.getClazz_id() +" ");
		}
		if (plan.getCourse_id()!=0) {
			sBuilder.append(" and p.course_id = " + plan.getCourse_id() +" ");
		}
		if (plan.getUser_id()!=0) {
			sBuilder.append(" and p.user_id = " + plan.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(plan.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + plan.getReal_name() +"%' ");
		}
		sBuilder.append(" order by plan_id asc) t");

		if (plan.getStart() != -1) {
			sBuilder.append(" limit " + plan.getStart() + "," + plan.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Plan.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			plans = new ArrayList<Plan>();
			for (Object object : list) {
				plans.add((Plan)object);
			}
		}
		return plans;
	}

	public int  listPlansCount(Plan plan, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM plan p ");
		sBuilder.append("  join clazz c on p.clazz_id=c.clazz_id ");
		sBuilder.append("  join course co on p.course_id=co.course_id ");
		sBuilder.append("  join user u on p.user_id=u.user_id WHERE 1=1 ");

		if (plan.getPlan_id()!=0) {
			sBuilder.append(" and plan_id = " + plan.getPlan_id() +" ");
		}
		if (plan.getPlan_year()!=0) {
			sBuilder.append(" and plan_year = " + plan.getPlan_year() +" ");
		}
		if (plan.getPlan_year_half()!=0) {
			sBuilder.append(" and plan_year_half = " + plan.getPlan_year_half() +" ");
		}
		if (plan.getClazz_id()!=0) {
			sBuilder.append(" and p.clazz_id = " + plan.getClazz_id() +" ");
		}
		if (plan.getCourse_id()!=0) {
			sBuilder.append(" and p.course_id = " + plan.getCourse_id() +" ");
		}
		if (plan.getUser_id()!=0) {
			sBuilder.append(" and p.user_id = " + plan.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(plan.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + plan.getReal_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
