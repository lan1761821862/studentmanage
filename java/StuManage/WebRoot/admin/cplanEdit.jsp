<%@ page language ="java"  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<!DOCTYPE html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#attr.cplan!=null && #attr.cplan.cplan_id!=0">编辑</s:if><s:else>添加</s:else>班级课表信息</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
$(document).ready(function(){
	
	var num = /^\d+(\.\d+)?$/;
	 $("#addBtn").bind('click',function(){
		if($("#paramsCplan\\.cplan_year").val()==''){
			alert('年份不能为空');
			return;
		}
		if($("#paramsCplan\\.clazz_id").val()=='0'){
			alert('班级不能为空');
			return;
		}
		if($("#paramsCplan\\.cplan_week").val()=='0'){
			alert('星期几不能为空');
			return;
		}
		if($("#paramsCplan\\.cplan_lesson").val()=='0'){
			alert('第几节课不能为空');
			return;
		}
		if($("#paramsCplan\\.course_id").val()=='0'){
			alert('课程不能为空');
			return;
		}
		$("#paramsCplan\\.cplan_id").val(0);
		$("#info").attr('action','Admin_addCplan.action').submit();
		 
	 });
	 
	 $("#editBtn").bind('click',function(){
	 	if($("#paramsCplan\\.cplan_year").val()==''){
			alert('年份不能为空');
			return;
		}
		if($("#paramsCplan\\.clazz_id").val()=='0'){
			alert('班级不能为空');
			return;
		}
		if($("#paramsCplan\\.cplan_week").val()=='0'){
			alert('星期几不能为空');
			return;
		}
		if($("#paramsCplan\\.cplan_lesson").val()=='0'){
			alert('第几节课不能为空');
			return;
		}
		if($("#paramsCplan\\.course_id").val()=='0'){
			alert('课程不能为空');
			return;
		}
		$("#info").attr('action','Admin_saveCplan.action').submit();
			 
	});
	
});
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">班级课表&gt;&gt;<s:if test="#attr.cplan!=null && #attr.cplan.cplan_id!=0">编辑</s:if><s:else>添加</s:else>班级课表</span>
</div>
<form id="info" name="info" action="Admin_addCplan.action" method="post">   
<s:hidden id="paramsCplan.cplan_id" name="paramsCplan.cplan_id" value="%{#attr.cplan.cplan_id}" /> 
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle"><s:if test="#attr.cplan!=null && #attr.cplan.cplan_id!=0">编辑</s:if><s:else>添加</s:else>班级课表</TD>
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
          <td width="35%" align="right" style="padding-right:5px"><font color="red">*</font> 年份：</td>
          <td width="65%"> 
           <s:textfield name="paramsCplan.cplan_year" id="paramsCplan.cplan_year" value="%{#attr.cplan.cplan_year}" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})"/>
          </td> 
	   </tr>
	   <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 学期：</td>
          <td>
         	<s:select name="paramsCplan.cplan_year_half" id="paramsCplan.cplan_year_half" list="#{1:'上半年',2:'下半年'}"   value="%{#attr.cplan.cplan_year_half}"
         		listKey="key" listValue="value" emptyOption="false"  cssStyle="width:155px"></s:select>
          </td>
	   </tr>
	   <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 班级：</td>
          <td>
          	<s:select id="paramsCplan.clazz_id" name="paramsCplan.clazz_id" list="#attr.clazzs" listKey="clazz_id" value="%{#attr.cplan.clazz_id}"
          		listValue="clazz_name" headerKey="0" headerValue="请选择" cssStyle="width:155px"></s:select>
          </td>
	   </tr>
	   <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 星期几：</td>
          <td>
          	<s:select name="paramsCplan.cplan_week" id="paramsCplan.cplan_week" 
          		list="#{'1':'星期一','2':'星期二','3':'星期三','4':'星期四','5':'星期五'}" value="%{#attr.cplan.cplan_week}"   
          		listKey="key" listValue="value" headerKey="0" headerValue="请选择" cssStyle="width:155px"></s:select>
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 第几节课：</td>
          <td>
          	<s:select name="paramsCplan.cplan_lesson" id="paramsCplan.cplan_lesson" 
          		list="#{'1':'第1节课','2':'第2节课','3':'第3节课','4':'第4节课','5':'第5节课','6':'第6节课','7':'第7节课','8':'第8节课'}" value="%{#attr.cplan.cplan_lesson}"   
          		listKey="key" listValue="value" headerKey="0" headerValue="请选择" cssStyle="width:155px"></s:select>
          </td>
        </tr>
	   <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font> 课程：</td>
          <td>
          	<s:select name="paramsCplan.course_id" id="paramsCplan.course_id" list="#attr.courses" value="%{#attr.cplan.course_id}"  
          		listKey="course_id" listValue="course_name" headerKey="0" headerValue="请选择" cssStyle="width:155px"></s:select>
          </td>
        </tr>
        <tr>
          <td align="right" style="padding-right:5px">备注：</td>
          <td>
          	<s:textarea name="paramsCplan.note" id="paramsCplan.note" value="%{#attr.cplan.note}" cssStyle="width:300px;height:60px;">
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
          	<s:if test="#attr.cplan!=null && #attr.cplan.cplan_id!=0">
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