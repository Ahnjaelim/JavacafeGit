package kr.co.javacafe.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Shop extends BaseEntity {

	// 엔티티 이름을 order로 하려고 했으나...order by 때문인지 테이블 생성이 안됨ㅋ
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sno;
	
	private String sid;
	
	private Long rno;
	
	private Long scount;
	
	private Long sprice;
	
	private String cphone;
}
