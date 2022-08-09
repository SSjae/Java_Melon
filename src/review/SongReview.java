package review;

import java.util.Scanner;

import dao.ReviewDAO;
import dao.Session;
import dao.SongDAO;

public class SongReview {
	
	public SongReview() {
	
		Scanner sc = new Scanner(System.in);
		SongDAO sdao = new SongDAO();
		ReviewDAO rdao = new ReviewDAO();
		
		System.out.println("\n--------------------------------------------------------------------");
		System.out.println("좋아하는 노래에 자신의 의견을 달아주세요!");
		System.out.print("노래 검색 : ");
		String search = sc.nextLine();
		if (sdao.songNameSearch(search).equals("")) {
			System.out.println("'" + search + "'" + "로 검색된 결과가 없습니다.");
			System.out.println("다시 검색해주세요.");
		} else {
			System.out.println("'" + search + "'" + "로 검색한 결과 입니다.");
			System.out.println("--------------------------------------------------------------------\n");
			System.out.println(sdao.songNameSearch(search));
			System.out.println("--------------------------------------------------------------------");
			
			System.out.print("노래 선택 : ");
			int mychoice = sc.nextInt();
			Session.put("songnum", (sdao.reviewSearchSongnum(mychoice, search) + ""));// search로 검색된 songnum 받아오기
			
			System.out.println("--------------------------------------------------------------------");
			sc = new Scanner(System.in);
			System.out.println("※주의※\n저작권 등 다른 사람의 권리를 침해하거나 명예를 훼손하는 리뷰는\n이용약관 및 관련 법률에 의해 제재를 받을 수 있습니다.");
			System.out.print("\n리뷰 작성  : ");
			String review =  sc.nextLine();
			rdao.reviewWrite(1, review);
			System.out.printf("'%s'에 대한 리뷰가 작성되었습니다.\n",search);
			System.out.println("--------------------------------------------------------------------\n");
			Session.put("songnum", null);
		}
	}
}
