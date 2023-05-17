package xml;

public class ReadArticleServiceImpl implements ReadArticleService {

	@Override
	public Article getArticleAndReadCnt(int id) throws Exception {
		System.out.println("getArticleAndReadCnt("+id+") 호출");
		if(id ==0 ) {
			throw new Exception("id는 0이 안됨"); 
			//예외 발생 => 모든 정상적인 처리 멈추고 예외 처리 들어간다(예외처리방식 1.try catch 2.throws)
			//밑에 Article 객체 리턴못함
		}
		return new Article(id); //new Article(1) 리턴
	}

}
