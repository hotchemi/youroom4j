package youroom4j.model;

/**
 * Representing one single attachment status.
 *
 * @author Shintaro Katafuchi
 */
public class Attachment {

	private String originalFilename;

	private Data data;

	private String contentType;

	private String attachmentType;

	private String fileName;

	public Attachment() {

	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * For debug.
	 *
	 * @return all fields.
	 */
	@Override
	public String toString() {
		return new StringBuilder()
		.append("original_file_name:").append(originalFilename).append("\n")
		.append("Data:").append(data).append("\n")
		.append("content_type:").append(contentType).append("\n")
		.append("attachment_type:").append(attachmentType).append("\n")
		.append("file_name:").append(fileName).append("\n")
		.toString();
	}

}