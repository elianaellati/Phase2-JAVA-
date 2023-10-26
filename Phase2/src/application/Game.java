package application;
public class Game extends Media  {
double weight;

Game (String title, int copiesAvailable,String code,double weight){
	super(title,copiesAvailable,code);
	this.weight=weight;      //constructor with parameters
}
public double getWeight() {
	return weight;
}
public void setWeight(double weight) {
	this.weight = weight;
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


@Override
public String toString() {
	return "Game Title:"+ title + "\n"
		  +"Number of copiesAvailable:" + copiesAvailable + "\n"
		  +"Game code:" + code+ "\n"
		  +"Game weight:" + weight+"";
}
public String toString1() {
	return "" + title + " " + copiesAvailable + " " + code+" "+ weight +"";
}




}
