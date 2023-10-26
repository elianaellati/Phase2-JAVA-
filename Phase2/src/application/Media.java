package application;

abstract class Media implements Comparable <Media> {
protected String title;
protected int copiesAvailable;
protected String code;
Media(String title , int copiesAvailable,String code){
	this.title=title;
	this.copiesAvailable=copiesAvailable;
	this.code=code;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}
public int getCopiesAvailable() {
	return copiesAvailable;
}


public void setCopiesAvailable(int copiesAvailable) {
	this.copiesAvailable = copiesAvailable;
}



public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public int compareTo(Media e){
return this.getTitle().compareTo(e.getTitle());
}
}
