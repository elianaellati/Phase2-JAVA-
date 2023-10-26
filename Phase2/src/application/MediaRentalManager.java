package application;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MediaRentalManager implements MediaRentInt {
	ArrayList<Customer> List1 = new ArrayList<>();
	ArrayList<Media> List2 = new ArrayList<>();
	int i = 0;

	public void addCustomer(String name, String address, String ID, String Mobile, String plan) {
		try {
			List1.add(new Customer(name, address, ID, Mobile, plan)); // adding name and address and plan to the
																		// arraylist

		} catch (IllegalAccessException ex) {
			System.out.println(ex.getMessage());

		}

	}

	public void addMovie(String title, int copiesAvailable, String code, String rating) {

		try {
			List2.add(new Moviee(title, copiesAvailable, code, rating));// adding title ,copiesavailbe,rating to media
																		// arraylist

		} catch (IllegalAccessException ex) {
			System.out.println(ex.getMessage());

		}

	}

	public void addGame(String title, int copiesAvailable, String code, double weight) {
		List2.add(new Game(title, copiesAvailable, code, weight));
	}

	public void addAlbum(String title, int copiesAvailable, String code, String artist, String songs) {
		List2.add(new Album(title, copiesAvailable, code, artist, songs));
	}

	public void Delete(String Id) {

		for (int k = 0; k < List1.size(); ++k) {

			if (Id.compareToIgnoreCase(List1.get(k).getID()) == 0) {
				List1.remove(k);
			}
		}

	}

	public void Delete2(String code) {

		for (int k = 0; k < List2.size(); ++k) {

			if (code.compareToIgnoreCase(List2.get(k).getCode()) == 0) {
				List2.remove(k);
			}
		}

	}

	public int[] find2(String code) {
		int n = 0;
		int f = 0;

		for (int k = 0; k < List2.size(); ++k) {

			if (List2.get(k) instanceof Moviee) {
				if (((Moviee) List2.get(k)).getCode().compareToIgnoreCase(code) == 0) {
					n = k;
					f = 1;
				}
			} else if (List2.get(k) instanceof Album) {
				if (((Album) List2.get(k)).getCode().compareToIgnoreCase(code) == 0) {
					n = k;
					f = 2;
				}
			} else if (List2.get(k) instanceof Game) {
				if (((Game) List2.get(k)).getCode().compareToIgnoreCase(code) == 0) {
					n = k;
					f = 3;
				}

			}

		}
		int[] j = { n, f };
		return j;
	}

	public int find(String id) {
		int n = 0;
		for (int k = 0; k < List1.size(); ++k) {

			if (id.compareTo(List1.get(k).getID()) == 0) {
				n = k;

			}
		}

		return n;
	}

	public int check(String Id) {
		int f = 0;
		for (int k = 0; k < List1.size(); ++k) {

			if (Id.compareTo(List1.get(k).getID()) == 0) {
				f = 1;

			}
		}

		return f;
	}

	public String getAllCustomersInfo() {
		Collections.sort(List1); // it will go to the customer arraylist and find compareto method so it will
									// order the arraylist according to names in ascending order
		String w = "";
		for (int i = 0; i < List1.size(); ++i) {
			w = w + List1.get(i).toString1() + "\n";
		}
		return w;

	}

	public String getAllMediaInfo() {
		Collections.sort(List2); // it will order the media arraylist according to mediatitle in ascending order
		String w = "";
		for (int i = 0; i < List2.size(); ++i) {
			if (List2.get(i) instanceof Moviee) {
				w = w + ((Moviee) List2.get(i)).toString1() + "\n";
			} else if (List2.get(i) instanceof Album) {
				w = w + ((Album) List2.get(i)).toString1() + "\n";
			}

			else {
				w = w + ((Game) List2.get(i)).toString1() + "\n";
			}

		}
		return w;

	}

	public boolean addToCart2(String id, String code) {
		for (int j = 0; j < List1.size(); ++j) {

			if (id.compareTo(List1.get(j).getID()) == 0) {
				for (int i = 0; i < List2.size(); ++i) {
					if (code.compareToIgnoreCase(List2.get(i).getCode()) == 0) {

						List1.get(j).List.add(List2.get(i).getTitle());

					}
				}

			}
		}

		return false;
	}

	public boolean removeFromCart(String customerName, String mediaTitle) {
		for (int j = 0; j < List1.size(); ++j) {

			if (customerName.compareTo(List1.get(j).name) == 0) {
				if (List1.get(j).List.contains(mediaTitle) == true) {// the user will remove the media title he added by
																		// adding the customer name then the mediatitle
					// first it will check if the user name is in a customer list ,if it is true it
					// will check if media title he added is in list array in order to remove it
					List1.get(j).List.remove(mediaTitle);
					System.out.print("it is successfully removed from the cart");
					return true;

				}
			}
		}
		return false;

	}

	/*
	 * first it will go to customer array in order to get the list(medias each
	 * customer chose) then we will get the first media the customer chose in order
	 * to check if the media is in media arraylist and we will check each plan for
	 * each customer if the plan is limited it will reserved only two places in
	 * rented array ,if it is unlimited it will take all medias in list array after
	 * they are checked and added to rented array if the user reserved a media the
	 * number of copies will be decreased
	 */

	public String processRequests1() {
		String res = "";
		Collections.sort(List1);
		int p;
		int a;
		int j;
		for (int i = 0; i < List1.size(); ++i) {
			p = 0;
			for (int m = 0; m < List1.get(i).List.size(); ++m) {
				for (j = 0; j < List2.size(); ++j) {
					if ((List1.get(i).List.get(m).compareTo(List2.get(j).getTitle()) == 0)
							&& (List2.get(j).getCopiesAvailable() > 0)
							&& List1.get(i).getPlan().compareToIgnoreCase("limit") == 0 && p < 2
							&& List1.get(i).Rented.size() != 2) {
						if (List1.get(i).Rented.contains(List1.get(i).List.get(m)) == false) {
							List1.get(i).Rented.add(List1.get(i).List.get(m));
						}
						res = res + "Sending" + " " + List1.get(i).Rented.get(p) + " " + "To" + " "
								+ List1.get(i).getName() + "\n";
						a = List2.get(j).getCopiesAvailable();
						List2.get(j).setCopiesAvailable(--a);
						++p;
					} else if ((List1.get(i).List.get(m).compareTo(List2.get(j).getTitle()) == 0)
							&& (List2.get(j).getCopiesAvailable() != 0)
							&& (List1.get(i).getPlan().compareToIgnoreCase("unlimit") == 0)) {
						if (List1.get(i).Rented.contains(List1.get(i).List.get(m)) == false) {
							List1.get(i).Rented.add(List1.get(i).List.get(m));
						}

						res = res + "Sending" + " " + List1.get(i).Rented.get(p) + " " + "To" + " "
								+ List1.get(i).getName() + "\n";
						a = List2.get(j).getCopiesAvailable();
						List2.get(j).setCopiesAvailable(--a);

					}
				}

			}

		}

		return res;
	}

	public boolean returnMedia(String id, String mediaTitle) {
		int c;

		for (int i = 0; i < List1.size(); ++i) {

			if (id.compareTo(List1.get(i).getID()) == 0) {

				for (int m = 0; m < List1.get(i).Rented.size(); ++m) {
					if ((List1.get(i).Rented.get(m).compareTo(mediaTitle)) == 0) {

						for (int j = 0; j < List2.size(); ++j) {
							if (List1.get(i).Rented.get(m).compareTo(List2.get(j).getTitle()) == 0) {
								c = List2.get(j).getCopiesAvailable();
								System.out.print(c);
								List2.get(j).setCopiesAvailable(++c);
								List1.get(i).Rented.remove(mediaTitle);

							}

						}
						System.out.print("it is successfully returned to media ");
						System.out.print(List2.get(0).getCopiesAvailable());
						return true;

					}
				}
			}
		}
		return false;
	}

	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
		ArrayList<String> media = new ArrayList<>();
		int f = 0;
		for (int j = 0; j < List2.size(); ++j) {

			if (List2.get(j).getTitle().compareToIgnoreCase(title) == 0) {
				media.add(title);
				f = 1;
			}
			if (List2.get(j) instanceof Moviee) {
				if (((Moviee) List2.get(j)).getRating().compareToIgnoreCase(rating) == 0) {
					media.add(List2.get(j).title);
					f = 1;
				}
			}
			if (List2.get(j) instanceof Album) {
				if (((Album) List2.get(j)).getArtist().compareToIgnoreCase(artist) == 0) {
					media.add(List2.get(j).getTitle());
				}
				f = 1;
			}
			if (List2.get(j) instanceof Album) {
				String[] n = ((Album) List2.get(j)).getSongs().split(",");
				String[] b = songs.split(",");
				for (int i = 0; i < b.length; ++i) {
					for (int k = 0; k < n.length; ++k) {
						if (b[i].compareToIgnoreCase(n[k]) == 0) {
							media.add(List2.get(j).getTitle());
						}
					}
				}
				f = 1;
			}
		}
		if (f == 1) {
			for (int p = 0; p < media.size(); ++p) {
				for (int x = p + 1; x < media.size(); ++x) {
					if (media.get(p).compareTo(media.get(x)) == 0) {
						media.remove(p);
					}
				}
			}
		} else if (f == 0) {
			for (int z = 0; z < List2.size(); ++z) {
				media.add(List2.get(z).getTitle());
			}
		}
		Collections.sort(media);
		return media;
	}

	public String print() {
		String d = "";
		String w = "";
		for (int y = 0; y < List1.size(); ++y) {
			d = "";
			for (int p = 0; p < List1.get(y).Rented.size(); ++p) {

				d = d + List1.get(y).Rented.get(p) + ",";
			}
			System.out.print(List1.get(y).Rented);
			w = w + List1.get(y).toString1() + " " + d + "\n";
		}
		return w;
	}

}

/*search media the user will enter some description for example the user will enter any title any artist any song any rating
 * then we will check if the title he entered is in media title if it is true it will be added to media array  if the user enter any
 * of the previous parameter and the flag stayed zero which mean the conditions didnt achieve then we will print all the media title 
 * the list2 contain(movies,albums,games) , if the user add media title and rating for the same class for example then the mediatitle 
 * will be added twice to solve this problem we will check if the media contain the same mediatitle twice if it is true we will remove one of these mediatitle,then we will
 * order the media title in ascending ording using collections.sort */



