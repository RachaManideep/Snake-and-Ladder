package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public int playerPos[][] = new int[10][10];
    public int snakeAndLadderPos[][] = new int[6][6];

    public int rand;
    public Label randRes;
    public Label gameRes;

    public static final int Size = 60;
    public static final int width = 10;
    public static final int height = 10;

    public Circle player1;
    public Circle player2;

    public int player1Position = 0;
    public int player2Position = 0;

    public boolean player1Turn = true;
    public boolean player2Turn = true;

    public int player1XPos = 113;
    public int player1YPos = 630;

    public int player1NewXPos = 0;
    public int player1NewYPos = 0;

    public int player2XPos = 480;
    public int player2YPos = 630;

    public int player2NewXPos = 0;
    public int player2NewYPos = 0;

    public int posCir1 = 1;
    public int posCir2 = 1;

    public boolean gameStart = false;
    public Button gameButton;

    private Group titleGroup = new Group();

    private Parent creategameContent() {

        Pane pane = new Pane();
        pane.setPrefSize(width * Size, (height * Size) + 60);
        pane.getChildren().addAll(titleGroup);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile tile = new Tile(Size, Size);
                tile.setTranslateX(j * Size);
                tile.setTranslateY(i * Size);
                titleGroup.getChildren().add(tile);

            }

        }

        player1 = new Circle(30);
        player1.setId("Player 1");
        player1.setFill(Color.BLACK);
        player1.getStyleClass().add("Style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 = new Circle(30);
        player2.setId("Player 2");
        player2.setFill(Color.ROYALBLUE);
        player2.getStyleClass().add("Style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);

        Button button1 = new Button("Player 1");
        button1.setTranslateX(15);
        button1.setTranslateY(615);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (gameStart) {
                    if (player1Turn) {
                        getDiceValue();
                        randRes.setText(String.valueOf(rand));
                        player1Position += rand;
                        movePlayer1();
                        translatePlayer(player1XPos, player1YPos, player1);

                        player1Turn = false;
                        player2Turn = true;
                        if (gameStart)
                            gameRes.setText("Player Two turn");
                    }
                }
            }
        });


        Button button2 = new Button("Player 2");
        button2.setTranslateX(525);
        button2.setTranslateY(615);
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (gameStart) {
                    if (player2Turn) {
                        getDiceValue();
                        randRes.setText(String.valueOf(rand));
                        player2Position += rand;
                        movePlayer2();
                        translatePlayer(player2XPos, player2YPos, player2);

                        player2Turn = false;
                        player1Turn = true;
                        if (gameStart)
                            gameRes.setText("Player One turn");

                    }
                }
            }
        });


        gameButton = new Button("Start Game");
        gameButton.setTranslateX(262);
        gameButton.setTranslateY(615);

        gameButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!gameStart) {
                    gameStart = true;
                    randRes.setText("Dice Result");
                    randRes.setTranslateX(165);
                    gameButton.setText("Game Started");
                    player1XPos = 30;
                    player1YPos = 570;

                    player2XPos = 30;
                    player2YPos = 570;

                    player1Position = 1;
                    player2Position = 1;

                    posCir1 = 1;
                    posCir2 = 1;

                    player1.setTranslateX(player1XPos);
                    player1.setTranslateY(player1YPos);
                    player2.setTranslateX(player2XPos);
                    player2.setTranslateY(player2YPos);

                    rand = (int) (Math.random() * 2 + 1);
                    if (rand == 1) {
                        player1Turn = true;
                        gameRes.setText("Player One Turn");
                    } else {
                        player2Turn = true;
                        gameRes.setText("Player Two Turn");
                    }
                }

            }
        });

        randRes = new Label("Dice Result");
        randRes.setTranslateX(172);
        randRes.setTranslateY(622);

        gameRes = new Label("Game Result");
        gameRes.setTranslateX(367);
        gameRes.setTranslateY(622);

        Image img = new Image("snake and ladder img.jpeg");
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        imageView.setFitWidth(600);
        imageView.setFitHeight(600);


        titleGroup.getChildren().addAll(imageView, player1, player2, button1, button2, gameButton, randRes, gameRes);

        return pane;

    }

    public void getDiceValue() {
        rand = (int) (Math.random() * 6 + 1);
    }

    public void movePlayer1() {

        if (player1XPos == 30 && player1YPos == 30) {
            player1XPos = 30;
            player1YPos = 30;
        }

        for (int i = 0; i < rand; i++) {
            if (posCir1 % 2 == 1) {
                player1XPos += 60;
            }
            if (posCir1 % 2 == 0) {
                player1XPos -= 60;
            }
            if (player1XPos > 570) {
                player1YPos -= 60;
                player1XPos -= 60;
                posCir1++;
            }
            if (player1XPos < 30) {
                player1YPos -= 60;
                player1XPos += 60;
                posCir1++;
            }

            if (player1XPos < 20 || player1YPos < 20) {
                player1XPos = 30;
                player1YPos = 30;
                gameRes.setTranslateX(397);
                gameRes.setText("Player One Won");
                gameButton.setText("Start Again");
                gameStart = false;
            }
        }
        moveNewPlayer1();
    }

    // New positions of player1 for Snakes and Ladders
    public void moveNewPlayer1() {

        if (player1Position == 3) {
            player1XPos = 90;
            player1YPos = 390;
            posCir1 += 3;
            player1Position = 39;
        }
        if (player1Position == 10) {
            player1XPos = 510;
            player1YPos = 510;
            posCir1 += 1;
            player1Position = 12;
        }
        if (player1Position == 27) {
            player1XPos = 450;
            player1YPos = 270;
            posCir1 += 3;
            player1Position = 53;
        }
        if (player1Position == 56) {
            player1XPos = 210;
            player1YPos = 90;
            posCir1 += 3;
            player1Position = 84;
        }
        if (player1Position == 61 || player1Position == 99) {
            player1XPos = 30;
            player1YPos = 30;
            posCir1 += 3;
            player1Position = 99;
        }
        if (player1Position == 72) {
            player1XPos = 570;
            player1YPos = 90;
            posCir1 += 1;
            player1Position = 90;
        }
        if (player1Position == 16) {
            player1XPos = 450;
            player1YPos = 510;
            player1Position = 13;
        }
        if (player1Position == 31) {
            player1XPos = 280;
            player1YPos = 570;
            posCir1 -= 3;
            player1Position = 4;
        }
        if (player1Position == 47) {
            player1XPos = 210;
            player1YPos = 450;
            posCir1 -= 2;
            player1Position = 25;
        }
        if (player1Position == 63) {
            player1XPos = 30;
            player1YPos = 270;
            posCir1 -= 1;
            player1Position = 60;
        }
        if (player1Position == 66) {
            player1XPos = 510;
            player1YPos = 270;
            posCir1 -= 1;
            player1Position = 52;
        }
        if (player1Position == 97) {
            player1XPos = 330;
            player1YPos = 150;
            posCir1 -= 2;
            player1Position = 75;
        }
        if (player1Position >= 100) {
            player1XPos = 30;
            player1YPos = 30;
            posCir1 = 10;
            player1Position = 100;
        }

        player1Turn = false;
        player2Turn = true;
        if (gameStart)
            gameRes.setText("Player Two turn");
    }

    public void movePlayer2() {

        if (player1XPos == 30 && player1YPos == 30) {
            player1XPos = 30;
            player1YPos = 30;
        }

        for (int i = 0; i < rand; i++) {
            if (posCir2 % 2 == 1) {
                player2XPos += 60;
            }
            if (posCir2 % 2 == 0) {
                player2XPos -= 60;
            }
            if (player2XPos > 570) {
                player2YPos -= 60;
                player2XPos -= 60;
                posCir2++;
            }
            if (player2XPos < 30) {
                player2YPos -= 60;
                player2XPos += 60;
                posCir2++;
            }

            if (player2XPos < 20 || player2YPos < 20 || player2Position == 100) {
                player2XPos = 30;
                player2YPos = 30;
                gameRes.setTranslateX(397);
                gameRes.setText("Player Two Won");
                gameButton.setText("Start Again");
                gameStart = false;
            }
        }
        moveNewPlayer2();
    }

    // New positions of player2 for Snakes and Ladders
    public void moveNewPlayer2() {

        if (player2Position == 3) {
            player2XPos = 90;
            player2YPos = 390;
            posCir2 += 3;
            player2Position = 39;
        }
        if (player2Position == 10) {
            player2XPos = 510;
            player2YPos = 510;
            posCir2 += 1;
            player2Position = 12;
        }
        if (player2Position == 27) {
            player2XPos = 450;
            player2YPos = 270;
            posCir2 += 3;
            player2Position = 53;
        }
        if (player2Position == 56) {
            player2XPos = 210;
            player2YPos = 90;
            posCir2 += 3;
            player2Position = 84;
        }
        if (player2Position == 61 || player2Position == 99) {
            player2XPos = 90;
            player2YPos = 30;
            posCir2 += 3;
            player2Position = 99;
        }
        if (player2Position == 72) {
            player2XPos = 570;
            player2YPos = 90;
            posCir2 += 1;
            player2Position = 90;
        }
        if (player2Position == 16) {
            player2XPos = 450;
            player2YPos = 510;
            player2Position = 13;
        }
        if (player2Position == 31) {
            player2XPos = 210;
            player2YPos = 570;
            posCir2 -= 3;
            player2Position = 4;
        }
        if (player2Position == 47) {
            player2XPos = 210;
            player2YPos = 600;
            posCir2 -= 2;
            player2Position = 25;
        }
        if (player2Position == 63) {
            player2XPos = 40;
            player2YPos = 450;
            posCir2 -= 1;
            player2Position = 60;
        }
        if (player2Position == 66) {
            player2XPos = 510;
            player2YPos = 270;
            posCir2 -= 1;
            player2Position = 52;
        }
        if (player2Position == 97) {
            player2XPos = 330;
            player2YPos = 150;
            posCir2 -= 2;
            player2Position = 75;
        }
        if (player2Position >= 100) {
            player2XPos = 30;
            player2YPos = 30;
            posCir2 = 10;
            player2Position = 100;
        }

        player2Turn = false;
        player1Turn = true;
        if (gameStart)
            gameRes.setText("Player One turn");
    }

    public void translatePlayer(int x, int y, Circle b) {

        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(creategameContent());
        primaryStage.setTitle("Snake and Ladder Game");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}