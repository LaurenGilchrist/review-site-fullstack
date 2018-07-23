package org.wecancodeit.reviewsitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TagController {

	@Resource
	TagRepository tagRepo;

	@Resource
	ReviewRepository reviewRepo;

	// Can add tag with HTML forms
	@RequestMapping("/add-tag")
	public String addTag(@RequestParam(value = "reviewId") Long reviewId, String tagName) {
		Tag newTag = tagRepo.findByDescriptionIgnoreCase(tagName);
		if (newTag == null) {
			newTag = new Tag(tagName);
			tagRepo.save(newTag);
		}
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		Tag existing = tagRepo.findByDescriptionIgnoreCase(tagName);
		if (!review.getTags().contains(existing)) {
			review.addTag(newTag);
			reviewRepo.save(review);
		}
		return "redirect:/review?id=" + reviewId;
	}

	// can Remove Tag with HTML forms
	@RequestMapping("/remove-tag-button")
	public String removeTagButton(@RequestParam Long tagId, @RequestParam Long reviewId) {
		Optional<Tag> tagToRemoveResult = tagRepo.findById(tagId);
		Tag tagToRemove = tagToRemoveResult.get();

		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();

		review.removeTag(tagToRemove);
		reviewRepo.save(review);

		return "redirect:/review?id=" + reviewId;

	}
	
	//Showing Tags with Java and Thymeleaf
	@RequestMapping("/all-tags-ajax")
	public String showAllTags(Model model) {
		model.addAttribute("tagsModel", tagRepo.findAll());
		return "tagsAjax";
	}
	
	
	
	
	
	

}