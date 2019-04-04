package company_management.db.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import company_management.bean.CompanyBean;
import company_management.bean.EmployeeBean;

public class SaveMySqlDAO {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://192.168.100.100:3306/company_management";
	
	private static final String DB_USER="stage18";
	private static final String DB_PASSWORD="stage18";
	
	public void insertCompany(CompanyBean company) throws SQLException {
		
		System.out.println("insert company");
		
		Statement stmt = null;
		Connection conn = null;
		try {
			
			conn = this.getDBConnection();
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			String insertCompany = "INSERT INTO COMPANY (id_company, company_name, phone, email, date_ins) ";
			
			insertCompany += " values ("
					+ "'"+ company.getIdCompany() +"', "
					+ "'"+  company.getCompanyName() + "', "
					+ "'"+  company.getPhone() + "', "
					+ "'"+ company.getEmail() + "', "
					+ "SYSDATE()) ";
			
			System.out.println(insertCompany);
			
		
			if (!companyExist(company.getIdCompany()) ) {
				
				stmt.executeUpdate(insertCompany);
				
			} else {
				
				this.modifyCompany(company);
				
			}
			
			System.out.println("dopo nuovo test");
			
			if (company!= null  ) {
				
				
				System.out.println("company not = null");
				
				
				if (company.getCompanyEmployees()!=null && company.getCompanyEmployees().size()>0) {

					ArrayList<EmployeeBean> employees = company.getCompanyEmployees();
					
					System.out.println("numero impiegati " + company.getCompanyEmployees().size());
					
					for (EmployeeBean employee:employees) {
						
						
						
						String insertEmployee = "INSERT INTO EMPLOYEE (id_employee, surname, name, badge, fk_company, date_ins)";
							insertEmployee += "values ("
									+ "'"+ employee.getIdEmployee()+"', "
									+"'"+  employee.getSurname()+"', "
									+"'"+  employee.getName()+"', "
									+"'"+  employee.getCompanyBadge()+"', "
									+"'"+  employee.getFkCompany()+"', "
									+ "SYSDATE()) ";
							
							System.out.println(insertEmployee);
							stmt.executeUpdate(insertEmployee);
						
					}
					
				}
				

				
				
			}

			conn.commit();
			
			
		}catch (SQLException sqle ) {
			
			if (conn != null) conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolledd back " +sqle.getMessage());
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
			
		}catch (Exception e ) {
			if (conn != null) conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolledd back"  + e.getMessage());
			throw new SQLException(e.getMessage());
			
		}finally {
			if (stmt != null ) stmt.close();
			if (conn != null ) conn.close();
			
		}
		
	}

	public ArrayList<CompanyBean> modifyCompany(CompanyBean company) throws SQLException {
		
		System.out.println("modify company");
		
		Statement stmt = null;
		Connection conn = null;
		try {
			
			conn = this.getDBConnection();
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			String updateCompany = "UPDATE COMPANY SET company_name = '"+company.getCompanyName()+"', " +
												" phone = '"+company.getPhone()+"', " +
					"email= '"+company.getEmail() + "' WHERE ID_COMPANY = '"+company.getIdCompany()+"'";
			

			
			System.out.println(updateCompany);
			stmt.executeUpdate(updateCompany);
			conn.commit();
			return this.searchCompanies();
			
			
		}catch (SQLException sqle ) {
			
			if (conn != null) conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolledd back");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
			
		}catch (Exception e ) {
			if (conn != null) conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolledd back");
			throw new SQLException(e.getMessage());
			
		}finally {
			if (stmt != null ) stmt.close();
			if (conn != null ) conn.close();
			
		}
		
	}


	public ArrayList<EmployeeBean> deleteEmployee(String idEmployee, String idCompany) throws SQLException {
		
		System.out.println("delete employee");
		
		Statement stmt = null;
		Connection conn = null;
		try {
			
			conn = this.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String deleteEmployee = "DELETE FROM EMPLOYEE WHERE ID_EMPLOYEE = '"+idEmployee + "' AND FK_COMPANY = '"+idCompany+"'";
			

			
			System.out.println(deleteEmployee);
			stmt.executeUpdate(deleteEmployee);
			conn.commit();
			return this.searchAllEmployeesForCompany(idCompany);
			
			
		}catch (SQLException sqle ) {
			
			if (conn != null) conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolledd back");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
			
		}catch (Exception e ) {
			if (conn != null) conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolledd back");
			throw new SQLException(e.getMessage());
			
		}finally {
			if (stmt != null ) stmt.close();
			if (conn != null ) conn.close();
			
		}
		
	}

	public boolean deleteAllEmployeesForCompany(String idCompany) throws SQLException {
		
		System.out.println("delete employee");
		
		Statement stmt = null;
		Connection conn = null;
		try {
			
			conn = this.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String deleteEmployee = "DELETE FROM EMPLOYEE WHERE FK_COMPANY = '"+idCompany+"'";
			

			
			System.out.println(deleteEmployee);
			stmt.executeUpdate(deleteEmployee);
			conn.commit();
			return true;
			
			
		}catch (SQLException sqle ) {
			
			if (conn != null) conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolledd back");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
			
		}catch (Exception e ) {
			if (conn != null) conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolledd back");
			throw new SQLException(e.getMessage());
			
		}finally {
			if (stmt != null ) stmt.close();
			if (conn != null ) conn.close();
			
		}
		
	}	

	public ArrayList<EmployeeBean> deleteCompany(String idCompany) throws SQLException {
		
		System.out.println("delete employee");
		
		Statement stmt = null;
		Connection conn = null;
		try {
			
			conn = this.getDBConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			
			String deleteEmployee = "DELETE FROM COMPANY WHERE ID_COMPANY = '"+idCompany+"'";
			

			
			System.out.println(deleteEmployee);
			stmt.executeUpdate(deleteEmployee);
			conn.commit();
			return this.searchAllEmployeesForCompany(idCompany);
			
			
		}catch (SQLException sqle ) {
			
			if (conn != null) conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolledd back");
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
			
		}catch (Exception e ) {
			if (conn != null) conn.rollback();
			System.out.println("INSERT ERROR: Transaction is being rolledd back");
			throw new SQLException(e.getMessage());
			
		}finally {
			if (stmt != null ) stmt.close();
			if (conn != null ) conn.close();
			
		}
		
	}	
	
	
	public ArrayList<CompanyBean> searchCompanies() throws SQLException, IOException {
		
		Statement stmt = null;
		Connection conn = null;
		
		try {
			
			conn = this.getDBConnection();
			stmt = conn.createStatement();
			String searchCompanies = " SELECT * FROM COMPANY";
			System.out.println(" QUERY COMPANY: " + searchCompanies);
			
			ResultSet companyListRs = stmt.executeQuery(searchCompanies);
			
			ArrayList<CompanyBean> companyList = new ArrayList();
			while (companyListRs.next()) {
				
				System.out.println(companyListRs.getString("COMPANY_NAME"));
				
				CompanyBean company = new CompanyBean();
				company.setIdCompany(companyListRs.getString("ID_COMPANY"));
				company.setCompanyName(companyListRs.getString("COMPANY_NAME"));
				company.setPhone(companyListRs.getString("PHONE"));
				company.setEmail(companyListRs.getString("EMAIL"));
				companyList.add(company);
				
				
			}
			System.out.println(companyList.size());
			return companyList;
			
		}catch(SQLException sqle) {
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception e) {
			throw new SQLException (e.getMessage());
		}finally {
			if (stmt != null ) stmt.close();
			if (conn != null ) conn.close();			
		}
		
		
		
	}
	
	public ArrayList<CompanyBean> searchCompanies(String companyName) throws SQLException, IOException {
		
		Statement stmt = null;
		Connection conn = null;
		
		try {
			
			conn = this.getDBConnection();
			stmt = conn.createStatement();
			String searchCompanies = " SELECT * FROM COMPANY WHERE company_name like  '%"+companyName+"%'";
			System.out.println(" QUERY COMPANY: " + searchCompanies);
			
			ResultSet companyListRs = stmt.executeQuery(searchCompanies);
			
			ArrayList<CompanyBean> companyList = new ArrayList();
			while (companyListRs.next()) {
				
				CompanyBean company = new CompanyBean();
				company.setIdCompany(companyListRs.getString("ID_COMPANY"));
				company.setCompanyName(companyListRs.getString("COMPANY_NAME"));
				company.setPhone(companyListRs.getString("PHONE"));
				company.setEmail(companyListRs.getString("EMAIL"));
				companyList.add(company);
				
				
			}
			return companyList;
			
		}catch(SQLException sqle) {
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception e) {
			throw new SQLException (e.getMessage());
		}finally {
			if (stmt != null ) stmt.close();
			if (conn != null ) conn.close();			
		}
		
		
		
	}

	public CompanyBean searchCompany(String idCompany) throws SQLException, IOException {
		
		Statement stmt = null;
		Connection conn = null;
		
		try {
			
			conn = this.getDBConnection();
			stmt = conn.createStatement();
			String searchCompanies = " SELECT * FROM COMPANY WHERE id_company = '" +idCompany+"'";
			System.out.println(" QUERY COMPANY: " + searchCompanies);
			
			ResultSet companyListRs = stmt.executeQuery(searchCompanies);
			
			CompanyBean company = new CompanyBean();
			
			if (companyListRs.next()) {
				
				company.setIdCompany(companyListRs.getString("ID_COMPANY"));
				company.setCompanyName(companyListRs.getString("COMPANY_NAME"));
				company.setPhone(companyListRs.getString("PHONE"));
				company.setEmail(companyListRs.getString("EMAIL"));
				
			}

			return company;
			
		}catch(SQLException sqle) {
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception e) {
			throw new SQLException (e.getMessage());
		}finally {
			if (stmt != null ) stmt.close();
			if (conn != null ) conn.close();			
		}
		
		
		
	}

	
	public boolean companyExist(String idCompany) throws SQLException, IOException {
		
		Statement stmt = null;
		Connection conn = null;
		
		try {
			
			conn = this.getDBConnection();
			stmt = conn.createStatement();
			String searchCompanies = " SELECT * FROM COMPANY WHERE id_company = '" +idCompany+"'";
			System.out.println(" QUERY COMPANY: " + searchCompanies);
			
			ResultSet companyListRs = stmt.executeQuery(searchCompanies);
			
			
			
			if (companyListRs.next()) 
				return true;
			return false;

		}catch(SQLException sqle) {
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception e) {
			throw new SQLException (e.getMessage());
		}finally {
			if (stmt != null ) stmt.close();
			if (conn != null ) conn.close();			
		}
		
		
		
	}

	
	public ArrayList<EmployeeBean> searchAllEmployees() throws SQLException, IOException {
		
		Statement stmt = null;
		Connection conn = null;
		
		try {
			
			conn = this.getDBConnection();
			stmt = conn.createStatement();
			String searchEmployee = " SELECT * FROM EMPLOYEE ";
			System.out.println(" QUERY EMPLOYEE: " + searchEmployee);
			
			ResultSet employessListRs = stmt.executeQuery(searchEmployee);
			
			ArrayList<EmployeeBean> employeeList = new ArrayList();
			while (employessListRs.next()) {
				
				EmployeeBean employee = new EmployeeBean();
				
				employee.setCompanyBadge(employessListRs.getString("BADGE"));
				employee.setFkCompany(employessListRs.getString("FK_COMPANY"));
				employee.setIdEmployee(employessListRs.getString("ID_EMPLOYEE"));
				employee.setName(employessListRs.getString("NAME"));
				employee.setSurname(employessListRs.getString("SURNAME"));
				
				employeeList.add(employee);
				
				
			}
			System.out.println(employeeList.size());
			return employeeList;
			
		}catch(SQLException sqle) {
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception e) {
			throw new SQLException (e.getMessage());
		}finally {
			if (stmt != null ) stmt.close();
			if (conn != null ) conn.close();			
		}
		
		
		
	}
	
	public ArrayList<EmployeeBean> searchAllEmployeesForCompany(String idCompany) throws SQLException, IOException {
		
		Statement stmt = null;
		Connection conn = null;
		
		try {
			
			conn = this.getDBConnection();
			stmt = conn.createStatement();
			String searchEmployee = " SELECT * FROM EMPLOYEE WHERE fk_company = '"+idCompany+"'";
			System.out.println(" QUERY EMPLOYEE: " + searchEmployee);
			
			ResultSet employessListRs = stmt.executeQuery(searchEmployee);
			
			ArrayList<EmployeeBean> employeeList = new ArrayList();
			while (employessListRs.next()) {
				
				EmployeeBean employee = new EmployeeBean();
				
				employee.setCompanyBadge(employessListRs.getString("BADGE"));
				employee.setFkCompany(employessListRs.getString("FK_COMPANY"));
				employee.setIdEmployee(employessListRs.getString("ID_EMPLOYEE"));
				employee.setName(employessListRs.getString("NAME"));
				employee.setSurname(employessListRs.getString("SURNAME"));
				
				employeeList.add(employee);
				
				
			}
			System.out.println(employeeList.size());
			return employeeList;
			
		}catch(SQLException sqle) {
			throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
		}catch(Exception e) {
			throw new SQLException (e.getMessage());
		}finally {
			if (stmt != null ) stmt.close();
			if (conn != null ) conn.close();			
		}
		
		
		
	}


	private Connection getDBConnection() {
		// TODO Auto-generated method stub
		System.out.println("---------- MY SQL JDBC CONNECTION -------------");
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: MySQL JDBC Driver not found!!");
			try {
				throw new Exception (e.getMessage());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		// da verificare
		try {
			System.out.println(DB_CONNECTION);;
			System.out.println(DB_USER);;
			System.out.println(DB_PASSWORD);;
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			System.out.println("SQL Connection to Company_Management database estabilished!");
			
		}catch(SQLException sqle) {
			System.out.println("Connection to Company_Management database failed!");
			try {
					throw new SQLException(sqle.getErrorCode()+":"+sqle.getMessage());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dbConnection;
	}
	
	
	
	

}
