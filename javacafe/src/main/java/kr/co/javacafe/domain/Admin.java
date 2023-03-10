package kr.co.javacafe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ano;
	
	@Column(length = 100, nullable = false)
	private String id;
	
	@Column(length = 100, nullable = false)
	private String pw;
	
	
	public void change(String id, String pw) {
		this.id = id;
		this.pw = pw;
		
	}
		
		 
}
