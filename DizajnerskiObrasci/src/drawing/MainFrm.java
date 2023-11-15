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
public class MainFrm extends JFrame {
	private JPanel contentPane;
	private int drawing=1;
	private int selecting=0;
	private int activity=drawing;
	private Color outerColor=Color.BLACK,innerColor= Color.WHITE;
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
	private final JLabel lblInfo = new JLabel("");
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
		setBounds(100, 100, 789, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
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
		
		contentPane.add(PnlDrawing, BorderLayout.CENTER);
		
		JPanel sidepnl = new JPanel();
		contentPane.add(sidepnl, BorderLayout.EAST);
		
		tglbtnDrawing.setSelected(true);
		setDrawing();
		GroupLayout gl_sidepnl = new GroupLayout(sidepnl);
		gl_sidepnl.setHorizontalGroup(
			gl_sidepnl.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_sidepnl.createSequentialGroup()
					.addGroup(gl_sidepnl.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_sidepnl.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_sidepnl.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_sidepnl.createParallelGroup(Alignment.LEADING)
								.addComponent(tglbtnDrawing, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
								.addComponent(btnModify, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))))
					.addGap(28))
				.addGroup(gl_sidepnl.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglbtnSelecting, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(gl_sidepnl.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglbtnPoint, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(gl_sidepnl.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglbtnLine, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(gl_sidepnl.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglbtnRectangle, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(gl_sidepnl.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglbtnCircle, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
				.addGroup(gl_sidepnl.createSequentialGroup()
					.addContainerGap()
					.addComponent(tglbtnDonut, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
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
		
		gl_sidepnl.setVerticalGroup(
			gl_sidepnl.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_sidepnl.createSequentialGroup()
					.addGap(59)
					.addComponent(tglbtnDrawing)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnSelecting)
					.addGap(69)
					.addComponent(btnModify)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDelete)
					.addGap(62)
					.addComponent(tglbtnPoint)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnLine)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnRectangle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnCircle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnDonut)
					.addContainerGap(68, Short.MAX_VALUE))
		);
		sidepnl.setLayout(gl_sidepnl);
		
		JPanel pnltxt = new JPanel();
		contentPane.add(pnltxt, BorderLayout.SOUTH);
		
		pnltxt.add(lblInfo);
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
				
			}
}


