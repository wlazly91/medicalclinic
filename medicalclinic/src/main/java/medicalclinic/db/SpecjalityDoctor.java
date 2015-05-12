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
 * klasa reprezentuj¹ca tabelê specjality docotr
 * (specjalnoœæ lekarza)
 * @author £ukasz Kochanek
 * @version 1.0
 */
@Entity
@Table(name = "SPECJALITY_DOCTOR")
public class SpecjalityDoctor implements ObjectDB {

	private static final long serialVersionUID = 7176054541156531542L;
	
	@Id
	@Column(name = "ID_SPECJALITY_DOCTOR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDSPECJALITYDOCTOR") 
	@SequenceGenerator(name="IDSPECJALITYDOCTOR", sequenceName = "IDSPECJALITYDOCTOR", allocationSize=1)
	private int idSpecDoc;
	
	@ManyToOne
	@JoinColumn(name = "ID_DOCTOR")
	private Doctor idDoc;
	
	@ManyToOne
	@JoinColumn(name = "ID_SPECJALITY")
	private Specjality idSpecjaliy;
	
	
	public SpecjalityDoctor() {}
	
	/**
	 * @param idDoc the idDoc to set
	 */
	public void setIdDoc(Doctor idDoc) {
		this.idDoc = idDoc;
	}
	
	/**
	 * @param idSpecDoc the idSpecDoc to set
	 */
	public void setIdSpecDoc(int idSpecDoc) {
		this.idSpecDoc = idSpecDoc;
	}
	
	/**
	 * @param idSpecjaliy the idSpecjaliy to set
	 */
	public void setIdSpecjaliy(Specjality idSpecjaliy) {
		this.idSpecjaliy = idSpecjaliy;
	}
	
	/**
	 * @return the idDoc
	 */
	public Doctor getIdDoc() {
		return idDoc;
	}
	
	/**
	 * @return the idSpecDoc
	 */
	public int getIdSpecDoc() {
		return idSpecDoc;
	}
	
	/**
	 * @return the idSpecjaliy
	 */
	public Specjality getIdSpecjaliy() {
		return idSpecjaliy;
	}
	
}
