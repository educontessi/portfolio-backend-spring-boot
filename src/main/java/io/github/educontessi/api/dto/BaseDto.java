package io.github.educontessi.api.dto;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@MappedSuperclass
public abstract class BaseDto {

	protected Long id;
	protected String apiVersion;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime created;
	
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime changed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

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

}
