package company_management.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company_management.bean.CompanyBean;
import company_management.bean.EmployeeBean;
import company_management.db.dao.SaveMySqlDAO;

/**
 * Servlet implementation class CompanyManagementServlet
 */
@WebServlet("/CompanyManagementServlet")
public class CompanyManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyManagementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String whatsend = request.getParameter("whatsend");
		System.out.println("whatsend GET:: " + whatsend);
		
		if (whatsend.equals("employee")) {
			
			SaveMySqlDAO saveCompany = new SaveMySqlDAO();
			// dispatcher della pagina desiderata
			ServletContext sc = request.getSession().getServletContext();
			
			if (request.getParameter("idCompany") != null ) {
			String idCompany = request.getParameter("idCompany");
			CompanyBean company = new CompanyBean();		
			
			
				try {
					company = saveCompany.searchCompany(idCompany);
				
					
					request.getSession().setAttribute("COMPANY", company);				
				} catch(SQLException e) {
					System.out.println("ERROR:" + e.getErrorCode()+":"+e.getMessage());
					e.printStackTrace();
					RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
					rd.forward(request, response);	
				
				}
			}
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployee.jsp");
			rd.forward(request, response);


			
		}else if(whatsend.equals("company")) {
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formCompany.jsp");
			rd.forward(request, response);		
			
		}else if (whatsend.equals("listCompany")) {

			SaveMySqlDAO saveCompany = new SaveMySqlDAO();
			ArrayList<CompanyBean> listCompany = new ArrayList();			
			ServletContext sc = request.getSession().getServletContext();
			request.getSession().removeAttribute("COMPANY");
			
			try {
				listCompany = saveCompany.searchCompanies();
				request.setAttribute("COMPANIES", listCompany);				
				RequestDispatcher rd = sc.getRequestDispatcher("/listCompany.jsp");
				rd.forward(request, response);		
				
			} catch(SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode()+":"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
				
			}
						
			
		}else if (whatsend.equals("listEmployee")) {

			SaveMySqlDAO saveCompany = new SaveMySqlDAO();
			ArrayList<EmployeeBean> listEmployee = new ArrayList();			
			ServletContext sc = request.getSession().getServletContext();
			String idCompany = request.getParameter("idCompany");
			
			try {
				if (idCompany!=null)
					listEmployee = saveCompany.searchAllEmployeesForCompany(idCompany);
				else 
					listEmployee = saveCompany.searchAllEmployees();
				
				request.setAttribute("EMPLOYEES", listEmployee);				
				request.setAttribute("COMPANYID", idCompany);				
				RequestDispatcher rd = sc.getRequestDispatcher("/listEmployee.jsp");
				rd.forward(request, response);		
				
			} catch(SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode()+":"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
				
			}
						
			
		}else if (whatsend.equals("modifyCompany")) {

			SaveMySqlDAO saveCompany = new SaveMySqlDAO();
			CompanyBean company = new CompanyBean();		
			ServletContext sc = request.getSession().getServletContext();
			String idCompany = request.getParameter("idCompany");
			
			
			try {
				company = saveCompany.searchCompany(idCompany);
				
				
				request.getSession().setAttribute("COMPANY", company);				
				RequestDispatcher rd = sc.getRequestDispatcher("/formCompany.jsp");
				rd.forward(request, response);		
				
			} catch(SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode()+":"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
				
			}
		}else if (whatsend.equals("deleteEmployee")) {

			SaveMySqlDAO saveCompany = new SaveMySqlDAO();
			ArrayList<EmployeeBean> listEmployee = new ArrayList();			
			ServletContext sc = request.getSession().getServletContext();
			String idCompany = request.getParameter("idCompany");
			String idEmployee = request.getParameter("idEmployee");
			
			
			try {
				listEmployee = saveCompany.deleteEmployee(idEmployee, idCompany);
				
				request.setAttribute("EMPLOYEES", listEmployee);				
				request.setAttribute("COMPANYID", idCompany);				

				
						
				RequestDispatcher rd = sc.getRequestDispatcher("/listEmployee.jsp");
				rd.forward(request, response);		
				
			} catch(SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode()+":"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
				
			}					
			
		
						
			
		}else if (whatsend.equals("deleteCompany")) {

			SaveMySqlDAO saveCompany = new SaveMySqlDAO();
			ArrayList<CompanyBean> listCompany = new ArrayList();			
			ServletContext sc = request.getSession().getServletContext();
			String idCompany = request.getParameter("idCompany");
			
			
			
			try {
				if ( saveCompany.deleteAllEmployeesForCompany(idCompany) ) {
					
					saveCompany.deleteCompany(idCompany);
					
				}
				
				
				listCompany = saveCompany.searchCompanies();
				request.setAttribute("COMPANIES", listCompany);				
				RequestDispatcher rd = sc.getRequestDispatcher("/listCompany.jsp");
				rd.forward(request, response);		

												
			} catch(SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode()+":"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
				
			}					
			
		
						
			
		}else if (whatsend.equals("homepage")) {
			// redirect specifico
			request.getSession().removeAttribute("COMPANY");
			response.sendRedirect("/company_management/index.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String whatsend = request.getParameter("whatsend");
		System.out.println("whatsend POST:: " + whatsend);
		
		if (whatsend.equals("company")) {
			
			String idCompany = request.getParameter("idCompany");
			System.out.println("idCompany :: "+idCompany);
			
			String companyName = request.getParameter("companyName");
			System.out.println("idCompany :: "+companyName);
			
			String phone = request.getParameter("phone");
			System.out.println("phone :: "+phone);
			
			String email = request.getParameter("email");
			System.out.println("email :: "+email);			
			
			
			CompanyBean company = new CompanyBean();
			ArrayList<EmployeeBean> companyEmployees = new ArrayList<EmployeeBean>();
			
			company.setCompanyName(companyName);
			company.setEmail(email);
			company.setIdCompany(idCompany);
			company.setPhone(phone);
			company.setCompanyEmployees(companyEmployees);
			
			request.getSession().removeAttribute("COMPANY");
			request.getSession().setAttribute("COMPANY", company);
			
			// dispatcher della pagina desiderata
			ServletContext sc = request.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/formCompany.jsp");
			
			System.out.println("prima di forward " + rd.toString());
			
			rd.forward(request, response);
			
			
		}else if (whatsend.equals("employee")) {

			String idEmployee = request.getParameter("idEmployee");
			System.out.println("idEmployee :: "+idEmployee);
			
			String name = request.getParameter("name");
			System.out.println("name :: "+name);

			String surname = request.getParameter("surname");
			System.out.println("surname :: "+surname);
			
			String companyBadge = request.getParameter("companyBadge");
			System.out.println("badge :: "+companyBadge);
			
			String fkCompany = request.getParameter("fkCompany");
			System.out.println("fkCompany :: "+fkCompany);		
			
			CompanyBean company = new CompanyBean();
			EmployeeBean employee = new EmployeeBean();
			
			employee.setCompanyBadge(companyBadge);
			employee.setFkCompany(fkCompany);
			employee.setIdEmployee(idEmployee);
			employee.setName(name);
			employee.setSurname(surname);
			
			if (request.getSession()!=null && request.getSession().getAttribute("COMPANY")!=null) {
				
				company = (CompanyBean) request.getSession().getAttribute("COMPANY");
				ArrayList<EmployeeBean> companyEmployees = company.getCompanyEmployees();
				
				if (companyEmployees != null) {
				EmployeeBean employeeToRemove = new EmployeeBean();
				for (EmployeeBean cl: companyEmployees)
					if (cl.getIdEmployee().equalsIgnoreCase(idEmployee)) employeeToRemove = cl;
				companyEmployees.remove(employeeToRemove);
				
				companyEmployees.add(employee);
				} else {
					companyEmployees = new ArrayList<EmployeeBean>();
					companyEmployees.add(employee);
				}
				
				
				
				company.setCompanyEmployees(companyEmployees);
				request.getSession().removeAttribute("COMPANY");
				request.getSession().setAttribute("COMPANY", company);
				
			}
			
			ServletContext sc = request.getSession().getServletContext();
			
			request.getSession().removeAttribute("EMPLOYEE");
			request.getSession().setAttribute("EMPLOYEE", employee);
			RequestDispatcher rd = sc.getRequestDispatcher("/formEmployee.jsp");
			rd.forward(request, response);
			
		}else if (whatsend.equals("saveCompany")) {
			CompanyBean company = new CompanyBean();
			company = (CompanyBean) request.getSession().getAttribute("COMPANY");
			SaveMySqlDAO saveCompany = new SaveMySqlDAO();
			ServletContext sc = request.getSession().getServletContext();
			try {
				saveCompany.insertCompany(company);
				

				RequestDispatcher rd = sc.getRequestDispatcher("/success_insert.jsp");
				rd.forward(request, response);		
				
			} catch(SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode()+":"+e.getMessage());
				e.printStackTrace();
				request.getSession().removeAttribute("COMPANY");
				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
				
			}
			
		}else if (whatsend.equals("modifyCompany")) {

			SaveMySqlDAO saveCompany = new SaveMySqlDAO();
			ArrayList<CompanyBean> companyList = new ArrayList<CompanyBean>();
			ServletContext sc = request.getSession().getServletContext();
			CompanyBean company = new CompanyBean();
			
			String idCompany = request.getParameter("idCompany");
			String companyName = request.getParameter("companyName");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			
			company.setCompanyName(companyName);
			company.setIdCompany(idCompany);
			company.setEmail(email);
			company.setPhone(phone);
			
			try {
				companyList = saveCompany.modifyCompany(company);
				

				RequestDispatcher rd = sc.getRequestDispatcher("/success_insert.jsp");
				rd.forward(request, response);		
				
			} catch(SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode()+":"+e.getMessage());
				e.printStackTrace();
				request.getSession().removeAttribute("COMPANY");
				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
				
			}
			
		}else if (whatsend.equals("deleteEmployee")) {

			SaveMySqlDAO saveCompany = new SaveMySqlDAO();
			ArrayList<EmployeeBean> listEmployee = new ArrayList();			
			ServletContext sc = request.getSession().getServletContext();
			String idCompany = request.getParameter("idCompany");
			String idEmployee = request.getParameter("idEmployee");
			
			try {
				listEmployee = saveCompany.deleteEmployee(idEmployee, idCompany);
				request.getSession().setAttribute("EMPLOYEES", listEmployee);
				request.getSession().setAttribute("COMPANYID", idCompany);			
				RequestDispatcher rd = sc.getRequestDispatcher("/listEmployee.jsp");
				
				System.out.println(" dopo delete " + listEmployee.size());;
				rd.forward(request, response);		
				
			} catch(SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode()+":"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
				
			}
		}else if (whatsend.equalsIgnoreCase("search")) {
			
			
			String companyName = request.getParameter("companyName");
			SaveMySqlDAO saveCompany = new SaveMySqlDAO();
			ArrayList<CompanyBean> listCompany = new ArrayList();			
			ServletContext sc = request.getSession().getServletContext();
			
			
			try {
				listCompany = saveCompany.searchCompanies(companyName);
				request.setAttribute("COMPANIES", listCompany);		
				request.setAttribute("COMPANYNAME", companyName);
				RequestDispatcher rd = sc.getRequestDispatcher("/listCompany.jsp");
				rd.forward(request, response);		
				
			} catch(SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode()+":"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
				
			}
			
		}else if (whatsend.equalsIgnoreCase("searchEmployeesForCompany")) {
			
			
			String idCompany = request.getParameter("idCompany");
			SaveMySqlDAO saveCompany = new SaveMySqlDAO();
			ArrayList<CompanyBean> listCompany = new ArrayList();			
			ServletContext sc = request.getSession().getServletContext();
			
			
			try {
				listCompany = saveCompany.searchCompanies(idCompany);
				request.setAttribute("COMPANIES", listCompany);		

				RequestDispatcher rd = sc.getRequestDispatcher("/listCompany.jsp");
				rd.forward(request, response);		
				
			} catch(SQLException e) {
				System.out.println("ERROR:" + e.getErrorCode()+":"+e.getMessage());
				e.printStackTrace();
				RequestDispatcher rd = sc.getRequestDispatcher("/error.jsp");
				rd.forward(request, response);	
				
			}
			
		}
		
	}

}
