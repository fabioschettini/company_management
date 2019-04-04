<%@ page import="company_management.bean.CompanyBean" %>  
<%@ page import="company_management.bean.EmployeeBean" %>  
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String idEmployee="";
	String name="";
	String surname="";
	String companyBadge="";
	String fkCompany="";
	
	EmployeeBean employee = new EmployeeBean();
	CompanyBean company = new CompanyBean();
	boolean employeeFilled=  false;
	boolean companyFilled = false;
	
	if (request!=null && request.getAttribute("EMPLOYEE")!=null) {
		
		employee = (EmployeeBean) request.getSession().getAttribute("EMPLOYEE");
		idEmployee = employee.getIdEmployee();
		name = employee.getName();
		surname = employee.getSurname();
		companyBadge = employee.getCompanyBadge();
		fkCompany = employee.getFkCompany();
		
		employeeFilled = true;
		
	}
	
	if (request.getSession() != null && request.getSession().getAttribute("COMPANY") != null) {
		
		company = (CompanyBean) request.getSession().getAttribute("COMPANY");
		fkCompany = company.getIdCompany();
		companyFilled = true;
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
          		  <li><a href="/company_management/CompanyManagementServlet?whatsend=listCompany" target="_top"><span> Companies List </span></a></li>        		  
       			  <li><a href="/company_management/CompanyManagementServlet?whatsend=company" target="_top"><span>Company</span> </a></li>        
       			  <li class="active"><a href="#"><span>Insert Employee</span></a></li>  
         		  <li><% if (employeeFilled) { %>
						<a href="/company_management/CompanyManagementServlet?whatsend=employee" target="_top"><span>Insert another Employee </span></a>
					  <%} %>
		  		  </li>        			  
        		</ul>        		
      		</div>
      		<div class="clr"></div>
    	</div>
  	</div>          

  <div class="content">
    <div class="content_resize">
      <div class="mainbar"><div class="submb">
        <div class="article">
        	<h2>Insert Employee</h2>
        	<form name="companyForm" action="/company_management/CompanyManagementServlet" method="post">        	
        	<ol>
        		<li>					        		
        		<label for="idEmployee">Employee ID (Required)</label>
					<% if ( employeeFilled) { %> 
						
							<%=idEmployee %><input name="idEmployee" value="<%=idEmployee %>" type="hidden" />
																		
						<% } else { %>
						<input name="idEmployee" value="<%=idEmployee %>" type="text" maxLength="16" />
						<% } %>           		
        		</li>
        		<li>
        			<label for="name">Name (required)</label>
						<% if (companyFilled && employeeFilled) { %> 
						
							<%=name %>
																		
						<% } else { %>
						<input name="name" value="<%=name %>" type="text" maxLength="45" />
						<% } %>	
        		</li>
				<li>
        			<label for="surname">Surname (required)</label>
						<% if (companyFilled && employeeFilled) { %> 
						
							<%=surname %>
																		
						<% } else { %>
						<input name="surname" value="<%=surname %>" type="text" maxLength="45" />
						<% } %>	
        		</li>        	        			
        		<li>
        			<label for="name">Company badge:</label>
						<% if (companyFilled && employeeFilled) { %> 
						
							<%=companyBadge %>
																		
						<% } else { %>						
						<input name="companyBadge" value="<%=companyBadge %>" type="text" maxLength="5" />
						<% } %>		
        		</li>
        	</ol>     
        	<div class="clr"></div>   
				<br>
					<input name="fkCompany" value="<%=fkCompany %>" type="hidden" />
					<input name="whatsend" value="employee" type="hidden" />
					<% if (employeeFilled && !companyFilled) { %> 
					<input type="submit" value="Modify Employee" />		
					<% } else if (!employeeFilled && companyFilled) {  %>	
					<input type="submit" value="Insert Employee" />
					<% } %>								
        	</form>        	
        	        
			
			<% if (employeeFilled && companyFilled) { %> 
			<button onClick="window.location.href='/comapany_management/CompanyManagementServlet/whatsend=employee'">Insert another Employee>></button>
			<form name="saveOnDB" action="/company_management/CompanyManagementServlet" method="post">
				<input name="whatsend" value="saveCompany" type="hidden" />
				<input type="submit" value="Save and Finish" />
			</form>
				<% } %>		        	        
        	
      </div>
      </div>
      </div>
      <div class="clr"></div>
    </div>
  </div>   
  <div class="footer">
    <div class="footer_resize">
      <p class="lf">&copy; Copyright MyWebSite. Designed by Blue <a href="http://www.bluewebtemplates.com">Website Templates</a></p>

      <div class="clr"></div>
    </div>
  </div>       

</div>
</body>
</html>