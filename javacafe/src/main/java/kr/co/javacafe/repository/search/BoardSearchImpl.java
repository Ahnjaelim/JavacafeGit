package kr.co.javacafe.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import kr.co.javacafe.domain.Board;
import kr.co.javacafe.domain.QBoard;


public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {

	public BoardSearchImpl() {
		super(Board.class);
	}

		
	@Override
	public Page<Board> search1(Pageable pageable) { 
		
		QBoard board = QBoard.board; 
		JPQLQuery<Board> query = from(board); // select.. from board

		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		booleanBuilder.or(board.title.contains("11")); // title like...
		
		booleanBuilder.or(board.content.contains("11")); // content like...
		
		query.where(booleanBuilder);
		
		query.where(board.bno.gt(0L));
		
		query.where(board.title.contains("1")); // where title like ...

		//paging 시작
		this.getQuerydsl().applyPagination(pageable, query);
		
		//JPQLQuery fetch 시작 부분
		
		List<Board> List = query.fetch();
		
		long count = query.fetchCount();
		
		return null;
	}

	@Override
	public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {
		
		QBoard board = QBoard.board;
		JPQLQuery<Board> query = from(board);
		
		// 검색 조건과 키워드가 있다면
		if((types != null && types.length > 0) && keyword != null) {
			BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
			
			for(String type: types) {
				switch(type) {
				
				case "t":
					booleanBuilder.or(board.title.contains(keyword));
					break;
				case "c":
					booleanBuilder.or(board.content.contains(keyword));
					break;
				case "w":
					booleanBuilder.or(board.writer.contains(keyword));
				}
			} // end for
			query.where(booleanBuilder);
		} // end if
		
		// bno > 0
		query.where(board.bno.gt(0L));
		
		// paging
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<Board> list = query.fetch();
		
		long count = query.fetchCount();
		
		return new PageImpl<>(list, pageable, count);
	}

}
