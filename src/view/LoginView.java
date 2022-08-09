package view;

import java.util.Scanner;

import dao.UserDAO;
import dto.UserDTO;
import uploader.UploaderView;
import user.UserView;

public class LoginView {
	
	public LoginView() {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		
		System.out.println("--------------------------------------------------------------------");
		System.out.print("아이디 : ");
		String userid = sc.next();
		System.out.print("비밀번호 : ");
		String userpw = sc.next();
		System.out.println("--------------------------------------------------------------------");
		
		if(udao.login(userid,userpw)) {
			System.out.println("'" + userid + "'" + "님 어서오세요~");
			
			if (udao.perm(userid) == false) {
				new UserView();
			} else if (udao.perm(userid) == true) {
				new UploaderView();
			}
			
		}
		else {
			System.out.println("아이디 / 비밀번호 오류 입니다. 다시 시도해 주세요.");
		}
	}
}
		

