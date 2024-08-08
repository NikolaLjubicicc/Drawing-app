package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import adapter.HexagonAdapter;
import geometry.Point;


public class HexagonDlg extends JDialog{

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JButton btnOuterColor;
	private JButton btnInnerColor;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtRadius;
	private HexagonAdapter hexagon = null;
	private Color outerColor = null, innerColor = null;

	
	
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HexagonDlg hdlg=new HexagonDlg();
					hdlg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public HexagonDlg() {
		setBounds(100, 100, 324, 369);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblCenterX = new JLabel("Center X : ");
		JLabel lblCenterY = new JLabel("Center Y : ");
		JLabel lblRadius = new JLabel("Radius : ");
		txtX = new JTextField();
		txtX.setColumns(10);
		txtY = new JTextField();
		txtY.setColumns(10);
		txtRadius = new JTextField();
		txtRadius.setColumns(10);
		btnOuterColor = new JButton("Outer color");
		btnOuterColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outerColor = JColorChooser.showDialog(null, "Choose the outer color", outerColor);
				btnOuterColor.setBackground(outerColor);
				if (outerColor == null) outerColor = Color.BLACK;
			}
		});
		btnInnerColor = new JButton("Inner color");
		btnInnerColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				innerColor = JColorChooser.showDialog(null, "Choose the inner color", innerColor);
				btnInnerColor.setBackground(innerColor);
				if (innerColor == null) innerColor = Color.BLACK;
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblCenterY, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblCenterX, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(92)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnOuterColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnInnerColor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))))
					.addContainerGap(69, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCenterX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCenterY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRadius)
						.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addComponent(btnOuterColor)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnInnerColor)
					.addContainerGap(45, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							int X = Integer.parseInt(txtX.getText());
							int Y = Integer.parseInt(txtY.getText());
							int radius = Integer.parseInt(txtRadius.getText());

							if(X < 0 || Y < 0 || radius < 1) {
								JOptionPane.showMessageDialog(null, "Invalid values!", "Error!", JOptionPane.ERROR_MESSAGE);
								return;
							}
							hexagon = new HexagonAdapter(new Point(X, Y), radius, false, outerColor,innerColor);
							dispose();
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Invalid values!", "Error!", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(66)
						.addComponent(okButton)
						.addGap(56)
						.addComponent(cancelButton)
						.addGap(200))
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(okButton)
							.addComponent(cancelButton))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			buttonPane.setLayout(gl_buttonPane);
		}
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JButton getBtnOuterColor() {
		return btnOuterColor;
	}

	public void setBtnOuterColor(JButton btnOuterColor) {
		this.btnOuterColor = btnOuterColor;
	}

	public JButton getBtnInnerColor() {
		return btnInnerColor;
	}

	public void setBtnInnerColor(JButton btnInnerColor) {
		this.btnInnerColor = btnInnerColor;
	}

	public JTextField getTxtX() {
		return txtX;
	}

	public void setTxtX(JTextField txtX) {
		this.txtX = txtX;
	}

	public JTextField getTxtY() {
		return txtY;
	}

	public void setTxtY(JTextField txtY) {
		this.txtY = txtY;
	}

	public JTextField getTxtRadius() {
		return txtRadius;
	}

	public void setTxtRadius(JTextField txtRadius) {
		this.txtRadius = txtRadius;
	}

	public HexagonAdapter getHexagon() {
		return hexagon;
	}

	public void setHexagon(HexagonAdapter hexagon) {
		this.hexagon = hexagon;
		txtX.setText("" + hexagon.getX());
		txtY.setText("" + hexagon.getY());
		txtRadius.setText("" + hexagon.getR());
		outerColor = hexagon.getColor();
		innerColor = hexagon.getInnerColor();
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
	public void setPoint(Point point) {
		txtX.setText("" + point.getX());
		txtY.setText("" + point.getY());
	}
	public void setColors(Color outerColor, Color innerColor) {
		this.outerColor = outerColor;
		this.innerColor = innerColor;
	}

}
