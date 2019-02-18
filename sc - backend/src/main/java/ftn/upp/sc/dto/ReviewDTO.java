package ftn.upp.sc.dto;

public class ReviewDTO {
	
	private String commentForAuthor;
	private String commentForEditor;
	private String recommendation;
	
	public ReviewDTO(){
		
	}

	public String getCommentForAuthor() {
		return commentForAuthor;
	}

	public void setCommentForAuthor(String commentForAuthor) {
		this.commentForAuthor = commentForAuthor;
	}

	public String getCommentForEditor() {
		return commentForEditor;
	}

	public void setCommentForEditor(String commentForEditor) {
		this.commentForEditor = commentForEditor;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	
	

}
