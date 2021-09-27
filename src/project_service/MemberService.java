package project_service;

import project.Container;
import project_dao.MemberDao;
import project_dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = Container.memberDao;
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public int getMemberIndexByLoginId(String loginId) {
		return memberDao.getMemberIndexByLoginId(loginId);
	}

	public boolean isJoinableLoginId(String loginId) {
		return memberDao.isJoinableLoginId(loginId);
	}

	public void join(Member member) {
		memberDao.add(member);
	}

	public void withdraw(Member member) {
		memberDao.remove(member);
	}

	public String getMemberNameById(int memberId) {
		return memberDao.getMemberNameById(memberId);
	}

}
