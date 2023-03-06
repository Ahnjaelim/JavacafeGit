package kr.co.javacafe.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "staff")
public class Staffimage implements Comparable<Staffimage>{

	@Id
	private String uuid;
	
	private String fileName;
	
	private int ord;
	
	@ManyToOne
	private Staff staff;
	
	@Override
	public int compareTo(Staffimage other) {
		return this.ord - other.ord;
	}
	
	public void changeStaff(Staff staff) {
		this.staff = staff;
	}
}
