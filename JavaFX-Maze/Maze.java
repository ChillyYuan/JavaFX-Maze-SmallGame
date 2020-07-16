package lab;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Maze extends Pane{
	public int rownum,colnum;
	public int width = 40,height = 40;//����ĳߴ�
	public int[][] map = new int[100][100];
	public int[][] flag = new int[4000][4000];//�������ǽ
	
	public Pane pane = new Pane();
	public ImageView imageViewMouse = new ImageView("file:����.jpg");//���
	public ImageView imageViewBurger = new ImageView("file:����.jpg");//�յ�
	

	public Maze() {
		getChildren().add(pane);
	}
	
	//���ļ���ȡ��ͼ
	public void readMap()throws Exception{
	    try{
	        //��ȡ�Թ��ļ�
	        File file = new File("map.txt");
	        Scanner input = new Scanner(file);
	        //��ȡ��ͼ�Ŀ�͸�
	        rownum = input.nextInt();
	        colnum = input.nextInt();
	        //���ص�ͼ
	        for (int row = 0; row < rownum; row++)
	            for (int col = 0; col < colnum; col++)
	                map[row][col] = input.nextInt();
	        input.close();
	    }
	    catch (InputMismatchException ex){
	        System.out.println("error!");
	    }
	}
	
	//����ͼ
	public void drawPicture(){
	    for (int row = 0; row < rownum; row++){
	        for (int col = 0; col < colnum; col++){
	        	Rectangle rectangle = new Rectangle(col * width, row * height, width, height);;
	        	
	            //���ߵ�·--��ɫ
	            if (map[row][col] == 1 || map[row][col] == 2 || map[row][col] == 3){                   
	                rectangle.setFill(Color.WHITE); 
	                flag[col * width][row * height] = 1;
	            }
	            
	            //�����ߵ�·--��ɫ
	            if (map[row][col] == 0){                  
	                rectangle.setFill(Color.BROWN);
	                flag[col * width][row * height] = 0;
	            }
	            
	            pane.getChildren().add(rectangle);
	            
	            //���ͼƬ
	            if(map[row][col] == 2) {          	
	            	imageViewMouse.setFitHeight(height);
	            	imageViewMouse.setFitWidth(width);
	            	imageViewMouse.setX(col * width);
	            	imageViewMouse.setY(row * height);
	                pane.getChildren().add(imageViewMouse);
	                
	                //����������
	                FadeTransition ft = new FadeTransition(Duration.millis(5000),imageViewMouse);
	                ft.setFromValue(0);
	                ft.setToValue(1);
	                ft.play();
	
	            }
	            	
	            //�յ�ͼƬ
	            if(map[row][col] == 3) { 
	            	imageViewBurger.setFitHeight(height);
	            	imageViewBurger.setFitWidth(width);
	            	imageViewBurger.setX(col * width);
	            	imageViewBurger.setY(row * height);
	                pane.getChildren().add(imageViewBurger);
	                
	                //����������
	                FadeTransition ft = new FadeTransition(Duration.millis(5000),imageViewBurger);
	                ft.setFromValue(0);
	                ft.setToValue(1);
	                ft.play();
	            }
	        }
	    }
	}
	
	//�ƶ�����
	public void move() {
		
        //���ж��Ƿ���ǽ��ײ����������ֹ����������
        imageViewMouse.setOnKeyPressed(e -> {
        	
        	//ע�ᴦ��������Ӧ�����¼�
       		switch(e.getCode()) {
       		
       			//������
	       		case DOWN : 
	       			if(flag[(int)imageViewMouse.getX()][(int)imageViewMouse.getY() + height] == 1) {
	       				imageViewMouse.setY(imageViewMouse.getY() + height);
	       				//ʹ����ͷ����
	       				imageViewMouse.setRotate(90);
	       				break;
	       			}	
	       			else break;
	       			
	       		//������		
	       		case UP : 
	       			if(flag[(int)imageViewMouse.getX()][(int)imageViewMouse.getY() - height] == 1) {
	       				imageViewMouse.setY(imageViewMouse.getY() - height); 
	       				//ʹ����ͷ����
	       				imageViewMouse.setRotate(-90);
	       				break;
	       			}	
	       			else break;
	       			
	       		//������
	       		case LEFT : 
	       			if(flag[(int)imageViewMouse.getX() - width][(int)imageViewMouse.getY()] == 1) {
	       				imageViewMouse.setX(imageViewMouse.getX() - width); 
	       				//�����󱣳�ԭ��
	       				imageViewMouse.setRotate(0);
	       				break;
	       			}	
	       			else break;
	       			
	       		//������
	       		case RIGHT : 
	       			if(flag[(int)imageViewMouse.getX() + width][(int)imageViewMouse.getY()] == 1) {
	       				imageViewMouse.setX(imageViewMouse.getX() + width);
	       				//ʹ����ͷ���ң�����ԭ����
	       				imageViewMouse.setRotate(0);
	       				break;
	       			}	
	       			else break;
	       			
	       		default:
	       			break;
       		}   
       		
       		//ÿ�ƶ�һ�Σ��Ͱ�ͼƬ������Ƴ����ټӽ�ȥ
       		//����ͼƬ�ᱻ������ס
       		pane.getChildren().remove(imageViewMouse);
       		pane.getChildren().add(imageViewMouse);
       		
       		//������Ե������������յ�
       		if(imageViewMouse.getX() == imageViewBurger.getX() && imageViewMouse.getY() == imageViewBurger.getY()) {
       			
       			//���������꣬�Ƴ�����
       			pane.getChildren().remove(imageViewBurger);
       			
//       			//���Ŵ��سɹ�����
//       			String url = "���سɹ�.mp3";//���سɹ�����Ƶ
//       			Media media = new Media(url);
//       			MediaPlayer mediaPlayer = new MediaPlayer(media); 
//       		     			
//       			mediaPlayer.setAutoPlay(true);
//       			mediaPlayer.setCycleCount(20);
//       			mediaPlayer.play();

       			
       			//��ʾ���سɹ����ڶ�����̨��
       			StackPane stackPane = new StackPane();
       			Button button = new Button("���سɹ���");
       			stackPane.getChildren().add(button);  
       			
       			Stage stage = new Stage();
       			stage.setTitle("�����ʾ");
       			stage.setScene(new Scene(stackPane,250,150));
       			stage.show();
       			
       			//������������ͬ��ǰ���и�e�ˣ�Ҫʹ������������
       			button.setOnAction(e1 -> {
           			stage.close();
       			});
       			
       			//���سɹ�����������ʧ
                FadeTransition ft = new FadeTransition(Duration.millis(5000),imageViewMouse);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.play();
       		}     		
       	});
        
	}
}

	