package UI;

import java.util.ArrayList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

import Domain.Director;
import Domain.GeneralStaff;
import Domain.Handler;
import Domain.Leave;
import Domain.Main;
import Domain.NextHandler;
import Domain.NextHandler2;
import Domain.LeaveData;
import Domain.Staff;
import Domain.Supervisor;

public class EndorsePanel extends JFrame {

	private String curLeaveIndex;
	private JTable tblLeave = new JTable();

	JPanel pnlLeaveCtTop;

	boolean firstRun = true;
	private ArrayList<Leave> avaLeaveList = new ArrayList<Leave>();

	private LeaveData curLeaveData = new LeaveData();

	private Staff curStaff = new GeneralStaff();

	private JLabel lblSelLeaveID = new JLabel();

	public EndorsePanel(String loginStaff) {
		this.setSize(1024, 768);
		this.setLocation(100, 100);

		/**
		 * initial related data
		 */
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

		/**
		 * end of initial data
		 */

		avaLeaveList = Main.leavesData.getAllEndorseRecord(curStaff.getName());
		curLeaveData.setAllLeave(avaLeaveList);

		LayoutManager flayout = new FlowLayout();
		pnlLeaveCtTop = new JPanel(flayout);
		pnlLeaveCtTop.setBackground(Color.gray);

		pnlLeaveCtTop.add(new JLabel("Selected Leave ID:"));

		pnlLeaveCtTop.add(lblSelLeaveID);
		JButton btnConfirm = new JButton("Endorse Application");

		// Listen for actions on buttons.
		btnConfirm.addActionListener(new EndorseLeave());

		btnConfirm.setToolTipText("Click this button to Endrose the application.");
		pnlLeaveCtTop.add(btnConfirm);

		JButton btnReject = new JButton("Decline Application");

		// Listen for actions on buttons.
		btnReject.addActionListener(new RejectLeave());

		btnReject.setToolTipText("Click this button to decline the application.");
		pnlLeaveCtTop.add(btnReject);

		pnlLeaveCtTop.setVisible(false);

		LayoutManager blayout = new BorderLayout();
		JPanel pnlLeave = new JPanel(blayout);

		LayoutManager blayout2 = new BorderLayout();
		JPanel pnlLeaveCt = new JPanel(blayout2);

		pnlLeaveCt.add(pnlLeaveCtTop, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane(tblLeave);
		setUpTableLeave(tblLeave);

		pnlLeaveCt.add(scrollPane, BorderLayout.NORTH);

		pnlLeave.add(pnlLeaveCt, BorderLayout.CENTER);

		pnlLeaveCt.setVisible(true);
		pnlLeaveCtTop.setVisible(true);

		pnlLeave.setVisible(true);

		this.add(pnlLeave);
		this.setVisible(true);

		checkEndorseNotice();
	}

	private void checkEndorseNotice() {
		ArrayList<Leave> leaveList = new ArrayList<Leave>();

		leaveList = avaLeaveList;

		if (leaveList.size() > 0) {
			for (int i = 0; i < leaveList.size(); i++) {
				curLeaveIndex = Integer.toString(leaveList.get(i).getID());

				String leaveDesc = leaveList.get(i).getApplicant() + " applied a holiday from "
						+ leaveList.get(i).getDateX().toString() + " to " + leaveList.get(i).getDateY().toString();

				// Custom button text
				Object[] options = { "Endorse", "Decline" };
				int n = JOptionPane.showOptionDialog(null, leaveDesc, "Make your endorement",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

				switch (n) {
				case JOptionPane.YES_OPTION:
					approveLeave(Integer.parseInt(curLeaveIndex));
					break;
				case JOptionPane.NO_OPTION:
					rejectLeave(Integer.parseInt(curLeaveIndex));
					break;
				}
			}
		}

	}

	private void setUpTableLeave(JTable stblLeave) {
		DefaultTableModel tableModel = new DefaultTableModel(0, 0);

		String header[] = new String[] { "ID", "Applicant", "Date From", "Date To", "Supervisor", "Status" };

		// add header in table model
		tableModel.setColumnIdentifiers(header);
		// set model into the table object
		stblLeave.setModel(tableModel);

		tableModel.setRowCount(0);

		ArrayList<Leave> leaveList = new ArrayList<Leave>();

		leaveList = avaLeaveList;
		// System.out.println(leaveList.size());
		if (leaveList.size() > 0) {
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

		stblLeave.setRowSelectionAllowed(true);
		ListSelectionModel rowSelectionModel = stblLeave.getSelectionModel();
		rowSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		/**
		 * add the row select listener
		 */
		rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {

				int[] selectedRow = stblLeave.getSelectedRows();

				for (int i = 0; i < selectedRow.length; i++) {
					curLeaveIndex = (String) stblLeave.getValueAt(selectedRow[i], 0);
					lblSelLeaveID.setText(curLeaveIndex);
					break;
				}

				// System.out.println("Selected: " + curStaff);
			}
		});

		tableModel.fireTableDataChanged();
	}

	private void approveLeave(int leaveID) {
		// JOptionPane.showMessageDialog(null, curStaff.getSupervisor());

		Handler handler1 = new NextHandler();
		Handler handler2 = new NextHandler2();

		handler1.setNext(handler2);
		handler1.process(curStaff, curLeaveData, Integer.parseInt(curLeaveIndex), "Approve");

		// update the grid
		setUpTableLeave(tblLeave);
	}

	private void rejectLeave(int leaveID) {
		curLeaveData.updateStatus(Integer.parseInt(curLeaveIndex), "Decline");
		Domain.Main.leavesData.updateStatus(Integer.parseInt(curLeaveIndex), "Decline");

		// update the grid
		setUpTableLeave(tblLeave);
	}

	/// endorse leave
	private class EndorseLeave implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			approveLeave(Integer.parseInt(curLeaveIndex));
		}
	}

	/// endorse leave
	private class RejectLeave implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			rejectLeave(Integer.parseInt(curLeaveIndex));
		}
	}
}
