package sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import geometry.Circle;

public class SortFrm extends JFrame {


	private JPanel contentPane;
	private ArrayList<Circle> circlelist=new ArrayList<Circle>();
	private DefaultListModel<Circle> dlm=new DefaultListModel<Circle>();
	
	JList<Circle> SortList = new JList<Circle>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortFrm frame = new SortFrm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SortFrm() {
		setTitle("Nikola Ljubicic IT-44-2021");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel CenterPanel = new JPanel();
		contentPane.add(CenterPanel, BorderLayout.CENTER);
		
		JScrollPane ScrollPane = new JScrollPane();
		
		
		
		SortList.setModel(dlm);
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortDlg dlg=new SortDlg(SortFrm.this);
				dlg.setVisible(true);
				circlelist.add(dlg.getcircle());
				dlm.removeAllElements();
				circlelist.sort(null);
				dlm.addAll(circlelist);
				SortList.setModel(dlm);
				
				
			}
		});
		
		GroupLayout gl_CenterPanel = new GroupLayout(CenterPanel);
		gl_CenterPanel.setHorizontalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_CenterPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(ScrollPane, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addComponent(btnAdd)
					.addGap(99))
		);
		gl_CenterPanel.setVerticalGroup(
			gl_CenterPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_CenterPanel.createSequentialGroup()
					.addGroup(gl_CenterPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(ScrollPane, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_CenterPanel.createSequentialGroup()
							.addGap(105)
							.addComponent(btnAdd)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		
		ScrollPane.setViewportView(SortList);
		CenterPanel.setLayout(gl_CenterPanel);
	}

}
