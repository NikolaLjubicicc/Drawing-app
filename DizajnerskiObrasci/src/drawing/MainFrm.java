package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import drawing.CircleDlg;
import drawing.DonutDlg;
import drawing.PointDlg;
import drawing.RectangleDlg;
import drawing.MainFrm;
import drawing.LineDlg;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.PnlDrawing;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
public class MainFrm extends JFrame {
	private JPanel contentPane;
	private int drawing=1;
	private int selecting=0;
	private int activity=drawing;
	private Color outerColor=Color.WHITE,innerColor= Color.BLACK;
	boolean lineWaitingForEndPoint=false;
	private Point startPoint;
	private PnlDrawing PnlDrawing=new PnlDrawing();
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
	
	private final JLabel lblInfo = new JLabel("");
	private final JButton btnUndo = new JButton("Undo");
	private final JButton btnRedo = new JButton("Redo");
	private final JButton btnToFront = new JButton("To front");
	private final JButton btnBringToFront = new JButton("Bring to front");
	private final JButton btnToBack = new JButton("To back");
	private final JButton btnBringToBack = new JButton("Bring to back");
	JTextArea logTextArea = new JTextArea();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm frame = new MainFrm();
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
	public MainFrm() {
		setTitle("Nikola Ljubicic IT 44/2021");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		PnlDrawing.setBackground(new Color(255, 255, 255));
		PnlDrawing.setBounds(10, 47, 560, 557);
		PnlDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point click=new Point(e.getX(),e.getY());
				PnlDrawing.deselect();
				if(activity==selecting) {
					try {
						PnlDrawing.select(click);
						lblInfo.setText(PnlDrawing.getShape(PnlDrawing.getSelected()).toString());
						return;
					}
					catch(Exception exc) {
						lblInfo.setText("");
						JOptionPane.showMessageDialog(PnlDrawing, "Please select an object!");
						
					}
				}
				if(tglbtnPoint.isSelected()) {
					PointDlg pdlg=new PointDlg();
					pdlg.setPoint(click);
					pdlg.setColors(outerColor);
					pdlg.setVisible(true);
					if(pdlg.getPoint()!=null) {
						PnlDrawing.newShape(pdlg.getPoint());
					}
					return;
				}
				else if (tglbtnLine.isSelected()) {
					if(lineWaitingForEndPoint) {
						LineDlg ldlg = new LineDlg();
						Line l = new Line(startPoint,click);
						ldlg.setLine(l);
						ldlg.setColors(outerColor);
						ldlg.setVisible(true);
						if(ldlg.getLine()!= null) {
							PnlDrawing.newShape(ldlg.getLine());
						}
						lineWaitingForEndPoint=false;
						return;
					}
					startPoint=click;
					lineWaitingForEndPoint=true;
			
				}
				else if(tglbtnRectangle.isSelected()) {
					RectangleDlg rdlg=new RectangleDlg();
					rdlg.setPoint(click);
					rdlg.setColors(outerColor, innerColor);
					rdlg.setVisible(true);
					if(rdlg.getRectangle()!=null) {
						PnlDrawing.newShape(rdlg.getRectangle());
					}
					return;
				}
				else if(tglbtnCircle.isSelected()) {
					CircleDlg cdlg=new CircleDlg();
					cdlg.setPoint(click);
					cdlg.setColors(outerColor, innerColor);
					cdlg.setVisible(true);
					if(cdlg.getCircle()!=null) {
						PnlDrawing.newShape(cdlg.getCircle());
					}
					return;
				}
				else if(tglbtnDonut.isSelected()){
					DonutDlg ddlg=new DonutDlg();
					ddlg.setPoint(click);
					ddlg.setColors(outerColor, innerColor);
					ddlg.setVisible(true);
					if(ddlg.getDonut()!=null) {
						PnlDrawing.newShape(ddlg.getDonut());
					}
					return;
				}
			}
		});
		contentPane.setLayout(null);
		
		contentPane.add(PnlDrawing);
		PnlDrawing.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel sidepnl = new JPanel();
		sidepnl.setBounds(580, 5, 125, 599);
		contentPane.add(sidepnl);
		
		tglbtnDrawing.setSelected(true);
		setDrawing();
		
	
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
		btnToFront.setEnabled(false);
		btnBringToFront.setEnabled(false);
		btnToBack.setEnabled(false);
		btnBringToBack.setEnabled(false);
		btnUndo.setEnabled(false);
		btnRedo.setEnabled(false);
		tglbtnDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDrawing();
			}
		});
		tglbtnSelecting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectingShapes();
				tglbtnPoint.setSelected(false);
				tglbtnLine.setSelected(false);
				tglbtnRectangle.setSelected(false);
				tglbtnCircle.setSelected(false);
				tglbtnDonut.setSelected(false);
			}
		});
		
		btnModify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int i=PnlDrawing.getSelected();
				if(i==-1) {
					return;
				}
				Shape s=PnlDrawing.getShape(i);
				if(s instanceof Point) {
					PointDlg pdlg=new PointDlg();
					pdlg.setPoint((Point)s);
					pdlg.setVisible(true);
					if(pdlg.getPoint()!=null) {
						PnlDrawing.setShape(i, pdlg.getPoint());
						PnlDrawing.repaint();
					}
				}
				else if(s instanceof Line) {
					LineDlg ldlg=new LineDlg();
					ldlg.setLine((Line)s);
					ldlg.setVisible(true);
					if(ldlg.getLine() != null) {
						PnlDrawing.setShape(i, ldlg.getLine());
						PnlDrawing.repaint();
					}}
				else if(s instanceof Rectangle) {
					RectangleDlg rdlg=new RectangleDlg();
					rdlg.setRectangle((Rectangle) s);
					rdlg.setVisible(true);
					if(rdlg.getRectangle()!=null) {
						PnlDrawing.setShape(i, rdlg.getRectangle());
						PnlDrawing.repaint();
					}
				}
				else if(s instanceof Donut) {
					DonutDlg ddlg=new DonutDlg();
					ddlg.setDonut((Donut) s);
					ddlg.setVisible(true);
					if(ddlg.getDonut()!=null) {
						PnlDrawing.setShape(i, ddlg.getDonut());
						PnlDrawing.repaint();
					}
				}
				else if(s instanceof Circle) {
					CircleDlg cdlg=new CircleDlg();
					cdlg.setCircle((Circle) s);
					cdlg.setVisible(true);
					if(cdlg.getCircle()!=null) {
						PnlDrawing.setShape(i, cdlg.getCircle());
						PnlDrawing.repaint();
					}
				}
				
			}});
			
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PnlDrawing.isEmpty()) return;
				if (JOptionPane.showConfirmDialog(null, "Do you really want to delete the selected shape?", "Yes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
					PnlDrawing.remove();
				lblInfo.setText("");
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
		
		JPanel pnltxt = new JPanel();
		pnltxt.setBounds(5, 663, 700, 10);
		contentPane.add(pnltxt);
		
		pnltxt.add(lblInfo);
		
		
		logTextArea.setBackground(new Color(210, 210, 210));
		logTextArea.setBounds(10, 614, 695, 39);
		contentPane.add(logTextArea);
		
		JButton btnInnerColor = new JButton("Inner color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Choose the inner color", innerColor);
				btnInnerColor.setBackground(innerColor);
				if (innerColor == null) innerColor = Color.BLACK;
			}
		});
		btnInnerColor.setBackground(Color.BLACK);
		btnInnerColor.setForeground(new Color(0, 0, 0));
		btnInnerColor.setBounds(96, 16, 125, 21);
		contentPane.add(btnInnerColor);
		
		JButton btnOuterColor = new JButton("Outer color");
		btnOuterColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outerColor = JColorChooser.showDialog(null, "Choose the outer color", outerColor);
				btnOuterColor.setBackground(outerColor);
				if (outerColor == null) outerColor = Color.BLACK;
				
			}
		});
		btnOuterColor.setBackground(Color.WHITE);
		btnOuterColor.setBounds(354, 16, 125, 21);
		contentPane.add(btnOuterColor);
	}

			private void setDrawing() {
				tglbtnSelecting.setSelected(false);
				activity=drawing;
				PnlDrawing.deselect();
				btnModify.setEnabled(false);
				btnDelete.setEnabled(false);
				tglbtnPoint.setEnabled(true);
				tglbtnPoint.setSelected(false);
				tglbtnLine.setEnabled(true);
				tglbtnRectangle.setEnabled(true);
				tglbtnCircle.setEnabled(true);
				tglbtnDonut.setEnabled(true);
			}
			
			private void setSelectingShapes() {
				tglbtnDrawing.setSelected(false);
				activity=selecting;
				btnModify.setEnabled(true);
				btnDelete.setEnabled(true);
				tglbtnPoint.setEnabled(false);
				tglbtnLine.setEnabled(false);
				tglbtnRectangle.setEnabled(false);
				tglbtnCircle.setEnabled(false);
				tglbtnDonut.setEnabled(false);
				tglbtnHexagon.setEnabled(false);
				
			}
}


