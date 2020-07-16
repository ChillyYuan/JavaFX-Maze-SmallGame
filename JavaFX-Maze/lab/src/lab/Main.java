package lab;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	Maze maze = new Maze();
	    
	@Override
    public void start(Stage primaryStage) throws Exception{
		
        //��ȡ��ͼ
	   maze.readMap();
	   
        //����ͼ
	   maze.drawPicture();
	   
        //���ܼ���������������ƶ�
	   maze.setOnKeyPressed( e -> maze.move() );    

	   Scene scene = new Scene(maze, maze.colnum * maze.width, maze.rownum * maze.height);
	   primaryStage.setTitle("�����Թ�");
	   primaryStage.setScene(scene);
	   primaryStage.show();
     
        //ʹ������ͼƬ���Խ��ܼ�������
       maze.imageViewMouse.requestFocus();

        
//     public static void main(String[] args) {
//        Application.launch(args);
//     }
    }
}




