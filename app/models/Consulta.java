package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class Consulta extends Model {

	public String doutor;
	public String especialidade;
	public String serviço;
	
	@ManyToOne
	public Paciente paciente;
}