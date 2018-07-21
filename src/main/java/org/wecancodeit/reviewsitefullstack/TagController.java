package org.wecancodeit.reviewsitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TagController {

	@Resource
	TagRepository tagRepo;
	
	@Resource
	ReviewRepository reviewRepo;
	
	@RequestMapping("/add-tag")
	public String addTag(@RequestParam(value="reviewId") Long reviewId, String tagName) {
		Tag newTag = tagRepo.findByDescriptionIgnoreCase(tagName);
		if(newTag == null) {
			newTag = new Tag(tagName);
			tagRepo.save(newTag);
		}
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		Tag existing = tagRepo.findByDescriptionIgnoreCase(tagName);
		if(!review.getTags().contains(existing)) {
			review.addTag(newTag);
			reviewRepo.save(review);		
		}
		return "redirect:/review?id=" + reviewId;
}

	@RequestMapping("delete-tag-button")
	public String deleteTagButton(@RequestParam Long tagId, @RequestParam Long reviewId) {
		Optional<Tag> tagToDeleteResult = tagRepo.findById(tagId);
		Tag tagToDelete = tagToDeleteResult.get();
		
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		
		review.deleteTag(tagToDelete);
		reviewRepo.save(review);
		
		return "redirect:/review?id=" + reviewId;
		
}	
}