package br.com.icc.stockquotemanager.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.icc.stockquotemanager.model.Quote;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
class QuoteRepositoryTest {

	private QuoteRepository quoteRepository;

	@Autowired
	public QuoteRepositoryTest(QuoteRepository quoteRepository) {
		this.quoteRepository = quoteRepository;
	}

	String id = "petr4";

	@Test
	void shouldreturnAStockById() {
		String correctId = "petr4";
		List<Quote> quote = quoteRepository.findByStockId(correctId);
		Assert.assertEquals(quote.get(0).getStockId(), id);

	}

	@Test
	void shouldNotReturnAStockByIdBecauseIdisInvalid() {
		String fakeId = "petr107";
		List<Quote> quote = quoteRepository.findByStockId(id);
		Assert.assertNotEquals(quote.get(0).getStockId(), fakeId);

	}

}
