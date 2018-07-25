package org.wecancodeit.reviewsitefullstack;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

	
	@Resource
	private CommentRepository commentRepo;
	
	@Resource 
	private ReviewRepository reviewRepo;
	
	
	@RequestMapping("add-comment")
	public String addComment(String author, Long reviewId, String content) {
		Optional<Review> reviewResult = reviewRepo.findById(reviewId);
		Review review = reviewResult.get();
		
		Comment newComment = new Comment(author, review, content);
		commentRepo.save(newComment);
		
		return "redirect:/review?id=" + reviewId;
	}
	
	
	//Showing Tags with Java and Thymeleaf
		@RequestMapping("/all-comments-ajax")
		public String showAllComments(Model model) {
			model.addAttribute("commentsModel", commentRepo.findAll());
			return "commentsAjax";
		}
	
	//Use Ajax to remove comments from Database
		@RequestMapping("/remove-comment")
		public String RemoveComment(@PathVariable String author, Long reviewId) {
			
			Comment commentToDelete = commentRepo.findByAuthor(author);		
			commentRepo.delete(commentToDelete);		
			
			
			return "redirect:/review?id=" + reviewId;
			
		}
		
}

