package uploader;//임다솔

import java.util.Scanner;

import dao.AlbumDAO;
import dao.Session;
import dao.SongDAO;
import dto.SongDTO;

public class AddSongView {
	
	public AddSongView() {
	
		while(true) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("'" + Session.get("login_id") + "'" + "님의 멋진 노래를 등록해주세요!");
			System.out.println("--------------------------------------------------------------------");
			Scanner sc= new Scanner(System.in);
			AlbumDAO adao = new AlbumDAO();
			SongDAO sdao = new SongDAO();
			
			System.out.print("1. 노래 등록하기\n2. 취소(이전페이지)\n메뉴 선택 : ");
			int choice = sc.nextInt();
			
			if(choice == 2) {
				System.out.println("이전 페이지로 돌아갑니다");
				System.out.println("--------------------------------------------------------------------");
				break;
			} else if(choice == 1) {
				if(adao.uploaderAlbList().equals("")) {
					System.out.println("올린 앨범이 없습니다.");
					System.out.println("앨범을 추가하고 다시 이용해주세요.");
					break;
				} else {
					System.out.println("--------------------------------------------------------------------");
					System.out.println("\t번호\t\t앨범제목\t\t아티스트\t\t발행날짜");
					System.out.println("--------------------------------------------------------------------");
					System.out.println(adao.uploaderAlbList());
					System.out.println("--------------------------------------------------------------------");
					System.out.print("앨범 선택 : ");
					int albchoice = sc.nextInt();
					int albnum = adao.uploaderAlbListAlbnum(albchoice);
					
					System.out.print("노래 제목 입력: ");
					sc = new Scanner(System.in);
					String songname = sc.nextLine();
					
					System.out.print("가수 입력 : ");
					sc = new Scanner(System.in);
					String singer = sc.nextLine();
					
					System.out.print("장르 입력 : ");
					sc = new Scanner(System.in);
					String genre = sc.nextLine();
					
					System.out.print("작곡가 입력 : ");
					sc = new Scanner(System.in);
					String writer = sc.nextLine();
					
					System.out.print("작사가 입력 : ");
					sc = new Scanner(System.in);
					String lyricist = sc.nextLine();
					
					System.out.print("노래 정보 입력 : ");
					sc = new Scanner(System.in);
					String songinfo = sc.nextLine();
					
					System.out.print("노래 가사 입력 : ");
					sc = new Scanner(System.in);
					String lyrics = sc.nextLine();
					
					SongDTO song = new SongDTO(songname, songinfo, lyrics, singer, genre, writer, lyricist, albnum, Session.get("login_id"));
					
					if (sdao.addSong(song)) {
						System.out.println("'" + Session.get("login_id") + "'" + "님의 멋잇는 노래를 추가했습니다!");
						System.out.println("--------------------------------------------------------------------");
					} else {
						System.out.println("노래 추가를 실패했습니다! 다시 시도해주세요");
					}
				}
			} else {
				System.out.println("잘못 입력 하셨습니다.");
			}
		}
	}
}