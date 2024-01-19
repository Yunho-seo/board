# board
게시판 연습

18 할일 : 제목, 본문, 작성자 빈칸이면 Validation 검증해서 공백으로 못넘어가게 하기 / 수정기능, 삭제 기능 완성하기

19 할일 : search 완성, 조회수 increasing, search시 페이지 부동, 가능하면 디자인 
  <br> -> 우선 제목(Title)로만 search가 됨 (select 태그의 option이 소용이 없음 -> title, writer 옵션에 알맞게 검색되도록 수정해야함)
  -> 조회수 increasing은 못함 (regCount 필드에서 컬럼으로 조작해야 할듯?)
  -> search시 boardList.jsp url을 유지하지 못함 (ArrayList<BoardVO>의 list 객체가 dao.boardList() 전체를 불러오는건데 search 결과를 불러오면
     boardList()를 가져오지 못하게 됨, 그래서 BoardSearchController를 만들어서 로직 처리하고 결과 불러오는(검색하는) 페이지도 boardSearch.jsp 새로 만듬)
     이부분은 뭔가 애매함, 그래서 좋은 방법을 고민해보아야 할 듯
  -> 디자인은 백엔드부 작업 얼추 끝나면 프런트 작업할 때 부트스트랩이나 여러가지 css 적용해서 간단히 해보면 좋을 듯 하다)

20-21 할일 : 조회수 increasing, 페이지 디자인, 비밀번호 (디시인사이드처럼 글 작성할때 비밀번호 설정해놓고 수정/삭제 시 비밀번호를 통한 접근) - 이거 할 수 있을까?
