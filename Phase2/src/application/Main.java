package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JComboBox;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	MediaRentalManager m = new MediaRentalManager();

	@Override
	public void start(Stage primaryStage) {
		try {

			BorderPane root = new BorderPane();
			File output = new File("outpu.txt");
			File output1 = new File("Movie.txt");// create a file called moviee.txt in order to print the informations

			if (!output.exists()) { // if the file does not exist it will print the following
				System.out.print("file does not exist");
				System.exit(2);
			}
			if (!output1.exists()) { // if the file does not exist it will print the following
				System.out.print("file does not exist");
				System.exit(2);
			}
			Scanner sc = new Scanner(output);// to read what is written on the files
			Scanner sc1 = new Scanner(output1);

			if ("outpu.txt".length() != 0) {

				String fline = "";
				String[] token;
				String[] re;
				int ll = 0;
				int f = 0;
				while (sc.hasNextLine()) {
					ll = 0;
					fline = sc.nextLine();
					token = fline.split(" ");
					String namee = token[0];
					String address = token[1];
					String id = token[2];
					String mobile = token[3];
					String plan = token[4];
					m.addCustomer(namee, address, id, mobile, plan);
					String rented = token[5];

					re = rented.split(",");
					while (ll < re.length) {
						m.List1.get(f).Rented.add(re[ll]);
						++ll;
					}
					++f;

				}
			}
			PrintWriter out = new PrintWriter(new FileWriter(output, true));
			double weight = 0;
			String line;
			String[] tokens;
			String artist;
			if ("Movie.txt".length() != 0) {
				while (sc1.hasNextLine()) {
					line = sc1.nextLine();
					tokens = line.split(" ");
					String title1 = tokens[0];
					String y = tokens[1];
					int copies = Integer.parseInt(y);
					String code = tokens[2];
					String Rating = tokens[3];
					if (tokens.length == 5) {
						artist = tokens[4];
						m.addAlbum(title1, copies, code, Rating, artist);

					}

					else if (Rating.compareToIgnoreCase("HR") == 0 || Rating.compareToIgnoreCase("DR") == 0
							|| Rating.compareToIgnoreCase("DR") == 0) {
						m.addMovie(title1, copies, code, Rating);
					} else {
						weight = Double.parseDouble(Rating);
						m.addGame(title1, copies, code, weight);
					}

				}

			}

			VBox ver = new VBox();
			ImageView cc = new ImageView(
					"https://library.kissclipart.com/20180901/ibe/kissclipart-customer-icon-clipart-computer-icons-customer-clip-b852349ef9042b43.png");
			cc.setFitWidth(200);
			cc.setFitHeight(200);
			Button btn1 = new Button("", cc);
			ImageView c1 = new ImageView(
					"https://d29fhpw069ctt2.cloudfront.net/photo/6569/preview/86520cc7-f141-4768-8e5c-8d26d273e0ec_1280x1280.jpg");
			c1.setFitWidth(200);
			c1.setFitHeight(200);
			Button btn2 = new Button("", c1);
			ImageView c = new ImageView(
					"https://static.vecteezy.com/system/resources/thumbnails/000/350/264/small/Real_Estate__28404_29.jpg");
			c.setFitWidth(200);
			c.setFitHeight(200);
			Button btn3 = new Button("", c);
			btn3.setMinWidth(200);
			btn3.setMinHeight(200);
			ver.getChildren().addAll(btn1, btn2, btn3);
			ver.setPadding(new Insets(50, 50, 50, 50));
			ver.setSpacing(25);
			ver.setAlignment(Pos.CENTER);
			VBox ver1 = new VBox();
			ImageView btn5 = new ImageView("https://tixcdn.com/media/default/0001/30/thumb_29096_default_big.jpeg");
			btn5.setFitWidth(800);
			btn5.setFitHeight(500);
			Label rent = new Label("Media Rental Manger");
			rent.setFont(Font.font(50));
			rent.setFont(Font.font(null, FontWeight.EXTRA_LIGHT, rent.getFont().getSize()));

			ver1.getChildren().addAll(rent, btn5);
			ver1.setAlignment(Pos.CENTER);
			ver1.setSpacing(30);
			root.setCenter(ver1);
			root.setLeft(ver);
			root.setStyle("-fx-background-color: pink ;");
			Scene scene1 = new Scene(root, 1600, 800);
			primaryStage.setScene(scene1);
			primaryStage.show();
			primaryStage.setMaximized(true);
			btn1.setOnAction(e -> {
				BorderPane stack = new BorderPane();
				stack.setStyle("-fx-background-color: pink ;");
				Button bt = new Button("Add new Customer");
				bt.setFont(Font.font(15));
				bt.setFont(Font.font(null, FontWeight.BOLD, bt.getFont().getSize()));
				bt.setMinWidth(300);
				bt.setMinHeight(50);
				VBox v = new VBox();
				v.setAlignment(Pos.CENTER);
				Scene scene2 = new Scene(stack, 1600, 800);
				primaryStage.setScene(scene2);
				primaryStage.show();

				bt.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						BorderPane o = new BorderPane();
						o.setStyle("-fx-background-color: pink ;");
						GridPane g = new GridPane();
						Label label = new Label("Customer ID: ");
						label.setFont(Font.font(15));
						label.setFont(Font.font(null, FontWeight.BOLD, label.getFont().getSize()));
						g.add(label, 0, 0);
						TextField ID = new TextField();
						ID.setMinSize(100, 40);
						TextField Name = new TextField();
						Name.setMinSize(100, 40);
						TextField Address = new TextField();
						Address.setMinSize(100, 40);
						TextField Mobile = new TextField();
						Mobile.setMinSize(100, 40);
						TextField Plan = new TextField();
						Plan.setMinSize(100, 40);
						g.add(ID, 1, 0);
						Name.setDisable(true);
						ID.setOnKeyTyped(e -> {
							Name.setDisable(false);
						});
						Label label1 = new Label("Customer Name: ");
						label1.setFont(Font.font(15));
						label1.setFont(Font.font(null, FontWeight.BOLD, label1.getFont().getSize()));
						g.add(label1, 0, 1);
						g.add(Name, 1, 1);
						Address.setDisable(true);
						Name.setOnKeyTyped(e -> {
							Address.setDisable(false);
						});
						Label label2 = new Label("Customer Address: ");
						label2.setFont(Font.font(15));
						label2.setFont(Font.font(null, FontWeight.BOLD, label2.getFont().getSize()));
						g.add(label2, 0, 2);
						g.add(Address, 1, 2);
						Mobile.setDisable(true);
						Address.setOnKeyTyped(e -> {
							Mobile.setDisable(false);
						});
						Label label3 = new Label("Customer Mobile: ");
						label3.setFont(Font.font(15));
						label3.setFont(Font.font(null, FontWeight.BOLD, label3.getFont().getSize()));
						g.add(label3, 0, 3);
						g.add(Mobile, 1, 3);
						Plan.setDisable(true);
						Mobile.setOnKeyTyped(e -> {
							Plan.setDisable(false);
						});
						Label label4 = new Label("Customer Plan: ");
						label4.setFont(Font.font(15));
						label4.setFont(Font.font(null, FontWeight.BOLD, label4.getFont().getSize()));
						g.add(label4, 0, 4);
						g.add(Plan, 1, 4);

						Label cv = new Label("Add");
						cv.setFont(Font.font(15));
						cv.setFont(Font.font(null, FontWeight.BOLD, cv.getFont().getSize()));
						Image image1 = new Image("plus.png");
						Button btt = new Button("", new ImageView(image1));
						btt.setMinHeight(25);
						btt.setMinWidth(25);
						int i = 0;
						btt.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {

								m.addCustomer(Name.getText(), Address.getText(), ID.getText(), Mobile.getText(),
										Plan.getText());
								ID.clear();
								Name.clear();
								Address.clear();
								Mobile.clear();
								Plan.clear();

							}

						});
						Label cv1 = new Label("Back");
						cv1.setFont(Font.font(15));
						cv1.setFont(Font.font(null, FontWeight.BOLD, cv1.getFont().getSize()));
						Image image2 = new Image("back.png");
						Button btt1 = new Button("", new ImageView(image2));
						btt1.setMinHeight(25);
						btt1.setMinWidth(25);
						btt1.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene2);
								FileWriter writer;
								try {
									writer = new FileWriter(output, false);
									writer.write(m.getAllCustomersInfo());
									writer.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						});

						btt1.setMinWidth(2);
						HBox n = new HBox();
						n.getChildren().addAll(cv, btt, cv1, btt1);
						n.setSpacing(30);
						n.setAlignment(Pos.CENTER);
						n.setPadding(new Insets(25, 25, 50, 25));
						o.setBottom(n);
						g.setAlignment(Pos.CENTER);

						g.setHgap(5);
						g.setVgap(10);
						o.setCenter(g);

						Scene scene3 = new Scene(o, 1600, 800);
						primaryStage.setScene(scene3);
						primaryStage.show();

					}
				});

				Button bt2 = new Button("Delete Customer");
				bt2.setFont(Font.font(15));
				bt2.setFont(Font.font(null, FontWeight.BOLD, bt2.getFont().getSize()));
				bt2.setMinWidth(300);
				bt2.setMinHeight(50);
				bt2.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						BorderPane e = new BorderPane();
						e.setStyle("-fx-background-color: pink ;");
						GridPane g = new GridPane();
						TextField id = new TextField();
						id.setMinSize(100, 40);
						TextField name = new TextField();
						name.setMinSize(100, 40);
						TextField address = new TextField();
						address.setMinSize(100, 40);
						TextField mobile = new TextField();
						mobile.setMinSize(100, 40);
						TextField plan = new TextField();
						plan.setMinSize(100, 40);
						Label label = new Label("Customer ID: ");
						g.add(label, 0, 0);
						label.setFont(Font.font(25));
						label.setFont(Font.font(null, FontWeight.BOLD, label.getFont().getSize()));
						g.add(id, 1, 0);
						Label c = new Label("Find");
						c.setFont(Font.font(15));
						c.setFont(Font.font(null, FontWeight.BOLD, c.getFont().getSize()));
						Image image11 = new Image("find.png");
						Button b = new Button("", new ImageView(image11));
						b.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								Label label = new Label("Customer Name: ");
								label.setFont(Font.font(15));
								label.setFont(Font.font(null, FontWeight.BOLD, label.getFont().getSize()));
								g.add(label, 0, 1);
								g.add(name, 1, 1);
								Label label1 = new Label("Customer Address: ");
								label1.setFont(Font.font(15));
								label1.setFont(Font.font(null, FontWeight.BOLD, label1.getFont().getSize()));
								g.add(label1, 0, 2);
								g.add(address, 1, 2);
								Label label2 = new Label("Customer Mobile: ");
								label2.setFont(Font.font(15));
								label2.setFont(Font.font(null, FontWeight.BOLD, label2.getFont().getSize()));
								g.add(label2, 0, 3);
								g.add(mobile, 1, 3);
								Label label3 = new Label("Customer Plan: ");
								label3.setFont(Font.font(15));
								label3.setFont(Font.font(null, FontWeight.BOLD, label2.getFont().getSize()));
								g.add(label3, 0, 4);
								g.add(plan, 1, 4);
								int y = m.find(id.getText());
								int j = m.check(id.getText());
								if (j == 1) {
									name.setText(m.List1.get(y).getName());
									address.setText(m.List1.get(y).getAddress());
									mobile.setText(m.List1.get(y).getMobile());
									plan.setText(m.List1.get(y).getPlan());
								}

							}

						});
						Label c1 = new Label("Add");
						c1.setFont(Font.font(15));
						c1.setFont(Font.font(null, FontWeight.BOLD, c1.getFont().getSize()));
						Image image22 = new Image("garbage.png");
						Button b1 = new Button("", new ImageView(image22));
						b1.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								m.Delete(id.getText());
								try {
									FileWriter writer = new FileWriter(output, false);
									writer.write(m.getAllCustomersInfo());
									writer.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						});
						Label c2 = new Label("Back");
						c2.setFont(Font.font(15));
						c2.setFont(Font.font(null, FontWeight.BOLD, c2.getFont().getSize()));
						Image image33 = new Image("back.png");
						Button b2 = new Button("", new ImageView(image33));
						b2.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene2);

							}

						});
						HBox k = new HBox();
						k.getChildren().addAll(c, b, c1, b1, c2, b2);
						k.setSpacing(25);
						k.setAlignment(Pos.CENTER);
						k.setPadding(new Insets(25, 25, 50, 25));
						e.setBottom(k);
						g.setAlignment(Pos.CENTER);

						g.setHgap(5);
						g.setVgap(10);
						e.setCenter(g);
						Scene scene = new Scene(e, 1600, 800);
						primaryStage.setScene(scene);
						primaryStage.show();

					}
				});
				Button bt3 = new Button("Update Customer");

				bt3.setFont(Font.font(15));
				bt3.setFont(Font.font(null, FontWeight.BOLD, bt3.getFont().getSize()));
				bt3.setMinWidth(300);
				bt3.setMinHeight(50);
				bt3.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						BorderPane q = new BorderPane();
						q.setStyle("-fx-background-color: pink ;");
						GridPane up = new GridPane();
						Label c = new Label("Find");
						c.setFont(Font.font(15));
						c.setFont(Font.font(null, FontWeight.BOLD, c.getFont().getSize()));
						Image image11 = new Image("find.png");
						Button b = new Button("", new ImageView(image11));
						Label label = new Label("Customer ID ");
						label.setFont(Font.font(25));
						label.setFont(Font.font(null, FontWeight.BOLD, label.getFont().getSize()));
						up.add(label, 0, 0);

						TextField p = new TextField();
						p.setMinSize(100, 40);
						up.add(p, 1, 0);
						Label update = new Label("Update");

						update.setFont(Font.font(15));
						update.setFont(Font.font(null, FontWeight.BOLD, update.getFont().getSize()));
						Image image4 = new Image("update.png");
						Button upd = new Button("", new ImageView(image4));
						b.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								int l = m.check(p.getText());
								int u = m.find(p.getText());
								if (l == 1) {
									Label po = new Label("Please Select:");
									po.setFont(Font.font(25));
									po.setFont(Font.font(null, FontWeight.BOLD, po.getFont().getSize()));
									up.add(po, 0, 1);
									CheckBox check1 = new CheckBox("Customer ID: ");
									check1.setFont(Font.font(15));
									check1.setFont(Font.font(null, FontWeight.BOLD, check1.getFont().getSize()));
									up.add(check1, 0, 2);
									CheckBox check2 = new CheckBox("Customer Name: ");
									check2.setFont(Font.font(15));
									check2.setFont(Font.font(null, FontWeight.BOLD, check2.getFont().getSize()));
									up.add(check2, 0, 3);
									CheckBox check3 = new CheckBox("Customer Address: ");
									check3.setFont(Font.font(15));
									check3.setFont(Font.font(null, FontWeight.BOLD, check3.getFont().getSize()));
									up.add(check3, 0, 4);
									CheckBox check4 = new CheckBox("Customer Mobile: ");
									check4.setFont(Font.font(15));
									check4.setFont(Font.font(null, FontWeight.BOLD, check4.getFont().getSize()));
									up.add(check4, 0, 5);
									CheckBox check5 = new CheckBox("Customer Plan: ");
									check5.setFont(Font.font(15));
									check5.setFont(Font.font(null, FontWeight.BOLD, check5.getFont().getSize()));
									up.add(check5, 0, 6);

									check1.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											if (check1.isSelected()) {
												Label label = new Label("Customer ID: ");
												label.setFont(Font.font(15));
												label.setFont(
												Font.font(null, FontWeight.BOLD, label.getFont().getSize()));
												up.add(label, 0, 7);
												TextField d = new TextField();
												up.add(d, 1, 7);

												upd.setOnAction(new EventHandler<ActionEvent>() {

													@Override
													public void handle(ActionEvent arg0) {

														m.List1.get(u).setID(d.getText());

													}

												});
											}

										}

									});
									check2.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent arg0) {
											Label label = new Label("Customer Name: ");
											label.setFont(Font.font(15));
											label.setFont(Font.font(null, FontWeight.BOLD, label.getFont().getSize()));
											up.add(label, 0, 8);
											TextField name = new TextField();
											up.add(name, 1, 8);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													m.List1.get(u).setName(name.getText());
												}

											});

										}

									});
									check3.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											Label label = new Label("Customer Address: ");
											label.setFont(Font.font(15));
											label.setFont(Font.font(null, FontWeight.BOLD, label.getFont().getSize()));
											up.add(label, 0, 9);
											TextField address = new TextField();
											up.add(address, 1, 9);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													m.List1.get(u).setAddress(address.getText());
												}

											});
										}

									});
									check4.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											Label label = new Label("Customer Mobile: ");
											label.setFont(Font.font(15));
											label.setFont(Font.font(null, FontWeight.BOLD, label.getFont().getSize()));
											up.add(label, 0, 10);
											TextField mobile = new TextField();
											up.add(mobile, 1, 10);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													m.List1.get(u).setMobile(mobile.getText());
												}

											});
										}

									});
									check5.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											Label label = new Label("Customer Plan: ");
											label.setFont(Font.font(15));
											label.setFont(Font.font(null, FontWeight.BOLD, label.getFont().getSize()));

											up.add(label, 0, 11);
											TextField plan = new TextField();
											up.add(plan, 1, 11);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													try {
														m.List1.get(u).setPlan(plan.getText());
													} catch (IllegalAccessException e) {
														e.printStackTrace();
													}
												}

											});
										}

									});

								}

							}

						});

						Label c2 = new Label("Back");
						Image image33 = new Image("back.png");
						c2.setFont(Font.font(15));
						c2.setFont(Font.font(null, FontWeight.BOLD, c2.getFont().getSize()));
						Button b2 = new Button("", new ImageView(image33));
						b2.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene2);
								FileWriter writer;
								try {
									writer = new FileWriter(output, false);
									writer.write(m.getAllCustomersInfo());
									writer.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}

						});

						HBox hor = new HBox();
						hor.getChildren().addAll(c, b, update, upd, c2, b2);
						hor.setSpacing(25);
						hor.setPadding(new Insets(25, 25, 50, 25));
						hor.setAlignment(Pos.CENTER);
						q.setBottom(hor);

						up.setAlignment(Pos.CENTER);
						up.setHgap(5);
						up.setVgap(10);
						q.setCenter(up);

						Scene scene = new Scene(q, 1600, 800);
						primaryStage.setScene(scene);
						primaryStage.show();

					}
				});
				Button bt4 = new Button("Search a customer by ID");
				bt4.setFont(Font.font(15));
				bt4.setFont(Font.font(null, FontWeight.BOLD, bt4.getFont().getSize()));
				bt4.setMinWidth(300);
				bt4.setMinHeight(50);
				bt4.setOnAction(new EventHandler<ActionEvent>() {
					@Override

					public void handle(ActionEvent arg0) {
						BorderPane e = new BorderPane();
						e.setStyle("-fx-background-color: pink ;");
						GridPane g = new GridPane();
						Label label = new Label("Customer ID: ");
						label.setFont(Font.font(25));
						label.setFont(Font.font(null, FontWeight.BOLD, label.getFont().getSize()));
						g.add(label, 0, 0);
						TextField id = new TextField();
						id.setMinHeight(30);
						id.setMinWidth(5);
						g.add(id, 1, 0);
						Label c = new Label("Find");
						c.setFont(Font.font(15));
						c.setFont(Font.font(null, FontWeight.BOLD, c.getFont().getSize()));
						Image image11 = new Image("find.png");
						Button b = new Button("", new ImageView(image11));
						b.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								Label labell = new Label("Customer Name: ");
								labell.setFont(Font.font(15));
								labell.setFont(Font.font(null, FontWeight.BOLD, labell.getFont().getSize()));
								g.add(labell, 0, 1);
								TextField name = new TextField();
								g.add(name, 1, 1);
								Label labell2 = new Label("Customer Address: ");
								labell2.setFont(Font.font(15));
								labell2.setFont(Font.font(null, FontWeight.BOLD, labell2.getFont().getSize()));
								g.add(labell2, 0, 2);
								TextField address = new TextField();
								g.add(address, 1, 2);
								Label labe = new Label("Customer Mobile: ");
								labe.setFont(Font.font(15));
								labe.setFont(Font.font(null, FontWeight.BOLD, labe.getFont().getSize()));
								g.add(labe, 0, 3);
								TextField mobile = new TextField();
								g.add(mobile, 1, 3);
								Label labe2 = new Label("Customer Plan: ");
								labe2.setFont(Font.font(15));
								labe2.setFont(Font.font(null, FontWeight.BOLD, labe2.getFont().getSize()));
								g.add(labe2, 0, 4);
								TextField plan = new TextField();
								g.add(plan, 1, 4);
								int y = m.find(id.getText());
								if (m.check(id.getText()) == 1) {
									name.setText(m.List1.get(y).getName());
									address.setText(m.List1.get(y).getAddress());
									mobile.setText(m.List1.get(y).getMobile());
									plan.setText(m.List1.get(y).getPlan());
								}

							}

						});

						Label c2 = new Label("Back");
						c2.setFont(Font.font(15));
						c2.setFont(Font.font(null, FontWeight.BOLD, c2.getFont().getSize()));
						Image image33 = new Image("back.png");
						Button b2 = new Button("", new ImageView(image33));
						b2.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene2);

							}

						});
						HBox k = new HBox();
						k.getChildren().addAll(c, b, c2, b2);
						k.setSpacing(25);
						k.setAlignment(Pos.CENTER);
						k.setPadding(new Insets(25, 25, 50, 25));
						e.setBottom(k);
						g.setAlignment(Pos.CENTER);

						g.setHgap(5);
						g.setVgap(10);
						e.setCenter(g);
						Scene scene = new Scene(e, 1600, 800);
						primaryStage.setScene(scene);
						primaryStage.show();
					}

				});

				Button bt5 = new Button("Return to the main page");
				bt5.setFont(Font.font(15));
				bt5.setFont(Font.font(null, FontWeight.BOLD, bt5.getFont().getSize()));
				bt5.setMinWidth(300);
				bt5.setMinHeight(50);
				bt5.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						primaryStage.setScene(scene1);

					}

				});

				v.getChildren().addAll(bt, bt2, bt3, bt4, bt5);
				v.setPadding(new Insets(50, 50, 50, 50));
				v.setSpacing(20);
				stack.setCenter(v);

			});
			btn2.setOnAction(e -> {
				BorderPane stack1 = new BorderPane();
				Button bt = new Button("Add new Media");
				bt.setFont(Font.font(15));
				bt.setFont(Font.font(null, FontWeight.BOLD, bt.getFont().getSize()));
				stack1.setStyle("-fx-background-color: pink ;");
				bt.setMinWidth(300);
				bt.setMinHeight(50);
				VBox v = new VBox();
				v.setAlignment(Pos.CENTER);
				stack1.setStyle("-fx-background-color: pink ;");
				Scene scene3 = new Scene(stack1, 1600, 800);
				primaryStage.setScene(scene3);
				primaryStage.show();
				bt.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						BorderPane o = new BorderPane();
						o.setStyle("-fx-background-color: pink ;");
						GridPane g = new GridPane();
						Label l = new Label("Please Select ");
						l.setFont(Font.font(20));
						l.setFont(Font.font(null, FontWeight.BOLD, l.getFont().getSize()));
						g.add(l, 0, 0);
						ComboBox a = new ComboBox();
						a.setStyle("-fx-background-color : white; -fx-font-size : 15;");
						a.getItems().addAll("Add Album", "Add Movie", "Add game");
						g.add(a, 1, 0);
						Label cv = new Label("Add");
						cv.setFont(Font.font(15));
						cv.setFont(Font.font(null, FontWeight.BOLD, cv.getFont().getSize()));
						Image image1 = new Image("plus.png");
						Button btt = new Button("", new ImageView(image1));

						btt.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								TextField album = new TextField();
								album.setMinHeight(15);
								TextField copies = new TextField();
								TextField code = new TextField();
								TextField artist = new TextField();
								TextField songs = new TextField();
								TextField rating = new TextField();
								TextField weight = new TextField();
								if (a.getValue().equals("Add Album")) {
									Label l = new Label("please enter an album name ");
									l.setFont(Font.font(18));
									l.setFont(Font.font(null, FontWeight.BOLD, l.getFont().getSize()));
									g.add(l, 0, 1);
									g.add(album, 1, 1);
									copies.setDisable(true);
									album.setOnKeyTyped(new EventHandler<KeyEvent>() {
										@Override
										public void handle(KeyEvent keyEvent) {
											copies.setDisable(false);
										}
									});
									Label ll = new Label("please enter the number of copies ");
									ll.setFont(Font.font(18));
									ll.setFont(Font.font(null, FontWeight.BOLD, ll.getFont().getSize()));
									g.add(ll, 0, 2);
									g.add(copies, 1, 2);
									code.setDisable(true);
									copies.setOnKeyTyped(new EventHandler<KeyEvent>() {
										@Override
										public void handle(KeyEvent keyEvent) {
											code.setDisable(false);
										}
									});
									Label la = new Label("please enter a code ");
									la.setFont(Font.font(18));
									la.setFont(Font.font(null, FontWeight.BOLD, la.getFont().getSize()));
									g.add(la, 0, 3);
									g.add(code, 1, 3);
									artist.setDisable(true);
									code.setOnKeyTyped(new EventHandler<KeyEvent>() {
										@Override
										public void handle(KeyEvent keyEvent) {
											artist.setDisable(false);
										}
									});
									Label ar = new Label("please enter an artist ");
									ar.setFont(Font.font(18));
									ar.setFont(Font.font(null, FontWeight.BOLD, ar.getFont().getSize()));
									g.add(ar, 0, 4);
									g.add(artist, 1, 4);
									songs.setDisable(true);
									artist.setOnKeyTyped(new EventHandler<KeyEvent>() {
										@Override
										public void handle(KeyEvent keyEvent) {
											songs.setDisable(false);
										}
									});
									Label song = new Label("please enter the songs ");
									song.setFont(Font.font(18));
									song.setFont(Font.font(null, FontWeight.BOLD, ar.getFont().getSize()));
									g.add(song, 0, 5);
									g.add(songs, 1, 5);
									btt.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {

											m.addAlbum(album.getText(), Integer.parseInt(copies.getText()),
													code.getText(), artist.getText(), songs.getText());

											album.clear();
											copies.clear();
											code.clear();
											artist.clear();
											songs.clear();

										}

									});
								}
								if (a.getValue().equals("Add Movie")) {
									Label mov = new Label("Please enter a Movie ");
									mov.setFont(Font.font(18));
									mov.setFont(Font.font(null, FontWeight.BOLD, mov.getFont().getSize()));
									g.add(mov, 0, 1);
									TextField Movie = new TextField();
									g.add(Movie, 1, 1);
									copies.setDisable(true);
									Movie.setOnKeyTyped(new EventHandler<KeyEvent>() {
										@Override
										public void handle(KeyEvent keyEvent) {
											copies.setDisable(false);
										}
									});
									Label co = new Label("Please enter the number of copies ");
									co.setFont(Font.font(18));
									co.setFont(Font.font(null, FontWeight.BOLD, co.getFont().getSize()));
									g.add(co, 0, 2);
									g.add(copies, 1, 2);
									code.setDisable(true);
									copies.setOnKeyTyped(new EventHandler<KeyEvent>() {
										@Override
										public void handle(KeyEvent keyEvent) {
											code.setDisable(false);
										}
									});
									Label cod = new Label("Please enter a code ");
									cod.setFont(Font.font(18));
									cod.setFont(Font.font(null, FontWeight.BOLD, cod.getFont().getSize()));
									g.add(cod, 0, 3);
									g.add(code, 1, 3);
									rating.setDisable(true);
									code.setOnKeyTyped(new EventHandler<KeyEvent>() {
										@Override
										public void handle(KeyEvent keyEvent) {
											rating.setDisable(false);
										}
									});
									Label ra = new Label("Please enter the Rating ");
									ra.setFont(Font.font(18));
									ra.setFont(Font.font(null, FontWeight.BOLD, ra.getFont().getSize()));
									g.add(ra, 0, 4);
									g.add(rating, 1, 4);
									btt.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {

											m.addMovie(Movie.getText(), Integer.parseInt(copies.getText()),
													code.getText(), rating.getText());
											Movie.clear();
											copies.clear();
											code.clear();
											rating.clear();
										}

									});
								}
								if (a.getValue().equals("Add game")) {
									Label ga = new Label("Please enter a Game ");
									ga.setFont(Font.font(18));
									ga.setFont(Font.font(null, FontWeight.BOLD, ga.getFont().getSize()));
									g.add(ga, 0, 1);
									TextField Game = new TextField();
									g.add(Game, 1, 1);
									copies.setDisable(true);
									Game.setOnKeyTyped(new EventHandler<KeyEvent>() {
										@Override
										public void handle(KeyEvent keyEvent) {
											copies.setDisable(false);
										}
									});
									Label coo = new Label("Please enter the number of copies ");
									coo.setFont(Font.font(18));
									coo.setFont(Font.font(null, FontWeight.BOLD, coo.getFont().getSize()));
									g.add(coo, 0, 2);
									g.add(copies, 1, 2);
									code.setDisable(true);
									copies.setOnKeyTyped(new EventHandler<KeyEvent>() {
										@Override
										public void handle(KeyEvent keyEvent) {
											code.setDisable(false);
										}
									});
									Label cd = new Label("Please enter a code ");
									cd.setFont(Font.font(18));
									cd.setFont(Font.font(null, FontWeight.BOLD, cd.getFont().getSize()));
									g.add(cd, 0, 3);
									g.add(code, 1, 3);
									weight.setDisable(true);
									code.setOnKeyTyped(new EventHandler<KeyEvent>() {
										@Override
										public void handle(KeyEvent keyEvent) {
											weight.setDisable(false);
										}
									});
									Label w = new Label("Please enter the Weight ");
									w.setFont(Font.font(18));
									w.setFont(Font.font(null, FontWeight.BOLD, w.getFont().getSize()));
									g.add(w, 0, 4);
									g.add(weight, 1, 4);
									btt.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {

											m.addGame(Game.getText(), Integer.parseInt(copies.getText()),
													code.getText(), Double.parseDouble(weight.getText()));
											Game.clear();
											copies.clear();
											code.clear();
											weight.clear();
										}

									});

								}

							}

						});

						Label cv1 = new Label("Back");
						cv1.setFont(Font.font(15));
						cv1.setFont(Font.font(null, FontWeight.BOLD, cv1.getFont().getSize()));
						Image image2 = new Image("back.png");
						Button btt1 = new Button("", new ImageView(image2));
						btt1.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene3);
								FileWriter writer;
								try {
									writer = new FileWriter(output1, false);
									writer.write(m.getAllMediaInfo());
									writer.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						});

						btt1.setMinWidth(2);
						HBox n = new HBox();
						n.getChildren().addAll(cv, btt, cv1, btt1);
						n.setSpacing(35);
						n.setAlignment(Pos.CENTER);
						n.setPadding(new Insets(25, 25, 50, 25));
						o.setBottom(n);
						g.setAlignment(Pos.CENTER);

						g.setHgap(5);
						g.setVgap(10);
						o.setCenter(g);

						Scene scene3 = new Scene(o, 1600, 800);
						primaryStage.setScene(scene3);
						primaryStage.show();

					}
				});

				Button bt2 = new Button("Delete Media");
				bt2.setFont(Font.font(15));
				bt2.setFont(Font.font(null, FontWeight.BOLD, bt2.getFont().getSize()));
				bt2.setMinWidth(300);
				bt2.setMinHeight(50);
				bt2.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						BorderPane e = new BorderPane();
						e.setStyle("-fx-background-color: pink ;");
						GridPane g = new GridPane();
						Label co = new Label("Customer Code: ");
						co.setFont(Font.font(25));
						co.setFont(Font.font(null, FontWeight.BOLD, co.getFont().getSize()));
						g.add(co, 0, 0);
						TextField code = new TextField();
						g.add(code, 1, 0);
						code.setMinHeight(35);
						code.setMinWidth(5);
						Label c = new Label("Find");
						c.setFont(Font.font(15));
						c.setFont(Font.font(null, FontWeight.BOLD, c.getFont().getSize()));
						Image image11 = new Image("find.png");
						Button b = new Button("", new ImageView(image11));
						b.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								int[] s = m.find2(code.getText());
								if (s[1] == 1) {
									Label w = new Label("The Movie ");
									w.setFont(Font.font(15));
									w.setFont(Font.font(null, FontWeight.BOLD, w.getFont().getSize()));
									g.add(w, 0, 1);
									TextField Movie = new TextField();
									g.add(Movie, 1, 1);
									Label we = new Label(" The Number of Copies ");
									we.setFont(Font.font(15));
									we.setFont(Font.font(null, FontWeight.BOLD, we.getFont().getSize()));
									g.add(we, 0, 2);
									TextField copies = new TextField();
									g.add(copies, 1, 2);
									Label ww = new Label("The Code ");
									ww.setFont(Font.font(15));
									ww.setFont(Font.font(null, FontWeight.BOLD, ww.getFont().getSize()));
									g.add(ww, 0, 3);
									TextField code = new TextField();
									g.add(code, 1, 3);
									Label rr = new Label(" The Rating ");
									rr.setFont(Font.font(15));
									rr.setFont(Font.font(null, FontWeight.BOLD, rr.getFont().getSize()));
									g.add(rr, 0, 4);
									TextField rating = new TextField();
									g.add(rating, 1, 4);
									Movie.setText(m.List2.get(s[0]).getTitle());
									copies.setText(String.valueOf(m.List2.get(s[0]).getCopiesAvailable()));
									code.setText(m.List2.get(s[0]).getCode());
									rating.setText(((Moviee) m.List2.get(s[0])).getRating());

								}
								if (s[1] == 2) {
									Label o = new Label("The Album ");
									o.setFont(Font.font(15));
									o.setFont(Font.font(null, FontWeight.BOLD, o.getFont().getSize()));
									g.add(o, 0, 1);
									TextField album = new TextField();
									g.add(album, 1, 1);
									Label q = new Label("please enter the number of copies ");
									q.setFont(Font.font(15));
									q.setFont(Font.font(null, FontWeight.BOLD, o.getFont().getSize()));
									g.add(q, 0, 2);
									TextField copies = new TextField();
									g.add(copies, 1, 2);
									Label b = new Label("please enter a code ");
									b.setFont(Font.font(15));
									b.setFont(Font.font(null, FontWeight.BOLD, b.getFont().getSize()));
									g.add(b, 0, 3);
									TextField code = new TextField();
									g.add(code, 1, 3);
									Label bb = new Label("please enter the artist name ");
									bb.setFont(Font.font(15));
									bb.setFont(Font.font(null, FontWeight.BOLD, bb.getFont().getSize()));
									g.add(bb, 0, 4);
									TextField artist = new TextField();
									g.add(artist, 1, 4);
									Label a = new Label("please enter the songs ");
									a.setFont(Font.font(15));
									a.setFont(Font.font(null, FontWeight.BOLD, a.getFont().getSize()));
									g.add(a, 0, 5);
									TextField songs = new TextField();
									g.add(songs, 1, 5);
									album.setText(m.List2.get(s[0]).getTitle());
									copies.setText(String.valueOf(m.List2.get(s[0]).getCopiesAvailable()));
									code.setText(m.List2.get(s[0]).getCode());
									artist.setText(((Album) m.List2.get(s[0])).getArtist());
									songs.setText(((Album) m.List2.get(s[0])).getSongs());
								}
								if (s[1] == 3) {
									Label t = new Label("please enter a Game ");
									t.setFont(Font.font(15));
									t.setFont(Font.font(null, FontWeight.BOLD, t.getFont().getSize()));
									g.add(t, 0, 1);
									TextField Game = new TextField();
									g.add(Game, 1, 1);
									Label b = new Label("please enter the number of copies ");
									b.setFont(Font.font(15));
									b.setFont(Font.font(null, FontWeight.BOLD, b.getFont().getSize()));
									g.add(b, 0, 2);
									TextField copies = new TextField();
									g.add(copies, 1, 2);
									Label a = new Label("please enter a code ");
									a.setFont(Font.font(15));
									a.setFont(Font.font(null, FontWeight.BOLD, a.getFont().getSize()));
									g.add(a, 0, 3);
									TextField code = new TextField();
									g.add(code, 1, 3);
									Label c = new Label("please enter the weight ");
									c.setFont(Font.font(15));
									c.setFont(Font.font(null, FontWeight.BOLD, c.getFont().getSize()));
									g.add(c, 0, 4);
									TextField weight = new TextField();
									g.add(weight, 1, 4);
									Game.setText(m.List2.get(s[0]).getTitle());
									copies.setText(String.valueOf(m.List2.get(s[0]).getCopiesAvailable()));
									code.setText(m.List2.get(s[0]).getCode());
									weight.setText(String.valueOf(((Game) m.List2.get(s[0])).getWeight()));

								}
							}
						});
						Label c1 = new Label("Add");
						c1.setFont(Font.font(15));
						c1.setFont(Font.font(null, FontWeight.BOLD, c1.getFont().getSize()));
						Image image22 = new Image("garbage.png");
						Button b1 = new Button("", new ImageView(image22));
						b1.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								m.Delete2(code.getText());
							}

						});
						Label c2 = new Label("Back");
						c2.setFont(Font.font(15));
						c2.setFont(Font.font(null, FontWeight.BOLD, c2.getFont().getSize()));
						Image image33 = new Image("back.png");
						Button b2 = new Button("", new ImageView(image33));
						b2.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene3);
								try {
									FileWriter writer = new FileWriter(output1, false);
									writer.write(m.getAllMediaInfo());
									writer.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						});
						HBox k = new HBox();
						k.getChildren().addAll(c, b, c1, b1, c2, b2);
						k.setSpacing(25);
						k.setAlignment(Pos.CENTER);
						k.setPadding(new Insets(25, 25, 50, 25));
						e.setBottom(k);
						g.setAlignment(Pos.CENTER);

						g.setHgap(5);
						g.setVgap(10);
						e.setCenter(g);
						Scene scene = new Scene(e, 1600, 800);
						primaryStage.setScene(scene);
						primaryStage.show();

					}
				});
				Button bt3 = new Button("Update Media");
				bt3.setFont(Font.font(15));
				bt3.setFont(Font.font(null, FontWeight.BOLD, bt3.getFont().getSize()));
				bt3.setMinWidth(300);
				bt3.setMinHeight(50);
				bt3.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						BorderPane q = new BorderPane();
						q.setStyle("-fx-background-color: pink ;");
						GridPane up = new GridPane();
						Label c = new Label("Find");
						c.setFont(Font.font(15));
						c.setFont(Font.font(null, FontWeight.BOLD, c.getFont().getSize()));
						Image image11 = new Image("find.png");
						Button b = new Button("", new ImageView(image11));
						Label e = new Label("Customer Code:");
						e.setFont(Font.font(25));
						e.setFont(Font.font(null, FontWeight.BOLD, e.getFont().getSize()));
						up.add(e, 0, 0);
						TextField code = new TextField();
						code.setMinHeight(35);
						code.setMinWidth(5);
						up.add(code, 1, 0);
						Label update = new Label("Update");
						update.setFont(Font.font(15));
						update.setFont(Font.font(null, FontWeight.BOLD, update.getFont().getSize()));
						Image image4 = new Image("update.png");
						Button upd = new Button("", new ImageView(image4));
						b.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								int v[] = m.find2(code.getText());
								if (v[1] == 1) {
									Label rr = new Label("Please Select:");
									rr.setFont(Font.font(15));
									rr.setFont(Font.font(null, FontWeight.BOLD, rr.getFont().getSize()));
									up.add(rr, 0, 1);
									CheckBox check1 = new CheckBox("Movie Name: ");
									check1.setFont(Font.font(15));
									check1.setFont(Font.font(null, FontWeight.BOLD, rr.getFont().getSize()));
									up.add(check1, 0, 2);
									CheckBox check2 = new CheckBox("Number of copies: ");
									check2.setFont(Font.font(15));
									check2.setFont(Font.font(null, FontWeight.BOLD, rr.getFont().getSize()));
									up.add(check2, 0, 3);
									CheckBox check3 = new CheckBox("Code: ");
									check3.setFont(Font.font(15));
									check3.setFont(Font.font(null, FontWeight.BOLD, rr.getFont().getSize()));
									up.add(check3, 0, 4);
									CheckBox check4 = new CheckBox("Rating: ");
									check4.setFont(Font.font(15));
									check4.setFont(Font.font(null, FontWeight.BOLD, rr.getFont().getSize()));
									up.add(check4, 0, 5);

									check1.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											if (check1.isSelected()) {
												Label movie = new Label("Movie Name: ");
												movie.setFont(Font.font(15));
												movie.setFont(
														Font.font(null, FontWeight.BOLD, movie.getFont().getSize()));
												up.add(movie, 0, 6);
												TextField d = new TextField();
												up.add(d, 1, 6);

												upd.setOnAction(new EventHandler<ActionEvent>() {

													@Override
													public void handle(ActionEvent arg0) {

														m.List2.get(v[0]).setTitle(d.getText());
													}

												});
											}

										}

									});
									check2.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent arg0) {
											Label copiess = new Label("Number of copies ");
											copiess.setFont(Font.font(15));
											copiess.setFont(
													Font.font(null, FontWeight.BOLD, copiess.getFont().getSize()));
											up.add(copiess, 0, 7);
											TextField number = new TextField();
											up.add(number, 1, 7);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													m.List2.get(v[0])
															.setCopiesAvailable(Integer.parseInt(number.getText()));
												}

											});

										}

									});
									check3.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											Label codee = new Label("Media Code");
											codee.setFont(Font.font(15));
											codee.setFont(Font.font(null, FontWeight.BOLD, codee.getFont().getSize()));
											up.add(codee, 0, 8);
											TextField code = new TextField();
											up.add(code, 1, 8);

											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													m.List2.get(v[0]).setCode(code.getText());
												}

											});
										}

									});
									check4.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											Label ratingg = new Label("please enter the Rating ");
											ratingg.setFont(Font.font(15));
											ratingg.setFont(
													Font.font(null, FontWeight.BOLD, ratingg.getFont().getSize()));
											up.add(ratingg, 0, 9);
											TextField rating = new TextField();
											up.add(rating, 1, 9);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													try {
														((Moviee) m.List2.get(v[0])).setRating(rating.getText());
													} catch (IllegalAccessException e) {

														e.printStackTrace();
													}
												}

											});
										}

									});

								}
								if (v[1] == 2) {
									Label pd = new Label("Please press one you want to update ");
									pd.setFont(Font.font(15));
									pd.setFont(Font.font(null, FontWeight.BOLD, pd.getFont().getSize()));
									up.add(pd, 0, 1);
									CheckBox check1 = new CheckBox("Album Name: ");

									check1.setFont(Font.font(15));
									check1.setFont(Font.font(null, FontWeight.BOLD, check1.getFont().getSize()));
									up.add(check1, 0, 2);
									CheckBox check2 = new CheckBox("Number of copies: ");

									check2.setFont(Font.font(15));
									check2.setFont(Font.font(null, FontWeight.BOLD, check2.getFont().getSize()));
									up.add(check2, 0, 3);
									CheckBox check3 = new CheckBox("Code: ");

									check3.setFont(Font.font(15));
									check3.setFont(Font.font(null, FontWeight.BOLD, check3.getFont().getSize()));
									up.add(check3, 0, 4);
									CheckBox check4 = new CheckBox("Artist: ");

									check4.setFont(Font.font(15));
									check4.setFont(Font.font(null, FontWeight.BOLD, check4.getFont().getSize()));
									up.add(check4, 0, 5);
									CheckBox check5 = new CheckBox("Songs: ");

									check5.setFont(Font.font(15));
									check5.setFont(Font.font(null, FontWeight.BOLD, check5.getFont().getSize()));
									up.add(check5, 0, 6);
									check1.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											if (check1.isSelected()) {
												Label name = new Label("Album Name: ");
												name.setFont(Font.font(15));
												name.setFont(
														Font.font(null, FontWeight.BOLD, name.getFont().getSize()));
												up.add(name, 0, 7);
												TextField d = new TextField();
												up.add(d, 1, 7);

												upd.setOnAction(new EventHandler<ActionEvent>() {

													@Override
													public void handle(ActionEvent arg0) {

														m.List2.get(v[0]).setTitle(d.getText());
													}

												});
											}

										}

									});
									check2.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent arg0) {
											Label cope = new Label("Number of copies ");
											cope.setFont(Font.font(15));
											cope.setFont(Font.font(null, FontWeight.BOLD, cope.getFont().getSize()));
											up.add(cope, 0, 8);
											TextField number = new TextField();
											up.add(number, 1, 8);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													m.List2.get(v[0])
															.setCopiesAvailable(Integer.parseInt(number.getText()));
												}

											});

										}

									});
									check3.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											Label codee = new Label("code ");
											codee.setFont(Font.font(15));
											codee.setFont(Font.font(null, FontWeight.BOLD, codee.getFont().getSize()));
											up.add(codee, 0, 9);
											TextField code = new TextField();
											up.add(code, 1, 9);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													m.List2.get(v[0]).setCode(code.getText());
												}

											});
										}

									});
									check4.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											Label artist = new Label("Artist");
											artist.setFont(Font.font(15));
											artist.setFont(
													Font.font(null, FontWeight.BOLD, artist.getFont().getSize()));
											up.add(artist, 0, 10);
											TextField Artist = new TextField();
											up.add(Artist, 1, 10);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													((Album) m.List2.get(v[0])).setArtist(Artist.getText());
												}
											});
										}
									});
									check5.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											Label song = new Label("Songs");
											song.setFont(Font.font(15));
											song.setFont(Font.font(null, FontWeight.BOLD, song.getFont().getSize()));
											up.add(song, 0, 11);
											TextField songs = new TextField();
											up.add(songs, 1, 11);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													((Album) m.List2.get(v[0])).setSongs(songs.getText());
												}

											});
										}

									});
								}
								if (v[1] == 3) {
									Label gg = new Label("Please Select:");
									gg.setFont(Font.font(15));
									gg.setFont(Font.font(null, FontWeight.BOLD, gg.getFont().getSize()));
									up.add(gg, 0, 1);
									CheckBox check1 = new CheckBox("Game Name: ");
									check1.setFont(Font.font(15));
									check1.setFont(Font.font(null, FontWeight.BOLD, check1.getFont().getSize()));
									up.add(check1, 0, 2);
									CheckBox check2 = new CheckBox("Number of copies: ");
									check2.setFont(Font.font(15));
									check2.setFont(Font.font(null, FontWeight.BOLD, check2.getFont().getSize()));
									up.add(check2, 0, 3);
									CheckBox check3 = new CheckBox("Code: ");
									check3.setFont(Font.font(15));
									check3.setFont(Font.font(null, FontWeight.BOLD, check3.getFont().getSize()));
									up.add(check3, 0, 4);
									CheckBox check4 = new CheckBox("Weight: ");
									check4.setFont(Font.font(15));
									check4.setFont(Font.font(null, FontWeight.BOLD, check4.getFont().getSize()));
									up.add(check4, 0, 5);

									check1.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											if (check1.isSelected()) {
												Label game = new Label("Game: ");
												game.setFont(Font.font(15));
												game.setFont(
														Font.font(null, FontWeight.BOLD, game.getFont().getSize()));
												up.add(game, 0, 6);
												TextField d = new TextField();
												up.add(d, 1, 6);

												upd.setOnAction(new EventHandler<ActionEvent>() {

													@Override
													public void handle(ActionEvent arg0) {

														m.List2.get(v[0]).setTitle(d.getText());
													}

												});
											}

										}

									});
									check2.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent arg0) {
											Label co = new Label("Number of copies ");
											co.setFont(Font.font(15));
											co.setFont(Font.font(null, FontWeight.BOLD, co.getFont().getSize()));
											up.add(co, 0, 7);
											TextField number = new TextField();
											up.add(number, 1, 7);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													m.List2.get(v[0])
															.setCopiesAvailable(Integer.parseInt(number.getText()));
												}

											});

										}

									});
									check3.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											Label co = new Label("please enter the code ");
											co.setFont(Font.font(15));
											co.setFont(Font.font(null, FontWeight.BOLD, co.getFont().getSize()));
											up.add(co, 0, 8);
											TextField code = new TextField();
											up.add(code, 1, 8);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													m.List2.get(v[0]).setCode(code.getText());
												}

											});
										}

									});
									check4.setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											Label wei = new Label("please enter the weight ");
											wei.setFont(Font.font(15));
											wei.setFont(Font.font(null, FontWeight.BOLD, wei.getFont().getSize()));
											up.add(wei, 0, 9);
											TextField weight = new TextField();
											up.add(weight, 1, 9);
											upd.setOnAction(new EventHandler<ActionEvent>() {

												@Override
												public void handle(ActionEvent arg0) {

													((Game) m.List2.get(v[0]))
															.setWeight(Double.parseDouble(weight.getText()));

												}

											});
										}

									});
								}
							}

						});

						Label c2 = new Label("Back");
						c2.setFont(Font.font(15));
						c2.setFont(Font.font(null, FontWeight.BOLD, c2.getFont().getSize()));
						Image image33 = new Image("back.png");
						Button b2 = new Button("", new ImageView(image33));
						b2.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene3);
								FileWriter writer;
								try {
									writer = new FileWriter(output1, false);
									writer.write(m.getAllMediaInfo());
									writer.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								System.out.print(m.getAllMediaInfo());
							}

						});

						HBox hor = new HBox();
						hor.getChildren().addAll(c, b, update, upd, c2, b2);
						hor.setSpacing(25);
						hor.setPadding(new Insets(25, 25, 50, 25));
						hor.setAlignment(Pos.CENTER);
						q.setBottom(hor);

						up.setAlignment(Pos.CENTER);
						up.setHgap(5);
						up.setVgap(10);
						q.setCenter(up);
						Scene scene = new Scene(q, 1600, 800);
						primaryStage.setScene(scene);
						primaryStage.show();

					}
				});

				Button bt4 = new Button("Search a Media by a code ");
				bt4.setFont(Font.font(15));
				bt4.setFont(Font.font(null, FontWeight.BOLD, bt4.getFont().getSize()));
				bt4.setMinWidth(300);
				bt4.setMinHeight(50);
				bt4.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						BorderPane e = new BorderPane();
						e.setStyle("-fx-background-color: pink ;");
						GridPane g = new GridPane();
						Label cus = new Label("Customer Code:");
						cus.setFont(Font.font(15));
						cus.setFont(Font.font(null, FontWeight.BOLD, cus.getFont().getSize()));
						g.add(cus, 0, 0);
						TextField code = new TextField();
						code.setMinHeight(35);
						code.setMinWidth(5);
						g.add(code, 1, 0);
						Label c = new Label("Find");
						c.setFont(Font.font(15));
						c.setFont(Font.font(null, FontWeight.BOLD, c.getFont().getSize()));
						Image image11 = new Image("find.png");
						Button b = new Button("", new ImageView(image11));
						b.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								int[] s = m.find2(code.getText());
								if (s[1] == 1) {
									Label ee = new Label("The Movie ");
									ee.setFont(Font.font(15));
									ee.setFont(Font.font(null, FontWeight.BOLD, ee.getFont().getSize()));
									g.add(ee, 0, 1);
									TextField Movie = new TextField();
									g.add(Movie, 1, 1);
									Label co = new Label(" the number of copies ");
									co.setFont(Font.font(15));
									co.setFont(Font.font(null, FontWeight.BOLD, co.getFont().getSize()));
									g.add(co, 0, 2);
									TextField copies = new TextField();
									g.add(copies, 1, 2);
									Label cod = new Label("the code ");
									cod.setFont(Font.font(15));
									cod.setFont(Font.font(null, FontWeight.BOLD, co.getFont().getSize()));
									g.add(cod, 0, 3);
									TextField code = new TextField();
									g.add(code, 1, 3);
									Label ratin = new Label(" the rating ");
									ratin.setFont(Font.font(15));
									ratin.setFont(Font.font(null, FontWeight.BOLD, co.getFont().getSize()));
									g.add(ratin, 0, 4);
									TextField rating = new TextField();
									g.add(rating, 1, 4);
									Movie.setText(m.List2.get(s[0]).getTitle());
									copies.setText(String.valueOf(m.List2.get(s[0]).getCopiesAvailable()));
									code.setText(m.List2.get(s[0]).getCode());
									rating.setText(((Moviee) m.List2.get(s[0])).getRating());

								}

								if (s[1] == 2) {
									Label t = new Label(" an Album ");
									t.setFont(Font.font(15));
									t.setFont(Font.font(null, FontWeight.BOLD, t.getFont().getSize()));
									g.add(t, 0, 1);
									TextField album = new TextField();
									g.add(album, 1, 1);
									Label tt = new Label("please enter the number of copies ");
									tt.setFont(Font.font(15));
									tt.setFont(Font.font(null, FontWeight.BOLD, tt.getFont().getSize()));
									g.add(tt, 0, 2);
									TextField copies = new TextField();
									g.add(copies, 1, 2);
									Label te = new Label("please enter a code ");
									te.setFont(Font.font(15));
									te.setFont(Font.font(null, FontWeight.BOLD, te.getFont().getSize()));
									g.add(te, 0, 3);
									TextField code = new TextField();
									g.add(code, 1, 3);
									Label ee = new Label("please enter the artist name ");
									ee.setFont(Font.font(15));
									ee.setFont(Font.font(null, FontWeight.BOLD, ee.getFont().getSize()));
									g.add(ee, 0, 4);
									TextField artist = new TextField();
									g.add(artist, 1, 4);
									Label u = new Label("please enter the songs ");
									u.setFont(Font.font(15));
									u.setFont(Font.font(null, FontWeight.BOLD, u.getFont().getSize()));
									g.add(u, 0, 5);
									TextField songs = new TextField();
									g.add(songs, 1, 5);
									album.setText(m.List2.get(s[0]).getTitle());
									copies.setText(String.valueOf(m.List2.get(s[0]).getCopiesAvailable()));
									code.setText(m.List2.get(s[0]).getCode());
									artist.setText(((Album) m.List2.get(s[0])).getArtist());
									songs.setText(((Album) m.List2.get(s[0])).getSongs());
								}
								if (s[1] == 3) {
									Label u = new Label("please enter a Game ");
									u.setFont(Font.font(15));
									u.setFont(Font.font(null, FontWeight.BOLD, u.getFont().getSize()));
									g.add(u, 0, 1);
									TextField Game = new TextField();
									g.add(Game, 1, 1);
									Label r = new Label("please enter the number of copies ");
									r.setFont(Font.font(15));
									r.setFont(Font.font(null, FontWeight.BOLD, r.getFont().getSize()));
									g.add(r, 0, 2);
									TextField copies = new TextField();
									g.add(copies, 1, 2);
									Label cod = new Label("please enter a code ");
									cod.setFont(Font.font(15));
									cod.setFont(Font.font(null, FontWeight.BOLD, cod.getFont().getSize()));
									g.add(cod, 0, 3);
									TextField code = new TextField();
									g.add(code, 1, 3);
									Label w = new Label("please enter the weight ");
									w.setFont(Font.font(15));
									w.setFont(Font.font(null, FontWeight.BOLD, w.getFont().getSize()));
									g.add(w, 0, 4);
									TextField weight = new TextField();
									g.add(weight, 1, 4);
									Game.setText(m.List2.get(s[0]).getTitle());
									copies.setText(String.valueOf(m.List2.get(s[0]).getCopiesAvailable()));
									code.setText(m.List2.get(s[0]).getCode());
									weight.setText(String.valueOf(((Game) m.List2.get(s[0])).getWeight()));

								}

							}

						});

						Label c2 = new Label("Back");
						c2.setFont(Font.font(15));
						c2.setFont(Font.font(null, FontWeight.BOLD, c2.getFont().getSize()));
						Image image33 = new Image("back.png");
						Button b2 = new Button("", new ImageView(image33));
						b2.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene3);

							}

						});
						HBox k = new HBox();
						k.getChildren().addAll(c, b, c2, b2);
						k.setSpacing(25);
						k.setAlignment(Pos.CENTER);
						k.setPadding(new Insets(25, 25, 50, 25));
						e.setBottom(k);
						g.setAlignment(Pos.CENTER);

						g.setHgap(5);
						g.setVgap(10);
						e.setCenter(g);
						Scene scene = new Scene(e, 1600, 800);
						primaryStage.setScene(scene);
						primaryStage.show();

					}

				});
				Button bt5 = new Button("Print All Media information");
				bt5.setFont(Font.font(15));
				bt5.setFont(Font.font(null, FontWeight.BOLD, bt5.getFont().getSize()));
				bt5.setMinWidth(300);
				bt5.setMinHeight(50);
				bt5.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						BorderPane e = new BorderPane();
						e.setStyle("-fx-background-color: pink ;");
						GridPane op = new GridPane();

						Label r1 = new Label("Movies");
						r1.setFont(Font.font(25));
						r1.setFont(Font.font(null, FontWeight.BOLD, r1.getFont().getSize()));
						op.add(r1, 0, 0);
						TextArea area = new TextArea();
						area.setPrefHeight(200);
						area.setPrefWidth(500);
						op.add(area, 1, 0);
						Label r = new Label("Albums");
						r.setFont(Font.font(25));
						r.setFont(Font.font(null, FontWeight.BOLD, r.getFont().getSize()));
						op.add(r, 0, 1);
						TextArea area1 = new TextArea();
						area1.setPrefHeight(200);
						area1.setPrefWidth(500);
						op.add(area1, 1, 1);
						Label r2 = new Label("Games");
						r2.setFont(Font.font(25));
						r2.setFont(Font.font(null, FontWeight.BOLD, r.getFont().getSize()));
						op.add(r2, 0, 2);
						TextArea area2 = new TextArea();
						area2.setPrefWidth(500);
						area2.setPrefHeight(200);
						op.add(area2, 1, 2);
						for (int k = 0; k < m.List2.size(); ++k) {
							if (m.List2.get(k) instanceof Moviee) {
								area.setFont(Font.font(15));
								area.setFont(Font.font(null, FontWeight.BOLD, area.getFont().getSize()));
								area.appendText(m.List2.get(k).toString() + "\n");

							} else if (m.List2.get(k) instanceof Album) {
								area1.setFont(Font.font(15));
								area1.setFont(Font.font(null, FontWeight.BOLD, area1.getFont().getSize()));
								area1.appendText(m.List2.get(k).toString() + "\n");

							} else if (m.List2.get(k) instanceof Game) {
								area2.setFont(Font.font(15));
								area2.setFont(Font.font(null, FontWeight.BOLD, area2.getFont().getSize()));
								area2.appendText(m.List2.get(k).toString() + "\n");
							}

						}
						Label c2 = new Label("Back");
						Image image33 = new Image("back.png");
						Button b2 = new Button("", new ImageView(image33));
						b2.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene3);

							}

						});
						HBox k = new HBox();
						k.getChildren().addAll(c2, b2);
						k.setSpacing(25);
						k.setAlignment(Pos.CENTER);
						k.setPadding(new Insets(25, 25, 50, 25));
						e.setBottom(k);
						op.setAlignment(Pos.CENTER);
						op.setHgap(20);
						op.setVgap(10);
						e.setCenter(op);
						Scene scene4 = new Scene(e, 1600, 800);
						primaryStage.setScene(scene4);
						primaryStage.show();
					}

				});
				Button bt6 = new Button("Return to the main page");
				bt6.setFont(Font.font(15));
				bt6.setFont(Font.font(null, FontWeight.BOLD, bt6.getFont().getSize()));
				bt6.setMinWidth(300);
				bt6.setMinHeight(50);
				bt6.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						primaryStage.setScene(scene1);

					}

				});

				v.getChildren().addAll(bt, bt2, bt3, bt4, bt5, bt6);
				v.setPadding(new Insets(50, 50, 50, 50));
				v.setSpacing(20);
				stack1.setCenter(v);

			});

			btn3.setOnAction(e -> {
				BorderPane stack = new BorderPane();
				stack.setStyle("-fx-background-color: pink ;");
				Button bt = new Button("Rent Form");
				bt.setFont(Font.font(15));
				bt.setFont(Font.font(null, FontWeight.BOLD, bt.getFont().getSize()));
				bt.setMinWidth(300);
				bt.setMinHeight(50);
				VBox v = new VBox();
				v.setAlignment(Pos.CENTER);
				Scene scene3 = new Scene(stack, 1600, 800);
				primaryStage.setScene(scene3);
				primaryStage.show();
				bt.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						BorderPane o = new BorderPane();
						o.setStyle("-fx-background-color: pink ;");
						GridPane g = new GridPane();
						RadioButton check = new RadioButton("for Rent click Here");
						check.setFont(Font.font(20));
						check.setFont(Font.font(null, FontWeight.BOLD, check.getFont().getSize()));
						g.add(check, 0, 0);
						Label cv = new Label("Print");
						cv.setFont(Font.font(15));
						cv.setFont(Font.font(null, FontWeight.BOLD, cv.getFont().getSize()));
						Image image1 = new Image("print.png");
						Button btt = new Button("", new ImageView(image1));
						Label vv = new Label("cart");
						vv.setFont(Font.font(15));
						vv.setFont(Font.font(null, FontWeight.BOLD, vv.getFont().getSize()));
						Image image = new Image("cart.png");
						Button b = new Button("", new ImageView(image));
						Label v = new Label("Process");
						v.setFont(Font.font(15));
						v.setFont(Font.font(null, FontWeight.BOLD, v.getFont().getSize()));
						Image i = new Image("process.png");
						Button bb = new Button("", new ImageView(i));

						check.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								Label id = new Label("Customer ID");
								id.setFont(Font.font(20));
								id.setFont(Font.font(null, FontWeight.BOLD, id.getFont().getSize()));
								g.add(id, 0, 2);
								TextField txt = new TextField();
								txt.setMinHeight(35);
								txt.setMinWidth(5);
								g.add(txt, 1, 2);

								Label code = new Label("Media Code ");
								code.setFont(Font.font(20));
								code.setFont(Font.font(null, FontWeight.BOLD, id.getFont().getSize()));
								g.add(code, 0, 3);
								TextField txtt = new TextField();
								txtt.setMinHeight(35);
								txtt.setMinWidth(5);
								g.add(txtt, 1, 3);
								btt.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent arg0) {
										int y = m.check(txt.getText());
										int j = m.find(txt.getText());
										int p[] = m.find2(txtt.getText());
										TextArea area = new TextArea();
										Label cust = new Label("The informaitons about the Customer");
										cust.setFont(Font.font(15));
										cust.setFont(Font.font(null, FontWeight.BOLD, cust.getFont().getSize()));
										g.add(cust, 0, 4);
										g.add(area, 0, 5);
										if (y == 1) {
											area.setFont(Font.font(15));
											area.setFont(Font.font(null, FontWeight.BOLD, txt.getFont().getSize()));
											area.appendText(m.List1.get(j).toString() + "\n");
										}
										Label media = new Label("The informaitons about the Media");
										TextArea area1 = new TextArea();
										media.setFont(Font.font(15));
										media.setFont(Font.font(null, FontWeight.BOLD, media.getFont().getSize()));
										g.add(media, 0, 6);
										g.add(area1, 0, 7);
										if (p[1] == 1 || p[1] == 2 || p[1] == 3) {
											area1.setFont(Font.font(15));
											area1.setFont(Font.font(null, FontWeight.BOLD, txt.getFont().getSize()));
											area1.appendText(m.List2.get(p[0]).toString() + "\n");
										}
										Label date = new Label("Rented Date ");
										date.setFont(Font.font(15));
										date.setFont(Font.font(null, FontWeight.BOLD, date.getFont().getSize()));
										Date date1 = java.util.Calendar.getInstance().getTime();
										TextField date2 = new TextField(date1.toString());
										date2.setFont(Font.font(15));
										date2.setFont(Font.font(null, FontWeight.BOLD, date2.getFont().getSize()));
										date2.setMinWidth(150);
										HBox ho = new HBox();
										ho.getChildren().addAll(date, date2);
										ho.setSpacing(10);
										g.add(ho, 0, 8);

									}
								});
								b.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent arg0) {
										m.addToCart2(txt.getText(), txtt.getText());

									}

								});

							}

						});
						bb.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								m.processRequests1();
								FileWriter writer;
								try {
									writer = new FileWriter(output1, false);
									writer.write(m.getAllMediaInfo());
									writer.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						});

						Label cv1 = new Label("Back");
						cv1.setFont(Font.font(15));
						cv1.setFont(Font.font(null, FontWeight.BOLD, cv1.getFont().getSize()));
						Image image2 = new Image("back.png");
						Button btt1 = new Button("", new ImageView(image2));
						btt1.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene3);

								FileWriter writer;
								try {
									writer = new FileWriter(output, false);
									writer.write(m.print());
									writer.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}

						});

						btt1.setMinWidth(2);
						HBox n = new HBox();
						n.getChildren().addAll(cv, btt, cv1, btt1, vv, b, v, bb);
						n.setSpacing(25);
						n.setAlignment(Pos.CENTER);
						n.setPadding(new Insets(25, 25, 50, 25));
						o.setBottom(n);
						g.setAlignment(Pos.CENTER);

						g.setHgap(5);
						g.setVgap(10);
						o.setCenter(g);

						Scene scene3 = new Scene(o, 1600, 800);
						primaryStage.setScene(scene3);
						primaryStage.show();

					}
				});

				Button bt2 = new Button("Print the interseted cart");
				bt2.setFont(Font.font(15));
				bt2.setFont(Font.font(null, FontWeight.BOLD, bt2.getFont().getSize()));
				bt2.setMinWidth(300);
				bt2.setMinHeight(50);
				bt2.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						BorderPane e = new BorderPane();
						e.setStyle("-fx-background-color: pink ;");
						GridPane g = new GridPane();
						CheckBox box = new CheckBox("Customer ID ");
						box.setFont(Font.font(25));
						box.setFont(Font.font(null, FontWeight.BOLD, box.getFont().getSize()));
						g.add(box, 0, 0);

						Label c = new Label("Find");
						c.setFont(Font.font(15));
						c.setFont(Font.font(null, FontWeight.BOLD, c.getFont().getSize()));
						Image image11 = new Image("find.png");
						Button bv = new Button("", new ImageView(image11));
						Label c2 = new Label("Back");
						c2.setFont(Font.font(15));
						c2.setFont(Font.font(null, FontWeight.BOLD, c2.getFont().getSize()));
						Image image33 = new Image("back.png");
						Button b2 = new Button("", new ImageView(image33));
						box.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								Label idd = new Label("Customer ID ");
								idd.setFont(Font.font(20));
								idd.setFont(Font.font(null, FontWeight.BOLD, idd.getFont().getSize()));
								g.add(idd, 0, 1);
								TextField id = new TextField();
								id.setMinHeight(30);
								id.setMinWidth(10);
								g.add(id, 1, 1);
								bv.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent arg0) {
										if (m.check(id.getText()) == 1) {
											int y = m.find(id.getText());
											TextArea area = new TextArea();
											g.add(area, 0, 2);
											area.setFont(Font.font(15));
											area.setFont(Font.font(null, FontWeight.BOLD, area.getFont().getSize()));
											area.setText(String.valueOf(m.List1.get(y).List));
										}

									}

								});

							}

						});

						b2.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene3);

							}

						});
						HBox k = new HBox();
						k.getChildren().addAll(c, bv, c2, b2);
						k.setSpacing(25);
						k.setAlignment(Pos.CENTER);
						k.setPadding(new Insets(25, 25, 50, 25));
						e.setBottom(k);
						g.setAlignment(Pos.CENTER);

						g.setHgap(5);
						g.setVgap(10);
						e.setCenter(g);
						Scene scene = new Scene(e, 1600, 800);
						primaryStage.setScene(scene);
						primaryStage.show();

					}
				});
				Button bt3 = new Button("Print Rented media");
				bt3.setFont(Font.font(15));
				bt3.setFont(Font.font(null, FontWeight.BOLD, bt3.getFont().getSize()));
				bt3.setMinWidth(300);
				bt3.setMinHeight(50);
				bt3.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						BorderPane e = new BorderPane();
						e.setStyle("-fx-background-color: pink ;");
						GridPane g = new GridPane();
						CheckBox box = new CheckBox("Customer ID ");
						box.setFont(Font.font(25));
						box.setFont(Font.font(null, FontWeight.BOLD, box.getFont().getSize()));
						g.add(box, 0, 0);

						Label c = new Label("Find");
						c.setFont(Font.font(15));
						c.setFont(Font.font(null, FontWeight.BOLD, c.getFont().getSize()));
						Image image11 = new Image("find.png");
						Button bv = new Button("", new ImageView(image11));
						Label c2 = new Label("Back");
						c2.setFont(Font.font(15));
						c2.setFont(Font.font(null, FontWeight.BOLD, c2.getFont().getSize()));
						Image image33 = new Image("back.png");
						Button b2 = new Button("", new ImageView(image33));
						box.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								Label cust = new Label(" Customer ID ");
								cust.setFont(Font.font(25));
								cust.setFont(Font.font(null, FontWeight.BOLD, cust.getFont().getSize()));
								g.add(cust, 0, 1);
								TextField id = new TextField();
								id.setMinHeight(30);
								id.setMinWidth(10);
								g.add(id, 1, 1);
								bv.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent arg0) {
										if (m.check(id.getText()) == 1) {
											int y = m.find(id.getText());
											TextArea area = new TextArea();
											g.add(area, 0, 2);
											area.setFont(Font.font(15));
											area.setFont(Font.font(null, FontWeight.BOLD, area.getFont().getSize()));
											area.setText(String.valueOf(m.List1.get(y).Rented));

										}

									}

								});

							}

						});

						b2.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {

								primaryStage.setScene(scene3);

							}

						});

						HBox hor = new HBox();
						hor.getChildren().addAll(c, bv, c2, b2);
						hor.setSpacing(25);
						hor.setPadding(new Insets(25, 25, 50, 25));
						hor.setAlignment(Pos.CENTER);
						e.setBottom(hor);

						g.setAlignment(Pos.CENTER);
						g.setHgap(5);
						g.setVgap(10);
						e.setCenter(g);
						Scene scene = new Scene(e, 1600, 800);
						primaryStage.setScene(scene);
						primaryStage.show();

					}
				});
				Button bt4 = new Button("Return Media");
				bt4.setFont(Font.font(15));
				bt4.setFont(Font.font(null, FontWeight.BOLD, bt4.getFont().getSize()));
				bt4.setMinWidth(300);
				bt4.setMinHeight(50);
				bt4.setOnAction(new EventHandler<ActionEvent>() {
					@Override

					public void handle(ActionEvent arg0) {
						BorderPane e = new BorderPane();
						e.setStyle("-fx-background-color: pink ;");
						GridPane g = new GridPane();
						CheckBox check = new CheckBox("Return Media  ");
						check.setFont(Font.font(25));
						check.setFont(Font.font(null, FontWeight.BOLD, check.getFont().getSize()));
						g.add(check, 0, 0);

						Label ol = new Label("Return");
						ol.setFont(Font.font(15));
						ol.setFont(Font.font(null, FontWeight.BOLD, ol.getFont().getSize()));
						Image im = new Image("return.png");
						Button er = new Button("", new ImageView(im));
						check.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent arg0) {
								Label custo = new Label("Customer ID ");
								custo.setFont(Font.font(20));
								custo.setFont(Font.font(null, FontWeight.BOLD, custo.getFont().getSize()));
								g.add(custo, 0, 2);
								TextField txt = new TextField();
								g.add(txt, 1, 2);
								Label media = new Label("Media Title ");
								media.setFont(Font.font(20));
								media.setFont(Font.font(null, FontWeight.BOLD, media.getFont().getSize()));
								g.add(media, 0, 3);
								TextField txtt = new TextField();
								g.add(txtt, 1, 3);

								er.setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent arg0) {
										m.returnMedia(txt.getText(), txtt.getText());

									}

								});

							}

						});

						Label c2 = new Label("Back");
						c2.setFont(Font.font(15));
						c2.setFont(Font.font(null, FontWeight.BOLD, c2.getFont().getSize()));
						Image image33 = new Image("back.png");
						Button b2 = new Button("", new ImageView(image33));
						b2.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								primaryStage.setScene(scene3);
								FileWriter writer;
								try {
									writer = new FileWriter(output1, false);
									writer.write(m.getAllMediaInfo());
									writer.close();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

						});
						HBox k = new HBox();
						k.getChildren().addAll(ol, er, c2, b2);
						k.setSpacing(25);
						k.setAlignment(Pos.CENTER);
						k.setPadding(new Insets(25, 25, 50, 25));
						e.setBottom(k);
						g.setAlignment(Pos.CENTER);

						g.setHgap(5);
						g.setVgap(10);
						e.setCenter(g);
						Scene scene = new Scene(e, 1600, 800);
						primaryStage.setScene(scene);
						primaryStage.show();

					}

				});

				Button bt5 = new Button("Return to the main page");
				bt5.setFont(Font.font(15));
				bt5.setFont(Font.font(null, FontWeight.BOLD, bt5.getFont().getSize()));
				bt5.setMinWidth(300);
				bt5.setMinHeight(50);
				bt5.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						primaryStage.setScene(scene1);

					}

				});

				v.getChildren().addAll(bt, bt2, bt3, bt4, bt5);
				v.setPadding(new Insets(50, 50, 50, 50));
				v.setSpacing(20);
				stack.setCenter(v);

			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
		



