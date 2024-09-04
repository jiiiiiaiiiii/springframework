package com.mycompany.springframework.dto;

import lombok.Data;

@Data
// DAO 인터페이스와 JSP에서 사용
public class Ch13Pager {			//★: custom field(seed value) -> 나머지 필드는 계산에 의한 값
   private int totalRows;     	 		//★페이징 대상이 되는 전체 행수(테이블의 전체행수와 일치하지 않을 수 있음 => ex. "여행"을 포함한 title 등)
   private int totalPageNo;   		//전체 페이지 수: (totalRows/rowsPerPage)[+1]
   private int totalGroupNo;    		//전체 그룹 수
   private int startPageNo;   		//그룹의 시작 페이지 번호
   private int endPageNo;      	 	//그룹의 끝 페이지 번호
   private int pageNo;         		//★현재 페이지 번호: 사용자가 클릭한 페이지
   private int pagesPerGroup;   	//★그룹당 페이지 수
   private int groupNo;      			//현재 그룹 번호
   private int rowsPerPage;   		//★페이지당 행 수 
   private int startRowNo;      		//페이지의 시작 행 번호(1, ..., n) [for oracle]
   private int startRowIndex;   	//페이지의 시작 행 인덱스(0, ..., n-1) for mysql
   private int endRowNo;      		//페이지의 마지막 행 번호
   private int endRowIndex;   		//페이지의 마지막 행 인덱스

   public Ch13Pager(int rowsPerPage, int pagesPerGroup, int totalRows, int pageNo) {
      this.rowsPerPage = rowsPerPage;
      this.pagesPerGroup = pagesPerGroup;
      this.totalRows = totalRows;
      this.pageNo = pageNo;

      totalPageNo = totalRows / rowsPerPage;
      if(totalRows % rowsPerPage != 0) totalPageNo++;	// 나머지가 있으면 +1
      
      totalGroupNo = totalPageNo / pagesPerGroup;
      if(totalPageNo % pagesPerGroup != 0) totalGroupNo++;
      
      groupNo = (pageNo - 1) / pagesPerGroup + 1;
      
      startPageNo = (groupNo-1) * pagesPerGroup + 1;
      
      endPageNo = startPageNo + pagesPerGroup - 1;
      if(groupNo == totalGroupNo) endPageNo = totalPageNo;
      
      startRowNo = (pageNo - 1) * rowsPerPage + 1;	//시작'1' 일때, 현재 페이지에서 몇번째 행인지
      startRowIndex = startRowNo - 1;						//시작'0' 일때, 현재 페이지에서 몇번째 행인지
      endRowNo = pageNo * rowsPerPage;
      endRowIndex = endRowNo - 1; 
   }
}
