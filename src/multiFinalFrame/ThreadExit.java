package multiFinalFrame;

import Info.userNames;
import client.gameClientReadMsg;
import client.gameUserList;
import client.scoreAll;
import view.MainFrame;

public class ThreadExit {

	public ThreadExit() throws Exception {
		gameClientReadMsg.serverSongRandom.clear();
		gameClientReadMsg.interrupted();
		// 타이머 있으면 타이머도 끄기!!
		// Game.interrupted(); 혹시 몰라
		// MusicPlayer.interrupted(); 혹시 몰라
		// 메인버튼 클라스 쓰레드 임시 보류
		userNames.flag = false;
		MainFrame.MainBackMusic.close();
		gameUserList.gameUserName.clear();
		scoreAll.userScore.clear();

		
		//gameClient.getSocket().close(); // 맨마지막으로 옮기기!
	}

}
