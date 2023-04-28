package services;


public class FormDeco extends Form {

	protected Form form;


	public String searchForField(String fieldName){
		if(this.fieldName.equals(fieldName)){
			return this.content;
		}
		else{
			return form.searchForField(fieldName);
		}
	}
	
	public FormDeco(String fieldName, String content, Form form ) {
		this.form = new Form();
		this.form =  form;
		this.fieldName = fieldName;
		this.content = content;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
	
}