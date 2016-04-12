package UI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Domain.Director;
import Domain.GeneralStaff;
import Domain.Main;
import Domain.Staff;
import Domain.Supervisor;

@SuppressWarnings("serial")
public class LoginPanel extends JFrame {

	public LoginPanel(String type) {
		this.setSize(500, 300);
		this.setLocation(500, 300);

		JPanel aPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel();

		if (type == "staff") {
			topPanel.add(new JLabel("Staff Profile"));
		} else if (type == "leave") {
			topPanel.add(new JLabel("Leave Application"));
		} else {
			topPanel.add(new JLabel("Leave Endorsement"));
		}

		aPanel.add(topPanel, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel(new GridLayout(8, 3, 5, 10));
		for (int i = 0; i < 6; i++) {
			centerPanel.add(new JPanel());
		}
		centerPanel.add(new JLabel("Username", SwingConstants.RIGHT));
		final JTextField usernameTextField = new JTextField("", 20);
		centerPanel.add(usernameTextField);
		centerPanel.add(new JPanel());
		centerPanel.add(new JLabel("Password", SwingConstants.RIGHT));
		final JPasswordField passwordTextField = new JPasswordField("", 20);
		centerPanel.add(passwordTextField);
		centerPanel.add(new JPanel());
		centerPanel.add(new JPanel());
		JButton button = new JButton("Login");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Staff loginStaff;

				if (Main.staffsData.getRole(usernameTextField.getText()).equals("Director")) {
					loginStaff = new Director();
				} else if (Main.staffsData.getRole(usernameTextField.getText()).equals("Supervisor")) {
					loginStaff = new Supervisor();
				} else {
					loginStaff = new GeneralStaff();
				}

				loginStaff.setName(usernameTextField.getText());
				loginStaff.setRole(Domain.Main.staffsData.getRole(usernameTextField.getText()));
				loginStaff.setPass(passwordTextField.getText());

				if (type.equals("staff")) {
					if (loginStaff.validStaffLoginHR()) {
						ManageStaffPanel managestaffpanel = new ManageStaffPanel();
						LoginPanel.this.dispose();
					} else {
						JOptionPane.showMessageDialog(LoginPanel.this, "Wrong username or password!");
					}
				} else if (type.equals("leave")) {
					if (loginStaff.validStaffLogin()) {
						if (!loginStaff.rightForLeaveApp()) {
							JOptionPane.showMessageDialog(null,
									"Director, " + loginStaff.getName() + " cannot use this leave application.");
						} else {
							LeavePanel leavepanel = new LeavePanel(usernameTextField.getText());
							LoginPanel.this.dispose();
						}
					} else {
						JOptionPane.showMessageDialog(LoginPanel.this, "Wrong username or password!");
					}

				} else {
					if (loginStaff.validStaffLogin()) {
						if (!loginStaff.rightForEndorsement()) {
							JOptionPane.showMessageDialog(null,
									"Director, " + loginStaff.getName() + " cannot use this leave application.");
						} else {
							EndorsePanel endorsepanel = new EndorsePanel(usernameTextField.getText());
							LoginPanel.this.dispose();
						}
					} else {
						JOptionPane.showMessageDialog(LoginPanel.this, "Wrong username or password!");
					}
				}
			}
		});
		centerPanel.add(button);
		centerPanel.add(new JPanel());
		for (int i = 0; i < 9; i++) {
			centerPanel.add(new JPanel());
		}
		aPanel.add(centerPanel, BorderLayout.CENTER);

		this.add(aPanel);
		this.setVisible(true);
	}
}
