package fr.dauphine.qcm.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class AbstractEntity<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = -8796045861878061257L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private T id;

	@Version
	private Long version;

	public void setId(T id) {
		this.id = id;
	}

	public T getId() {
		return id;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getVersion() {
		return version;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && (obj instanceof AbstractEntity<?>)
				&& equals((AbstractEntity<?>) obj);
	}

	private boolean equals(AbstractEntity<?> entity) {
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
