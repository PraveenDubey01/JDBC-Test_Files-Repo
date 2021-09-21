package edu.test;

import java.util.ArrayList;
import java.util.Scanner;

import edu.dao.CustomerDao;
import edu.dto.CustomerDto;
import edu.dto.EmployeeDto;

public class TestCustomer {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		CustomerDao cusDao = new CustomerDao();
		int choice;
		int accno;
		String cname;
		String email;
		int balance;
		String flag = null;
		CustomerDto cusDto =null;
		while (true) {
			System.out.println("Press 1 to Insert Customer");
			System.out.println("Press 2 to delete Customer");
			System.out.println("Press 3 to View Records Of Customer");
			System.out.println("Press 4 to View Complete Records Of All Customer");
			System.out.println("Press 5 to Update Record/Details Of Customer");
			System.out.println("Enter Your Choice : ");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Enter AccNumber : ");
				accno = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter Name of Customer");
				cname = sc.nextLine();
				System.out.println("Enter Email of Customer");
				email = sc.nextLine();
				System.out.println("Enter Balance of Customer");
				balance = sc.nextInt();
				cusDto = new CustomerDto();
				cusDto.setAccno(accno);
				cusDto.setCname(cname);
				cusDto.setEmail(email);
				cusDto.setBalance(balance);
				if (cusDao.insertCustomer(cusDto))
					System.out.println("Customer Registered Successfully");
				else
					System.out.println("Failed to Register New Customer");
				break;
			case 2:
				System.out.println("Enter AccNumber : ");
				accno = sc.nextInt();
				sc.nextLine();
				if (cusDao.deleteCustomer(accno))
					System.out.println("Customer De-registered Successfully");
				else
					System.out.println("Failed to De-register New Customer");
				break;
			case 3:
				System.out.println("Enter AccNumber : ");
				accno = sc.nextInt();
				sc.nextLine();
				System.out.println("Records of customer is as follows:- ");
				cusDto = cusDao.selectCustomer(accno);
				System.out.println(cusDto);
				break;
			case 4:
				System.out.println("Complete Records of All Customers:-");
				ArrayList<CustomerDto> cus = cusDao.selectallCustomer();
				if(cus.size()==0)
					System.out.println("No Employee Founded");
				else
				{
					System.out.println("Following are the Records :- ");
					for(CustomerDto c:cus)
					System.out.println(c);
				}
				break;
			case 5:
				System.out.println("Enter Customer Account Number : ");
				accno=sc.nextInt();
				sc.nextLine();
				cusDao.selectCustomer(accno);
			    cusDto = cusDao.selectCustomer(accno);
				System.out.println(cusDto);
				System.out.println("Do you want to change Name Of Customer ? if yes.. Comment Y");
				flag = sc.nextLine();
				if(flag.equalsIgnoreCase("Y")) 
				{
					System.out.println("Enter new Name : ");
					cusDto.setCname(sc.nextLine());
					}
				System.out.println("Do you want to change Email Of Customer ? if yes.. Comment Y");
				flag = sc.nextLine();
				if(flag.equalsIgnoreCase("Y")) 
				{
					System.out.println("Enter new Email id : ");
					cusDto.setEmail(sc.nextLine());
					}
				System.out.println("Do you want to Update Balance  ? if yes.. Comment Y");
				flag = sc.nextLine();
				if(flag.equalsIgnoreCase("Y"))
				{
					System.out.println("Enter Updated Balance : ");
					cusDto.setBalance(sc.nextInt());
					}
				if(cusDao.updateCustomer(cusDto))
				{
					System.out.println("Customer Details Updated Successfully ! , Recheck Current Details Of Customer");
					System.out.println(cusDto);					
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