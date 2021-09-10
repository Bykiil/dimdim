package br.com.youngdevs.dimdim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name="USER_DIMDIM")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message= "O campo nome é obrigatório")
	private String nome;
	
	@NotBlank(message= "O campo email é obrigatório")
	private String email;
	
	@NotBlank(message= "O campo senha é obrigatório")
	private String senha;
}
