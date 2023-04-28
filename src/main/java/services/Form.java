package services;


public  class Form {

    protected String content;
    protected String fieldName;

    public Form(String fieldName, String content){
        this.fieldName = fieldName;
        this.content = content;
    }
    public Form(){};
    
    public String searchForField(String fieldName){
        if(this.fieldName.equals(fieldName)){
            return this.content;
        }
        return null;
    }
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
    

}
