package kr.co.javacafe.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import kr.co.javacafe.domain.KioskState;
import kr.co.javacafe.domain.QKioskState;

public class KioskStateSearchImpl extends QuerydslRepositorySupport implements KioskStateSearch{

	public KioskStateSearchImpl() {
		super(KioskState.class);
	}

	@Override
	public Page<KioskState> searchAll(String[] types, String keyword, Pageable pageable) {
		
		QKioskState kioskState = QKioskState.kioskState;
		JPQLQuery<KioskState> query = from(kioskState);
		
		// 검색 조건과 키워드가 있다면
		if((types != null && types.length > 0) && keyword != null) {
			BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
			for(String type: types) {
				switch(type) {
				case "t":
					booleanBuilder.or(kioskState.kid.contains(keyword));
					break;
				}
			} // end for
			query.where(booleanBuilder);
		} // end if
		
		query.where(kioskState.ksno.gt(0L));
		
		// paging
		this.getQuerydsl().applyPagination(pageable, query);
		List<KioskState> list = query.fetch();
		long count = query.fetchCount();
		return new PageImpl<>(list, pageable, count);
				
	}

	
}
