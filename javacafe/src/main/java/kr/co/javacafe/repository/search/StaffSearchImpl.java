package kr.co.javacafe.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import kr.co.javacafe.domain.QStaff;
import kr.co.javacafe.domain.Staff;
import kr.co.javacafe.dto.StaffListAllDTO;

public class StaffSearchImpl extends QuerydslRepositorySupport implements
StaffSearch{

	public StaffSearchImpl() {
		super(Staff.class);
	}

	@Override
	public Page<Staff> search1(Pageable pageable) {
		
		QStaff staff = QStaff.staff; //q도메인 객체
		
		JPQLQuery<Staff> query = from(staff); // select.. from staff
		BooleanBuilder booleanBuilder = new BooleanBuilder(); // (
		
		booleanBuilder.or(staff.sname.contains("11")); //sname like
		booleanBuilder.or(staff.saddr.contains("11")); //sname like

		query.where(booleanBuilder);
		query.where(staff.sno.gt(0L));
		
		query.where(staff.sname.contains("1")); // where sname like
		
		//paging
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<Staff> list = query.fetch();
		
		long count = query.fetchCount();
		
		
		return null;
	}

	@Override
	public Page<Staff> searcAll(String[] types, String keyword, Pageable pageable) {
		
		QStaff staff = QStaff.staff;
		JPQLQuery<Staff> query = from(staff);
		
		//검색 조건과 키워드가 있다면
		if( (types != null && types.length > 0) && keyword != null) {
			BooleanBuilder booleanBuilder = new BooleanBuilder();
			
			for(String type: types) {
				switch(type) {
				case "t" :
					booleanBuilder.or(staff.sname.contains(keyword));
					break;
				case "c" :
					booleanBuilder.or(staff.saddr.contains(keyword));
					break;
				case "w" :
					booleanBuilder.or(staff.saddr.contains(keyword));
					break;
				}
			}// end for
			query.where(booleanBuilder);
		}//end if
		
		//sno > 0
		
		query.where(staff.sno.gt(0L));
		
		//paging
		this.getQuerydsl().applyPagination(pageable, query);
		
		List<Staff> list = query.fetch();
		
		long count = query.fetchCount();
		return new PageImpl<>(list, pageable, count);
	}

	@Override
	public Page<StaffListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable) {
		
		
		return null;
	}

	
	
}
