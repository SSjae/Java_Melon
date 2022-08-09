package user;

import java.util.Scanner;

import dao.UserDAO;

public class ModifyUserView {
    public ModifyUserView() {
        Scanner sc = new Scanner(System.in);
        UserDAO udao = new UserDAO();

        System.out.println("\n-------------------------------------------");
        System.out.println("회원 정보를 수정 및 추가합니다.목록↓");
        System.out.println("1. 비밀번호 수정\n2. 이름 수정\n3. 나이 수정\n"
                + "4. 핸드폰 번호 수정\n5. 이메일 주소 수정\n6. 닉네임 수정\n7. 수정 취소");
        System.out.println("-------------------------------------------");
        System.out.print("메뉴 선택 : ");
        int choice = sc.nextInt();
        System.out.println("-------------------------------------------");

        if (choice == 7) {
            System.out.println("회원 정보 수정을 취소합니다.\n※도용방지를 위해 비빌번호를 자주 변경 해주세요※");
        } else {
            System.out.print("비밀번호를 재확인합니다 : ");
            String userpw = sc.next();
            if (udao.checkPw(userpw)) {
            	System.out.println("-------------------------------------------");
                System.out.print("정보 수정 내용 : ");
                sc = new Scanner(System.in);
                String newData = sc.nextLine();
                System.out.println("-------------------------------------------");

                boolean check = false;

                if (udao.checkAdd(choice)) {
                    check = udao.modifyUser(choice, newData);
                } else {
                    System.out.println("수정 할 정보가 없습니다.");
                    System.out.println("-------------------------------------------");
                    System.out.println("1. Yes\t2. No");
                    System.out.println("-------------------------------------------");
                    System.out.print("메뉴 선택 : ");
                    int addchoice = sc.nextInt();
                    System.out.println("-------------------------------------------");

                    if(addchoice == 1) {
                        check = udao.modifyUser(choice, newData);
                        System.out.println("입력하신 정보를 성공적으로 수정했습니다.");
                    } else {
                        System.out.println("정보 수정이 취소됐습니다.");
                    }
                }

                if (check) {
                    System.out.println("회원님의 정보를 수정했습니다.");
                } else {
                    System.out.println("다시 입력 해주세요.");
                }
            } else {
                System.out.println("입력하신 비밀번호가 불일치합니다.\n※계정 도용은 범죄입니다※");
            }
        }
    }
}