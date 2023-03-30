package Sudoku;

import java.awt.*;

import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class MenuSudoku extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnStartGame;
	private JButton btnExit;
	private JPanel contentPane_1;

	/* Mở chương trình MenuSudoku*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuSudoku frame = new MenuSudoku();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Khởi tạo chương trình MenuSudoku */
	public MenuSudoku() {
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnTitle = new JPanel();
		contentPane.add(pnTitle, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("SUDOKU");
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 35));
		pnTitle.add(lblTitle);
		
		
		contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(contentPane_1, BorderLayout.CENTER);
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnStartGame.setBounds(106, 76, 255, 68);
		btnStartGame.addActionListener(this);
		contentPane_1.add(btnStartGame);
		
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnExit.setBounds(106, 155, 255, 68);
		btnExit.addActionListener(this);
		contentPane_1.add(btnExit);
		
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(btnStartGame.getText())) {
			MainSudoku sudoku = new MainSudoku();
			sudoku.setVisible(true);
			this.dispose();
		}else if (e.getActionCommand().equals(btnExit.getText())) {
			System.exit(EXIT_ON_CLOSE);
		}
	}
}

