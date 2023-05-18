<%@ page language ="java"  contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html public "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级课表一周视图信息</title>
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
   var cplan_year = document.getElementById('cplan_year').value;
   var cplan_year_half = document.getElementById('cplan_year_half').value;
   var clazz_id = document.getElementById('clazz_id').value;
   if(cplan_year=='0' || cplan_year_half=='0' || clazz_id=='0'){
	   alert("请选择年份、学期、班级进行查询！");
	   return;
   }
   document.info.action="Admin_listCplansByClazz.action";
   document.info.submit();
}
function del()
{
	var checks=document.getElementsByName("chkid");
    var ids="";
	for (var i=0;i<checks.length;i++)
    {
        var e = checks[i];
		if(e.checked==true)
		{
		  if(ids=="")
		  {
		    ids=ids+e.value;
		  }
		  else
		  {
		    ids=ids+","+e.value;
		  }
		}
    }
    if(ids=="")
    {
       alert("请至少选择一个要删除的班级课表！");
       return false;
    }
    if(confirm('确认删除吗!?'))
    {
       document.info.action="Admin_delCplansByClazz.action?paramsCplan.ids="+ids;
       document.info.submit();
    }
    
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
  document.info.action="Admin_listCplansByClazz.action";
  document.info.submit();
}
function ChangePage(pagenum)
{
  document.getElementById("pageNo").value=pagenum;
  document.info.action="Admin_listCplansByClazz.action";
  document.info.submit();
}
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="images/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">班级课表&gt;&gt;课表一周视图</span>
</div>
<form name="info" id="info" action="Admin_listCplansByClazz.action" method="post">
<input type="hidden" name="pageNo" id="pageNo" value="${pageNo}"/>
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">课表一周视图</td>
    <td width="" align="right">
            年份：
      <input type="text" style="width:100px;" id="cplan_year" name="paramsCplan.cplan_year" value="${paramsCplan.cplan_year}" class="inputstyle" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy'})"/>&nbsp;
      <s:select name="paramsCplan.cplan_year_half" id="cplan_year_half" list="#{1:'上半年',2:'下半年'}" value="%{#attr.paramsCplan.cplan_year_half}" listKey="key" listValue="value" headerKey="0" headerValue="选择学期" cssClass="selectstyle" cssStyle="width:100px"></s:select>&nbsp;
            班级：
      <s:select name="paramsCplan.clazz_id" id="clazz_id" list="#attr.clazzs" value="%{#attr.paramsCplan.clazz_id}" listKey="clazz_id" listValue="clazz_name" headerKey="0" headerValue="选择班级"  cssClass="selectstyle" cssStyle="width:100px"></s:select>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="serch();" class="btnstyle"/>&nbsp;
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
<table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td align="center">第几节课</td>
     <td width="" align="center">星期一</td>
     <td width="" align="center">星期二</td>
     <td width="" align="center">星期三</td>
     <td width="" align="center">星期四</td>
     <td width="" align="center">星期五</td>
   </tr>
   <s:if test="#attr.cplans!=null && #attr.cplans.size()>0">
   <s:iterator value="#attr.cplans" id="cplan" status="status">
   <tr class="<s:if test='(#status.index + 1)%2==0'>list1</s:if><s:else>list0</s:else>" onmouseover="tr_mouseover(this)" onmouseout="tr_mouseout(this)"> 
     <td width="" align="center"><s:property value="#cplan.cplan_lessonDesc"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#cplan.week1_lesson"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#cplan.week2_lesson"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#cplan.week3_lesson"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#cplan.week4_lesson"/>&nbsp;</td>
     <td width="" align="center"><s:property value="#cplan.week5_lesson"/>&nbsp;</td>
   </tr> 
   </s:iterator>
   </s:if>
   <s:elseif test="#attr.paramsCplan==null">
    <tr>
     <td height="60" colspan="6" align="center"><b>&lt;请选择条件查询班级课表周视图&gt;</b></td>
   </tr>
   </s:elseif>
   <s:else>
   <tr>
     <td height="60" colspan="6" align="center"><b>&lt;不存在班级课表信息&gt;</b></td>
   </tr>
   </s:else>
   
</table>
<jsp:include page="page.jsp"></jsp:include>
</form> 
</body>
</html>