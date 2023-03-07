package kr.co.javacafe.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "imageSet")
public class Staff extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sno; //스탭 번호
	
	@Column(length = 5000, nullable = false)
	private String sname; //스탭이름
	
	@Column(length = 1500, nullable = false)
	private int sphone; // 전화번호
	
	@Column(length = 1000, nullable = false)
	private String saddr; // 주소
	
	@Column(length = 500, nullable = false)	
	private Boolean swork; // 출근여부
	
	public void change(String sname, int sphone, String saddr, Boolean swork) {
		this.sname = sname;
		this.sphone = sphone;
		this.saddr = saddr;
		this.swork = swork;
	}
	
	@OneToMany(mappedBy = "staff", cascade = {CascadeType.ALL},
	fetch = FetchType.LAZY)
	@Builder.Default
	private Set<Staffimage> imageSet = new HashSet<>();
	
	public void addImage(String uuid, String fileName) {
		Staffimage staffimage = Staffimage.builder()
				.uuid(uuid)
				.fileName(fileName)
				.staff(this)
				.ord(imageSet.size())
				.build();
		imageSet.add(staffimage);
	}
	
	public void clearImages() {
		imageSet.forEach(staffimage -> staffimage.changeStaff(null));
		
		this.imageSet.clear();
	}
}
