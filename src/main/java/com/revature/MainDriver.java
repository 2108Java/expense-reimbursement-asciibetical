package com.revature;
import com.revature.repo.RequestDAO;
import com.revature.repo.RequestDAOImpl;
import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;
import com.revature.service.EmployeeServiceImpl;
public class MainDriver {

	public static void main(String[] args) {
		UserDAO uDao = new UserDAOImpl();
		RequestDAO rDao = new RequestDAOImpl();
		EmployeeServiceImpl test = new EmployeeServiceImpl(uDao, rDao);
		System.out.println("hello");
		test.createNewAccount("user4", "lilmissrayna@gmail.com");
		

	}

}
