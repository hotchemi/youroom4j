package youroom4j.model;

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

	@Override
	public String toString() {
		return "Data{" +
						"text=" + text + "}";
	}

}