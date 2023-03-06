package kr.co.javacafe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

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
@ToString
public class Recipe extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	
	@Column(length = 3, nullable = false)
	@ColumnDefault("0")
	private String rcate;
	
	@Column(length = 200, nullable = false)
	private String rname;

	@Column(length = 200, nullable = false)
	private String reng;
	
	@Column(length = 2000, nullable = false)
	private String rdesc;
	
	@Column(length = 2000, nullable = false)
	private String rtext;

	@Column(length = 10, nullable = false)
	@ColumnDefault("0")
	private int rcost;

	@Column(length = 10, nullable = false)
	@ColumnDefault("0")
	private int rprice;

	@Column(length = 10, nullable = false)
	@ColumnDefault("0")
	private int rkcal;

	@Column(length = 200, nullable = true)
	private String rimg;	

	@Column(length = 3, nullable = false)
	private int rstate;
	
	public void change(String rcate, String rname, String reng, String rdesc, String rtext, int rcost, int rprice, int rkcal, int rstate, String rimg) {
		this.rcate = rcate;
		this.rname = rname;
		this.reng = reng;
		this.rdesc = rdesc;
		this.rtext = rtext;
		this.rcost = rcost;
		this.rprice = rprice;
		this.rkcal = rkcal;
		this.rstate = rstate;
		this.rimg = rimg;
	}
}
