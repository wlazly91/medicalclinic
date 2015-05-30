package medicalclinic.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PERMISSIONS_USER")
public class PermissionsUser implements ObjectDB {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_PER_USR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDUSERPER") 
	@SequenceGenerator(name="IDUSERPER", sequenceName = "IDUSERPER", allocationSize=1)
	private Integer idPerUser;
	
	@Column(name = "ID_USER")
	private Integer idUser;
	
	@Column(name = "ID_PER")
	private Integer idPer;
	
	public void setIdPer(Integer idPer) {
		this.idPer = idPer;
	}
	
	public void setIdPerUser(Integer idPerUser) {
		this.idPerUser = idPerUser;
	}
	
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	public Integer getIdPer() {
		return idPer;
	}
	
	public Integer getIdPerUser() {
		return idPerUser;
	}
	
	public Integer getIdUser() {
		return idUser;
	}
	
}

