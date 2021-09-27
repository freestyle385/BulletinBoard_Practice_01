package project_dao;

import java.util.ArrayList;
import java.util.List;

import project.Container;
import project_dto.Member;

public class MemberDao extends Dao {

	public List<Member> members;

	public MemberDao() {
		members = new ArrayList<>();
	}

	public void add(Member member) {
		members.add(member);
		lastId = member.id;
	}

	public int getMemberIndexByLoginId(String loginId) {
		// loginId를 통해 회원의 배열 인덱스를 알아냄(0 ~ )
		// 중복된 아이디가 없을 경우 인덱스 범위 외의 -1를 반환
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);

	}

	public boolean isJoinableLoginId(String loginId) {
		// getMemberIndexByLoginId의 리턴값(배열 인덱스)을 통해 가입할 아이디의 중복여부를 나타냄
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}
		return false;
	}

	public void remove(Member member) {
		members.remove(member);
	}

	public String getMemberNameById(int memberId) {
		for (Member member : members) {
			if (member.id == memberId) {
				return member.name;
			}
		}

		return "";
	}

}
