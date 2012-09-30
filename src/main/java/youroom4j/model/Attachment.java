package youroom4j.model;

import java.io.Serializable;

/**
 * Representing one single attachment status.
 *
 * @author Shintaro Katafuchi
 */
public class Attachment implements Serializable {

	private static final long serialVersionUID = 1L;

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

	@Override
	public String toString() {
		return "Attachment{" +
						"original_file_name=" + originalFilename +
						", data=" + data +
						", content_type=" + contentType +
						", attachment_type=" + attachmentType +
						", file_name=" + fileName + "}";
	}

}