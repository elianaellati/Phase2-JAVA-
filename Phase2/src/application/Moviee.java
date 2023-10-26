package application;
public class Moviee extends Media  {
String rating;

Moviee(String title, int copiesAvailable,String code,String rating) throws IllegalAccessException{
	super(title,copiesAvailable,code);
	setRating(rating);
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


public String getRating() {
	return rating;
}

public void setRating(String rating) throws IllegalAccessException  {
	if(rating.compareToIgnoreCase("hr")==0 || rating.compareToIgnoreCase("ac")==0 || rating.compareToIgnoreCase("dr") ==0 ) {
	this.rating = rating;
	} // if the user enter a rating not equal hr or dr or ac an exception will be throw and the following sentence wil be print
	else
		throw new IllegalAccessException ("invalid rating");
}
/*@Override
public String toString() {
	return "Movie  " +title + "Number of CopiesAvailable  "  +copiesAvailable+ "Movie Code  " +  code  + "Rating   "  +  rating  + "";
}*/
@Override
public String toString() {
	
	return "Movie Title:" + title +  "\n"
		  +"Moviee Rating:" + rating + "\n"
		  +"Number of copiesAvailable=" + copiesAvailable + "\n"
		  +"Movie code:" + code+ "\n";
}
public String toString1() {

	return "" + title +" " + copiesAvailable + " "+ code+" "+ rating+"";
}



}

