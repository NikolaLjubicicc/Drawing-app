package mvc;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import drawing.CircleDlg;
import drawing.DonutDlg;
import drawing.LineDlg;
import drawing.PointDlg;
import drawing.RectangleDlg;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.PnlDrawing;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class DrawingFrame extends JFrame{
	
	private DrawingView view = new DrawingView();
	private DrawingController controller;
	
	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}
	private JPanel contentPane;

	
	JButton btnModify = new JButton("Modify");
	
	JButton btnDelete = new JButton("Delete");
	
	JToggleButton tglbtnDrawing = new JToggleButton("Drawing");
	
	JToggleButton tglbtnSelecting = new JToggleButton("Selecting");
	
	JToggleButton tglbtnPoint = new JToggleButton("Point");
	
	JToggleButton tglbtnLine = new JToggleButton("Line");
	
	JToggleButton tglbtnRectangle = new JToggleButton("Rectangle");
	
	JToggleButton tglbtnCircle = new JToggleButton("Circle");
	
	JToggleButton tglbtnDonut = new JToggleButton("Donut");
	
	JToggleButton tglbtnHexagon = new JToggleButton("Hexagon");
	
	JTextArea logTextArea = new JTextArea();
	
	JButton btnInnerColor = new JButton("Inner color");
	JButton btnOuterColor = new JButton("Outer color");
	private final JButton btnUndo = new JButton("Undo");
	private final JButton btnRedo = new JButton("Redo");
	private final JButton btnToFront = new JButton("To front");
	private final JButton btnBringToFront = new JButton("Bring to front");
	private final JButton btnToBack = new JButton("To back");
	private final JButton btnBringToBack = new JButton("Bring to back");
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DrawingModel model = new DrawingModel();
					DrawingFrame frame = new DrawingFrame();
					frame.getView().setModel(model); 
					frame.setController(new DrawingController(model, frame)); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();  
				}
			}
		});
	}
	
	
	public DrawingFrame() {
		
		setTitle("Nikola Ljubicic IT 44/2021");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		view.setBackground(new Color(255, 255, 255));
		view.setBounds(10, 47, 560, 557);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.MouseClicked(e);
			}
		});
	 
		contentPane.setLayout(null);
		
		contentPane.add(view);
		view.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel sidepnl = new JPanel();
		sidepnl.setBounds(580, 5, 125, 599);
		contentPane.add(sidepnl);


		GroupLayout gl_sidepnl = new GroupLayout(sidepnl);
		gl_sidepnl.setHorizontalGroup(
			gl_sidepnl.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_sidepnl.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_sidepnl.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_sidepnl.createSequentialGroup()
							.addGroup(gl_sidepnl.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnToBack, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(btnBringToBack, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(btnBringToFront, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_sidepnl.createSequentialGroup()
							.addGroup(gl_sidepnl.createParallelGroup(Alignment.TRAILING)
								.addComponent(tglbtnHexagon, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(btnModify, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(btnRedo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(btnUndo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(btnToFront, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_sidepnl.createSequentialGroup()
							.addGroup(gl_sidepnl.createParallelGroup(Alignment.TRAILING)
								.addComponent(tglbtnDrawing, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(tglbtnSelecting, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(tglbtnPoint, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(tglbtnLine, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(tglbtnRectangle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(tglbtnCircle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
								.addComponent(tglbtnDonut, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
							.addContainerGap())))
		);
		tglbtnPoint.setEnabled(false);
		tglbtnLine.setEnabled(false);
		tglbtnRectangle.setEnabled(false);
		tglbtnCircle.setEnabled(false);
		tglbtnDonut.setEnabled(false);
		tglbtnHexagon.setEnabled(false);
		btnModify.setEnabled(false);
		btnDelete.setEnabled(false);

		btnUndo.setEnabled(true);
		btnRedo.setEnabled(true);
	
		btnToFront.setEnabled(true);
		
		btnBringToFront.setEnabled(true);
	
		btnToBack.setEnabled(true);
	
		btnBringToBack.setEnabled(true);
		gl_sidepnl.setVerticalGroup(
			gl_sidepnl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sidepnl.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglbtnDrawing)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnSelecting)
					.addGap(30)
					.addComponent(tglbtnPoint)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnLine)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnRectangle)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnCircle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnDonut)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnHexagon)
					.addGap(29)
					.addComponent(btnModify)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUndo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRedo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnToFront)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBringToFront, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnToBack)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBringToBack)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		tglbtnDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setDrawing();
			}
		});
		tglbtnSelecting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setSelectingShapes();
			
			}
		});
		
		btnModify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				controller.modify();
			}});
			
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
		tglbtnPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnLine.setSelected(false);
				tglbtnRectangle.setSelected(false);
				tglbtnCircle.setSelected(false);
				tglbtnDonut.setSelected(false);
			}
		});
		tglbtnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnPoint.setSelected(false);
				tglbtnRectangle.setSelected(false);
				tglbtnCircle.setSelected(false);
				tglbtnDonut.setSelected(false);
			}
		});
		tglbtnRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnLine.setSelected(false);
				tglbtnPoint.setSelected(false);
				tglbtnCircle.setSelected(false);
				tglbtnDonut.setSelected(false);
			}
		});
		tglbtnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnLine.setSelected(false);
				tglbtnRectangle.setSelected(false);
				tglbtnPoint.setSelected(false);
				tglbtnDonut.setSelected(false);
			}
		});
		tglbtnDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglbtnLine.setSelected(false);
				tglbtnRectangle.setSelected(false);
				tglbtnCircle.setSelected(false);
				tglbtnPoint.setSelected(false);
			}
		});
		sidepnl.setLayout(gl_sidepnl);
		
		
		logTextArea.setBackground(new Color(210, 210, 210));
		logTextArea.setBounds(10, 614, 695, 39);
		contentPane.add(logTextArea);
		
	
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setInnerColor();
			}
		});
		btnInnerColor.setBackground(Color.BLACK);
		btnInnerColor.setForeground(new Color(0, 0, 0));
		btnInnerColor.setBounds(96, 16, 125, 21);
		contentPane.add(btnInnerColor);
		
	
		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setOuterColor();
			}
		});
		btnOuterColor.setBackground(Color.WHITE);
		btnOuterColor.setBounds(354, 16, 125, 21);
		contentPane.add(btnOuterColor);
		
		view.repaint();
	
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		
	}
	
	
	


	public DrawingView getPnlDrawing() {
		return view;
	}

	public void setPnlDrawing (DrawingView view) {
		this.view = view;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JToggleButton getTglbtnDrawing() {
		return tglbtnDrawing;
	}

	public void setTglbtnDrawing(JToggleButton tglbtnDrawing) {
		this.tglbtnDrawing = tglbtnDrawing;
	}

	public JToggleButton getTglbtnSelecting() {
		return tglbtnSelecting;
	}

	public void setTglbtnSelecting(JToggleButton tglbtnSelecting) {
		this.tglbtnSelecting = tglbtnSelecting;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnHexagon = tglbtnHexagon;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}
	public JButton getInnerColor() {
		return btnInnerColor;
	}
	public JButton getOuterColor() {
		return btnOuterColor;
	}

}
