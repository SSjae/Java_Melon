package view;

import java.util.Scanner;

import dao.UserDAO;
import dto.UserDTO;

public class JoinView {
	public JoinView() {
		
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		boolean perm = false;
		// -----------------------------------------------------------------------
		
		System.out.println("음악을 사랑하는 당신을 알려주세요!\n");
		System.out.println("1. '일반 유저'\t2. '아티스트'");
		System.out.print("메뉴 선택 : ");
		int choice =  sc.nextInt();
		
		switch (choice) {
		case 1:
			System.out.println("\n저는 음악을 사랑하는 '일반 유저'입니다!\n");
			break;

		case 2:
			System.out.println("\n저는 음악을 만드는 멋진 '아티스트' 입니다!\n");
			break;
		}
		if (choice == 2) {
			perm = true; 
		}
		
		System.out.println("--------------------------------------------------------------------");
		System.out.print("아이디 : ");
		String userid = sc.next();
		if(udao.checkDup(userid)) {
			System.out.print("비밀번호 : ");
			String userpw = sc.next();
			System.out.print("이름 : ");
			String username = sc.next();
			System.out.print("닉네임 : ");
			String usernick = sc.next();

			UserDTO user = new UserDTO(userid, userpw, username, usernick, perm);
			if(udao.join(user)) {
				System.out.println("회원가입 성공!");
				System.out.println("'" + username + "'" + "님 가입을 환영합니다!");
			}
			else {
				System.out.println("회원가입 실패!\n다시 시도해 주세요.");
			}
		}
		else {
			System.out.println("중복된 아이디가 있습니다. 다시 시도해 주세요.");
		}
	}
}


