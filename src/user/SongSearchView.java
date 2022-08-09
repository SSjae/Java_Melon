package user;

import java.util.Scanner;

import dao.SongDAO;
import dao.Session;

public class SongSearchView {
	public SongSearchView() {
        Scanner sc = new Scanner(System.in);
        SongDAO sdao = new SongDAO();

        System.out.println("------------------------------------------------------");
		System.out.println("노래 검색으로 넘어갑니다.");
		System.out.println("잠시만 기다려주세요.");
        System.out.println("------------------------------------------------------");
        System.out.println("최고의 뮤직플랫폼 멜론의 노래 검색입니다. 무슨 노래를 원하시나요?");
        System.out.println("트로트부터 발라드, 힙합 뿐만 아니라 해외 노래도 존재하는 멜론");
        System.out.println("------------------------------------------------------");
        System.out.println("1. 제목\n2. 장르\n3. 가수");
        System.out.println("------------------------------------------------------");
        System.out.print("메뉴 선택 : ");
        int choice = sc.nextInt();
        
        System.out.println("------------------------------------------------------");
        System.out.print("검색어 : ");
        sc = new Scanner(System.in);
        String keyword = sc.nextLine();
        
        System.out.println("------------------------------------------------------");
        System.out.println("'" + Session.get("login_id")+ "'" + "님이 검색한 노래입니다.");
        System.out.println("\n[노래 정보]");
//		System.out.println("번호\t제목\t가수\t작곡\t장르");
//        System.out.println("------------------------------------------------------");
        System.out.println("\t번호\t|\t\t제목\t\t|\t가수\t|\t작곡가\t|\t장르\t");
		System.out.println("----------------------------------------------------------"
				+ "----------------------------------------");
        System.out.println(sdao.search(keyword,choice));
        System.out.println("----------------------------------------------------------"
				+ "----------------------------------------");
        System.out.print("노래 선택 : ");
        int songchoice = sc.nextInt();
        new SelMusicView(sdao.searchsongnum(keyword, choice, songchoice));
    }
}