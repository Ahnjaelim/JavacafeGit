package kr.co.javacafe.repository.search;

import java.util.List;

import org.hibernate.dialect.pagination.SQL2008StandardLimitHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

import ch.qos.logback.core.joran.conditional.Condition;
import kr.co.javacafe.domain.Admin;
import kr.co.javacafe.domain.QAdmin;



public class AdminSearchImpl extends QuerydslRepositorySupport implements AdminSearch {

    public AdminSearchImpl(){
        super(Admin.class);
        
    }
    

	@Override
	public Page<Admin> asearch(Pageable pageable) {

        QAdmin admin = QAdmin.admin;

        JPQLQuery<Admin> query = from(admin);

        BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

        booleanBuilder.or(admin.id.contains("11")); // iname like ...        

        query.where(booleanBuilder);
        query.where(admin.ano.gt(0L));


        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Admin> list = query.fetch();

        long count = query.fetchCount();


        return null;

	}


	@Override
	public Page<Admin> asearchAll(String[] types, String keyword, Pageable pageable) {
      
		QAdmin admin = QAdmin.admin;
        JPQLQuery<Admin> query = from(admin);

        if( (types != null && types.length > 0) && keyword != null ){ //검색 조건과 키워드가 있다면

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

            for(String type: types){

                switch (type){
                    case "i":
                        booleanBuilder.or(admin.id.contains(keyword));
                        break;
                                     
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //bno > 0
        query.where(admin.ano.gt(0L));

        //paging
        this.getQuerydsl().applyPagination(pageable, query);

        List<Admin> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);

    }






}