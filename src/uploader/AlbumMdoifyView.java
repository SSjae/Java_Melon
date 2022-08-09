package uploader;
import java.util.Scanner;

import dao.AlbumDAO;
import dao.Session;
import dao.SongDAO;
import dao.UserDAO;

public class AlbumMdoifyView {
	public AlbumMdoifyView() {
		
		UserDAO udao = new UserDAO();
		AlbumDAO adao = new AlbumDAO();
		Scanner sc = new Scanner(System.in);
		
		if(adao.uploaderAlbList().equals("")) {
			System.out.println("올린 앨범이 없습니다.\n");
		} else {
			while(true) {
				System.out.println("--------------------------------------------------------------------");
				System.out.println("번호\t\t앨범제목\t\t프로듀서\t\t발행날짜");
				System.out.println(adao.uploaderAlbList());
				System.out.println("--------------------------------------------------------------------");
				System.out.print("1. 앨범 수정하기\n2. 앨범 삭제하기\n3. 나가기\n메뉴 선택 : ");
				int choice = sc.nextInt();
				
				if(choice == 3) {
					break;
				}else if(choice == 2) {
					System.out.print("비밀번호를 재입력하세요 : ");
					String userpw = sc.next();
					if(udao.checkPw(userpw)) {
						System.out.print("삭제할 앨범 번호 선택 : ");
						int choice1 = sc.nextInt();
						if(adao.existSong(adao.uploaderAlbListAlbnum(choice1))) {
							System.out.println("앨범 안에 노래가 있습니다. 노래 먼저 삭제해주세요");
						}
						else {
							adao.removeAlbum(adao.uploaderAlbListAlbnum(choice1));
							System.out.println("앨범이 삭제되었습니다!");
							System.out.println("--------------------------------------------------------------------");
							break;
						}
					}
					//비밀번호 검사 실패
					else {
						System.out.println("비밀번호 오류입니다");
						break;
					}
				} else if(choice == 1) {
					System.out.print("수정할 앨범 번호를 입력하세요 : ");
					int choice3 = sc.nextInt();
					System.out.println("--------------------------------------------------------------------");
					System.out.println("< 앨범 정보 >");
					System.out.println(adao.albumInfo(adao.uploaderAlbListAlbnum(choice3)));
					System.out.println("--------------------------------------------------------------------");
					System.out.println("\n1. 앨범 제목\n2. 발매날짜(yyyy-mm-dd)\n3. 프로듀서");
					int choice4 = sc.nextInt();
					System.out.print("새로운 정보를 입력하세요 : ");
					sc = new Scanner(System.in);
					String newData = sc.nextLine();
			
					//세가지 입력받은 데이터를 DAO에 넘겨주기(행, 열, 새로운 데이터)
					if(adao.modifyAlbum(adao.uploaderAlbListAlbnum(choice3), choice4, newData)){
						System.out.println("정상적으로 수정되었습니다.");
						break;
					}
					else {
						System.out.println("알 수 없는 오류 / 다음에 다시 시도해 주세요.");
						break;
					}
				}
			}
		}
	}
}