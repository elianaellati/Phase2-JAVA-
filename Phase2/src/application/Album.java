package application;
public class Album extends Media  {
String artist;
String songs;
Album (String title, int copiesAvailable,String code,String artist,String songs){
	super(title,copiesAvailable,code);
	this.artist=artist;  // constructors with parameters
	this.songs=songs;
}
public String getArtist() {
	return artist;
}
public void setArtist(String artist) {
	this.artist = artist;
}
public String getSongs() {
	return songs;
}
public void setSongs(String songs) {
	this.songs = songs;
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
	return"Album Title:" + title + "\n"
		  +"Number of CopiesAvailable:" + copiesAvailable + "\n"
		  +"code of an Album:" + code + "\n"
		  +"Artist Name:" + artist + "\n"
		  +"Songs:" + songs + "\n";
}

public String toString1() {
	return""+title+" "+copiesAvailable+" "+code+" "+artist+" "+songs+"";
}



}
