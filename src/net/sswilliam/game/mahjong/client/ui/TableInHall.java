package net.sswilliam.game.mahjong.client.ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import net.sswilliam.game.mahjong.model.Table;

public class TableInHall extends JPanel {

	public JButton centerIndex = new JButton();
	public JButton dongBtn = new JButton();
	public JButton nanbtn = new JButton();
	public JButton xiBtn = new JButton();
	public JButton beiBtn = new JButton();
	public JLabel dongLabel =  new JLabel();
	public JLabel nanLable = new JLabel();
	public JLabel xiLabel = new JLabel();
	public JLabel beiLabel = new JLabel();
	
	public int id = -1;
	public TableInHall(int id){
		this.setLayout(null);
		this.id = id;
		this.setBorder(new LineBorder(Color.gray));
		this.setSize(250, 250);
		
		centerIndex.setText(""+id);
		centerIndex.setEnabled(false);
		dongBtn.setText("东");
		nanbtn.setText("南");
		xiBtn.setText("西");
		beiBtn.setText("北");
		
		dongLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nanLable.setHorizontalAlignment(SwingConstants.CENTER);
		xiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		beiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		UIManager.add(this, centerIndex, 85, 85, 80, 80);
		UIManager.add(this, dongBtn, 95, 170, 60, 60);
		UIManager.add(this, dongLabel, 85, 230, 80, 20);
		UIManager.add(this, nanbtn, 175, 95, 60, 60);
		UIManager.add(this, nanLable, 165, 155, 80, 20);
		UIManager.add(this, xiBtn, 95, 20, 60, 60);
		UIManager.add(this, xiLabel,85, 0, 80, 20);
		UIManager.add(this, beiBtn, 15, 95, 60, 60);
		UIManager.add(this, beiLabel, 5, 155, 80, 20);
		
		
	}
	
	public void refreshData(String source){
		String[] datas = source.split(",");
//		System.out.println(datas.length);
		if(datas.length == 5){
			int i = Integer.parseInt(datas[0]);
			switch (i) {
			case 0:
				this.centerIndex.setText(this.id+": 闲");
				break;
			case 1:

				this.centerIndex.setText(this.id+": 玩");
				break;
			default:
				break;
			}
			if(datas[1].equals("#")){
				dongLabel.setText("空");
			}else{
				dongLabel.setText(datas[1]);
			}
			if(datas[2].equals("#")){
				nanLable.setText("空");
			}else{
				nanLable.setText(datas[2]);
			}
			if(datas[3].equals("#")){
				xiLabel.setText("空");
			}else{
				xiLabel.setText(datas[3]);
			}
			if(datas[4].equals("#")){
				beiLabel.setText("空");
			}else{
				beiLabel.setText(datas[4]);
			}
		}
	}
}
