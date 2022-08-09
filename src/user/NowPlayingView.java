package user;

import dao.Session;
import dao.SongDAO;

public class NowPlayingView {
	SongDAO sdao = new SongDAO();
	
	public NowPlayingView() {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("♬♩♪~ 현재 재생 중인 곡 ~♩♪♬\n");
		if(Session.get("now_playing") == null) {
			System.out.println("현재 재생중인 노래가 없습니다.");
		} else {
			System.out.println("♬♩♪~ " + sdao.selmusic(Integer.parseInt(Session.get("now_playing")))
					+ "를(을) 재생 중 ♩♪♬~");
		}
		System.out.println("--------------------------------------------------------------------");
	}
}