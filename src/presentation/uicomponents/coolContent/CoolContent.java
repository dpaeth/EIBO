package presentation.uicomponents.coolContent;
/**
 * Content für den Walk des Charackters Cool
 */
import javafx.animation.*;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import structure.Characters;
import structure.Mp3Player;

import java.io.IOException;
import java.util.Objects;

public class CoolContent extends StackPane {

    private Image RIGHT1, RIGHT2, RIGHT3, RIGHT4, RIGHT5, RIGHT6, RIGHT7, RIGHT8;
    private Image LEFT1, LEFT2,LEFT3, LEFT4, LEFT5, LEFT6, LEFT7,LEFT8;
    private final Mp3Player player;
    private final Group walk;
    private Pane background;
    private final Node rotateImage;
    private final SimpleStringProperty direct = new SimpleStringProperty();
    private final Timeline animation;
    private final Timeline rotateRight;
    private final Timeline rotate;
    private int turn = 0;
    private final BarChart<String, Number> spektrum;

    private Characters akt;
    private final Button coolButton;
    private final Button frostButton;
    private final Button kidButton;

    private final IntegerProperty r1 = new SimpleIntegerProperty();
    private final IntegerProperty g1 = new SimpleIntegerProperty();
    private final IntegerProperty b1 = new SimpleIntegerProperty();
    private final IntegerProperty r2 = new SimpleIntegerProperty();
    private final IntegerProperty g2 = new SimpleIntegerProperty();
    private final IntegerProperty b2 = new SimpleIntegerProperty();


    public CoolContent(Mp3Player player) {

        this.player = player;
        this.animation = new Timeline();
        this.rotate = new Timeline();
        this.rotateRight = new Timeline();

        HBox cControl = new HBox();

        this.coolButton = new Button();
        coolButton.setId("cool");
        this.frostButton = new Button();
        frostButton.setId("frost");
        this.kidButton = new Button();
        kidButton.setId("kid");

        cControl.getChildren().addAll(coolButton, frostButton, kidButton);
        cControl.setSpacing(20);
        cControl.setPadding(new Insets(20));
        cControl.setAlignment(Pos.TOP_CENTER);

        animation.setCycleCount(Timeline.INDEFINITE);
        rotate.setCycleCount(Timeline.INDEFINITE);
        rotateRight.setCycleCount(Timeline.INDEFINITE);

        initFXML();
        initImg(Characters.cool);

        rotateImage = background.lookup("#rotateImage");


        this.spektrum = player.getSpektrum();
        spektrum.setLegendVisible(false);
        spektrum.setAnimated(false);
        spektrum.setBarGap(0);
        spektrum.setCategoryGap(0);
        spektrum.setVerticalGridLinesVisible(false);
        spektrum.setHorizontalGridLinesVisible(false);
        spektrum.setHorizontalZeroLineVisible(false);
        spektrum.setVerticalZeroLineVisible(false);
        spektrum.setMaxSize(1500, 1000);
        spektrum.setMinSize(700, 400);
        NumberAxis yAxis = new NumberAxis(-50, 50, 10);
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis,null,"dB"));
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setTickLabelFill(Color.TRANSPARENT);
        yAxis.setTickLabelFill(Color.TRANSPARENT);

        ImageView right1 = new ImageView(RIGHT1);
        walk = new Group(right1);
        direct.setValue("right");
        background.setPadding(new Insets(2100, 0, 0, 0));

        init();

        this.getChildren().addAll(background, spektrum/*,vBox*/, walk, cControl);
        setAlignment(walk, Pos.BOTTOM_CENTER);
        setAlignment(background, Pos.BOTTOM_CENTER);
        this.setStyle("-fx-background-color: #1b1b1b;");
        this.setMinHeight(520);
        this.setMinWidth(760);
        this.setPrefWidth(760);
        this.setPrefHeight(520);
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        for (Node n:spektrum.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: rgb("+r1.get()+", "+g1.get()+", "+b1.get()+")");
        }

        for(Node n:spektrum.lookupAll(".default-color1.chart-bar")) {
            n.setStyle("-fx-bar-fill: rgb("+r2.get()+", "+g2.get()+", "+b2.get()+")");
        }
    }

    private void init() {
        /*
        this.r1.bindBidirectional(player.getR1());
        this.g1.bindBidirectional(player.getG1());
        this.b1.bindBidirectional(player.getB1());
        this.r2.bindBidirectional(player.getR2());
        this.g2.bindBidirectional(player.getG2());
        this.b2.bindBidirectional(player.getB2());

        for (Node n:this.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: rgb("+r1.get()+", "+g1.get()+", "+b1.get()+")");
        }

        for(Node n:spektrum.lookupAll(".default-color1.chart-bar")) {
            n.setStyle("-fx-bar-fill: rgb("+r2.get()+", "+g2.get()+", "+b2.get()+")");
        }

        player.aktSongIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                for (Node n:spektrum.lookupAll(".default-color0.chart-bar")) {
                    n.setStyle("-fx-bar-fill: rgb("+r1.get()+", "+g1.get()+", "+b1.get()+")");
                }

                for(Node n:spektrum.lookupAll(".default-color1.chart-bar")) {
                    n.setStyle("-fx-bar-fill: rgb("+r2.get()+", "+g2.get()+", "+b2.get()+")");
                }
            }
        });
        */

        coolButton.addEventHandler(ActionEvent.ACTION, e -> {
            if (akt!=Characters.cool){
                initImg(Characters.cool);
            }
        });
        frostButton.addEventHandler(ActionEvent.ACTION, e -> {
            if (akt!=Characters.frost){
                initImg(Characters.frost);
            }
        });
        kidButton.addEventHandler(ActionEvent.ACTION, e -> {
            if (akt!=Characters.kid){
                initImg(Characters.kid);
            }
        });



        r1.bind(player.getR1());
        g1.bind(player.getG1());
        b1.bind(player.getB1());
        r2.bind(player.getR2());
        g2.bind(player.getG2());
        b2.bind(player.getB2());

        player.getR1().addListener(((observableValue, number, t1) -> {
            for(Node n:spektrum.lookupAll(".default-color0.chart-bar")) {
                n.setStyle("-fx-bar-fill: rgb("+r1.get()+", "+g1.get()+", "+b1.get()+")");
            }

            for(Node n:spektrum.lookupAll(".default-color1.chart-bar")) {
                n.setStyle("-fx-bar-fill: rgb("+r2.get()+", "+g2.get()+", "+b2.get()+")");
            }
        }));




        player.turn().addListener((observable, oldValue, newValue) -> {
            turn++;
            if(turn % 2 == 0) {
                if(direct.getValue() == "left") {
                    direct.setValue("right");
                    go(direct);
                    rotateImageWalkRight();
                } else {
                    direct.setValue("left");
                    go(direct);
                    rotateImageWalkLeft();
                }
            }
        });

        player.isPlaying().addListener((observable, oldValue, newValue) -> {
            if(player.isPlaying().getValue()) {
                if(direct.getValue() == "right") {
                    rotateImageWalkRight();
                    go(direct);
                } else {
                    rotateImageWalkLeft();
                    go(direct);
                }
            } else {
                stop();
            }
        });



    }
    private void initImg(Characters c) {
        this.akt = c;

        this.RIGHT1 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/RIGHT/step1.png")));
        this.RIGHT2 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/RIGHT/step2.png")));
        this.RIGHT3 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/RIGHT/step3.png")));
        this.RIGHT4 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/RIGHT/step4.png")));
        this.RIGHT5 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/RIGHT/step5.png")));
        this.RIGHT6 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/RIGHT/step6.png")));
        this.RIGHT7 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/RIGHT/step7.png")));
        this.RIGHT8 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/RIGHT/step8.png")));

        this.LEFT1 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/LEFT/step1.png")));
        this.LEFT2 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/LEFT/step2.png")));
        this.LEFT3 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/LEFT/step3.png")));
        this.LEFT4 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/LEFT/step4.png")));
        this.LEFT5 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/LEFT/step5.png")));
        this.LEFT6 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/LEFT/step6.png")));
        this.LEFT7 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/LEFT/step7.png")));
        this.LEFT8 = new Image(String.valueOf(this.getClass().getResource("./"+c+"/LEFT/step8.png")));
    }
    private void initFXML () {
        try {
            background = FXMLLoader.load(this.getClass().getResource("./background.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void rotateImageWalkRight() {
        if(rotate.getKeyFrames() != null) {
            rotate.stop();
        }
        DoubleProperty r = rotateImage.rotateProperty();
        rotateRight.getKeyFrames().addAll(
                new KeyFrame(new Duration(0), new KeyValue(r, 0)),
                new KeyFrame(new Duration(23000), new KeyValue(r, -360))
        );
        rotateRight.play();
    }
    private void rotateImageWalkLeft() {
        if(rotate.getKeyFrames() != null) {
            rotateRight.stop();
        }
        DoubleProperty r = rotateImage.rotateProperty();
        rotate.getKeyFrames().addAll(
                new KeyFrame(new Duration(0), new KeyValue(r, 0)),
                new KeyFrame(new Duration(23000), new KeyValue(r, +360))
        );
        rotate.play();
    }
    private void stop() {
        if(animation.getKeyFrames() != null) {
            animation.stop();
        }
        if(rotate.getKeyFrames() != null) {
            rotate.stop();
        }
        if(rotateRight.getKeyFrames() != null) {
            rotateRight.stop();
        }
    }
    private void go(StringProperty direct) {
        ImageView step1, step2, step3, step4, step5, step6, step7, step8;
        String arrow = direct.getValue();

        switch(arrow) {
            case "right":
                step1 = new ImageView(RIGHT1);
                step2 = new ImageView(RIGHT2);
                step3 = new ImageView(RIGHT3);
                step4 = new ImageView(RIGHT4);
                step5 = new ImageView(RIGHT5);
                step6 = new ImageView(RIGHT6);
                step7 = new ImageView(RIGHT7);
                step8 = new ImageView(RIGHT8);
                animate(step1, step2, step3, step4, step5, step6, step7, step8);
                break;
            case "left" :
                step1 = new ImageView(LEFT1);
                step2 = new ImageView(LEFT2);
                step3 = new ImageView(LEFT3);
                step4 = new ImageView(LEFT4);
                step5 = new ImageView(LEFT5);
                step6 = new ImageView(LEFT6);
                step7 = new ImageView(LEFT7);
                step8 = new ImageView(LEFT8);
                animate(step1, step2, step3, step4, step5, step6, step7, step8);

                break;
            case "none":
                System.out.println("none");
        }

    }
    private void animate(ImageView step1,ImageView step2,
                         ImageView step3,ImageView step4,
                         ImageView step5,ImageView step6,
                         ImageView step7,ImageView step8) {
        if(animation != null) {
            animation.stop();
        }
            Objects.requireNonNull(animation).getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(100), (ActionEvent event) -> walk.getChildren().setAll(step1)),
                    new KeyFrame(Duration.millis(200), (ActionEvent event) -> walk.getChildren().setAll(step2)),
                    new KeyFrame(Duration.millis(300), (ActionEvent event) -> walk.getChildren().setAll(step3)),
                    new KeyFrame(Duration.millis(400), (ActionEvent event) -> walk.getChildren().setAll(step4)),
                    new KeyFrame(Duration.millis(500), (ActionEvent event) -> walk.getChildren().setAll(step5)),
                    new KeyFrame(Duration.millis(600), (ActionEvent event) -> walk.getChildren().setAll(step6)),
                    new KeyFrame(Duration.millis(700), (ActionEvent event) -> walk.getChildren().setAll(step7)),
                    new KeyFrame(Duration.millis(800), (ActionEvent event) -> walk.getChildren().setAll(step8))


                    );
        /*
            animation.getKeyFrames().add(new KeyFrame(Duration.millis(200),
                    (ActionEvent event) -> {
                        walk.getChildren().setAll(step2);
                    }
            ));
            animation.getKeyFrames().add(new KeyFrame(Duration.millis(300),
                    (ActionEvent event) -> {
                        walk.getChildren().setAll(step3);
                    }
            ));
            animation.getKeyFrames().add(new KeyFrame(Duration.millis(400),
                    (ActionEvent event) -> {
                        walk.getChildren().setAll(step4);
                    }
            ));
            animation.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                    (ActionEvent event) -> {
                        walk.getChildren().setAll(step5);
                    }
            ));
            animation.getKeyFrames().add(new KeyFrame(Duration.millis(600),
                    (ActionEvent event) -> {
                        walk.getChildren().setAll(step6);
                    }
            ));
            animation.getKeyFrames().add(new KeyFrame(Duration.millis(700),
                    (ActionEvent event) -> {
                        walk.getChildren().setAll(step7);
                    }
            ));
            animation.getKeyFrames().add(new KeyFrame(Duration.millis(800),
                    (ActionEvent event) -> {
                        walk.getChildren().setAll(step8);
                    }
            ));
            */
            animation.play();
    }


}


