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
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDUserPer") 
	@SequenceGenerator(name="IDUserPer", sequenceName = "IDUserPer", allocationSize=1)
	private int idPerUser;
	
	@Column(name = "ID_USER")
	private int idUser;
	
	@Column(name = "ID_PER")
	private int idPer;
	
	public void setIdPer(int idPer) {
		this.idPer = idPer;
	}
	
	public void setIdPerUser(int idPerUser) {
		this.idPerUser = idPerUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public int getIdPer() {
		return idPer;
	}
	
	public int getIdPerUser() {
		return idPerUser;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
}

