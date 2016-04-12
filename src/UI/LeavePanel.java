package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Domain.Director;
import Domain.GeneralStaff;
import Domain.Leave;
import Domain.Main;
import Domain.LeaveData;
import Domain.Staff;
import Domain.Supervisor;
//import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
//import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
//import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class LeavePanel extends JFrame {

	// private JDatePickerImpl txtDateFrom;
	// private JDatePickerImpl txtDateTo;

	private JTextField txtDateFrom;
	private JTextField txtDateTo;

	private JTable tblLeave = new JTable();

	private Staff curStaff = new GeneralStaff();

	private LeaveData curLeaveData = new LeaveData();

	ArrayList<Leave> curLeaveList = new ArrayList<Leave>();

	public LeavePanel(String loginStaff) {
		try {
			this.setSize(1024, 768);
			this.setLocation(100, 100);

			if (Main.staffsData.getRole(loginStaff).equals("Director")) {
				curStaff = new Director();
			} else if (Main.staffsData.getRole(loginStaff).equals("Supervisor")) {
				curStaff = new Supervisor();
			} else {
				curStaff = new GeneralStaff();
			}

			curStaff.setName(loginStaff);
			curStaff.setRole(Main.staffsData.getRole(loginStaff));
			curStaff.setSupervisor(Main.staffsData.getSupervisor(loginStaff));
			System.out.println(Main.staffsData.getRole(loginStaff));

			curLeaveList = curLeaveData.getAllOwnRecord(curStaff.getName());

			LayoutManager flayout = new FlowLayout();
			JPanel pnlLeaveCtTop = new JPanel(flayout);

			JLabel lblDateX = new JLabel();
			lblDateX.setText("Date From: ");

			// UtilDateModel model = new UtilDateModel();
			// JDatePanelImpl datePanel = new JDatePanelImpl(model);
			// txtDateFrom = new JDatePickerImpl(datePanel);
			txtDateFrom = new JTextField("", 20);
			pnlLeaveCtTop.add(lblDateX);
			pnlLeaveCtTop.add(txtDateFrom);

			JLabel lblDateY = new JLabel();
			lblDateY.setText("Date To: ");
			// UtilDateModel model2 = new UtilDateModel();
			// JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
			// txtDateTo = new JDatePickerImpl(datePanel2);
			txtDateTo = new JTextField("", 20);

			pnlLeaveCtTop.add(lblDateY);
			pnlLeaveCtTop.add(txtDateTo);

			JButton btnConfirm = new JButton("Confirm Application");

			// Listen for actions on buttons.
			btnConfirm.addActionListener(new ActionAddLeave());

			btnConfirm.setToolTipText("Click this button to confirm the application.");
			pnlLeaveCtTop.add(btnConfirm);

			LayoutManager blayout = new BorderLayout();
			JPanel pnlLeave = new JPanel(blayout);

			LayoutManager blayout2 = new BorderLayout();
			JPanel pnlLeaveCt = new JPanel(blayout2);

			pnlLeaveCt.add(pnlLeaveCtTop, BorderLayout.NORTH);

			JScrollPane scrollPane = new JScrollPane(tblLeave);
			setUpTableLeave(tblLeave);

			pnlLeaveCt.add(scrollPane, BorderLayout.CENTER);

			pnlLeave.add(pnlLeaveCt, BorderLayout.CENTER);

			pnlLeaveCt.setVisible(true);
			pnlLeaveCtTop.setVisible(true);
			pnlLeave.setVisible(true);

			this.add(pnlLeave);
			this.setVisible(true);

			checkNotice();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Notice", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void checkNotice() {
		ArrayList<Leave> leaveList = new ArrayList<Leave>();
		leaveList = curLeaveList;

		if (leaveList != null && leaveList.size() > 0) {
			for (int i = 0; i < leaveList.size(); i++) {
				if (!leaveList.get(i).getLeaveStatus().equals("Waiting for endorsement")) {
					String noticeDesc = "Your holiday which from " + leaveList.get(i).getDateX().toString() + " to "
							+ leaveList.get(i).getDateY().toString() + " has " + leaveList.get(i).getLeaveStatus();

					JOptionPane.showMessageDialog(null, noticeDesc, "Notice", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}

	}

	private void setUpTableLeave(JTable jTable) {
		DefaultTableModel tableModel = new DefaultTableModel(0, 0);

		String header[] = new String[] { "ID", "Applicant", "Date From", "Date To", "Supervisor", "Status" };

		// add header in table model
		tableModel.setColumnIdentifiers(header);
		// set model into the table object
		jTable.setModel(tableModel);

		tableModel.setRowCount(0);

		ArrayList<Leave> leaveList = new ArrayList<Leave>();

		leaveList = curLeaveList;
		if (leaveList != null && leaveList.size() > 0) {
			for (int i = 0; i < leaveList.size(); i++) {
				String[] data = new String[6];

				data[0] = Integer.toString(leaveList.get(i).getID());
				data[1] = leaveList.get(i).getApplicant();
				data[2] = leaveList.get(i).getDateX().toString();
				data[3] = leaveList.get(i).getDateY().toString();
				data[4] = leaveList.get(i).getSupervisor();
				data[5] = leaveList.get(i).getLeaveStatus();

				tableModel.addRow(data);
			}
		}

		tableModel.fireTableDataChanged();
	}

	/**
	 * add Leave update
	 */
	private class ActionAddLeave implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Leave newLeave = new Leave();
				Main.leaveID++;
				newLeave.setID(Main.leaveID);
				// newLeave.setDateX((Date) txtDateFrom.getModel().getValue());
				// newLeave.setDateY((Date) txtDateTo.getModel().getValue());
				// SimpleDateFormat formatter = new
				// SimpleDateFormat("yyyy-MMM-dd");

				newLeave.setDateX(txtDateFrom.getText());
				newLeave.setDateY(txtDateTo.getText());

				newLeave.setApplicant(curStaff.getName());

				newLeave.setSupervisor(Domain.Main.staffsData.getSupervisor(curStaff.getName()));
				newLeave.setLeaveStatus("Waiting for endorsement");

				Domain.Main.leavesData.addNewLeave(newLeave);
				// curLeaveData.addNewLeave(newLeave);
				curLeaveList.add(newLeave);
				// update the grid
				setUpTableLeave(tblLeave);
				// txtDateFrom.getJFormattedTextField().setText("");
				// txtDateTo.getJFormattedTextField().setText("");

				txtDateFrom.setText("");
				txtDateTo.setText("");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Notice", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
}
