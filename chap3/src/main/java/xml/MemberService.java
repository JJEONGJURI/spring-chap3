package xml;

public class MemberService {
	public void regist(Member member) {
		System.out.println("MemberService.regist() 메서드 실행");
	}
	public boolean update(String memberid, UpdateInfo info) {
		System.out.println("MemberService.update() 메서드 실행");
		return true;
	}	
	public boolean delete(String id, String info) { //update랑 자료형 다름 (UpdateInfo, String)
		System.out.println("MemberService.delete() 메서드 실행");
		return false;
	}
	

}
