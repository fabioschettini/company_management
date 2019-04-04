<%@ page import="company_management.bean.CompanyBean" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Design by http://www.bluewebtemplates.com
Released for free under a Creative Commons Attribution 3.0 License
-->

<%

String companyName="";
String idCompany="";
String phone="";
String email="";

// se sono presenti in sessione prelevo i campi
CompanyBean company = new CompanyBean();
boolean companyFilled=false;

System.out.println("companyFilled" + companyFilled);

if (request.getSession()!= null && request.getSession().getAttribute("COMPANY") != null) {
	
	company = (CompanyBean) request.getSession().getAttribute("COMPANY");
	companyName = company.getCompanyName() + "";
	idCompany = company.getIdCompany()  + "";
	phone = company.getPhone()  + "";
	email = company.getEmail()  + "";
	
	System.out.println("filled true");
	companyFilled=true;
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
          <li class="active"><a href="#"><span>Insert Company</span></a></li>
           <li><% if (companyFilled) { %>
				<a href="/company_management/CompanyManagementServlet?whatsend=employee" target="_top"><span>Insert Employee</span></a>
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
        	<h2>Insert Company</h2>
        	<form name="companyForm" action="/company_management/CompanyManagementServlet" method="post">
        	<ol>
        		<li>
        		<label for="idCompany">Company ID (Required)</label>
						<% if (companyFilled) { %> 
						
							<%=idCompany %><input name="idCompany" value="<%=idCompany %>" type="hidden" />
																		
						<% } else { %>
						<input name="idCompany" value="<%=idCompany %>" type="text" maxLength="16" />
						<% } %>             		
        		</li>
        		<li>
        			<label for="name">Company Name (required)</label>
        			<input name="companyName" value="<%=companyName %>" type="text" maxLength="45" />
        		</li>
        		<li>
        			<label for="name">Phone</label>
        			<input name="phone" value="<%=phone %>" type="text" maxLength="16" />
        		</li>
        		<li>
        			<label for="name">Email (required)</label>
        			<input name="email" value="<%=email %>" type="text" maxLength="45" />
        		</li>
        	</ol>     
        	<div class="clr"></div>   
			<br>
        		 <input name="whatsend" value="company" type="hidden" />
				<% if (companyFilled) { %> 
				<input type="submit" value="Modify and Save Company" />	
				<% } else { %>	
				<input type="submit" value="Insert Company" />
				<% } %>

        	</form>
        	
				<% if (companyFilled) { %> 
							<br>
				<form name="saveOnDB" action="/company_management/CompanyManagementServlet" method="post">
				<input name="whatsend" value="saveCompany" type="hidden" />
				<input type="submit" value="Save Company on DB" />	
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