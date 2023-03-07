package kr.co.javacafe.domain;

import lombok.*;
import javax.persistence.*;

// 엔티티 객체를 위한 엔티티 클래스는 반드시 @entity를 적용해야하고 @id가 필요하다
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Event extends BaseEntity {

	// 게시물은 데이터베이스에 추가될 때 생성되는 번호(auto increment)를 이용할 것이므로
	// 키 생성전략(key generate strategy)중에 GenerationType.IDENTITY로 데이터베이스에서 알아서 결정하는 방식을 이용한다.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long eno;
	
	@Column(length = 500, nullable = false) // 칼럼의 길이와 null 허용 여부
	private String etitle;
	
	@Column(length = 2000, nullable = false)
	private String econtent;
	
	@Column(length = 50, nullable = false)
	private String ewriter;
	
	// Event에서 제목 & 내용 수정이 가능하므로 이에 맞도록 change 메소드 추가
	public void change(String etitle, String econtent) {
		this.etitle = etitle;
		this.econtent = econtent;
	}
	
}
