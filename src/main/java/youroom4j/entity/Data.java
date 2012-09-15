package youroom4j.entity;

/**
 * Representing one single data status.
 *
 * @author Shintaro Katafuchi
 */
public class Data {

	private String text;

	public Data() {

	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Override for debug.
	 * @return all fields.
	 */
	@Override
	public String toString() {
		return new StringBuilder()
		.append("text:").append(text).append("\n")
		.toString();
	}
}