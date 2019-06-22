package com.cg.usermanagement.ui;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.usermanagement.dto.User;
import com.cg.usermanagement.exceptions.UMSException;
import com.cg.usermanagement.service.UserService;

public class Main {
	static Logger logger = Logger.getRootLogger();

	public static void main(String[] args) throws SQLException, UMSException {
		// TODO Auto-generated method stub
		PropertyConfigurator.configure("resources//log4j.properties");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		User details = new User();
		UserService service = new UserService();
		boolean admincheckflag = false;
		boolean emailcheckflag = false;
		boolean fullnamecheckflag = false;
		boolean passwordcheckflag = false;
		boolean idcheckflag = false;

		do {
			do {
				System.out.println("Enter the Email");
				String adminEmail = scanner.next();
				details.setadmin_email(adminEmail);
				emailcheckflag = service.emailpattern(details);
			} while (!emailcheckflag);

			do {
				System.out.println("Enter the Password");
				String adminpassword = scanner.next();
				details.setAdminpassword(adminpassword);
				passwordcheckflag = service.passwordcheck(details);
			} while (!passwordcheckflag);

			admincheckflag = service.logincheck(details);
			if(admincheckflag==false)
			{
				System.err.println("The given login credentials are incorrect");
				System.out.println("Please enter the correct credentials");
			}

		} while (!admincheckflag);
		String continueChoice = "";
		boolean continueValue = false;
		do {
			System.out.println("****** User Management System ******");
			System.out.println("1.User List");
			System.out.println("2.Create New User");
			System.out.println("3.Edit User");
			System.out.println("4.Delete User");

			int choice = 0;
			boolean choiceFlag = false;

			do {
				try {
					System.out.println("Enter The Choice");
					Scanner s1 = new Scanner(System.in);
					choice = s1.nextInt();

					switch (choice) {

					case 1:

						List<User> list = service.userList();
						if (list != null) {
							Iterator<User> iterator = list.iterator();
							while (iterator.hasNext()) {
								System.out.println(iterator.next());
							}
						}
						System.out.println("1.Edit");
						System.out.println("2.Delete");
						System.out.println("3.Main Menu");

						int i = 0;
						i = scanner.nextInt();
						switch (i) {
						case 1:

							do {
								try {
									boolean idverificationflag = false;
									do {

										System.out.println("Enter the user Id");
										@SuppressWarnings("resource")
										Scanner s = new Scanner(System.in);
										int id = s.nextInt();

										details.setID(id);
										idverificationflag = service.Idverification(details);
										if (idverificationflag) {
											continue;
										} else {
											System.out.println("The given ID does not exist");
										}
									} while (!idverificationflag);
									idcheckflag = true;
								} catch (InputMismatchException e) {
									idcheckflag = false;
									System.err.println("please enter only digits");
								}
							} while (!idcheckflag);
							emailcheckflag = false;
							do {
								System.out.println("Enter the Email");
								String mail = scanner.next();
								details.setEmail(mail);
								emailcheckflag = service.emailpattern(details);
							} while (!emailcheckflag);
							do {
								System.out.println("Enter the FullName");
								String fullname;
								fullname = scanner.next();
								details.setFullname(fullname);
								fullnamecheckflag = service.fullnamepattern(details);
							} while (!fullnamecheckflag);
							do {
								System.out.println("Enter the Password");
								String password;
								password = scanner.next();
								details.setPassword(password);
								passwordcheckflag = service.passwordcheck(details);
							} while (!passwordcheckflag);
							service.editUser(details);
							break;

						case 2:

							do {
								try {
									boolean idverificationflag = false;
									do {

										System.out.println("Enter the user Id");
										Scanner s = new Scanner(System.in);
										int id = s.nextInt();
										details.setID(id);
										idverificationflag = service.Idverification(details);
									} while (!idverificationflag);
									service.deleteUser(details);
									idcheckflag = true;
								} catch (InputMismatchException e) {
									idcheckflag = false;
									System.err.println("please enter only digits");
								}
							} while (!idcheckflag);
							break;

						case 3:

							break;
						}

						choiceFlag = true;
						break;

					case 2:
						boolean newUserFlag = true;
						do {
							do {
								System.out.println("Enter the Email");
								String mail = scanner.next();
								details.setEmail(mail);
								emailcheckflag = service.useremailpattern(details);
							} while (!emailcheckflag);
							do {
								System.out.println("Enter the FullName");
								String fullname;
								fullname = scanner.next();
								details.setFullname(fullname);
								fullnamecheckflag = service.fullnamepattern(details);
							} while (!fullnamecheckflag);
							do {
								System.out.println("Enter the Password");
								String password;
								password = scanner.next();
								details.setPassword(password);
								passwordcheckflag = service.userpasswordcheck(details);
							} while (!passwordcheckflag);
							int userId = 0;
							userId = service.newUser(details);
							if (userId != 0) {
								System.out.println("The user is created with userId" + userId);
								logger.info("New user added successfully");

							} else {
								System.out.println("Enter other email");
								newUserFlag = false;

							}
						} while (!newUserFlag);
						choiceFlag = true;
						break;

					case 3:

						do {
							try {
								boolean idverificationflag = false;
								do {

									System.out.println("Enter the user Id");
									Scanner s = new Scanner(System.in);
									int id = s.nextInt();

									details.setID(id);
									idverificationflag = service.Idverification(details);
								} while (!idverificationflag);
								idcheckflag = true;
							} catch (InputMismatchException e) {
								idcheckflag = false;
								System.err.println("please enter only digits");
							}
						} while (!idcheckflag);

						emailcheckflag = false;
						do {
							System.out.println("Enter the Email");
							String mail = scanner.next();
							details.setEmail(mail);
							emailcheckflag = service.useremailpattern(details);
						} while (!emailcheckflag);
						do {
							System.out.println("Enter the FullName");
							String fullname;
							fullname = scanner.next();
							details.setFullname(fullname);
							fullnamecheckflag = service.fullnamepattern(details);
						} while (!fullnamecheckflag);
						do {
							System.out.println("Enter the Password");
							String password;
							password = scanner.next();
							details.setPassword(password);
							passwordcheckflag = service.userpasswordcheck(details);
						} while (!passwordcheckflag);
						service.editUser(details);
						choiceFlag = true;
						break;

					case 4:

						do {
							try {
								boolean idverificationflag = false;
								do {

									System.out.println("Enter the user Id");
									Scanner s = new Scanner(System.in);
									int id = s.nextInt();

									details.setID(id);
									idverificationflag = service.Idverification(details);
								} while (!idverificationflag);
								service.deleteUser(details);
								idcheckflag = true;
							} catch (InputMismatchException e) {
								idcheckflag = false;
								System.err.println("please enter only digits");
							}
						} while (!idcheckflag);
						choiceFlag = true;
						break;

					}

				} catch (InputMismatchException e) {
					choiceFlag = false;
					System.err.println("please enter only digits");

				}
			} while (!choiceFlag);

			do {
				scanner = new Scanner(System.in);
				System.out.println("do you want to continue again [yes/no]");
				continueChoice = scanner.nextLine();
				if (continueChoice.equalsIgnoreCase("yes")) {
					continueValue = true;
					break;
				} else if (continueChoice.equalsIgnoreCase("no")) {
					System.out.println("thank you");
					continueValue = false;
					break;
				} else {
					System.out.println("enter yes or no");
					continueValue = false;
					continue;
				}
			} while (!continueValue);

		} while (continueValue);
	}
}
