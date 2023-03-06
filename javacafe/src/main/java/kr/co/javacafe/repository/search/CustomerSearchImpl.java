package kr.co.javacafe.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import kr.co.javacafe.domain.Customer;
import kr.co.javacafe.domain.QCustomer;


public class CustomerSearchImpl extends QuerydslRepositorySupport implements CustomerSearch{

	public CustomerSearchImpl() {
		super(Customer.class);
		
	}

	@Override
	public Page<Customer> search(Pageable pageable) {
		QCustomer customer = QCustomer.customer; // Q도메인 객체
		JPQLQuery<Customer> query = from(customer); // select from customer 객체
		
		// where절
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.or(customer.cname.contains("1")); // cname like concat()
		booleanBuilder.or(customer.cphone.contains("1")); // ceng like concat()
		query.where(booleanBuilder);
		query.where(customer.cno.gt(0L)); // cno가 0보다 큼
		
		// 페이징
		this.getQuerydsl().applyPagination(pageable, query);
		
		// 쿼리 실행
		List<Customer> list = query.fetch();
		long count = query.fetchCount();
		return null;
	}

	@Override
	public Page<Customer> searchAll(String[] types, String keyword, Pageable pageable) {
		QCustomer customer = QCustomer.customer; // Q도메인 객체
		JPQLQuery<Customer> query = from(customer); // select from recipe 객체
		
		if((types != null && types.length > 0) && keyword != null) { // 검색 조건과 키워드가 있다면
			// where절
			BooleanBuilder booleanBuilder = new BooleanBuilder();
			for(String type : types) {
				switch(type) {
				case "n" : 
					booleanBuilder.or(customer.cname.contains(keyword));
					break;
				case "p" : 
					booleanBuilder.or(customer.cphone.contains(keyword));
					break;
				} // end of switch
			} // end of for
			query.where(booleanBuilder);
		}// end of if
		
		// cno > 0
		query.where(customer.cno.gt(0L));
		
		// 페이징
		this.getQuerydsl().applyPagination(pageable, query);
		
		// 쿼리 실행
		List<Customer> list = query.fetch();
		long count = query.fetchCount();		
		
		return new PageImpl<>(list, pageable, count);
	}

}
