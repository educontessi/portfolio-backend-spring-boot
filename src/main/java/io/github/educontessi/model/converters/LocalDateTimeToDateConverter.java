package io.github.educontessi.model.converters;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converte o atributo da entidade {@link LocalDateTime} em registro de data e
 * hora da representação da coluna do banco de dados e de volta novamente.
 * 
 * @author Eduardo Possamai Contessi
 *
 */
@Converter(autoApply = true)
public class LocalDateTimeToDateConverter implements AttributeConverter<LocalDateTime, java.sql.Timestamp> {

	@Override
	public java.sql.Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
		if (attribute == null) {
			return null;
		}

		return Timestamp.valueOf(attribute);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(java.sql.Timestamp dbValue) {
		if (dbValue == null) {
			return null;
		}

		return dbValue.toLocalDateTime();
	}

}
