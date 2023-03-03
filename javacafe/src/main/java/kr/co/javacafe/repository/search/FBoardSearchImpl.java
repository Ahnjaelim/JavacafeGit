package kr.co.javacafe.repository.search;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import kr.co.javacafe.domain.Board;
import kr.co.javacafe.domain.FBoard;
import kr.co.javacafe.domain.QBoard;
import kr.co.javacafe.domain.QFBoard;

public class FBoardSearchImpl extends QuerydslRepositorySupport implements FBoardSearch {

	
	 public FBoardSearchImpl(){
	        super(FBoard.class);
	    }
	@Override
	public Page<FBoard> search1(Pageable pageable) {
	
		QFBoard fboard = QFBoard.fBoard;

        JPQLQuery<FBoard> query = from(fboard);

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

        booleanBuilder.or(fboard.ftitle.contains("11")); // title like ...

        booleanBuilder.or(fboard.fcontent.contains("11")); // content like ....

        query.where(booleanBuilder);
        query.where(fboard.fno.gt(0L));


        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<FBoard> list = query.fetch();

        long count = query.fetchCount();

        return null; 
		
	}

	@Override
	public Page<FBoard> searchAll(String[] types, String keyword, Pageable pageable) {
		
		QFBoard fboard = QFBoard.fBoard;
        JPQLQuery<FBoard> query = from(fboard);

        if( (types != null && types.length > 0) && keyword != null ){ //검색 조건과 키워드가 있다면

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

            for(String type: types){

                switch (type){
                    case "t":
                        booleanBuilder.or(fboard.ftitle.contains(keyword));
                        break;
                    case "c":
                        booleanBuilder.or(fboard.fcontent.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(fboard.fwriter.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //bno > 0
        query.where(fboard.fno.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<FBoard> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);

    }

}
