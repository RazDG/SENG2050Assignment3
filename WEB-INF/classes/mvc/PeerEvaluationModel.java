package mvc;
public class PeerEvaluationModel {
	private String comment;

	public PeerEvaluationModel() { }

	public PeerEvaluationModel(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
