/**
 * 
 */
package medicalclinic.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * klasa reprezentuj¹ca tabele postoffice
 * (poczta)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "POST_OFFICE")
public class PostOffice implements ObjectDB {

	private static final long serialVersionUID = -6669312476488384074L;

	@Id
	@Column(name = "ID_POSTOFFICE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDPOSTOFFICE") 
	@SequenceGenerator(name="IDPOSTOFFICE", sequenceName = "IDPOSTOFFICE", allocationSize=1)
	private int idPostOf;
	
	@ManyToOne
    @JoinColumn(name = "ID_LOCALITY")
	private Locality idLocality;
	
	@Column(name = "CODE")
	private String code;
	
	
	/**
	 * 
	 */
	public PostOffice() {}
	
	
	public PostOffice(String code)
	{
		this.code = code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @param idLocality the idLocality to set
	 */
	public void setIdLocality(Locality idLocality) {
		this.idLocality = idLocality;
	}
	
	/**
	 * @param idPostOf the idPostOf to set
	 */
	public void setIdPostOf(int idPostOf) {
		this.idPostOf = idPostOf;
	}
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return the idLocality
	 */
	public Locality getIdLocality() {
		return idLocality;
	}
	
	/**
	 * @return the idPostOf
	 */
	public int getIdPostOf() {
		return idPostOf;
	}
	
	
}
