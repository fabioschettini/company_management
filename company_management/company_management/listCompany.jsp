<%@ page import="java.util.ArrayList" %> 
<%@ page import="company_management.bean.CompanyBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%

String idCompany = "";

ArrayList<CompanyBean> companiesList = new ArrayList<CompanyBean>();

if ( request.getAttribute("COMPANIES") != null ) {
	companiesList = (ArrayList<CompanyBean>) request.getAttribute("COMPANIES");
	System.out.println(companiesList.size());
}
%>
<%
String companyName="";
if (request.getAttribute("COMPANYNAME") != null ) {
	companyName = (String) request.getAttribute("COMPANYNAME");
}
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
          <li><a href="/company_management/CompanyManagementServlet?whatsend=homepage" target="_top"><span>Home page</span> </a></li>
          <li class="active"><a href="#"><span>Companies list</span></a></li>
          <li><a href="/company_management/CompanyManagementServlet?whatsend=company" target="_top"><span>Insert company</span></a></li>        	
        </ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>   

<div class="content">
    <div class="content_resize">
      <div class="mainbar"><div class="submb">
        <div class="article">
        	<h2> Companies List</h2>
        </div>
        	        	
        	<form name="searchForm" action="/company_management/CompanyManagementServlet" method="post">
        	
        	<div class="myTable">
				<div class="myTableRow">
					<div class="myTableHead">Search company</div>
    				<input name="companyName" value="<%=companyName %>" type="text" maxlength="45" />
					<input name="whatsend" value="search" type="hidden" />
					<input type="submit" value="Search" />    
				</div>
			</div>
			
			
			<div class="myTable">
				<div class="myTableRow">
					<div class="myTableHead">id company</div>
					<div class="myTableHead">company name</div>
					<div class="myTableHead">phone</div>
					<div class="myTableHead">email</div>
					<div class="myTableHead">modify</div>
					<div class="myTableHead">delete</div>
					<div class="myTableHead">Employees</div>
				</div>

		<%

		String phone =  "";
		String email = "";
			for (CompanyBean company:companiesList) {
				idCompany = company.getIdCompany();
				companyName = company.getCompanyName();
				phone = company.getPhone();
				email = company.getEmail();
				
				System.out.println("idCompany " + idCompany);

		
		%>           		
			<div class="myTableRow">
				<div class="myTableCell"><%=idCompany %></div>
				<div class="myTableCell"><%=companyName %></div>
				<div class="myTableCell"><%=phone %></div>
				<div class="myTableCell"><%=email %></div>
				<div class="myTableCell"><a href="/company_management/CompanyManagementServlet?whatsend=modifyCompany&idCompany=<%=idCompany %>" target="_top"> GO </a></div>
				<div class="myTableCell"><a href="/company_management/CompanyManagementServlet?whatsend=deleteCompany&idCompany=<%=idCompany%>" target="_top"> GO </a></div>
				<div class="myTableCell"><a href="/company_management/CompanyManagementServlet?whatsend=listEmployee&idCompany=<%=idCompany %>" target="_top"> GO </a></div>
			</div>			
		<%} %>        	
					</div>
        	<div class="clr"></div>   
        	</form>

      </div>
      
      <div class="clr"></div>
    </div>
  </div>          	        	
</div>
</div>

	

</body>
</html>