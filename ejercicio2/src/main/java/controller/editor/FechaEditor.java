package controller.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FechaEditor extends PropertyEditorSupport {
	private static final String DATE_MASK = "yyyy-MM-dd";

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			this.setValue(new SimpleDateFormat(DATE_MASK).parse(text));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getAsText() {
		if (this.getValue() != null)
			return new SimpleDateFormat(DATE_MASK).format(this.getValue());
		else
			return null;
	}
}
