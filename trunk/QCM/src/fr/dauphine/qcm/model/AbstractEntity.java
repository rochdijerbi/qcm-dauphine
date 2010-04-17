package fr.dauphine.qcm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = -8796045861878061257L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Date version;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setVersion(Date version) {
		this.version = version;
	}

	public Date getVersion() {
		return version;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && (obj instanceof AbstractEntity)
				&& equals((AbstractEntity) obj);
	}

	private boolean equals(AbstractEntity entity) {
		return getId() != null && entity.getId() != null
				&& equals(getId().equals(entity.getId()));
	}

	@Override
	public int hashCode() {
		if (getId() == null) {
			return 0;
		}

		return getId().hashCode();
	}
}
