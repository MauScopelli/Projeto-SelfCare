package br.com.fiap.selfcare.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TB_USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ")
	@SequenceGenerator(sequenceName="user_sequence", allocationSize=1, name="USER_SEQ")
	private Long id;
	
	private String name;
	
	private Integer weight;
	
	private Integer height;
	
	private Integer age;
	
	@Enumerated(EnumType.STRING)
	private Activity activity;
	
	private boolean comorbidity;
	
	private boolean medication;
	
}
