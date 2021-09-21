package edu.test;

import java.util.ArrayList;
import java.util.Scanner;
import edu.dao.EmployeeDao;
import edu.dto.EmployeeDto;

public class TestEmployee {
	public static void main(String[] args) throws Exception {
		EmployeeDao empDao = new EmployeeDao();
		Scanner sc = new Scanner(System.in);
		int choice;
		int eid;
		String ename;
		double salary;
		String deptno;
		String flag;
		EmployeeDto empDto = null;
		while (true) {
			System.out.println("Press 1 to Insert Employee");
			System.out.println("Press 2 to delete Employee");
			System.out.println("Press 3 to View Record of Employee");
			System.out.println("Press 4 to view Complete REcords of All Employees");
			System.out.println("Press 5 to Update details/Records of Employee");
			System.out.println();
			System.out.println("Enter Your Choice : ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Enter Emp id : ");
				eid = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Name of Employee");
				ename = sc.nextLine();
				System.out.println("Enter Salary of Employee");
				salary = sc.nextDouble();
				sc.nextLine();
				System.out.println("Enter Dept no of Employee");
				deptno = sc.nextLine();
				empDto = new EmployeeDto();
				empDto.setEid(eid);
				empDto.setEname(ename);
				empDto.setSalary(salary);
				empDto.setDeptno(deptno);
				if (empDao.insertEmployee(empDto))
					System.out.println("Employee Registered Successfully");
				else
					System.out.println("Failed to Register New Employee");
				break;
			case 2:
				System.out.println("Enter Emp id : ");
				eid = sc.nextInt();
				sc.nextLine();
				if (empDao.deleteEmployee(eid))
					System.out.println("Employee De-registered Successfully");
				else
					System.out.println("Failed to De-register New Employee");
				break;
			case 3:
				System.out.println("Enter EmpId : ");
				eid = sc.nextInt();
				sc.nextLine();
				System.out.println("Records of Employee is as follows:- ");
				empDto = empDao.selectEmployee(eid);
				System.out.println(empDto);
				break;
			case 4:
				System.out.println("complete Records Of all Employee");;
				ArrayList<EmployeeDto> emps = empDao.selectallEmployee();
				if(emps.size()==0)
					System.out.println("No Employee Founded");
				else
				{
					System.out.println("Following are the Records");
					for(EmployeeDto e:emps)
					System.out.println(e);
				}
				break;
			case 5:
				System.out.println("Enter Employee id : ");
				eid=sc.nextInt();
				sc.nextLine();
				empDao.selectEmployee(eid);
				EmployeeDto emp = empDao.selectEmployee(eid);
				System.out.println(emp);
				System.out.println("Do you want to change Name ? if yes.. Comment Y");
				flag = sc.nextLine();
				if(flag.equalsIgnoreCase("Y")) 
				{
					System.out.println("Enter new Name : ");
					emp.setEname(sc.nextLine());
					}
				System.out.println("Do you want to change Salary ? if yes.. Comment Y");
				flag = sc.nextLine();
				if(flag.equalsIgnoreCase("Y")) 
				{
					System.out.println("Enter new Salary : ");
					emp.setSalary(sc.nextDouble());
					sc.nextLine();
					}
				System.out.println("Do you want to change Department  ? if yes.. Comment Y");
				flag = sc.nextLine();
				if(flag.equalsIgnoreCase("Y")) 
				{
					System.out.println("Enter new Department : ");
					emp.setDeptno(sc.nextLine());
					}
				if(empDao.updateEmployee(emp))
				{
					System.out.println("Employee Details Updated Successfully ! , Recheck Current Details Of Employee");
					System.out.println(emp);					
				}
				else
					System.out.println("Failed To update Details ! Sorry :(");
				break;
			default:
				System.exit(1);
			}
		}
	}
}