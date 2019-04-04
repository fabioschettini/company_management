<%@ page import="java.util.ArrayList" %> 
<%@ page import="company_management.bean.EmployeeBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
ArrayList<EmployeeBean> employeeList = new ArrayList<EmployeeBean>();

String idEmployee="";
if ( request.getAttribute("EMPLOYEES") != null ) {
	employeeList = (ArrayList<EmployeeBean>) request.getAttribute("EMPLOYEES");
	
	System.out.println(employeeList.size());
}
%>
<%
String idCompany="";
if (request.getAttribute("COMPANYID") != null ) {
	idCompany = (String) request.getAttribute("COMPANYID");
}

System.out.println("JSP");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Company Management</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<!-- CuFon: Enables smooth pretty custom font rendering. 100% SEO friendly. To disable, remove this section -->
<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/georgia.js"></script>
<script type="text/javascript" src="js/cuf_run.js"></script>
<!-- CuFon ends -->
</head>
<body>

<div class="main">

  <div class="header">
    <div class="header_resize">
      <div class="logo"><h1><a href="/company_management">Company Management</a></h1></div>
      <div class="menu_nav">
        <ul>
        	<li><a href="/company_management/CompanyManagementServlet?whatsend=homepage" target="_top"><span>Home page</span></a></li>
        	<li><a href="/company_management/CompanyManagementServlet?whatsend=listCompany" target="_top"><span>Companies List</span></a></li>
        	<li><a href="/company_management/CompanyManagementServlet?whatsend=company" target="_top"><span>Insert company</span></a></li>
        	<li><a href="/company_management/CompanyManagementServlet?whatsend=employee&idCompany=<%=idCompany%>" target="_top"><span>Insert employee</span></a></li>  
        </ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>   

<div class="content">
    <div class="content_resize">
      <div class="mainbar"><div class="submb">
        <div class="article">
        	<h2> Employees List</h2>
        </div>
        	        	

        	<div class="myTable">
				<div class="myTableRow">
					<div class="myTableHead">Company Id: <%=idCompany %></div> 
				</div>
			</div> 
			
			<!--<div class="myTable">
				<div class="myTableRow">
					<div class="myTableHead">Search for company</div>
    				<input name="companyName" value="<%=idCompany %>" type="text" maxlength="45" />
					<input name="whatsend" value="searchEmployeesForCompany" type="hidden" />
					<input type="submit" value="Search" />    
				</div>
				<!-- <div class="myTableRow">
					<div class="myTableHead">Search for employee</div>
    				<input name="companyName" value="<%=idEmployee %>" type="text" maxlength="45" />
					<input name="whatsend" value="search" type="hidden" />
					<input type="submit" value="Search" />    
				</div>			  
			</div>-->

			<div class="myTable">

		
				<div class="myTableRow">
					
					<div class="myTableHead">id employee</div>
					<div class="myTableHead">name</div>
					<div class="myTableHead">surname</div>
					<div class="myTableHead">badge</div>
					<div class="myTableHead">delete</div>
				</div>

		<%
		
		String name =  "";  
		String surname =  "";
		String badge = "";
		String fkCompany ="";
		if (employeeList != null ) {
			for (EmployeeBean employee:employeeList) {
				idEmployee = employee.getIdEmployee();
				name = employee.getName();
				surname = employee.getSurname();
				badge = employee.getCompanyBadge();
				fkCompany=employee.getFkCompany();
			

		
		%>        		
			<div class="myTableRow">
				
				<div class="myTableCell"><%=idEmployee %></div>
				<div class="myTableCell"><%=name %></div>
				<div class="myTableCell"><%=surname %></div>
				<div class="myTableCell"><%=badge %></div>
				<div class="myTableCell"><a href="/company_management/CompanyManagementServlet?whatsend=deleteEmployee&idCompany=<%=idCompany%>&idEmployee=<%=idEmployee%>" target="_top"> GO </a></div>

			</div>			
		<%}} %>        	
					</div>
        	<div class="clr"></div>   


      </div>
      
      <div class="clr"></div>
    </div>
  </div>          	        	
</div>


</div>

</body>




<body>


</body>
</html>