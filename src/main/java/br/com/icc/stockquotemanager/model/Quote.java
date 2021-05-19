package br.com.icc.stockquotemanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Quote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String stockId;
	private LocalDate date;
	private BigDecimal value;

	public Quote(LocalDate date, BigDecimal value, String stockId) {
		this.date = date;
		this.value = value;
		this.stockId = stockId;

	}

}
