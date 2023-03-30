package Sudoku;

import java.awt.*;

import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class MainSudoku extends JFrame implements ActionListener, KeyListener {	
	ArrayList<String> S = new ArrayList<String>();
	
	String str;
	int I, J;
	int an[] = {18, 35, 52};
	File file = new File("debai.txt");
	int [][]a = new int[9][9];
	int [][]aa = new int[9][9];
	int x[] = new int[81];
	int y[] = new int[81];
	int tmp[] = new int[81];
	private JPanel contentPane;
	private JPanel matrixPanel;
	private JComboBox cbxLV;
	private JButton btnSolver;
	private JButton btnNew;
	private JPanel panel;
	private JPanel panel1;
	private JButton[][] bt;
	private JButton btnReset;
	private JButton btnSelectFile;
	private JButton btnMenu;
	private JButton btnExit;

	/* Mở chương trình */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainSudoku frame = new MainSudoku();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/* Khởi tạo giao diện chương trình Sudoku */
	public MainSudoku() {
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				S.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(matrixPanel, "Có lỗi xảy ra.", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		
		btnSelectFile = new JButton("Select File");
		btnSelectFile.setBackground(Color.WHITE);
		btnSelectFile.addActionListener(this);
		
		btnNew = new JButton("New Game");
		btnNew.setBackground(Color.WHITE);
		btnNew.addActionListener(this);
		panel.add(btnNew);
		panel.add(btnSelectFile);
		
		
		cbxLV = new JComboBox();
		cbxLV.addItem("Dễ");
		cbxLV.addItem("Trung bình");
		cbxLV.addItem("Khó");
		cbxLV.setSelectedIndex(0);
		panel.add(cbxLV);
		
		btnSolver = new JButton("Sudoku Solver");
		btnSolver.addActionListener(this);
		panel.add(btnSolver);
		
		panel1 = new JPanel();
		panel1.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel1, BorderLayout.SOUTH);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		panel1.add(btnReset);
		
		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(this);
		panel1.add(btnMenu);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(this);
		panel1.add(btnExit);
		
		
		matrixPanel = new JPanel();
		matrixPanel.setBackground(Color.WHITE);
		contentPane.add(matrixPanel, BorderLayout.CENTER);
		matrixPanel.setLayout(new GridLayout(9,9));
		
		bt = new JButton[9][9];
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++) {
				bt[i][j] = new JButton();
				bt[i][j].addActionListener(this);
				bt[i][j].addKeyListener(this);
				bt[i][j].setActionCommand(i + " " + j);
				bt[i][j].setBackground(Color.white);
				bt[i][j].setFont(new Font("UTM Micra", 1, 30));
				bt[i][j].setForeground(Color.black);
				bt[i][j].setOpaque(true);
				matrixPanel.add(bt[i][j]);
			}
		
		for (int i = 0; i < 9; i += 3)
			for (int j = 0; j < 9; j += 3) {
				bt[i][j].setBorder(BorderFactory.createMatteBorder(3,3,1,1, Color.black));
				bt[i][j + 2].setBorder(BorderFactory.createMatteBorder(3,1,1,3, Color.black));
				bt[i + 2][j + 2].setBorder(BorderFactory.createMatteBorder(1,1,3,3, Color.black));
				bt[i + 2][j].setBorder(BorderFactory.createMatteBorder(1,3,3,1, Color.black));
				bt[i][j + 1].setBorder(BorderFactory.createMatteBorder(3,1,1,1, Color.black));
				bt[i + 1][j + 2].setBorder(BorderFactory.createMatteBorder(1,1,1,3, Color.black));
				bt[i + 2][j + 1].setBorder(BorderFactory.createMatteBorder(1,1,3,1, Color.black));
				bt[i + 1][j].setBorder(BorderFactory.createMatteBorder(1,3,1,1, Color.black));
				bt[i + 1][j + 1].setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
			}
		try {
			buildMatrix();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//ĐỌC DỮ LIỆU VÀ ẨN ĐI MỘT SỐ Ô
	public void buildMatrix() throws FileNotFoundException {
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++) {
				bt[i][j].setText("");
				bt[i][j].setForeground(Color.black);
				bt[i][j].setBackground(Color.white);
			}
		if(S.size()==1)
			str = S.get(0);
		else
			str = S.get((int)((S.size() - 1) * Math.random()) + 1);
        int N = 0;
        for (int i = 0; i < 9; i++)
        	for (int j = 0; j < 9; j++)
        		a[i][j] = str.charAt(i * 9 + j) - 48;
        
        for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j ++) {
				x[N] = i;
				y[N] = j;
				tmp[N++] = (int) (10000 * Math.random());
			}
		for (int i = 0; i < N - 1; i ++)
			for (int j = i + 1; j < N; j++)
				if (tmp[i] > tmp[j]) {
					int t = x[i];
					x[i] = x[j];
					x[j] = t;
					
					t = y[i];
					y[i] = y[j];
					y[j] = t;
					
					t = tmp[i];
					tmp[i] = tmp[j];
					tmp[j] = t;
				}
		for (int i = 0; i < an[cbxLV.getSelectedIndex()]; i++) {
			a[x[i]][y[i]] = 0;
		}
		for (int i = 0; i < 9; i++)
        	for (int j = 0; j < 9; j++)
        		if (a[i][j] > 0)
        			bt[i][j].setText(a[i][j] + "");
		for (int i = 0; i < 9; i++)
        	for (int j = 0; j < 9; j++)
        		aa[i][j] = a[i][j];
    }

	public void actionPerformed(ActionEvent e) { 
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				bt[i][j].setBackground(Color.white);
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals(btnNew.getText())) {
			try {
				buildMatrix();
			} catch (FileNotFoundException er) {
				// TODO Auto-generated catch block
				er.printStackTrace();
			}
		}else if (e.getActionCommand().equals(btnSelectFile.getText())) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(contentPane);
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    try {
			    	S.clear();
			    	S = new ArrayList<String>();
					Scanner sc = new Scanner(selectedFile);
					while(sc.hasNextLine()) {
						S.add(sc.nextLine());
					}
					sc.close();
				} catch (FileNotFoundException er) {
					JOptionPane.showMessageDialog(matrixPanel, "Có lỗi xảy ra.", "Lỗi", JOptionPane.INFORMATION_MESSAGE);
					er.printStackTrace();
				}
			}
		}else if (e.getActionCommand().equals(btnSolver.getText())) {
			//TODO
			if (solveSudoku(a)) {
				for (int i = 0; i < 9; i++)
		        	for (int j = 0; j < 9; j++)
		        		if(aa[i][j] == 0 ) {
		        			bt[i][j].setText(a[i][j] + "");
		        			bt[i][j].setForeground(Color.GREEN);
		        		}
				JOptionPane.showMessageDialog(matrixPanel, "Chúc mừng bạn đã thành công !!", "Thành công!", JOptionPane.INFORMATION_MESSAGE);
				}
//			else {
//				JOptionPane.showMessageDialog(matrixPanel, "Chưa chính xác. Thử lại lần nữa nhé !!", "Báo lỗi!", JOptionPane.INFORMATION_MESSAGE);
//			}
			
		}else if (e.getActionCommand().equals(btnReset.getText())) {	
			for(int i = 0; i < 9; i++)
				for(int j = 0; j < 9; j++) {
					bt[i][j].setText("");
					bt[i][j].setForeground(Color.black);
					bt[i][j].setBackground(Color.white);
				}
			for (int i = 0; i < 9; i++)
	        	for (int j = 0; j < 9; j++) {
	        		a[i][j] = aa[i][j];
	        		if (a[i][j] > 0)
	        			bt[i][j].setText(a[i][j] + "");
	        	}
		}else if (e.getActionCommand().equals(btnMenu.getText())) {
			MenuSudoku frame = new MenuSudoku();
			frame.setVisible(true);
			this.dispose();
		}else if(e.getActionCommand().equals(btnExit.getText())) {
			System.exit(EXIT_ON_CLOSE);
		}
		/* TÔ MÀU CHO Ô ĐƯỢC CHỌN ĐỂ ĐIỀN */
		else {
			String s = e.getActionCommand(); 
			int k = s.indexOf(32); 
			I = Integer.parseInt(s.substring(0, k));
			J = Integer.parseInt(s.substring(k + 1, s.length()));
//			System.out.println(I);
//			System.out.println(J);
			bt[I][J].setBackground(Color.lightGray);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	//ĐIỀN SỐ VÀO Ô TRỐNG//
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int v = e.getKeyCode();
		if ((v >= 49 && v <= 57) || (v >= 97 && v <= 105)) {
			if (v >= 49 && v <= 57)
				v -= 48;
			if (v >= 97 && v <= 105)
				v -= 96;
			if (aa[I][J] == 0) {
				bt[I][J].setText(v + "");
				if (isValidPlacement(a, v, I, J)) {
					a[I][J] = v;
					bt[I][J].setForeground(Color.GREEN);
					boolean check = true;
					for (int i = 0; i <  9; i++)
						for (int j = 0; j < 9; j++) {
							if(a[i][j]==0)
								check = false;
						}
					if (check) {
						JOptionPane.showMessageDialog(matrixPanel, "Thắng rồi. Bạn giỏi lắm !", "Chúc mừng!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else {
					bt[I][J].setForeground(Color.RED);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	// GIẢI THUẬT VÉT CẠN - SUDOKUSOLVER //
	private boolean isNumberInRow(int[][] board, int num, int row) {
		for(int i = 0; i < 9; i++) {
			if (board[row][i] == num) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isNumberInCol(int[][] board, int num, int col) {
		for(int i = 0; i < 9; i++) {
			if (board[i][col] == num) {
				return true;
			}
		}
		return false;
	}

	private boolean isNumberInBox(int[][] board, int num, int row, int col) {
		int localBoxRow = row - row % 3;
		int localBoxColumn = col - col % 3;
		for(int i = localBoxRow; i < localBoxRow + 3; i++)
			for(int j = localBoxColumn; j < localBoxColumn + 3; j++) {
				if(board[i][j] == num) {
					return true;
				}
			}
		return false;
	}
	
	private boolean isValidPlacement(int[][] board, int number, int row, int col) { 
		return !isNumberInRow(board, number, row) &&
				!isNumberInCol(board, number, col) &&
				!isNumberInBox(board, number, row, col);
	}
	
	private boolean solveSudoku(int[][] board) {
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				if (board[row][col] == 0) {
					for(int numTry = 1; numTry <= 9; numTry++) {
						if(isValidPlacement(board, numTry, row, col)) {
							board[row][col] = numTry;
			
							if(solveSudoku(board)) {
								return true;
							}
							else {
								board[row][col] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

}
