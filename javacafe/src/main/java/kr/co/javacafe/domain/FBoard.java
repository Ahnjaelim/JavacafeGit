package kr.co.javacafe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FBoard extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fno;

	@Column(length = 500, nullable = false)
	private	String ftitle;
	
	@Column(length = 2000, nullable = false)
	private	String fcontent;

	@Column(length = 50, nullable = false)
	private	String fwriter;
	
	public void change(String ftitle, String fcontent) {
		this.ftitle = ftitle;
		this.fcontent = fcontent; 
	}
	
}
