package JDP;


import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame(){
		super("Task Manager");
		
		MenuFrame menu = new MenuFrame();
		ContentFrame content = new ContentFrame(); 
		
		setSize(800,550);//사이즈 정의
		setLocation(270,90);//켜졌을때의 프레임의 위치
		setResizable(false);//크기조정 불가
		
		add("North",menu.menubar);//위쪽에 메뉴 추가
		add(content.content);//중앙에 content 추가
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
