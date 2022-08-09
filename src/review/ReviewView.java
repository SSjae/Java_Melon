package review;

import java.util.Scanner;

import dao.ReviewDAO;
import dao.SongDAO;

public class ReviewView {
	public ReviewView() {
		System.out.println("※주의※\n저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 리뷰는\n이용약관 및 관련 법률에 의해 제재를 받을 수 있습니다.\n");
		
		while(true) {
			Scanner sc = new Scanner(System.in);
			ReviewDAO rdao = new ReviewDAO();
			
			System.out.println("멋진 노래와 앨범에 대한 리뷰를 달아주세요!");
			System.out.println("--------------------------------------------------------------------");
			System.out.println("1. 노래 리뷰\t2. 앨범 리뷰\t3. 리뷰 수정 및 삭제\t4. 리뷰 조회\t5. 나가기");
			System.out.print("메뉴 선택 : ");
			int choice = sc.nextInt();
			
			if(choice ==  5) {
				System.out.println("리뷰 작성을 종료 합니다.");
				break;
			}
			switch(choice) {
			case 1:
				new SongReview();
				break;
				
			case 2:
				new ALbumReview();
				break;
				
			case 3:
				new ModifyReview();
				break;
				
			case 4:
				System.out.println("<나의 리뷰>");
				System.out.println(rdao.showMyReview());
				break;
				
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				break;
			}
		}
	}
}
