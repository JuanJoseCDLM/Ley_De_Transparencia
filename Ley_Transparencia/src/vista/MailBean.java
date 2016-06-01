package vista;

import java.io.Serializable;

public class MailBean implements Serializable {
    // alot of properties
    private Attachment attachment;
    // setters and getters

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
}