package org.wecancodeit.reviewsitefullstack;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)

public class ReviewControllerMockMVCTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private ReviewRepository reviewRepo;
	
	@MockBean
	private TagRepository tagRepo;
	
	@Mock
	private Review review1;
	
	@Mock
	private Review review2;
	
	@Mock
	private Category category1;
	
	@Mock
	private Category category2;
	
	@Mock
	private Tag tag1;
	
	@Mock
	private Tag tag2;
	
	@Test
	public void shouldRouteToSingleReviewView()throws Exception {
		long reviewId = 1;
		when(reviewRepo.findById(reviewId)).thenReturn(Optional.of(review1));
		mvc.perform(get("/review?id=1")).andExpect(view().name(is("reviewTemplate")));
		
	}
	@Test
	public void shouldRouteToAllReviewView() throws Exception {
		
	}
	
	@Test
	public void shouldBeOkForSingleReview() throws Exception {
		
	}
	
	@Test
	public void shouldBeOkForAllReviews() throws Exception {
}
}
