package presentation.uicomponents.frostcontent;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import structure.Mp3Player;
import java.io.IOException;

public class FrostContent extends StackPane {

    private Image RIGHT1, RIGHT2, RIGHT3, RIGHT4, RIGHT5, RIGHT6, RIGHT7, RIGHT8, RIGHT9, RIGHT10;
    private Image LEFT1, LEFT2,LEFT3, LEFT4, LEFT5, LEFT6, LEFT7,LEFT8, LEFT9, LEFT10;
    private Mp3Player player;
    private Group walk;
    private Parent background;
    private Node arrowleft;
    private Node arrowright;
    private SimpleStringProperty direct = new SimpleStringProperty();
    private Timeline animation;

    public FrostContent(Mp3Player player) {
        this.player = player;
        this.animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        initImg();
        this.background = initBackgr();
        arrowleft = background.lookup("#arrowleft");
        arrowright = background.lookup("#arrowright");
        direct.setValue("none");
        ImageView right1 = new ImageView(RIGHT1);
        walk = new Group(right1);
        init();
        this.getChildren().addAll(background, walk);
        this.setAlignment(walk, Pos.BOTTOM_CENTER);
        this.setMaxHeight(750);
        this.setMaxWidth(500);
    }

    private void init() {
        arrowright.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                direct.setValue("right");
                go(direct);
            }
        });
        arrowleft.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                direct.setValue("left");
                go(direct);
            }
        });
    }
    private void initImg() {
        this.RIGHT1 = new Image(String.valueOf(this.getClass().getResource("./frost/RIGHT/step1.png")));
        this.RIGHT2 = new Image(String.valueOf(this.getClass().getResource("./frost/RIGHT/step2.png")));
        this.RIGHT3 = new Image(String.valueOf(this.getClass().getResource("./frost/RIGHT/step3.png")));
        this.RIGHT4 = new Image(String.valueOf(this.getClass().getResource("./frost/RIGHT/step4.png")));
        this.RIGHT5 = new Image(String.valueOf(this.getClass().getResource("./frost/RIGHT/step5.png")));
        this.RIGHT6 = new Image(String.valueOf(this.getClass().getResource("./frost/RIGHT/step6.png")));
        this.RIGHT7 = new Image(String.valueOf(this.getClass().getResource("./frost/RIGHT/step7.png")));
        this.RIGHT8 = new Image(String.valueOf(this.getClass().getResource("./frost/RIGHT/step8.png")));
        this.RIGHT9 = new Image(String.valueOf(this.getClass().getResource("./frost/RIGHT/step9.png")));
        this.RIGHT10 = new Image(String.valueOf(this.getClass().getResource("./frost/RIGHT/step10.png")));

        this.LEFT1 = new Image(String.valueOf(this.getClass().getResource("./frost/LEFT/step1.png")));
        this.LEFT2 = new Image(String.valueOf(this.getClass().getResource("./frost/LEFT/step2.png")));
        this.LEFT3 = new Image(String.valueOf(this.getClass().getResource("./frost/LEFT/step3.png")));
        this.LEFT4 = new Image(String.valueOf(this.getClass().getResource("./frost/LEFT/step4.png")));
        this.LEFT5 = new Image(String.valueOf(this.getClass().getResource("./frost/LEFT/step5.png")));
        this.LEFT6 = new Image(String.valueOf(this.getClass().getResource("./frost/LEFT/step6.png")));
        this.LEFT7 = new Image(String.valueOf(this.getClass().getResource("./frost/LEFT/step7.png")));
        this.LEFT8 = new Image(String.valueOf(this.getClass().getResource("./frost/LEFT/step8.png")));
        this.LEFT9 = new Image(String.valueOf(this.getClass().getResource("./frost/LEFT/step9.png")));
        this.LEFT10 = new Image(String.valueOf(this.getClass().getResource("./frost/LEFT/step10.png")));
    }
    private Parent initBackgr() {
        Parent background = null;
        try {
            background = FXMLLoader.load(this.getClass().getResource("./background.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return background;
    }
    private void go(StringProperty direct) {
        ImageView step1, step2, step3, step4, step5, step6, step7, step8, step9, step10;
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
                step9 = new ImageView(RIGHT9);
                step10 = new ImageView(RIGHT10);
                animate(step1, step2, step3, step4, step5, step6, step7, step8, step9, step10);

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
                step9 = new ImageView(LEFT9);
                step10 = new ImageView(LEFT10);
                animate(step1, step2, step3, step4, step5, step6, step7, step8, step9, step10);

                break;
            case "none":
                System.out.println("none");
        }


    }
    private void animate(ImageView step1,ImageView step2,
                         ImageView step3,ImageView step4,
                         ImageView step5,ImageView step6,
                         ImageView step7,ImageView step8,
                         ImageView step9,ImageView step10) {
        if(animation != null) {
            animation.stop();
        }
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(200),
                (ActionEvent event) -> {
                    walk.getChildren().setAll(step2);
                }
        ));
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(400),
                (ActionEvent event) -> {
                    walk.getChildren().setAll(step3);
                }
        ));
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(600),
                (ActionEvent event) -> {
                    walk.getChildren().setAll(step4);
                }
        ));
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(800),
                (ActionEvent event) -> {
                    walk.getChildren().setAll(step5);
                }
        ));
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
                (ActionEvent event) -> {
                    walk.getChildren().setAll(step6);
                }
        ));
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(1200),
                (ActionEvent event) -> {
                    walk.getChildren().setAll(step7);
                }
        ));
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(1400),
                (ActionEvent event) -> {
                    walk.getChildren().setAll(step8);
                }
        ));
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(1600),
                (ActionEvent event) -> {
                    walk.getChildren().setAll(step9);
                }
        ));
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(1800),
                (ActionEvent event) -> {
                    walk.getChildren().setAll(step10);
                }
        ));
        animation.getKeyFrames().add(new KeyFrame(Duration.millis(2000),
                (ActionEvent event) -> {
                    walk.getChildren().setAll(step1);
                }
        ));
        animation.play();
    }

}


