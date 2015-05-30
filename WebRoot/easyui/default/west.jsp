 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
 <div id="aa" class="easyui-accordion" style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
     <div title="用户管理" style="overflow: auto; padding: 10px;">
         <ul class="easyui-tree">
            <li>
                <a  onclick="addTab('部门','easyui/departmentManage.jsp')">部门管理</a>  
            </li>
            <li>
                <a  onclick="addTab('员工','easyui/staffManage.jsp')">员工管理</a>  
            </li>
            <li>
                <a  onclick="addTab('用户','easyui/userManage.jsp')">用户管理</a>  
            </li>
         </ul>
     </div>
     <div selected="true" data-options="title:'当前日期'">
	        <div id="calender" class="easyui-calendar easyui-draggable easyui-resizable" style="width:193px;height:180px;"></div>  
     </div>
</div>