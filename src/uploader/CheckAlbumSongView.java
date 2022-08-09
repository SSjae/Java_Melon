package uploader;//임다솔

import java.util.Scanner;

import dao.AlbumDAO;
import dao.SongDAO;

public class CheckAlbumSongView {
	public CheckAlbumSongView() {
		
		while(true) {
			System.out.println("등록한 앨범 및 노래 조회 페이지입니다");
			
			Scanner sc = new Scanner(System.in);
			AlbumDAO adao = new AlbumDAO();
			SongDAO sdao = new SongDAO();
			
			System.out.print("1. 앨범 조회하기\n2. 노래조회하기\n3. 나가기\n메뉴 선택 : ");
			int choice = sc.nextInt();
			
			if(choice == 3) {
				break;
			}
			
			switch (choice) {
			case 1:
				if(adao.uploaderAlbList().equals("")) {
					System.out.println("올린 앨범이 없습니다.\n");
				} else {
					System.out.println("--------------------------------------------------------------------");
					System.out.println("번호\t\t앨범이름\t\t아티스트\t\t발행날짜");
					System.out.println(adao.uploaderAlbList());
					System.out.println("--------------------------------------------------------------------");
				}
				break;
	
			case 2:
				if(sdao.uplodersonglist().equals("")) {
					System.out.println("올린 노래가 없습니다.\n");
				} else {
					System.out.println("--------------------------------------------------------------------");
					System.out.println("번호\t\t노래제목\t\t가수\t\t작곡가\t\t장르");
					System.out.println(sdao.uplodersonglist());
					System.out.println("--------------------------------------------------------------------");
				}
				break;
			default :
				System.out.println("다시 입력하세요");
				break;
			}
		}
	}
}