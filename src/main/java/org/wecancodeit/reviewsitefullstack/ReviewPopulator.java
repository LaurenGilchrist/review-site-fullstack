package org.wecancodeit.reviewsitefullstack;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner {

	@Resource
	private CategoryRepository categoryRepo;
	
	@Resource
	private ReviewRepository reviewRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Override
	public void run(String...args) throws Exception {
		Category coffee = new Category("Coffee");
		coffee = categoryRepo.save(coffee);
		Category tea = new Category("Tea");
		tea = categoryRepo.save(tea);
		Category beer = new Category("Beer");
		beer = categoryRepo.save(beer);
		Category wine = new Category("Wine");
		wine = categoryRepo.save(wine);
		
		Tag hot = new Tag("Hot");
		hot = tagRepo.save(hot);
		Tag cold = new Tag("Cold");
		cold = tagRepo.save(cold);
		Tag nonAlcoholic = new Tag("nonAlcoholic");
		nonAlcoholic = tagRepo.save(nonAlcoholic);
		Tag alcoholic = new Tag("Alcoholic");
		alcoholic = tagRepo.save(alcoholic);
		
		reviewRepo.save(new Review("Dunkin Doughnuts", "So good 24-7", "/images/dunkin-donuts.jpg", coffee, hot, cold, nonAlcoholic));
		reviewRepo.save(new Review("Starbucks", "A little pricey", "/images/starbucks.jpg", coffee, hot, cold, nonAlcoholic));
		reviewRepo.save(new Review("Bud Light", "Much needed", "/images/BudLight.jpg", beer, cold, alcoholic));
		reviewRepo.save(new Review("Nestea", "Refreshing", "/images/nestea.jpg", tea, cold, nonAlcoholic));
		
}
}