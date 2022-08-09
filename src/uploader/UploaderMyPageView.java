package uploader;

import java.util.Scanner;

import com.mysql.cj.Session;

public class UploaderMyPageView {
	public UploaderMyPageView() {
		while(true) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("'업로더' 마이페이지 입니다");
			System.out.print("\n1. 등록한 앨범 및 노래 조회\n2. 앨범 수정 및 삭제\n3. "
					+ "노래수정 및 삭제\n4. 회원수정 및 탈퇴\n5. 이전페이지로 이동합니다\n");
			System.out.println("--------------------------------------------------------------------");
			System.out.print("메뉴 선택 : ");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if(choice == 5) {
				System.out.println("이전페이지로 이동합니다");
				break;
			}
			
			
			switch(choice) {
			case 1:
				new CheckAlbumSongView();
				break;
			case 2:
				new AlbumMdoifyView();
				break;
			case 3:
				new SongModifyView();
				break;
			case 4:
				new ModifyView();		
				break;
			default:
				System.out.println("※다시 입력해 주세요※\n");
			}
		}
	}
}