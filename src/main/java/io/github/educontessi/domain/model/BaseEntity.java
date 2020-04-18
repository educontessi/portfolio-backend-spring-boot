package io.github.educontessi.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

import io.github.educontessi.domain.converters.LocalDateTimeToDateConverter;

@MappedSuperclass
public abstract class BaseEntity {

	@Column(name = "create_date", insertable = false, updatable = false)
	@Convert(converter = LocalDateTimeToDateConverter.class)
	protected LocalDateTime created;

	@Column(name = "changed", insertable = false, updatable = false)
	@Convert(converter = LocalDateTimeToDateConverter.class)
	protected LocalDateTime changed;

	@Column(name = "deleted", columnDefinition = "tinyint(1) default 1", insertable = false, updatable = true)
	protected boolean deleted;

	@Column(name = "delete_date", insertable = false, updatable = true)
	@Convert(converter = LocalDateTimeToDateConverter.class)
	protected LocalDateTime deletedDate;

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getChanged() {
		return changed;
	}

	public void setChanged(LocalDateTime changed) {
		this.changed = changed;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
		this.setDeletedDate(this.deleted ? LocalDateTime.now() : null);
	}

	public LocalDateTime getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(LocalDateTime deletedDate) {
		this.deletedDate = deletedDate;
	}

}
