package uploader;

import java.util.Scanner;

import dao.UserDAO;

public class ModifyUploaderView {
    public ModifyUploaderView() {
        Scanner sc = new Scanner(System.in);
        UserDAO udao = new UserDAO();
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("회원정보를 수정하는 페이지입니다");
        System.out.println("\n회원 정보를 수정 및 추가합니다");
        System.out.println("\n1. 비밀번호 수정\n2. 이름 수정\n3. 나이 수정\n"
                + "4. 핸드폰 수정\n5. 이메일 수정\n6. 닉네임 수정\n7. 수정 취소");
        System.out.print("메뉴 선택 : "); 
        int choice = sc.nextInt();
        System.out.println("--------------------------------------------------------------------");


        if (choice == 7) {
            System.out.println("회원 정보 수정을 취소합니다");
        } else {
            System.out.print("\n※비밀번호를 다시 입력해 주세요※ : ");
            String userpw = sc.next();
            if (udao.checkPw(userpw)) {
                System.out.print("새로운 정보를 입력해 주세요 : ");
                sc = new Scanner(System.in);
                String newData = sc.nextLine();

                boolean check = false;

                if (udao.checkAdd(choice)) {
                    check = udao.modifyUser(choice, newData);
                } else {
                    System.out.println("\n※수정할 정보가 없습니다※");
                    System.out.println("1. Yes\t2. No");
                    System.out.print("새로운 정보를 추가하시겠습니까?");
                    int addchoice = sc.nextInt();

                    if(addchoice == 1) {
                        check = udao.modifyUser(choice, newData);
                        System.out.println("새로운 정보를 추가 하였습니다");
                    } else {
                        System.out.println("취소되었습니다");
                    }
                }

                if (check) {
                    System.out.println("회원 정보를 수정하였습니다.\n");
                } else {
                    System.out.println("※다시 입력해 주세요※\n");
                }
            } else {
                System.out.println("※비밀번호가 일치하지 않습니다. 다시 시도하여 주세요※\n");
            }
        }
    }
}
