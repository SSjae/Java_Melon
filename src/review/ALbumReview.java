package review;

import java.util.Scanner;

import dao.AlbumDAO;
import dao.ReviewDAO;
import dao.Session;

public class ALbumReview {
	
	public ALbumReview() {
		
		Scanner sc = new Scanner(System.in);
		AlbumDAO adao = new AlbumDAO();
		ReviewDAO rdao = new ReviewDAO();
		
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println("좋아하는 앨범에 자신의 의견을 달아주세요!");
		System.out.print("앨범 검색 : ");
		String search = sc.nextLine();
		System.out.println("--------------------------------------------------------------------\n");
		System.out.println(adao.albNameSearch(search));
		System.out.println("--------------------------------------------------------------------");
		
		if (adao.albNameSearch(search).equals("")) {
			System.out.println("'" + search + "'" + "로 검색된 결과가 없습니다.");
			System.out.println("다시 검색해 주세요.");
		} else {
		
			System.out.print("앨범 선택 : ");
			int mychoice = sc.nextInt();
			
			Session.put("albnum", adao.reviewSearchAlbnum(mychoice, search) + "");
			
			sc = new Scanner(System.in);
			System.out.println("--------------------------------------------------------------------");
			System.out.println("※주의※\n저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 리뷰는\n이용약관 및 관련 법률에 의해 제재를 받을 수 있습니다.");
			System.out.print("리뷰 작성 : ");
			String review = sc.nextLine();
			rdao.reviewWrite(2, review);
			System.out.printf("'%s'에 대한 리뷰가 작성되었습니다.\n",search);
			System.out.println("--------------------------------------------------------------------\n");
			Session.put("albnum", null);
		}
	}
}
