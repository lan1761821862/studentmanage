<%@ page language ="java"  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增选课信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
var tempClassName="";
function tr_mouseover(obj) 
{ 
	tempClassName=obj.className;
	obj.className="list_mouseover";
}
function tr_mouseout(obj)      
{ 
	obj.className=tempClassName;
}      
function CheckAll(obj) 
{
	var checks=document.getElementsByName("chkid");
    for (var i=0;i<checks.length;i++)
	{
	    var e = checks[i];
	    e.checked = obj.checked;
	}
    
}

function serch()
{
   document.info.action="Admin_addScourceShow.action";
   document.info.submit();
}
 
function GoPage()
{
  var pagenum=document.getElementById("goPage").value;
  var patten=/^\d+$/;
  if(!patten.exec(pagenum))
  {
    alert("页码必须为大于0的数字");
    return false;
  }
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_addScourceShow.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_addScourceShow.action";
  document.info.submit();
}
$(document).ready(function(){
	$("a[id^='addScource']").bind('click',function(){
		if($("#scource_year").val()=='' || $("#scource_year").val()=='0' || $("#scource_year_half").val()=='0'){
			alert("请设置选修年份和学期后再进行选课！");
			return;
		}
		var course_id=$(this).attr('id').split('_')[1];
		$("#course_id").val(course_id);
		$("#info").attr('action','Admin_addScource.action').submit();
	});
});
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">选课管理&gt;&gt;新增选修课程</span>
</div>
<form name="info" id="info" action="Admin_addScource.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<input type="hidden" name="paramsScource.user_id" id="paramsScource.user_id" value="${admin.user_id}"/>
<input type="hidden" name="paramsScource.course_id" id="course_id" value="0"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">选修课程列表</td>
    <td width="" align="right">
            课程名称：
      <input type="text" id="paramsCourse.course_name" name="paramsCourse.course_name" value="${paramsCourse.course_name}" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;&nbsp;
            选修年份：
      <input type="text" style="width:70px;" id="scource_year" name="paramsScource.scource_year" value="${paramsScource.scource_year}"
      		 class="inputstyle" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})"/>&nbsp;
            选修学期：
      <s:select name="paramsScource.scource_year_half" id="scource_year_half" list="#{1:'上半年',2:'下半年'}" 
      			value="%{#attr.paramsScource.scource_year_half}" listKey="key" listValue="value" 
      			headerKey="0" headerValue="选择学期" cssClass="selectstyle" cssStyle="width:100px">
      </s:select>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">选课名称</td>
     <td width="" align="center">任课教师</td>
     <td width="" align="center">操作</td>
   </tr>
   <s:if test="#attr.courses!=null && #attr.courses.size()>0">
   <s:iterator value="#attr.courses" id="course" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:property value="#status.index+1"/></td>
     <td width="" align="center"><s:property value="#course.course_name"/></td>
     <td width="" align="center"><s:property value="#course.teacher_name"/></td>
     <td width="" align="center">
       <s:a id="addScource_%{#course.course_id}" href="javascript:void(0)">选修</s:a>
     </td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:else>
   <tr>
     <td height="60" colspan="7" align="center"><b>&lt;不存在选修课程信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>