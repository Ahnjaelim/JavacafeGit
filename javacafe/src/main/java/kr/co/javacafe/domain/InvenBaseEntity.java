package kr.co.javacafe.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@EntityListeners(value= {AuditingEntityListener.class})
@Getter
abstract class InvenBaseEntity {

	@CreatedDate
	@Column(name = "iregdate", updatable = false)
	private LocalDateTime iregDate;
	
	@LastModifiedDate
	@Column(name = "imoddate")
	private Timestamp imodDate;
}
