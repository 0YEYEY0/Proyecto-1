package com.example.proyecto1;

import com.almasb.fxgl.scene.Scene;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;




public class Minesweeper extends Application {

    private static  final int TILE_SIZE =40;
    private static final int a=800;
    private static final int l=600;

    private static final int X_TILES = a/TILE_SIZE;
    private static final int Y_TTLES = l/TILE_SIZE;
    private Tile[][] grid = new Tile[X_TILES][Y_TTLES];

    private Parent createContent(){
        Pane root = new Pane();
        root.setPrefSize(a,l);
        for (int y = 0 < Y_TTLES; y++){
            for (int x = 0; x < X_TILES; x++){
                Tile tile = new Tile(x,y, Math.random()<0.2);
                grid[x][y] = tile;
                root.getChildren().add(tile);
                /**
                 * 8:50
                 */
            }

        }
        return root;
    }



    private class Tile extends StackPane{
        private int x,y;
        private boolean t_bomb;
        private int bombs = 0;

        private Rectangle border = new Rectangle(TILE_SIZE -2,TILE_SIZE-2);
        private Text text = new Text();
        public Tile(int x,int y, boolean t_bomb){
            this.x = x;
            this.y = y;
            this.t_bomb = t_bomb;


            border.setStroke(Color.LIGHTGRAY);

            text.setText(t_bomb ?"X" : "");
            getChildren().addAll(border,text);
            setTranslateX(x * TILE_SIZE);
            setTranslateY(y * TILE_SIZE);

        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage)throws Exception {
        Scene scene = new Scene(createContent());

        stage.setScene(scene);
        stage.show();

    }
}
