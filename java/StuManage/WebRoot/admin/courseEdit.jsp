<%@ page language ="java"  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.course!=null">编辑</s:if><s:else>添加</s:else>课程信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	 
	 $("#addBtn").bind('click',function(){
		if($("#paramsCourse\\.course_name").val()==''){
			alert('课程名称不能为空');
			return;
		} 
		if($("#paramsCourse\\.course_type").val()=='0'){
			alert('课程类型不能为空');
			return;
		}
		if($("#paramsCourse\\.course_type").val()=='2'){
			if($("#paramsCourse\\.teacher_id").val()=='0'){
				alert('选修课任课教师不能为空');
				return;
			}
		}
		$("#paramsCourse\\.course_id").val(0);
		$("#info").attr('action','Admin_addCourse.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
		 	if($("#paramsCourse\\.course_name").val()==''){
				alert('课程名称不能为空');
				return;
			}
		 	if($("#paramsCourse\\.course_type").val()=='0'){
				alert('课程类型不能为空');
				return;
			}
			if($("#paramsCourse\\.course_type").val()=='2'){
				if($("#paramsCourse\\.teacher_id").val()=='0'){
					alert('选修课任课教师不能为空');
					return;
				}
			}
			$("#info").attr('action','Admin_saveCourse.action').submit();
			 
	});
	 
	$("#paramsCourse\\.course_type").change(function(){
		var course_type = $(this).val();
		if(course_type==1){
			$("#paramsCourse\\.teacher_id").val(0);
			$("#paramsCourse\\.teacher_id").attr("disabled", "disabled");
		}else{
			$("#paramsCourse\\.teacher_id").removeAttr("disabled");  
		}
	});
	
	var course_type = "<s:property value='#attr.course.course_type'/>";
	if(course_type==1){
		$("#paramsCourse\\.teacher_id").val(0);
		$("#paramsCourse\\.teacher_id").attr("disabled", "disabled");
	}else{
		$("#paramsCourse\\.teacher_id").removeAttr("disabled");  
	}
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">课程管理&gt;&gt;<s:if test="#attr.course!=null">编辑</s:if><s:else>添加</s:else>课程</span>
</div>
<form id="info" name="info" action="Admin_addCourse.action" method="post">   
<s:hidden id="paramsCourse.course_id" name="paramsCourse.course_id" value="%{#attr.course.course_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.course!=null">编辑</s:if><s:else>添加</s:else>课程</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
        <tr>
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 课程名称：</td>
          <td width="65%">
          	<s:textfield name="paramsCourse.course_name" id="paramsCourse.course_name" value="%{#attr.course.course_name}"/>
          </td>
        </tr> 
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 课程类型：</td>
          <td>
          	<s:select list="#{'1':'必修课','2':'选修课'}" id="paramsCourse.course_type" name="paramsCourse.course_type" 
          		value="%{#attr.course.course_type}"  cssStyle="width:155px;"
          		listKey="key" listValue="value" headerKey="0" headerValue="请选择">
          	</s:select>
          </td>
        </tr>
        <tr>
          <td width="35%" align="right" style="padding-right:5px">任课教师：</td>
          <td width="65%">
          	<s:select list="#attr.teachers" id="paramsCourse.teacher_id" name="paramsCourse.teacher_id" 
          		value="%{#attr.course.teacher_id}"  cssStyle="width:155px;"
          		listKey="user_id" listValue="real_name" headerKey="0" headerValue="请选择">
          	</s:select>
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px">课程说明：</td>
          <td>
          	<s:textarea name="paramsCourse.note" id="paramsCourse.note" value="%{#attr.course.note}" cssStyle="width:300px;height:60px;">
          	</s:textarea>
          </td>
        </tr> 
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
          	<s:if test="#attr.course!=null">
          	<input type="button" id="editBtn" Class="btnstyle" value="编 辑"/> 
          	</s:if>
          	<s:else>
          	<input type="button" id="addBtn" Class="btnstyle" value="添 加" />
          	</s:else>
            &nbsp;<label style="color:red">${tip}</label>
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>