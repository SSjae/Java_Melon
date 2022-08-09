package review;

import java.util.Scanner;

import dao.ReviewDAO;

public class ModifyReview {
	public ModifyReview() {
		
		Scanner sc = new Scanner(System.in);
		ReviewDAO rdao = new ReviewDAO();
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println("리뷰 수정 및 삭제\n");
		System.out.println("1. 리뷰 수정\t2. 리뷰 삭제");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		System.out.println("--------------------------------------------------------------------\n");
		
		if (choice == 1) {
			System.out.println("리뷰를 수정합니다.");
			System.out.println("--------------------------------------------------------------------\n");
			System.out.println(rdao.showMyReview());
			System.out.println("--------------------------------------------------------------------\n");
			
			System.out.print("수정할 리뷰 선택 : ");
			int mychoice = sc.nextInt();
			System.out.println();
			
			sc = new Scanner(System.in);
			System.out.print("수정할 내용 : ");
			String review = sc.nextLine();
			
			if(rdao.modifyReview(rdao.getReviewNum(mychoice), review)) {
				System.out.println("리뷰 수정을 완료하였습니다.");
			} else {
				System.out.println(rdao.getReviewNum(mychoice));
				System.out.println("리뷰 수정에 실패하였습니다. 다시 시도하여 주세요.");
			}
			
		} else if (choice == 2) {
			System.out.println("리뷰를 삭제합니다.");
			System.out.println(rdao.showMyReview());
			
			System.out.print("삭제할 리뷰 선택 : ");
			int mychoice = sc.nextInt();
			
			if(rdao.deleteReview(rdao.getReviewNum(mychoice))) {
				System.out.println("리뷰를 삭제하였습니다.");
			} else {
				System.out.println("리뷰 삭제에 실패하였습니다.\n다시 시도하여 주세요.");
			}
			
		}
		
	}

}
