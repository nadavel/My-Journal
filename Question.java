package application;

public class Question {
	
	private int id;
	private String primary;
	private String related;
	private String followup;
	
	public Question(int id, String primary, String related, String followup) {
		this.id = id;
		this.primary = primary;
		this.related = related;
		this.followup = followup;
	}

	public String getPrimary() {
		return primary;
	}

	public String getRelated() {
		return related;
	}

	public String getFollowup() {
		return followup;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", primary=" + primary + ", related=" + related + ", followup=" + followup + "]";
	}
	
	

}
